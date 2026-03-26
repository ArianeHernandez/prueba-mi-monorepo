package com.osmosyscol.datasuite.modelatos.logica.dto;

public class ParametroCarga {

	private Integer id_parametro_carga;
	private Integer id_formato;
	private String nombre;

	// ----------

	public Integer getId_parametro_carga() {
		return id_parametro_carga;
	}

	public void setId_parametro_carga(Integer id_parametro_carga) {
		this.id_parametro_carga = id_parametro_carga;
	}

	public Integer getId_formato() {
		return id_formato;
	}

	public void setId_formato(Integer id_formato) {
		this.id_formato = id_formato;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
