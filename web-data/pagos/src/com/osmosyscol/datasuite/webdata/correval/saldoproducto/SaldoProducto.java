package com.osmosyscol.datasuite.webdata.correval.saldoproducto;

import java.math.BigDecimal;

public class SaldoProducto {
	
	private Integer id_usuario;
	private String nombre_producto;
	private BigDecimal saldo_total;
	private BigDecimal saldo_disponible;
	private Integer id_negocio;
	private String nombre_negocio;
	private String codigo;
	
	
	public Integer getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getNombre_producto() {
		return nombre_producto;
	}
	public void setNombre_producto(String nombre_producto) {
		this.nombre_producto = nombre_producto;
	}
	public BigDecimal getSaldo_total() {
		return saldo_total;
	}
	public void setSaldo_total(BigDecimal saldo_total) {
		this.saldo_total = saldo_total;
	}
	public BigDecimal getSaldo_disponible() {
		return saldo_disponible;
	}
	public void setSaldo_disponible(BigDecimal saldo_disponible) {
		this.saldo_disponible = saldo_disponible;
	}
	public Integer getId_negocio() {
		return id_negocio;
	}
	public void setId_negocio(Integer id_negocio) {
		this.id_negocio = id_negocio;
	}
	public String getNombre_negocio() {
		return nombre_negocio;
	}
	public void setNombre_negocio(String nombre_negocio) {
		this.nombre_negocio = nombre_negocio;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
