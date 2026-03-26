package com.osmosyscol.datasuite.webdata.logica.servicios.json;

import com.osmosyscol.datasuite.logica.dto.Credencial;
import com.osmosyscol.datasuite.logica.servicios.CredencialServicio;
import com.osmosyscol.datasuite.logica.servicios.CuentaServicio;
import com.osmosyscol.datasuite.logica.servicios.PersonaServicio;


public class CuentaJSONServicio {

	public Boolean aceptaTerminosCondiciones(String login) {
		Credencial credencial = PersonaServicio.getInstance().obtenerCredencialPorLogin(login);
		
		Boolean actualizacionExitosa = false;
		
		//Se actualiza la informacion de la credencial
		if(credencial!=null){
			
			actualizacionExitosa = CredencialServicio.getInstance().aceptaTerminosCondiciones(credencial);
		}
		
		return actualizacionExitosa;
	}
	
	public Boolean validarUsoToken(String login) {
		return CuentaServicio.getInstance().validarUsoToken(login);
	}
	
}
