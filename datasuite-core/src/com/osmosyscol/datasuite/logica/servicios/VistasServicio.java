package com.osmosyscol.datasuite.logica.servicios;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.modelatos.logica.dto.Campo;
import com.osmosyscol.datasuite.modelatos.logica.dto.Estructura;
import com.osmosyscol.datasuite.modelatos.logica.servicios.EstructuraServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ValidadorServicio;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.CampoDao;
import com.osmosyscol.datasuite.persistencia.dao.CreaDatosDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

/**
 * @author osmosys
 * 
 */
public class VistasServicio {

	private static VistasServicio instance;

	private VistasServicio() {
	}

	public static VistasServicio getInstance() {
		if (instance == null) {
			instance = new VistasServicio();
		}
		return instance;
	}

	public Boolean regenerarVistas() {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager(2);

			CreaDatosDao creaDatosDao = (CreaDatosDao) daoManager.getDao(CreaDatosDao.class);
			DaoManager daoManagerData1 = DaoConfig.getDaoManager(1);
			CampoDao campoDao = (CampoDao) daoManagerData1.getDao(CampoDao.class);

			List<Estructura> estructuras = EstructuraServicio.getInstance().obtenerEstructuras();

			if (estructuras != null) {
				for (Estructura estructura : estructuras) {

					try {

						List<Campo> camposlist = campoDao.obtenerCamposPorEstructuraOrdenId(estructura.getId_estructura().intValue());
						creaDatosDao.crearVistaDummy(estructura.getId_estructura(), camposlist);

					} catch (Exception e) {
						SimpleLogger.setError("Error", e);
					}
				}

				for (Estructura estructura : estructuras) {

					try {

						List<Campo> camposlist = campoDao.obtenerCamposPorEstructuraOrdenId(estructura.getId_estructura().intValue());
						creaDatosDao.crearVista(estructura.getId_estructura().toString(), camposlist);

					} catch (Exception e) {
						SimpleLogger.setError("Error", e);
					}
				}

				SimpleLogger.setInfo("FIN DE CREACION DE VISTAS ");
			}

			return true;

		} catch (Exception e) {
			SimpleLogger.setError("Error regenerando vistas", e);
		}
		return false;

	}

}
