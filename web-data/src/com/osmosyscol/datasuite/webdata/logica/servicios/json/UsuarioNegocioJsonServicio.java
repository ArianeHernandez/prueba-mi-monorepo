package com.osmosyscol.datasuite.webdata.logica.servicios.json;


import com.osmosyscol.datasuite.logica.servicios.PersonaServicio;
import com.osmosyscol.datasuite.logica.servicios.UsuarioServicio;


public class UsuarioNegocioJsonServicio {

	public static Boolean activarUsuario(Integer id_usuario, String activar){
		return UsuarioServicio.getInstance().activarUsuario(id_usuario, activar);
	}
	
	public Boolean estaUsuarioBloqueado(String login, String password){
		return PersonaServicio.getInstance().estaUsuarioBloqueado(login, password);
	}
	
}
