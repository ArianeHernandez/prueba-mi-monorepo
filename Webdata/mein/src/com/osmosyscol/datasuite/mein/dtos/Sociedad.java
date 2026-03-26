package com.osmosyscol.datasuite.mein.dtos;

import com.osmosyscol.datasuite.bpm.dto.ApVistaMacrosectorDto;

public class Sociedad {
	
	private Integer id;
	private Integer idcarga;
	private PersonaMein	datos_basicos;
	private PersonaMein	representante_legal;
	private Integer repLegal_id;
	private PersonaMein	contador;
	private Integer contador_id;
	private PersonaMein	revisor_fiscal;
	private Integer revFiscal_id;
	private PersonaMein	apoderado;
	private Integer apoderado_id;
	private String objeto_social;
	private Integer naturaleza;
	private ObjGenerico naturalezaObj;
	private String porcentaje_participacion;
	private Integer macrosector;
	private ObjGenerico macrosectorObj;
	private ApVistaMacrosectorDto macrosectorAPVista;
	private String actividad_economica;
	private String actividad_economica_nombre;
	private ObjGenerico actividad_economicaObj;
	private Integer tamano_empresa;
	private String tamano_empresa_nombre;
	private String categoria;
	private DepartamentoCiudad ciudad;
	private Integer departamento_dane;
	private DepartamentoDane departamentoObj;
	private Integer municipio_dane;
	private MunicipioDane municipioObj;
	private Integer trabajadores_hombres;
	private Integer trabajadores_mujeres;
	private String nit_sociedad_controlada;
	private String name_sociedad_controlada;
	private String radicado_sociedad_controlada;
	private Integer replegal_tiene_limitacion;
	private ObjGenerico replegal_tiene_limitacionObj;
	
