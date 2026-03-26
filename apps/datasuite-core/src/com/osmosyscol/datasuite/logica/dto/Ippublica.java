package com.osmosyscol.datasuite.logica.dto;

public class Ippublica {

	private Integer id_ippublica;
	private String desde;
	private String hasta;
	private Integer id_usuario;
	private String estado;

	public Integer getId_ippublica() {
		return id_ippublica;
	}

	public void setId_ippublica(Integer idIppublica) {
		id_ippublica = idIppublica;
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

	public Integer getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Integer idUsuario) {
		id_usuario = idUsuario;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
