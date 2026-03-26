package com.osmosyscol.datasuite.mein.dtos;

import java.util.Date;

public class CumplimientoHipotesis {

	private Integer id;
	private Integer idCarga;
	private Date fecha_verificacion;
	
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
	public Date getFecha_verificacion() {
		return fecha_verificacion;
	}
	public void setFecha_verificacion(Date fecha_relacion) {
		this.fecha_verificacion = fecha_relacion;
	}
	
}
