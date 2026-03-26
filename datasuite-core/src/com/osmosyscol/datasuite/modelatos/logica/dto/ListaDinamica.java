package com.osmosyscol.datasuite.modelatos.logica.dto;

public class ListaDinamica {

	private Integer id_lista_dinamica;
	private String nombre;
	private String descripcion;
	private String c_id;
	private String c_nombre;
	private String consulta;
	private String estado;
	private Integer base_datos;
	private String servicio;

	// -----------

	public Integer getBase_datos() {
		return base_datos;
	}

	public void setBase_datos(Integer base_datos) {
		this.base_datos = base_datos;
	}

	public Integer getId_lista_dinamica() {
		return id_lista_dinamica;
	}

	public String getC_id() {
		return c_id;
	}

	public void setC_id(String c_id) {
		this.c_id = c_id;
	}

	public String getC_nombre() {
		return c_nombre;
	}

	public void setC_nombre(String c_nombre) {
		this.c_nombre = c_nombre;
	}

	public void setId_lista_dinamica(Integer id_lista_dinamica) {
		this.id_lista_dinamica = id_lista_dinamica;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getConsulta() {
		return consulta;
	}

	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEstado() {
		return estado;
	}

	public String getServicio() {
		return servicio;
	}
	
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
}
