package com.osmosyscol.datasuite.webdata.logica.dto;


/**
 * Estado de la carga
 * @author oaortizs
 */
public class Estado {

	private String nombre_interno;
	private String nombre_cliente;
	private String estado;
	
	//------------

	public String getNombre_interno() {
		return nombre_interno;
	}

	public void setNombre_interno(String nombre_interno) {
		this.nombre_interno = nombre_interno;
	}

	public String getNombre_cliente() {
		return nombre_cliente;
	}

	public void setNombre_cliente(String nombre_cliente) {
		this.nombre_cliente = nombre_cliente;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Estado [nombre_interno=" + nombre_interno + ", nombre_cliente=" + nombre_cliente + ", estado=" + estado + "]";
	}

}
