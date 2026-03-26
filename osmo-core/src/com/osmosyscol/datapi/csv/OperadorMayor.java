package com.osmosyscol.datapi.csv;

import org.apache.commons.lang.math.NumberUtils;


public class OperadorMayor extends Operador {

	public OperadorMayor() {
		setSiguiente(new OperadorMenorIgual());
	}

	
	public Boolean comparar(Filtro filtro, String valor) {
		if (filtro.getOperador().equals(Filtro.OPERADOR_MAYOR) ){

			if (NumberUtils.isNumber(valor) && NumberUtils.isNumber(filtro.getValor())) {
				Number numero1 = NumberUtils.createNumber(valor);
				Number numero2 = NumberUtils.createNumber(filtro.getValor());
				if ( numero1.doubleValue() > numero2.doubleValue()) {
					return true;
				}
			}
			return false;

		}
		return super.comparar(filtro, valor);
	}

}
