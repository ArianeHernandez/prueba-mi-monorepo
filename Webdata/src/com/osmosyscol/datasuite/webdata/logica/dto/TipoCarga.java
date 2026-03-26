package com.osmosyscol.datasuite.webdata.logica.dto;

public class TipoCarga {

	
	private Integer id_tipocarga;
	
	private String nombre;

	public Integer getId_tipocarga() {
		return id_tipocarga;
	}

	public void setId_tipocarga(Integer id_tipocarga) {
		this.id_tipocarga = id_tipocarga;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "TipoCarga [id_tipocarga=" + id_tipocarga + ", nombre=" + nombre + "]";
	}
	
}
