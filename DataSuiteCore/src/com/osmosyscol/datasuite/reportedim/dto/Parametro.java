package com.osmosyscol.datasuite.reportedim.dto;

public class Parametro {

	public static final String TIPO_STRING = "string";
	public static final String TIPO_INT = "integer";
	public static final String TIPO_DATE = "date";
	public static final String TIPO_FLOAT = "float";
	public static final String TIPO_DOUBLE = "double";
	public static final String TIPO_BOOLEAN = "boolean";
	public static final String TIPO_MONEY = "money";
	
	private String nombre;
	private String tipo;
	private String encriptado; // {S,N}
	private String valor;
	private Boolean filtro = false;
	private String titulo;
	private Integer orden;
	private Integer id_parametro;
	private Integer id_reporte;
	private Integer id_accion;
	
	public Integer getId_reporte() {
		return id_reporte;
	}

	public void setId_reporte(Integer id_reporte) {
		this.id_reporte = id_reporte;
	}

	public Integer getId_accion() {
		return id_accion;
	}

	public void setId_accion(Integer id_accion) {
		this.id_accion = id_accion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEncriptado() {
		return encriptado;
	}

	public void setEncriptado(String encriptado) {
		this.encriptado = encriptado;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Boolean getFiltro() {
		return filtro;
	}

	public void setFiltro(Boolean esFiltro) {
		this.filtro = esFiltro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public void setId_parametro(Integer id_parametro) {
		this.id_parametro = id_parametro;
	}

	public Integer getId_parametro() {
		return id_parametro;
	}

	@Override
	public String toString() {
		return "Parametro [nombre=" + nombre + ", tipo=" + tipo
				+ ", encriptado=" + encriptado + ", valor=" + valor
				+ ", filtro=" + filtro + ", titulo=" + titulo + ", orden="
				+ orden + ", id_parametro=" + id_parametro + ", id_reporte="
				+ id_reporte + ", id_accion=" + id_accion + "]";
	}

	
	
}
