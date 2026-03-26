package com.osmosyscol.datasuite.modelatos.logica.servicios;

import com.osmosyscol.datasuite.modelatos.persistencia.dao.ValidadorDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class ValidadorServicio {

	public static ValidadorServicio instance;

	public static ValidadorServicio getInstance() {
		if (instance == null) {
			instance = new ValidadorServicio();
		}
		return instance;
	}

	public void validar_creadatos() {

		ValidadorDao validadorDao = (ValidadorDao) DaoConfig.getDao(ValidadorDao.class, 2);
		validadorDao.validar_creadatos();

	}

	public void validar_datasuite() {

		ValidadorDao validadorDao = (ValidadorDao) DaoConfig.getDao(ValidadorDao.class, 2);
		validadorDao.validar_datasuite();
	}

}
