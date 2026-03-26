package com.osmosyscol.datasuite.pagos.acciones.aplicardevolucion;

import java.math.BigDecimal;

public class RetiroDevolucion {

	private Long idRetiro;
	private Integer idCarga;
	private String numeroOrden;
	private String producto;
	private String encargo;
	private BigDecimal valor;
	private String consecutivoDebito;
	private String estadoRespuestaBanco;
	private String codigoConcepto;
	private String lineaProducto;
	private Integer idArchivo;

	private Integer idCliente;

	public Long getIdRetiro() {
		return idRetiro;
	}

	public void setIdRetiro(Long idRetiro) {
		this.idRetiro = idRetiro;
	}

	public Integer getIdCarga() {
		return idCarga;
	}

	public void setIdCarga(Integer idCarga) {
		this.idCarga = idCarga;
	}

	public String getNumeroOrden() {
		return numeroOrden;
	}

	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrden = numeroOrden;
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

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getConsecutivoDebito() {
		return consecutivoDebito;
	}

	public void setConsecutivoDebito(String consecutivoDebito) {
		this.consecutivoDebito = consecutivoDebito;
	}

	public String getEstadoRespuestaBanco() {
		return estadoRespuestaBanco;
	}

	public void setEstadoRespuestaBanco(String estadoRespuestaBanco) {
		this.estadoRespuestaBanco = estadoRespuestaBanco;
	}

	public String getCodigoConcepto() {
		return codigoConcepto;
	}

	public void setCodigoConcepto(String codigoConcepto) {
		this.codigoConcepto = codigoConcepto;
	}

	public String getLineaProducto() {
		return lineaProducto;
	}

	public void setLineaProducto(String lineaProducto) {
		this.lineaProducto = lineaProducto;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Integer getIdArchivo() {
		return idArchivo;
	}

	public void setIdArchivo(Integer idArchivo) {
		this.idArchivo = idArchivo;
	}

}
