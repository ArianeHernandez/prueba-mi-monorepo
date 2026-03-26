package com.osmosyscol.datasuite.modelatos.logica.servicios;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.modelatos.logica.dto.Destino;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.DestinoDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class DestinoServicio {

	private static DestinoServicio destinoServicio;

	private DestinoServicio() {
	}

	public static DestinoServicio getInstance() {
		if (destinoServicio == null) {
			destinoServicio = new DestinoServicio();
		}
		return destinoServicio;
	}

	// ------------------------------

	public Destino obtenerDestino(Integer id_destino) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			DestinoDao destinoDao = (DestinoDao) daoManager.getDao(DestinoDao.class);

			return destinoDao.obtenerDestino(id_destino);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	
	// ------------------------------
	
	public List<Destino> obtenerTodosDestino() {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			DestinoDao destinoDao = (DestinoDao) daoManager.getDao(DestinoDao.class);

			return destinoDao.obtenerTodosDestino();

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	// ------------------------------
	
	public Destino guardarDestino(Destino destino) {
		if (destino != null) {
			try {
				DaoManager daoManager = DaoConfig.getDaoManager();
				DestinoDao destinoDao = (DestinoDao) daoManager.getDao(DestinoDao.class);

				Destino rdestino = destinoDao.guardarDestino(destino);
				return rdestino;

			} catch (Exception e) {
				SimpleLogger.setError("Ha ocurrido un error", e);
			}
		}
		return null;
	}
	// ------------------------------
	
	public List<Destino> obtenerDestinosPorFormato(Integer id_formato) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			DestinoDao destinoDao = (DestinoDao) daoManager.getDao(DestinoDao.class);

			return destinoDao.obtenerDestinosPorFormato(id_formato);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	
	public Boolean eliminarDestino(Integer id_destino) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			DestinoDao destinoDao = (DestinoDao) daoManager.getDao(DestinoDao.class);

			return destinoDao.eliminarDestino(id_destino);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	
	
}
