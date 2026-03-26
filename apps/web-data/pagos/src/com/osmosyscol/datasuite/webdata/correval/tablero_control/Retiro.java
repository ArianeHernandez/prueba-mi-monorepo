package com.osmosyscol.datasuite.webdata.correval.tablero_control;

import java.math.BigDecimal;

public class Retiro {

	private Long id_retiro;
	private String estado;
	private Long id_banco_actual;
	private Long id_banco_default;
	private Long id_banco_beneficiario;
	private BigDecimal valor;
	private Long id_carga;
	private Boolean pendiente;
	private Boolean retrazado;
	private Long id_archivo;
	private String respuesta_banco;

	// -----

	public String getRespuesta_banco() {
		return respuesta_banco;
	}

	public void setRespuesta_banco(String respuesta_banco) {
		this.respuesta_banco = respuesta_banco;
	}

	public Boolean getRetrazado() {
		return retrazado;
	}

	public void setRetrazado(Boolean retrazado) {
		this.retrazado = retrazado;
	}

	public Boolean getPendiente() {
		return pendiente;
	}

	public void setPendiente(Boolean pendiente) {
		this.pendiente = pendiente;
	}

	public Long getId_carga() {
		return id_carga;
	}

	public void setId_carga(Long id_carga) {
		this.id_carga = id_carga;
	}

	public Long getId_banco_default() {
		return id_banco_default;
	}

	public void setId_banco_default(Long id_banco_default) {
		this.id_banco_default = id_banco_default;
	}

	public Long getId_retiro() {
		return id_retiro;
	}

	public void setId_retiro(Long id_retiro) {
		this.id_retiro = id_retiro;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Long getId_banco_actual() {
		return id_banco_actual;
	}

	public void setId_banco_actual(Long id_banco) {
		this.id_banco_actual = id_banco;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public void setId_banco_beneficiario(Long id_banco_beneficiario) {
		this.id_banco_beneficiario = id_banco_beneficiario;
	}

	public Long getId_banco_beneficiario() {
		return id_banco_beneficiario;
	}

	public Long getId_archivo() {
		return id_archivo;
	}

	public void setId_archivo(Long id_archivo) {
		this.id_archivo = id_archivo;
	}

	// -----

	public String toString() {
		return "Retiro [ id_retiro: " + id_retiro + " estado: " + estado + " id_banco_actual: " + id_banco_actual + " id_banco_default:" + id_banco_default + " valor:" + valor + " pendiente:" + pendiente + " ]";
	}

}
