package com.osmosyscol.datasuite.mein.dtos;

import java.util.Date;
import java.util.List;

public class AutorizacionJunta {
	
	private Integer id;
	private String acta_autorizacion;
	private Date fecha_del_acta;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getActa_autorizacion() {
		return acta_autorizacion;
	}
	public void setActa_autorizacion(String acta_autorizacion) {
		this.acta_autorizacion = acta_autorizacion;
	}
	public Date getFecha_del_acta() {
		return fecha_del_acta;
	}
	public void setFecha_del_acta(Date fecha_del_acta) {
		this.fecha_del_acta = fecha_del_acta;
	}
	
}
