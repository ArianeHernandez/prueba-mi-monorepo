package com.osmosyscol.datasuite.logica.dto.excel;

import java.util.ArrayList;
import java.util.List;

public class Excel {

	private List<HojaExcel> hojas = new ArrayList<HojaExcel>();
	private String nombre;

	public List<HojaExcel> getHojas() {
		return hojas;
	}

	public void setHojas(List<HojaExcel> hojas) {
		this.hojas = hojas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
