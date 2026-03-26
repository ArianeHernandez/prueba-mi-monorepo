package com.osmosyscol.datasuite.webdata.logica.dto;

public class ValorListaDinamicaCampo {
	
	private Integer id_campo;
	
	private String valor;
	
	private Integer id_carga;
	
	//-------------

	public Integer getId_campo() {
		return id_campo;
	}

	public void setId_campo(Integer id_campo) {
		this.id_campo = id_campo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Integer getId_carga() {
		return id_carga;
	}

	public void setId_carga(Integer id_carga) {
		this.id_carga = id_carga;
	}

}
