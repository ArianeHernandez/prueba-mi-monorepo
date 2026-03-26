package com.osmosyscol.datasuite.modelatos.logica.dto;

import java.util.List;

public class GrupoFormato {

	private Integer id_grupoformato;
	private String nombre;
	private String descripcion;
	private Integer orden;
	
	
	
	//informacion calculada
	private List<Formato> formatosAsociados;

	public Integer getId_grupoformato() {
		return id_grupoformato;
	}

	public void setId_grupoformato(Integer id_grupoformato) {
		this.id_grupoformato = id_grupoformato;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Formato> getFormatosAsociados() {
		return formatosAsociados;
	}

	public void setFormatosAsociados(List<Formato> formatosAsociados) {
		this.formatosAsociados = formatosAsociados;
	}
	
	public Integer getOrden() {
		return orden;
	}	
	
	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
