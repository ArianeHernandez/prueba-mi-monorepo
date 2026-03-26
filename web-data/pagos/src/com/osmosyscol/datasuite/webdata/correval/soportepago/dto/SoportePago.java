package com.osmosyscol.datasuite.webdata.correval.soportepago.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.osmosyscol.commons.utils.JavaToXML;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.commons.utils.XMLFormat;

public class SoportePago {

	private Integer id_carga;
	private Integer idRetiro;
	private String estadoPago;
	private String nombreOriginador;
	private Date fecha;
	private String concepto;
	private String monto;
	private String nombreDestinatario;
	private String identificacionDestinatario;
	private String bancoDestino;
	private String tipoCuenta;
	private String cuentaDestino;

	// ----------------------------------------------------

	public Integer getId_carga() {
		return id_carga;
	}

	public void setId_carga(Integer id_carga) {
		this.id_carga = id_carga;
	}

	public Integer getIdRetiro() {
		return idRetiro;
	}

	public void setIdRetiro(Integer idRetiro) {
		this.idRetiro = idRetiro;
	}

	public String getEstadoPago() {
		return StringUtils.upperCase(estadoPago);
	}

	public void setEstadoPago(String estadoPago) {
		this.estadoPago = estadoPago;
	}

	public String getNombreOriginador() {
		return StringUtils.upperCase(nombreOriginador);
	}

	public void setNombreOriginador(String nombreOriginador) {
		this.nombreOriginador = nombreOriginador;
	}

	public Date getFecha() {
		return fecha;
	}

	public String getFechaFormato() {

		if (fecha == null) {
			return "";
		}

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		return sdf.format(fecha);
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getConcepto() {
		return StringUtils.upperCase(concepto);
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public String getNombreDestinatario() {
		return StringUtils.upperCase(nombreDestinatario);
	}

	public void setNombreDestinatario(String nombreDestinatario) {
		this.nombreDestinatario = nombreDestinatario;
	}

	public String getIdentificacionDestinatario() {
		return StringUtils.upperCase(identificacionDestinatario);
	}

	public void setIdentificacionDestinatario(String identificacionDestinatario) {
		this.identificacionDestinatario = identificacionDestinatario;
	}

	public String getBancoDestino() {
		return StringUtils.upperCase(bancoDestino);
	}

	public void setBancoDestino(String bancoDestino) {
		this.bancoDestino = bancoDestino;
	}

	public String getTipoCuenta() {
		return StringUtils.upperCase(tipoCuenta);
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public String getCuentaDestino() {
		return StringUtils.upperCase(cuentaDestino);
	}

	public void setCuentaDestino(String cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}

	// -----------------------------------------------

	@Override
	public String toString() {
		return XMLFormat.format(JavaToXML.exe("SoportePago", this).toString());
	}

}
