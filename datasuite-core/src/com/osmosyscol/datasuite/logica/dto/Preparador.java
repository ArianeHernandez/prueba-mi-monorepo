package com.osmosyscol.datasuite.logica.dto;

public class Preparador {
	private Integer id_preparador;
	private Integer id_usuario;
	private Integer id_persona;
	private String activo;

	// --

	private String nombre;
	private String login;

	// -------------------------

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Integer getId_preparador() {
		return id_preparador;
	}

	public void setId_preparador(Integer id_preparador) {
		this.id_preparador = id_preparador;
	}

	public Integer getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}

	public Integer getId_persona() {
		return id_persona;
	}

	public void setId_persona(Integer id_persona) {
		this.id_persona = id_persona;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public String getActivo() {
		return activo;
	}

}
