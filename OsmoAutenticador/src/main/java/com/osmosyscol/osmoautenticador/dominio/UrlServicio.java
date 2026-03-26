package com.osmosyscol.osmoautenticador.dominio;

public class UrlServicio {

	public static final String NORMAL = "N";
	public static final String PRINCIPAL = "P";

	// ---

	private Servicio servicio;
	private String url;
	private String tipo;

	// ---

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
