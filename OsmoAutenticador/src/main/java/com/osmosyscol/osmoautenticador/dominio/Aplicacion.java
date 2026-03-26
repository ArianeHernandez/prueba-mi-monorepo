package com.osmosyscol.osmoautenticador.dominio;

import java.util.List;

public class Aplicacion {

	private Integer id_aplicacion;
	private String nombre;
	private String descripcion;

	// ------------------

	private List<Rol> roles;

	// ------------------

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public Integer getId_aplicacion() {
		return id_aplicacion;
	}

	public void setId_aplicacion(Integer id_aplicacion) {
		this.id_aplicacion = id_aplicacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
