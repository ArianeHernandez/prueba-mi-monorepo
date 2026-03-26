package com.osmosyscol.datasuite.mein.dtos;

import java.util.List;

public class DatosBasicosPres {
	
	private Integer id;
	private String nombre_camara_comercio;
	private String nombre_mediador;
	private String certificacon_pres;
	private String copia_expediente;
	private ObjGenerico tipo_acreedor;
	private String valor_activos_reportados;
	private String valor_pasivos_reportados;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre_camara_comercio() {
		return nombre_camara_comercio;
	}
	public void setNombre_camara_comercio(String nombre_camara_comercio) {
		this.nombre_camara_comercio = nombre_camara_comercio;
	}
	public String getNombre_mediador() {
		return nombre_mediador;
	}
	public void setNombre_mediador(String nombre_mediador) {
		this.nombre_mediador = nombre_mediador;
	}
	public String getCertificacon_pres() {
		return certificacon_pres;
	}
	public void setCertificacon_pres(String certificacon_pres) {
		this.certificacon_pres = certificacon_pres;
	}
	public String getCopia_expediente() {
		return copia_expediente;
	}
	public void setCopia_expediente(String copia_expediente) {
		this.copia_expediente = copia_expediente;
	}
	public ObjGenerico getTipo_acreedor() {
		return tipo_acreedor;
	}
	public void setTipo_acreedor(ObjGenerico tipo_acreedor) {
		this.tipo_acreedor = tipo_acreedor;
	}
	public String getValor_activos_reportados() {
		return valor_activos_reportados;
	}
	public void setValor_activos_reportados(String valor_activos_reportados) {
		this.valor_activos_reportados = valor_activos_reportados;
	}
	public String getValor_pasivos_reportados() {
		return valor_pasivos_reportados;
	}
	public void setValor_pasivos_reportados(String valor_pasivos_reportados) {
		this.valor_pasivos_reportados = valor_pasivos_reportados;
	}
	
}
