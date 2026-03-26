package com.osmosyscol.datapi.csv;

public class OperadorIgual extends Operador {

	public OperadorIgual() {
		setSiguiente(new OperadorMenor());
	}

	
	public Boolean comparar(Filtro filtro, String valor) {
		if (filtro.getOperador().equals(Filtro.OPERADOR_IGUAL)) {

			if (filtro.getValor().equals(valor)) {
				return true;
			}
			return false;

		}
		return super.comparar(filtro, valor);
	}

}
