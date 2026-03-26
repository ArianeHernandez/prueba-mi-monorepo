package com.osmosyscol.datasuite.modelatos.logica.dto;


public class HistorialConsulta {

	private Integer id_historial;
	private String consulta;
	private String descripcion;
	private String filtro;
	
	public Integer getId_historial() {
		return id_historial;
	}
	public void setId_historial(Integer id_historial) {
		this.id_historial = id_historial;
	}
	public String getConsulta() {
		return consulta;
	}
	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getFiltro() {
		return filtro;
	}
	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
}
