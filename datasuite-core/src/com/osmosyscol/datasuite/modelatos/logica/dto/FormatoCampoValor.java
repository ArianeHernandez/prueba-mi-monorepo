package com.osmosyscol.datasuite.modelatos.logica.dto;

public class FormatoCampoValor {

	private Integer id_campo;
	private Integer id_formato_campo;
	private String nombre_campo;
	private String titulo;
	private String valor;
	
	public Integer getId_campo() {
		return id_campo;
	}

	public void setId_campo(Integer id_campo) {
		this.id_campo = id_campo;
	}

	public Integer getId_formato_campo() {
		return id_formato_campo;
	}

	public void setId_formato_campo(Integer id_formato_campo) {
		this.id_formato_campo = id_formato_campo;
	}

	public String getNombre_campo() {
		return nombre_campo;
	}

	public void setNombre_campo(String nombre_campo) {
		this.nombre_campo = nombre_campo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
}
