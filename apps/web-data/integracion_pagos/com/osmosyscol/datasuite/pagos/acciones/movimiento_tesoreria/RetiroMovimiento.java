package com.osmosyscol.datasuite.pagos.acciones.movimiento_tesoreria;

import java.math.BigDecimal;

public class RetiroMovimiento {

	private Integer id_carga;
	private Integer id_retiro;

	private String codigo_producto;
	private String codigo_concepto;
	private String codigo_cuenta;
	private String numero_orden;
	private String codigo_banco;
	private String tipo_cuenta;
	private String numero_cuenta;
	private BigDecimal valor;

	public String getCodigo_concepto() {
		return codigo_concepto;
	}

	public void setCodigo_concepto(String codigo_concepto) {
		this.codigo_concepto = codigo_concepto;
	}

	public Integer getId_retiro() {
		return id_retiro;
	}

	public void setId_retiro(Integer id_retiro) {
		this.id_retiro = id_retiro;
	}

	public Integer getId_carga() {
		return id_carga;
	}

	public void setId_carga(Integer id_carga) {
		this.id_carga = id_carga;
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

	public String getNumero_orden() {
		return numero_orden;
	}

	public void setNumero_orden(String numero_orden) {
		this.numero_orden = numero_orden;
	}

	public String getCodigo_banco() {
		return codigo_banco;
	}

	public void setCodigo_banco(String codigo_banco) {
		this.codigo_banco = codigo_banco;
	}

	public String getTipo_cuenta() {
		return tipo_cuenta;
	}

	public void setTipo_cuenta(String tipo_cuenta) {
		this.tipo_cuenta = tipo_cuenta;
	}

	public String getNumero_cuenta() {
		return numero_cuenta;
	}

	public void setNumero_cuenta(String numero_cuenta) {
		this.numero_cuenta = numero_cuenta;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "RetiroMovimiento [id_carga=" + id_carga + ", id_retiro=" + id_retiro + ", codigo_producto="
				+ codigo_producto + ", codigo_concepto=" + codigo_concepto + ", codigo_cuenta=" + codigo_cuenta
				+ ", numero_orden=" + numero_orden + ", codigo_banco=" + codigo_banco + ", tipo_cuenta=" + tipo_cuenta
				+ ", numero_cuenta=" + numero_cuenta + ", valor=" + valor + "]";
	}

}
