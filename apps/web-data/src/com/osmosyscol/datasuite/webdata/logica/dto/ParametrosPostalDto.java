package com.osmosyscol.datasuite.webdata.logica.dto;


public class ParametrosPostalDto {
	
	private Integer id;
	private String nombre;
	private String valor;
	private String proceso; 
	private String tipodato;
	private String descripcion;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getProceso() {
		return proceso;
	}
	public void setProceso(String proceso) {
		this.proceso = proceso;
	}
	public String getTipoDato() {
		return tipodato;
	}
	public void setTipoDato(String tipodato) {
		this.tipodato = tipodato;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

}
