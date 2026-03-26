package com.osmosyscol.datasuite.webdata.correval.ordenespago;

import java.math.BigDecimal;

import com.osmosyscol.commons.utils.StringUtils;

public class NotificacionBeneficiario {

	private Integer id_beneficiario;
	private Integer id_retiro;
	private String nombre1_beneficiario;
	private String nombre2_beneficiario;
	private String apellido1_beneficiario;
	private String apellido2_beneficiario;
	private String correo_beneficiario;
	private String cuenta_beneficiario;
	private String banco_beneficiario;
	private String estado_banco;
	private String nombre_completo;
	private String nombre_empresa;
	private String nombre_cliente;

	private BigDecimal valor;

	public Integer getId_beneficiario() {
		return id_beneficiario;
	}

	public void setId_beneficiario(Integer id_beneficiario) {
		this.id_beneficiario = id_beneficiario;
	}

	public Integer getId_retiro() {
		return id_retiro;
	}

	public void setId_retiro(Integer id_retiro) {
		this.id_retiro = id_retiro;
	}

	public String getNombre1_beneficiario() {
		return nombre1_beneficiario;
	}

	public void setNombre1_beneficiario(String nombre1_beneficiario) {
		this.nombre1_beneficiario = nombre1_beneficiario;
	}

	public String getNombre2_beneficiario() {
		return nombre2_beneficiario;
	}

	public void setNombre2_beneficiario(String nombre2_beneficiario) {
		this.nombre2_beneficiario = nombre2_beneficiario;
	}

	public String getApellido1_beneficiario() {
		return apellido1_beneficiario;
	}

	public void setApellido1_beneficiario(String apellido1_beneficiario) {
		this.apellido1_beneficiario = apellido1_beneficiario;
	}

	public String getApellido2_beneficiario() {
		return apellido2_beneficiario;
	}

	public void setApellido2_beneficiario(String apellido2_beneficiario) {
		this.apellido2_beneficiario = apellido2_beneficiario;
	}

	public String getCorreo_beneficiario() {
		return correo_beneficiario;
	}

	public void setCorreo_beneficiario(String correo_beneficiario) {
		this.correo_beneficiario = correo_beneficiario;
	}

	public String getCuenta_beneficiario() {

		if (cuenta_beneficiario != null && cuenta_beneficiario.length() > 4) {
			return "******" + cuenta_beneficiario.substring(cuenta_beneficiario.length() - 4);
		}

		return cuenta_beneficiario;
	}

	public void setCuenta_beneficiario(String cuenta_beneficiario) {
		this.cuenta_beneficiario = cuenta_beneficiario;
	}

	public String getBanco_beneficiario() {
		return StringUtils.trimToEmpty(banco_beneficiario).toUpperCase();
	}

	public void setBanco_beneficiario(String banco_beneficiario) {
		this.banco_beneficiario = banco_beneficiario;
	}

	public String getEstado_banco() {
		return estado_banco;
	}

	public void setEstado_banco(String estado_banco) {
		this.estado_banco = estado_banco;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getNombre_empresa() {
		return nombre_empresa;
	}

	public void setNombre_empresa(String nombre_empresa) {
		this.nombre_empresa = nombre_empresa;
	}

	public String getNombre_cliente() {
		return nombre_cliente;
	}

	public void setNombre_cliente(String nombre_cliente) {
		this.nombre_cliente = nombre_cliente;
	}

	public String getNombre_completo() {

		nombre_completo = "";

		nombre_completo += StringUtils.isBlank(nombre1_beneficiario) ? "" : " " + StringUtils.trimToEmpty(nombre1_beneficiario);
		nombre_completo += StringUtils.isBlank(nombre2_beneficiario) ? "" : " " + StringUtils.trimToEmpty(nombre2_beneficiario);
		nombre_completo += StringUtils.isBlank(apellido1_beneficiario) ? "" : " " + StringUtils.trimToEmpty(apellido1_beneficiario);
		nombre_completo += StringUtils.isBlank(apellido2_beneficiario) ? "" : " " + StringUtils.trimToEmpty(apellido2_beneficiario);

		nombre_completo = StringUtils.trimToEmpty(nombre_completo);

		return nombre_completo;
	}

	public void setNombre_completo(String nombre_completo) {
		this.nombre_completo = nombre_completo;
	}

}
