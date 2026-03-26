package com.osmosyscol.datasuite.modelatos.logica.dto;

import java.util.List;
import java.util.Map;

import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.constantes.Constantes;

public class Campo {

	private Integer id_campo;
	private Integer id_estructura;

	private Integer orden;

	private String nombre;

	private Integer id_tipocampo;
	private Integer id_estructurarelacionada;
	private Integer id_lista_valores;

	private String obligatorio = "N";
	private String patronvalidacion;

	private String llaveprimaria = "N";
	private String visualizacion = "N";

	private String multiplicidad = Constantes.CAMPO_MULTIPLICIDAD_UNICO;

	private String unico = "N";

	private String actualizable = "N";
	private String endpoint;

	private String tiposeleccionado;

	private String tipocampo;

	private String bloqueo_edicion;
	
	private String descripcion;
	
	private Integer id_xml;

	// -------------------------
	private List<ValorLista> valores;
	private List<Map<String, Object>> datos;

	// -------------------------------
	// --
	
	private String info_adicional;
	
	private Integer id_tipo_archivo;

	public String getBloqueo_edicion() {
		return bloqueo_edicion;
	}

	public void setBloqueo_edicion(String bloqueo_edicion) {
		this.bloqueo_edicion = bloqueo_edicion;
	}

	public String getActualizable() {
		return actualizable;
	}

	public void setActualizable(String actualizable) {
		this.actualizable = actualizable;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getTiposeleccionado() {
		return tiposeleccionado;
	}

	public void setTiposeleccionado(String tiposeleccionado) {
		this.tiposeleccionado = tiposeleccionado;
	}

	public Integer getId_campo() {
		return id_campo;
	}

	public void setId_campo(Integer id_campo) {
		this.id_campo = id_campo;
	}

	public Integer getId_estructura() {
		return id_estructura;
	}

	public void setId_estructura(Integer id_entidad) {
		this.id_estructura = id_entidad;
	}

	public Integer getId_tipocampo() {
		return id_tipocampo;
	}

	public void setId_tipocampo(Integer id_tipocampo) {
		this.id_tipocampo = id_tipocampo;
	}

	public Integer getId_estructurarelacionada() {
		return id_estructurarelacionada;
	}

	public void setId_estructurarelacionada(Integer id_entidadrelacionada) {
		this.id_estructurarelacionada = id_entidadrelacionada;
	}

	public String getObligatorio() {
		return obligatorio;
	}

	public void setObligatorio(String obligatorio) {
		this.obligatorio = obligatorio;
	}

	public String getPatronvalidacion() {
		return patronvalidacion;
	}

	public void setPatronvalidacion(String patronvalidacion) {
		this.patronvalidacion = patronvalidacion;
	}

	public String getLlaveprimaria() {
		return llaveprimaria;
	}

	public void setLlaveprimaria(String llaveprimaria) {
		this.llaveprimaria = llaveprimaria;
	}

	public String getVisualizacion() {
		return visualizacion;
	}

	public void setVisualizacion(String tipodescripcion) {
		this.visualizacion = tipodescripcion;
	}

	public String getMultiplicidad() {
		return multiplicidad;
	}

	public void setMultiplicidad(String multiplicidad) {
		this.multiplicidad = multiplicidad;
	}

	public String getUnico() {
		return unico;
	}

	public void setUnico(String unico) {
		this.unico = unico;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre == null) {
			this.nombre = null;
		} else {
			this.nombre = nombre.substring(0, 1).toUpperCase() + nombre.substring(1);
		}
	}

	public Integer getId_lista_valores() {
		return id_lista_valores;
	}

	public void setId_lista_valores(Integer id_lista_valores) {
		this.id_lista_valores = id_lista_valores;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	@Override
	public String toString() {
		return "Campo [id_campo=" + id_campo + ", , nombre=" + nombre + "]";
	}

	public List<ValorLista> getValores() {
		return valores;
	}

	public void setValores(List<ValorLista> valores) {
		this.valores = valores;
	}

	public List<Map<String, Object>> getDatos() {
		return datos;
	}

	public void setDatos(List<Map<String, Object>> datos) {
		this.datos = datos;
	}

	public String getTipocampo() {
		return tipocampo;
	}

	public void setTipocampo(String tipocampo) {
		this.tipocampo = tipocampo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getNombreFisico(){
		if (nombre != null){
			return StringUtils.replace(nombre, " ", "_");
		}
		return null;
	}

	public Integer getId_xml() {
		return id_xml;
	}

	public void setId_xml(Integer id_xml) {
		this.id_xml = id_xml;
	}

	public String getInfo_adicional() {
		return info_adicional;
	}

	public void setInfo_adicional(String info_adicional) {
		this.info_adicional = info_adicional;
	}

	public Integer getId_tipo_archivo() {
		return id_tipo_archivo;
	}

	public void setId_tipo_archivo(Integer id_tipo_archivo) {
		this.id_tipo_archivo = id_tipo_archivo;
	}
	
}
