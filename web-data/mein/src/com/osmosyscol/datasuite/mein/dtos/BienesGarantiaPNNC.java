package com.osmosyscol.datasuite.mein.dtos;

public class BienesGarantiaPNNC {

	private Integer id;
	private Integer idCarga;
	private Integer bienes_sujetos;
	private String certificacion_bienes_pnnc;
	private String avaluo_bienes_pnnc;
	private String obligaciones_pnnc;
	
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
	public Integer getBienes_sujetos() {
		return bienes_sujetos;
	}
	public void setBienes_sujetos(Integer bienes_sujetos) {
		this.bienes_sujetos = bienes_sujetos;
	}
	public String getCertificacion_bienes_pnnc() {
		return certificacion_bienes_pnnc;
	}
	public void setCertificacion_bienes_pnnc(String certificacion_bienes_pnnc) {
		this.certificacion_bienes_pnnc = certificacion_bienes_pnnc;
	}
	public String getAvaluo_bienes_pnnc() {
		return avaluo_bienes_pnnc;
	}
	public void setAvaluo_bienes_pnnc(String avaluo_bienes_pnnc) {
		this.avaluo_bienes_pnnc = avaluo_bienes_pnnc;
	}
	public String getObligaciones_pnnc() {
		return obligaciones_pnnc;
	}
	public void setObligaciones_pnnc(String obligaciones_pnnc) {
		this.obligaciones_pnnc = obligaciones_pnnc;
	}
	
}
