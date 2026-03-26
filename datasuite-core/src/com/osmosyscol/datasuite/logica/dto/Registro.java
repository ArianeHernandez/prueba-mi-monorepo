package com.osmosyscol.datasuite.logica.dto;

import java.util.List;

public class Registro {

	private Integer id_registro;
	
	private Boolean eliminar = false;
	
	private List<CampoValor> campos;

	public Integer getId_registro() {
		return id_registro;
	}

	public void setId_registro(Integer id_registro) {
		this.id_registro = id_registro;
	}

	public Boolean getEliminar() {
		return eliminar;
	}

	public void setEliminar(Boolean eliminar) {
		this.eliminar = eliminar;
	}

	public List<CampoValor> getCampos() {
		return campos;
	}

	public void setCampos(List<CampoValor> campos) {
		this.campos = campos;
	}

	@Override
	public String toString() {
		return "Registro [id_registro=" + id_registro + ", eliminar=" + eliminar + ", campos=" + campos + "]";
	}
	
	
}
