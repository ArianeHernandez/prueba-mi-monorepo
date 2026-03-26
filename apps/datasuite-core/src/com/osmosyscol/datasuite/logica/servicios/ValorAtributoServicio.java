package com.osmosyscol.datasuite.logica.servicios;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.ValorAtributo;
import com.osmosyscol.datasuite.persistencia.dao.ValorAtributoDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class ValorAtributoServicio {

	private static ValorAtributoServicio instance;

	private ValorAtributoServicio() {
	}

	public static ValorAtributoServicio getInstance() {
		if (instance == null) {
			instance = new ValorAtributoServicio();
		}
		return instance;
	}

	// ----------------------------------------------------
	public Integer crearValorAtributo(ValorAtributo valorAtributo) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			ValorAtributoDao valorAtributoDao = (ValorAtributoDao) daoManager.getDao(ValorAtributoDao.class);
			return valorAtributoDao.crearValorAtributo(valorAtributo);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un Error en el servicio crearValorAtributo.", e);
			return null;
		}
	}

	// ----------------------------------------------------
	public Boolean actualizarListaValorAtributo(List<ValorAtributo> listValorAtributo) {

		try {
			if (listValorAtributo.size() < 1) {
				return false;
			}
			this.eliminarValorAtributosAdministrativo(listValorAtributo.get(0).getId_administrativo());
			for (ValorAtributo valorAtributo : listValorAtributo) {
				this.crearValorAtributo(valorAtributo);
			}
			return true;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un Error en el servicio actualizarListaValorAtributo.", e);
			return false;
		}
	}

	// ----------------------------------------------------
	public Boolean actualizarValorAtributo(ValorAtributo valorAtributo) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			ValorAtributoDao ValorAtributoDao = (ValorAtributoDao) daoManager.getDao(ValorAtributoDao.class);
			return ValorAtributoDao.actualizarValorAtributo(valorAtributo);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un Error en el servicio actualizarValorAtributo.", e);
			return false;
		}
	}

	// ----------------------------------------------------
	public Boolean eliminarValorAtributo(ValorAtributo valorAtributo) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			ValorAtributoDao valorAtributoDao = (ValorAtributoDao) daoManager.getDao(ValorAtributoDao.class);
			return valorAtributoDao.eliminarValorAtributo(valorAtributo);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un Error en el servicio eliminarValorAtributo.", e);
			return false;
		}
	}

	// ----------------------------------------------------
	public Boolean eliminarTodoValoresAtributo(Integer id_atributo) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			ValorAtributoDao valorAtributoDao = (ValorAtributoDao) daoManager.getDao(ValorAtributoDao.class);
			return valorAtributoDao.eliminarTodoValoresAtributo(id_atributo);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un Error en el servicio eliminarTodoValoresAtributo.", e);
			return false;
		}
	}

	// ----------------------------------------------------
	public Boolean eliminarValorAtributosAdministrativo(Integer id_administrativo) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			ValorAtributoDao valorAtributoDao = (ValorAtributoDao) daoManager.getDao(ValorAtributoDao.class);
			return valorAtributoDao.eliminarValorAtributosAdministrativo(id_administrativo);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un Error en el servicio eliminarValorAtributo.", e);
			return false;
		}
	}

	// ----------------------------------------------------
	public List<ValorAtributo> obtenerValorAtributo(ValorAtributo valorAtributo) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			ValorAtributoDao valorAtributoDao = (ValorAtributoDao) daoManager.getDao(ValorAtributoDao.class);
			return valorAtributoDao.obtenerValorAtributo(valorAtributo);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un Error en el servicio obtenerValorAtributo.", e);
			return null;
		}
	}

	// ----------------------------------------------------
	public List<ValorAtributo> obtenerValorAtributoPor(Integer id_administrativo) {
		ValorAtributo valorAtributo = new ValorAtributo();
		valorAtributo.setId_administrativo(id_administrativo);
		return this.obtenerValorAtributo(valorAtributo);
	}

	// ----------------------------------------------------
	public Integer obtenerIdSiguiente() {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			ValorAtributoDao valorAtributoDao = (ValorAtributoDao) daoManager.getDao(ValorAtributoDao.class);
			return valorAtributoDao.obtenerIdSiguiente();
		} catch (Exception e) {
			SimpleLogger.setError("Error en servicio ValorAtributoServicio.obtenerIdSiguiente", e);
		}
		return null;

	}

	// ----------------------------------------------------

}
