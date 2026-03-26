package com.osmosyscol.datasuite.logica.dto;

public class AccionRedireccion {

	private Integer id_accion_redireccion;
	private String nombre;
	private String endpoint;
	private Integer id_instancia;
	
	public Integer getId_accion_redireccion() {
		return id_accion_redireccion;
	}
	public void setId_accion_redireccion(Integer id_accion_redireccion) {
		this.id_accion_redireccion = id_accion_redireccion;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getEndpoint() {
		return endpoint;
	}
	
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	
	public Integer getId_instancia() {
		return id_instancia;
	}
	
	public void setId_instancia(Integer id_instancia) {
		this.id_instancia = id_instancia;
	}
	
}
