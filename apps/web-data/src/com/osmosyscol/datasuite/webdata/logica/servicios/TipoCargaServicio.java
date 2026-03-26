package com.osmosyscol.datasuite.webdata.logica.servicios;

import java.util.List;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.webdata.logica.dto.TipoCarga;
import com.osmosyscol.datasuite.webdata.persistencia.dao.TipoCargaDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class TipoCargaServicio {
	
	private static TipoCargaServicio instance;
	
	private TipoCargaServicio() {
	}
	
	public static TipoCargaServicio getInstance() {
		if(instance == null){
			instance = new TipoCargaServicio();
		}
		return instance;
	}

	
	public List<TipoCarga> obtenerTiposCarga(){
		try {
			
			TipoCargaDao tipoCargaDao = (TipoCargaDao) DaoConfig.getDao(TipoCargaDao.class);
			return tipoCargaDao.obtenerTiposCarga();
			
		} catch (Throwable e) {
			SimpleLogger.setError("Ha ocurrido un error" ,e);
		}
		return null;
	}
}
