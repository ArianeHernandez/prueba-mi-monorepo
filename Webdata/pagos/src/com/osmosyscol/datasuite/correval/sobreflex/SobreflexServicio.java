package com.osmosyscol.datasuite.correval.sobreflex;

import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class SobreflexServicio {
	
	private static SobreflexServicio instancia;

	private SobreflexServicio() {
	}

	// ---------------------------------------------------------------

	public static SobreflexServicio getInstance() {
		if (instancia == null) {
			instancia = new SobreflexServicio();
		}
		return instancia;
	}

	// ---------------------------------------------------------------
	
	public ParametrosAppletSobreflex getParametrosAppletSobreflex(){
		
		ParametrosAppletSobreflex parametros = new ParametrosAppletSobreflex();
		parametros.setCoordenadasX(ParametrosInicio.getProperty("CoordenadasX"));
		parametros.setCoordenadasY(ParametrosInicio.getProperty("CoordenadasY"));
		parametros.setTamLetras(ParametrosInicio.getProperty("TamLetras"));
		parametros.setMargenes(ParametrosInicio.getProperty("Margenes"));
	
		
		return parametros;
	}

	
	
}
