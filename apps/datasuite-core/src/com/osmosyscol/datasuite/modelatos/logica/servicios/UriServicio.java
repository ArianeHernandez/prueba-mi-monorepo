package com.osmosyscol.datasuite.modelatos.logica.servicios;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.modelatos.logica.dto.Uri;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.UriDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class UriServicio {

	private static UriServicio uriServicio;

	private UriServicio() {
	}

	public static UriServicio getInstance() {
		if (uriServicio == null) {
			uriServicio = new UriServicio();
		}
		return uriServicio;
	}

	// ------------------------------

	public Uri obtenerUri(Integer id_uri) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			UriDao uriDao = (UriDao) daoManager.getDao(UriDao.class);

			return uriDao.obtenerUri(id_uri);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	
	// ------------------------------
	
	public List<Uri> obtenerTodasUri(Integer numero_pagina) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			UriDao uriDao = (UriDao) daoManager.getDao(UriDao.class);

			return uriDao.obtenerTodasUri(numero_pagina);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	// ------------------------------
	
	public Integer totalUris() {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			UriDao uriDao = (UriDao) daoManager.getDao(UriDao.class);

			return uriDao.totalUris();

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	
	// ------------------------------
	public Boolean guardarUri(Uri uri) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			UriDao uriDao = (UriDao) daoManager.getDao(UriDao.class);

			return uriDao.guardarUri(uri);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	
	// ------------------------------
	
	public Boolean eliminarUri(Integer id_uri){
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			UriDao uriDao = (UriDao) daoManager.getDao(UriDao.class);

			return uriDao.eliminarUri(id_uri);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	
}
