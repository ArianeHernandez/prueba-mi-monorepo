package com.osmosyscol.datasuite.modelatos.logica.dto;

public class TipoEstilo {
	
	private Integer id_tipo_estilo;
	private String nombre;
	private String etiqueta;
	private String texto_ayuda;

	public Integer getId_tipo_estilo() {
		return id_tipo_estilo;
	}

	public void setId_tipo_estilo(Integer id_tipo_estilo) {
		this.id_tipo_estilo = id_tipo_estilo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	public String getTexto_ayuda() {
		return texto_ayuda;
	}

	public void setTexto_ayuda(String texto_ayuda) {
		this.texto_ayuda = texto_ayuda;
	}

}
