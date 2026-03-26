package com.osmosyscol.datasuite.webdata.correval.cargueplano.dto;

import java.math.BigDecimal;

import com.osmosyscol.datasuite.logica.constantes.Constantes;

public class Retiro {

	private String nombre_beneficiario;

	private String tipo_documento_beneficiario;

	private Long identificacion_beneficiario;

	private String cuenta_beneficiario;

	private Long tipo_cuenta_beneficiario;

	private String tipo_cuenta_beneficiario_banco;
	
	private String correo_beneficiario;

	private String estado = Constantes.CARGA_ESTADO_BORRADOR;

	private BigDecimal valor;

	private Integer id_carga;

	private String cod_banco;

	private Integer id_cliente;

	private String observacion;

	// ----

	public String getTipo_documento_beneficiario() {
		return tipo_documento_beneficiario;
	}

	public String getCorreo_beneficiario() {
		return correo_beneficiario;
	}

	public void setCorreo_beneficiario(String correo_beneficiario) {
		this.correo_beneficiario = correo_beneficiario;
	}

	public void setTipo_documento_beneficiario(String tipo_documento_beneficiario) {
		this.tipo_documento_beneficiario = tipo_documento_beneficiario;
	}

	public String getNombre_beneficiario() {
		return nombre_beneficiario;
	}

	public void setNombre_beneficiario(String nombre_beneficiario) {
		this.nombre_beneficiario = nombre_beneficiario;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getId_carga() {
		return id_carga;
	}

	public void setId_carga(Integer id_carga) {
		this.id_carga = id_carga;
	}

	public Integer getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}

	public Long getIdentificacion_beneficiario() {
		return identificacion_beneficiario;
	}

	public void setIdentificacion_beneficiario(Long identificacion_beneficiario) {
		this.identificacion_beneficiario = identificacion_beneficiario;
	}

	public String getCuenta_beneficiario() {
		return cuenta_beneficiario;
	}

	public void setCuenta_beneficiario(String cuenta_beneficiario) {
		this.cuenta_beneficiario = null;
		if (cuenta_beneficiario != null && cuenta_beneficiario.matches("[0-9]*")) {
			this.cuenta_beneficiario = cuenta_beneficiario;
		}
	}

	public Long getTipo_cuenta_beneficiario() {
		return tipo_cuenta_beneficiario;
	}

	public void setTipo_cuenta_beneficiario(Long tipo_cuenta_beneficiario) {
		this.tipo_cuenta_beneficiario = tipo_cuenta_beneficiario;
	}

	public String getCod_banco() {
		return cod_banco;
	}

	public void setCod_banco(String cod_banco) {
		this.cod_banco = cod_banco;
	}

	public String getTipo_cuenta_beneficiario_banco() {
		return tipo_cuenta_beneficiario_banco;
	}

	public void setTipo_cuenta_beneficiario_banco(String tipo_cuenta_beneficiario_occidente) {
		this.tipo_cuenta_beneficiario_banco = tipo_cuenta_beneficiario_occidente;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getObservacion() {
		return observacion;
	}

}
