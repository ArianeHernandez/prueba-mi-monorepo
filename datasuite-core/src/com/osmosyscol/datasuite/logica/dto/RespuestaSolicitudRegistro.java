package com.osmosyscol.datasuite.logica.dto;

public class RespuestaSolicitudRegistro {

	public RespuestaSolicitudRegistro() {
	}

	public RespuestaSolicitudRegistro(String pinSolicitud, Boolean existeOsmoautenticador) {
		this.pinSolicitud = pinSolicitud;
		this.existeCredencial = existeOsmoautenticador;
	}

	private String pinSolicitud;
	private Boolean existeCredencial;

	public String getPinSolicitud() {
		return pinSolicitud;
	}

	public void setPinSolicitud(String pinSolicitud) {
		this.pinSolicitud = pinSolicitud;
	}

	public Boolean getExisteCredencial() {
		return existeCredencial;
	}

	public void setExisteCredencial(Boolean existeOsmoautenticador) {
		this.existeCredencial = existeOsmoautenticador;
	}

}
