package com.osmosyscol.datasuite.mein.servicios.json;

import org.apache.cocoon.environment.Session;

import co.htsoft.commons.util.SMessage;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.mein.servicios.SignAppServicio;

public class SignAppJsonServicio implements JsonService  {
	
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}
	
	public SMessage validarToken(String token) {
		Integer id_persona = (Integer) session.getAttribute("id_persona");
		return SignAppServicio.getInstance().validarToken(token, id_persona);
	}
	
	public SMessage validarTokenSinSesion(String identificacion, Integer tipo_identificacion, String token){
		return SignAppServicio.getInstance().validarTokenSinSesion(identificacion, tipo_identificacion, token);
	}
	
}
