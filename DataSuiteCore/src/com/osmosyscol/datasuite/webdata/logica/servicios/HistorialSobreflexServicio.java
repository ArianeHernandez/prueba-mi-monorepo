package com.osmosyscol.datasuite.webdata.logica.servicios;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.HistorialSobreflex;
import com.osmosyscol.datasuite.webdata.persistencia.dao.HistorialSobreflexDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class HistorialSobreflexServicio {

	// ---------------------------------------------------------------

	private static HistorialSobreflexServicio instance;

	private HistorialSobreflexServicio() {
	}

	// ---------------------------------------------------------------

	public static HistorialSobreflexServicio getInstance() {
		if (instance == null) {
			instance = new HistorialSobreflexServicio();
		}
		return instance;
	}

	// ---------------------------------------------------------------

	public Boolean guardarHistorialSobreflex(String login, String hash) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			HistorialSobreflexDao historialSobreflexDao = (HistorialSobreflexDao) daoManager.getDao(HistorialSobreflexDao.class);

			HistorialSobreflex historialSobreflex = new HistorialSobreflex();
			
			historialSobreflex.setId(historialSobreflexDao.obtenerSiguiente());
			historialSobreflex.setHash(hash);
			historialSobreflex.setLogin(login);
			historialSobreflex.setActivo("S");

			return historialSobreflexDao.guardarHistorialSobreflex(historialSobreflex);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return false;

	}

	// ---------------------------------------------------------------

	public String obtenerHistorialSobreflex(String hash) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			HistorialSobreflexDao historialSobreflexDao = (HistorialSobreflexDao) daoManager.getDao(HistorialSobreflexDao.class);

			HistorialSobreflex historialSobreflex = historialSobreflexDao.obtenerHistorialSobreflex(hash);
			
			if(historialSobreflex == null){
				SimpleLogger.setError("No se pudo obtener el historial sobreflex en HistorialSobreFlexServicio.obtenerHistorialSobreFlex("+hash+")");
				return null;
			}
			
			String login = historialSobreflex.getLogin();

			desactivarHistorialSobreflex(hash);

			return login;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;

	}

	// ---------------------------------------------------------------

	public Boolean desactivarHistorialSobreflex(String hash) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			HistorialSobreflexDao historialSobreflexDao = (HistorialSobreflexDao) daoManager.getDao(HistorialSobreflexDao.class);

			return historialSobreflexDao.desactivarHistorialSobreflex(hash);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return false;

	}

	// ---------------------------------------------------------------

}