package com.osmosyscol.datasuite.webdata.logica.servicios.json;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.logica.servicios.ConfiguracionServicio;

public class ConfiguracionJsonServicio implements JsonService {

	private Session session;
	
	@Override
	public void setSession(Session session) {
		 this.session = session;
	}

	public String obtenerValorByEtiqueta(String etiqueta) {
		return ConfiguracionServicio.getInstance().obtenerValorByEtiqueta(etiqueta);
	}
	
}
