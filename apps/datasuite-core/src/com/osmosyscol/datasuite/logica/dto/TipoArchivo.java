package com.osmosyscol.datasuite.logica.dto;

public class TipoArchivo {
	private Integer id_tipo_archivo;
	private String nombre;
	private String activo;
	
	public Integer getId_tipo_archivo() {
		return id_tipo_archivo;
	}
	public void setId_tipo_archivo(Integer id_tipo_archivo) {
		this.id_tipo_archivo = id_tipo_archivo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getActivo() {
		return activo;
	}
	public void setActivo(String activo) {
		this.activo = activo;
	}
	@Override
	public String toString() {
		return "TipoArchivo [id_tipo_archivo=" + id_tipo_archivo + ", nombre="
				+ nombre + ", activo=" + activo + "]";
	}

}
