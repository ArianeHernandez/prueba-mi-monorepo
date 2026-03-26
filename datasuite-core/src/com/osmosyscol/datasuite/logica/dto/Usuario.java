package com.osmosyscol.datasuite.logica.dto;

public class Usuario {

	private Integer id_usuario;
	private Integer id_persona;
	private String activo;
	private String observacion;
	private String uso_firma;
	private String uso_firma_liberador;
	private String uso_firma_revisor;
	private String uso_firma_preparador;
	private String uso_ftp_usuario;

	// --

	private String nombre;
	private String login;
	private String tipo_persona;
	private String apellido;
	private String identificacion;
	private String tipo_documento;
	// -------------------------
	
	private String codigo_comercial;

	public String getUso_firma() {
		return uso_firma;
	}

	public void setUso_firma(String usoFirma) {
		uso_firma = usoFirma;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String documento) {
		this.identificacion = documento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Integer getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}

	public Integer getId_persona() {
		return id_persona;
	}

	public void setId_persona(Integer id_persona) {
		this.id_persona = id_persona;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getTipo_persona() {
		return tipo_persona;
	}

	public void setTipo_persona(String tipoPersona) {
		tipo_persona = tipoPersona;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTipo_documento() {
		return tipo_documento;
	}

	public void setTipo_documento(String tipo_documento) {
		this.tipo_documento = tipo_documento;
	}

	public String getUso_firma_liberador() {
		return uso_firma_liberador;
	}

	public String getUso_firma_preparador() {
		return uso_firma_preparador;
	}

	public String getUso_firma_revisor() {
		return uso_firma_revisor;
	}

	public void setUso_firma_liberador(String uso_firma_liberador) {
		this.uso_firma_liberador = uso_firma_liberador;
	}

	public void setUso_firma_preparador(String uso_firma_preparador) {
		this.uso_firma_preparador = uso_firma_preparador;
	}

	public void setUso_firma_revisor(String uso_firma_revisor) {
		this.uso_firma_revisor = uso_firma_revisor;
	}

	public String getUso_ftp_usuario() {
		return uso_ftp_usuario;
	}

	public void setUso_ftp_usuario(String uso_ftp_usuario) {
		this.uso_ftp_usuario = uso_ftp_usuario;
	}

	@Override
	public String toString() {
		return "Usuario [id_usuario=" + id_usuario + ", id_persona=" + id_persona + ", activo=" + activo + ", observacion=" + observacion
				+ ", uso_firma=" + uso_firma + ", uso_firma_liberador=" + uso_firma_liberador + ", uso_firma_revisor=" + uso_firma_revisor
				+ ", uso_firma_preparador=" + uso_firma_preparador + ", uso_ftp_usuario=" + uso_ftp_usuario + ", nombre=" + nombre
				+ ", login=" + login + ", tipo_persona=" + tipo_persona + ", apellido=" + apellido + ", identificacion=" + identificacion
				+ ", tipo_documento=" + tipo_documento + "]";
	}

	public String getCodigo_comercial() {
		return codigo_comercial;
	}

	public void setCodigo_comercial(String codigo_comercial) {
		this.codigo_comercial = codigo_comercial;
	}


}
