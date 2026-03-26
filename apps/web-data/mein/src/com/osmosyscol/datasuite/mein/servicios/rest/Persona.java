package com.osmosyscol.datasuite.mein.servicios.rest;

public class Persona {
	
    private String ActividadEconomicaCIIU; 
    private String ApartadoAereo; 
    private CiudadSigsDto CiudadDomicilio; 
    private CiudadSigsDto CiudadNotificacion; // TODO: El response presenta una estructura
    private String CorreoElectronico; 
    private DepartamentoSigsDto DepartamentoDomicilio; 
    private DepartamentoSigsDto DepartamentoNotificacion; // El response presenta una estructura
    private Integer DigitoVerificacion; // En el response es de tipo entero
    private String DireccionNotificacion; 
    private String DireccionResidencia; 
    private String FaxesNotificacion; 
    private String FaxesResidencia; 
    private String FechaIngreso;
    private String Genero;  
    private Integer IdCiudadDomicilio;
    private Integer IdCiudadNotificacion;
    private Integer IdDepartamentoDomicilio;
    private Integer IdDepartamentoNotificacion;
    private Integer IdNacionalidad;
    private Integer IdPaisDomicilio;
    private Integer IdPaisNotificacion;
    private Integer IdSegundaNacionalidad;
    private Integer IdTipoIdentificacion;
    private PaisSigsDto Nacionalidad; 
    private Long NumeroIdentificacion;
    private String NumeroIdentificacionCaracter;
    private String PaginaWeb; 
    private PaisSigsDto PaisDomicilio; 
    private PaisSigsDto PaisNotificacion; // El response presenta una estructura
    private String RazonSocial;
    private PaisSigsDto SegundaNacionalidad; 
    private String Sigla; 
    private String TelefonosNotificacion; 
    private String TelefonosResidencia; 
    private TipoIdentificacionSigsDto TipoIdentificacion; // El response presenta una estructura
    private String VersionActividadEconomica;
	public String getActividadEconomicaCIIU() {
		return ActividadEconomicaCIIU;
	}
	public void setActividadEconomicaCIIU(String actividadEconomicaCIIU) {
		ActividadEconomicaCIIU = actividadEconomicaCIIU;
	}
	public String getApartadoAereo() {
		return ApartadoAereo;
	}
	public void setApartadoAereo(String apartadoAereo) {
		ApartadoAereo = apartadoAereo;
	}
	public CiudadSigsDto getCiudadDomicilio() {
		return CiudadDomicilio;
	}
	public void setCiudadDomicilio(CiudadSigsDto ciudadDomicilio) {
		CiudadDomicilio = ciudadDomicilio;
	}
	public CiudadSigsDto getCiudadNotificacion() {
		return CiudadNotificacion;
	}
	public void setCiudadNotificacion(CiudadSigsDto ciudadNotificacion) {
		CiudadNotificacion = ciudadNotificacion;
	}
	public String getCorreoElectronico() {
		return CorreoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		CorreoElectronico = correoElectronico;
	}
	public DepartamentoSigsDto getDepartamentoDomicilio() {
		return DepartamentoDomicilio;
	}
	public void setDepartamentoDomicilio(DepartamentoSigsDto departamentoDomicilio) {
		DepartamentoDomicilio = departamentoDomicilio;
	}
	public DepartamentoSigsDto getDepartamentoNotificacion() {
		return DepartamentoNotificacion;
	}
	public void setDepartamentoNotificacion(
			DepartamentoSigsDto departamentoNotificacion) {
		DepartamentoNotificacion = departamentoNotificacion;
	}
	public Integer getDigitoVerificacion() {
		return DigitoVerificacion;
	}
	public void setDigitoVerificacion(Integer digitoVerificacion) {
		DigitoVerificacion = digitoVerificacion;
	}
	public String getDireccionNotificacion() {
		return DireccionNotificacion;
	}
	public void setDireccionNotificacion(String direccionNotificacion) {
		DireccionNotificacion = direccionNotificacion;
	}
	public String getDireccionResidencia() {
		return DireccionResidencia;
	}
	public void setDireccionResidencia(String direccionResidencia) {
		DireccionResidencia = direccionResidencia;
	}
	public String getFaxesNotificacion() {
		return FaxesNotificacion;
	}
	public void setFaxesNotificacion(String faxesNotificacion) {
		FaxesNotificacion = faxesNotificacion;
	}
	public String getFaxesResidencia() {
		return FaxesResidencia;
	}
	public void setFaxesResidencia(String faxesResidencia) {
		FaxesResidencia = faxesResidencia;
	}
	public String getFechaIngreso() {
		return FechaIngreso;
	}
	public void setFechaIngreso(String fechaIngreso) {
		FechaIngreso = fechaIngreso;
	}
	public String getGenero() {
		return Genero;
	}
	public void setGenero(String genero) {
		Genero = genero;
	}
	public Integer getIdCiudadDomicilio() {
		return IdCiudadDomicilio;
	}
	public void setIdCiudadDomicilio(Integer idCiudadDomicilio) {
		IdCiudadDomicilio = idCiudadDomicilio;
	}
	public Integer getIdCiudadNotificacion() {
		return IdCiudadNotificacion;
	}
	public void setIdCiudadNotificacion(Integer idCiudadNotificacion) {
		IdCiudadNotificacion = idCiudadNotificacion;
	}
	public Integer getIdDepartamentoDomicilio() {
		return IdDepartamentoDomicilio;
	}
	public void setIdDepartamentoDomicilio(Integer idDepartamentoDomicilio) {
		IdDepartamentoDomicilio = idDepartamentoDomicilio;
	}
	public Integer getIdDepartamentoNotificacion() {
		return IdDepartamentoNotificacion;
	}
	public void setIdDepartamentoNotificacion(Integer idDepartamentoNotificacion) {
		IdDepartamentoNotificacion = idDepartamentoNotificacion;
	}
	public Integer getIdNacionalidad() {
		return IdNacionalidad;
	}
	public void setIdNacionalidad(Integer idNacionalidad) {
		IdNacionalidad = idNacionalidad;
	}
	public Integer getIdPaisDomicilio() {
		return IdPaisDomicilio;
	}
	public void setIdPaisDomicilio(Integer idPaisDomicilio) {
		IdPaisDomicilio = idPaisDomicilio;
	}
	public Integer getIdPaisNotificacion() {
		return IdPaisNotificacion;
	}
	public void setIdPaisNotificacion(Integer idPaisNotificacion) {
		IdPaisNotificacion = idPaisNotificacion;
	}
	public Integer getIdSegundaNacionalidad() {
		return IdSegundaNacionalidad;
	}
	public void setIdSegundaNacionalidad(Integer idSegundaNacionalidad) {
		IdSegundaNacionalidad = idSegundaNacionalidad;
	}
	public Integer getIdTipoIdentificacion() {
		return IdTipoIdentificacion;
	}
	public void setIdTipoIdentificacion(Integer idTipoIdentificacion) {
		IdTipoIdentificacion = idTipoIdentificacion;
	}
	public PaisSigsDto getNacionalidad() {
		return Nacionalidad;
	}
	public void setNacionalidad(PaisSigsDto nacionalidad) {
		Nacionalidad = nacionalidad;
	}
	public Long getNumeroIdentificacion() {
		return NumeroIdentificacion;
	}
	public void setNumeroIdentificacion(Long numeroIdentificacion) {
		NumeroIdentificacion = numeroIdentificacion;
	}
	public String getNumeroIdentificacionCaracter() {
		return NumeroIdentificacionCaracter;
	}
	public void setNumeroIdentificacionCaracter(String numeroIdentificacionCaracter) {
		NumeroIdentificacionCaracter = numeroIdentificacionCaracter;
	}
	public String getPaginaWeb() {
		return PaginaWeb;
	}
	public void setPaginaWeb(String paginaWeb) {
		PaginaWeb = paginaWeb;
	}
	public PaisSigsDto getPaisDomicilio() {
		return PaisDomicilio;
	}
	public void setPaisDomicilio(PaisSigsDto paisDomicilio) {
		PaisDomicilio = paisDomicilio;
	}
	public PaisSigsDto getPaisNotificacion() {
		return PaisNotificacion;
	}
	public void setPaisNotificacion(PaisSigsDto paisNotificacion) {
		PaisNotificacion = paisNotificacion;
	}
	public String getRazonSocial() {
		return RazonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		RazonSocial = razonSocial;
	}
	public PaisSigsDto getSegundaNacionalidad() {
		return SegundaNacionalidad;
	}
	public void setSegundaNacionalidad(PaisSigsDto segundaNacionalidad) {
		SegundaNacionalidad = segundaNacionalidad;
	}
	public String getSigla() {
		return Sigla;
	}
	public void setSigla(String sigla) {
		Sigla = sigla;
	}
	public String getTelefonosNotificacion() {
		return TelefonosNotificacion;
	}
	public void setTelefonosNotificacion(String telefonosNotificacion) {
		TelefonosNotificacion = telefonosNotificacion;
	}
	public String getTelefonosResidencia() {
		return TelefonosResidencia;
	}
	public void setTelefonosResidencia(String telefonosResidencia) {
		TelefonosResidencia = telefonosResidencia;
	}
	public TipoIdentificacionSigsDto getTipoIdentificacion() {
		return TipoIdentificacion;
	}
	public void setTipoIdentificacion(TipoIdentificacionSigsDto tipoIdentificacion) {
		TipoIdentificacion = tipoIdentificacion;
	}
	public String getVersionActividadEconomica() {
		return VersionActividadEconomica;
	}
	public void setVersionActividadEconomica(String versionActividadEconomica) {
		VersionActividadEconomica = versionActividadEconomica;
	}
    
	
    
}
