package com.osmosyscol.osmoautenticador.autenticador;

import java.util.HashMap;

public class EntradaAutenticador {

	private HashMap<String, Object> datos;

	public EntradaAutenticador() {
		datos = new HashMap<String, Object>();
	}

	public HashMap<String, Object> getDatos() {
		return datos;
	}

	public void setDatos(HashMap<String, Object> datos) {
		this.datos = datos;
	}

}
