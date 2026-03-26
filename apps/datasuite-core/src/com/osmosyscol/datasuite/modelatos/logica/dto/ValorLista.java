package com.osmosyscol.datasuite.modelatos.logica.dto;

import java.util.Date;

import com.osmosyscol.commons.utils.StringUtils;

public class ValorLista {

	public static final String ESTADO_ACTIVO = "A";
	public static final String ESTADO_ELIMINADO = "E";

	private Integer posicion;
	private String id;
	private Object valueid;
	private String nombre;
	private String colorfondo="#FFFFFF";
	private String colorletra="#000000";
	private String estado = ESTADO_ACTIVO;

	// --------------

	public Object getValueid() {
		return valueid;
	}

	public void setValueid(Object valueid) {
		this.valueid = valueid;
		this.id = null;

		if (this.valueid != null) {

			this.id = valueid.toString();

			if (valueid instanceof Date) {
				this.id = StringUtils.toString((Date) valueid);
			}
		}

	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getColorfondo() {
		return colorfondo;
	}

	public void setColorfondo(String colorfondo) {
		this.colorfondo = colorfondo;
	}

	public String getColorletra() {
		return colorletra;
	}

	public void setColorletra(String colorletra) {
		this.colorletra = colorletra;
	}

	public Integer getPosicion() {
		return posicion;
	}

	public void setPosicion(Integer posicion) {
		this.posicion = posicion;
	}

	@Override
	public String toString() {
		return "ValorLista [posicion=" + posicion + ", id=" + id + ", nombre=" + nombre + ", estado=" + estado + "]";
	}

}
