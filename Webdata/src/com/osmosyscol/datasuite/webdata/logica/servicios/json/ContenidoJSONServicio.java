package com.osmosyscol.datasuite.webdata.logica.servicios.json;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.logica.dto.Contenido;
import com.osmosyscol.datasuite.logica.servicios.ContenidoServicio;

public class ContenidoJSONServicio implements JsonService {

	@SuppressWarnings("unused")
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}
	
	public String obtenerContenido(String url, String etiqueta){
		Contenido contenido = ContenidoServicio.getInstance().obtenerContenido(url, etiqueta);
		String textoContenido = contenido.getTexto();
		return textoContenido;
	}
	
}