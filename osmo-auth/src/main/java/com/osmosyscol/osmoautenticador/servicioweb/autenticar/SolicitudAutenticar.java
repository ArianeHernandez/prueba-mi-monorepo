// CVS $Id$

package com.osmosyscol.osmoautenticador.servicioweb.autenticar;

public class SolicitudAutenticar {

	private String login;
	private String clave;
	private String tipoclave;
	private String aplicacion;
	private String configuracion;

	// --------------------------------------------

	public String getLogin() {
		return login;
	}

	public String getConfiguracion() {
		return configuracion;
	}

	public void setConfiguracion(String configuracion) {
		this.configuracion = configuracion;
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

	public String getTipoclave() {
		return tipoclave;
	}

	public void setTipoclave(String tipoclave) {
		this.tipoclave = tipoclave;
	}

	public String getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}

}
