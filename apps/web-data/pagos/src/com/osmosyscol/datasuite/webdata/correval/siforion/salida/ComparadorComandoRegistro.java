package com.osmosyscol.datasuite.webdata.correval.siforion.salida;

import java.util.Comparator;

public class ComparadorComandoRegistro implements Comparator<ComandoRegistro>{
	

	public int compare(ComandoRegistro o1, ComandoRegistro o2) {
		return o1.getOrden().compareTo(o2.getOrden());
	}

}
