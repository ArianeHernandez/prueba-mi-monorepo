// CVS $Id$

package com.osmosyscol.osmoautenticador.servicioweb.autenticar;

public class SolicitudAutorizar {

	private String login;
	private String url;
	private String aplicacion;

	// --------------------------------------------

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}

}
