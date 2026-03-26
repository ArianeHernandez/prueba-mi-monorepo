package com.osmosyscol.datasuite.logica.dto;

import com.osmosyscol.datasuite.logica.constantes.Constantes;

public class Administrador {

	private Integer id_administrador;
	private Integer id_persona;
	private String activo = Constantes.SI;

	private Persona persona;

	// ----------------

	private String login;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Integer getId_administrador() {
		return id_administrador;
	}

	public void setId_administrador(Integer id_administrador) {
		this.id_administrador = id_administrador;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public void setId_persona(Integer id_persona) {
		this.id_persona = id_persona;
	}

	public Integer getId_persona() {
		return id_persona;
	}

}
