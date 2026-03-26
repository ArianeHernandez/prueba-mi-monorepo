package com.osmosyscol.datasuite.logica.dto.excel;

import java.util.ArrayList;
import java.util.List;

public class Fila {

	private List<Celda> columnas = new ArrayList<Celda>();
	private Integer numero;

	public List<Celda> getColumnas() {
		return columnas;
	}

	public void setColumnas(List<Celda> columnas) {
		this.columnas = columnas;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

}
