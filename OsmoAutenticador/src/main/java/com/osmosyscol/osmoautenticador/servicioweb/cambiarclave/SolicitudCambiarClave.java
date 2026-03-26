// CVS $Id$

package com.osmosyscol.osmoautenticador.servicioweb.cambiarclave;

public class SolicitudCambiarClave {

	private String login;
	private String clave;
	private String clave_nueva;
	private String aplicacion;
	private String tipoclave;

	// --------------------------------------------

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

	public String getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}

	public String getClave_nueva() {
		return clave_nueva;
	}

	public void setClave_nueva(String clave_nueva) {
		this.clave_nueva = clave_nueva;
	}

	public String getTipoclave() {
		return tipoclave;
	}

	public void setTipoclave(String tipoclave) {
		this.tipoclave = tipoclave;
	}

}
