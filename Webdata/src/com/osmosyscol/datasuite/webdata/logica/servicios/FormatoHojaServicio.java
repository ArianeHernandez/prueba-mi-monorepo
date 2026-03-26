package com.osmosyscol.datasuite.webdata.logica.servicios;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.modelatos.logica.dto.FormatoCampo;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.FormatoDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class FormatoHojaServicio {

	// ---------------------------------------------------------------

	private static FormatoHojaServicio formatoHojaServicio;

	private FormatoHojaServicio() {
	}

	// ---------------------------------------------------------------

	public static FormatoHojaServicio getInstance() {
		if (formatoHojaServicio == null) {
			formatoHojaServicio = new FormatoHojaServicio();
		}
		return formatoHojaServicio;
	}

	// ---------------------------------------------------------------

	public List<FormatoCampo> obtenerFormatoCampoBase(Integer id_formato) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);

			return formatoDao.obtenerFormatoCampoHojaPorFormato(id_formato);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
