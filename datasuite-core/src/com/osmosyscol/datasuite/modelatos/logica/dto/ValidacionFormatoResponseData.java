package com.osmosyscol.datasuite.modelatos.logica.dto;

public class ValidacionFormatoResponseData {

	private Boolean valido;
	private Boolean mostrar_mensaje;
	private String mensaje_validacion;
	private Boolean bloquear_formulario;

	public Boolean getValido() {
		return valido;
	}

	public void setValido(Boolean valido) {
		this.valido = valido;
	}

	public String getMensaje_validacion() {
		return mensaje_validacion;
	}

	public void setMensaje_validacion(String mensaje_validacion) {
		this.mensaje_validacion = mensaje_validacion;
	}

	public Boolean getBloquear_formulario() {
		return bloquear_formulario;
	}

	public void setBloquear_formulario(Boolean bloquear_formulario) {
		this.bloquear_formulario = bloquear_formulario;
	}

	public Boolean getMostrar_mensaje() {
		return mostrar_mensaje;
	}

	public void setMostrar_mensaje(Boolean mostrar_mensaje) {
		this.mostrar_mensaje = mostrar_mensaje;
	}
	
}
