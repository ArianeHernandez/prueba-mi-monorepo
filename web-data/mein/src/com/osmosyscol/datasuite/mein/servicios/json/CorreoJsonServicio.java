package com.osmosyscol.datasuite.mein.servicios.json;

import org.apache.cocoon.environment.Session;

import co.htsoft.commons.util.SMessage;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.mein.servicios.CorreoServicio;
import com.osmosyscol.datasuite.mein.servicios.SignAppServicio;

public class CorreoJsonServicio implements JsonService  {
	
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}
	
	public SMessage enviarCorreoRevision(String id_carga) { 
		return CorreoServicio.getInstance().EnviarCorreoRevision(id_carga);
	}
	
	public SMessage enviarCorreoLiberacion(String id_carga) { 
		return CorreoServicio.getInstance().EnviarCorreoLiberacion(id_carga);
	}
	
}
