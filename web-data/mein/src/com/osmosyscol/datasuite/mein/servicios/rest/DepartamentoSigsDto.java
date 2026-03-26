package com.osmosyscol.datasuite.mein.servicios.rest;

public class DepartamentoSigsDto {
	private Integer IdDepartamento;
	private Integer IdPais;
	private String Nombre;
	private String Pais;
	public DepartamentoSigsDto(Integer idDepartamento, Integer idPais,
			String nombre, String pais) {
		super();
		IdDepartamento = idDepartamento;
		IdPais = idPais;
		Nombre = nombre;
		Pais = pais;
	}
	public DepartamentoSigsDto() {
		super();
	}
	public Integer getIdDepartamento() {
		return IdDepartamento;
	}
	public void setIdDepartamento(Integer idDepartamento) {
		IdDepartamento = idDepartamento;
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
	public String getPais() {
		return Pais;
	}
	public void setPais(String pais) {
		Pais = pais;
	}
}
