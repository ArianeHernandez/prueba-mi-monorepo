package com.osmosyscol.datasuite.mein.servicios.rest;

public class RespuestaRegistrarPersonaNaturalJuridica {
	private Integer estado;
	private String Mensaje;
	private Persona[] Personas;
	private Integer TotalRegistros;
	
	public RespuestaRegistrarPersonaNaturalJuridica() {
		super();
	}

	public Integer getEstado() { 
		return estado;
	}

	public void setEstado(Integer estado) { 
		this.estado = estado;
	}

	public String getMensaje() {
		return Mensaje;
	}

	public void setMensaje(String mensaje) {
		Mensaje = mensaje;
	}

	public Persona[] getPersonas() {
		return Personas;
	}

	public void setPersonas(Persona[] personas) {
		Personas = personas;
	}

	public Integer getTotalRegistros() {
		return TotalRegistros;
	}

	public void setTotalRegistros(Integer totalRegistros) {
		TotalRegistros = totalRegistros;
	}
		
}
