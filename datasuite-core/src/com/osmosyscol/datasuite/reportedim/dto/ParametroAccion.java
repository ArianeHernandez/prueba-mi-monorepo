package com.osmosyscol.datasuite.reportedim.dto;

public class ParametroAccion {

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
	private String id_reporte;
	private Integer id_navegacion;
	private Integer id_parametro_accion;

	public ParametroAccion(){
		
	}
	
	public ParametroAccion(ParametroAccion pa){
		this.nombre = (pa.getNombre()==null)?null:new String(pa.getNombre());
		this.tipo = (pa.getTipo()==null)?null:new String(pa.getTipo());
		this.encriptado = (pa.getEncriptado()==null)?null:new String(pa.getEncriptado());
		this.valor = (pa.getValor()==null)?null:new String(pa.getValor());
		this.id_reporte = (pa.getId_reporte()==null)?null:new String(pa.getId_reporte());
		this.id_navegacion = (pa.getId_navegacion()==null)?null:new Integer(pa.getId_navegacion());
		this.id_parametro_accion = (pa.getId_parametro_accion()==null)?null:new Integer(pa.getId_parametro_accion());
	}
	
	public Integer getId_navegacion() {
		return id_navegacion;
	}

	public void setId_navegacion(Integer id_navegacion) {
		this.id_navegacion = id_navegacion;
	}
	
	public Integer getId_parametro_accion() {
		return id_parametro_accion;
	}

	public void setId_parametro_accion(Integer id_parametro_accion) {
		this.id_parametro_accion = id_parametro_accion;
	}

	public String getId_reporte() {
		return id_reporte;
	}

	public void setId_reporte(String id_reporte) {
		this.id_reporte = id_reporte;
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

	@Override
	public String toString() {
		return "ParametroAccion [nombre=" + nombre + ", tipo=" + tipo
				+ ", encriptado=" + encriptado + ", valor=" + valor
				+ ", id_reporte=" + id_reporte +
				", id_parametro_accion=" + id_parametro_accion +
				", id_navegacion=" + id_navegacion +
				"]";
	}

	
	
}
