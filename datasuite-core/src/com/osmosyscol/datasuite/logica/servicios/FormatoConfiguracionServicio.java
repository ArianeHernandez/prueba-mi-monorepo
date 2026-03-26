package com.osmosyscol.datasuite.logica.servicios;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.FormatoConfiguracion;
import com.osmosyscol.datasuite.persistencia.dao.FormatoConfiguracionDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class FormatoConfiguracionServicio {
	
	private static FormatoConfiguracionServicio servicio = null;
	
	private FormatoConfiguracionServicio(){
		
	}
	
	public static FormatoConfiguracionServicio getInstance(){
		if (servicio == null){
			servicio = new FormatoConfiguracionServicio();
		}
		return servicio;
	}
	
	public List<FormatoConfiguracion> obtenerConfiguracionesFormato(Integer id_formato){
		try {
			
			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoConfiguracionDao dao = (FormatoConfiguracionDao) daoManager.getDao(FormatoConfiguracionDao.class);
			
			return dao.obtenerConfiguracionesFormato(id_formato);
			
		}catch (Exception e){
			SimpleLogger.setError("Error en FormatoConfiguracionServicio.obtenerConfiguracionesFormato", e);
		}
		return null;
	}
	
	public FormatoConfiguracion obtenerConfiguracionFormato(Integer id_formato, String tipo){
		try {
			
			DaoManager daoManager = DaoConfig.getDaoManager();
			FormatoConfiguracionDao dao = (FormatoConfiguracionDao) daoManager.getDao(FormatoConfiguracionDao.class);
			
			return dao.obtenerConfiguracionFormato(id_formato, tipo);
			
		}catch (Exception e){
			SimpleLogger.setError("Error en FormatoConfiguracionServicio.obtenerConfiguracionFormato", e);
		}
		return null;
	}

}
