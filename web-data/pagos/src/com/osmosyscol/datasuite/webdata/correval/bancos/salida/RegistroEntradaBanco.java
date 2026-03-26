package com.osmosyscol.datasuite.webdata.correval.bancos.salida;

import java.math.BigDecimal;

import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class RegistroEntradaBanco {

	private String id_carga;
	private String tipo_identificacion_beneficiario;
	private Long num_identificacion_beneficiario;
	private String nombre_beneficiario;
	private String cod_banco_beneficiario;
	private String tipo_cuenta_beneficiario;
	private String num_cuenta_beneficiario;
	private BigDecimal valor;
	private String consecutivo_interno;
	private String digito_verificacion;
	private String direccion_beneficiario;
	private String observacion;
	private String cod_negocio_cliente;
	private String cod_producto_cliente;
	private String cod_cuenta_cliente;

	// -------------------------------------------

	public String getCod_negocio_cliente() {
		return cod_negocio_cliente;
	}

	public void setCod_negocio_cliente(String cod_negocio_cliente) {
		this.cod_negocio_cliente = cod_negocio_cliente;
	}

	public String getCod_producto_cliente() {
		return cod_producto_cliente;
	}

	public void setCod_producto_cliente(String cod_producto_cliente) {
		this.cod_producto_cliente = cod_producto_cliente;
	}

	public String getCod_cuenta_cliente() {
		return cod_cuenta_cliente;
	}

	public void setCod_cuenta_cliente(String cod_cuenta_cliente) {
		this.cod_cuenta_cliente = cod_cuenta_cliente;
	}

	public String getId_carga() {
		return id_carga;
	}

	public void setId_carga(String id_carga) {
		this.id_carga = id_carga;
	}

	public String getObservacion() {
		return StringUtils.trim(observacion);
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getTipo_identificacion_beneficiario() {
		return StringUtils.trim(tipo_identificacion_beneficiario);
	}

	public void setTipo_identificacion_beneficiario(String tipo_identificacion_beneficiario) {
		this.tipo_identificacion_beneficiario = tipo_identificacion_beneficiario;
	}

	public Long getNum_identificacion_beneficiario() {
		return num_identificacion_beneficiario;
	}

	public void setNum_identificacion_beneficiario(Long num_identificacion_beneficiario) {
		this.num_identificacion_beneficiario = num_identificacion_beneficiario;
	}

	public String getNombre_beneficiario() {

		return StringUtils.trim(nombre_beneficiario);
	}

	public void setNombre_beneficiario(String nombre_beneficiario) {
		this.nombre_beneficiario = nombre_beneficiario;
	}

	public String getCod_banco_beneficiario() {
		return StringUtils.trim(cod_banco_beneficiario);
	}

	public void setCod_banco_beneficiario(String cod_banco_beneficiario) {
		this.cod_banco_beneficiario = cod_banco_beneficiario;
	}

	public String getTipo_cuenta_beneficiario() {
		return tipo_cuenta_beneficiario;
	}

	public void setTipo_cuenta_beneficiario(String tipo_cuenta_beneficiario) {
		this.tipo_cuenta_beneficiario = tipo_cuenta_beneficiario;
	}

	public String getNum_cuenta_beneficiario() {
		return num_cuenta_beneficiario;
	}

	public void setNum_cuenta_beneficiario(String num_cuenta_beneficiario) {
		this.num_cuenta_beneficiario = StringUtils.quitarCaracteresEspeciales(num_cuenta_beneficiario, "0123456789", null);
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getConsecutivo_interno() {
		return StringUtils.trim(consecutivo_interno);
	}

	public void setConsecutivo_interno(String consecutivo_interno) {
		this.consecutivo_interno = consecutivo_interno;
	}

	public String getDigito_verificacion() {

		if (StringUtils.esVacio(digito_verificacion) && num_identificacion_beneficiario != null) {
			digito_verificacion = "" + StringUtils.calcularDigitoVerificacion("" + num_identificacion_beneficiario);

		}

		return digito_verificacion;
	}

	public void setDigito_verificacion(String digito_verificacion) {
		this.digito_verificacion = digito_verificacion;
	}

	@Override
	public String toString() {
		return "RegistroEntradaBanco [tipo_identificacion_beneficiario=" + tipo_identificacion_beneficiario + ", num_identificacion_beneficiario=" + num_identificacion_beneficiario + ", nombre_beneficiario=" + nombre_beneficiario + ", cod_banco_beneficiario=" + cod_banco_beneficiario + ", tipo_cuenta_beneficiario=" + tipo_cuenta_beneficiario + ", num_cuenta_beneficiario=" + num_cuenta_beneficiario + ", valor=" + valor + ", consecutivo_interno=" + consecutivo_interno + "]";
	}

	public String getDireccion_beneficiario() {
		if (StringUtils.esVacio(direccion_beneficiario)) {
			direccion_beneficiario = ParametrosInicio.getProperty("correval.direccion");

		}

		return direccion_beneficiario;
	}

	public void setDireccion_beneficiario(String direccion_beneficiario) {
		this.direccion_beneficiario = direccion_beneficiario;
	}

}
