package com.osmosyscol.datasuite.webdata.logica.servicios;

import java.util.List;

import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.datasuite.webdata.persistencia.dao.CargaDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class AjusteCargaServicio {

	private static AjusteCargaServicio instance = new AjusteCargaServicio();

	private AjusteCargaServicio() {
	}

	public static AjusteCargaServicio getInstance() {
		return instance;
	}

	// ------------------------------------
	
	public void ajustarCargasSinFinalizar(){
		
		List<Carga> cargas = obtenerCargasSinFinalizar();
		
		if(cargas != null){
			for (Carga carga : cargas) {
				CargaServicio.getInstance().finalizarCarga(carga);
			}
		}
	}
	
	// ------------------------------------

	public List<Carga> obtenerCargasSinFinalizar(){
		
		CargaDao cargaDao = (CargaDao) DaoConfig.getDao(CargaDao.class);
		
		return cargaDao.obtenerCargasSinFinalizar();
		
	}
	
	// ------------------------------------
	
}