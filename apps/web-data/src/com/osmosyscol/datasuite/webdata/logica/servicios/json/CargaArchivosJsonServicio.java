package com.osmosyscol.datasuite.webdata.logica.servicios.json;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.servicio.json.JsonService;

public class CargaArchivosJsonServicio implements JsonService {

	private Integer id_usuario;

	private Integer id_administrativo;

	private Integer id_persona;

	private Session session;

	public void setSession(Session session) {

		this.session = session;

		Object text = session.getAttribute("id_usuario");
		if (text != null) {
			id_usuario = new Integer(text.toString());
			id_persona = (Integer) session.getAttribute("id_persona");
		}

		text = session.getAttribute("id_administrativo");
		if (text != null) {
			id_administrativo = new Integer(text.toString());
		}
	}

	public EstadoCargaArchivo consultarEstadoCargaArchivo(Integer idArchivo) {
		return AdminEstadoCargue.getInstance().getEstadoCargaArchivo(idArchivo);
	}	

	public Boolean consultarFinCargaArchivo(Integer idArchivo) {
		return AdminEstadoCargue.getInstance().getFinCarga(idArchivo);
	}

}
