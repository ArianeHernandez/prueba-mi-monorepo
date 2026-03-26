package com.osmosyscol.datasuite.mein.dtos;

public class MunicipioDane {
	
	private Integer id;
	private Integer codigo_ciudad;
	private String nombre_ciudad;
	private DepartamentoDane departamento;
	private Integer codigoMunicipioPostal;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCodigo_ciudad() {
		return codigo_ciudad;
	}
	public void setCodigo_ciudad(Integer codigo_ciudad) {
		this.codigo_ciudad = codigo_ciudad;
	}
	public String getNombre_ciudad() {
		return nombre_ciudad;
	}
	public void setNombre_ciudad(String nombre_ciudad) {
		this.nombre_ciudad = nombre_ciudad;
	}
	public DepartamentoDane getDepartamento() {
		return departamento;
	}
	public void setDepartamento(DepartamentoDane departamento) {
		this.departamento = departamento;
	}
	public Integer getCodigoMunicipioPostal() {
		return codigoMunicipioPostal;
	}
	public void setCodigoMunicipioPostal(Integer codigoMunicipioPostal) {
		this.codigoMunicipioPostal = codigoMunicipioPostal;
	}
	
}
