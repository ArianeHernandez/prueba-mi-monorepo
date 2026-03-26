package com.osmosyscol.osmoautenticador.dominio;

public class TipoAutenticacion {

	private Integer id_tipo_autenticacion;
	private String descripcion;
	
	// ----
	
	public Integer getId_tipo_autenticacion() {
		return id_tipo_autenticacion;
	}
	public void setId_tipo_autenticacion(Integer id_tipo_autenticacion) {
		this.id_tipo_autenticacion = id_tipo_autenticacion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
}
