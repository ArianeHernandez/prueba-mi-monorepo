package com.osmosyscol.datasuite.modelatos.logica.dto;

import java.util.List;

public class ValidacionFormatoRequestData {

	private List<FormatoCampoValor> listadoCampos;
	private Integer id_carga;

	public List<FormatoCampoValor> getListadoCampos() {
		return listadoCampos;
	}

	public void setListadoCampos(List<FormatoCampoValor> listadoCampos) {
		this.listadoCampos = listadoCampos;
	}
	
	public Integer getIdCarga() {
		return id_carga;
	}
	public void setIdCarga(Integer id_carga) {
		this.id_carga = id_carga;
	}
	
}
