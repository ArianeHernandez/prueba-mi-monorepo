package com.osmosyscol.datasuite.modelatos.logica.dto;

import java.util.List;

public class ValidacionEstructura {

	
	private Integer id_validacion_estructura;     
	private Integer id_estructura;     
	private String id_tipovalidacion; 
	private String descripcion; 
	private List<ValidacionCampo> listaValidacionCampo;
	private String enlinea;
	
	//Informacion calculada
	private String nombre_tipovalidacion;
	private String requiere_primer_valor;
	private String requiere_segundo_valor;
		
	
	public String getEnlinea() {
		return enlinea==null?"N":enlinea;
	}
	public void setEnlinea(String enLinea) {
		this.enlinea = enLinea;
	}
	public String getNombre_tipovalidacion() {
		return nombre_tipovalidacion;
	}
	public void setNombre_tipovalidacion(String nombre_tipovalidacion) {
		this.nombre_tipovalidacion = nombre_tipovalidacion;
	}
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
	public Integer getId_validacion_estructura() {
		return id_validacion_estructura;
	}
	public void setId_validacion_estructura(Integer id_validacion_estructura) {
		this.id_validacion_estructura = id_validacion_estructura;
	}
	public Integer getId_estructura() {
		return id_estructura;
	}
	public void setId_estructura(Integer id_estructura) {
		this.id_estructura = id_estructura;
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
	public List<ValidacionCampo> getListaValidacionCampo() {
		return listaValidacionCampo;
	}
	public void setListaValidacionCampo(List<ValidacionCampo> listaValidacionCampo) {
		this.listaValidacionCampo = listaValidacionCampo;
	}
	

}
