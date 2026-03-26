package com.osmosyscol.datasuite.logica.dto;

public class TipoDocumento {

	private Integer id;
	
	private String nombre;

	private String prefijo;
	
	private String id_g3a;
	
	public String getPrefijo() {
		return prefijo;
	}

	public void setPrefijo(String prefijo) {
		this.prefijo = prefijo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getId_g3a() {
		return id_g3a;
	}

	public void setId_g3a(String id_g3a) {
		this.id_g3a = id_g3a;
	}

	@Override
	public String toString() {
		return "TipoDocumento [id=" + id + ", nombre=" + nombre + ", prefijo=" + prefijo + ", id_g3a=" + id_g3a + "]";
	}

}
