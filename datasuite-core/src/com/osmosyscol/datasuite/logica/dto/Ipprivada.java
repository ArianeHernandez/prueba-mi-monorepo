package com.osmosyscol.datasuite.logica.dto;

public class Ipprivada {

	private Integer id_ipprivada;
	private String desde;
	private String hasta;
	private Integer id_ippublica;

	public Integer getId_ipprivada() {
		return id_ipprivada;
	}

	public void setId_ipprivada(Integer idIpprivada) {
		id_ipprivada = idIpprivada;
	}

	public String getDesde() {
		return desde;
	}

	public void setDesde(String desde) {
		this.desde = desde;
	}

	public String getHasta() {
		return hasta;
	}

	public void setHasta(String hasta) {
		this.hasta = hasta;
	}

	public Integer getId_ippublica() {
		return id_ippublica;
	}

	public void setId_ippublica(Integer idIppublica) {
		id_ippublica = idIppublica;
	}

}