package com.osmosyscol.osmoautenticador.persistencia.dto;

public class ServicioDto {

	private Integer idServicio;
	private Integer idRol;
	private String nombre;
	private String activo;
	
	public ServicioDto() {
		
	}

	public Integer getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(Integer idServicio) {
		this.idServicio = idServicio;
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

	public void setIdRol(Integer id_rol) {
		this.idRol = id_rol;
	}

	public Integer getIdRol() {
		return idRol;
	}
}
