package com.osmosyscol.osmoautenticador.dominio;

public class Usuario {

	private int id_usuario;
	private String login;
	private String clave;
	private String clave2;
	
	// ----

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id) {
		this.id_usuario = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getClave2() {
		return clave2;
	}

	public void setClave2(String clave2) {
		this.clave2 = clave2;
	}
}
