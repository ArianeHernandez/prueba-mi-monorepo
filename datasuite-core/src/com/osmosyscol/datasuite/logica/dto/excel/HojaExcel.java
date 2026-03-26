package com.osmosyscol.datasuite.logica.dto.excel;

import java.util.ArrayList;
import java.util.List;

public class HojaExcel {

	private List<Fila> filas = new ArrayList<Fila>();
	private String nombre;

	public List<Fila> getFilas() {
		return filas;
	}

	public void setFilas(List<Fila> filas) {
		this.filas = filas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
