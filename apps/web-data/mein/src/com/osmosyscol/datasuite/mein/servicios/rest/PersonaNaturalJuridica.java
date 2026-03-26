package com.osmosyscol.datasuite.mein.servicios.rest;

import java.util.Date;

public class PersonaNaturalJuridica {
	private String actividadEconomicaCIIU;
	private String apartadoAereo;
	private String correoElectronico;
	private String direccionNotificacion;
	private String direccionResidencia;
	private String faxesNotificacion; 
	private String faxesResidencia; 
	private Character genero;
	private Integer idCiudadDomicilio; 
	private Integer idCiudadNotificacion;
	private Integer idDepartamentoDomicilio; 
	private Integer idDepartamentoNotificacion;
	private Integer idNacionalidad; 
	private Integer idPaisDomicilio;
	private Integer idPaisNotificacion;
	private Integer idSegundaNacionalidad;
	private Integer idTipoIdentificacion;
	private String numeroIdentificacionCaracter;
	private String paginaWeb;
	private String razonSocial;
	private String sigla;	
	private String telefonosNotificacion;
	private String telefonosResidencia;	
	private String versionActividadEconomica;
	private Date fechaIngreso;
	private Long numeroIdentificacion;
		
	public PersonaNaturalJuridica() {
		super();
	}
	
//	Constructor de Datos de Identificación y Notificación Completos
	public PersonaNaturalJuridica(Integer idTipoIdentificacion, Long numeroIdentificacion,
	        String numeroIdentificacionCaracter,
	        String razonSocial, String sigla,
	        Integer idPaisNotificacion, String telefonosNotificacion,
	        Integer idDepartamentoNotificacion, Integer idCiudadNotificacion,
	        String correoElectronico, String direccionNotificacion) {
	    super();
	    this.idTipoIdentificacion = idTipoIdentificacion;
	    this.numeroIdentificacion = numeroIdentificacion;
	    this.numeroIdentificacionCaracter = numeroIdentificacionCaracter;
	    this.razonSocial = razonSocial;
	    this.sigla = sigla;
	    this.idPaisNotificacion = idPaisNotificacion;
	    this.telefonosNotificacion = telefonosNotificacion;
	    this.idDepartamentoNotificacion = idDepartamentoNotificacion;
	    this.idCiudadNotificacion = idCiudadNotificacion;
	    this.correoElectronico = correoElectronico;
	    this.direccionNotificacion = direccionNotificacion;
	}
	
//	Constructor de Identificación y Contacto Esencial
	public PersonaNaturalJuridica(Integer idTipoIdentificacion, Long numeroIdentificacion,
	        String correoElectronico, String direccionNotificacion) {
	    super();
	    this.idTipoIdentificacion = idTipoIdentificacion;
	    this.numeroIdentificacion = numeroIdentificacion;
	    this.numeroIdentificacionCaracter = numeroIdentificacion.toString();
	    this.correoElectronico = correoElectronico;
	    this.direccionNotificacion = direccionNotificacion;
	}

//	 Constructor de Identificación y Razón Social Básica
	public PersonaNaturalJuridica(String numeroIdentificacionCaracter,
	        String razonSocial, Integer idTipoIdentificacion) {
	    super();
	    this.numeroIdentificacionCaracter = numeroIdentificacionCaracter;
	    this.razonSocial = razonSocial;
	    this.idTipoIdentificacion = idTipoIdentificacion;
	}

	public String getActividadEconomicaCIIU() {
		return actividadEconomicaCIIU;
	}

	public void setActividadEconomicaCIIU(String actividadEconomicaCIIU) {
		this.actividadEconomicaCIIU = actividadEconomicaCIIU;
	}

	public String getApartadoAereo() {
		return apartadoAereo;
	}

