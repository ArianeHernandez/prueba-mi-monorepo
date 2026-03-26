package com.osmosyscol.osmoautenticador.persistencia.dto;

public class ServicioExDto {
	
	private Integer id_servicio;
	private Integer id_servicio_ex;
	private String naturaleza;
	
	
	public String getNaturaleza() {
		return naturaleza;
	}
	public void setNaturaleza(String naturaleza) {
		this.naturaleza = naturaleza;
	}
	public Integer getId_servicio() {
		return id_servicio;
	}
	public void setId_servicio(Integer id_servicio) {
		this.id_servicio = id_servicio;
	}
	public Integer getId_servicio_ex() {
		return id_servicio_ex;
	}
	public void setId_servicio_ex(Integer id_servicio_ex) {
		this.id_servicio_ex = id_servicio_ex;
	}
	

}
