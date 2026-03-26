package com.osmosyscol.datasuite.pagos.acciones.centralpagos.dto;

import java.math.BigDecimal;
import java.util.Date;

public class SolicitudRadicacionPago {

	private Integer id;
	private Integer empresa;
	private Integer vigencia_radicacion;
	private Integer id_solicitante;
	private String tipo_doc_presupuesto;
	private Integer vigencia_doc_presupuesto;
	private Integer numero_doc_presuspuesto;
	private String prefijo_factura;
	private Integer numero_factura;
	private Date fecha_factura;
	private BigDecimal valor_total;
	private BigDecimal valor_base;
	private BigDecimal valor_iva;
	private Integer tipo_mv_radica;
	private String mv_radica;

	private String codigo;

	public Integer getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Integer empresa) {
		this.empresa = empresa;
	}

	public Integer getVigencia_radicacion() {
		return vigencia_radicacion;
	}

	public void setVigencia_radicacion(Integer vigencia_radicacion) {
		this.vigencia_radicacion = vigencia_radicacion;
	}

	public Integer getId_solicitante() {
		return id_solicitante;
	}

	public void setId_solicitante(Integer id_solicitante) {
		this.id_solicitante = id_solicitante;
	}

	public String getTipo_doc_presupuesto() {
		return tipo_doc_presupuesto;
	}

	public void setTipo_doc_presupuesto(String tipo_doc_presupuesto) {
		this.tipo_doc_presupuesto = tipo_doc_presupuesto;
	}

	public Integer getVigencia_doc_presupuesto() {
		return vigencia_doc_presupuesto;
	}

	public void setVigencia_doc_presupuesto(Integer vigencia_doc_presupuesto) {
		this.vigencia_doc_presupuesto = vigencia_doc_presupuesto;
	}

	public Integer getNumero_doc_presuspuesto() {
		return numero_doc_presuspuesto;
	}

	public void setNumero_doc_presuspuesto(Integer numero_doc_presuspuesto) {
		this.numero_doc_presuspuesto = numero_doc_presuspuesto;
	}

	public String getPrefijo_factura() {
		return prefijo_factura;
	}

	public void setPrefijo_factura(String prefijo_factura) {
		this.prefijo_factura = prefijo_factura;
	}

	public Integer getNumero_factura() {
		return numero_factura;
	}

	public void setNumero_factura(Integer numero_factura) {
		this.numero_factura = numero_factura;
	}

	public java.util.Date getFecha_factura() {
		return fecha_factura;
	}

	public void setFecha_factura(java.util.Date fecha_factura) {
		this.fecha_factura = fecha_factura;
	}

	public BigDecimal getValor_base() {
		return valor_base;
	}

	public void setValor_base(BigDecimal valor_base) {
		this.valor_base = valor_base;
	}

	public BigDecimal getValor_iva() {
		return valor_iva;
	}

	public void setValor_iva(BigDecimal valor_iva) {
		this.valor_iva = valor_iva;
	}

	public Integer getTipo_mv_radica() {
		return tipo_mv_radica;
	}

	public void setTipo_mv_radica(Integer tipo_mv_radica) {
		this.tipo_mv_radica = tipo_mv_radica;
	}

	public String getMv_radica() {
		return mv_radica;
	}

	public void setMv_radica(String mv_radica) {
		this.mv_radica = mv_radica;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getValor_total() {
		return valor_total;
	}

	public void setValor_total(BigDecimal valor_total) {
		this.valor_total = valor_total;
	}

}
