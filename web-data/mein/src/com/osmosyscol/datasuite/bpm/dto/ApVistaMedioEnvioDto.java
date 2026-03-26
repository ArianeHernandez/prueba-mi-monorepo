package com.osmosyscol.datasuite.bpm.dto;

public class ApVistaMedioEnvioDto {
	private Integer id;
	private String medio_envio;
	private String codigo;
	
	
	public String getMedio_envio() {
		return medio_envio;
	}
	public void setMedio_envio(String medio_envio) {
		this.medio_envio = medio_envio;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}
