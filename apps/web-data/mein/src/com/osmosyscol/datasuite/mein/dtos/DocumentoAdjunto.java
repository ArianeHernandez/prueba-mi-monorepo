package com.osmosyscol.datasuite.mein.dtos;

public class DocumentoAdjunto {
	private String url;
	private String nombreArchivo;
	private String nombreInterno;
	private String extension;
	private long tipo;
	private String descripcion;
	private long idCarga;
	private String idArchivoInterno;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public String getNombreInterno() {
		return nombreInterno;
	}
	public void setNombreInterno(String nombreInterno) {
		this.nombreInterno = nombreInterno;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public long getTipo() {
		return tipo;
	}
	public void setTipo(long tipo) {
		this.tipo = tipo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public long getIdCarga() {
		return idCarga;
	}
	public void setIdCarga(long idCarga) {
		this.idCarga = idCarga;
	}
	public String getIdArchivoInterno() {
		return idArchivoInterno;
	}
	public void setIdArchivoInterno(String idArchivoInterno) {
		this.idArchivoInterno = idArchivoInterno;
	}
	
	
}
