package com.osmosyscol.datasuite.modelatos.logica.dto;


public class HistorialConsultaConf {

	private Integer id_historial_consulta_conf;
	private String nombre_columna;
	private String etiqueta_columna;
	private Integer mostrar;
	private Integer excel;
	private Integer filtrar;
	private Integer posicion;
	private String tipo_dato;
	
	public Integer getId_historial_consulta_conf() {
		return id_historial_consulta_conf;
	}
	public void setId_historial_consulta_conf(Integer id_historial_consulta_conf) {
		this.id_historial_consulta_conf = id_historial_consulta_conf;
	}
	public String getNombre_columna() {
		return nombre_columna;
	}
	public void setNombre_columna(String nombre_columna) {
		this.nombre_columna = nombre_columna;
	}
	public String getEtiqueta_columna() {
		return etiqueta_columna;
	}
	public void setEtiqueta_columna(String etiqueta_columna) {
		this.etiqueta_columna = etiqueta_columna;
	}
	public Integer getMostrar() {
		return mostrar;
	}
	public void setMostrar(Integer mostrar) {
		this.mostrar = mostrar;
	}
	public Integer getExcel() {
		return excel;
	}
	public void setExcel(Integer excel) {
		this.excel = excel;
	}
	public Integer getFiltrar() {
		return filtrar;
	}
	public void setFiltrar(Integer filtrar) {
		this.filtrar = filtrar;
	}
	public Integer getPosicion() {
		return posicion;
	}
	public void setPosicion(Integer posicion) {
		this.posicion = posicion;
	}
	public String getTipo_dato() {
		return tipo_dato;
	}
	public void setTipo_dato(String tipo_dato) {
		this.tipo_dato = tipo_dato;
	}
	
}
