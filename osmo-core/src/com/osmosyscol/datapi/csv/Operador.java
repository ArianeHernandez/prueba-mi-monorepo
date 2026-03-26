package com.osmosyscol.datapi.csv;

public abstract class Operador {

	private Operador siguiente;

	public Boolean comparar(Filtro filtro, String valor) {
		if (siguiente != null) {
			return siguiente.comparar(filtro, valor);
		}
		return false;
	}

	public void setSiguiente(Operador operador) {
		siguiente = operador;
	}

}
