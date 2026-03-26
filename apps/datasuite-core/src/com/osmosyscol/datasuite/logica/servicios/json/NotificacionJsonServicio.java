package com.osmosyscol.datasuite.logica.servicios.json;

import java.util.List;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.logica.dto.Notificacion;
import com.osmosyscol.datasuite.logica.servicios.NotificacionServicio;

public class NotificacionJsonServicio implements JsonService {

	private Session session;
	
	public NotificacionJsonServicio() {
	}

	private NotificacionServicio notificacionServicio = NotificacionServicio.getInstance();

	public void setSession(Session session) {
		this.session = session;
	}

	private Integer obtenerIdAdministrativo() {
		try {
			return (Integer) session.getAttribute("id_administrativo");

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error.", e);
		}
		return null;
	}

	public List<Notificacion> obtenerNotificacionesSinLeerAdministrativo() {
		Integer id_administrativo = obtenerIdAdministrativo();
		if (id_administrativo != null) {
			return notificacionServicio.obtenerNotificacionesSinLeerAdministrativo(id_administrativo);
		}
		return null;
	}

	public Integer contarNotificacionesSinLeerAdministrativo() {
		Integer id_administrativo = obtenerIdAdministrativo();
		if (id_administrativo != null) {
			return notificacionServicio.contarNotificacionesSinLeerAdministrativo(id_administrativo);
		}
		return null;
	}
	
	public Boolean marcarNotificacionLeida(Integer id_notificacion){
		return notificacionServicio.marcarNotificacionLeida(id_notificacion);
	}
	
	public List<Notificacion> obtenerNotificacionesNuevas() {
		Integer id_administrativo = obtenerIdAdministrativo();
		if (id_administrativo != null) {
			return notificacionServicio.obtenerNotificacionesNuevas(id_administrativo);
		}
		return null;
	}
	
	public Boolean marcarNotificacionNueva(Integer id_notificacion){
	
		Integer id_administrativo = obtenerIdAdministrativo();
		if (id_administrativo != null) {
		return notificacionServicio.marcarNotificacionNueva(id_notificacion);	
		}
		return null;
	}
	
}
