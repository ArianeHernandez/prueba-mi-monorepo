package com.osmosyscol.datasuite.modelatos.logica.dto;

public class ExcepcionFormatoCliente {

	private Integer id_formato;
	private Integer id_usuario;
	private Integer id_negocio;

	private String tipo_excepcion;
	private String nombre_formato;

	public String getNombre_formato() {
		return nombre_formato;
	}

	public void setNombre_formato(String nombre_formato) {
		this.nombre_formato = nombre_formato;
	}

	public Integer getId_formato() {
		return id_formato;
	}

	public void setId_formato(Integer id_formato) {
		this.id_formato = id_formato;
	}

	public Integer getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getTipo_excepcion() {
		return tipo_excepcion;
	}

	public void setTipo_excepcion(String tipo_excepcion) {
		this.tipo_excepcion = tipo_excepcion;
	}

	public void setId_negocio(Integer id_negocio) {
		this.id_negocio = id_negocio;
	}

	public Integer getId_negocio() {
		return id_negocio;
	}

}
