// CVS $Id$

package com.osmosyscol.osmoautenticador.servicioweb.guardarusuario;


public class RespuestaGuardarUsuario {

	private Boolean exitoso;

	private String mensaje;

	// ---------------------------------

	public Boolean getExitoso() {
		return exitoso;
	}

	public void setExitoso(Boolean exitoso) {
		this.exitoso = exitoso;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
