package com.osmosyscol.datasuite.mein.dtos;

import java.util.List;

public class CondicionControlante {
	
	private Integer id;
	private String nit;
	private String nombre_de_la_sociedad;
	private String es_controlante_de_socieda;
	private String certificacion_controlante;
	private String composicion_capital;
	private String certificado_existencia;
	private String prueba_controlante;
	private String obligaciones_alimentarias;
	private String radicado_solicitud_auto;
	private Integer condicion_deudor;
	private ObjGenerico condicion_deudor_obj;
	private APVistaDeudorCondicion condicion_deudor_apvista;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCertificacion_controlante() {
		return certificacion_controlante;
	}
	public void setCertificacion_controlante(String certificacion_controlante) {
		this.certificacion_controlante = certificacion_controlante;
	}
	public String getComposicion_capital() {
		return composicion_capital;
	}
	public void setComposicion_capital(String composicion_capital) {
		this.composicion_capital = composicion_capital;
	}
	public String getCertificado_existencia() {
		return certificado_existencia;
	}
	public void setCertificado_existencia(String certificado_existencia) {
		this.certificado_existencia = certificado_existencia;
	}
	public String getPrueba_controlante() {
		return prueba_controlante;
	}
	public void setPrueba_controlante(String prueba_controlante) {
		this.prueba_controlante = prueba_controlante;
	}
	public String getObligaciones_alimentarias() {
		return obligaciones_alimentarias;
	}
	public void setObligaciones_alimentarias(String obligaciones_alimentarias) {
		this.obligaciones_alimentarias = obligaciones_alimentarias;
	}
	public String getNit() {
		return nit;
	}
	public void setNit(String nit) {
		this.nit = nit;
	}
	public String getNombre_de_la_sociedad() {
		return nombre_de_la_sociedad;
	}
	public void setNombre_de_la_sociedad(String nombre_de_la_sociedad) {
		this.nombre_de_la_sociedad = nombre_de_la_sociedad;
	}
	public String getEs_controlante_de_socieda() {
		return es_controlante_de_socieda;
	}
	public void setEs_controlante_de_socieda(String es_controlante_de_socieda) {
		this.es_controlante_de_socieda = es_controlante_de_socieda;
	}
	public String getRadicado_solicitud_auto() {
		return radicado_solicitud_auto;
	}
	public void setRadicado_solicitud_auto(String radicado_solicitud_auto) {
		this.radicado_solicitud_auto = radicado_solicitud_auto;
	}
	public Integer getCondicion_deudor() {
		return condicion_deudor;
	}
	public void setCondicion_deudor(Integer condicion_deudor) {
		this.condicion_deudor = condicion_deudor;
	}
	public ObjGenerico getCondicion_deudor_obj() {
		return condicion_deudor_obj;
	}
	public void setCondicion_deudor_obj(ObjGenerico condicion_deudor_obj) {
		this.condicion_deudor_obj = condicion_deudor_obj;
	}
	public APVistaDeudorCondicion getCondicion_deudor_apvista() {
		return condicion_deudor_apvista;
	}
	public void setCondicion_deudor_apvista(APVistaDeudorCondicion condicion_deudor_apvista) {
		this.condicion_deudor_apvista = condicion_deudor_apvista;
	}
}
