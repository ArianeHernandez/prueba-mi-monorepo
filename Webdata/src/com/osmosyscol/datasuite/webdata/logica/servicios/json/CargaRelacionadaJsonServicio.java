package com.osmosyscol.datasuite.webdata.logica.servicios.json;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.servicio.json.JsonService;

public class CargaRelacionadaJsonServicio implements JsonService {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}
	
	public Boolean guardarCargaRelacionada(Integer id_carga){
		
		try {
			session.setAttribute("id_carga_relacionada", id_carga);
			return true;
			
		} catch (Exception e) {
			return false;
		}
		
	}

}
