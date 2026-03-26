package com.osmosyscol.datasuite.mein.dtos;

public class DepartamentoDane {
	
	private Integer id;
	private Integer codigo_departamento;
	private String nombre_departamento;
	private PaisDane pais;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCodigo_departamento() {
		return codigo_departamento;
	}
	public void setCodigo_departamento(Integer codigo_departamento) {
		this.codigo_departamento = codigo_departamento;
	}
	public String getNombre_departamento() {
		return nombre_departamento;
	}
	public void setNombre_departamento(String nombre_departamento) {
		this.nombre_departamento = nombre_departamento;
	}
	public PaisDane getPais() {
		return pais;
	}
	public void setPais(PaisDane pais) {
		this.pais = pais;
	}
	
}
