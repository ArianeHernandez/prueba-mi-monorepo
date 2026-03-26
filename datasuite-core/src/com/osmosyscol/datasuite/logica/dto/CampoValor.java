package com.osmosyscol.datasuite.logica.dto;

public class CampoValor {

	private String valor;

	private Integer id_campo;

	//-------------

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Integer getId_campo() {
		return id_campo;
	}

	public void setId_campo(Integer id_campo) {
		this.id_campo = id_campo;
	}

	@Override
	public String toString() {
		return "CampoValor [valor=" + valor + ", id_campo=" + id_campo + "]";
	}

}
