package com.osmosyscol.datasuite.logica.dto;

public class OperacionInterna {

	private Integer id_operacion_interna;
	private String nombre;
	private String sincrona;
	private String clase;

	public Integer getId_operacion_interna() {
		return id_operacion_interna;
	}

	public void setId_operacion_interna(Integer id_operacion_interna) {
		this.id_operacion_interna = id_operacion_interna;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSincrona() {
		return sincrona;
	}

	public void setSincrona(String sincrona) {
		this.sincrona = sincrona;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

}
