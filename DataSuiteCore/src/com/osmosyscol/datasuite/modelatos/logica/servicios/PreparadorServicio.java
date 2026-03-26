package com.osmosyscol.datasuite.modelatos.logica.servicios;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.Preparador;
import com.osmosyscol.datasuite.persistencia.dao.PreparadorDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class PreparadorServicio {

	private static PreparadorServicio instancia;

	private PreparadorServicio() {
	}

	public static PreparadorServicio getInstance() {
		if (instancia == null) {
			instancia = new PreparadorServicio();
		}
		return instancia;
	}

	// ----------------------------------

	public List<Preparador> obtenerPreparadores(Integer id_usuario, Integer id_tipo_proceso) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			PreparadorDao preparadorDao = (PreparadorDao) daoManager.getDao(PreparadorDao.class);
				return preparadorDao.obtenerPreparadores(id_usuario, id_tipo_proceso);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}

	}
	
	// ----------------------------------

	public Boolean asociarPreparador(Integer id_proceso, Integer id_preparador) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			PreparadorDao preparadorDao = (PreparadorDao) daoManager.getDao(PreparadorDao.class);
			preparadorDao.asociarPreparador(id_proceso, id_preparador);

			return true;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return false;
		}

	}
	
	// ----------------------------------

	
	public Boolean desasociarPreparador(Integer id_preparador) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			PreparadorDao preparadorDao = (PreparadorDao) daoManager.getDao(PreparadorDao.class);
			preparadorDao.desasociarPreparador(id_preparador);

			return true;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return false;
		}

	}
	
	public List<Preparador> obtenerPreparadoresPorProceso(Integer id_proceso) {
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			PreparadorDao preparadorDao = (PreparadorDao) daoManager.getDao(PreparadorDao.class);

			return preparadorDao.obtenerPreparadoresPorProceso(id_proceso);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
	}
	
}
