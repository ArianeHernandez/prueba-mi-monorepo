package com.osmosyscol.datasuite.auditoria.dto;

import java.util.Date;

import com.osmosyscol.commons.utils.StringUtils;

public class Auditoria {

	private Integer id_auditoria;
	private Date fecha_accion;
	private String codigo_accion;
	private String ip;
	private String usuario;
	private Integer target;
	private String nuevo_valor;
	private String estado;
	private String descripcion;
	private String nombre_usuario;
	
	
	
	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getId_auditoria() {
		return id_auditoria;
	}

	public void setId_auditoria(Integer id_auditoria) {
		this.id_auditoria = id_auditoria;
	}

	public Date getFecha_accion() {
		return fecha_accion;
	}

	public void setFecha_accion(Date fecha_accion) {
		this.fecha_accion = fecha_accion;
	}

	public String getCodigo_accion() {
		return codigo_accion;
	}

	public void setCodigo_accion(String codigo_accion) {
		this.codigo_accion = codigo_accion;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Integer getTarget() {
		return target;
	}

	public void setTarget(Integer target) {
		this.target = target;
	}

	public String getNuevo_valor() {
		return nuevo_valor;
	}

	public void setNuevo_valor(String nuevo_valor) {
		this.nuevo_valor = nuevo_valor;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFecha_accion_str() {
		return StringUtils.toStringFormat(fecha_accion);
	}

	public void setFecha_accion_str(Date fecha_accion_str) {
	}

}
