// CVS $Id$

package com.osmosyscol.osmoautenticador.servicioweb.guardarusuario;


public class SolicitudGuardarUsuario {

	private String login;
	private String clave;
	private Integer[] roles;
	
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
	public Integer[] getRoles() {
		return roles;
	}
	public void setRoles(Integer[] roles) {
		this.roles = roles;
	}


}
