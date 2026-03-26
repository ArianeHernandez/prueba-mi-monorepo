package com.osmosyscol.osmoautenticador.persistencia.dto;

public class RolDto {

	private Integer idRol;
	private Integer idAplicacion;
	private String nombre;
	private String naturaleza;
	public RolDto() {
	}
	
	public Integer getIdRol() {
		return idRol;
	}
	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}
	public Integer getIdAplicacion() {
		return idAplicacion;
	}
	public void setIdAplicacion(Integer idAplicacion) {
		this.idAplicacion = idAplicacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setNaturaleza(String naturaleza) {
		this.naturaleza = naturaleza;
	}
	public String getNaturaleza() {
		return naturaleza;
	}
	
	
	
}
