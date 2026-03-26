package com.osmosyscol.datasuite.logica.dto;

import com.osmosyscol.commons.utils.StringUtils;

public class ProcesoAdmin {
	private Integer id_proceso_admin;
	private Integer id_negocio;
	private Integer id_formato_entrada;
	private String nombre;
	private String nombre_negocio;
	private Integer id_tipo_proceso;

	public String getNombre_negocio() {
		return nombre_negocio;
	}

	public void setNombre_negocio(String nombre_negocio) {
		this.nombre_negocio = nombre_negocio;
	}
	
	public Integer getId_proceso_admin() {
		return id_proceso_admin;
	}

	public void setId_proceso_admin(Integer idProcesoAdmin) {
		id_proceso_admin = idProcesoAdmin;
	}

	public Integer getId_negocio() {
		return id_negocio;
	}

	public void setId_negocio(Integer idNegocio) {
		id_negocio = idNegocio;
	}

	public Integer getId_formato_entrada() {
		return id_formato_entrada;
	}

	public void setId_formato_entrada(Integer idFormatoEntrada) {
		id_formato_entrada = idFormatoEntrada;
	}

	public String getNombre() {
		return StringUtils.trim(nombre).toUpperCase();
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getId_tipo_proceso() {
		return id_tipo_proceso;
	}

	public void setId_tipo_proceso(Integer id_tipo_proceso) {
		this.id_tipo_proceso = id_tipo_proceso;
	}

}
