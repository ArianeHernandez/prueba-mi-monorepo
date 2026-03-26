package com.osmosyscol.datasuite.modelatos.logica.dto;

import com.osmosyscol.datasuite.logica.constantes.Constantes;

public class ListaValores {

	public static final String POR_ID = "I";
	public static final String POR_VALOR = "V";

	public static final String ASCENDENTE = "A";
	public static final String DESCENDENTE = "D";
	

	// ---

	private Integer id_lista_valores;
	private String nombre;
	private String descripcion;
	private Integer id_modelo;
	private Integer id_tipocampo;
	private String estado = "A";

	private String criterio_orden = POR_VALOR;
	private String tipo_orden = ASCENDENTE;
	
	private String c_id;
	private String c_nombre;
	private String consulta;
	private String base_datos;
	private Integer intervalo_actualizacion;
	
	private String tomar_codigo;

	// -----------

	public String getTomar_codigo() {
		return tomar_codigo;
	}

	public void setTomar_codigo(String tomar_codigo) {
		this.tomar_codigo = tomar_codigo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getId_lista_valores() {
		return id_lista_valores;
	}

	public void setId_lista_valores(Integer id_lista_valores) {
		this.id_lista_valores = id_lista_valores;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getId_modelo() {
		return id_modelo;
	}

	public void setId_modelo(Integer id_modelo) {
		this.id_modelo = id_modelo;
	}

	public Integer getId_tipocampo() {
		return id_tipocampo;
	}

	public void setId_tipocampo(Integer id_tipocampo) {
		this.id_tipocampo = id_tipocampo;
	}

	public String getCriterio_orden() {
		return criterio_orden;
	}

	public void setCriterio_orden(String criterio_orden) {
		this.criterio_orden = criterio_orden;
	}

	public String getTipo_orden() {
		return tipo_orden;
	}

	public void setTipo_orden(String tipo_orden) {
		this.tipo_orden = tipo_orden;
	}

	public String getC_id() {
		return c_id;
	}

	public void setC_id(String c_id) {
		this.c_id = c_id;
	}

	public String getC_nombre() {
		return c_nombre;
	}

	public void setC_nombre(String c_nombre) {
		this.c_nombre = c_nombre;
	}

	public String getConsulta() {
		return consulta;
	}

	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}

	public String getBase_datos() {
		return base_datos;
	}

	public void setBase_datos(String base_datos) {
		this.base_datos = base_datos;
	}

	public Integer getIntervalo_actualizacion() {
		return intervalo_actualizacion;
	}

	public void setIntervalo_actualizacion(Integer intervalo_actualizacion) {
		this.intervalo_actualizacion = intervalo_actualizacion;
	}

	@Override
	public String toString() {
		return "ListaValores [id_lista_valores=" + id_lista_valores + ", nombre=" + nombre + ", descripcion=" + descripcion + ", id_modelo=" + id_modelo + ", id_tipocampo="
				+ id_tipocampo + ", estado=" + estado + ", criterio_orden=" + criterio_orden + ", tipo_orden=" + tipo_orden + ", c_id=" + c_id + ", c_nombre=" + c_nombre
				+ ", consulta=" + consulta + ", base_datos=" + base_datos + ", intervalo_actualizacion=" + intervalo_actualizacion + "]";
	}
	
}
