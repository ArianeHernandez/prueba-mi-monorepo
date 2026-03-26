package com.osmosyscol.datasuite.webdata.correval.servicios.reporte_tipo_negocio;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TipoNegocio {
	
	private Integer id_encargo;
	private String nombre_cliente;
	private String tipo_negocio;
	private String producto;
	private String encargo;
	private BigDecimal saldo;
	private Date fecha;
	
	public Integer getId_encargo() {
		return id_encargo;
	}
	public void setId_encargo(Integer id_encargo) {
		this.id_encargo = id_encargo;
	}
	public String getNombre_cliente() {
		return nombre_cliente;
	}
	public void setNombre_cliente(String nombre_cliente) {
		this.nombre_cliente = nombre_cliente;
	}
	public String getTipo_negocio() {
		return tipo_negocio;
	}
	public void setTipo_negocio(String tipo_negocio) {
		this.tipo_negocio = tipo_negocio;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public String getEncargo() {
		return encargo;
	}
	public void setEncargo(String encargo) {
		this.encargo = encargo;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
}