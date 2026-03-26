package com.osmosyscol.datasuite.mein.dtos;

public class BienesSujetosGarantia {

	private Integer id;
	private Integer idCarga;
	private Integer bienes_sujetos_garantia;
	private String certificacion_garantia;
	private String certificacion_bienes;
	private String avaluo_bienes;
	private String obligaciones_bienes;
	
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
	public Integer getBienes_sujetos_garantia() {
		return bienes_sujetos_garantia;
	}
	public void setBienes_sujetos_garantia(Integer bienes_sujetos_garantia) {
		this.bienes_sujetos_garantia = bienes_sujetos_garantia;
	}
	public String getCertificacion_garantia() {
		return certificacion_garantia;
	}
	public void setCertificacion_garantia(String certificacion_garantia) {
		this.certificacion_garantia = certificacion_garantia;
	}
	public String getCertificacion_bienes() {
		return certificacion_bienes;
	}
	public void setCertificacion_bienes(String certificacion_bienes) {
		this.certificacion_bienes = certificacion_bienes;
	}
	public String getAvaluo_bienes() {
		return avaluo_bienes;
	}
	public void setAvaluo_bienes(String avaluo_bienes) {
		this.avaluo_bienes = avaluo_bienes;
	}
	public String getObligaciones_bienes() {
		return obligaciones_bienes;
	}
	public void setObligaciones_bienes(String obligaciones_bienes) {
		this.obligaciones_bienes = obligaciones_bienes;
	}
	
}
