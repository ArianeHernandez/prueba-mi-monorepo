package com.osmosyscol.datasuite.logica.servicios;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cocoon.environment.Session;
import org.apache.cocoon.environment.SourceResolver;

import com.google.gson.Gson;
import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.mensajeria.EnviaMails;
import com.osmosyscol.commons.servicio.AutenticacionServicio;
import com.osmosyscol.commons.servicio.UsuarioServicio;
import com.osmosyscol.commons.utils.JavaToXML;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.dto.Administrador;
import com.osmosyscol.datasuite.logica.dto.Administrativo;
import com.osmosyscol.datasuite.logica.dto.Contenido;
import com.osmosyscol.datasuite.logica.dto.Credencial;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.logica.dto.Responsable;
import com.osmosyscol.datasuite.logica.dto.TipoDocumento;
import com.osmosyscol.datasuite.logica.dto.TipoProceso;
import com.osmosyscol.datasuite.logica.dto.TipoProcesoRol;
import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.datasuite.persistencia.dao.AdministradorDao;
import com.osmosyscol.datasuite.persistencia.dao.PersonaDao;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.datasuite.webdata.logica.servicios.HistorialSobreflexServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

import co.htsoft.commons.net.CallPage;
import co.htsoft.commons.rsa.SecureJsonUtils;

public class PersonaServicio {

	private static PersonaServicio personaServicio;

	private PersonaServicio() {
	}

	public static PersonaServicio getInstance() {
		if (personaServicio == null) {
			personaServicio = new PersonaServicio();
		}
		return personaServicio;
	}

