package com.osmosyscol.datasuite.bpm.dto;

public class BPMResponse {

	private Long Elemento;
	private Boolean Estado;
	private String Mensaje;
	private Integer TotalRegistros;
	
	public Long getElemento() {
		return Elemento;
	}
	public void setElemento(Long elemento) {
		Elemento = elemento;
	}
	public Boolean getEstado() {
		return Estado;
	}
	public void setEstado(Boolean estado) {
		Estado = estado;
	}
	public String getMensaje() {
		return Mensaje;
	}
	public void setMensaje(String mensaje) {
		Mensaje = mensaje;
	}
	public Integer getTotalRegistros() {
		return TotalRegistros;
	}
	public void setTotalRegistros(Integer totalRegistros) {
		TotalRegistros = totalRegistros;
	}
	
}
