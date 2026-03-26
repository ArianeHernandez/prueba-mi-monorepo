package com.osmosyscol.datasuite.mein.dtos;

public class WSData {

	private Integer id_carga;
	private String request;
	private String response;
	private String integracion;
	private String sistema;
	
	public Integer getId_carga() {
		return id_carga;
	}
	public void setId_carga(Integer id_carga) {
		this.id_carga = id_carga;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getIntegracion() {
		return integracion;
	}
	public void setIntegracion(String integracion) {
		this.integracion = integracion;
	}
	public String getSistema() {
		return sistema;
	}
	public void setSistema(String sistema) {
		this.sistema = sistema;
	}
	@Override
	public String toString() {
		return "WSData [id_carga=" + id_carga + ", request=" + request
				+ ", response=" + response + ", integracion=" + integracion
				+ ", sistema=" + sistema + "]";
	}
	
}
