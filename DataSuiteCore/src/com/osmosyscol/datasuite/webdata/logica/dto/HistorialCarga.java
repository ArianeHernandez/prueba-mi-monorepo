package com.osmosyscol.datasuite.webdata.logica.dto;

import java.util.Date;

public class HistorialCarga {

	private Integer id_carga;
	
	private Integer id_persona;
	
	private Date fecha;
	
	private String estado;
	
	private String estado_inicial;
	
	private Integer id_carga_historia;
	
	//--------Datos calculados

	private String nombres_persona;
	
	private String apellidos_persona;
	
	private Integer peso;
	
	//----------------------------------------------
	
	public Integer getId_carga() {
		return id_carga;
	}

	public void setId_carga(Integer id_carga) {
		this.id_carga = id_carga;
	}

	public Integer getId_persona() {
		return id_persona;
	}

	public void setId_persona(Integer id_persona) {
		this.id_persona = id_persona;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getId_carga_historia() {
		return id_carga_historia;
	}

	public void setId_carga_historia(Integer id_carga_historia) {
		this.id_carga_historia = id_carga_historia;
	}

	public String getNombres_persona() {
		return nombres_persona;
	}

	public void setNombres_persona(String nombres_ersona) {
		this.nombres_persona = nombres_ersona;
	}

	public String getApellidos_persona() {
		return apellidos_persona;
	}

	public void setApellidos_persona(String apellidos_persona) {
		this.apellidos_persona = apellidos_persona;
	}

	public String getEstado_inicial() {
		return estado_inicial;
	}

	public void setEstado_inicial(String estado_inicial) {
		this.estado_inicial = estado_inicial;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	
}
