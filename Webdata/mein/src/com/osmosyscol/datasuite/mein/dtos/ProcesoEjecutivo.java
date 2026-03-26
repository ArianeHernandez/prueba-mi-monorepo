package com.osmosyscol.datasuite.mein.dtos;

import java.math.BigDecimal;

public class ProcesoEjecutivo {
	
	private Integer id;
	private Integer idcarga;
	private String numero_proceso;
	private String juzgado;
	private String nombre_demandante;
	private String correo_notificacion;
	private BigDecimal valor;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdcarga() {
		return idcarga;
	}
	public void setIdcarga(Integer idcarga) {
		this.idcarga = idcarga;
	}
	public String getNumero_proceso() {
		return numero_proceso;
	}
	public void setNumero_proceso(String numero_proceso) {
		this.numero_proceso = numero_proceso;
	}
	public String getJuzgado() {
		return juzgado;
	}
	public void setJuzgado(String juzgado) {
		this.juzgado = juzgado;
	}
	public String getNombre_demandante() {
		return nombre_demandante;
	}
	public void setNombre_demandante(String nombre_demandante) {
		this.nombre_demandante = nombre_demandante;
	}
	public String getCorreo_notificacion() {
		return correo_notificacion;
	}
	public void setCorreo_notificacion(String correo_notificacion) {
		this.correo_notificacion = correo_notificacion;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
}
