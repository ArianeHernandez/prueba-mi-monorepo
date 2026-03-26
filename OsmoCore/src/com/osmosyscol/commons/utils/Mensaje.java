package com.osmosyscol.commons.utils;

public class Mensaje<T> {

	private String mensaje;
	private String estado;
	private Boolean exitoso;
	private T info;

	// --------------------------------

	public Mensaje() {
		exitoso = false;
	}

	public Mensaje(String estado, Boolean exitoso) {
		this.estado = estado;
		this.exitoso = exitoso;
	}

	public Mensaje(String estado, String mensaje, Boolean exitoso) {
		this.mensaje = mensaje;
		this.estado = estado;
		this.exitoso = exitoso;
	}

	public Mensaje(String estado, String mensaje, Boolean exitoso, T info) {
		this.mensaje = mensaje;
		this.estado = estado;
		this.exitoso = exitoso;
		this.info = info;
	}

	// --------------------------------

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Boolean getExitoso() {
		return exitoso;
	}

	public void setExitoso(Boolean exitoso) {
		this.exitoso = exitoso;
	}

	public T getInfo() {
		return info;
	}

	public void setInfo(T info) {
		this.info = info;
	}

}
