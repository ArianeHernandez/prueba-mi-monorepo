package com.osmosyscol.datasuite.mein.dtos;

public class PruebaAprobacion {

	private Integer id;
	private Integer idCarga;
	private String votos_positivos;
	private String documentos_capacidad;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdCarga() {
		return idCarga;
	}
	public void setIdCarga(Integer idCarga) {
		this.idCarga = idCarga;
	}
	public String getVotos_positivos() {
		return votos_positivos;
	}
	public void setVotos_positivos(String votos_positivos) {
		this.votos_positivos = votos_positivos;
	}
	public String getDocumentos_capacidad() {
		return documentos_capacidad;
	}
	public void setDocumentos_capacidad(String documentos_capacidad) {
		this.documentos_capacidad = documentos_capacidad;
	}
	
}
