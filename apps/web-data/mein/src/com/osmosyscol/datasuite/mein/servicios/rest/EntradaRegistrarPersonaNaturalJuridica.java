package com.osmosyscol.datasuite.mein.servicios.rest;

public class EntradaRegistrarPersonaNaturalJuridica {
	private PersonaNaturalJuridica PersonaNaturalJuridica;
	private String Usuario;
	
	public EntradaRegistrarPersonaNaturalJuridica() {
		super();
	}
	
	public EntradaRegistrarPersonaNaturalJuridica(PersonaNaturalJuridica personaNaturalJuridica, String usuario) {
		super();
		this.PersonaNaturalJuridica = personaNaturalJuridica;
		this.Usuario = usuario;
	}
	public PersonaNaturalJuridica getPersonaNaturalJuridica() {
		return PersonaNaturalJuridica;
	}
	public void setPersonaNaturalJuridica(
			PersonaNaturalJuridica personaNaturalJuridica) {
		this.PersonaNaturalJuridica = personaNaturalJuridica;
	}
	public String getUsuario() {
		return Usuario;
	}
	public void setUsuario(String usuario) {
		this.Usuario = usuario;
	}
}
