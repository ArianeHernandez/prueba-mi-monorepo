package com.osmosyscol.datasuite.mein.dtos;

import java.util.List;

public class DepartamentoCiudad {
	
	private Integer id;
	private String region;
	private Integer codigoDaneDepartamento;
	private String departamento;
	private Integer codigoDaneMunicipio;
	private String municipio;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public Integer getCodigoDaneDepartamento() {
		return codigoDaneDepartamento;
	}
	public void setCodigoDaneDepartamento(Integer codigoDaneDepartamento) {
		this.codigoDaneDepartamento = codigoDaneDepartamento;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public Integer getCodigoDaneMunicipio() {
		return codigoDaneMunicipio;
	}
	public void setCodigoDaneMunicipio(Integer codigoDaneMunicipio) {
		this.codigoDaneMunicipio = codigoDaneMunicipio;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}	
	
}
