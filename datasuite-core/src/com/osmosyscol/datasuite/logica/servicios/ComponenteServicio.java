package com.osmosyscol.datasuite.logica.servicios;

import java.util.List;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.Componente;
import com.osmosyscol.datasuite.persistencia.dao.ComponenteDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class ComponenteServicio {

	private static ComponenteServicio instance;

	private ComponenteServicio() {

	}

	public static ComponenteServicio getInstance() {
		if (instance == null) {
			instance = new ComponenteServicio();
		}
		return instance;
	}

	// ----------------------------------------------------------------------

	public List<Componente> obtenerComponentes() {

		try {
			ComponenteDao componenteDao = (ComponenteDao) DaoConfig.getDao(ComponenteDao.class);

			return componenteDao.obteneComponentes();

		} catch (Exception e) {
			SimpleLogger.setError("Error", e);
		}

		return null;

	}

}