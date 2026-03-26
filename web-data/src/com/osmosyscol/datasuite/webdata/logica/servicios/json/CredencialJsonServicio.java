package com.osmosyscol.datasuite.webdata.logica.servicios.json;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.logica.dto.Credencial;
import com.osmosyscol.datasuite.logica.servicios.BloqueoCuentas;
import com.osmosyscol.datasuite.logica.servicios.CredencialServicio;


public class CredencialJsonServicio implements JsonService{
	private Session session;
	
	public void setSession(Session session) {
		this.session = session;
		
	}
	
	public Boolean aceptaTerminosCondiciones(){
		Credencial credencial = (Credencial)this.session.getAttribute("credencial");
		
		Boolean actualizacionExitosa = false;
		
		//Se actualiza la informacion de la credencial
		if(credencial!=null){
		
			actualizacionExitosa = CredencialServicio.getInstance().aceptaTerminosCondiciones(credencial);
			
			//Se actualiza la credencial que esta en session
			if(actualizacionExitosa){
				credencial.setAcepta_terminos("S");
			}
			
		}
		
		
		return actualizacionExitosa;
	}
	
	public Boolean activarCredencial(String login){
		return BloqueoCuentas.getInstance().activarCuenta(login);
	}
	
	
	
}
