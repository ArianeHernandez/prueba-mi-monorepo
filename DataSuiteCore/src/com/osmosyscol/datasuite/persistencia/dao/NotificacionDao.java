package com.osmosyscol.datasuite.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.logica.dto.Notificacion;

public interface NotificacionDao {

	public List<Notificacion> obtenerNotificacionesAdministrativo(Integer idAdministrativo, Integer pagina);

	public List<Notificacion> obtenerNotificacionesSinLeerAdministrativo(Integer idAdministrativo);

	public Integer contarNotificacionesAdministrativo(Integer idAdministrativo);

	public Integer contarNotificacionesSinLeerAdministrativo(Integer idAdministrativo);

	public Notificacion obtenerNotificacion(Integer idNotificacion);

	public Boolean marcarNotificacionLeida(Integer idNotificacion, String leida);

	public Integer obtenerPaginaNotificacion(Integer idNotificacion);

	public List<Notificacion> obtenerNotificacionesNuevas(Integer idAdministrativo);

	public Boolean marcarNotificacionNueva(Integer idNotificacion, String no);
	
	public Boolean guardarNotificacion(Notificacion notificacion);

}
