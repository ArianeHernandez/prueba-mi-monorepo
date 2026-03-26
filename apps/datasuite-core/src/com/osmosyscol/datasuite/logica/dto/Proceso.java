package com.osmosyscol.datasuite.logica.dto;

import java.util.List;

import com.osmosyscol.commons.utils.StringUtils;

public class Proceso {

	private Integer id_proceso;
	private String nombre;
	private String estado = "I";
	private Integer id_formato_salida;
	private Integer id_usuario;
	private Integer id_negocio;
	private Integer id_grupoformato;
	private Integer id_tipo_proceso;

	private Integer peso = 0;

	// -----------------
	private Integer total_cargas = 0;

	// -----------------

	public Proceso() {
	}

	public Proceso(Integer id_proceso, Integer id_usuario, Integer id_negocio, String nombre, Integer id_tipo_proceso) {
		this.id_proceso = id_proceso;
		this.id_usuario = id_usuario;
		this.id_negocio = id_negocio;
		this.nombre = nombre;
		this.id_tipo_proceso = id_tipo_proceso;
	}

	// -----------------

	public Integer getId_proceso() {
		return id_proceso;
	}

	public void setId_proceso(Integer id_proceso) {
		this.id_proceso = id_proceso;
	}

	public String getNombre() {
		return StringUtils.trim(nombre).toUpperCase();
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getId_formato_salida() {
		return id_formato_salida;
	}

	public void setId_formato_salida(Integer id_formato_salida) {
		this.id_formato_salida = id_formato_salida;
	}

	public Integer getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}

	public void setId_negocio(Integer id_negocio) {
		this.id_negocio = id_negocio;
	}

	public Integer getId_negocio() {
		return id_negocio;
	}

	public Integer getTotal_cargas() {
		return total_cargas;
	}

	public void setTotal_cargas(Integer total_cargas) {
		this.total_cargas = total_cargas;
	}

	public Integer getId_grupoformato() {
		return id_grupoformato;
	}

	public void setId_grupoformato(Integer id_grupoformato) {
		this.id_grupoformato = id_grupoformato;
	}

	public Integer getPeso() {
		return peso == null ? 0 : peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	public Integer getId_tipo_proceso() {
		return id_tipo_proceso;
	}

	public void setId_tipo_proceso(Integer id_tipo_proceso) {
		this.id_tipo_proceso = id_tipo_proceso;
	}

}
