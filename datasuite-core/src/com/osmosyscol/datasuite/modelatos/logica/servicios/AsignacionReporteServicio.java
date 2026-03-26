package com.osmosyscol.datasuite.modelatos.logica.servicios;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.AutenticacionServicio;
import com.osmosyscol.datasuite.logica.dto.Rol;
import com.osmosyscol.datasuite.persistencia.dao.AsignacionReporteDao;
import com.osmosyscol.datasuite.persistencia.dao.RolDao;
import com.osmosyscol.datasuite.reportedim.dto.AsignacionReporte;
import com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.RolesUsuarioSOAPStub;
import com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoEntradalistarRolesporLogin;
import com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarRolesporLogin;
import com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradalistarRolesporLogin;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class AsignacionReporteServicio {

	private static String ROLES_USUARIO = "/ws/RolesUsuario";
	private static AsignacionReporteServicio asignacionReporteServicio;

	private AsignacionReporteServicio() {
	}

	public static AsignacionReporteServicio getInstance() {
		if (asignacionReporteServicio == null) {
			asignacionReporteServicio = new AsignacionReporteServicio();
		}
		return asignacionReporteServicio;
	}
		
	public Integer obtenerSiguienteAsignacionReporteId(){
		try{
			DaoManager daoManager = DaoConfig.getDaoManager();
			AsignacionReporteDao asignacionReporteDao = (AsignacionReporteDao) daoManager.getDao(AsignacionReporteDao.class);
			return asignacionReporteDao.obtenerSiguienteAsignacionReporteId();
		}catch(Exception e){
			SimpleLogger.setError("Ha ocurrido un error en AsignacionReporteServicio.obtenerSiguienteAsignacionReporteId", e);
			return null;
		}
	}
	
	public List<AsignacionReporte> obtenerAsignacionesReporte(){
		try{
			DaoManager daoManager = DaoConfig.getDaoManager();
			AsignacionReporteDao asignacionReporteDao = (AsignacionReporteDao) daoManager.getDao(AsignacionReporteDao.class);
			return asignacionReporteDao.obtenerAsignacionesReporte();
		}catch(Exception e){
			SimpleLogger.setError("Ha ocurrido un error en AsignacionReporteServicio.obtenerAsignacionesReporte", e);
			return null;
		}
	}
	
	public Set<AsignacionReporte> obtenerReportesAsignadosUsuario(String login, Integer id_usuario, Integer id_administrativo){
		try{
			Set<AsignacionReporte> listaReportes = new HashSet<AsignacionReporte>();
			List<Integer> ids_roles = this.obtenerRolesPorLogin(login);			
				
			if(ids_roles.contains(0)){
				// Buscar Solamente por Rol Administrador Sistema Modelatos
				listaReportes.addAll(this.obtenerAsignacionReporteRolTodos(0));
			}
			
			if(ids_roles.contains(1)){
				// Buscar Solamente por Rol Administrador Usuarios Webdata
				listaReportes.addAll(this.obtenerAsignacionReporteRolTodos(1));
			}
			 
			if(ids_roles.contains(2)){
				// Buscar Solamente por Rol Director de Negocio Webdata
				listaReportes.addAll(this.obtenerAsignacionReporteRolTodos(2));
			}
			
			if(ids_roles.contains(3)){
				// Buscar por Cliente y Rol Administrador Cliente Webdata
				listaReportes.addAll(this.obtenerAsignacionReporteRolTodos(3));
				listaReportes.addAll(this.obtenerAsignacionReporteCliente(3,id_usuario));
			}
			
			if(ids_roles.contains(4)){
				// Buscar por Cliente y Rol Preparador Cliente Webdata
				listaReportes.addAll(this.obtenerAsignacionReporteRolTodos(4));
				listaReportes.addAll(this.obtenerAsignacionReporteCliente(4,id_usuario));
			}
			
			if(ids_roles.contains(5)){
				// Buscar por Cliente y Rol Revisor Cliente Webdata
				listaReportes.addAll(this.obtenerAsignacionReporteRolTodos(5));
				listaReportes.addAll(this.obtenerAsignacionReporteCliente(5,id_usuario));
			}
			
			if(ids_roles.contains(6)){
				// Buscar por Cliente y Rol Liberador Cliente Webdata
				listaReportes.addAll(this.obtenerAsignacionReporteRolTodos(6));
				listaReportes.addAll(this.obtenerAsignacionReporteCliente(6,id_usuario));
			}
			
			if(ids_roles.contains(7)){
				// Buscar por Cliente y Rol Cliente Natural
				listaReportes.addAll(this.obtenerAsignacionReporteRolTodos(7));
				listaReportes.addAll(this.obtenerAsignacionReporteCliente(7,id_usuario));
			}
			
			if(ids_roles.contains(8)){
				// Buscar Solamente por Rol Auth y traer todos los de Administrativo
				if(id_administrativo != null){
					DaoManager daoManager = DaoConfig.getDaoManager();
					RolDao rolDao = (RolDao) daoManager.getDao(RolDao.class);
					List<Rol> id_roles_auth = rolDao.obtenerRolesPorPersona(id_administrativo);
					
					for(Rol r : id_roles_auth){
						listaReportes.addAll(this.obtenerAsignacionReporteAdministrativo(r.getId_rol()));
					}
				}
				listaReportes.addAll(this.obtenerAsignacionReporteRolTodos(8));
			}
			
			return listaReportes;
			
		}catch(Exception e){
			SimpleLogger.setError("Ha ocurrido un error en AsignacionReporteServicio.obtenerAsignacionesReporteUsuario", e);
			return null;
		}
	}
	
	private List<AsignacionReporte> obtenerAsignacionReporteCliente(Integer id_rol, Integer id_usuario) {
		
		try{	
			DaoManager daoManager = DaoConfig.getDaoManager();
			AsignacionReporteDao asignacionReporteDao = (AsignacionReporteDao) daoManager.getDao(AsignacionReporteDao.class);
			return asignacionReporteDao.obtenerAsignacionesReporteCliente(id_rol, id_usuario);
			
		}catch(Exception e){
			SimpleLogger.setError("Ha ocurrido un error en AsignacionReporteServicio.obtenerAsignacionReporteRolTodos", e);
			return null;
		}
		
	}

	public List<AsignacionReporte> obtenerAsignacionReporteRolTodos(Integer id_rol){
		try{
			
			DaoManager daoManager = DaoConfig.getDaoManager();
			AsignacionReporteDao asignacionReporteDao = (AsignacionReporteDao) daoManager.getDao(AsignacionReporteDao.class);
			return asignacionReporteDao.obtenerAsignacionesReporteRolTodos(id_rol);
			
		}catch(Exception e){
			SimpleLogger.setError("Ha ocurrido un error en AsignacionReporteServicio.obtenerAsignacionReporteRolTodos", e);
			return null;
		}

	}
	
	public AsignacionReporte obtenerAsignacionReporte(Integer id_asignacion){
		try{
			if(id_asignacion == null)
				throw new Exception("Se ha recibido un id nulo...");
			
			DaoManager daoManager = DaoConfig.getDaoManager();
			AsignacionReporteDao asignacionReporteDao = (AsignacionReporteDao) daoManager.getDao(AsignacionReporteDao.class);
			return asignacionReporteDao.obtenerAsignacionReporte(id_asignacion);
		}catch(Exception e){
			SimpleLogger.setError("Ha ocurrido un error en AsignacionReporteServicio.obtenerAsignacionReporte", e);
			return null;
		}
	}
	
	public List<AsignacionReporte> obtenerAsignacionReporteAdministrativo(Integer id_rol_auth){
		try{
			if(id_rol_auth == null)
				throw new Exception("Se ha recibido un id nulo...");
			
			DaoManager daoManager = DaoConfig.getDaoManager();
			AsignacionReporteDao asignacionReporteDao = (AsignacionReporteDao) daoManager.getDao(AsignacionReporteDao.class);
			return asignacionReporteDao.obtenerAsignacionReporteAdministrativo(id_rol_auth);
		}catch(Exception e){
			SimpleLogger.setError("Ha ocurrido un error en AsignacionReporteServicio.obtenerAsignacionReporteAdministrativo", e);
			return null;
		}
	}
	
	public Boolean crearAsignacionReporte(AsignacionReporte asignacionReporte){
		try{
			if(asignacionReporte == null)
				throw new Exception("Se ha recibido una asignacion nula...");
			
			if(asignacionReporte.getId_asignacion() == null)
				throw new Exception("No se puede crear una asignacion con id nulo...");
			
			DaoManager daoManager = DaoConfig.getDaoManager();
			AsignacionReporteDao asignacionReporteDao = (AsignacionReporteDao) daoManager.getDao(AsignacionReporteDao.class);
			return asignacionReporteDao.crearAsignacionReporte(asignacionReporte.getId_asignacion(), asignacionReporte.getId_reporte(), asignacionReporte.getId_rol(), asignacionReporte.getTitulo(), asignacionReporte.getActivo(), asignacionReporte.getId_cliente(), asignacionReporte.getId_rol_auth());
		}catch(Exception e){
			SimpleLogger.setError("Ha ocurrido un error en AsignacionReporteServicio.crearAsignacionReporte", e);
			return false;
		}
	}
	
	public Boolean eliminarAsignacionReporte(Integer id_asignacion){
		try{			
			if(id_asignacion == null)
				throw new Exception("Se ha recibido un id nulo...");
			
			DaoManager daoManager = DaoConfig.getDaoManager();
			AsignacionReporteDao asignacionReporteDao = (AsignacionReporteDao) daoManager.getDao(AsignacionReporteDao.class);
			return asignacionReporteDao.eliminarAsignacionReporte(id_asignacion);
		}catch(Exception e){
			SimpleLogger.setError("Ha ocurrido un error en AsignacionReporteServicio.eliminarAsignacionReporte", e);
			return false;
		}
	}
	
	public Boolean actualizarAsignacionReporte(AsignacionReporte asignacion){
		try{			
			if(asignacion.getId_asignacion() == null)
				throw new Exception("Se ha recibido un id nulo...");
			
			DaoManager daoManager = DaoConfig.getDaoManager();
			AsignacionReporteDao asignacionReporteDao = (AsignacionReporteDao) daoManager.getDao(AsignacionReporteDao.class);
			return asignacionReporteDao.actualizarAsignacionReporte(asignacion);
		}catch(Exception e){
			SimpleLogger.setError("Ha ocurrido un error en AsignacionReporteServicio.actualizarAsignacionReporte", e);
			return false;
		}
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
