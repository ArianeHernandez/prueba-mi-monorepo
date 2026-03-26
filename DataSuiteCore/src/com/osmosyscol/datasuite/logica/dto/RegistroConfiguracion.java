package com.osmosyscol.datasuite.logica.dto;

public class RegistroConfiguracion {
	
	private String etiqueta;
	private String valor;
	private Integer id_configuracion;
	private String nombre_servicio;
	
	
	public RegistroConfiguracion() {

	}
	
	
	public String getNombre_servicio() {
		return nombre_servicio;
	}
	
	public void setNombre_servicio(String nombre_servicio) {
		this.nombre_servicio = nombre_servicio;
	}
	
	public String getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public Integer getId_configuracion() {
		return id_configuracion;
	}
	
	public void setId_configuracion(Integer id_configuracion) {
		this.id_configuracion = id_configuracion;
	}
	


}
