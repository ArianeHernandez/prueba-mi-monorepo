package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.NotificacionAccion;
import com.osmosyscol.datasuite.persistencia.dao.NotificacionAccionDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class NotificacionAccionDaoImp extends BaseSqlMapDao implements NotificacionAccionDao {

	public NotificacionAccionDaoImp(DaoManager daoManager) {
		super(daoManager);
	}
	
	
	public Boolean insertarNotificacionAccion(Integer id_notificacion_accion,Integer id_accion,
			String tipo,String correo,Integer id_administrativo,String mensaje){
		
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_notificacion_accion", id_notificacion_accion);
		map.put("id_accion", id_accion);
		map.put("tipo", tipo);
		map.put("correo", correo);
		map.put("id_administrativo", id_administrativo);
		map.put("mensaje", mensaje);
		
		Boolean conf = false;
		try {
			insert("NotificacionAccion.insertarNotificacionAccion", map);
			conf=true;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en NotificacionAccionDaoImp.insertarNotificacionAccion", e);
		}
		return conf;
		
	}
	
	public Integer obtenerSiguienteIDNotificacionAccion(){
		return (Integer) queryForObject("NotificacionAccion.obtenerSiguienteIDNotificacionAccion", null);
	}
	
	public Boolean borrarNotificacionAccion(Integer id_notificacion_accion){
		Integer cantidad = delete("NotificacionAccion.borrarNotificacionAccion", id_notificacion_accion);
		return cantidad>0;
	}
	
	public Boolean actualizarNotificacionAccion(Integer id_notificacion_accion,String tipo,
			String correo,Integer id_administrativo,String mensaje){
		
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_notificacion_accion", id_notificacion_accion);
		map.put("tipo", tipo);
		map.put("correo", correo);
		map.put("id_administrativo", id_administrativo);
		map.put("mensaje", mensaje);
		
		Integer cantidad = update("NotificacionAccion.actualizarNotificacionAccion", map);
		
		return cantidad>0;
	}
	
	public NotificacionAccion obtenerNotificacionAccion(Integer id_notificacion_accion){
		return (NotificacionAccion) queryForObject("NotificacionAccion.obtenerNotificacionAccion", id_notificacion_accion);
	}
	
	public List<NotificacionAccion> obtenerNotificacionesAccPorAccion(Integer id_accion){
		return queryForList("NotificacionAccion.obtenerNotificacionesAccPorAccion", id_accion);
	}

}
