package com.osmosyscol.datasuite.mein.dtos;

public class Sociedaddd {
	
	private Integer id;
	private Integer idcarga;
	private PersonaMeinDd	datos_basicos;
	private PersonaMeinDd	representante_legal;
	private PersonaMeinDd	contador;
	private PersonaMeinDd	revisor_fiscal;
	private PersonaMeinDd	apoderado;
	private String objeto_social;
//	private Integer naturaleza;
	private ObjGenerico naturalezaObj;
	private Integer porcentaje_participacion;
	private DepartamentoCiudad departamento_ciudad;
	private ObjGenerico macrosectorObj;
	private ObjGenerico actividad_economica;
	private ObjGenerico tamano_empresa;
	private ObjGenerico categoria;
	private DepartamentoCiudad ciudad;//todo remover
	private Integer trabajadores_hombres;
	private Integer trabajadores_mujeres;
	private PersonaMeinDd asistente;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdcarga() {
		return idcarga;
	}
	public void setIdcarga(Integer idcarga) {
		this.idcarga = idcarga;
	}
	public PersonaMeinDd getDatos_basicos() {
		return datos_basicos;
	}
	public void setDatos_basicos(PersonaMeinDd datos_basicos) {
		this.datos_basicos = datos_basicos;
	}
	public PersonaMeinDd getRepresentante_legal() {
		return representante_legal;
	}
	public void setRepresentante_legal(PersonaMeinDd representante_legal) {
		this.representante_legal = representante_legal;
	}
	public PersonaMeinDd getContador() {
		return contador;
	}
	public void setContador(PersonaMeinDd contador) {
		this.contador = contador;
	}
	public PersonaMeinDd getRevisor_fiscal() {
		return revisor_fiscal;
	}
	public void setRevisor_fiscal(PersonaMeinDd revisor_fiscal) {
		this.revisor_fiscal = revisor_fiscal;
	}
	public PersonaMeinDd getApoderado() {
		return apoderado;
	}
	public void setApoderado(PersonaMeinDd apoderado) {
		this.apoderado = apoderado;
	}
	public String getObjeto_social() {
		return objeto_social;
	}
	public void setObjeto_social(String objeto_social) {
		this.objeto_social = objeto_social;
	}
	public ObjGenerico getNaturalezaObj() {
		return naturalezaObj;
	}
	public void setNaturalezaObj(ObjGenerico naturalezaObj) {
		this.naturalezaObj = naturalezaObj;
	}
	public Integer getPorcentaje_participacion() {
		return porcentaje_participacion;
	}
	public void setPorcentaje_participacion(Integer porcentaje_participacion) {
		this.porcentaje_participacion = porcentaje_participacion;
	}
	public DepartamentoCiudad getDepartamento_ciudad() {
		return departamento_ciudad;
	}
	public void setDepartamento_ciudad(DepartamentoCiudad departamento_ciudad) {
		this.departamento_ciudad = departamento_ciudad;
	}
	public ObjGenerico getMacrosectorObj() {
		return macrosectorObj;
	}
	public void setMacrosectorObj(ObjGenerico macrosectorObj) {
		this.macrosectorObj = macrosectorObj;
	}
	public ObjGenerico getActividad_economica() {
		return actividad_economica;
	}
	public void setActividad_economica(ObjGenerico actividad_economica) {
		this.actividad_economica = actividad_economica;
	}
	public ObjGenerico getTamano_empresa() {
		return tamano_empresa;
	}
	public void setTamano_empresa(ObjGenerico tamano_empresa) {
		this.tamano_empresa = tamano_empresa;
	}
	public ObjGenerico getCategoria() {
		return categoria;
	}
	public void setCategoria(ObjGenerico categoria) {
		this.categoria = categoria;
	}
	public DepartamentoCiudad getCiudad() {
		return ciudad;
	}
	public void setCiudad(DepartamentoCiudad ciudad) {
		this.ciudad = ciudad;
	}
	public Integer getTrabajadores_hombres() {
		return trabajadores_hombres;
	}
	public void setTrabajadores_hombres(Integer trabajadores_hombres) {
		this.trabajadores_hombres = trabajadores_hombres;
	}
	public Integer getTrabajadores_mujeres() {
		return trabajadores_mujeres;
	}
	public void setTrabajadores_mujeres(Integer trabajadores_mujeres) {
		this.trabajadores_mujeres = trabajadores_mujeres;
	}
	public PersonaMeinDd getAsistente() {
		return asistente;
	}
	public void setAsistente(PersonaMeinDd asistente) {
		this.asistente = asistente;
	}
	
	
	
}
