package com.osmosyscol.datasuite.bpm.dto;

import java.util.List;

public class ProcesoRadicacionDto {
	private String NumeroProc;
	private String DependenciaId;
	private String DependenciaNombre;
	private String ClaseProcesoId;
	private String ClaseProcesoNombre;
	private String FechaInicio;
	private String Identificacion;
	private String PersonaNombre;
	private String PersonaDereccionNotificacion;
	private String Correo;
	private String Telefono;
	private String PAI_Codigo;
	private String DEP_Codigo;
	private String MUN_Codigo;
	private String IDET_Codigo;
	private String IDET_Nombre;
	private String IDET_Id;
	private String RadicadoInicial;
	private String ApoderadoTipoIdentificacionNombre;
	private String ApoderadoTelefono;
	private String ApoderadoNombre;
	private String ApoderadoIdentificacion;
	private String ApoderadoEmail;
	private String ApoderadoDireccion;
	private String ApoderadoDepartamentoCodigo;
	private String ApoderadoCiudadCodigo;
	private String ApoderadoPaisCodigo;
	private String ApoderadoTipoIdentificacionId;
	private String idTipoPonente;
	private String ValorTipoPonente;
	
	private List<PropiedadesPartesDto> ListaPropiedadesPartes;
	private List<PropiedadesProcesoActuacionesDto> ListaPropiedadesProcesoActuaciones;
	private CamposAdicionalesDto camposAdicionales = new CamposAdicionalesDto();
	
