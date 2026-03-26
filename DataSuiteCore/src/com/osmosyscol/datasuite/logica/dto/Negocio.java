package com.osmosyscol.datasuite.logica.dto;

public class Negocio {

	private Integer id_negocio;
	private Integer id_modelo;
	private String nombre;
	private String descripcion;
	private String cod_negocio;
	private String activo;

	// ---

	private Integer numero_administradores;

	// ---

	public Integer getId_negocio() {
		return id_negocio;
	}

	public void setId_negocio(Integer id_negocio) {
		this.id_negocio = id_negocio;
	}

	public Integer getId_modelo() {
		return id_modelo;
	}

	public void setId_modelo(Integer id_modelo) {
		this.id_modelo = id_modelo;
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

	public String getCod_negocio() {
		return cod_negocio;
	}

	public void setCod_negocio(String cod_negocio) {
		this.cod_negocio = cod_negocio;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public Integer getNumero_administradores() {
		return numero_administradores;
	}

	public void setNumero_administradores(Integer numeroAdministradores) {
		this.numero_administradores = numeroAdministradores;
	}

	@Override
	public String toString() {
		return "Negocio [id_negocio=" + id_negocio + ", id_modelo=" + id_modelo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", cod_negocio=" + cod_negocio
				+ ", activo=" + activo + ", numero_administradores=" + numero_administradores + "]";
	}

}
