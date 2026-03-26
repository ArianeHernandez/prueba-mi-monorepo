package com.osmosyscol.datasuite.modelatos.logica.dto;

public class Variable {

	private Integer id_variable;
	private String nombre_variable;
	private String valor_variable;
	private String visibilidad;
	private String editable;
	private String descripcion;
	private Integer id_modelo;

	public Variable() {
	}

	public Variable(Integer id_variable, String nombre_variable, String descripcion, Integer id_negocio, String valor_variable, String editable, String visibilidad) {
		super();
		this.descripcion = descripcion;
		this.editable = editable;
		this.id_modelo = id_negocio;
		this.id_variable = id_variable;
		this.nombre_variable = nombre_variable;
		this.valor_variable = valor_variable;
		this.visibilidad = visibilidad;
	}

	// ------------------------

	public Integer getId_modelo() {
		return id_modelo;
	}

	public void setId_modelo(Integer id_negocio) {
		this.id_modelo = id_negocio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getId_variable() {
		return id_variable;
	}

	public void setId_variable(Integer id_variable) {
		this.id_variable = id_variable;
	}

	public String getNombre_variable() {
		return nombre_variable;
	}

	public void setNombre_variable(String nombre_variable) {
		this.nombre_variable = nombre_variable;
	}

	public String getValor_variable() {
		return valor_variable;
	}

	public void setValor_variable(String valor_variable) {
		this.valor_variable = valor_variable;
	}

	public String getVisibilidad() {
		return visibilidad;
	}

	public void setVisibilidad(String visibilidad) {
		this.visibilidad = visibilidad;
	}

	public String getEditable() {
		return editable;
	}

	public void setEditable(String editable) {
		this.editable = editable;
	}

}
