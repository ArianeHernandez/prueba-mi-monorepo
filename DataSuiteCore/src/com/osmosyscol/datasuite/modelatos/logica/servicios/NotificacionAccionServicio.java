package com.osmosyscol.datasuite.modelatos.logica.servicios;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.NotificacionAccion;
import com.osmosyscol.datasuite.persistencia.dao.NotificacionAccionDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;


public class NotificacionAccionServicio {

	private static NotificacionAccionServicio instancia;

	private NotificacionAccionServicio() {
	}

	public static NotificacionAccionServicio getInstance() {
		if (instancia == null) {
			instancia = new NotificacionAccionServicio();
		}
		return instancia;
	}	
	
	
	public Boolean insertarNotificacionAccion(Integer id_notificacion_accion,Integer id_accion,
			String tipo,String correo,Integer id_administrativo,String mensaje){
		
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			NotificacionAccionDao notificacionAccionDao = (NotificacionAccionDao) daoManager.getDao(NotificacionAccionDao.class);
			return notificacionAccionDao.insertarNotificacionAccion(id_notificacion_accion, id_accion, 
					tipo, correo, id_administrativo, mensaje);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en NotificacionAccionServicio.insertarNotificacionAccion", e);
			return false;
		}
		
	}
	
	public Integer obtenerSiguienteIDNotificacionAccion(){
		
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			NotificacionAccionDao notificacionAccionDao = (NotificacionAccionDao) daoManager.getDao(NotificacionAccionDao.class);
			return notificacionAccionDao.obtenerSiguienteIDNotificacionAccion();

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en NotificacionAccionServicio.obtenerSiguienteIDNotificacionAccion", e);
			return null;
		}
		
	}
	
	public Boolean borrarNotificacionAccion(Integer id_notificacion_accion){
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			NotificacionAccionDao notificacionAccionDao = (NotificacionAccionDao) daoManager.getDao(NotificacionAccionDao.class);
			return notificacionAccionDao.borrarNotificacionAccion(id_notificacion_accion);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en NotificacionAccionServicio.borrarNotificacionAccion", e);
			return false;
		}
	}
	
	public Boolean actualizarNotificacionAccion(Integer id_notificacion_accion,String tipo,
			String correo,Integer id_administrativo,String mensaje){
		
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			NotificacionAccionDao notificacionAccionDao = (NotificacionAccionDao) daoManager.getDao(NotificacionAccionDao.class);
			return notificacionAccionDao.actualizarNotificacionAccion(id_notificacion_accion, tipo, correo, id_administrativo, mensaje);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en NotificacionAccionServicio.actualizarNotificacionAccion", e);
			return false;
		}
		
	}
	
	public NotificacionAccion obtenerNotificacionAccion(Integer id_notificacion_accion){
		
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			NotificacionAccionDao notificacionAccionDao = (NotificacionAccionDao) daoManager.getDao(NotificacionAccionDao.class);
			return notificacionAccionDao.obtenerNotificacionAccion(id_notificacion_accion);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en NotificacionAccionServicio.obtenerNotificacionAccion", e);
			return null;
		}
		
	}
	
	public List<NotificacionAccion> obtenerNotificacionesAccPorAccion(Integer id_accion){
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			NotificacionAccionDao notificacionAccionDao = (NotificacionAccionDao) daoManager.getDao(NotificacionAccionDao.class);
			return notificacionAccionDao.obtenerNotificacionesAccPorAccion(id_accion);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en NotificacionAccionServicio.obtenerNotificacionesAccPorAccion", e);
			return null;
		}
	}
	
}
