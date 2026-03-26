package com.osmosyscol.datasuite.logica.dto;

public interface AccionPoliticaSeguridad {
	
	public Boolean validarClave(String login, String clave, String valor);
	
	public Boolean validarIngreso(String login, String clave, String valor);
	
}
