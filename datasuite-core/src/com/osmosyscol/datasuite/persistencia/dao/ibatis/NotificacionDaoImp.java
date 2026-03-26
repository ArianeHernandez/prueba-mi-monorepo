package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.config.Constantes;
import com.osmosyscol.datasuite.logica.dto.Notificacion;
import com.osmosyscol.datasuite.persistencia.dao.NotificacionDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class NotificacionDaoImp extends BaseSqlMapDao implements NotificacionDao {

	public NotificacionDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	@SuppressWarnings("unchecked")
	public List<Notificacion> obtenerNotificacionesAdministrativo(Integer idAdministrativo, Integer pagina) {
		return queryForListPag("Notificacion.obtenerNotificacionesAdministrativo", idAdministrativo, pagina, Constantes.PAGINACIONLISTADO);
	}

	@SuppressWarnings("unchecked")
	public List<Notificacion> obtenerNotificacionesSinLeerAdministrativo(Integer idAdministrativo) {
		return queryForList("Notificacion.obtenerNotificacionesSinLeerAdministrativo", idAdministrativo);
	}

	public Integer contarNotificacionesAdministrativo(Integer idAdministrativo) {
		return (Integer) queryForObject("Notificacion.contarNotificacionesAdministrativo", idAdministrativo);
	}

	public Integer contarNotificacionesSinLeerAdministrativo(Integer idAdministrativo) {
		return (Integer) queryForObject("Notificacion.contarNotificacionesSinLeerAdministrativo", idAdministrativo);
	}

	public Notificacion obtenerNotificacion(Integer idNotificacion) {
		return (Notificacion) queryForObject("Notificacion.obtenerNotificacion", idNotificacion);
	}

	public Boolean marcarNotificacionLeida(Integer idNotificacion, String leida) {
		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("id_notificacion", idNotificacion);
		map.put("leida", leida);

		update("Notificacion.marcarNotificacionLeida", map);
		return true;
	}

	public Integer obtenerPaginaNotificacion(Integer idNotificacion) {
		Integer posicion = (Integer)queryForObject("Notificacion.obtenerPosicion", idNotificacion);
		if (posicion != null) {
			return (posicion / 10) + 1;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Notificacion> obtenerNotificacionesNuevas(Integer idAdministrativo) {
		return queryForList("Notificacion.obtenerNotificacionesNuevas", idAdministrativo);
	}

	public Boolean marcarNotificacionNueva(Integer idNotificacion, String nueva) {
		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("id_notificacion", idNotificacion);
		map.put("nueva", nueva);

		update("Notificacion.marcarNotificacionNueva", map);
		return true;
	}

	public Boolean guardarNotificacion(Notificacion notificacion) {
		
		insert("Notificacion.guardarNotificacion", notificacion);
		return true;
	}

}
