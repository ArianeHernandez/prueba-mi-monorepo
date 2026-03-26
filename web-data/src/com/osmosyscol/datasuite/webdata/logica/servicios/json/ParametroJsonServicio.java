package com.osmosyscol.datasuite.webdata.logica.servicios.json;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.logica.servicios.ParametroInicioServicio;

public class ParametroJsonServicio implements JsonService{

	private static Session session;

	public void setSession(Session session) {
		this.session = session;
	}
	
	public static boolean validarSaldo() {
		return ParametroInicioServicio.getInstance().validarSaldo();
	}
	
}
