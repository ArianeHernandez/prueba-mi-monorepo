package com.osmosyscol.datasuite.webdata.logica.servicios;

import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class FirmaServicio {
	
	// ---------------------------------------------------------------

	private static FirmaServicio firmaServicio;
	
	private FirmaServicio(){
	}
	
	// ---------------------------------------------------------------
	
	public static FirmaServicio getInstance(){
		if (firmaServicio == null){
			firmaServicio = new FirmaServicio();
		}
		return	firmaServicio;
	}
	
	
	// ---------------------------------------------------------------
	
	public String obtenerUrlServidor(){
		ParametrosInicio.loadProperties("DATASUITE");
		String aplicacion = ParametrosInicio.getProperty("firma.aplicacion");
		String servicio = ParametrosInicio.getProperty("firma.servicio");
		
		return aplicacion+servicio;
	}
}
