package com.osmosyscol.datasuite.logica.dto;

import java.util.List;

public class Liberador {
	private Integer id_liberador;
	private Integer id_usuario;
	private Integer id_persona;
	private String activo;

	// ---

	private List<LiberadorTipoProceso> listadoLiberadorTipoProceso;

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

	public Integer getId_liberador() {
		return id_liberador;
	}

	public void setId_liberador(Integer id_liberador) {
		this.id_liberador = id_liberador;
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

	public List<LiberadorTipoProceso> getListadoLiberadorTipoProceso() {
		return listadoLiberadorTipoProceso;
	}

	public void setListadoLiberadorTipoProceso(List<LiberadorTipoProceso> listadoLiberadorTipoProceso) {
		this.listadoLiberadorTipoProceso = listadoLiberadorTipoProceso;
	}

}
