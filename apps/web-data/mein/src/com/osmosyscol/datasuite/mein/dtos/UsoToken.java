package com.osmosyscol.datasuite.mein.dtos;

public class UsoToken {

	private String token;
	private String tipo_documento;
	private String numero_documento;
	private String correo;
	private String celular;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTipo_documento() {
		return tipo_documento;
	}

	public void setTipo_documento(String tipo_documento) {
		this.tipo_documento = tipo_documento;
	}

	public String getNumero_documento() {
		return numero_documento;
	}

	public void setNumero_documento(String numero_documento) {
		this.numero_documento = numero_documento;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	@Override
	public String toString() {
		return "UsoToken [token=" + token + ", tipo_documento="
				+ tipo_documento + ", numero_documento=" + numero_documento
				+ ", correo=" + correo + ", celular=" + celular + "]";
	}

}
