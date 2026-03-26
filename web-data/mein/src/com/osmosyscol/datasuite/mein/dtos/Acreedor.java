package com.osmosyscol.datasuite.mein.dtos;

public class Acreedor {
	
	private Integer id;
	private Integer idcarga;
	private Integer	categoria;
	private	PersonaMein	datos_basicos;
	private	Credito	acreencia;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdcarga() {
		return idcarga;
	}
	public void setIdcarga(Integer idcarga) {
		this.idcarga = idcarga;
	}
	public Integer getCategoria() {
		return categoria;
	}
	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}
	public PersonaMein getDatos_basicos() {
		return datos_basicos;
	}
	public void setDatos_basicos(PersonaMein datos_basicos) {
		this.datos_basicos = datos_basicos;
	}
	public Credito getAcreencia() {
		return acreencia;
	}
	public void setAcreencia(Credito acreencia) {
		this.acreencia = acreencia;
	}
	
}
