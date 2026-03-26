package com.osmosyscol.datasuite.webdata.logica.servicios;

import com.osmosyscol.commons.log.SimpleLogger;

public class EstadoConexion {

	private boolean error = false;
	private String tituloError;
	private String template;

	public EstadoConexion() {
	}

	public EstadoConexion(String tituloError, String template) {
		error = true;
		this.template = template;
		this.tituloError = tituloError;

		SimpleLogger.setError(tituloError);
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getTituloError() {
		return tituloError;
	}

	public void setTituloError(String tituloError) {
		this.tituloError = tituloError;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

}
