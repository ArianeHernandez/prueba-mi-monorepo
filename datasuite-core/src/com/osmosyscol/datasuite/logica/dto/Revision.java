package com.osmosyscol.datasuite.logica.dto;

public class Revision {
	private Integer id_revision;
	private String nombre;
	private Integer id_proceso;
	private Integer id_revision_siguiente;

	// -----------
	private Integer total_cargas = 0;
	// -----------------
	
	public Integer getId_revision() {
		return id_revision;
	}

	public void setId_revision(Integer id_revision) {
		this.id_revision = id_revision;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getId_proceso() {
		return id_proceso;
	}

	public void setId_proceso(Integer id_proceso) {
		this.id_proceso = id_proceso;
	}

	public Integer getId_revision_siguiente() {
		return id_revision_siguiente;
	}

	public void setId_revision_siguiente(Integer id_revision_siguiente) {
		this.id_revision_siguiente = id_revision_siguiente;
	}

	public Integer getTotal_cargas() {
		return total_cargas;
	}

	public void setTotal_cargas(Integer total_cargas) {
		this.total_cargas = total_cargas;
	}

}
