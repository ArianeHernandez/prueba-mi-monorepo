package com.osmosyscol.datasuite.webdata.logica.dto;

import java.util.ArrayList;
import java.util.List;

public class FormatoDato {

	private Integer id_formatodato;
	private Integer id_padre;
	private Integer id_elementocarga;
	private Integer id_formato_campo;
	private Integer id_estructura;
	private Integer id_campo;
	private String valor;
	private List<FormatoDato> formatoDatoList = new ArrayList<FormatoDato>();

	// -------------------

	public Integer getId_elementocarga() {
		return id_elementocarga;
	}

	public Integer getId_formatodato() {
		return id_formatodato;
	}

	public void setId_formatodato(Integer id_formatodato) {
		this.id_formatodato = id_formatodato;
	}

	public Integer getId_padre() {
		return id_padre;
	}

	public void setId_padre(Integer id_padre) {
		this.id_padre = id_padre;
	}

	public void setId_elementocarga(Integer id_elementocarga) {
		this.id_elementocarga = id_elementocarga;
	}

	public Integer getId_formato_campo() {
		return id_formato_campo;
	}

	public void setId_formato_campo(Integer id_formato_campo) {
		this.id_formato_campo = id_formato_campo;
	}

	public Integer getId_estructura() {
		return id_estructura;
	}

	public void setId_estructura(Integer id_estructura) {
		this.id_estructura = id_estructura;
	}

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

	public List<FormatoDato> getFormatoDatoList() {
		return formatoDatoList;
	}

	public void setFormatoDatoList(List<FormatoDato> formatoDatoList) {
		this.formatoDatoList = formatoDatoList;
	}

	// -------------------

}
