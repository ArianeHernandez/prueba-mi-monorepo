package com.osmosyscol.datasuite.mein.dtos;

import java.util.Date;


public class BienesPnnc {
	
	private Integer id;
	private String relacion_bienes;
	private Date fecha_relacion;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRelacion_bienes() {
		return relacion_bienes;
	}
	public void setRelacion_bienes(String relacion_bienes) {
		this.relacion_bienes = relacion_bienes;
	}
	public Date getFecha_relacion() {
		return fecha_relacion;
	}
	public void setFecha_relacion(Date fecha_relacion) {
		this.fecha_relacion = fecha_relacion;
	}
	
}