	public void setApartadoAereo(String apartadoAereo) {
		this.apartadoAereo = apartadoAereo;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getDireccionNotificacion() {
		return direccionNotificacion;
	}

	public void setDireccionNotificacion(String direccionNotificacion) {
		this.direccionNotificacion = direccionNotificacion;
	}

	public String getDireccionResidencia() {
		return direccionResidencia;
	}

	public void setDireccionResidencia(String direccionResidencia) {
		this.direccionResidencia = direccionResidencia;
	}

	public String getFaxesNotificacion() {
		return faxesNotificacion;
	}

	public void setFaxesNotificacion(String faxesNotificacion) {
		this.faxesNotificacion = faxesNotificacion;
	}

	public String getFaxesResidencia() {
		return faxesResidencia;
	}

	public void setFaxesResidencia(String faxesResidencia) {
		this.faxesResidencia = faxesResidencia;
	}

	public Character getGenero() {
		return genero;
	}

	public void setGenero(Character genero) {
		this.genero = genero;
	}

	public Integer getIdCiudadDomicilio() {
		return idCiudadDomicilio;
	}

	public void setIdCiudadDomicilio(Integer idCiudadDomicilio) {
		this.idCiudadDomicilio = idCiudadDomicilio;
	}

	public Integer getIdCiudadNotificacion() {
		return idCiudadNotificacion;
	}

	public void setIdCiudadNotificacion(Integer idCiudadNotificacion) {
		this.idCiudadNotificacion = idCiudadNotificacion;
	}

	public Integer getIdDepartamentoDomicilio() {
		return idDepartamentoDomicilio;
	}

	public void setIdDepartamentoDomicilio(Integer idDepartamentoDomicilio) {
		this.idDepartamentoDomicilio = idDepartamentoDomicilio;
	}

	public Integer getIdDepartamentoNotificacion() {
		return idDepartamentoNotificacion;
	}

	public void setIdDepartamentoNotificacion(Integer idDepartamentoNotificacion) {
		this.idDepartamentoNotificacion = idDepartamentoNotificacion;
	}

	public Integer getIdNacionalidad() {
		return idNacionalidad;
	}

	public void setIdNacionalidad(Integer idNacionalidad) {
		this.idNacionalidad = idNacionalidad;
	}

	public Integer getIdPaisDomicilio() {
		return idPaisDomicilio;
	}

	public void setIdPaisDomicilio(Integer idPaisDomicilio) {
		this.idPaisDomicilio = idPaisDomicilio;
	}

	public Integer getIdPaisNotificacion() {
		return idPaisNotificacion;
	}

	public void setIdPaisNotificacion(Integer idPaisNotificacion) {
		this.idPaisNotificacion = idPaisNotificacion;
	}

	public Integer getIdSegundaNacionalidad() {
		return idSegundaNacionalidad;
	}

	public void setIdSegundaNacionalidad(Integer idSegundaNacionalidad) {
		this.idSegundaNacionalidad = idSegundaNacionalidad;
	}

	public Integer getIdTipoIdentificacion() {
		return idTipoIdentificacion;
	}

	public void setIdTipoIdentificacion(Integer idTipoIdentificacion) {
		this.idTipoIdentificacion = idTipoIdentificacion;
	}

	public String getNumeroIdentificacionCaracter() {
		return numeroIdentificacionCaracter;
	}

	public void setNumeroIdentificacionCaracter(String numeroIdentificacionCaracter) {
		this.numeroIdentificacionCaracter = numeroIdentificacionCaracter;
	}

	public String getPaginaWeb() {
		return paginaWeb;
	}

	public void setPaginaWeb(String paginaWeb) {
		this.paginaWeb = paginaWeb;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getTelefonosNotificacion() {
		return telefonosNotificacion;
	}

	public void setTelefonosNotificacion(String telefonosNotificacion) {
		this.telefonosNotificacion = telefonosNotificacion;
	}

	public String getTelefonosResidencia() {
		return telefonosResidencia;
	}

	public void setTelefonosResidencia(String telefonosResidencia) {
		this.telefonosResidencia = telefonosResidencia;
	}

	public String getVersionActividadEconomica() {
		return versionActividadEconomica;
	}

	public void setVersionActividadEconomica(String versionActividadEconomica) {
		this.versionActividadEconomica = versionActividadEconomica;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Long getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(Long numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}
	
}