	public String getNumeroProc() {
		return NumeroProc;
	}
	public void setNumeroProc(String numeroProc) {
		NumeroProc = numeroProc;
	}
	public String getDependenciaId() {
		return DependenciaId;
	}
	public void setDependenciaId(String dependenciaId) {
		DependenciaId = dependenciaId;
	}
	public String getDependenciaNombre() {
		return DependenciaNombre;
	}
	public void setDependenciaNombre(String dependenciaNombre) {
		DependenciaNombre = dependenciaNombre;
	}
	public String getClaseProcesoId() {
		return ClaseProcesoId;
	}
	public void setClaseProcesoId(String claseProcesoId) {
		ClaseProcesoId = claseProcesoId;
	}
	public String getClaseProcesoNombre() {
		return ClaseProcesoNombre;
	}
	public void setClaseProcesoNombre(String claseProcesoNombre) {
		ClaseProcesoNombre = claseProcesoNombre;
	}
	public String getFechaInicio() {
		return FechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		FechaInicio = fechaInicio;
	}
	public String getIdentificacion() {
		return Identificacion;
	}
	public void setIdentificacion(String identificacion) {
		Identificacion = identificacion;
	}
	public String getPersonaNombre() {
		return PersonaNombre;
	}
	public void setPersonaNombre(String personaNombre) {
		PersonaNombre = personaNombre;
	}
	public String getPersonaDereccionNotificacion() {
		return PersonaDereccionNotificacion;
	}
	public void setPersonaDereccionNotificacion(String personaDereccionNotificacion) {
		PersonaDereccionNotificacion = personaDereccionNotificacion;
	}
	public String getCorreo() {
		return Correo;
	}
	public void setCorreo(String correo) {
		Correo = correo;
	}
	public String getTelefono() {
		return Telefono;
	}
	public void setTelefono(String telefono) {
		Telefono = telefono;
	}
	public String getPAI_Codigo() {
		return PAI_Codigo;
	}
	public void setPAI_Codigo(String pAI_Codigo) {
		PAI_Codigo = pAI_Codigo;
	}
	public String getDEP_Codigo() {
		return DEP_Codigo;
	}
	public void setDEP_Codigo(String dEP_Codigo) {
		DEP_Codigo = dEP_Codigo;
	}
	public String getMUN_Codigo() {
		return MUN_Codigo;
	}
	public void setMUN_Codigo(String mUN_Codigo) {
		MUN_Codigo = mUN_Codigo;
	}
	public String getIDET_Codigo() {
		return IDET_Codigo;
	}
	public void setIDET_Codigo(String iDET_Codigo) {
		IDET_Codigo = iDET_Codigo;
	}
	public String getIDET_Nombre() {
		return IDET_Nombre;
	}
	public void setIDET_Nombre(String iDET_Nombre) {
		IDET_Nombre = iDET_Nombre;
	}
	public String getRadicadoInicial() {
		return RadicadoInicial;
	}
	public void setRadicadoInicial(String radicadoInicial) {
		RadicadoInicial = radicadoInicial;
	}
	public String getApoderadoTipoIdentificacionNombre() {
		return ApoderadoTipoIdentificacionNombre;
	}
	public void setApoderadoTipoIdentificacionNombre(
			String apoderadoTipoIdentificacionNombre) {
		ApoderadoTipoIdentificacionNombre = apoderadoTipoIdentificacionNombre;
	}
	public String getApoderadoTelefono() {
		return ApoderadoTelefono;
	}
	public void setApoderadoTelefono(String apoderadoTelefono) {
		ApoderadoTelefono = apoderadoTelefono;
	}
	public String getApoderadoNombre() {
		return ApoderadoNombre;
	}
	public void setApoderadoNombre(String apoderadoNombre) {
		ApoderadoNombre = apoderadoNombre;
	}
	public String getApoderadoIdentificacion() {
		return ApoderadoIdentificacion;
	}
	public void setApoderadoIdentificacion(String apoderadoIdentificacion) {
		ApoderadoIdentificacion = apoderadoIdentificacion;
	}
	public String getApoderadoEmail() {
		return ApoderadoEmail;
	}
	public void setApoderadoEmail(String apoderadoEmail) {
		ApoderadoEmail = apoderadoEmail;
	}
	public String getApoderadoDireccion() {
		return ApoderadoDireccion;
	}
	public void setApoderadoDireccion(String apoderadoDireccion) {
		ApoderadoDireccion = apoderadoDireccion;
	}
	public String getApoderadoDepartamentoCodigo() {
		return ApoderadoDepartamentoCodigo;
	}
	public void setApoderadoDepartamentoCodigo(String apoderadoDepartamentoCodigo) {
		ApoderadoDepartamentoCodigo = apoderadoDepartamentoCodigo;
	}
	public String getApoderadoCiudadCodigo() {
		return ApoderadoCiudadCodigo;
	}
	public void setApoderadoCiudadCodigo(String apoderadoCiudadCodigo) {
		ApoderadoCiudadCodigo = apoderadoCiudadCodigo;
	}
	public String getApoderadoPaisCodigo() {
		return ApoderadoPaisCodigo;
	}
	public void setApoderadoPaisCodigo(String apoderadoPaisCodigo) {
		ApoderadoPaisCodigo = apoderadoPaisCodigo;
	}
	public String getApoderadoTipoIdentificacionId() {
		return ApoderadoTipoIdentificacionId;
	}
	public void setApoderadoTipoIdentificacionId(
			String apoderadoTipoIdentificacionId) {
		ApoderadoTipoIdentificacionId = apoderadoTipoIdentificacionId;
	}
	public List<PropiedadesPartesDto> getListaPropiedadesPartes() {
		return ListaPropiedadesPartes;
	}
	public void setListaPropiedadesPartes(
			List<PropiedadesPartesDto> listaPropiedadesPartes) {
		ListaPropiedadesPartes = listaPropiedadesPartes;
	}
	public List<PropiedadesProcesoActuacionesDto> getListaPropiedadesProcesoActuaciones() {
		return ListaPropiedadesProcesoActuaciones;
	}
	public void setListaPropiedadesProcesoActuaciones(
			List<PropiedadesProcesoActuacionesDto> listaPropiedadesProcesoActuaciones) {
		ListaPropiedadesProcesoActuaciones = listaPropiedadesProcesoActuaciones;
	}
	public CamposAdicionalesDto getCamposAdicionales() {
		return camposAdicionales;
	}
	public void setCamposAdicionales(CamposAdicionalesDto camposAdicionales) {
		this.camposAdicionales = camposAdicionales;
	}
	public String getIDET_Id() {
		return IDET_Id;
	}
	public void setIDET_Id(String iDET_Id) {
		IDET_Id = iDET_Id;
	}
	public String getIdTipoPonente() {
		return idTipoPonente;
	}
	public void setIdTipoPonente(String idTipoPonente) {
		this.idTipoPonente = idTipoPonente;
	}
	public String getValorTipoPonente() {
		return ValorTipoPonente;
	}
	public void setValorTipoPonente(String valorTipoPonente) {
		ValorTipoPonente = valorTipoPonente;
	}
	
}
