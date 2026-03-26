package com.osmosyscol.datasuite.near.interop.postal.adjuntos;

public class WebDataInfo {
	
	public WebDataInfo(String idCarga, String entidad) {
		super();
		IdCarga = idCarga;
		Entidad = entidad;
	}
	String IdCarga;
	String Entidad;
	public String getIdCarga() {
		return IdCarga;
	}
	public void setIdCarga(String idCarga) {
		IdCarga = idCarga;
	}
	public String getEntidad() {
		return Entidad;
	}
	public void setEntidad(String entidad) {
		Entidad = entidad;
	}

}
