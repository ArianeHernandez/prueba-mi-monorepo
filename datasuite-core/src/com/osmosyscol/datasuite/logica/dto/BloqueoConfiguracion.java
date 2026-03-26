package com.osmosyscol.datasuite.logica.dto;

public class BloqueoConfiguracion {

	private Integer id_usuario;
	private Integer numero_intentos;
	private Integer tiempo_intentos;

	public Integer getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}

	public Integer getNumero_intentos() {
		return numero_intentos;
	}

	public void setNumero_intentos(Integer numero_intentos) {
		this.numero_intentos = numero_intentos;
	}

	public Integer getTiempo_intentos() {
		return tiempo_intentos;
	}

	public void setTiempo_intentos(Integer tiempo_intentos) {
		this.tiempo_intentos = tiempo_intentos;
	}

}
