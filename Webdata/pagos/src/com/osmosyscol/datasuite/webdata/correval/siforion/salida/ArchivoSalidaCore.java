package com.osmosyscol.datasuite.webdata.correval.siforion.salida;

import java.util.Date;


public class ArchivoSalidaCore {

	//Tipo de archivo
	private Integer id_archivo;
	private Date fechaCreacion;
	private String tipoArchivo;
	private String nombreGenerador;
	
	public Integer getId_archivo() {
		return id_archivo;
	}
	public void setId_archivo(Integer id_archivo) {
		this.id_archivo = id_archivo;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getTipoArchivo() {
		return tipoArchivo;
	}
	public void setTipoArchivo(String tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
	}
	public String getNombreGenerador() {
		return nombreGenerador;
	}
	public void setNombreGenerador(String nombreGenerador) {
		this.nombreGenerador = nombreGenerador;
	}

	
	
}