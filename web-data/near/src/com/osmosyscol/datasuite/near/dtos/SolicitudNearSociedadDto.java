package com.osmosyscol.datasuite.near.dtos;

public class SolicitudNearSociedadDto {
	Integer idcarga;
	public SolicitudNearSociedadDto(Integer idcarga, String token) {
		super();
		this.idcarga = idcarga;
		this.token = token;
	}
	String token;
	public Integer getIdcarga() {
		return idcarga;
	}
	public void setIdcarga(Integer idcarga) {
		this.idcarga = idcarga;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
