package com.osmosyscol.datasuite.mein.domain;

public class Representante {
	private String cargo;
	private String nombre;
	private String tipo_documento;
	private String documento;
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo_documento() {
		return tipo_documento;
	}
	public void setTipo_documento(String tipo_documento) {
		this.tipo_documento = tipo_documento;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	@Override
	public String toString() {
		return "Representante [cargo=" + cargo + ", nombre=" + nombre
				+ ", tipo_documento=" + tipo_documento + ", documento="
				+ documento + "]";
	}
	
}
