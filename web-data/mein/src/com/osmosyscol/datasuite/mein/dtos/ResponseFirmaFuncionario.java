package com.osmosyscol.datasuite.mein.dtos;

public class ResponseFirmaFuncionario {

	private String cedulaUsuario;
	private Integer ancho;
	private Integer alto;
	private String imagen;
	private String NombreCompleto;
	private String Cargo;
	private String Mensaje;
	
	public String getCedulaUsuario() {
		return cedulaUsuario;
	}
	public void setCedulaUsuario(String cedulaUsuario) {
		this.cedulaUsuario = cedulaUsuario;
	}
	public Integer getAncho() {
		return ancho;
	}
	public void setAncho(Integer ancho) {
		this.ancho = ancho;
	}
	public Integer getAlto() {
		return alto;
	}
	public void setAlto(Integer alto) {
		this.alto = alto;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getNombreCompleto() {
		return NombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.NombreCompleto = nombreCompleto;
	}
	public String getCargo() {
		return Cargo;
	}
	public void setCargo(String cargo) {
		this.Cargo = cargo;
	}
	public String getMensaje() {
		return Mensaje;
	}
	public void setMensaje(String mensaje) {
		this.Mensaje = mensaje;
	}
	
	
}
