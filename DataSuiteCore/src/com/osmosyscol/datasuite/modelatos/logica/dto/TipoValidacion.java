package com.osmosyscol.datasuite.modelatos.logica.dto;


public class TipoValidacion {
	
	private String id_tipovalidacion;     
	private String descripcion;                          
	private String requiere_primer_valor; 
	private String requiere_segundo_valor; 
	
	
	public String getRequiere_primer_valor() {
		return requiere_primer_valor;
	}
	public void setRequiere_primer_valor(String requiere_primer_valor) {
		this.requiere_primer_valor = requiere_primer_valor;
	}
	public String getRequiere_segundo_valor() {
		return requiere_segundo_valor;
	}
	public void setRequiere_segundo_valor(String requiere_segundo_valor) {
		this.requiere_segundo_valor = requiere_segundo_valor;
	}
	public String getId_tipovalidacion() {
		return id_tipovalidacion;
	}
	public void setId_tipovalidacion(String id_tipovalidacion) {
		this.id_tipovalidacion = id_tipovalidacion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	

}
