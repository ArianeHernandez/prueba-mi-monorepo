package com.osmosyscol.datasuite.webdata.correval.bancos.entrada;

public class RegistroEstado {

	public RegistroEstado(String id_registro, String estado) {
		super();
		this.id_registro = id_registro;
		this.estado = estado;
	}

	private String id_registro;
	private String estado;

	// ---
	public void setId_registro(String id_registro) {
		this.id_registro = id_registro;
	}

	public String getId_registro() {
		return id_registro;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEstado() {
		return estado;
	}

	public Boolean existe() {
		return id_registro != null;
	}

	public Boolean esFinal() {
		return (estado != null && estado.toUpperCase().equals("ES"));
	}

	public Boolean esNoFinal() {
		return (estado != null && !estado.toUpperCase().equals("ES"));
	}

	public Boolean esIndefinido() {
		return (estado == null);
	}

}
