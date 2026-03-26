package com.osmosyscol.osmoautenticador.dominio;

import java.util.ArrayList;
import java.util.List;

public class Servicio {
	private String nombre;
	private Integer id_servicio;
	private List<UrlServicio> urls = new ArrayList<UrlServicio>();

	// --

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getId_servicio() {
		return id_servicio;
	}

	public void setId_servicio(Integer id_servicio) {
		this.id_servicio = id_servicio;
	}

	public List<UrlServicio> getUrls() {
		return urls;
	}

	public void setUrls(List<UrlServicio> urls) {
		this.urls = urls;
	}

}
