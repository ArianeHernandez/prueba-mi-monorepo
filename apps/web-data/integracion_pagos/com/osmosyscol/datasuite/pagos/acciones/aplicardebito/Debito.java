package com.osmosyscol.datasuite.pagos.acciones.aplicardebito;

import java.math.BigDecimal;

public class Debito {

	private String codigo_producto;
	private String codigo_cuenta;
	private String codigo_concepto;
	private BigDecimal valor_total;
	private String descripcion;
	private String tipo_liquidacion;
	private String aplica_gmf;
	private String tipo_identificacion;
	private String numero_identificacion;
	private String id_compensacion;
	private String id_cuenta_bancaria;
	private String tipo_cuenta_bancaria;
	private Integer id_carga;
	private Integer id_retiro;
	private String linea_producto;

	public Integer getId_retiro() {
		return id_retiro;
	}
	
	public void setId_retiro(Integer id_retiro) {
		this.id_retiro = id_retiro;
	}
	
	public String getCodigo_producto() {
		return codigo_producto;
	}

	public void setCodigo_producto(String codigo_producto) {
		this.codigo_producto = codigo_producto;
	}

	public String getCodigo_cuenta() {
		return codigo_cuenta;
	}

	public void setCodigo_cuenta(String codigo_cuenta) {
		this.codigo_cuenta = codigo_cuenta;
	}

	public String getCodigo_concepto() {
		return codigo_concepto;
	}

	public void setCodigo_concepto(String codigo_concepto) {
		this.codigo_concepto = codigo_concepto;
	}

	public BigDecimal getValor_total() {
		return valor_total;
	}

	public void setValor_total(BigDecimal valor_total) {
		this.valor_total = valor_total;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipo_liquidacion() {
		return tipo_liquidacion;
	}

	public void setTipo_liquidacion(String tipo_liquidacion) {
		this.tipo_liquidacion = tipo_liquidacion;
	}

	public String getAplica_gmf() {
		return aplica_gmf;
	}

	public void setAplica_gmf(String aplica_gmf) {
		this.aplica_gmf = aplica_gmf;
	}

	public String getTipo_identificacion() {
		return tipo_identificacion;
	}

	public void setTipo_identificacion(String tipo_identificacion) {
		this.tipo_identificacion = tipo_identificacion;
	}

	public String getNumero_identificacion() {
		return numero_identificacion;
	}

	public void setNumero_identificacion(String numero_identificacion) {
		this.numero_identificacion = numero_identificacion;
	}

	public String getId_compensacion() {
		return id_compensacion;
	}

	public void setId_compensacion(String id_compensacion) {
		this.id_compensacion = id_compensacion;
	}

	public String getId_cuenta_bancaria() {
		return id_cuenta_bancaria;
	}

	public void setId_cuenta_bancaria(String id_cuenta_bancaria) {
		this.id_cuenta_bancaria = id_cuenta_bancaria;
	}

	public String getTipo_cuenta_bancaria() {
		return tipo_cuenta_bancaria;
	}

	public void setTipo_cuenta_bancaria(String tipo_cuenta_bancaria) {
		this.tipo_cuenta_bancaria = tipo_cuenta_bancaria;
	}

	public Integer getId_carga() {
		return id_carga;
	}

	public void setId_carga(Integer id_carga) {
		this.id_carga = id_carga;
	}

	public String getLinea_producto() {
		return linea_producto;
	}

	public void setLinea_producto(String linea_producto) {
		this.linea_producto = linea_producto;
	}
}
