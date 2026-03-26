package com.osmosyscol.datasuite.webdata.logica.servicios;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.Rol;
import com.osmosyscol.datasuite.webdata.logica.dto.Servicio;
import com.osmosyscol.datasuite.webdata.persistencia.dao.AccesoRolDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

/**
 * Servicio para limitar el acceso a los servicios por Rol 
 * 
 * @author oaortizs
 *
 */
public class AccesoRolServicio {
	
	private static AccesoRolServicio instance;
	
	private AccesoRolServicio() {
	}

	public static AccesoRolServicio getInstance() {
		
		if(instance == null){
			instance = new AccesoRolServicio();
		}
		
		return instance;
	}

	public List<Servicio> obtenerServicios(){
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			AccesoRolDao dao = (AccesoRolDao)daoManager.getDao(AccesoRolDao.class);
			
			return dao.obtenerServicios();
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	

	public Servicio obtenerServicio(Integer id_servicio){
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			AccesoRolDao dao = (AccesoRolDao)daoManager.getDao(AccesoRolDao.class);
			
			return dao.obtenerServicio(id_servicio);
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	
	
	public List<Rol> obtenerRolesPorServicio(Integer id_usuario, Integer id_servicio){
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			AccesoRolDao dao = (AccesoRolDao)daoManager.getDao(AccesoRolDao.class);
			
			return dao.obtenerRolesPorServicio(id_usuario, id_servicio);
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	
	public Boolean guardarAccesoRol(Integer id_servicio, Integer id_rol){

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			AccesoRolDao dao = (AccesoRolDao)daoManager.getDao(AccesoRolDao.class);
			
			return dao.guardarAccesoRol(id_servicio, id_rol);
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return false;
	}
	
	public Boolean eliminarAccesoRol(Integer id_servicio, Integer id_rol){
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			AccesoRolDao dao = (AccesoRolDao)daoManager.getDao(AccesoRolDao.class);
			
			return dao.eliminarAccesoRol(id_servicio, id_rol);
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return false;
	}

	public List<Servicio> obtenerServiciosAdministrativo(Integer id_administrativo){
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			AccesoRolDao dao = (AccesoRolDao)daoManager.getDao(AccesoRolDao.class);
			
			return dao.obtenerServiciosAdministrativo(id_administrativo);
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	
	public Integer obtenerIdServicioPorURL(String url){
		try {
		
			DaoManager daoManager = DaoConfig.getDaoManager();
			AccesoRolDao dao = (AccesoRolDao) daoManager.getDao(AccesoRolDao.class);
			
			return dao.obtenerIdServicioPorURL(url);
			
		} catch (Exception e){
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	
	public Boolean validarAccesoAdministrativo(Integer id_servicio, Integer id_administrativo){
		try {
			
			DaoManager daoManager = DaoConfig.getDaoManager();
			AccesoRolDao dao = (AccesoRolDao) daoManager.getDao(AccesoRolDao.class);
			
			return dao.validarAccesoAdministrativo(id_servicio, id_administrativo);
			
		} catch (Exception e){
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	
	public Boolean validarAccesoAdministrativoPorUrl(Integer id_administrativo, String url){
		try {
			
			DaoManager daoManager = DaoConfig.getDaoManager();
			AccesoRolDao dao = (AccesoRolDao) daoManager.getDao(AccesoRolDao.class);
			
			return dao.validarAccesoAdministrativoPorUrl(id_administrativo, url);
			
		} catch (Exception e){
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
}
