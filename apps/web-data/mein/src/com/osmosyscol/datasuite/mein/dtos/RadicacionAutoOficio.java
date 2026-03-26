package com.osmosyscol.datasuite.mein.dtos;

import java.util.Date;

public class RadicacionAutoOficio {
	
	private Integer id;
	private Integer idcarga;
	private Integer anexosFisicos;
	private String radicacionAnterior;
	private EnrolamientoCliente deudor;
	private APVistaMedioEnvio codigoMedioEnvio;
	private String funcionarioId;
	private String funcionarioAsignadoId;
	private APVistaTramite tramite;
	private String referenciaExterna;
	private ParticularPostal particular;
	private Integer tipo_radicado;
	private APVistaDocumentoTipo documentalTipo;
	private APVistaSeguridadTipo codigoTipoSeguridad;
	private APVistaCuaderno cuadernoTipo;
	private String entregaFisica;
	private String extensionArchivo;
	private Integer foliosNumero;
	private String loginUsuario;
	private String numeroRadicado;
	private APVistaDependencias dependencia;
	private APVistaDependencias dependenciaAsignada;
	private APVistaDependencias corresponsalDependencia;
	private Integer deudorId;
	private Integer codigoMedioEnvioId;
	private Integer tramiteId;
	private Integer particularId;
	private Integer documentalTipoId;
	private Integer codigoTipoSeguridadId;
	private Integer cuadernoTipoId;
	private Integer dependenciaId;
	private Integer dependenciaAsignadaId;
	private Integer corresponsalDependenciaId;
	private String Asunto;
	private String Cumplimiento;
	private String termino_dias;
	private Integer tipoBorrador;
	private Date fechaRadicado;
	private Integer tipoAutoActaId;
	private APVistaTipoAutoActa tipoAutoActa;
	private Integer serieId; 
	private Integer subserieId; 
	private APVistaSerie serie; 
	private APVistaSubserie subserie;
	
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
	public Integer getAnexosFisicos() {
		return anexosFisicos;
	}
	public void setAnexosFisicos(Integer anexosFisicos) {
		this.anexosFisicos = anexosFisicos;
	}
	public String getRadicacionAnterior() {
		return radicacionAnterior;
	}
	public void setRadicacionAnterior(String radicacionAnterior) {
		this.radicacionAnterior = radicacionAnterior;
	}
	public EnrolamientoCliente getDeudor() {
		return deudor;
	}
	public void setDeudor(EnrolamientoCliente deudor) {
		this.deudor = deudor;
	}
	public APVistaMedioEnvio getCodigoMedioEnvio() {
		return codigoMedioEnvio;
	}
	public void setCodigoMedioEnvio(APVistaMedioEnvio codigoMedioEnvio) {
		this.codigoMedioEnvio = codigoMedioEnvio;
	}
	public String getFuncionarioId() {
		return funcionarioId;
	}
	public void setFuncionarioId(String funcionarioId) {
		this.funcionarioId = funcionarioId;
	}
	public String getFuncionarioAsignadoId() {
		return funcionarioAsignadoId;
	}
	public void setFuncionarioAsignadoId(String funcionarioAsignadoId) {
		this.funcionarioAsignadoId = funcionarioAsignadoId;
	}
	public APVistaTramite getTramite() {
		return tramite;
	}
	public void setTramiteId(APVistaTramite tramite) {
		this.tramite = tramite;
	}
	public String getReferenciaExterna() {
		return referenciaExterna;
	}
	public void setReferenciaExterna(String referenciaExterna) {
		this.referenciaExterna = referenciaExterna;
	}
	public ParticularPostal getParticular() {
		return particular;
	}
	public void setParticular(ParticularPostal particular) {
		this.particular = particular;
	}
	public Integer getTipo_radicado() {
		return tipo_radicado;
	}
	public void setTipo_radicado(Integer tipo_radicado) {
		this.tipo_radicado = tipo_radicado;
	}
	public APVistaDocumentoTipo getDocumentalTipo() {
		return documentalTipo;
	}
	public void setDocumentalTipo(APVistaDocumentoTipo documentalTipo) {
		this.documentalTipo = documentalTipo;
	}
	public String getEntregaFisica() {
		return entregaFisica;
	}
	public void setEntregaFisica(String entregaFisica) {
		this.entregaFisica = entregaFisica;
	}
	public String getExtensionArchivo() {
		return extensionArchivo;
	}
	public void setExtensionArchivo(String extensionArchivo) {
		this.extensionArchivo = extensionArchivo;
	}
	public Integer getFoliosNumero() {
		return foliosNumero;
	}
	public void setFoliosNumero(Integer foliosNumero) {
		this.foliosNumero = foliosNumero;
	}
	public String getLoginUsuario() {
		return loginUsuario;
	}
	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}
	public String getNumeroRadicado() {
		return numeroRadicado;
	}
	public void setNumeroRadicado(String numeroRadicado) {
		this.numeroRadicado = numeroRadicado;
	}
	public APVistaDependencias getDependencia() {
		return dependencia;
	}
	public void setDependencia(APVistaDependencias dependencia) {
		this.dependencia = dependencia;
	}
	public APVistaDependencias getDependenciaAsignada() {
		return dependenciaAsignada;
	}
	public void setDependenciaAsignada(APVistaDependencias dependenciaAsignada) {
		this.dependenciaAsignada = dependenciaAsignada;
	}
	public APVistaDependencias getCorresponsalDependencia() {
		return corresponsalDependencia;
	}
	public void setCorresponsalDependencia(APVistaDependencias corresponsalDependencia) {
		this.corresponsalDependencia = corresponsalDependencia;
	}
	public APVistaSeguridadTipo getCodigoTipoSeguridad() {
		return codigoTipoSeguridad;
	}
	public void setCodigoTipoSeguridad(APVistaSeguridadTipo codigoTipoSeguridad) {
		this.codigoTipoSeguridad = codigoTipoSeguridad;
	}
	public APVistaCuaderno getCuadernoTipo() {
		return cuadernoTipo;
	}
	public void setCuadernoTipo(APVistaCuaderno cuadernoTipo) {
		this.cuadernoTipo = cuadernoTipo;
	}
	public Integer getDeudorId() {
		return deudorId;
	}
	public void setDeudorId(Integer deudorId) {
		this.deudorId = deudorId;
	}
	public Integer getCodigoMedioEnvioId() {
		return codigoMedioEnvioId;
	}
	public void setCodigoMedioEnvioId(Integer codigoMedioEnvioId) {
		this.codigoMedioEnvioId = codigoMedioEnvioId;
	}
	public Integer getTramiteId() {
		return tramiteId;
	}
	public void setTramiteId(Integer tramiteId) {
		this.tramiteId = tramiteId;
	}
	public Integer getParticularId() {
		return particularId;
	}
	public void setParticularId(Integer particularId) {
		this.particularId = particularId;
	}
	public Integer getDocumentalTipoId() {
		return documentalTipoId;
	}
	public void setDocumentalTipoId(Integer documentalTipoId) {
		this.documentalTipoId = documentalTipoId;
	}
	public Integer getCodigoTipoSeguridadId() {
		return codigoTipoSeguridadId;
	}
	public void setCodigoTipoSeguridadId(Integer codigoTipoSeguridadId) {
		this.codigoTipoSeguridadId = codigoTipoSeguridadId;
	}
	public Integer getCuadernoTipoId() {
		return cuadernoTipoId;
	}
	public void setCuadernoTipoId(Integer cuadernoTipoId) {
		this.cuadernoTipoId = cuadernoTipoId;
	}
	public Integer getDependenciaId() {
		return dependenciaId;
	}
	public void setDependenciaId(Integer dependenciaId) {
		this.dependenciaId = dependenciaId;
	}
	public Integer getDependenciaAsignadaId() {
		return dependenciaAsignadaId;
	}
	public void setDependenciaAsignadaId(Integer dependenciaAsignadaId) {
		this.dependenciaAsignadaId = dependenciaAsignadaId;
	}
	public Integer getCorresponsalDependenciaId() {
		return corresponsalDependenciaId;
	}
	public void setCorresponsalDependenciaId(Integer corresponsalDependenciaId) {
		this.corresponsalDependenciaId = corresponsalDependenciaId;
	}
	public void setTramite(APVistaTramite tramite) {
		this.tramite = tramite;
	}
	public String getAsunto() {
		return Asunto;
	}
	public void setAsunto(String asunto) {
		Asunto = asunto;
	}
	public String getCumplimiento() {
		return Cumplimiento;
	}
	public void setCumplimiento(String cumplimiento) {
		Cumplimiento = cumplimiento;
	}
	public String getTermino_dias() {
		return termino_dias;
	}
	public void setTermino_dias(String termino_dias) {
		this.termino_dias = termino_dias;
	}
	public Integer getTipoBorrador() {
		return tipoBorrador;
	}
	public void setTipoBorrador(Integer tipoBorrador) {
		this.tipoBorrador = tipoBorrador;
	}
	public Date getFechaRadicado() {
		return fechaRadicado;
	}
	public void setFechaRadicado(Date fechaRadicado) {
		this.fechaRadicado = fechaRadicado;
	}
	public Integer getTipoAutoActaId() {
		return tipoAutoActaId;
	}
	public void setTipoAutoActaId(Integer tipoAutoActaId) {
		this.tipoAutoActaId = tipoAutoActaId;
	}
	public APVistaTipoAutoActa getTipoAutoActa() {
		return tipoAutoActa;
	}
	public void setTipoAutoActa(APVistaTipoAutoActa tipoAutoActa) {
		this.tipoAutoActa = tipoAutoActa;
	}
	
	public APVistaSerie getSerie() {
		return serie;
	}
	public void setSerie(APVistaSerie serie) {
		this.serie = serie;
	}
	
	public APVistaSubserie getSubserie() {
		return subserie;
	}
	public void setSubserie(APVistaSubserie subserie) {
		this.subserie = subserie;
	}
	
	public Integer getSerieId() {
		return serieId;
	}
	public void setSerieId(Integer serieId) {
		this.serieId = serieId;
	}
	
	public Integer getSubserieId() {
		return subserieId;
	}
	public void setSubserieId(Integer subserieId) {
		this.subserieId = subserieId;
	}
}
