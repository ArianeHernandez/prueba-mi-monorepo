package com.osmosyscol.datasuite.logica.dto;


public class Franja {

	private Integer id_franja;
	private String dia;
	private Integer hora_desde;
	private Integer hora_hasta;
	private Integer id_horario;

	//----
	
	public Integer getId_franja() {
		return id_franja;
	}

	public void setId_franja(Integer idFranja) {
		id_franja = idFranja;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public Integer getHora_desde() {
		return hora_desde;
	}

	public void setHora_desde(Integer horaDesde) {
		hora_desde = horaDesde;
	}

	public Integer getHora_hasta() {
		return hora_hasta;
	}

	public void setHora_hasta(Integer horaHasta) {
		hora_hasta = horaHasta;
	}

	public Integer getId_horario() {
		return id_horario;
	}

	public void setId_horario(Integer idHorario) {
		id_horario = idHorario;
	}
	
}
