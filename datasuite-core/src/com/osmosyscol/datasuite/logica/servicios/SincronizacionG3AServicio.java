package com.osmosyscol.datasuite.logica.servicios;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.cocoon.environment.Session;
import org.apache.commons.collections.CollectionUtils;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.AutenticacionServicio;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.dto.Credencial;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.logica.dto.TipoDocumento;
import com.osmosyscol.datasuite.persistencia.dao.PersonaDao;
import com.osmosyscol.g3a.cliente.entidades.IDUsuario;
import com.osmosyscol.g3a.cliente.entidades.Usuario;
import com.osmosyscol.g3a.servicios.AutenticacionServicioG3A;
import com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.RolesUsuarioSOAPStub;
import com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoEntradalistarRolesporLogin;
import com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarRolesporLogin;
import com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradalistarRolesporLogin;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class SincronizacionG3AServicio {

	private static SincronizacionG3AServicio instance;
	
	private static String ROLES_USUARIO = "/ws/RolesUsuario";

	private SincronizacionG3AServicio() {
	}
	
	public static SincronizacionG3AServicio getInstance() {
		if (instance == null) {
			instance = new SincronizacionG3AServicio();
		}
		return instance;
	}

	/***
	 * Retorna la persona de acuerdo al login,
	 * si no esta registrada en fidupagos debe crearla de acuerdo a la informacion 
	 * en G3A
	 * @param login
	 * @param session 
	 * @return
	 */
	public Persona obtenerPersona(String login, Session session) {

		try {
			
			Persona personalogin = PersonaServicio.getInstance().obtenerPersonaPorLogin(login);
			if(personalogin != null){
				actualizarPersona(login, session, personalogin);
				return personalogin;
			}
			personalogin = registrarPersona(login, session);
			return personalogin;
		} catch (Throwable e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;
	}

	/***
	 * Se actualiza la información del usuario de acuerdo a los roles de G3A
	 * @param login
	 * @param session
	 * @param personalogin
	 */
	private void actualizarPersona(String login, Session session, Persona personalogin) {
		
		DaoManager daoManager = null;
		try {
			
			List<Integer> roles = obtenerRolesPorLogin(login);
			
			Boolean esCliente = !roles.contains(Constantes.MAP_TABLAS_ROLNUM.get(Constantes.ROL_DIRECTOR_NEGOCIO)) && 
								!roles.contains(Constantes.ROL_ADMIN_WEBDATA);
			
			if(CollectionUtils.isNotEmpty(roles)){
				daoManager = DaoConfig.getDaoManager();
				daoManager.startTransaction();
				
				PersonaDao personaDao = (PersonaDao) daoManager.getDao(PersonaDao.class);
				
				
				Usuario usuario = (Usuario) session.getAttribute(AutenticacionServicioG3A.G3A_USUARIO);
				IDUsuario idUsuario = usuario.getIdUsuario();
				
				TipoDocumento tipoDocumento = TipoDocumentoServicio.getInstance().buscarTipoDocumentoG3A(idUsuario.getTipoIdCliente());
				
				Integer id_usuario = null;
				
				if (esCliente){
					com.osmosyscol.datasuite.logica.dto.Usuario cliente = UsuarioServicio.getInstance().obtenerUsuarioPorIdentificacion(idUsuario.getNumeroIdCliente(), tipoDocumento.getId());
					id_usuario = cliente.getId_usuario(); 
				}
				
				//Desactivar todos los roles
				Set keySet = Constantes.MAP_TABLAS_ROLNUM.keySet();
				for (Object object : keySet) {
					String rol = (String)object;
					if(Constantes.MAP_TABLAS_ROL.containsKey(rol) ){
						if(Constantes.ROL_ADMINISTRATIVO.equals(rol) || Constantes.ROL_DIRECTOR_NEGOCIO.equals(rol)){
							personaDao.activarPersonaUsuario(personalogin.getId_persona(), null, Constantes.NO, rol, Constantes.NO);
						}else{
							personaDao.activarPersonaUsuario(personalogin.getId_persona(), id_usuario, Constantes.NO, rol, Constantes.NO);
						}
							
					}
				}
				
				//Activar  o crear los roles de acuerdo a G3A
				for (Integer rolNum : roles) {
					String rol = (String)Constantes.MAP_TABLAS_ROLNUM.getKey(rolNum);
					
					if(rol != null){
						Boolean existe = personaDao.existeRelacionPersonaUsuarioRol(personalogin.getId_persona(), id_usuario, rol);
						if(existe){
							personaDao.activarPersonaUsuario(personalogin.getId_persona(), id_usuario, Constantes.SI, rol, Constantes.NO);
						}else{
							personaDao.guardarPersonaUsuario(personalogin.getId_persona(), id_usuario, rol);
						}
					}
				}
				
				SimpleLogger.setInfo("Se asociaron los roles al usuario: " + login);
				
				daoManager.commitTransaction();
			}
			
		} catch (Throwable e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}finally{
			if(daoManager != null){
				daoManager.endTransaction();
			}
		}
		
	}

	/***
	 * Se registra la persona y el cliente si no existe
	 * @param login
	 * @param session
	 * @return
	 */
	private Persona registrarPersona(String login, Session session) {
		DaoManager daoManager = null;
		try {
			List<Integer> roles = obtenerRolesPorLogin(login);
			
			Boolean esCliente = !roles.contains(Constantes.MAP_TABLAS_ROLNUM.get(Constantes.ROL_DIRECTOR_NEGOCIO)) && 
								!roles.contains(Constantes.ROL_ADMIN_WEBDATA);
			
			//Usuario G3A
			Usuario usuario = (Usuario) session.getAttribute(AutenticacionServicioG3A.G3A_USUARIO);
			IDUsuario idUsuario = usuario.getIdUsuario();
			
			//Crear la persona natural
			Persona personaNatural = new Persona();
			personaNatural.setNombre(usuario.getNombresUsuario());
			personaNatural.setApellido(usuario.getApellidosUsuario());
			personaNatural.setIdentificacion(idUsuario.getNumeroIdUsuario());
			
			TipoDocumento tipoDocumento = TipoDocumentoServicio.getInstance().buscarTipoDocumentoG3A(idUsuario.getTipoIdUsuario());
			
			if (tipoDocumento == null) {
				SimpleLogger.setError("No se encuentra el tipo de documento: " + idUsuario.getTipoIdUsuario());
				return null;
			}
			
			personaNatural.setTipo_documento(tipoDocumento.getId());
			personaNatural.setCorreo(usuario.getCorreo());
			personaNatural.setGenero("M");
			personaNatural.setTipo_persona(Constantes.PERSONA_NATURAL);

			//Crear el cliente
			Persona personaCliente = null; 
			
			if(!idUsuario.getNumeroIdUsuario().equals(idUsuario.getNumeroIdCliente()) && esCliente){
				personaCliente = new Persona();
				personaCliente.setNombre(usuario.getNombresCliente());
				personaCliente.setApellido(usuario.getApellidosCliente());
				personaCliente.setIdentificacion(idUsuario.getNumeroIdCliente());
				personaCliente.setCorreo(usuario.getCorreo());
				
				tipoDocumento = TipoDocumentoServicio.getInstance().buscarTipoDocumentoG3A(idUsuario.getTipoIdCliente());
				if(tipoDocumento == null){
					SimpleLogger.setError("No se encontro el tipo documento " + idUsuario.getTipoIdCliente());
					return null;
				}
				personaCliente.setTipo_documento(tipoDocumento.getId());
				personaCliente.setTipo_persona(Constantes.PERSONA_JURIDICA);
			}
			
			
			daoManager = DaoConfig.getDaoManager();
			daoManager.startTransaction();
			
			PersonaDao personaDao = (PersonaDao) daoManager.getDao(PersonaDao.class);
			
			personaNatural = personaDao.guardarPersona(personaNatural);
			
			com.osmosyscol.datasuite.logica.dto.Usuario cliente = new com.osmosyscol.datasuite.logica.dto.Usuario();

			if(personaCliente != null){
				personaCliente = personaDao.guardarPersona(personaCliente);
				cliente.setId_persona(personaCliente.getId_persona());
			}
			else{
				cliente.setId_persona(personaNatural.getId_persona());
			}
			
			Integer id_usuario = null;
			
			if(esCliente){
				id_usuario = UsuarioServicio.getInstance().guardarUsuarioTrans(cliente, daoManager);
			}
			
			Credencial credencial = new Credencial();
			credencial.setLogin(login);
			credencial.setId_persona(personaNatural.getId_persona());
			credencial.setId_usuario(id_usuario);
			credencial.setEstado(Constantes.CREDENCIAL_ESTADO_ACTIVO);
			personaDao.guardarCredencial(credencial);
			
			if(CollectionUtils.isNotEmpty(roles)){
				for (Integer rolNum : roles) {
					String rol = (String)Constantes.MAP_TABLAS_ROLNUM.getKey(rolNum);
					if(rol != null){
						personaDao.guardarPersonaUsuario(personaNatural.getId_persona(), id_usuario, rol);
					}
				}
				SimpleLogger.setInfo("Se asociaron los roles al usuario: " + login);
			}
			
			daoManager.commitTransaction();
			return personaNatural;
			
		} catch (Throwable e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}finally{
			if(daoManager != null){
				daoManager.endTransaction();
			}
		}
		
		
		return null;
	}

	private List<Integer> obtenerRolesPorLogin(String login) throws Exception {
		
		URL url = new URL(AutenticacionServicio.getEndpoint() + ROLES_USUARIO);
		RolesUsuarioSOAPStub stub = new RolesUsuarioSOAPStub(url, null);
		
		TipoEntradalistarRolesporLogin entrada = new TipoEntradalistarRolesporLogin();
		TipoElementoEntradalistarRolesporLogin elementoEntrada = new TipoElementoEntradalistarRolesporLogin();
		
		elementoEntrada.setLogin(login);
		
		entrada.setElementoEntrada(elementoEntrada);
		
		TipoElementoSalidalistarRolesporLogin[] salida = stub.listarRolesporLogin(entrada);
		
		List<Integer> id_roles= new ArrayList<Integer>();
		
		if(salida != null){
			for (TipoElementoSalidalistarRolesporLogin salidaListarRoles : salida) {
				id_roles.add(new Integer(salidaListarRoles.getId_rol()));
			}
		}
		return id_roles;
		
	}

}
