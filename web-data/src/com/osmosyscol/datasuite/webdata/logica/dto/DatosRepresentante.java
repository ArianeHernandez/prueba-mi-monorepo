package com.osmosyscol.datasuite.webdata.logica.dto;

import com.osmosyscol.datasuite.mein.dtos.TipoDeDocumento;


public class DatosRepresentante {
	
	private Integer id;
	private Integer idcarga;
	private String nombres;
	private String apellidos;
	private Integer tipo_identificacion;
	private TipoDeDocumento tipo_identificacion_obj;
	private String numero_identificacion;
	private String correo_electronico;
	private String fecha_expedicion;
	private String sexo; //M o F
	private String lugar_nacimiento;	//Ciudad (Departamento)
	private String fecha_nacimiento;
	private String celular;
	private Integer tipoDocCPSuite;
	
	// CAMBIOS PARA FORMULARIO DE SOLICITUD DE ENROLAMIENTO MEIN
	private Integer relacion_sociedad;
	private String nit_sociedad;
	
	public Integer getRelacion_sociedad() {
		return relacion_sociedad;
	}
	public void setRelacion_sociedad(Integer relacion_sociedad) {
		this.relacion_sociedad = relacion_sociedad;
	}
	public String getNit_sociedad() {
		return nit_sociedad;
	}
	public void setNit_sociedad(String nit_sociedad) {
		this.nit_sociedad = nit_sociedad;
	}
	// CAMBIOS PARA FORMULARIO DE SOLICITUD DE ENROLAMIENTO MEIN
	
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombre) {
		this.nombres = nombre;
	}
	
	
	public String getCorreo_electronico() {
		return correo_electronico;
	}
	public void setCorreo_electronico(String correo_electronico) {
		this.correo_electronico = correo_electronico;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdcarga() {
		return idcarga;
	}
	public void setIdcarga(Integer idcarga) {
		this.idcarga = idcarga;
	}
	public Integer getTipo_identificacion() {
		return tipo_identificacion;
	}
	public void setTipo_identificacion(Integer tipo_identificacion) {
		this.tipo_identificacion = tipo_identificacion;
	}
	public String getNumero_identificacion() {
		return numero_identificacion;
	}
	public void setNumero_identificacion(String numero_identificacion) {
		this.numero_identificacion = numero_identificacion != null ? numero_identificacion.trim() : null;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getFecha_expedicion() {
		return fecha_expedicion;
	}
	public void setFecha_expedicion(String fecha_expedicion) {
		this.fecha_expedicion = fecha_expedicion;
	}
	public String getLugar_nacimiento() {
		return lugar_nacimiento;
	}
	public void setLugar_nacimiento(String lugar_nacimiento) {
		this.lugar_nacimiento = lugar_nacimiento;
	}
	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}
	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}
	public Integer getTipoDocCPSuite() {
		return tipoDocCPSuite;
	}
	public void setTipoDocCPSuite(Integer tipoDocCPSuite) {
		this.tipoDocCPSuite = tipoDocCPSuite;
	}
	public TipoDeDocumento getTipo_identificacion_obj() {
		return tipo_identificacion_obj;
	}
	public void setTipo_identificacion_obj(TipoDeDocumento tipo_identificacion_obj) {
		this.tipo_identificacion_obj = tipo_identificacion_obj;
	}
	
	
}
