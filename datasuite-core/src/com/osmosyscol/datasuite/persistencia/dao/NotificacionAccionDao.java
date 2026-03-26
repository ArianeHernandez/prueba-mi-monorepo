package com.osmosyscol.datasuite.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.logica.dto.NotificacionAccion;

public interface NotificacionAccionDao {
	
	public Boolean insertarNotificacionAccion(Integer id_notificacion_accion,Integer id_accion,String tipo,String correo,Integer id_administrativo,String mensaje);
	
	public Integer obtenerSiguienteIDNotificacionAccion();
	
	public Boolean borrarNotificacionAccion(Integer id_notificacion_accion);
	
	public Boolean actualizarNotificacionAccion(Integer id_notificacion_accion,String tipo,String correo,Integer id_administrativo,String mensaje);
	
	public NotificacionAccion obtenerNotificacionAccion(Integer id_notificacion_accion);
	
	public List<NotificacionAccion> obtenerNotificacionesAccPorAccion(Integer id_accion);

}

