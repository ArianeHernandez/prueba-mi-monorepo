package com.osmosyscol.datasuite.mein.servicios.json;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.mein.servicios.GeneradorFormularioServicio;

public class GeneradorFormularioJsonServicio implements JsonService  {

	private Session session;
	
	@Override
	public void setSession(Session session) {
		this.session = session;
	}
	
	public void generarFormulario (Integer id_carga) {
		GeneradorFormularioServicio.getInstance().generarFormulario(id_carga);
	}

}
