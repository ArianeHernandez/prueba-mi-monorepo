package com.osmosyscol.osmoautenticador.autenticador;

import java.util.HashMap;

public class RespuestaAutenticador {

	private boolean autenticado;
	private HashMap<String, Object> datos;

	public RespuestaAutenticador() {
		autenticado = false;
		datos = new HashMap<String, Object>();
	}

	public boolean isAutenticado() {
		return autenticado;
	}

	public void setAutenticado(boolean autenticado) {
		this.autenticado = autenticado;
	}

	public HashMap<String, Object> getDatos() {
		return datos;
	}

	public void setDatos(HashMap<String, Object> datos) {
		this.datos = datos;
	}

}
