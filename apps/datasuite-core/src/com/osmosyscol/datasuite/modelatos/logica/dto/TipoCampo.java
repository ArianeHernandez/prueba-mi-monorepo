package com.osmosyscol.datasuite.modelatos.logica.dto;

public class TipoCampo {

	private Integer id_tipocampo;
	private String nombre;
	private String tipo_dato;
	private String patron_validacion;
	private String longitud;

	// --------------------

	public Integer getId_tipocampo() {
		return id_tipocampo;
	}

	public void setId_tipocampo(Integer id_tipoelemento) {
		this.id_tipocampo = id_tipoelemento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo_dato() {
		return tipo_dato;
	}

	public void setTipo_dato(String clasejava) {
		this.tipo_dato = clasejava;
	}

	public String getPatron_validacion() {
		return patron_validacion;
	}

	public void setPatron_validacion(String patronvalidacion) {
		this.patron_validacion = patronvalidacion;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String getPatron_validacionJS() {
		if(patron_validacion != null){
			return patron_validacion.replace("\\", "\\\\");
		}
		return patron_validacion;
	}

	@Override
	public String toString() {
		return "TipoCampo [id_tipocampo=" + id_tipocampo + ", nombre=" + nombre + ", tipo_dato=" + tipo_dato + ", patron_validacion=" + patron_validacion + ", longitud="
				+ longitud + "]";
	}

	// --------------------
}
