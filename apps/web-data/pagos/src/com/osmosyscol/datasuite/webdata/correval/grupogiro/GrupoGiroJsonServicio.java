package com.osmosyscol.datasuite.webdata.correval.grupogiro;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.logica.dto.Mensaje;

public class GrupoGiroJsonServicio implements JsonService{

	private Session session;

	public void setSession(Session session) {
		this.session = session;
		
	}
	
	public GrupoGiroEstado obtenerEstadoGrupoGiro(){
		return (GrupoGiroEstado)session.getAttribute("estado_grupo_giro");
	}

	
	public Mensaje obtenerMensajeCreacionArchivo(){
		return GrupoGiroServicio.getInstance().obtenerMensajeCreacionArchivo(session);
	}
}
