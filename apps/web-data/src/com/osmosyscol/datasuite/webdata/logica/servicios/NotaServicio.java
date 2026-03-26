package com.osmosyscol.datasuite.webdata.logica.servicios;

import java.util.Date;
import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.webdata.logica.dto.Nota;
import com.osmosyscol.datasuite.webdata.persistencia.dao.NotaDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class NotaServicio {

	// ---------------------------------------------------------------

	private static NotaServicio instance;

	private NotaServicio() {
	}

	// ---------------------------------------------------------------

	public static NotaServicio getInstance() {
		if (instance == null) {
			instance = new NotaServicio();
		}
		return instance;
	}

	// ---------------------------------------------------------------
	
	public Boolean guardarNota(Integer id_nota, String nota, Date fecha, Integer id_persona, Integer id_carga, String estado, Integer id_revision, String nombre_instancia, Boolean interno) {

		try {
			
			DaoManager daoManager = DaoConfig.getDaoManager();
			NotaDao notaDao = (NotaDao) daoManager.getDao(NotaDao.class);

			return notaDao.guardarNota(id_nota, nota, fecha, id_persona, id_carga, estado, id_revision, nombre_instancia, interno);
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		
		return false;
		
	}

	public List<Nota> obtenerNotasPorCarga(Integer id_carga, Boolean internas) {
		try {
			
			DaoManager daoManager = DaoConfig.getDaoManager();
			NotaDao notaDao = (NotaDao) daoManager.getDao(NotaDao.class);

			return notaDao.obtenerNotasPorCarga(id_carga, internas);
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	
	}
	
	public Integer obtenerSiguiente() {
		try {
			
			DaoManager daoManager = DaoConfig.getDaoManager();
			NotaDao notaDao = (NotaDao) daoManager.getDao(NotaDao.class);

			return notaDao.obtenerSiguiente();
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	
	}
	
	public Nota obtenerNota(Integer id_nota) {
		try {
			
			DaoManager daoManager = DaoConfig.getDaoManager();
			NotaDao notaDao = (NotaDao) daoManager.getDao(NotaDao.class);

			return notaDao.obtenerNota(id_nota);
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	
	}
}