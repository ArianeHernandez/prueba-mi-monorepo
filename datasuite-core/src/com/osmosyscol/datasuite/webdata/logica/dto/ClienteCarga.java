package com.osmosyscol.datasuite.webdata.logica.dto;


public class ClienteCarga {

	private Integer id_carga;
	private Integer id_usuario;
	private String nombre_usuario;
	private String apellido_usuario;
	
	public Integer getId_carga() {
		return id_carga;
	}
	public void setId_carga(Integer id_carga) {
		this.id_carga = id_carga;
	}
	public Integer getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getNombre_usuario() {
		return nombre_usuario;
	}
	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}
	public String getApellido_usuario() {
		return apellido_usuario;
	}
	public void setApellido_usuario(String apellido_usuario) {
		this.apellido_usuario = apellido_usuario;
	}
	

}
