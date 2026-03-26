package com.osmosyscol.datasuite.webdata.correval.ordenespago;

import java.util.Date;

public class CargaCliente {
	
	private Integer cantidad;
	private String estado;
	private String nombre_cliente;
	private Date fecha_liberacion;

	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Date getFecha_liberacion() {
		return fecha_liberacion;
	}
	public void setFecha_liberacion(Date fecha_liberacion) {
		this.fecha_liberacion = fecha_liberacion;
	}
	public String getNombre_cliente() {
		return nombre_cliente;
	}
	public void setNombre_cliente(String nombre_cliente) {
		this.nombre_cliente = nombre_cliente;
	}
	
}
