package com.osmosyscol.datasuite.logica.dto;

import java.util.List;

public class ServicioContenido {
	
	private List<Contenido> contenidos;
	private String nombre_servicio;
	
	public String getNombre_servicio() {
		return nombre_servicio;
	}
	public void setNombre_servicio(String nombre_servicio) {
		this.nombre_servicio = nombre_servicio;
	}
	
	public List<Contenido> getContenidos() {
		return contenidos;
	}
	public void setContenidos(List<Contenido> contenidos) {
		this.contenidos = contenidos;
	}
}
