package com.osmosyscol.datasuite.mein.dtos;

import java.util.Date;

public class BienesAcreedores {

	private Integer id;
	private Integer idCarga;
	private Date fecha_bienes_acreedores;
	private String detalle_bienes;
	private String relacion_acreedores;
	private String relacion_bienes_garantia;
	private String relac_bienes_necesarios;
	
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
	public Date getFecha_bienes_acreedores() {
		return fecha_bienes_acreedores;
	}
	public void setFecha_bienes_acreedores(Date fecha_bienes_acreedores) {
		this.fecha_bienes_acreedores = fecha_bienes_acreedores;
	}
	public String getDetalle_bienes() {
		return detalle_bienes;
	}
	public void setDetalle_bienes(String detalle_bienes) {
		this.detalle_bienes = detalle_bienes;
	}
	public String getRelacion_acreedores() {
		return relacion_acreedores;
	}
	public void setRelacion_acreedores(String relacion_acreedores) {
		this.relacion_acreedores = relacion_acreedores;
	}
	public String getRelacion_bienes_garantia() {
		return relacion_bienes_garantia;
	}
	public void setRelacion_bienes_garantia(String relacion_bienes_garantia) {
		this.relacion_bienes_garantia = relacion_bienes_garantia;
	}
	public String getRelac_bienes_necesarios() {
		return relac_bienes_necesarios;
	}
	public void setRelac_bienes_necesarios(String relac_bienes_necesarios) {
		this.relac_bienes_necesarios = relac_bienes_necesarios;
	}
}
