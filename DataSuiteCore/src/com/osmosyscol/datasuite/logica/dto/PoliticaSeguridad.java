package com.osmosyscol.datasuite.logica.dto;

public class PoliticaSeguridad {
	
	private Integer id_politica_seguridad;
	private String nombre;
	private String descripcion;
	private String mensaje_error;
	private String clase;
	private String tipo_dato;
	private String valor;
	private String valor_usuario;
	
	public Integer getId_politica_seguridad() {
		return id_politica_seguridad;
	}
	public void setId_politica_seguridad(Integer id_politica_seguridad) {
		this.id_politica_seguridad = id_politica_seguridad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getMensaje_error() {
		return mensaje_error;
	}
	public void setMensaje_error(String mensaje_error) {
		this.mensaje_error = mensaje_error;
	}
	public String getClase() {
		return clase;
	}
	public void setClase(String clase) {
		this.clase = clase;
	}
	public String getTipo_dato() {
		return tipo_dato;
	}
	public void setTipo_dato(String tipo_dato) {
		this.tipo_dato = tipo_dato;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getValor_usuario() {
		return valor_usuario;
	}
	public void setValor_usuario(String valor_usuario) {
		this.valor_usuario = valor_usuario;
	}
	
}
