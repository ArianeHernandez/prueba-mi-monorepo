package com.osmosyscol.datasuite.logica.dto;


public class Mensaje implements IPorcentaje {

	private String mensaje;
	private String estado;
	private Boolean exitoso;
	private Object info;
	private Integer porcentaje;

	// Porcentaje antes de iniciar la generación del archivo
	private Integer porcentajeInicial;

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

	public Mensaje(String estado, String mensaje, Boolean exitoso, Object info) {
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

	public Object getInfo() {
		return info;
	}

	public void setInfo(Object info) {
		this.info = info;
	}

	public void actualizarPorcentaje(Integer porcentaje) {
		if (porcentajeInicial == null) {
			porcentajeInicial = porcentaje;
		}
		Integer porcentajeDif = 100 - porcentajeInicial;

		this.porcentaje = porcentajeInicial + ((porcentajeInicial >= 100) ? 0 : (porcentaje / porcentajeDif));
	}

	public Integer getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(Integer porcentaje) {
		this.porcentaje = porcentaje;
	}

	public Integer getPorcentajeInicial() {
		return porcentajeInicial;
	}

	public void setPorcentajeInicial(Integer porcentajeInicial) {
		this.porcentajeInicial = porcentajeInicial;
	}

}
