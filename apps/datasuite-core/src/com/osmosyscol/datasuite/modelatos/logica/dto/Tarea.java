package com.osmosyscol.datasuite.modelatos.logica.dto;

import java.util.Date;

public class Tarea {
	
	private Integer id_tarea;
	
	private String nombre;
	
	private Integer intervalo_ejecucion;
	
	private Integer tipo_intervalo;
	
	private String end_point;

	private Date fecha_inicio;

	private String activar = "N";
	
	//--------------------------------------------------------------------------------------------
	
	public Integer getId_tarea() {
		return id_tarea;
	}

	public void setId_tarea(Integer idProceso) {
		id_tarea = idProceso;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getIntervalo_ejecucion() {
		return intervalo_ejecucion;
	}

	public void setIntervalo_ejecucion(Integer intervaloEjecucion) {
		intervalo_ejecucion = intervaloEjecucion;
	}

	public String getEnd_point() {
		return end_point;
	}

	public void setEnd_point(String endPoint) {
		this.end_point = endPoint;
	}

	public Integer getTipo_intervalo() {
		return tipo_intervalo;
	}

	public void setTipo_intervalo(Integer tipoIntervalo) {
		tipo_intervalo = tipoIntervalo;
	}

	public Date getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(Date fechaInicio) {
		fecha_inicio = fechaInicio;
	}

	public String getActivar() {
		return activar;
	}

	public void setActivar(String ejecutar) {
		this.activar = ejecutar;
	}
	
}
