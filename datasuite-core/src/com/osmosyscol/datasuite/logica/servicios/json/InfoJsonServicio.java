package com.osmosyscol.datasuite.logica.servicios.json;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.logica.servicios.InfoServicio;

public class InfoJsonServicio  implements JsonService{
	
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}
	
	public String exeService(String className, String method, String[] params){
		return InfoServicio.getInstance().exeService(className, method, params);
	}

}
