package com.osmosyscol.datasuite.webdata.correval.grupogiro;

import java.util.Comparator;

public class ComparadorComando implements Comparator<Comando>{
	

	public int compare(Comando o1, Comando o2) {
		return o1.getOrden().compareTo(o2.getOrden());
	}

}