	// Campos adicionales post produccion
	private Integer pais_dane;
	private PaisDane pais_dane_obj;
	
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
	public PersonaMein getDatos_basicos() {
		return datos_basicos;
	}
	public void setDatos_basicos(PersonaMein datos_basicos) {
		this.datos_basicos = datos_basicos;
	}
	public PersonaMein getRepresentante_legal() {
		return representante_legal;
	}
	public void setRepresentante_legal(PersonaMein representante_legal) {
		this.representante_legal = representante_legal;
	}
	public PersonaMein getContador() {
		return contador;
	}
	public void setContador(PersonaMein contador) {
		this.contador = contador;
	}
	public PersonaMein getRevisor_fiscal() {
		return revisor_fiscal;
	}
	public void setRevisor_fiscal(PersonaMein revisor_fiscal) {
		this.revisor_fiscal = revisor_fiscal;
	}
	public PersonaMein getApoderado() {
		return apoderado;
	}
	public void setApoderado(PersonaMein apoderado) {
		this.apoderado = apoderado;
	}
	public String getObjeto_social() {
		return objeto_social;
	}
	public void setObjeto_social(String objeto_social) {
		this.objeto_social = objeto_social;
	}
	public Integer getNaturaleza() {
		return naturaleza;
	}
	public void setNaturaleza(Integer naturaleza) {
		this.naturaleza = naturaleza;
	}
	public ObjGenerico getNaturalezaObj() {
		return naturalezaObj;
	}
	public void setNaturalezaObj(ObjGenerico naturalezaObj) {
		this.naturalezaObj = naturalezaObj;
	}
	public String getPorcentaje_participacion() {
		return porcentaje_participacion;
	}
	public void setPorcentaje_participacion(String porcentaje_participacion) {
		this.porcentaje_participacion = porcentaje_participacion;
	}
	public Integer getMacrosector() {
		return macrosector;
	}
	public void setMacrosector(Integer macrosector) {
		this.macrosector = macrosector;
	}
	public ObjGenerico getMacrosectorObj() {
		return macrosectorObj;
	}
	public void setMacrosectorObj(ObjGenerico macrosectorObj) {
		this.macrosectorObj = macrosectorObj;
	}
	public String getActividad_economica() {
		return actividad_economica;
	}
	public void setActividad_economica(String actividad_economica) {
		this.actividad_economica = actividad_economica;
	}
	public ObjGenerico getActividad_economicaObj() {
		return actividad_economicaObj;
	}
	public void setActividad_economicaObj(ObjGenerico actividad_economicaObj) {
		this.actividad_economicaObj = actividad_economicaObj;
	}
	public Integer getTamano_empresa() {
		return tamano_empresa;
	}
	public void setTamano_empresa(Integer tamano_empresa) {
		this.tamano_empresa = tamano_empresa;
	}
	public String getTamano_empresa_nombre() {
		return tamano_empresa_nombre;
	}
	public void setTamano_empresa_nombre(String tamano_empresa_nombre) {
		this.tamano_empresa_nombre = tamano_empresa_nombre;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public DepartamentoCiudad getCiudad() {
		return ciudad;
	}
	public void setCiudad(DepartamentoCiudad ciudad) {
		this.ciudad = ciudad;
	}
	public Integer getDepartamento_dane() {
		return departamento_dane;
	}
	public void setDepartamento_dane(Integer departamento_dane) {
		this.departamento_dane = departamento_dane;
	}
	public DepartamentoDane getDepartamentoObj() {
		return departamentoObj;
	}
	public void setDepartamentoObj(DepartamentoDane departamentoObj) {
		this.departamentoObj = departamentoObj;
	}
	public Integer getMunicipio_dane() {
		return municipio_dane;
	}
	public void setMunicipio_dane(Integer municipio_dane) {
		this.municipio_dane = municipio_dane;
	}
	public MunicipioDane getMunicipioObj() {
		return municipioObj;
	}
	public void setMunicipioObj(MunicipioDane municipioObj) {
		this.municipioObj = municipioObj;
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
	public String getNit_sociedad_controlada() {
		return nit_sociedad_controlada;
	}
	public void setNit_sociedad_controlada(String nit_sociedad_controlada) {
		this.nit_sociedad_controlada = nit_sociedad_controlada;
	}
	public String getName_sociedad_controlada() {
		return name_sociedad_controlada;
	}
	public void setName_sociedad_controlada(String name_sociedad_controlada) {
		this.name_sociedad_controlada = name_sociedad_controlada;
	}
	public Integer getPais_dane() {
		return pais_dane;
	}
	public void setPais_dane(Integer pais_dane) {
		this.pais_dane = pais_dane;
	}
	public PaisDane getPais_dane_obj() {
		return pais_dane_obj;
	}
	public void setPais_dane_obj(PaisDane pais_dane_obj) {
		this.pais_dane_obj = pais_dane_obj;
	}
	public Integer getRepLegal_id() {
		return repLegal_id;
	}
	public void setRepLegal_id(Integer repLegal_id) {
		this.repLegal_id = repLegal_id;
	}
	public Integer getContador_id() {
		return contador_id;
	}
	public void setContador_id(Integer contador_id) {
		this.contador_id = contador_id;
	}
	public Integer getRevFiscal_id() {
		return revFiscal_id;
	}
	public void setRevFiscal_id(Integer revFiscal_id) {
		this.revFiscal_id = revFiscal_id;
	}
	public Integer getApoderado_id() {
		return apoderado_id;
	}
	public void setApoderado_id(Integer apoderado_id) {
		this.apoderado_id = apoderado_id;
	}
	public String getRadicado_sociedad_controlada() {
		return radicado_sociedad_controlada;
	}
	public void setRadicado_sociedad_controlada(
			String radicado_sociedad_controlada) {
		this.radicado_sociedad_controlada = radicado_sociedad_controlada;
	}
	public ApVistaMacrosectorDto getMacrosectorAPVista() {
		return macrosectorAPVista;
	}
	public void setMacrosectorAPVista(ApVistaMacrosectorDto macrosectorAPVista) {
		this.macrosectorAPVista = macrosectorAPVista;
	}
	public Integer getReplegal_tiene_limitacion() {
		return replegal_tiene_limitacion;
	}
	public void setReplegal_tiene_limitacion(Integer replegal_tiene_limitacion) {
		this.replegal_tiene_limitacion = replegal_tiene_limitacion;
	}
	public ObjGenerico getReplegal_tiene_limitacionObj() {
		return replegal_tiene_limitacionObj;
	}
	public void setReplegal_tiene_limitacionObj(
			ObjGenerico replegal_tiene_limitacionObj) {
		this.replegal_tiene_limitacionObj = replegal_tiene_limitacionObj;
	}
	public String getActividad_economica_nombre() {
		return actividad_economica_nombre;
	}
	public void setActividad_economica_nombre(String actividad_economica_nombre) {
		this.actividad_economica_nombre = actividad_economica_nombre;
	}
	
}
