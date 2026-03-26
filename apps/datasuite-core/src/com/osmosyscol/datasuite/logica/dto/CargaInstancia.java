package com.osmosyscol.datasuite.logica.dto;

import java.util.Date;


public class CargaInstancia {

	private Integer id_carga_instancia;
	private Integer id_carga;
	private Integer id_instancia;
	private Date fecha_llegada;
	private Date fecha_salida;
	private Integer id_persona;
	
	//Informacion calculada que no es propia de la tabla
	private String nombre_instancia;
	private String nombre_persona;
	private String apellido_persona;
	
	

	public Integer getId_carga_instancia() {
		return id_carga_instancia;
	}
	public void setId_carga_instancia(Integer id_carga_instancia) {
		this.id_carga_instancia = id_carga_instancia;
	}
	public Integer getId_persona() {
		return id_persona;
	}
	public void setId_persona(Integer id_persona) {
		this.id_persona = id_persona;
	}
	public String getNombre_persona() {
		return nombre_persona;
	}
	public void setNombre_persona(String nombre_persona) {
		this.nombre_persona = nombre_persona;
	}
	public String getApellido_persona() {
		return apellido_persona;
	}
	public void setApellido_persona(String apellido_persona) {
		this.apellido_persona = apellido_persona;
	}
	public String getNombre_instancia() {
		return nombre_instancia;
	}
	public void setNombre_instancia(String nombre_instancia) {
		this.nombre_instancia = nombre_instancia;
	}
	public Integer getId_carga() {
		return id_carga;
	}
	public void setId_carga(Integer id_carga) {
		this.id_carga = id_carga;
	}
	public Integer getId_instancia() {
		return id_instancia;
	}
	public void setId_instancia(Integer id_instancia) {
		this.id_instancia = id_instancia;
	}
	public Date getFecha_llegada() {
		return fecha_llegada;
	}
	public void setFecha_llegada(Date fecha_llegada) {
		this.fecha_llegada = fecha_llegada;
	}
	public Date getFecha_salida() {
		return fecha_salida;
	}
	public void setFecha_salida(Date fecha_salida) {
		this.fecha_salida = fecha_salida;
	}
	
	
	
}
