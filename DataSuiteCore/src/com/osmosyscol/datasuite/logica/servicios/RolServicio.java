package com.osmosyscol.datasuite.logica.servicios;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.config.Constantes;
import com.osmosyscol.datasuite.logica.dto.Rol;
import com.osmosyscol.datasuite.persistencia.dao.RolDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class RolServicio {

	private static RolServicio rolServicio;
	
	private RolServicio() {
		SimpleLogger.setDebug(Constantes.TRUE);

	}

	public static RolServicio getInstance() {
		if (rolServicio == null) {
			rolServicio = new RolServicio();
		}
		return rolServicio;
	}

	
	public Rol obtenerRol(Integer id_rol) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			RolDao rolDao = (RolDao) daoManager.getDao(RolDao.class);
			return rolDao.obtenerRol(id_rol);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	
	public List<Rol> obtenerRolesHijos(Integer id_rol_padre) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			RolDao rolDao = (RolDao) daoManager.getDao(RolDao.class);
			return rolDao.obtenerRolesHijos(id_rol_padre);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public Boolean guardarRol(Integer id_usuario, Rol rol) {
		
		rol.setId_usuario(id_usuario);
		
		try {
			Boolean guarda = false;
			if(rol.getId_rol()!=null){
				SimpleLogger.setInfo("actualizando rol...");
				guarda = actualizarRol(rol);
				
			}else if(rol.getNombre_rol().length()>0 ){
				SimpleLogger.setInfo("creando rol...");
				DaoManager daoManager = DaoConfig.getDaoManager();
				RolDao rolDao = (RolDao) daoManager.getDao(RolDao.class);
				guarda= rolDao.guardarRol(rol);
			}
			
			return guarda;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		
		return null;
	}

	public Boolean desactivarRol(Integer id_rol){
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			RolDao rolDao = (RolDao) daoManager.getDao(RolDao.class);
			return rolDao.desactivarRol(id_rol);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	
	public Boolean activarRol(Integer id_rol){
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			RolDao rolDao = (RolDao) daoManager.getDao(RolDao.class);
			return rolDao.activarRol(id_rol);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	private Boolean actualizarRol(Rol rol){
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			RolDao rolDao = (RolDao) daoManager.getDao(RolDao.class);
			boolean actualiza=false;
			
			if(rol.getId_rol()>0){
				if(rol.getNombre_rol().length()>0 ){
					if( StringUtils.contieneSolo(rol.getActivo(), Constantes.TRUE + Constantes.FALSE, 1)){
						actualiza=rolDao.actualizarRol(rol);
						
					}
				}
			}
			
			return actualiza;
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	
	public Boolean eliminarRol(Integer id_rol ){
		try {
		
				DaoManager daoManager = DaoConfig.getDaoManager();
				RolDao rolDao = (RolDao) daoManager.getDao(RolDao.class);
				
				try {
					
					daoManager.startTransaction();
					
					Boolean eliminarRelacionesAdmin = rolDao.eliminarRelacionesRolAdministrativoPorRol(id_rol);
					Boolean eliminarRelacionesServicio = rolDao.eliminarRelacionesRolServicioPorRol(id_rol);
					Boolean eliminarRelacionesInstancia = rolDao.eliminarRelacionesRolInstanciaPorRol(id_rol);
					Boolean eliminarRelacionesRolesHijo = rolDao.eliminarRelacionesRolHijoPorRolPadre(id_rol);
					
					if(eliminarRelacionesAdmin 
							&& eliminarRelacionesServicio 
							&& eliminarRelacionesInstancia
							&& eliminarRelacionesRolesHijo){
						
						//Si se elimina el rol se finaliza la transaccion
						if(rolDao.eliminarRol(id_rol)){
							daoManager.commitTransaction();
							return true;
							
						}else{
							return false;
						}
					}else{
						return false;
					}
					
				} catch (Exception e) {
					SimpleLogger.setError("Ha ocurrido un error", e);
					return false;
				
				} finally{
					daoManager.endTransaction();
					
				}
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return false;
		}
		
	}
	
	public List<Rol> obtenerRolesActivos(){
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			RolDao rolDao = (RolDao) daoManager.getDao(RolDao.class);
			return rolDao.obtenerRolesActivos();
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
		
	}
	
	public Integer totalRolesActivos(){
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			RolDao rolDao = (RolDao) daoManager.getDao(RolDao.class);
			return rolDao.totalRolesActivos();
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
		
	}
	
	public List<Rol> obtenerTodosLosRoles(Integer id_usuario){
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			RolDao rolDao = (RolDao) daoManager.getDao(RolDao.class);
			return rolDao.obtenerTodosLosRoles(id_usuario);
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
		
	}
	
	public List<Rol> obtenerRolesAdministrativo(Integer id_administrativo){
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			RolDao rolDao = (RolDao) daoManager.getDao(RolDao.class);
			return rolDao.obtenerRolesPorPersona(id_administrativo);
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
	}
	
	public Boolean guardarRolesAdministrativo(Integer id_administrativo, List<Integer> roles){
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			RolDao rolDao = (RolDao) daoManager.getDao(RolDao.class);
			return rolDao.guardarRolesAdministrativo(id_administrativo, roles);
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return false;
	}
	
	public Integer totalPersonasPorRol(Integer id_rol) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			RolDao rolDao = (RolDao) daoManager.getDao(RolDao.class);
			return rolDao.totalPersonasPorRol(id_rol);
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
		
	}
	
	public List<Rol> obtenerRolesPorAdministrativoInstancia (Integer id_administrativo, Integer id_instancia) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			RolDao rolDao = (RolDao) daoManager.getDao(RolDao.class);
			return rolDao.obtenerRolesPorAdministrativoInstancia(id_administrativo, id_instancia);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error ", e);
			return null;
		}
	}
}
