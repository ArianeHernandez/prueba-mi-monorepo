package com.osmosyscol.datasuite.modelatos.logica.servicios;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.modelatos.logica.dto.ParametroCarga;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValorParametroCarga;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.ParametroCargaDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class ParametroCargaServicio {

	private static ParametroCargaServicio parametrocargaServicio;

	private ParametroCargaServicio() {
	}

	public static ParametroCargaServicio getInstance() {
		if (parametrocargaServicio == null) {
			parametrocargaServicio = new ParametroCargaServicio();
		}
		return parametrocargaServicio;
	}

	// ------------------------------

	public Boolean guardarValoresParametrosCarga(Integer id_carga, List<ValorParametroCarga> valorparametrocargaList) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager(2);
			ParametroCargaDao parametroCargaDao = (ParametroCargaDao) daoManager.getDao(ParametroCargaDao.class);

			if (valorparametrocargaList != null && valorparametrocargaList.size() > 0) {
				for (ValorParametroCarga valorParametroCarga : valorparametrocargaList) {
					valorParametroCarga.setId_carga(id_carga);
				}

				return parametroCargaDao.guardarValoresParametrosCarga(valorparametrocargaList);
			}

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return false;
		}
		return true;
	}

	// ------------------------------

	public List<ParametroCarga> obtenerParametrosCarga(Integer id_formato) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			ParametroCargaDao parametroCargaDao = (ParametroCargaDao) daoManager.getDao(ParametroCargaDao.class);

			return parametroCargaDao.obtenerParametrosCarga(id_formato);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	
	
	// ------------------------------

	public List<ParametroCarga> obtenerParametrosCargaPorFormatoSalida(Integer id_formato) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			ParametroCargaDao parametroCargaDao = (ParametroCargaDao) daoManager.getDao(ParametroCargaDao.class);

			return parametroCargaDao.obtenerParametrosCargaPorFormatoSalida(id_formato);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------
	
	public Boolean guardarParametroCarga(ParametroCarga parametroCarga) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			ParametroCargaDao parametroCargaDao = (ParametroCargaDao) daoManager.getDao(ParametroCargaDao.class);

			return parametroCargaDao.guardarParametroCarga(parametroCarga);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
	}
	
	// ------------------------------

	public List<ValorParametroCarga> obtenerValoresParametrosCarga(Integer id_carga) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager(2);
			ParametroCargaDao parametroCargaDao = (ParametroCargaDao) daoManager.getDao(ParametroCargaDao.class);

			return parametroCargaDao.obtenerValoresParametrosCarga(id_carga);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	
	
}
