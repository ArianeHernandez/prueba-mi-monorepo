package com.osmosyscol.datasuite.logica.dto;

import java.util.Date;

public class Notificacion {

	
	private Integer id_notificacion;
	private String titulo;	
	private String contenido;
	private Date fecha_envio;
	private String leida;
	private Integer id_administrativo;
	private String emisor;
	private String nueva;
	private Integer numero_solicitud;
	private String nombre_proceso;

	//-----------------
	
	public Integer getId_notificacion() {
		return id_notificacion;
	}
	public void setId_notificacion(Integer idNotificacion) {
		id_notificacion = idNotificacion;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public Date getFecha_envio() {
		return fecha_envio;
	}
	public void setFecha_envio(Date fechaEnvio) {
		fecha_envio = fechaEnvio;
	}
	public String getLeida() {
		return leida;
	}
	public void setLeida(String leida) {
		this.leida = leida;
	}
	public Integer getId_administrativo() {
		return id_administrativo;
	}
	public void setId_administrativo(Integer idAdministrativo) {
		id_administrativo = idAdministrativo;
	}
	public String getEmisor() {
		return emisor;
	}
	public void setEmisor(String emisor) {
		this.emisor = emisor;
	}
	public String getNueva() {
		return nueva;
	}
	public void setNueva(String nueva) {
		this.nueva = nueva;
	}
	public Integer getNumero_solicitud() {
		return numero_solicitud;
	}
	public void setNumero_solicitud(Integer numero_solicitud) {
		this.numero_solicitud = numero_solicitud;
	}
	public String getNombre_proceso() {
		return nombre_proceso;
	}
	public void setNombre_proceso(String nombre_proceso) {
		this.nombre_proceso = nombre_proceso;
	}
	
}
