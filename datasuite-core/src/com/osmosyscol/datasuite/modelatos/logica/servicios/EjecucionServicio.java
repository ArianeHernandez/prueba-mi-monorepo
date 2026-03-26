package com.osmosyscol.datasuite.modelatos.logica.servicios;

import java.util.Date;
import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.modelatos.logica.dto.Ejecucion;
import com.osmosyscol.datasuite.modelatos.logica.dto.Log;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.EjecucionDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class EjecucionServicio {

	private static EjecucionServicio ejecucionServicio;

	private EjecucionServicio() {
	}

	public static EjecucionServicio getInstance() {
		if (ejecucionServicio == null) {
			ejecucionServicio = new EjecucionServicio();
		}
		return ejecucionServicio;
	}

	// ------------------------------

	public List<Ejecucion> obtenerEjecucionesPorCarga(Integer id_carga) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			EjecucionDao ejecucionDao = (EjecucionDao) daoManager.getDao(EjecucionDao.class);

			return ejecucionDao.obtenerEjecucionesPorCarga(id_carga);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------

	public List<Log> obtenerLogEjecucionesCarga(Integer id_carga) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager(2);
			EjecucionDao ejecucionDao = (EjecucionDao) daoManager.getDao(EjecucionDao.class);

			return ejecucionDao.obtenerLogEjecucionesCarga(id_carga);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------

	public List<Log> obtenerLogErrorEjecucionesCarga(Integer id_carga) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager(2);
			EjecucionDao ejecucionDao = (EjecucionDao) daoManager.getDao(EjecucionDao.class);

			return ejecucionDao.obtenerLogErrorEjecucionesCarga(id_carga);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------

	public Integer obtenerSiguienteID() {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			EjecucionDao ejecucionDao = (EjecucionDao) daoManager.getDao(EjecucionDao.class);

			return ejecucionDao.obtenerSiguienteID();

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------

	public Boolean crearEjecucion(Integer id_ejecucion, Integer id_carga, Integer id_destino, String estado, Date fecha_inicio, Date fecha_final, String login) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			EjecucionDao ejecucionDao = (EjecucionDao) daoManager.getDao(EjecucionDao.class);

			if (login.contains("|")) {
				login = org.apache.commons.lang.StringUtils.substringAfter(login, "|");
			}

			return ejecucionDao.crearEjecucion(id_ejecucion, id_carga, id_destino, estado, fecha_inicio, fecha_final, login);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return false;
	}

	public List<Log> obtenerLogEjecucion(Integer id_ejecucion) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager(2);
			EjecucionDao ejecucionDao = (EjecucionDao) daoManager.getDao(EjecucionDao.class);

			return ejecucionDao.obtenerLogEjecucion(id_ejecucion);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

}
