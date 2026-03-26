package com.osmosyscol.datasuite.mein.servicios.rest;

public class CiudadSigsDto {
	private String Departamento;
	private Integer IdCiudad;
	private Integer IdDepartamento;
	private Integer IdPais;
	private String Nombre;
	private String Pais;
	
	public CiudadSigsDto(String departamento, Integer idCiudad,
			Integer idDepartamento, Integer idPais, String nombre, String pais) {
		super();
		Departamento = departamento;
		IdCiudad = idCiudad;
		IdDepartamento = idDepartamento;
		IdPais = idPais;
		Nombre = nombre;
		Pais = pais;
	}

	public CiudadSigsDto() {
		super();
	}

	public String getDepartamento() {
		return Departamento;
	}

	public void setDepartamento(String departamento) {
		Departamento = departamento;
	}

	public Integer getIdCiudad() {
		return IdCiudad;
	}

	public void setIdCiudad(Integer idCiudad) {
		IdCiudad = idCiudad;
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
