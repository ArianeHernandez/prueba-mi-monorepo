package com.osmosyscol.datasuite.logica.dto;

public class Rol {

	private Integer id_rol;
	private Integer id_rol_padre;
	private Integer id_usuario;
	private String nombre_rol;
	private String descripcion;
	private String activo = "N";
	private String nombre_rol_padre;

	public String getNombre_rol_padre() {
		return nombre_rol_padre;
	}

	public void setNombre_rol_padre(String nombre_rol_padre) {
		this.nombre_rol_padre = nombre_rol_padre;
	}

	public Rol() {

	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public Integer getId_rol() {
		return id_rol;
	}

	public void setId_rol(Integer id_rol) {
		this.id_rol = id_rol;
	}

	public Integer getId_rol_padre() {
		return id_rol_padre;
	}

	public void setId_rol_padre(Integer id_rol_padre) {
		this.id_rol_padre = id_rol_padre;
	}

	public String getNombre_rol() {
		return nombre_rol;
	}

	public void setNombre_rol(String nombre_rol) {
		this.nombre_rol = nombre_rol;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}

}
