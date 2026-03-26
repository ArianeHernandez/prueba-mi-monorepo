package com.osmosyscol.datasuite.mein.dtos;

public class APVistaSeguridadTipo {

	private Integer id;
	private Long id_vista;
	private String seguridadTipo;
	private String codigoAlfanumerico;
	private Long id_tipo_seguridad;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Long getId_vista() {
		return id_vista;
	}
	public void setId_vista(Long id_vista) {
		this.id_vista = id_vista;
	}
	public String getSeguridadTipo() {
		return seguridadTipo;
	}
	public void setSeguridadTipo(String seguridadTipo) {
		this.seguridadTipo = seguridadTipo;
	}
	public String getCodigoAlfanumerico() {
		return codigoAlfanumerico;
	}
	public void setCodigoAlfanumerico(String codigoAlfanumerico) {
		this.codigoAlfanumerico = codigoAlfanumerico;
	}
	public Long getId_tipo_seguridad() {
		return id_tipo_seguridad;
	}
	public void setId_tipo_seguridad(Long id_seguridad_tipo) {
		this.id_tipo_seguridad = id_seguridad_tipo;
	}
	
	
}
