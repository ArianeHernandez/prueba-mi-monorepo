/**
 * 
 */
package com.osmosyscol.osmoautenticador.dominio;

import java.util.List;

import com.osmosyscol.osmoautenticador.autenticador.Autenticador;

/**
 * @author ojcchar
 * 
 */
public class GrupoAutenticacion {

	private Integer id_grupo_autenticacion;
	private String descripcion;
	private String modo_autenticacion;
	private String modo_autorizacion;
	private List<Autenticador> autenticadores;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setAutenticadores(List<Autenticador> autenticadores) {
		this.autenticadores = autenticadores;
	}

	public List<Autenticador> getAutenticadores() {
		return autenticadores;
	}

	public void setId_grupo_autenticacion(Integer id_grupo_autenticacion) {
		this.id_grupo_autenticacion = id_grupo_autenticacion;
	}

	public Integer getId_grupo_autenticacion() {
		return id_grupo_autenticacion;
	}

	public void setModo_autenticacion(String modo_autenticacion) {
		this.modo_autenticacion = modo_autenticacion;
	}

	public String getModo_autenticacion() {
		return modo_autenticacion;
	}

	public void setModo_autorizacion(String modo_autorizacion) {
		this.modo_autorizacion = modo_autorizacion;
	}

	public String getModo_autorizacion() {
		return modo_autorizacion;
	}

}
