package com.osmosyscol.datasuite.logica.servicios.json;

import java.util.List;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.logica.dto.Atributo;
import com.osmosyscol.datasuite.logica.servicios.AtributoServicio;

public class AtributoJsonServicio22 implements JsonService{

	private static Session session;

	@SuppressWarnings("static-access")
	public void setSession(Session session) {
		this.session = session;

	}
	
	public static Integer crearAtributo(Atributo atributo) {
		atributo.setId_usuario((Integer) session.getAttribute("id_usuario"));
		return AtributoServicio.getInstance().crearAtributo(atributo);
	}
	
	public static Boolean eliminarAtributo(Integer id_atributo) {
		return AtributoServicio.getInstance().eliminarAtributo(id_atributo);
	}
	
	public static List<Atributo> obtenerAtributosUsuario(Integer id_usario) {
		return AtributoServicio.getInstance().obtenerAtributosUsuario(id_usario);
	}
	
	
	
}
