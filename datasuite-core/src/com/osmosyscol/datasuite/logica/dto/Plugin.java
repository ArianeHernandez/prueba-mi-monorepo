package com.osmosyscol.datasuite.logica.dto;

import com.osmosyscol.datasuite.modelatos.logica.servicios.AplicacionServicio;

public class Plugin {
	
	private String  nombre;
	private String  accion;
	private String  id_aplicacion;
	private String  recurso;
	private String  elemento;
	private Integer orden;
	private String  plantilla;
	private String  activo;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getId_aplicacion() {
		return id_aplicacion;
	}

	public void setId_aplicacion(String id_aplicacion) {
		this.id_aplicacion = id_aplicacion;
	}

	public String getRecurso() {
		return recurso;
	}

	public void setRecurso(String recurso) {
		this.recurso = recurso;
	}

	public String getElemento() {
		return elemento;
	}

	public void setElemento(String elemento) {
		this.elemento = elemento;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public String getPlantilla() {
		return plantilla;
	}

	public void setPlantilla(String plantilla) {
		this.plantilla = plantilla;
	}
	
	public String getActivo() {
		return activo;
	}
	
	public void setActivo(String activo) {
		this.activo = activo;
	}

	public Aplicacion getAplicacion(){
		if (id_aplicacion != null){
			return AplicacionServicio.getInstance().obtenerAplicacion(id_aplicacion);
		}
		return null;
	}
	
}
