package com.osmosyscol.datasuite.logica.servicios;

import java.util.Comparator;

import com.osmosyscol.datasuite.reportedim.dto.Resultado;

public class ComparadorResultado implements Comparator<Resultado> {

	public int compare(Resultado o1, Resultado o2) {
		return o1.getOrden().compareTo(o2.getOrden());
	}

}
