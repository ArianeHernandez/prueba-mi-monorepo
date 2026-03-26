package com.osmosyscol.datasuite.webdata.logica.dto;

import java.util.Date;

public class DatosUsuario {
	private Integer id;
	private Integer idcarga;
	private String tipo_usuario;
	private String nombres;
	private String apellidos;
	private Integer tipo_de_documento;
	private String tipo_de_documento_nombre;
	private String numero_de_documento;
	private Date fecha_de_nacimiento;
	private String fecha_de_nacimiento_string;
	private String cargo;
	private String direccion_oficina;
	private String telefono_oficina;
	private String celular;
	private String correo_electronico;
	
	public String getTipo_usuario() {
		return tipo_usuario;
	}
	public void setTipo_usuario(String tipo_usuario) {
		this.tipo_usuario = tipo_usuario;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombre) {
		this.nombres = nombre;
	}
	public Integer getTipo_de_documento() {
		return tipo_de_documento;
	}
	public void setTipo_de_documento(Integer tipo_de_documento) {
		this.tipo_de_documento = tipo_de_documento;
	}
	public String getNumero_de_documento() {
		return numero_de_documento;
	}
	public void setNumero_de_documento(String numero_de_documento) {
		this.numero_de_documento = numero_de_documento;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getDireccion_oficina() {
		return direccion_oficina;
	}
	public void setDireccion_oficina(String direccion_oficina) {
		this.direccion_oficina = direccion_oficina;
	}
	public String getTelefono_oficina() {
		return telefono_oficina;
	}
	public void setTelefono_oficina(String telefono_oficina) {
		this.telefono_oficina = telefono_oficina;
	}
	public String getCorreo_electronico() {
		return correo_electronico;
	}
	public void setCorreo_electronico(String correo_electronico) {
		this.correo_electronico = correo_electronico;
	}
	public String getTipo_de_documento_nombre() {
		return tipo_de_documento_nombre;
	}
	public void setTipo_de_documento_nombre(String tipo_de_documento_nombre) {
		this.tipo_de_documento_nombre = tipo_de_documento_nombre;
	}
	public Date getFecha_de_nacimiento() {
		return fecha_de_nacimiento;
	}
	public void setFecha_de_nacimiento(Date fecha_de_nacimiento) {
		this.fecha_de_nacimiento = fecha_de_nacimiento;
	}
	public String getFecha_de_nacimiento_string() {
		return fecha_de_nacimiento_string;
	}
	public void setFecha_de_nacimiento_string(String fecha_de_nacimiento_string) {
		this.fecha_de_nacimiento_string = fecha_de_nacimiento_string;
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
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}

	
}
