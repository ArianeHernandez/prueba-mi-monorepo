package com.osmosyscol.datasuite.mein.dtos;

public class TipoSolicitud {
	
	private Integer id;
	private Integer idcarga;
	private Integer grupo_niif;
	private ObjGenerico grupo_niif_obj;
	private Integer metodo;
	private Integer tipo_solicitud;
	private ObjGenerico tipo_solicitud_obj;
	private Integer norma_aplicable;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdcarga() {
		return idcarga;
	}
	public void setIdcarga(Integer idcarga) {
		this.idcarga = idcarga;
	}
	public Integer getGrupo_niif() {
		return grupo_niif;
	}
	public void setGrupo_niif(Integer grupo_niif) {
		this.grupo_niif = grupo_niif;
	}
	public Integer getMetodo() {
		return metodo;
	}
	public void setMetodo(Integer metodo) {
		this.metodo = metodo;
	}
	public Integer getTipo_solicitud() {
		return tipo_solicitud;
	}
	public void setTipo_solicitud(Integer tipo_solicitud) {
		this.tipo_solicitud = tipo_solicitud;
	}
	public Integer getNorma_aplicable() {
		return norma_aplicable;
	}
	public void setNorma_aplicable(Integer norma_aplicable) {
		this.norma_aplicable = norma_aplicable;
	}
	public ObjGenerico getTipo_solicitud_obj() {
		return tipo_solicitud_obj;
	}
	public void setTipo_solicitud_obj(ObjGenerico tipo_solicitud_obj) {
		this.tipo_solicitud_obj = tipo_solicitud_obj;
	}
	public ObjGenerico getGrupo_niif_obj() {
		return grupo_niif_obj;
	}
	public void setGrupo_niif_obj(ObjGenerico grupo_niif_obj) {
		this.grupo_niif_obj = grupo_niif_obj;
	}

}
