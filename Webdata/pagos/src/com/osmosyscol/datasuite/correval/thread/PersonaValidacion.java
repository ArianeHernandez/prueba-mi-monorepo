package com.osmosyscol.datasuite.correval.thread;

public class PersonaValidacion {

	private Integer id_estructura;
	private Integer id_registro;

	private String nombre;
	private String documento;

	public String getNombre() {
		return nombre;
	}

	public Integer getId_estructura() {
		return id_estructura;
	}

	public void setId_estructura(Integer id_estructura) {
		this.id_estructura = id_estructura;
	}

	public Integer getId_registro() {
		return id_registro;
	}

	public void setId_registro(Integer id_registro) {
		this.id_registro = id_registro;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

}
