package com.osmosyscol.commons.logging.dto;

import java.lang.reflect.Field;
import java.util.Date;

public class RegistroLogG3A {

	private String idCliente;
	private String idFilial;
	private String numeroCuenta;
	private int idSistema;

	// transaccion
	private String idConsecutivo;
	private Date fechaRegistro;
	private Date horaRegistro;
	private String idOperacion;
	private String canal;
	private double costoTransaccion;
	private String idUsuario;

	// transaccionCrm
	private String tipoTransaccion;
	private String modulo;
	private String functionalidad;
	private String direccionIp;

	// otros
	private String url;
	private String estado;
	private String descripcion;

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdFilial() {
		return idFilial;
	}

	public void setIdFilial(String idFilial) {
		this.idFilial = idFilial;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public int getIdSistema() {
		return idSistema;
	}

	public void setIdSistema(int idSistema) {
		this.idSistema = idSistema;
	}

	public String getIdConsecutivo() {
		return idConsecutivo;
	}

	public void setIdConsecutivo(String idConsecutivo) {
		this.idConsecutivo = idConsecutivo;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Date getHoraRegistro() {
		return horaRegistro;
	}

	public void setHoraRegistro(Date horaRegistro) {
		this.horaRegistro = horaRegistro;
	}

	public String getIdOperacion() {
		return idOperacion;
	}

	public void setIdOperacion(String idOperacion) {
		this.idOperacion = idOperacion;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public double getCostoTransaccion() {
		return costoTransaccion;
	}

	public void setCostoTransaccion(double costoTransaccion) {
		this.costoTransaccion = costoTransaccion;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getTipoTransaccion() {
		return tipoTransaccion;
	}

	public void setTipoTransaccion(String tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}

	public String getModulo() {
		return modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

	public String getFunctionalidad() {
		return functionalidad;
	}

	public void setFunctionalidad(String functionalidad) {
		this.functionalidad = functionalidad;
	}

	public String getDireccionIp() {
		return direccionIp;
	}

	public void setDireccionIp(String direccionIp) {
		this.direccionIp = direccionIp;
	}

	public String toString() {
		Class<? extends RegistroLogG3A> clase = this.getClass();
		Field[] fields = clase.getDeclaredFields();
		StringBuffer st = new StringBuffer("[" + this.getClass().getSimpleName() + ": ");

		for (Field field : fields) {
			try {
				st.append(field.getName() + "=" + field.get(this));
			} catch (Exception e) {
			}
		}

		st.append("]");

		return st.toString();
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}


}
