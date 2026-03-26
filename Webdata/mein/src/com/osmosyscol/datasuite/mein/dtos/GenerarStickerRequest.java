package com.osmosyscol.datasuite.mein.dtos;

public class GenerarStickerRequest {
	private String numRadicado;
	private Integer formatoRequerido;
	private String numeroProceso;
	
	public String getNumRadicado() {
		return numRadicado;
	}
	public void setNumRadicado(String numRadicado) {
		this.numRadicado = numRadicado;
	}
	public Integer getFormatoRequerido() {
		return formatoRequerido;
	}
	public void setFormatoRequerido(Integer formatoRequerido) {
		this.formatoRequerido = formatoRequerido;
	}
	public String getNumeroProceso() {
		return numeroProceso;
	}
	public void setNumeroProceso(String numeroProceso) {
		this.numeroProceso = numeroProceso;
	}
}
