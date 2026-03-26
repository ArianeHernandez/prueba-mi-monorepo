package com.osmosyscol.datasuite.logica.dto;

public class Aplicacion {

	private String id_aplicacion;
	private String url;
	private String carpeta_plugins;

	// ----------------------

	public String getId_aplicacion() {
		return id_aplicacion;
	}

	public void setId_aplicacion(String id_aplicacion) {
		this.id_aplicacion = id_aplicacion;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCarpeta_plugins() {
		return carpeta_plugins;
	}

	public void setCarpeta_plugins(String carpeta_plugins) {
		this.carpeta_plugins = carpeta_plugins;
	}

}
