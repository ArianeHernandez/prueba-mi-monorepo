package com.osmosyscol.datasuite.mein.dtos;

import java.util.List;

public class SupuestosAdmisibilidad {
	
	private Integer id;
	private ObjGenerico supuestos_admisibilidad;//situacion presentada lista valores
	private String certificacion_cesacion;
	private String anexo_certificacion;
	private ObjGenerico supuestos_pnnc; //situacion presentada lista valores
	private String certificacion_pnnc;
	private String anexo_pnnc;
	private String supuestos_pnc_cesacion;
	private String certificacion_pnc_cesac;
	private String anexo_pnnc_cesacion;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ObjGenerico getSupuestos_admisibilidad() {
		return supuestos_admisibilidad;
	}
	public void setSupuestos_admisibilidad(ObjGenerico supuestos_admisibilidad) {
		this.supuestos_admisibilidad = supuestos_admisibilidad;
	}
	public String getCertificacion_cesacion() {
		return certificacion_cesacion;
	}
	public void setCertificacion_cesacion(String certificacion_cesacion) {
		this.certificacion_cesacion = certificacion_cesacion;
	}
	public String getAnexo_certificacion() {
		return anexo_certificacion;
	}
	public void setAnexo_certificacion(String anexo_certificacion) {
		this.anexo_certificacion = anexo_certificacion;
	}
	public ObjGenerico getSupuestos_pnnc() {
		return supuestos_pnnc;
	}
	public void setSupuestos_pnnc(ObjGenerico supuestos_pnnc) {
		this.supuestos_pnnc = supuestos_pnnc;
	}
	public String getCertificacion_pnnc() {
		return certificacion_pnnc;
	}
	public void setCertificacion_pnnc(String certificacion_pnnc) {
		this.certificacion_pnnc = certificacion_pnnc;
	}
	public String getAnexo_pnnc() {
		return anexo_pnnc;
	}
	public void setAnexo_pnnc(String anexo_pnnc) {
		this.anexo_pnnc = anexo_pnnc;
	}
	public String getSupuestos_pnc_cesacion() {
		return supuestos_pnc_cesacion;
	}
	public void setSupuestos_pnc_cesacion(String supuestos_pnc_cesacion) {
		this.supuestos_pnc_cesacion = supuestos_pnc_cesacion;
	}
	public String getCertificacion_pnc_cesac() {
		return certificacion_pnc_cesac;
	}
	public void setCertificacion_pnc_cesac(String certificacion_pnc_cesac) {
		this.certificacion_pnc_cesac = certificacion_pnc_cesac;
	}
	public String getAnexo_pnnc_cesacion() {
		return anexo_pnnc_cesacion;
	}
	public void setAnexo_pnnc_cesacion(String anexo_pnnc_cesacion) {
		this.anexo_pnnc_cesacion = anexo_pnnc_cesacion;
	}
	
}
