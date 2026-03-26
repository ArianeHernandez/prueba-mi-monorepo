package com.osmosyscol.datasuite.webdata.correval.tablero_control;

import java.math.BigDecimal;

import com.google.gson.Gson;

public class Banco {

	private Long id;
	private String codigo;
	private String nombre;

	// -----

	private Long num_recibidos = 0L;
	private BigDecimal total_recibidos = BigDecimal.ZERO;

	private Long num_aprobado = 0L;
	private BigDecimal total_aprobados = BigDecimal.ZERO; // sin grupo de giro

	private Long num_agrupado = 0L;
	private BigDecimal total_agrupados = BigDecimal.ZERO; // con grupo de giro

	private Long num_transito = 0L;
	private BigDecimal total_transito = BigDecimal.ZERO;

	private Long num_pendiente = 0L;
	private BigDecimal total_pendiente = BigDecimal.ZERO;

	private Long num_rechazos = 0L;
	private BigDecimal total_rechazos = BigDecimal.ZERO;

	private Long num_rechazo_banco = 0L;
	private BigDecimal total_rechazo_banco = BigDecimal.ZERO;

	// -----

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {

		if (nombre == null) {
			return null;
		}

		return nombre.toLowerCase().replace(' ', '_');
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getNum_recibidos() {
		return num_recibidos;
	}

	public void setNum_recibidos(Long num_recibidos) {
		this.num_recibidos = num_recibidos;
	}

	public BigDecimal getTotal_recibidos() {
		return total_recibidos;
	}

	public void setTotal_recibidos(BigDecimal total_recibidos) {
		this.total_recibidos = total_recibidos;
	}

	public Long getNum_aprobado() {
		return num_aprobado;
	}

	public void setNum_aprobado(Long num_aprobado) {
		this.num_aprobado = num_aprobado;
	}

	public BigDecimal getTotal_aprobados() {
		return total_aprobados;
	}

	public void setTotal_aprobados(BigDecimal total_aprobados) {
		this.total_aprobados = total_aprobados;
	}

	public Long getNum_transito() {
		return num_transito;
	}

	public void setNum_transito(Long num_transito) {
		this.num_transito = num_transito;
	}

	public BigDecimal getTotal_transito() {
		return total_transito;
	}

	public void setTotal_transito(BigDecimal total_transito) {
		this.total_transito = total_transito;
	}

	public Long getNum_pendiente() {
		return num_pendiente;
	}

	public void setNum_pendiente(Long num_pendiente) {
		this.num_pendiente = num_pendiente;
	}

	public BigDecimal getTotal_pendiente() {
		return total_pendiente;
	}

	public void setTotal_pendiente(BigDecimal total_pendiente) {
		this.total_pendiente = total_pendiente;
	}

	public Long getNum_rechazos() {
		return num_rechazos;
	}

	public void setNum_rechazos(Long num_rechazos) {
		this.num_rechazos = num_rechazos;
	}

	public BigDecimal getTotal_rechazos() {
		return total_rechazos;
	}

	public void setTotal_rechazos(BigDecimal total_rechazos) {
		this.total_rechazos = total_rechazos;
	}

	public BigDecimal getTotal_agrupados() {
		return total_agrupados;
	}

	public void setTotal_agrupados(BigDecimal total_agrupados) {
		this.total_agrupados = total_agrupados;
	}

	public Long getNum_agrupado() {
		return num_agrupado;
	}

	public void setNum_agrupado(Long num_agrupado) {
		this.num_agrupado = num_agrupado;
	}

	public Long getNum_rechazo_banco() {
		return num_rechazo_banco;
	}

	public void setNum_rechazo_banco(Long num_rechazo_banco) {
		this.num_rechazo_banco = num_rechazo_banco;
	}

	public BigDecimal getTotal_rechazo_banco() {
		return total_rechazo_banco;
	}

	public void setTotal_rechazo_banco(BigDecimal total_rechazo_banco) {
		this.total_rechazo_banco = total_rechazo_banco;
	}

	public String toString() {
		return new Gson().toJson(this);
	}
}