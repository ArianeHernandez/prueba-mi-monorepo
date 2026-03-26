package com.osmosyscol.datasuite.webdata.utils;

import java.util.Comparator;

import com.osmosyscol.datasuite.modelatos.logica.dto.Formato;

public class ComparadorFormatos implements Comparator<Formato> {

	public int compare(Formato o1, Formato o2) {
		if(o1 != null && o2 != null){
			return o1.getNombre().compareTo(o2.getNombre());
		}
		return 0;
	}

} 
