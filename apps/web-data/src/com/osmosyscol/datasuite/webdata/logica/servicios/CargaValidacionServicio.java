package com.osmosyscol.datasuite.webdata.logica.servicios;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.datasuite.webdata.persistencia.dao.CargaDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class CargaValidacionServicio {
	// ---------------------------------------------------------------

	private static CargaValidacionServicio instancia;

	private CargaValidacionServicio() {
	}

	// ---------------------------------------------------------------

	public static CargaValidacionServicio getInstance() {
		if (instancia == null) {
			instancia = new CargaValidacionServicio();
		}
		return instancia;
	}

	// ---------------------------------------------------------------

	/**
	 * Obtiene todas las cargas similares (igual cantidad de registros, igual
	 * valor e igual cliente) segun el formato de salida.
	 */
	public List<Carga> obtenerCargasSimilaresCliente(Integer id_carga, Integer id_formato_salida, Integer id_cliente) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			return cargaDao.obtenerCargasSimilaresCliente(id_carga, id_formato_salida, id_cliente);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	
	// ---------------------------------------------------------------

	/**
	 * Obtiene todas las cargas similares (igual cantidad de registros, igual
	 * valor e igual cliente) segun el formato de salida, ya liberadas.
	 */
	public List<Carga> obtenerCargasSimilaresLiberadas(Integer id_carga, Integer id_formato_salida, Integer id_cliente) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			return cargaDao.obtenerCargasSimilaresLiberadas(id_carga, id_formato_salida, id_cliente);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ---------------------------------------------------------------
}
