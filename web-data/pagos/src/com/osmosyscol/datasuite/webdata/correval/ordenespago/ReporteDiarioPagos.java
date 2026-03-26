package com.osmosyscol.datasuite.webdata.correval.ordenespago;

import java.util.Date;

public class ReporteDiarioPagos {
	
	private Date fecha;
	private String nombre_cliente;
	private Integer autorizadas;
	private Integer rechazadas;
	private Integer enProceso;
	private Integer aplicadasBanco;
	private Integer rechazadasBanco;
	private Integer pendientesBanco;
	private Integer totalPagos;
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getNombre_cliente() {
		return nombre_cliente;
	}
	public void setNombre_cliente(String nombre_cliente) {
		this.nombre_cliente = nombre_cliente;
	}
	public Integer getAutorizadas() {
		return autorizadas;
	}
	public void setAutorizadas(Integer autorizadas) {
		this.autorizadas = autorizadas;
	}
	public Integer getRechazadas() {
		return rechazadas;
	}
	public void setRechazadas(Integer rechazadas) {
		this.rechazadas = rechazadas;
	}
	public Integer getEnProceso() {
		return enProceso;
	}
	public void setEnProceso(Integer enProceso) {
		this.enProceso = enProceso;
	}
	public Integer getAplicadasBanco() {
		return aplicadasBanco;
	}
	public void setAplicadasBanco(Integer aplicadasBanco) {
		this.aplicadasBanco = aplicadasBanco;
	}
	public Integer getRechazadasBanco() {
		return rechazadasBanco;
	}
	public void setRechazadasBanco(Integer rechazadasBanco) {
		this.rechazadasBanco = rechazadasBanco;
	}
	public Integer getPendientesBanco() {
		return pendientesBanco;
	}
	public void setPendientesBanco(Integer pendientesBanco) {
		this.pendientesBanco = pendientesBanco;
	}
	public Integer getTotalPagos() {
		return totalPagos;
	}
	public void setTotalPagos(Integer totalPagos) {
		this.totalPagos = totalPagos;
	}
	
}
