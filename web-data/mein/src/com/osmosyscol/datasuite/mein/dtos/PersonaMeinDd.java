package com.osmosyscol.datasuite.mein.dtos;

import java.util.Date;

public class PersonaMeinDd {
	
	private Integer id;
	private Integer idcarga;
	private ObjGenerico tipo_identificacion;
	private String identificacion;
	private Date fecha_expedicion_doc;
//	private String fecha_expedicion_doc_string;
	private String nombre_completo;
	private String correo;
	private String celular;
	private String telefono;
	private String direccion;
	private String tarjeta_profesional;
//	private Integer id_persona_mein;
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
	public ObjGenerico getTipo_identificacion() {
		return tipo_identificacion;
	}
	public void setTipo_identificacion(ObjGenerico tipo_identificacion) {
		this.tipo_identificacion = tipo_identificacion;
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public Date getFecha_expedicion_doc() {
		return fecha_expedicion_doc;
	}
	public void setFecha_expedicion_doc(Date fecha_expedicion_doc) {
		this.fecha_expedicion_doc = fecha_expedicion_doc;
	}
	public String getNombre_completo() {
		return nombre_completo;
	}
	public void setNombre_completo(String nombre_completo) {
		this.nombre_completo = nombre_completo;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTarjeta_profesional() {
		return tarjeta_profesional;
	}
	public void setTarjeta_profesional(String tarjeta_profesional) {
		this.tarjeta_profesional = tarjeta_profesional;
	}
	
	
}