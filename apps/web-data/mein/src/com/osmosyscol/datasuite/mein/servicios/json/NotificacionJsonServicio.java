package com.osmosyscol.datasuite.mein.servicios.json;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.mein.servicios.NotificacionServicio;

public class NotificacionJsonServicio  implements JsonService   {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}
	
	public Boolean notificarErrorCargueArchivos (Integer id_carga, String[] archivos) {
		return NotificacionServicio.getInstance().notificarErrorCargueArchivos(id_carga, archivos);
	}

}
