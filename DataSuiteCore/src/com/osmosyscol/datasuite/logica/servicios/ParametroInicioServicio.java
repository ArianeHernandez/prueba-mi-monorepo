package com.osmosyscol.datasuite.logica.servicios;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;


public class ParametroInicioServicio {

	private static ParametroInicioServicio instance;

	private ParametroInicioServicio() {
	}

	public static ParametroInicioServicio getInstance() {
		if (instance == null) {
			instance = new ParametroInicioServicio();
		}
		return instance;
	}
	
	public boolean validarSaldo(){
		String validarPropiedad = ParametrosInicio.getProperty("retiros.validarSaldoLiberacion");
		if( validarPropiedad == null || validarPropiedad.equals("false") ) {
			return false;
		}
		return true;
	}
	

}
