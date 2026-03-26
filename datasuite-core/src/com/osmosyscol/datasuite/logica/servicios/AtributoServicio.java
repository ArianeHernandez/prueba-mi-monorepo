package com.osmosyscol.datasuite.logica.servicios;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.Atributo;
import com.osmosyscol.datasuite.persistencia.dao.AtributoDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;



public class AtributoServicio {

	private static AtributoServicio instance;

	private AtributoServicio() {
	}

	public static AtributoServicio getInstance() {
		if (instance == null) {
			instance = new AtributoServicio();
		}
		return instance;
	}
	
	// ----------------------------------------------------
	public Integer crearAtributo(Atributo atributo) {

		try {
			atributo.setId_atributo(this.obtenerIdSiguiente());
			AtributoDao atributoDao = DaoConfig.getDao(AtributoDao.class);
			return atributoDao.crearAtributo(atributo);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un Error en el servicio crearAtributo.", e);
			return null;
		}
	}

	// ----------------------------------------------------
	public Boolean actualizarAtributo(Atributo atributo) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			AtributoDao atributoDao = (AtributoDao) daoManager.getDao(AtributoDao.class);
			
			return atributoDao.actualizarAtributo(atributo);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un Error en el servicio actualizarAtributo.", e);
			return false;
		}
	}

	// ----------------------------------------------------
	public Boolean eliminarAtributo(Integer id_atributo) {

		try {
			ValorAtributoServicio.getInstance().eliminarTodoValoresAtributo(id_atributo);
			AtributoDao atributoDao = DaoConfig.getDao(AtributoDao.class);
			return atributoDao.eliminarAtributo(id_atributo);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un Error en el servicio eliminarAtributo.", e);
			return false;
		}
	}

	// ----------------------------------------------------
	public Atributo obtenerAtributo(Integer id_atributo) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			AtributoDao atributoDao = (AtributoDao) daoManager.getDao(AtributoDao.class);
			return atributoDao.obtenerAtributo(id_atributo);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un Error en el servicio obtenerAtributo.", e);
			return null;
		}
	}

	// ----------------------------------------------------
	public List<Atributo> obtenerAtributosUsuario(Integer id_usuario) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			AtributoDao atributoDao = (AtributoDao) daoManager.getDao(AtributoDao.class);
			return atributoDao.obtenerAtributosUsuario(id_usuario);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un Error en el servicio obtenerAtributo.", e);
			return null;
		}
	}

	// ----------------------------------------------------
	public Integer obtenerIdSiguiente() {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			AtributoDao atributoDao = (AtributoDao) daoManager.getDao(AtributoDao.class);
			return atributoDao.obtenerIdSiguiente();
		} catch (Exception e) {
			SimpleLogger.setError("Error en servicio AtributoServicio.obtenerIdSiguiente", e);
		}
		return null;

	}
	
	// ----------------------------------------------------
	

}