	public Persona obtenerPersonaPorLogin(String login) {

		if (StringUtils.esVacio(login)) {
			return null;
		}

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			PersonaDao personaDao = (PersonaDao) daoManager.getDao(PersonaDao.class);
			return personaDao.obtenerPersonaPorLogin(login);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public Boolean loginDisponible(String login) {

		if (StringUtils.isBlank(login)) {
			return false;
		}

		if (obtenerCredencialPorLogin(login) != null) {
			return false;
		}

		{
			String val = ParametrosInicio.getProperty("sso.disponibilidadlogin.enpoint");

			if (StringUtils.isNotBlank(val)) {

				val = val.replace("{LOGIN}", login);

				System.out.println("URL: " + val);

				if (!StringUtils.esVerdad(new CallPage().call(val))) {
					return false;
				}

			}

		}

		return true;
	}

	public Persona obtenerPersonaPorIdentificacion(String identificacion, Integer tipoDocumento) {
		try {

			if (tipoDocumento == null) {
				tipoDocumento = Constantes.TIPODOCUMENTO_CEDULA;
			}

			PersonaDao personaDao = (PersonaDao) DaoConfig.getDao(PersonaDao.class);
			return personaDao.obtenerPersonaPorIdentificacion(identificacion, tipoDocumento);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public Persona obtenerPersona(Integer id_persona) {
		try {
			PersonaDao personaDao = (PersonaDao) DaoConfig.getDao(PersonaDao.class);
			return personaDao.obtenerPersona(id_persona);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------
	public List<Persona> obtenerPersonasNegocio(Integer id_negocio) {

		try {
			PersonaDao personaDao = (PersonaDao) DaoConfig.getDao(PersonaDao.class);
			List<Persona> listadoClientes = personaDao.obtenerPersonasNegocio(id_negocio);
			return listadoClientes;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------
	// Obtiene administradores y superadministradores
	public List<Persona> obtenerAdministradores() {

		try {
			PersonaDao personaDao = (PersonaDao) DaoConfig.getDao(PersonaDao.class);
			List<Persona> listadoClientes = personaDao.obtenerAdministradores();
			return listadoClientes;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------
	// Obtiene las personas clientes de un formato.

	public List<Persona> obtenerClientesFormato(Integer id_formato, Integer id_negocio, Integer numero_pagina) {
		try {
			PersonaDao personaDao = (PersonaDao) DaoConfig.getDao(PersonaDao.class);
			List<Persona> listadoPersonas = personaDao.obtenerClientesFormato(id_formato, id_negocio, numero_pagina);

			return listadoPersonas;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------

	public Integer totalClientesFormato(Integer id_formato, Integer id_negocio) {
		try {
			PersonaDao personaDao = (PersonaDao) DaoConfig.getDao(PersonaDao.class);

			return personaDao.totalClientesFormato(id_formato, id_negocio);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------

	public Boolean guardarSimplePersona(Persona persona) {
		return guardarPersonaSimple(persona) != null;
	}

	// ----------------------------------

	/**
	 * Solo guarda o actualiza informaciï¿½n de la persona, no crea login
	 * 
	 * @param persona
	 */
	public Persona guardarPersonaSimple(Persona persona) {
		return guardarPersona(persona, null);
	}

	public Persona guardarPersona(Persona persona, DaoManager daoManager) {

		try {

			if (!validarPersona(persona)) {
				SimpleLogger.setError("Persona no valida");
				return null;
			}

			if (daoManager == null) {
				daoManager = DaoConfig.getDaoManager();
			}

			PersonaDao personaDao = (PersonaDao) daoManager.getDao(PersonaDao.class);

			return personaDao.guardarPersona(persona);

		} catch (Exception e) {
			SimpleLogger.setError("Error al guardar la persona", e);
		}

		return null;
	}

	public Persona actualizarPersona(Persona persona) {

		try {

			if (!validarPersona(persona)) {
				return null;
			}

			PersonaDao personaDao = (PersonaDao) DaoConfig.getDao(PersonaDao.class);

			return personaDao.actualizarPersona(persona);

		} catch (Exception e) {
			SimpleLogger.setError("Error al guardar la persona", e);
		}

		return null;
	}

	// ----------------------------------

	public int[] obtenerRoles(String isusuario, String isadminmodela, String isadminwebdata, String issuperadmin, String rol) {
		int[] roles = new int[4];

		if (rol == null) {
			roles[0] = ("S".equals(isusuario) ? Constantes.ROL_CLIENTE : ("N".equals(isusuario) ? -Constantes.ROL_CLIENTE : 0));
		} else {
			roles[0] = (Integer) Constantes.MAP_TABLAS_ROLNUM.get(rol);
		}
		roles[1] = ("S".equals(isadminmodela) ? Constantes.ROL_ADMIN_MODELATOS : ("N".equals(isadminmodela) ? -Constantes.ROL_ADMIN_MODELATOS : 0));
		roles[2] = ("S".equals(isadminwebdata) ? Constantes.ROL_ADMIN_WEBDATA : ("N".equals(isadminwebdata) ? -Constantes.ROL_ADMIN_WEBDATA : 0));
		roles[3] = ("S".equals(issuperadmin) ? Constantes.ROL_SUP_ADMIN_MODELATOS : ("N".equals(issuperadmin) ? -Constantes.ROL_SUP_ADMIN_MODELATOS : 0));

		return roles;
	}

	public Boolean generarUsuarioyEnviarCorreo(Persona persona, int[] roles, SourceResolver resolver, Boolean usuarioNuevo, Credencial credencial) {
		try {

			String clave = "";

			Boolean tieneCorreo = persona.getCorreo() != null && persona.getCorreo().trim().length() > 0;

			if (tieneCorreo) {
				clave = StringUtils.randomString(5);
			} else {
				SimpleLogger.setWarn("El usuario a crear (" + credencial.getLogin() + ") no tiene cuenta de correo. La contraseña a crear sera el mismo nombre de usuario.");
				clave = credencial.getLogin().trim().toUpperCase();
				actualizarEstadoCredencial(Constantes.CREDENCIAL_ESTADO_ACTIVO, credencial.getLogin());
			}

			colocarCambioClave(credencial.getLoginUsuario());

			com.osmosyscol.commons.servicio.UsuarioServicio.getInstance().guardarUsuario(credencial.getLoginUsuario(), usuarioNuevo ? clave : null, roles);

			if (!usuarioNuevo) {
				quitarCambioClave(credencial.getLoginUsuario(), null);
			}

			if (tieneCorreo) {
				// Envio de mail de creacion de usuarios nuevos
				if (usuarioNuevo) {

					Map<String, String> archivos = new HashMap<String, String>();
					archivos.put("#logo#", "images/back/logo1.png");
					//archivos.put("#logo_potencia#", "images/back/Logo_potencia.png");
					Map<String, String> parametros = JavaToXML.objectToParameters("persona", persona);
					parametros.put("clave", clave);
					parametros.put("login", credencial.getLogin());
					Contenido contenido = ContenidoServicio.getInstance().obtenerContenido("General", "CLIENTE");
					String textoContenido = "Cliente";
					if (contenido != null && contenido.getTexto() != null && !contenido.getTexto().isEmpty()) {
						textoContenido = contenido.getTexto();
					}
					parametros.put("tipoUsuario", textoContenido);

					if (credencial.getId_usuario() != null && PersonaServicio.getInstance().esLoginPorIdentificacion()) {
						Usuario usuario = com.osmosyscol.datasuite.logica.servicios.UsuarioServicio.getInstance().obtenerUsuario(credencial.getId_usuario());
						if (usuario != null) {
							parametros.putAll(JavaToXML.objectToParameters("usuario", usuario));
						}
					}

					if (resolver != null) {
						EnviaMails.enviar(persona.getCorreo(), persona.getNombreCompleto(), "Usuario Creado", "cocoon:/persona/envioClave.email", archivos, parametros, resolver);
					} else {
						EnviaMails.enviar(persona.getCorreo(), persona.getNombreCompleto(), "Usuario Creado", "persona/envioClave.email", archivos, parametros, null);
					}

				}
			}
			return true;
		} catch (Exception e) {
			SimpleLogger.setError("Error enviando correo: ", e);
			com.osmosyscol.commons.servicio.UsuarioServicio.getInstance().eliminarUsuario(credencial.getLoginUsuario());
		}
		System.out.println("generarUsuarioyEnviarCorreo Return false : " + persona.getId_persona() + ", " + usuarioNuevo + ", "+ credencial.getLogin());
		return false;
	}

	// ------------------------------
	public Integer contarDirectoresNegocio(Integer id_negocio) {

		try {
			PersonaDao personaDao = (PersonaDao) DaoConfig.getDao(PersonaDao.class);
			Integer total = personaDao.contarDirectoresNegocio(id_negocio);
			return total;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------
	public List<Persona> obtenerDirectoresNegocio(Integer id_negocio, Integer pagina) {

		try {
			PersonaDao personaDao = (PersonaDao) DaoConfig.getDao(PersonaDao.class);
			List<Persona> listadoClientes = personaDao.obtenerDirectoresNegocio(id_negocio, pagina);
			return listadoClientes;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------
	public Persona obtenerPersonaUsuario(Integer id_usuario, String rol, String tipo_persona, String identificacion, Integer tipoDocumento) {

		try {

			PersonaDao personaDao = (PersonaDao) DaoConfig.getDao(PersonaDao.class);
			Persona persona = personaDao.obtenerPersonaUsuario(id_usuario, rol, tipo_persona, identificacion, tipoDocumento);
			return persona;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;
	}

	// ------------------------------
	public List<Persona> obtenerPersonasUsuario(Integer id_usuario, String rol, String tipo_persona, Integer pagina) {

		try {

			PersonaDao personaDao = (PersonaDao) DaoConfig.getDao(PersonaDao.class);
			List<Persona> listadoClientes = personaDao.obtenerPersonasUsuario(id_usuario, rol, tipo_persona, pagina);
			for (Persona persona : listadoClientes) {
				persona.setTiposProcesoRol(obtenerTipoProcesoRol(persona.getId_persona(), id_usuario, rol));
			}
			return listadoClientes;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;
	}

	// ------------------------------
	public Integer contarPersonasUsuario(Integer id_usuario, String rol, String tipo_persona) {

		try {

			PersonaDao personaDao = (PersonaDao) DaoConfig.getDao(PersonaDao.class);
			Integer total = personaDao.contarPersonasUsuario(id_usuario, rol, tipo_persona);
			return total;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;
	}

	public Boolean activarPersonaUsuario(Integer id_persona, Integer id_usuario, String activo, String rol, String todos) {
		try {
			// Validar que sea S o N
			if (!StringUtils.contieneSolo(activo, "SN", 1)) {
				return false;
			}

			DaoManager daoManager = DaoConfig.getDaoManager();

			PersonaDao dao = (PersonaDao) daoManager.getDao(PersonaDao.class);
			return dao.activarPersonaUsuario(id_persona, id_usuario, activo, rol, todos);

		} catch (Exception e) {
			SimpleLogger.setError("Error en activarUsuario", e);
		}
		return false;
	}

	public Boolean activarDirectorNegocio(Integer id_persona, Integer id_negocio, String activo, String todos) {
		try {
			if (!StringUtils.contieneSolo(activo, "SN", 1)) {
				return false;
			}

			if (!StringUtils.contieneSolo(todos, "SN", 1)) {
				return false;
			}

			DaoManager daoManager = DaoConfig.getDaoManager();

			PersonaDao dao = (PersonaDao) daoManager.getDao(PersonaDao.class);
			return dao.activarDirectorNegocio(id_persona, id_negocio, activo, todos);

		} catch (Exception e) {
			SimpleLogger.setError("Error en activarUsuario", e);
		}
		return false;
	}

	public Boolean eliminarDirectorNegocio(Integer id_persona, Integer id_negocio) {
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();

			PersonaDao dao = (PersonaDao) daoManager.getDao(PersonaDao.class);

			Credencial credencial = obtenerCredencialPersonaUsuario(id_persona, null);

			Boolean rta = dao.eliminarDirectorNegocio(id_persona, id_negocio);

			if (rta) {

				rta = UsuarioServicio.getInstance().eliminarUsuario(credencial.getLogin());
			}

			return rta;
		} catch (Exception e) {
			SimpleLogger.setError("Error en activarUsuario", e);
		}
		return false;
	}

	public Boolean eliminarPersonaUsuario(Integer idPersona, Integer idUsuario, String rol) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();

			PersonaDao dao = (PersonaDao) daoManager.getDao(PersonaDao.class);

			Credencial credencial = obtenerCredencialPersonaUsuario(idPersona, idUsuario);

			Boolean rta = dao.eliminarPersonaUsuario(idPersona, idUsuario, rol);

			if (rta) {

				com.osmosyscol.commons.servicio.UsuarioServicio.getInstance().guardarUsuario(credencial.getLoginUsuario(), null, new int[] { -(Integer) Constantes.MAP_TABLAS_ROLNUM.get(rol) });

				Credencial credencial2 = obtenerCredencialPersonaUsuario(idPersona, idUsuario);

				if (credencial2 == null && credencial != null) {

					rta = UsuarioServicio.getInstance().eliminarUsuario(credencial.getLogin());

				}
			}

			return rta;
		} catch (Exception e) {
			SimpleLogger.setError("Error en ", e);
		}
		return false;
	}

	public Integer guardarDirectorNegocio(Persona persona, Credencial credencial, Integer id_negocio, SourceResolver resolver) {

		Integer salida = 0; // error
		DaoManager daoManager = null;
		try {

			Boolean usuarioNuevo = (credencial.getId_persona() == null);

			if (id_negocio == null || id_negocio <= 0) {
				return 7; // Informaciï¿½n incompleta
			}
			daoManager = DaoConfig.getDaoManager();

			daoManager.startTransaction();

			Persona personarta = PersonaServicio.getInstance().guardarPersona(persona, daoManager);

			if (personarta != null && personarta.getId_persona() > 0) {

				persona.setId_persona(personarta.getId_persona());

				AdministradorDao administradorDao = (AdministradorDao) daoManager.getDao(AdministradorDao.class);

				Administrador administrador = new Administrador();
				administrador.setActivo("S");
				administrador.setLogin(credencial.getLogin());
				administrador.setPersona(personarta);

				administrador.setId_persona(personarta.getId_persona());

				salida = 3; // no se puede guardar director

				Integer id_administrador = administradorDao.actualizarAdministrador(administrador);

				salida = 4; // no se puede crear la relaciï¿½n con el negocio

				administradorDao.agregarAdministradorNegocio(id_negocio, "S", id_administrador);

				if (usuarioNuevo) {
					credencial.setId_persona(personarta.getId_persona());
					credencial = guardarCredencial(credencial, daoManager);

					if (credencial == null) {
						return 5; // no se puede guardar la credencial
					}

					Boolean rtaCorreo = true;

					rtaCorreo = generarUsuarioyEnviarCorreo(persona, obtenerRoles("S", null, null, null, "DN"), resolver, usuarioNuevo, credencial);

					if (!rtaCorreo) {
						return 6; // Error en el envio del correo
					}
				}

				daoManager.commitTransaction();
				salida = 1; // ï¿½xito
				return salida;
			} else {
				salida = 2; // No se puede guardar usuario
			}
		} catch (Exception e) {
			SimpleLogger.setError("Error en servicio actualizarActivoAdministrador", e);
		} finally {
			if (daoManager != null) {
				daoManager.endTransaction();
			}
		}
		return salida;
	}

	public Integer guardarPersonaUsuario(Persona persona, Credencial credencial, Integer id_negocio, SourceResolver resolver) {

		Integer id_usuario = null;

		if (persona != null) {
			credencial.setId_persona(persona.getId_persona());
		}

		return guardarPersonaUsuarioRol(persona, credencial, id_usuario, Constantes.ROL_CLIENTE_NATURAL, resolver);
	}

	public Integer guardarPersonaUsuarioRol(Persona persona, Credencial credencial, Integer id_usuario, String rol, SourceResolver resolver) {

		if (persona != null) {
			Credencial crede = obtenerCredencialPersonaUsuario(persona.getId_persona(), id_usuario);
			if (crede != null) {
				credencial = crede;
			}
		}

		Integer salida = 0; // error
		DaoManager daoManager = null;
		try {

			if (id_usuario == null || id_usuario <= 0) {
				return 3;
			}

			daoManager = DaoConfig.getDaoManager();

			daoManager.startTransaction();

			Boolean usuarioSinCredencial = (credencial.getId_persona() == null);

			Persona personarta = PersonaServicio.getInstance().guardarPersona(persona, daoManager);

			if (personarta != null && personarta.getId_persona() > 0) {

				persona.setId_persona(personarta.getId_persona());

				salida = 3; // no se puede guardar director

				PersonaDao dao = (PersonaDao) daoManager.getDao(PersonaDao.class);

				Boolean existeRelacion = dao.existeRelacionPersonaUsuarioRol(persona.getId_persona(), id_usuario, rol);
				Boolean exito = false;

				if (!existeRelacion) {
					exito = dao.guardarPersonaUsuario(persona.getId_persona(), id_usuario, rol);
				} else {
					// la persona ya fue gurdada
					exito = true;
				}

				if (credencial != null && org.apache.commons.lang.StringUtils.isNotEmpty(credencial.getLogin())) {

					if (usuarioSinCredencial) {

						credencial.setId_persona(personarta.getId_persona());
						credencial.setId_usuario(id_usuario);

						credencial = guardarCredencial(credencial, daoManager);
						if (credencial == null) {
							return 4; // No se puede guardar la credencial
						}

						salida = 5; // No se puede enviar correo

						if (rol.equals(Constantes.ROL_ADMIN_CLIENTE) || rol.equals(Constantes.ROL_CLIENTE_NATURAL)) {
							if (!com.osmosyscol.commons.utils.StringUtils.esVerdad(ParametrosInicio.getProperty("UsoSobreflex"))) {
								exito = exito && generarUsuarioyEnviarCorreo(persona, obtenerRoles("S", null, null, null, rol), resolver, usuarioSinCredencial, credencial);
							} else {
								String clave = com.osmosyscol.commons.utils.StringUtils.randomString(5);
								com.osmosyscol.commons.servicio.UsuarioServicio.getInstance().guardarUsuario(credencial.getLoginUsuario(), clave, obtenerRoles("S", null, null, null, rol));
								colocarCambioClave(credencial.getLoginUsuario());
							}
						} else {
							exito = exito && generarUsuarioyEnviarCorreo(persona, obtenerRoles("S", null, null, null, rol), resolver, usuarioSinCredencial, credencial);
						}

					} else {
						com.osmosyscol.commons.servicio.UsuarioServicio.getInstance().guardarUsuario(credencial.getLoginUsuario(), null, obtenerRoles("S", null, null, null, rol));
					}

				}

				if (exito) {

					daoManager.commitTransaction();
					salida = 1; //
				}

				return salida;
			} else {
				salida = 2; // No se puede guardar usuario
			}
		} catch (Exception e) {
			SimpleLogger.setError("Error en servicio actualizarActivoAdministrador", e);
		} finally {
			if (daoManager != null) {
				daoManager.endTransaction();
			}
		}
		return salida;
	}

	public Credencial obtenerCredencialPorLogin(String login) {
		if (StringUtils.esVacio(login)) {
			return null;
		}

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			PersonaDao personaDao = (PersonaDao) daoManager.getDao(PersonaDao.class);
			return personaDao.obtenerCredencialPorLogin(login);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public Credencial guardarCredencial(Credencial credencial) {
		return guardarCredencial(credencial, null);
	}

	public Credencial guardarCredencial(Credencial credencial, DaoManager daoManager) {

		try {

			if (!validarCredencial(credencial)) {
				return null;
			}

			if (daoManager == null) {
				daoManager = DaoConfig.getDaoManager();
			}

			PersonaDao personaDao = (PersonaDao) daoManager.getDao(PersonaDao.class);

			if (esLoginPorIdentificacion() && credencial.getId_usuario() != null) {
				Usuario usuario = com.osmosyscol.datasuite.logica.servicios.UsuarioServicio.getInstance().obtenerUsuario(credencial.getId_usuario());
				credencial.setLogin(credencial.getLogin() + Constantes.SEPARADOR_LOGIN + usuario.getIdentificacion());
			}

			if (credencial.getEstado() == null) {
				credencial.setEstado(Constantes.CREDENCIAL_ESTADO_CREADO);
			}
			return personaDao.guardarCredencial(credencial);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;

	}

	public Credencial obtenerCredencialPersonaUsuario(Integer id_persona, Integer id_usuario) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			PersonaDao personaDao = (PersonaDao) daoManager.getDao(PersonaDao.class);

			return personaDao.obtenerCredencialPersonaUsuario(id_persona, id_usuario);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;

	}

	public String obtenerCredencialExterna(String identificacion, Integer tipo_documento, Integer id_usuario) {

		System.out.println("obtenerCredencialExterna: " + identificacion + ", " + tipo_documento + ", " + id_usuario);

		// consulta en el core.. si el usuario ya tiene credenciales del cliente

		try {

			String val = ParametrosInicio.getProperty("sso.infodelegado.enpoint");

			if (StringUtils.isNotBlank(val)) {

				Usuario usuario = com.osmosyscol.datasuite.logica.servicios.UsuarioServicio.getInstance().obtenerUsuario(id_usuario);

				val = val.replace("{NIT}", usuario.getIdentificacion()).replace("{IDENTIFICACION}", identificacion);

				System.out.println("URL: " + val);

				PersonaValidacion pv = new Gson().fromJson(new CallPage().call(val), PersonaValidacion.class);

				if (pv != null) {

					Persona persona = obtenerPersonaPorIdentificacion(identificacion, tipo_documento);

					if (persona == null) {
						persona = new Persona();
						persona.setTipo_persona("N");
						persona.setGenero("M");
					}

					persona.setTipo_documento(tipo_documento);
					persona.setIdentificacion(identificacion);
					persona.setNombre(pv.getNombres());
					persona.setApellido(pv.getApellidos());
					persona.setCorreo(pv.getCorreo());
					persona.setDireccion(pv.getDireccion());
					persona.setTelefono(pv.getCelular());

					guardarPersonaSimple(persona);

					return pv.getLogin().toLowerCase();

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Credencial obtenerCredencialPersonaID(String identificacion, Integer tipo_documento, Integer id_usuario) {

		try {

			Persona persona = obtenerPersonaPorIdentificacion(identificacion, tipo_documento);

			if (persona == null) {
				return null;
			}

			DaoManager daoManager = DaoConfig.getDaoManager();
			PersonaDao personaDao = (PersonaDao) daoManager.getDao(PersonaDao.class);

			return personaDao.obtenerCredencialPersonaUsuario(persona.getId_persona(), id_usuario);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;

	}

	public Credencial obtenerCredencialPersonaIDUsuario(String identificacion, Integer id_usuario, Integer tipo_documento) {
		return obtenerCredencialPersonaID(identificacion, tipo_documento, id_usuario);
	}

	public Boolean validarPersona(Persona persona) {
		if (persona == null) {
			return false;
		}
		if (StringUtils.esVacio(persona.getNombre())) {
			return false;
		}
		if (!StringUtils.contieneSolo(persona.getTipo_persona(), "JN", 1)) {
			return false;
		}
		Boolean personaNatural = persona.getTipo_persona().equals("N");

//		if (personaNatural && !StringUtils.contieneSolo(persona.getGenero(), "MF", 1)) {
//			return false;
//		}
		if (personaNatural && StringUtils.esVacio(persona.getApellido())) {
			return false;
		}

		if (StringUtils.esVacio(persona.getIdentificacion())) {
			return false;
		}

		return true;
	}

	public Boolean validarCredencial(Credencial credencial) {
		if (credencial == null) {
			return false;
		}
		if (StringUtils.esVacio(credencial.getLogin())) {
			return false;
		}
		String login = credencial.getLogin();
		login = org.apache.commons.lang.StringUtils.replaceChars(login, "_.-", "");
		if (!org.apache.commons.lang.StringUtils.isAlphanumeric(login)) {
			return false;
		}
		if (credencial.getId_persona() == null || credencial.getId_persona() < 0) {
			return false;
		}
		return true;
	}

	public Boolean actualizarFechaIngreso(String login, Date fecha_ingreso) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			PersonaDao personaDao = (PersonaDao) daoManager.getDao(PersonaDao.class);

			return personaDao.actualizarFechaIngreso(login, fecha_ingreso);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error al actualizar la fecha de ingreso", e);
			return false;
		}

	}

	public Boolean actualizarIPIngreso(String login, String ip_ingreso) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			PersonaDao personaDao = (PersonaDao) daoManager.getDao(PersonaDao.class);

			return personaDao.actualizarIPIngreso(login, ip_ingreso);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error al actualizar la ip de ingreso", e);
			return false;
		}

	}

	public List<TipoDocumento> obtenerTiposDocumento() {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			PersonaDao personaDao = (PersonaDao) daoManager.getDao(PersonaDao.class);

			return personaDao.obtenerTiposDocumento();
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error al actualizar la ip de ingreso", e);
			return null;
		}

	}

	public TipoDocumento obtenerTipoDocumento(Integer id_tipo_documento) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			PersonaDao personaDao = (PersonaDao) daoManager.getDao(PersonaDao.class);

			return personaDao.obtenerTipoDocumento(id_tipo_documento);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error al actualizar la ip de ingreso", e);
			return null;
		}

	}

	public Boolean esLoginPorIdentificacion() {
		String activo = ParametrosInicio.getProperty("LoginPorIdentificacion");
		return StringUtils.esVerdad(activo);
	}

	public Boolean actualizarEstadoCredencial(String estado, String login) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			PersonaDao personaDao = (PersonaDao) daoManager.getDao(PersonaDao.class);

			return personaDao.actualizarEstadoCredencial(estado, login);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error al actualizar la ip de ingreso", e);
			return null;
		}

	}

	public List<Persona> obtenerAdministrativos(Integer id_usuario, Integer pagina) {

		try {
			PersonaDao personaDao = (PersonaDao) DaoConfig.getDao(PersonaDao.class);

			List<Persona> listadoAdministradores = personaDao.obtenerAdministrativos(id_usuario, pagina);

			return listadoAdministradores;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public Integer contarAdministrativos(Integer id_usuario) {

		try {
			PersonaDao personaDao = (PersonaDao) DaoConfig.getDao(PersonaDao.class);

			return personaDao.contarAdministrativos(id_usuario);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public Integer guardarPersonaAdministrativo(Integer id_usuario, Persona persona, Credencial credencial, SourceResolver resolver, List<Integer> roles, Administrativo administrativo) {

		credencial.setId_usuario(id_usuario);

		Integer salida = 0; // error
		DaoManager daoManager = null;
		try {

			daoManager = DaoConfig.getDaoManager();

			daoManager.startTransaction();

			Boolean usuarioNuevo = (credencial.getId_persona() == null);

			Persona personarta = PersonaServicio.getInstance().guardarPersona(persona, daoManager);

			if (administrativo == null) {
				administrativo = new Administrativo();
			}

			administrativo.setId_usuario(id_usuario);

			if (persona != null && personarta.getId_persona() != null) {

				persona.setId_persona(personarta.getId_persona());

				salida = 3; // no se puede guardar usuario

				if (usuarioNuevo) {

					credencial.setId_persona(personarta.getId_persona());

					credencial = guardarCredencial(credencial, daoManager);

					if (credencial == null) {
						return 4; // No se puede guardar la credencial
					}
				} else {
					credencial = obtenerCredencialPersonaUsuario(persona.getId_persona(), id_usuario);
				}
				salida = 6; // no se pueden guardar los roles
				administrativo.setId_persona(persona.getId_persona());
				Integer admin = AdministrativoServicio.getInstance().obtenerIdAdministrativoPersona(persona.getId_persona(), id_usuario);
				Integer id_administrativo = null;
				if(admin == null){
					id_administrativo = AdministrativoServicio.getInstance().guardarAdministrativoTrans(administrativo, daoManager);
				}else {
					id_administrativo = AdministrativoServicio.getInstance().obtenerIdAdministrativoPersona(persona.getId_persona(), id_usuario);
				}
				if (id_administrativo == null && admin == null) {
					return salida;
				}

				Boolean exito = RolServicio.getInstance().guardarRolesAdministrativo(id_administrativo, roles);

				if (!exito) {
					return salida;
				}

				salida = 5; // No se puede enviar correo

				exito = generarUsuarioyEnviarCorreo(persona, obtenerRoles("S", null, null, null, id_usuario == null ? Constantes.ROL_ADMINISTRATIVO : Constantes.ROL_ADMINISTRATIVOCL), resolver, usuarioNuevo, credencial);

				if (exito) {
					daoManager.commitTransaction();
					salida = 1; // Exito
				}

				return salida;
			} else {
				salida = 2; // No se puede guardar usuario
			}
		} catch (Exception e) {
			SimpleLogger.setError("Error en servicio guardarPersonaRol", e);
		} finally {
			if (daoManager != null) {
				daoManager.endTransaction();
			}
		}
		return salida;
	}

	// ------------------------------
	public Persona obtenerPersonaRol(String rol, String tipo_persona, String identificacion, Integer tipo_documento, Integer id_usuario) {

		try {

			PersonaDao personaDao = (PersonaDao) DaoConfig.getDao(PersonaDao.class);
			Persona persona = personaDao.obtenerPersonaRol(rol, tipo_persona, identificacion, tipo_documento, id_usuario);
			return persona;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;
	}

	public Boolean eliminarPersonaRol(Integer idPersona, String rol, Integer id_usuario) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();

			PersonaDao dao = (PersonaDao) daoManager.getDao(PersonaDao.class);

			Credencial credencial = obtenerCredencialPersonaUsuario(idPersona, id_usuario);

			Boolean rta = dao.eliminarPersonaRol(idPersona, rol, id_usuario);

			if (rta) {

				rta = UsuarioServicio.getInstance().eliminarUsuario(credencial.getLogin());
			}

			return rta;
		} catch (Exception e) {
			SimpleLogger.setError("Error en activarUsuario", e);
		}
		return false;
	}

	public Boolean guardarHistorialLoginClave(String login, String clave) {

		try {

			// Se encripta la clave antes de insertar
			String claveMD5 = StringUtils.MD5(clave);

			DaoManager daoManager = DaoConfig.getDaoManager();
			PersonaDao dao = (PersonaDao) daoManager.getDao(PersonaDao.class);
			return dao.guardarHistorialLoginClave(login, claveMD5);

		} catch (Exception e) {
			SimpleLogger.setError("Se ha generado un error", e);
			return false;
		}
	}

	public void quitarCambioClave(String login, Session session) {

		try {
			PersonaDao dao = (PersonaDao) DaoConfig.getDao(PersonaDao.class);
			dao.quitarCambioClave(login);

		} catch (Exception e) {
			SimpleLogger.setError("Se ha generado un error", e);
		} finally {
			try {
				if (session != null) {
					Credencial credencial = (Credencial) session.getAttribute("credencial");
					credencial.setCambiar_clave(null);
				}
			} catch (Exception e2) {
			}
		}

	}

	public Boolean colocarCambioClave(String login) {

		try {
			PersonaDao dao = (PersonaDao) DaoConfig.getDao(PersonaDao.class);
			return dao.colocarCambioClave(login);

		} catch (Exception e) {
			SimpleLogger.setError("Se ha generado un error", e);
		}

		return false;
	}

	public Boolean existeHistorialLoginClave(String login, String clave) {
		try {
			
			String clave_decifrada = SecureJsonUtils.ds(clave);
			
			// Se encripta la clave antes de verificar
			String claveMD5 = StringUtils.MD5(clave_decifrada);

			DaoManager daoManager = DaoConfig.getDaoManager();
			PersonaDao dao = (PersonaDao) daoManager.getDao(PersonaDao.class);
			return dao.existeHistorialLoginClave(login, claveMD5);

		} catch (Exception e) {
			SimpleLogger.setError("Se ha generado un error", e);
			return false;
		}

	}

	/**
	 * Determina si el campo activo es S de acuerdo a la tabla relacionada con el rol
	 * 
	 * @param id_persona
	 *            - id de la persona
	 * @param rol
	 *            - palabra clave del rol, ver map de roles en la clase constantes
	 * @return true si el flag es S, false en caso contrario o en caso de error
	 */
	public Boolean esPersonaRolActiva(Integer id_persona, String rol) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			PersonaDao dao = (PersonaDao) daoManager.getDao(PersonaDao.class);

			String activo = dao.esPersonaRolActiva(id_persona, rol);

			return Constantes.SI.equals(activo);

		} catch (Exception e) {
			SimpleLogger.setError("Se ha generado un error", e);
		}

		return false;
	}

	public Boolean regenerarClaveUsuario(Integer id_usuario, Integer id_persona) {

		try {

			Persona persona = obtenerPersona(id_persona);
			Credencial credencial = CredencialServicio.getInstance().obtenerCredencialPersonaUsuario(id_persona, id_usuario);
			
			if(credencial == null){
				System.out.println("No se encontro credencial para la persona : " + id_persona);
				return false;
			}
			
			return generarUsuarioyEnviarCorreo(persona, new int[]{}, null, true, credencial);

		} catch (Exception e) {
			SimpleLogger.setError("regenerarClaveUsuario", e);
		}
		System.out.println("regenerarClaveUsuario Return false : " + id_usuario + ", " + id_persona);
		return false;

	}

	public Boolean estaUsuarioBloqueado(String login, String password) {

		Credencial credencial = null;
		
		String clave = SecureJsonUtils.ds(password);
		if (!AutenticacionServicio.getInstance().autenticar(login, clave, null)) {
			return null;
		}
		
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			PersonaDao dao = (PersonaDao) daoManager.getDao(PersonaDao.class);

			credencial = dao.obtenerCredencialPorLogin(login);

		} catch (Exception e) {
			SimpleLogger.setError("Se ha generado un error", e);
		}
		
		if (credencial == null)
			return null;

		if (credencial.getEstado().equalsIgnoreCase("I")) {
			return true;
		} else {
			return false;
		}

	}

	public Boolean enviarCorreoSobreflex(String login) {

		Map<String, String> archivos = new HashMap<String, String>();
		archivos.put("#logo#", "images/back/logo1.png");
//		archivos.put("#logo_potencia#", "images/back/Logo_potencia.png");

		Persona persona = PersonaServicio.getInstance().obtenerPersonaPorLogin(login);

		String hash = Long.toHexString(System.currentTimeMillis());

		HistorialSobreflexServicio.getInstance().guardarHistorialSobreflex(login, hash);

		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("login", login.toLowerCase());
		parametros.put("hash", hash);
		parametros.put("nombre", persona.getNombreCompleto());
		parametros.put("tipo_usuario", "cliente");

		try {
			EnviaMails.enviar(persona.getCorreo(), persona.getNombreCompleto(), "Usuario Creado", "persona/envioClaveSobreflex.email", archivos, parametros, null);

			return true;
		} catch (Throwable e) {
			SimpleLogger.setError("Error al enviar correo (enviarCorreoSobreflex)", e);
		}

		return false;
	}

	public List<TipoProceso> obtenerTiposProcesosPorIdUsuario(Integer id_usuario) {
		try {

			PersonaDao personaDao = (PersonaDao) DaoConfig.getDao(PersonaDao.class);
			List<TipoProceso> tipoProceso = personaDao.obtenerTiposProcesosPorIdUsuario(id_usuario);
			return tipoProceso;

		} catch (Exception e) {
			SimpleLogger.setError("Se ha generado error al obtener tipos de procesos: ", e);
		}
		return null;
	}

	public TipoProceso obtenerTipoProcesoDefecto(Integer id_usuario) {

		List<TipoProceso> tiposProceso = obtenerTiposProcesosPorIdUsuario(id_usuario);

		if (tiposProceso == null) {
			return null;
		}

		// -------------

		TipoProceso resp = null;
		for (TipoProceso tipoProceso : tiposProceso) {
			if (tipoProceso.getProceso_por_defecto().equals("S")) {
				resp = tipoProceso;
			}
		}

		return resp;
	}

	public List<TipoProcesoRol> obtenerTipoProcesoRol(Integer id_persona, Integer id_usuario, String rol) {
		try {
			PersonaDao personaDao = (PersonaDao) DaoConfig.getDao(PersonaDao.class);
			List<TipoProcesoRol> tipoProcesoRol = personaDao.obtenerTipoProcesoRol(id_persona, id_usuario, rol);
			return tipoProcesoRol;
		} catch (Exception e) {
			SimpleLogger.setError("Error al optener el tipo proceso rol");
		}
		return null;
	}

	public Boolean guardarProcesoRol(List<TipoProcesoRol> tipoprocesos, Integer id_usuario, String identificacion, Integer tipoDocumento) {
		DaoManager daoManager = null;

		try {

			Persona persona = obtenerPersonaPorIdentificacion(identificacion, tipoDocumento);

			for (TipoProcesoRol tipoProcesoRol : tipoprocesos) {
				tipoProcesoRol.setId_persona(persona.getId_persona());
			}

			eliminarProcesoRol(tipoprocesos, id_usuario);

			for (TipoProcesoRol tipoProcesoRol : tipoprocesos) {

				Integer id_tipo_proceso = tipoProcesoRol.getId_tipo_proceso();
				String rol = tipoProcesoRol.getRol();

				if (id_tipo_proceso != null) {
					daoManager = DaoConfig.getDaoManager();
					PersonaDao personaDao = (PersonaDao) daoManager.getDao(PersonaDao.class);

					Integer id_rol = personaDao.obtenerIdRol(persona.getId_persona(), id_usuario, rol);
					personaDao.guardarProcesoRol(rol, id_tipo_proceso, id_rol);
				}
			}
			ajustarProcesoPersona();
			return true;
		} catch (Exception e) {
			SimpleLogger.setError("Error al guardar el proceso Rol: ", e);
		}
		return false;
	}

	private Boolean ajustarProcesoPersona() {
		ajusteProcesoLiberador();
		ajusteProcesoPreparador();
		ajusteProcesoRevisor();
		return true;
	}

	private Boolean eliminarProcesoRol(List<TipoProcesoRol> tipoprocesos, Integer id_usuario) {
		try {

			for (TipoProcesoRol tipoProcesoRol : tipoprocesos) {
				Integer id_persona = tipoProcesoRol.getId_persona();
				String rol = tipoProcesoRol.getRol();

				Integer id_rol = obtenerIdTipoRol(id_persona, rol, id_usuario);
				PersonaDao personaDao = (PersonaDao) DaoConfig.getDao(PersonaDao.class);
				personaDao.eliminarProcesoRol(rol, id_rol);
			}
		} catch (Exception e) {
			SimpleLogger.setError("Error al eliminarProcesoRol: ", e);
		}
		return true;
	}

	private Integer obtenerIdTipoRol(Integer id_persona, String rol, Integer id_usuario) {
		try {
			PersonaDao personaDao = (PersonaDao) DaoConfig.getDao(PersonaDao.class);
			return personaDao.obtenerIdRol(id_persona, id_usuario, rol);

		} catch (Exception e) {
			SimpleLogger.setError("Error al obtenerIdTipoRol: ", e);
		}
		return null;
	}
	
	public List<String> obtenerRolesPorPersona(Integer id_persona) {
		try {
			PersonaDao personaDao = (PersonaDao) DaoConfig.getDao(PersonaDao.class);
			return personaDao.obtenerRolesPorPersona(id_persona);

		} catch (Exception e) {
			SimpleLogger.setError("Error al obtenerIdTipoRol: ", e);
		}
		return null;
	}

	public List<TipoProceso> obtenerTiposProcesosPorPersona(Integer id_usuario, Integer id_persona) {
		try {

			PersonaDao personaDao = (PersonaDao) DaoConfig.getDao(PersonaDao.class);
			List<TipoProceso> tipoProceso = personaDao.obtenerTiposProcesosPorPersona(id_usuario, id_persona);
			return tipoProceso;

		} catch (Exception e) {
			SimpleLogger.setError("Se ha generado error al obtener tipos de procesos: ", e);
		}
		return null;
	}

	private Boolean ajusteProcesoLiberador() {
		PersonaDao personaDao = (PersonaDao) DaoConfig.getDao(PersonaDao.class);
		return personaDao.ajusteProcesoLiberador();
	}

	private Boolean ajusteProcesoPreparador() {
		PersonaDao personaDao = (PersonaDao) DaoConfig.getDao(PersonaDao.class);
		return personaDao.ajusteProcesoPreparador();
	}

	private Boolean ajusteProcesoRevisor() {
		PersonaDao personaDao = (PersonaDao) DaoConfig.getDao(PersonaDao.class);
		return personaDao.ajusteProcesoRevisor();
	}

	public Integer contarProcesosUsuario(Integer id_usuario, Integer id_persona, Integer id_tipo_proceso) {

		try {

			PersonaDao personaDao = (PersonaDao) DaoConfig.getDao(PersonaDao.class);
			return personaDao.contarProcesosRelacionados(id_usuario, id_persona, id_tipo_proceso);

		} catch (Exception e) {
			SimpleLogger.setError("Se ha generado error al obtener tipos de procesos: ", e);
		}

		return 0;

	}

	public boolean esAdminCliente(Integer id_usuario, Integer id_persona) {

		try {

			PersonaDao personaDao = (PersonaDao) DaoConfig.getDao(PersonaDao.class);
			return personaDao.esAdminCliente(id_usuario, id_persona);

		} catch (Exception e) {
			SimpleLogger.setError("Se ha generado error.", e);
		}

		return false;
	}

	public TipoProcesoRol obtenerTipoProcesoRolPorProceso(Integer id_persona, Integer id_usuario, Integer id_tipo_proceso) {
		try {

			PersonaDao personaDao = (PersonaDao) DaoConfig.getDao(PersonaDao.class);
			return personaDao.obtenerTipoProcesoRolPorProceso(id_persona, id_usuario, id_tipo_proceso);

		} catch (Exception e) {
			SimpleLogger.setError("Se ha generado error.", e);
		}

		return null;
	}

	public Integer obtenerIdLiberadorCarga(Integer id_carga) {
		try {
			PersonaDao personaDao = (PersonaDao) DaoConfig.getDao(PersonaDao.class);
			return personaDao.obtenerIdLiberadorCarga(id_carga);

		} catch (Exception e) {
			SimpleLogger.setError("Se ha generado error al obtener el id del liberador", e);
			return null;
		}

	}
	
	public List<Persona> obtenerAdministradoresClientePorUsuario(Integer id_usuario) {
		try {
			PersonaDao personaDao = (PersonaDao) DaoConfig.getDao(PersonaDao.class);
			return personaDao.obtenerAdministradoresClientePorUsuario(id_usuario);

		} catch (Exception e) {
			SimpleLogger.setError("Se ha generado error al obtener Administradores Cliente Por Usuario", e);
			return null;
		}

	}
	
	public List<Persona> obtenerRevisoresPorUsuario(Integer id_usuario, Integer id_tipo_revisor) {
		try {
			PersonaDao personaDao = (PersonaDao) DaoConfig.getDao(PersonaDao.class);
			return personaDao.obtenerRevisoresPorUsuario(id_usuario, id_tipo_revisor);

		} catch (Exception e) {
			SimpleLogger.setError("Se ha generado error al obtener Revisores Por Usuario", e);
			return null;
		}

	}
	
	public Integer obtenerIdTipoRevisorPorPersona(Integer id_persona) {
		try {
			PersonaDao personaDao = (PersonaDao) DaoConfig.getDao(PersonaDao.class);
			return personaDao.obtenerIdTipoRevisorPorPersona(id_persona);

		} catch (Exception e) {
			SimpleLogger.setError("Se ha generado error al obtener el id_tipo_revisor", e);
			return null;
		}

	}
	
	public List<Persona> obtenerPreparadoresNoAdminClientePorUsuario(Integer id_usuario) {
		try {
			PersonaDao personaDao = (PersonaDao) DaoConfig.getDao(PersonaDao.class);
			return personaDao.obtenerPreparadoresNoAdminClientePorUsuario(id_usuario);

		} catch (Exception e) {
			SimpleLogger.setError("Se ha generado error al obtener PreparadoresNoAdminCliente Por Usuario", e);
			return null;
		}

	}
	
	public Responsable obtenerResponsableActualCarga(Integer id_carga) {
		try {
			PersonaDao personaDao = (PersonaDao) DaoConfig.getDao(PersonaDao.class);
			return personaDao.obtenerResponsableActualCarga(id_carga);

		} catch (Exception e) {
			SimpleLogger.setError("Se ha generado error al obtener obtenerResponsableActualCarga Por carga", e);
			return null;
		}

	}
	
	public List<Persona> obtenerPosiblesResponsablesCarga(Integer id_carga) {
		try {
			PersonaDao personaDao = (PersonaDao) DaoConfig.getDao(PersonaDao.class);
			return personaDao.obtenerPosiblesResponsablesCarga(id_carga);

		} catch (Exception e) {
			SimpleLogger.setError("Se ha generado error al obtener obtenerPosiblesResponsablesCarga Por Carga", e);
			return null;
		}

	}
	
	public String obtenerCodigoLugarPorId(String id, String estructura, String nombreColumna){
		String codigo = DS_SqlUtils.queryForObject(String.class, "select $" + estructura + "." + nombreColumna + "$ from $" + estructura + "$ C WHERE ID = "+id+"");
		return codigo;
	}
	
	public String obtenerIdentificacionPorCodigo(String codigo, String estructura, String nombreColumna, String nombreId){
		String id = DS_SqlUtils.queryForObject(String.class, "select $" + estructura + "." + nombreColumna + "$ from $" + estructura + "$ C WHERE $" + estructura + "." + nombreId + "$ = $I("+codigo+")");
		return id;
	}
	
}
