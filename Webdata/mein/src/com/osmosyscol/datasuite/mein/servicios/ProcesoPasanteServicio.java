package com.osmosyscol.datasuite.mein.servicios;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.mein.dtos.ProcesoPasante;
import com.osmosyscol.datasuite.mein.persistencia.dao.ProcesoPasanteDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class ProcesoPasanteServicio {

	private static ProcesoPasanteServicio servicio;
	private static ProcesoPasanteDao dao;
	
	private ProcesoPasanteServicio() {
		if (dao == null) {
			DaoManager daoManager;
			try {
				daoManager = DaoConfig.getDaoManager();
				dao = (ProcesoPasanteDao) daoManager.getDao(ProcesoPasanteDao.class);
			} catch (Exception e) {
				SimpleLogger.setError("Error creando dao ProcesoPasante", e);
			}
		}
	}
	
	public static ProcesoPasanteServicio getInstance() {
		if (servicio == null) {
			servicio = new ProcesoPasanteServicio();
		}
		
		return servicio;
	}
	
	public ProcesoPasante obtenerProcesoPasante (Integer id_formato) {
		try {
			return dao.obtenerProcesoPasante(id_formato);
		} catch (Exception e) {
			SimpleLogger.setError("Error obtenido proceso pasante para formato " + id_formato, e);
			return null;
		}
	}
	
}
