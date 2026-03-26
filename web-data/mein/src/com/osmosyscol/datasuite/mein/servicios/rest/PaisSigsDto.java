package com.osmosyscol.datasuite.mein.servicios.rest;

public class PaisSigsDto {
	private Integer IdPais;
	private String Nombre;
	public PaisSigsDto(Integer idPais, String nombre) {
		super();
		IdPais = idPais;
		Nombre = nombre;
	}
	public PaisSigsDto() {
		super();
	}
	public Integer getIdPais() {
		return IdPais;
	}
	public void setIdPais(Integer idPais) {
		IdPais = idPais;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
}
