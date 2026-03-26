package com.osmosyscol.datasuite.logica.servicios;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.persistencia.dao.CreaDatosDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class ConsultaDatosServicio {

	private static ConsultaDatosServicio consultadatosServicio;

	private ConsultaDatosServicio() {
	}

	public static ConsultaDatosServicio getInstance() {
		if (consultadatosServicio == null) {
			consultadatosServicio = new ConsultaDatosServicio();
		}
		return consultadatosServicio;
	}

	// -------------------------------------------------------------

	public Boolean existeTabla(String idTabla) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager(2);
			CreaDatosDao creaDatosDao = (CreaDatosDao) daoManager.getDao(CreaDatosDao.class);
			return creaDatosDao.existeTabla(idTabla.toUpperCase());
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
}
