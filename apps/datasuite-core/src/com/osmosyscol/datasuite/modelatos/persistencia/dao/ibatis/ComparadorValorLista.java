package com.osmosyscol.datasuite.modelatos.persistencia.dao.ibatis;

import java.util.Comparator;

import com.osmosyscol.datasuite.modelatos.logica.dto.ListaValores;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValorLista;

public class ComparadorValorLista implements Comparator<ValorLista> {
	
	private boolean porValor = true;
	private boolean ascendente = true;
	
	public ComparadorValorLista(ListaValores listaValores) {
		porValor = ListaValores.POR_VALOR.equals(listaValores.getCriterio_orden());
		ascendente = ListaValores.ASCENDENTE.equals(listaValores.getTipo_orden());
	}

	public int compare(ValorLista o1, ValorLista o2) {
		if (porValor) {
			if (ascendente) {
				return o1.getNombre().compareTo(o2.getNombre());				
			}
			else {
				return o2.getNombre().compareTo(o1.getNombre());
			}
		}
		else {
			if (ascendente) {
				return o1.getId().compareTo(o2.getId());				
			}
			else {
				return o2.getId().compareTo(o1.getId());
			}
		}
	}

}
