package com.osmosyscol.datasuite.logica.dto;

public class Menu {

	private String tipo;
	private String icono;
	private String titulo;
	private String direccion;
	private Boolean autorizado;
	private String accion;
	private String aplicacion;
	private Integer id_menu;
	private Integer servicio;
	private Integer id_padre;
	private Integer id_tipo_proceso;
	private String valida_proceso;

	// -----------------------------------------------------

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Boolean getAutorizado() {
		return autorizado;
	}

	public void setAutorizado(Boolean autorizado) {
		this.autorizado = autorizado;
	}

	public String getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}

	public Integer getId_menu() {
		return id_menu;
	}

	public void setId_menu(Integer id_menu) {
		this.id_menu = id_menu;
	}

	public Integer getServicio() {
		return servicio;
	}

	public void setServicio(Integer servicio) {
		this.servicio = servicio;
	}

	public Integer getId_padre() {
		return id_padre;
	}

	public void setId_padre(Integer id_padre) {
		this.id_padre = id_padre;
	}

	public Integer getId_tipo_proceso() {
		return id_tipo_proceso;
	}

	public void setId_tipo_proceso(Integer id_tipo_proceso) {
		this.id_tipo_proceso = id_tipo_proceso;
	}

	public String getValida_proceso() {
		return valida_proceso;
	}

	public void setValida_proceso(String validarProceso) {
		this.valida_proceso = validarProceso;
	}

}
