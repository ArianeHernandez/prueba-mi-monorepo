package com.osmosyscol.datasuite.modelatos.logica.dto;

import java.util.List;

import com.osmosyscol.commons.utils.StringUtils;

public class Estructura {

	private Integer id_estructura;
	private Integer id_modelo;
	private String nombre;
	private String descripcion;
	private String estado;
	private String bloqueo_edicion;
	private String bloqueo_datos;

	private Integer id_campo_relacionado;

	private List<Integer> id_estructuras_relacionadas;

	private Integer id_xml;

	private Integer xpos;
	private Integer ypos;

	// ---------------------------------

	private List<Campo> campos;

	// ---------------------------------

	public List<Integer> getId_estructuras_relacionadas() {
		return id_estructuras_relacionadas;
	}

	public String getBloqueo_edicion() {
		return bloqueo_edicion;
	}

	public void setBloqueo_edicion(String bloqueo_edicion) {
		this.bloqueo_edicion = bloqueo_edicion;
	}

	public String getBloqueo_datos() {
		return bloqueo_datos;
	}

	public void setBloqueo_datos(String bloqueo_datos) {
		this.bloqueo_datos = bloqueo_datos;
	}

	public void setId_estructuras_relacionadas(List<Integer> id_estructuras_realcionadas) {
		this.id_estructuras_relacionadas = id_estructuras_realcionadas;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getId_estructura() {
		return id_estructura;
	}

	public void setId_estructura(Integer id_estructura) {
		this.id_estructura = id_estructura;
	}

	public Integer getId_modelo() {
		return id_modelo;
	}

	public void setId_modelo(Integer id_modelo) {
		this.id_modelo = id_modelo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre.substring(0, 1).toUpperCase() + nombre.substring(1);
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getId_campo_relacionado() {
		return id_campo_relacionado;
	}

	public void setId_campo_relacionado(Integer id_campo_relacionado) {
		this.id_campo_relacionado = id_campo_relacionado;
	}

	public String getNombreFisico() {
		if (nombre != null) {
			return StringUtils.replace(StringUtils.replace(nombre, " ", "_"), "-", "_");
		}
		return null;
	}

	public Integer getId_xml() {
		return id_xml;
	}

	public void setId_xml(Integer id_xml) {
		this.id_xml = id_xml;
	}

	public Integer getXpos() {
		return xpos;
	}

	public void setXpos(Integer xpos) {
		this.xpos = xpos;
	}

	public Integer getYpos() {
		return ypos;
	}

	public void setYpos(Integer ypos) {
		this.ypos = ypos;
	}

	/**
	 * @return the campos
	 */
	public List<Campo> getCampos() {
		return campos;
	}

	/**
	 * @param campos
	 *            the campos to set
	 */
	public void setCampos(List<Campo> campos) {
		this.campos = campos;
	}

	@Override
	public String toString() {
		return "Estructura [id_estructura=" + id_estructura + ", nombre=" + nombre + ", estado=" + estado + "]";
	}

}
