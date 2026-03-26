package com.osmosyscol.datasuite.pagos.acciones.centralpagos.dto;

import java.util.Date;

public class SolicitudGeneralPago {
	
	
	// Info MODELATOS
	private Integer id;
	private Integer idcarga;
	private Integer idelementocarga;
	private String estado;
	private Integer nreg;
	
	// Info DATAUSUITE
	private String codigo_contrato;
	private Integer empresa;
	private Integer tipo_contrato;
	private Integer numero_de_planilla;
	private Date periodo_de_cotizacion;
	private Integer num_factura;
	private Date fecha_del_documento;
	private Integer numero_de_pago;
	private String prefijo_factura;
	private Integer orden_pago;
	private Integer radicacion;
	private Date fecha_actualizacion;
	private String estado_orden;
	private String observacion;
	private Integer subtotal;
	private Integer valor_IVA;
	private Integer valor_total;
	private Boolean es_adicion;
	private Integer num_adicion;
	private String regimen_iva;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getIdcarga() {
		return idcarga;
	}

	public void setIdcarga(Integer idcarga) {
		this.idcarga = idcarga;
	}

	public Integer getIdelementocarga() {
		return idelementocarga;
	}

	public void setIdelementocarga(Integer idelementocarga) {
		this.idelementocarga = idelementocarga;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getNreg() {
		return nreg;
	}

	public void setNreg(Integer nreg) {
		this.nreg = nreg;
	}

	public String getCodigo_contrato() {
		return codigo_contrato;
	}

	public void setCodigo_contrato(String numero_contrato) {
		this.codigo_contrato = numero_contrato;
	}

	public Integer getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Integer empresa) {
		this.empresa = empresa;
	}

	public Integer getTipo_contrato() {
		return tipo_contrato;
	}

	public void setTipo_contrato(Integer tipo_contrato) {
		this.tipo_contrato = tipo_contrato;
	}

	public Integer getNumero_de_planilla() {
		return numero_de_planilla;
	}

	public void setNumero_de_planilla(Integer numero_de_planilla) {
		this.numero_de_planilla = numero_de_planilla;
	}

	public Date getPeriodo_de_cotizacion() {
		return periodo_de_cotizacion;
	}

	public void setPeriodo_de_cotizacion(Date periodo_de_cotizacion) {
		this.periodo_de_cotizacion = periodo_de_cotizacion;
	}

	public Integer getNum_factura() {
		return num_factura;
	}

	public void setNum_factura(Integer num_factura) {
		this.num_factura = num_factura;
	}

	public Date getFecha_del_documento() {
		return fecha_del_documento;
	}

	public void setFecha_del_documento(Date fecha_del_documento) {
		this.fecha_del_documento = fecha_del_documento;
	}

	public Integer getNumero_de_pago() {
		return numero_de_pago;
	}

	public void setNumero_de_pago(Integer numero_de_pago) {
		this.numero_de_pago = numero_de_pago;
	}

	public String getPrefijo_factura() {
		return prefijo_factura;
	}

	public void setPrefijo_factura(String prefijo_factura) {
		this.prefijo_factura = prefijo_factura;
	}

	public Integer getOrden_pago() {
		return orden_pago;
	}

	public void setOrden_pago(Integer orden_pago) {
		this.orden_pago = orden_pago;
	}

	public Integer getRadicacion() {
		return radicacion;
	}

	public void setRadicacion(Integer radicacion) {
		this.radicacion = radicacion;
	}

	public Date getFecha_actualizacion() {
		return fecha_actualizacion;
	}

	public void setFecha_actualizacion(Date fecha_actualizacion) {
		this.fecha_actualizacion = fecha_actualizacion;
	}

	public String getEstado_orden() {
		return estado_orden;
	}

	public void setEstado_orden(String estado_orden) {
		this.estado_orden = estado_orden;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Integer getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Integer subtotal) {
		this.subtotal = subtotal;
	}

	public Integer getValor_IVA() {
		return valor_IVA;
	}

	public void setValor_IVA(Integer valor_IVA) {
		this.valor_IVA = valor_IVA;
	}

	public Integer getValor_total() {
		return valor_total;
	}

	public void setValor_total(Integer valor_total) {
		this.valor_total = valor_total;
	}

	public Boolean getEs_adicion() {
		return es_adicion;
	}

	public void setEs_adicion(Boolean es_adicion) {
		this.es_adicion = es_adicion;
	}

	public Integer getNum_adicion() {
		return num_adicion;
	}

	public void setNum_adicion(Integer num_adicion) {
		this.num_adicion = num_adicion;
	}

	public String getRegimen_iva() {
		return regimen_iva;
	}

	public void setRegimen_iva(String regimen_iva) {
		this.regimen_iva = regimen_iva;
	}
	
	
	
	
	
}
