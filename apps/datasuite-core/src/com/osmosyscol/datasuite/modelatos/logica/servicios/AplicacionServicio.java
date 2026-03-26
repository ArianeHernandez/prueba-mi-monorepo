package com.osmosyscol.datasuite.modelatos.logica.servicios;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.Aplicacion;
import com.osmosyscol.datasuite.persistencia.dao.AplicacionDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class AplicacionServicio {

	private static AplicacionServicio instancia;

	private AplicacionServicio() {
	}

	public static AplicacionServicio getInstance() {
		if (instancia == null) {
			instancia = new AplicacionServicio();
		}
		return instancia;
	}

	public Aplicacion obtenerAplicacion(String id_aplicacion) {
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();

			AplicacionDao aplicacionDao = (AplicacionDao) daoManager.getDao(AplicacionDao.class);

			return aplicacionDao.obtenerAplicacion(id_aplicacion);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en AplicacionServicio.obtenerAplicaciones", e);
			return null;
		}
	}

}