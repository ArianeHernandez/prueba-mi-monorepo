package com.osmosyscol.datasuite.webdata.correval.productocuenta.dto;

import java.math.BigDecimal;


public class ProductoEncargos {

	private String cuenta;
	private String nombreProducto;
	private Integer codigoProducto;
	private String nombreLinea;
	private Integer codigoLinea;
	private BigDecimal saldo;
	
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public Integer getCodigoProducto() {
		return codigoProducto;
	}
	public void setCodigoProducto(Integer codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	public String getNombreLinea() {
		return nombreLinea;
	}
	public void setNombreLinea(String nombreLinea) {
		this.nombreLinea = nombreLinea;
	}
	public Integer getCodigoLinea() {
		return codigoLinea;
	}
	public void setCodigoLinea(Integer codigoLinea) {
		this.codigoLinea = codigoLinea;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

}
