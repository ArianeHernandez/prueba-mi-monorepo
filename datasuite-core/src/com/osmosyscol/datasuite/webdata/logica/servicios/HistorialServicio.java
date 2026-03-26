package com.osmosyscol.datasuite.webdata.logica.servicios;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.webdata.logica.dto.HistorialCarga;
import com.osmosyscol.datasuite.webdata.persistencia.dao.HistorialDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class HistorialServicio {

	private static HistorialServicio instance;
	
	private HistorialServicio() {
	
	}
	
	public static HistorialServicio getInstance() {
		if (instance == null) {
			instance = new HistorialServicio();
		}
		return instance;
	}

	public Boolean guardarHistorialCarga(HistorialCarga historialCarga){
		DaoManager daoManager;
		try {
			daoManager = DaoConfig.getDaoManager();
			return guardarHistorialCarga(historialCarga, daoManager);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return false;
	}
	
	public Boolean guardarHistorialCarga(HistorialCarga historialCarga, DaoManager daoManager){
		try {

			HistorialDao historialDao = (HistorialDao)daoManager.getDao(HistorialDao.class);
			
			return historialDao.guardarHistorialCarga(historialCarga);
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return false;
	}
	
	public List<HistorialCarga> obtenerHistorialCarga(Integer id_carga){
		
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			HistorialDao historialDao = (HistorialDao)daoManager.getDao(HistorialDao.class);
			
			return historialDao.obtenerHistorialCarga(id_carga);
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	
	public HistorialCarga obtenerUltimoHistorialCarga(Integer id_carga){
		
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			HistorialDao historialDao = (HistorialDao)daoManager.getDao(HistorialDao.class);
			
			return historialDao.obtenerUltimoHistorialCarga(id_carga);
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	
	public Integer obtenerPesoHistorialCarga(Integer id_carga, String estadoInicial){
		
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			HistorialDao historialDao = (HistorialDao)daoManager.getDao(HistorialDao.class);
			
			return historialDao.obtenerPesoHistorialCarga(id_carga, estadoInicial);
			
		} catch (Exception e) {
			SimpleLogger.setError("No se ha podido recuperar el peso total de la carga en el histórico", e);
		} 
		return null;
	}
	
	public Integer obtenerVecesLiberadoHistorialCarga(Integer id_persona, Integer id_carga, String estadoInicial){
		
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			HistorialDao historialDao = (HistorialDao)daoManager.getDao(HistorialDao.class);
			
			return historialDao.obtenerVecesLiberadoHistorialCarga(id_persona, id_carga, estadoInicial);
			
		} catch (Exception e) {
			SimpleLogger.setError("No se ha podido recuperar la cantidad de veces que ha sido liberado por el liberador " + id_persona, e);
		} 
		return null;
	}
	
}
