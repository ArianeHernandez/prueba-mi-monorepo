package com.osmosyscol.datasuite.mein.dtos;

public class PasanteMLRequest {
	
	private String id;
	private String extension;
	private String nombreInterno;
	private String url;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getNombreInterno() {
		return nombreInterno;
	}
	public void setNombreInterno(String nombreInterno) {
		this.nombreInterno = nombreInterno;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
