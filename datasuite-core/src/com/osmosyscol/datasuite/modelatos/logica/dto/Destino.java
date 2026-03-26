package com.osmosyscol.datasuite.modelatos.logica.dto;

public class Destino {

	private Integer id_destino;
	private Integer id_uri;
	private Integer id_formato;
	private String titulo;
	
	
	//--------------------------------------
	
	public Integer getId_uri() {
		return id_uri;
	}
	public void setId_uri(Integer id_uri) {
		this.id_uri = id_uri;
	}
	public Integer getId_destino() {
		return id_destino;
	}
	public Integer getId_formato() {
		return id_formato;
	}
	public void setId_formato(Integer id_formato) {
		this.id_formato = id_formato;
	}
	public void setId_destino(Integer id_destino) {
		this.id_destino = id_destino;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	
	}
