package com.osmosyscol.datasuite.mein.dtos;

public class RepartoIntendencias {
	private Integer id;
	private String categoria;
	private Integer codigo_dane_departamento;
	private Integer intendencia_asignada;
	private String dependencia;
	private String codigo_dependencia;
	private Integer apvista_dependencias;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public Integer getCodigo_dane_departamento() {
		return codigo_dane_departamento;
	}
	public void setCodigo_dane_departamento(Integer codigo_dane_departamento) {
		this.codigo_dane_departamento = codigo_dane_departamento;
	}
	public Integer getIntendencia_asignada() {
		return intendencia_asignada;
	}
	public void setIntendencia_asignada(Integer intendencia_asignada) {
		this.intendencia_asignada = intendencia_asignada;
	}
	public String getDependencia() {
		return dependencia;
	}
	public void setDependencia(String dependencia) {
		this.dependencia = dependencia;
	}
	public String getCodigo_dependencia() {
		return codigo_dependencia;
	}
	public void setCodigo_dependencia(String codigo_dependencia) {
		this.codigo_dependencia = codigo_dependencia;
	}
	public Integer getApvista_dependencias() {
		return apvista_dependencias;
	}
	public void setApvista_dependencias(Integer apvista_dependencias) {
		this.apvista_dependencias = apvista_dependencias;
	}
}
