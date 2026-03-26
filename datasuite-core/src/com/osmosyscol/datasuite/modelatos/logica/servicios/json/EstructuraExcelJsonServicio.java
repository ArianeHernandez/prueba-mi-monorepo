package com.osmosyscol.datasuite.modelatos.logica.servicios.json;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.commons.utils.Mensaje;
import com.osmosyscol.datasuite.modelatos.logica.servicios.EstructuraExcelServicio;

public class EstructuraExcelJsonServicio implements JsonService {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public Mensaje obtenerMensajeCarga(){
		Mensaje mensaje = (Mensaje) session.getAttribute(EstructuraExcelServicio.ATRIBUTO_MENSAJE);
		if (mensaje!= null && "F".equals(mensaje.getEstado())) {
			session.removeAttribute(EstructuraExcelServicio.ATRIBUTO_MENSAJE);
		}
		return mensaje;
	}
}
