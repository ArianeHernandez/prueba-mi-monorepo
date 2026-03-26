package com.osmosyscol.datasuite.logica.dto;

public class Contenido {
	
	private String url;
	private String etiqueta;
	private String texto;
	private Integer id_contenido;
	private String nombre_servicio;
	
	
	public Contenido() {

	}
	
	
	public String getNombre_servicio() {
		return nombre_servicio;
	}
	
	public void setNombre_servicio(String nombre_servicio) {
		this.nombre_servicio = nombre_servicio;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	public String getTexto() {
		
		if(texto!=null){
			return texto.replace('"', '\'');
		}
		
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	public Integer getId_contenido() {
		return id_contenido;
	}
	
	public void setId_contenido(Integer id_contenido) {
		this.id_contenido = id_contenido;
	}
	


}
