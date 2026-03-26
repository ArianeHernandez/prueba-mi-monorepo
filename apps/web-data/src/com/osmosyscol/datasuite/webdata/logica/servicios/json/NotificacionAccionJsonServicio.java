package com.osmosyscol.datasuite.webdata.logica.servicios.json;

import java.util.List;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.logica.dto.NotificacionAccion;
import com.osmosyscol.datasuite.modelatos.logica.servicios.NotificacionAccionServicio;

public class NotificacionAccionJsonServicio implements JsonService {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public Boolean insertarNotificacionAccion(Integer id_notificacion_accion,Integer id_accion,
			String tipo,String correo,Integer id_administrativo,String mensaje){
		return NotificacionAccionServicio.getInstance().insertarNotificacionAccion(id_notificacion_accion, id_accion, 
				tipo, correo, id_administrativo, mensaje);
	}
	
	public Integer obtenerSiguienteIDNotificacionAccion(){
		return NotificacionAccionServicio.getInstance().obtenerSiguienteIDNotificacionAccion();
	}
	
	public Boolean borrarNotificacionAccion(Integer id_notificacion_accion){
		return NotificacionAccionServicio.getInstance().borrarNotificacionAccion(id_notificacion_accion);
	}
	
	public Boolean actualizarNotificacionAccion(Integer id_notificacion_accion,String tipo,
			String correo,Integer id_administrativo,String mensaje){
		return NotificacionAccionServicio.getInstance().actualizarNotificacionAccion(id_notificacion_accion, tipo,
				correo, id_administrativo, mensaje);
	}
	
	public NotificacionAccion obtenerNotificacionAccion(Integer id_notificacion_accion){
		return NotificacionAccionServicio.getInstance().obtenerNotificacionAccion(id_notificacion_accion);
	}
	
	public List<NotificacionAccion> obtenerNotificacionesAccPorAccion(Integer id_accion){
		return NotificacionAccionServicio.getInstance().obtenerNotificacionesAccPorAccion(id_accion);
	}
	
}
