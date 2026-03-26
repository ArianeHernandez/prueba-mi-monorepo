package com.osmosyscol.commons.validacion;

import java.util.HashMap;
import java.util.Map;

import com.osmosyscol.commons.servicio.AutenticacionServicio;

public class ResultadoValidacion {

	private boolean permitido = true;
	private String siguientePagina = AutenticacionServicio.PAGINA_INICIO;
	private String mensaje = null;
	private Map<String, String> mensajeCampo = new HashMap<String, String>();

	// -----------

	public boolean isPermitido() {
		return permitido;
	}

	public void setPermitido(boolean permitido) {
		this.permitido = permitido;
	}

	public String getSiguientePagina() {
		return siguientePagina;
	}

	public void setSiguientePagina(String siguientePagina) {
		this.siguientePagina = siguientePagina;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Map<String, String> getMensajeCampo() {
		return mensajeCampo;
	}

	public void setMensajeCampo(Map<String, String> mensajeCampo) {
		this.mensajeCampo = mensajeCampo;
	}

	public String toString() {
		return (this.permitido) ? "Permitido" : "No permitido [" + siguientePagina + "]";
	}
}
