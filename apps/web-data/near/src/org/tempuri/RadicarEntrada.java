/**
 * RadicarEntrada.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class RadicarEntrada  implements java.io.Serializable {
    private java.lang.String anexosFisicos;

    private java.lang.String aplicaCiudadCodigo;

    private java.lang.String aplicaDepartamentoCodigo;

    private java.lang.String aplicaPaisCodigo;

    private java.lang.String aplicaEmail;

    private java.lang.String aplicaDireccion;

    private java.lang.String aplicaNombre;

    private java.lang.String aplicaTelefono;

    private java.lang.String aplicaIdentificacion;

    private java.lang.Integer aplicaTipoIdentificacionId;

    private java.lang.String aplicaTipoIdentificacionNombre;

    private java.lang.String particularIdentificacion;

    private java.lang.String particularNombre;

    private java.lang.Integer particularTipoIdentificacionId;

    private java.lang.String particularTipoIdentificacionNombre;

    private java.lang.String particularCiudadCodigo;

    private java.lang.String particularDepartamentoCodigo;

    private java.lang.String particularPaisCodigo;

    private java.lang.String particularDireccion;

    private java.lang.String particularTelefono;

    private java.lang.String particularEmail;

    private java.lang.Integer dependenciaId;

    private java.lang.String dependenciaNombre;

    private java.lang.Boolean entregaFisica;

    private java.lang.Integer foliosNumero;

    private java.lang.String referenciaExterna;

    private java.lang.Integer cuadernoTipoId;

    private java.lang.String cuadernoCodigo;

    private java.lang.String documentalTipoCodigo;

    private java.lang.Integer tramiteId;

    private java.lang.Long tramiteCodigo;

    private java.lang.String extensionArchivo;

    private java.lang.Integer codigoMedioEnvio;

    private java.lang.String codigoTipoSeguridad;

    private java.lang.String codigoSerie;

    private java.lang.String codigoSubSerie;

    private java.lang.String loginUsuario;

    private java.lang.String radicacionAnterior;

    private java.lang.String nombreSerie;

    private java.lang.String nombreSubSerie;

    public RadicarEntrada() {
    }

    public RadicarEntrada(
           java.lang.String anexosFisicos,
           java.lang.String aplicaCiudadCodigo,
           java.lang.String aplicaDepartamentoCodigo,
           java.lang.String aplicaPaisCodigo,
           java.lang.String aplicaEmail,
           java.lang.String aplicaDireccion,
           java.lang.String aplicaNombre,
           java.lang.String aplicaTelefono,
           java.lang.String aplicaIdentificacion,
           java.lang.Integer aplicaTipoIdentificacionId,
           java.lang.String aplicaTipoIdentificacionNombre,
           java.lang.String particularIdentificacion,
           java.lang.String particularNombre,
           java.lang.Integer particularTipoIdentificacionId,
           java.lang.String particularTipoIdentificacionNombre,
           java.lang.String particularCiudadCodigo,
           java.lang.String particularDepartamentoCodigo,
           java.lang.String particularPaisCodigo,
           java.lang.String particularDireccion,
           java.lang.String particularTelefono,
           java.lang.String particularEmail,
           java.lang.Integer dependenciaId,
           java.lang.String dependenciaNombre,
           java.lang.Boolean entregaFisica,
           java.lang.Integer foliosNumero,
           java.lang.String referenciaExterna,
           java.lang.Integer cuadernoTipoId,
           java.lang.String cuadernoCodigo,
           java.lang.String documentalTipoCodigo,
           java.lang.Integer tramiteId,
           java.lang.Long tramiteCodigo,
           java.lang.String extensionArchivo,
           java.lang.Integer codigoMedioEnvio,
           java.lang.String codigoTipoSeguridad,
           java.lang.String codigoSerie,
           java.lang.String codigoSubSerie,
           java.lang.String loginUsuario,
           java.lang.String radicacionAnterior,
           java.lang.String nombreSerie,
           java.lang.String nombreSubSerie) {
           this.anexosFisicos = anexosFisicos;
           this.aplicaCiudadCodigo = aplicaCiudadCodigo;
           this.aplicaDepartamentoCodigo = aplicaDepartamentoCodigo;
           this.aplicaPaisCodigo = aplicaPaisCodigo;
           this.aplicaEmail = aplicaEmail;
           this.aplicaDireccion = aplicaDireccion;
           this.aplicaNombre = aplicaNombre;
           this.aplicaTelefono = aplicaTelefono;
           this.aplicaIdentificacion = aplicaIdentificacion;
           this.aplicaTipoIdentificacionId = aplicaTipoIdentificacionId;
           this.aplicaTipoIdentificacionNombre = aplicaTipoIdentificacionNombre;
           this.particularIdentificacion = particularIdentificacion;
           this.particularNombre = particularNombre;
           this.particularTipoIdentificacionId = particularTipoIdentificacionId;
           this.particularTipoIdentificacionNombre = particularTipoIdentificacionNombre;
           this.particularCiudadCodigo = particularCiudadCodigo;
           this.particularDepartamentoCodigo = particularDepartamentoCodigo;
           this.particularPaisCodigo = particularPaisCodigo;
           this.particularDireccion = particularDireccion;
           this.particularTelefono = particularTelefono;
           this.particularEmail = particularEmail;
           this.dependenciaId = dependenciaId;
           this.dependenciaNombre = dependenciaNombre;
           this.entregaFisica = entregaFisica;
           this.foliosNumero = foliosNumero;
           this.referenciaExterna = referenciaExterna;
           this.cuadernoTipoId = cuadernoTipoId;
           this.cuadernoCodigo = cuadernoCodigo;
           this.documentalTipoCodigo = documentalTipoCodigo;
           this.tramiteId = tramiteId;
           this.tramiteCodigo = tramiteCodigo;
           this.extensionArchivo = extensionArchivo;
           this.codigoMedioEnvio = codigoMedioEnvio;
           this.codigoTipoSeguridad = codigoTipoSeguridad;
           this.codigoSerie = codigoSerie;
           this.codigoSubSerie = codigoSubSerie;
           this.loginUsuario = loginUsuario;
           this.radicacionAnterior = radicacionAnterior;
           this.nombreSerie = nombreSerie;
           this.nombreSubSerie = nombreSubSerie;
    }


    /**
     * Gets the anexosFisicos value for this RadicarEntrada.
     * 
     * @return anexosFisicos
     */
    public java.lang.String getAnexosFisicos() {
        return anexosFisicos;
    }


    /**
     * Sets the anexosFisicos value for this RadicarEntrada.
     * 
     * @param anexosFisicos
     */
    public void setAnexosFisicos(java.lang.String anexosFisicos) {
        this.anexosFisicos = anexosFisicos;
    }


    /**
     * Gets the aplicaCiudadCodigo value for this RadicarEntrada.
     * 
     * @return aplicaCiudadCodigo
     */
    public java.lang.String getAplicaCiudadCodigo() {
        return aplicaCiudadCodigo;
    }


    /**
     * Sets the aplicaCiudadCodigo value for this RadicarEntrada.
     * 
     * @param aplicaCiudadCodigo
     */
    public void setAplicaCiudadCodigo(java.lang.String aplicaCiudadCodigo) {
        this.aplicaCiudadCodigo = aplicaCiudadCodigo;
    }


    /**
     * Gets the aplicaDepartamentoCodigo value for this RadicarEntrada.
     * 
     * @return aplicaDepartamentoCodigo
     */
    public java.lang.String getAplicaDepartamentoCodigo() {
        return aplicaDepartamentoCodigo;
    }


    /**
     * Sets the aplicaDepartamentoCodigo value for this RadicarEntrada.
     * 
     * @param aplicaDepartamentoCodigo
     */
    public void setAplicaDepartamentoCodigo(java.lang.String aplicaDepartamentoCodigo) {
        this.aplicaDepartamentoCodigo = aplicaDepartamentoCodigo;
    }


    /**
     * Gets the aplicaPaisCodigo value for this RadicarEntrada.
     * 
     * @return aplicaPaisCodigo
     */
    public java.lang.String getAplicaPaisCodigo() {
        return aplicaPaisCodigo;
    }


    /**
     * Sets the aplicaPaisCodigo value for this RadicarEntrada.
     * 
     * @param aplicaPaisCodigo
     */
    public void setAplicaPaisCodigo(java.lang.String aplicaPaisCodigo) {
        this.aplicaPaisCodigo = aplicaPaisCodigo;
    }


    /**
     * Gets the aplicaEmail value for this RadicarEntrada.
     * 
     * @return aplicaEmail
     */
    public java.lang.String getAplicaEmail() {
        return aplicaEmail;
    }


    /**
     * Sets the aplicaEmail value for this RadicarEntrada.
     * 
     * @param aplicaEmail
     */
    public void setAplicaEmail(java.lang.String aplicaEmail) {
        this.aplicaEmail = aplicaEmail;
    }


    /**
     * Gets the aplicaDireccion value for this RadicarEntrada.
     * 
     * @return aplicaDireccion
     */
    public java.lang.String getAplicaDireccion() {
        return aplicaDireccion;
    }


    /**
     * Sets the aplicaDireccion value for this RadicarEntrada.
     * 
     * @param aplicaDireccion
     */
    public void setAplicaDireccion(java.lang.String aplicaDireccion) {
        this.aplicaDireccion = aplicaDireccion;
    }


    /**
     * Gets the aplicaNombre value for this RadicarEntrada.
     * 
     * @return aplicaNombre
     */
    public java.lang.String getAplicaNombre() {
        return aplicaNombre;
    }


    /**
     * Sets the aplicaNombre value for this RadicarEntrada.
     * 
     * @param aplicaNombre
     */
    public void setAplicaNombre(java.lang.String aplicaNombre) {
        this.aplicaNombre = aplicaNombre;
    }


    /**
     * Gets the aplicaTelefono value for this RadicarEntrada.
     * 
     * @return aplicaTelefono
     */
    public java.lang.String getAplicaTelefono() {
        return aplicaTelefono;
    }


    /**
     * Sets the aplicaTelefono value for this RadicarEntrada.
     * 
     * @param aplicaTelefono
     */
    public void setAplicaTelefono(java.lang.String aplicaTelefono) {
        this.aplicaTelefono = aplicaTelefono;
    }


    /**
     * Gets the aplicaIdentificacion value for this RadicarEntrada.
     * 
     * @return aplicaIdentificacion
     */
    public java.lang.String getAplicaIdentificacion() {
        return aplicaIdentificacion;
    }


    /**
     * Sets the aplicaIdentificacion value for this RadicarEntrada.
     * 
     * @param aplicaIdentificacion
     */
    public void setAplicaIdentificacion(java.lang.String aplicaIdentificacion) {
        this.aplicaIdentificacion = aplicaIdentificacion;
    }


    /**
     * Gets the aplicaTipoIdentificacionId value for this RadicarEntrada.
     * 
     * @return aplicaTipoIdentificacionId
     */
    public java.lang.Integer getAplicaTipoIdentificacionId() {
        return aplicaTipoIdentificacionId;
    }


    /**
     * Sets the aplicaTipoIdentificacionId value for this RadicarEntrada.
     * 
     * @param aplicaTipoIdentificacionId
     */
    public void setAplicaTipoIdentificacionId(java.lang.Integer aplicaTipoIdentificacionId) {
        this.aplicaTipoIdentificacionId = aplicaTipoIdentificacionId;
    }


    /**
     * Gets the aplicaTipoIdentificacionNombre value for this RadicarEntrada.
     * 
     * @return aplicaTipoIdentificacionNombre
     */
    public java.lang.String getAplicaTipoIdentificacionNombre() {
        return aplicaTipoIdentificacionNombre;
    }


    /**
     * Sets the aplicaTipoIdentificacionNombre value for this RadicarEntrada.
     * 
     * @param aplicaTipoIdentificacionNombre
     */
    public void setAplicaTipoIdentificacionNombre(java.lang.String aplicaTipoIdentificacionNombre) {
        this.aplicaTipoIdentificacionNombre = aplicaTipoIdentificacionNombre;
    }


    /**
     * Gets the particularIdentificacion value for this RadicarEntrada.
     * 
     * @return particularIdentificacion
     */
    public java.lang.String getParticularIdentificacion() {
        return particularIdentificacion;
    }


    /**
     * Sets the particularIdentificacion value for this RadicarEntrada.
     * 
     * @param particularIdentificacion
     */
    public void setParticularIdentificacion(java.lang.String particularIdentificacion) {
        this.particularIdentificacion = particularIdentificacion;
    }


    /**
     * Gets the particularNombre value for this RadicarEntrada.
     * 
     * @return particularNombre
     */
    public java.lang.String getParticularNombre() {
        return particularNombre;
    }


    /**
     * Sets the particularNombre value for this RadicarEntrada.
     * 
     * @param particularNombre
     */
    public void setParticularNombre(java.lang.String particularNombre) {
        this.particularNombre = particularNombre;
    }


    /**
     * Gets the particularTipoIdentificacionId value for this RadicarEntrada.
     * 
     * @return particularTipoIdentificacionId
     */
    public java.lang.Integer getParticularTipoIdentificacionId() {
        return particularTipoIdentificacionId;
    }


    /**
     * Sets the particularTipoIdentificacionId value for this RadicarEntrada.
     * 
     * @param particularTipoIdentificacionId
     */
    public void setParticularTipoIdentificacionId(java.lang.Integer particularTipoIdentificacionId) {
        this.particularTipoIdentificacionId = particularTipoIdentificacionId;
    }


    /**
     * Gets the particularTipoIdentificacionNombre value for this RadicarEntrada.
     * 
     * @return particularTipoIdentificacionNombre
     */
    public java.lang.String getParticularTipoIdentificacionNombre() {
        return particularTipoIdentificacionNombre;
    }


    /**
     * Sets the particularTipoIdentificacionNombre value for this RadicarEntrada.
     * 
     * @param particularTipoIdentificacionNombre
     */
    public void setParticularTipoIdentificacionNombre(java.lang.String particularTipoIdentificacionNombre) {
        this.particularTipoIdentificacionNombre = particularTipoIdentificacionNombre;
    }


    /**
     * Gets the particularCiudadCodigo value for this RadicarEntrada.
     * 
     * @return particularCiudadCodigo
     */
    public java.lang.String getParticularCiudadCodigo() {
        return particularCiudadCodigo;
    }


    /**
     * Sets the particularCiudadCodigo value for this RadicarEntrada.
     * 
     * @param particularCiudadCodigo
     */
    public void setParticularCiudadCodigo(java.lang.String particularCiudadCodigo) {
        this.particularCiudadCodigo = particularCiudadCodigo;
    }


    /**
     * Gets the particularDepartamentoCodigo value for this RadicarEntrada.
     * 
     * @return particularDepartamentoCodigo
     */
    public java.lang.String getParticularDepartamentoCodigo() {
        return particularDepartamentoCodigo;
    }


    /**
     * Sets the particularDepartamentoCodigo value for this RadicarEntrada.
     * 
     * @param particularDepartamentoCodigo
     */
    public void setParticularDepartamentoCodigo(java.lang.String particularDepartamentoCodigo) {
        this.particularDepartamentoCodigo = particularDepartamentoCodigo;
    }


    /**
     * Gets the particularPaisCodigo value for this RadicarEntrada.
     * 
     * @return particularPaisCodigo
     */
    public java.lang.String getParticularPaisCodigo() {
        return particularPaisCodigo;
    }


    /**
     * Sets the particularPaisCodigo value for this RadicarEntrada.
     * 
     * @param particularPaisCodigo
     */
    public void setParticularPaisCodigo(java.lang.String particularPaisCodigo) {
        this.particularPaisCodigo = particularPaisCodigo;
    }


    /**
     * Gets the particularDireccion value for this RadicarEntrada.
     * 
     * @return particularDireccion
     */
    public java.lang.String getParticularDireccion() {
        return particularDireccion;
    }


    /**
     * Sets the particularDireccion value for this RadicarEntrada.
     * 
     * @param particularDireccion
     */
    public void setParticularDireccion(java.lang.String particularDireccion) {
        this.particularDireccion = particularDireccion;
    }


    /**
     * Gets the particularTelefono value for this RadicarEntrada.
     * 
     * @return particularTelefono
     */
    public java.lang.String getParticularTelefono() {
        return particularTelefono;
    }


    /**
     * Sets the particularTelefono value for this RadicarEntrada.
     * 
     * @param particularTelefono
     */
    public void setParticularTelefono(java.lang.String particularTelefono) {
        this.particularTelefono = particularTelefono;
    }


    /**
     * Gets the particularEmail value for this RadicarEntrada.
     * 
     * @return particularEmail
     */
    public java.lang.String getParticularEmail() {
        return particularEmail;
    }


    /**
     * Sets the particularEmail value for this RadicarEntrada.
     * 
     * @param particularEmail
     */
    public void setParticularEmail(java.lang.String particularEmail) {
        this.particularEmail = particularEmail;
    }


    /**
     * Gets the dependenciaId value for this RadicarEntrada.
     * 
     * @return dependenciaId
     */
    public java.lang.Integer getDependenciaId() {
        return dependenciaId;
    }


    /**
     * Sets the dependenciaId value for this RadicarEntrada.
     * 
     * @param dependenciaId
     */
    public void setDependenciaId(java.lang.Integer dependenciaId) {
        this.dependenciaId = dependenciaId;
    }


    /**
     * Gets the dependenciaNombre value for this RadicarEntrada.
     * 
     * @return dependenciaNombre
     */
    public java.lang.String getDependenciaNombre() {
        return dependenciaNombre;
    }


    /**
     * Sets the dependenciaNombre value for this RadicarEntrada.
     * 
     * @param dependenciaNombre
     */
    public void setDependenciaNombre(java.lang.String dependenciaNombre) {
        this.dependenciaNombre = dependenciaNombre;
    }


    /**
     * Gets the entregaFisica value for this RadicarEntrada.
     * 
     * @return entregaFisica
     */
    public java.lang.Boolean getEntregaFisica() {
        return entregaFisica;
    }


    /**
     * Sets the entregaFisica value for this RadicarEntrada.
     * 
     * @param entregaFisica
     */
    public void setEntregaFisica(java.lang.Boolean entregaFisica) {
        this.entregaFisica = entregaFisica;
    }


    /**
     * Gets the foliosNumero value for this RadicarEntrada.
     * 
     * @return foliosNumero
     */
    public java.lang.Integer getFoliosNumero() {
        return foliosNumero;
    }


    /**
     * Sets the foliosNumero value for this RadicarEntrada.
     * 
     * @param foliosNumero
     */
    public void setFoliosNumero(java.lang.Integer foliosNumero) {
        this.foliosNumero = foliosNumero;
    }


    /**
     * Gets the referenciaExterna value for this RadicarEntrada.
     * 
     * @return referenciaExterna
     */
    public java.lang.String getReferenciaExterna() {
        return referenciaExterna;
    }


    /**
     * Sets the referenciaExterna value for this RadicarEntrada.
     * 
     * @param referenciaExterna
     */
    public void setReferenciaExterna(java.lang.String referenciaExterna) {
        this.referenciaExterna = referenciaExterna;
    }


    /**
     * Gets the cuadernoTipoId value for this RadicarEntrada.
     * 
     * @return cuadernoTipoId
     */
    public java.lang.Integer getCuadernoTipoId() {
        return cuadernoTipoId;
    }


    /**
     * Sets the cuadernoTipoId value for this RadicarEntrada.
     * 
     * @param cuadernoTipoId
     */
    public void setCuadernoTipoId(java.lang.Integer cuadernoTipoId) {
        this.cuadernoTipoId = cuadernoTipoId;
    }


    /**
     * Gets the cuadernoCodigo value for this RadicarEntrada.
     * 
     * @return cuadernoCodigo
     */
    public java.lang.String getCuadernoCodigo() {
        return cuadernoCodigo;
    }


    /**
     * Sets the cuadernoCodigo value for this RadicarEntrada.
     * 
     * @param cuadernoCodigo
     */
    public void setCuadernoCodigo(java.lang.String cuadernoCodigo) {
        this.cuadernoCodigo = cuadernoCodigo;
    }


    /**
     * Gets the documentalTipoCodigo value for this RadicarEntrada.
     * 
     * @return documentalTipoCodigo
     */
    public java.lang.String getDocumentalTipoCodigo() {
        return documentalTipoCodigo;
    }


    /**
     * Sets the documentalTipoCodigo value for this RadicarEntrada.
     * 
     * @param documentalTipoCodigo
     */
    public void setDocumentalTipoCodigo(java.lang.String documentalTipoCodigo) {
        this.documentalTipoCodigo = documentalTipoCodigo;
    }


    /**
     * Gets the tramiteId value for this RadicarEntrada.
     * 
     * @return tramiteId
     */
    public java.lang.Integer getTramiteId() {
        return tramiteId;
    }


    /**
     * Sets the tramiteId value for this RadicarEntrada.
     * 
     * @param tramiteId
     */
    public void setTramiteId(java.lang.Integer tramiteId) {
        this.tramiteId = tramiteId;
    }


    /**
     * Gets the tramiteCodigo value for this RadicarEntrada.
     * 
     * @return tramiteCodigo
     */
    public java.lang.Long getTramiteCodigo() {
        return tramiteCodigo;
    }


    /**
     * Sets the tramiteCodigo value for this RadicarEntrada.
     * 
     * @param tramiteCodigo
     */
    public void setTramiteCodigo(java.lang.Long tramiteCodigo) {
        this.tramiteCodigo = tramiteCodigo;
    }


    /**
     * Gets the extensionArchivo value for this RadicarEntrada.
     * 
     * @return extensionArchivo
     */
    public java.lang.String getExtensionArchivo() {
        return extensionArchivo;
    }


    /**
     * Sets the extensionArchivo value for this RadicarEntrada.
     * 
     * @param extensionArchivo
     */
    public void setExtensionArchivo(java.lang.String extensionArchivo) {
        this.extensionArchivo = extensionArchivo;
    }


    /**
     * Gets the codigoMedioEnvio value for this RadicarEntrada.
     * 
     * @return codigoMedioEnvio
     */
    public java.lang.Integer getCodigoMedioEnvio() {
        return codigoMedioEnvio;
    }


    /**
     * Sets the codigoMedioEnvio value for this RadicarEntrada.
     * 
     * @param codigoMedioEnvio
     */
    public void setCodigoMedioEnvio(java.lang.Integer codigoMedioEnvio) {
        this.codigoMedioEnvio = codigoMedioEnvio;
    }


    /**
     * Gets the codigoTipoSeguridad value for this RadicarEntrada.
     * 
     * @return codigoTipoSeguridad
     */
    public java.lang.String getCodigoTipoSeguridad() {
        return codigoTipoSeguridad;
    }


    /**
     * Sets the codigoTipoSeguridad value for this RadicarEntrada.
     * 
     * @param codigoTipoSeguridad
     */
    public void setCodigoTipoSeguridad(java.lang.String codigoTipoSeguridad) {
        this.codigoTipoSeguridad = codigoTipoSeguridad;
    }


    /**
     * Gets the codigoSerie value for this RadicarEntrada.
     * 
     * @return codigoSerie
     */
    public java.lang.String getCodigoSerie() {
        return codigoSerie;
    }


    /**
     * Sets the codigoSerie value for this RadicarEntrada.
     * 
     * @param codigoSerie
     */
    public void setCodigoSerie(java.lang.String codigoSerie) {
        this.codigoSerie = codigoSerie;
    }


    /**
     * Gets the codigoSubSerie value for this RadicarEntrada.
     * 
     * @return codigoSubSerie
     */
    public java.lang.String getCodigoSubSerie() {
        return codigoSubSerie;
    }


    /**
     * Sets the codigoSubSerie value for this RadicarEntrada.
     * 
     * @param codigoSubSerie
     */
    public void setCodigoSubSerie(java.lang.String codigoSubSerie) {
        this.codigoSubSerie = codigoSubSerie;
    }


    /**
     * Gets the loginUsuario value for this RadicarEntrada.
     * 
     * @return loginUsuario
     */
    public java.lang.String getLoginUsuario() {
        return loginUsuario;
    }


    /**
     * Sets the loginUsuario value for this RadicarEntrada.
     * 
     * @param loginUsuario
     */
    public void setLoginUsuario(java.lang.String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }


    /**
     * Gets the radicacionAnterior value for this RadicarEntrada.
     * 
     * @return radicacionAnterior
     */
    public java.lang.String getRadicacionAnterior() {
        return radicacionAnterior;
    }


    /**
     * Sets the radicacionAnterior value for this RadicarEntrada.
     * 
     * @param radicacionAnterior
     */
    public void setRadicacionAnterior(java.lang.String radicacionAnterior) {
        this.radicacionAnterior = radicacionAnterior;
    }


    /**
     * Gets the nombreSerie value for this RadicarEntrada.
     * 
     * @return nombreSerie
     */
    public java.lang.String getNombreSerie() {
        return nombreSerie;
    }


    /**
     * Sets the nombreSerie value for this RadicarEntrada.
     * 
     * @param nombreSerie
     */
    public void setNombreSerie(java.lang.String nombreSerie) {
        this.nombreSerie = nombreSerie;
    }


    /**
     * Gets the nombreSubSerie value for this RadicarEntrada.
     * 
     * @return nombreSubSerie
     */
    public java.lang.String getNombreSubSerie() {
        return nombreSubSerie;
    }


    /**
     * Sets the nombreSubSerie value for this RadicarEntrada.
     * 
     * @param nombreSubSerie
     */
    public void setNombreSubSerie(java.lang.String nombreSubSerie) {
        this.nombreSubSerie = nombreSubSerie;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RadicarEntrada)) return false;
        RadicarEntrada other = (RadicarEntrada) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.anexosFisicos==null && other.getAnexosFisicos()==null) || 
             (this.anexosFisicos!=null &&
              this.anexosFisicos.equals(other.getAnexosFisicos()))) &&
            ((this.aplicaCiudadCodigo==null && other.getAplicaCiudadCodigo()==null) || 
             (this.aplicaCiudadCodigo!=null &&
              this.aplicaCiudadCodigo.equals(other.getAplicaCiudadCodigo()))) &&
            ((this.aplicaDepartamentoCodigo==null && other.getAplicaDepartamentoCodigo()==null) || 
             (this.aplicaDepartamentoCodigo!=null &&
              this.aplicaDepartamentoCodigo.equals(other.getAplicaDepartamentoCodigo()))) &&
            ((this.aplicaPaisCodigo==null && other.getAplicaPaisCodigo()==null) || 
             (this.aplicaPaisCodigo!=null &&
              this.aplicaPaisCodigo.equals(other.getAplicaPaisCodigo()))) &&
            ((this.aplicaEmail==null && other.getAplicaEmail()==null) || 
             (this.aplicaEmail!=null &&
              this.aplicaEmail.equals(other.getAplicaEmail()))) &&
            ((this.aplicaDireccion==null && other.getAplicaDireccion()==null) || 
             (this.aplicaDireccion!=null &&
              this.aplicaDireccion.equals(other.getAplicaDireccion()))) &&
            ((this.aplicaNombre==null && other.getAplicaNombre()==null) || 
             (this.aplicaNombre!=null &&
              this.aplicaNombre.equals(other.getAplicaNombre()))) &&
            ((this.aplicaTelefono==null && other.getAplicaTelefono()==null) || 
             (this.aplicaTelefono!=null &&
              this.aplicaTelefono.equals(other.getAplicaTelefono()))) &&
            ((this.aplicaIdentificacion==null && other.getAplicaIdentificacion()==null) || 
             (this.aplicaIdentificacion!=null &&
              this.aplicaIdentificacion.equals(other.getAplicaIdentificacion()))) &&
            ((this.aplicaTipoIdentificacionId==null && other.getAplicaTipoIdentificacionId()==null) || 
             (this.aplicaTipoIdentificacionId!=null &&
              this.aplicaTipoIdentificacionId.equals(other.getAplicaTipoIdentificacionId()))) &&
            ((this.aplicaTipoIdentificacionNombre==null && other.getAplicaTipoIdentificacionNombre()==null) || 
             (this.aplicaTipoIdentificacionNombre!=null &&
              this.aplicaTipoIdentificacionNombre.equals(other.getAplicaTipoIdentificacionNombre()))) &&
            ((this.particularIdentificacion==null && other.getParticularIdentificacion()==null) || 
             (this.particularIdentificacion!=null &&
              this.particularIdentificacion.equals(other.getParticularIdentificacion()))) &&
            ((this.particularNombre==null && other.getParticularNombre()==null) || 
             (this.particularNombre!=null &&
              this.particularNombre.equals(other.getParticularNombre()))) &&
            ((this.particularTipoIdentificacionId==null && other.getParticularTipoIdentificacionId()==null) || 
             (this.particularTipoIdentificacionId!=null &&
              this.particularTipoIdentificacionId.equals(other.getParticularTipoIdentificacionId()))) &&
            ((this.particularTipoIdentificacionNombre==null && other.getParticularTipoIdentificacionNombre()==null) || 
             (this.particularTipoIdentificacionNombre!=null &&
              this.particularTipoIdentificacionNombre.equals(other.getParticularTipoIdentificacionNombre()))) &&
            ((this.particularCiudadCodigo==null && other.getParticularCiudadCodigo()==null) || 
             (this.particularCiudadCodigo!=null &&
              this.particularCiudadCodigo.equals(other.getParticularCiudadCodigo()))) &&
            ((this.particularDepartamentoCodigo==null && other.getParticularDepartamentoCodigo()==null) || 
             (this.particularDepartamentoCodigo!=null &&
              this.particularDepartamentoCodigo.equals(other.getParticularDepartamentoCodigo()))) &&
            ((this.particularPaisCodigo==null && other.getParticularPaisCodigo()==null) || 
             (this.particularPaisCodigo!=null &&
              this.particularPaisCodigo.equals(other.getParticularPaisCodigo()))) &&
            ((this.particularDireccion==null && other.getParticularDireccion()==null) || 
             (this.particularDireccion!=null &&
              this.particularDireccion.equals(other.getParticularDireccion()))) &&
            ((this.particularTelefono==null && other.getParticularTelefono()==null) || 
             (this.particularTelefono!=null &&
              this.particularTelefono.equals(other.getParticularTelefono()))) &&
            ((this.particularEmail==null && other.getParticularEmail()==null) || 
             (this.particularEmail!=null &&
              this.particularEmail.equals(other.getParticularEmail()))) &&
            ((this.dependenciaId==null && other.getDependenciaId()==null) || 
             (this.dependenciaId!=null &&
              this.dependenciaId.equals(other.getDependenciaId()))) &&
            ((this.dependenciaNombre==null && other.getDependenciaNombre()==null) || 
             (this.dependenciaNombre!=null &&
              this.dependenciaNombre.equals(other.getDependenciaNombre()))) &&
            ((this.entregaFisica==null && other.getEntregaFisica()==null) || 
             (this.entregaFisica!=null &&
              this.entregaFisica.equals(other.getEntregaFisica()))) &&
            ((this.foliosNumero==null && other.getFoliosNumero()==null) || 
             (this.foliosNumero!=null &&
              this.foliosNumero.equals(other.getFoliosNumero()))) &&
            ((this.referenciaExterna==null && other.getReferenciaExterna()==null) || 
             (this.referenciaExterna!=null &&
              this.referenciaExterna.equals(other.getReferenciaExterna()))) &&
            ((this.cuadernoTipoId==null && other.getCuadernoTipoId()==null) || 
             (this.cuadernoTipoId!=null &&
              this.cuadernoTipoId.equals(other.getCuadernoTipoId()))) &&
            ((this.cuadernoCodigo==null && other.getCuadernoCodigo()==null) || 
             (this.cuadernoCodigo!=null &&
              this.cuadernoCodigo.equals(other.getCuadernoCodigo()))) &&
            ((this.documentalTipoCodigo==null && other.getDocumentalTipoCodigo()==null) || 
             (this.documentalTipoCodigo!=null &&
              this.documentalTipoCodigo.equals(other.getDocumentalTipoCodigo()))) &&
            ((this.tramiteId==null && other.getTramiteId()==null) || 
             (this.tramiteId!=null &&
              this.tramiteId.equals(other.getTramiteId()))) &&
            ((this.tramiteCodigo==null && other.getTramiteCodigo()==null) || 
             (this.tramiteCodigo!=null &&
              this.tramiteCodigo.equals(other.getTramiteCodigo()))) &&
            ((this.extensionArchivo==null && other.getExtensionArchivo()==null) || 
             (this.extensionArchivo!=null &&
              this.extensionArchivo.equals(other.getExtensionArchivo()))) &&
            ((this.codigoMedioEnvio==null && other.getCodigoMedioEnvio()==null) || 
             (this.codigoMedioEnvio!=null &&
              this.codigoMedioEnvio.equals(other.getCodigoMedioEnvio()))) &&
            ((this.codigoTipoSeguridad==null && other.getCodigoTipoSeguridad()==null) || 
             (this.codigoTipoSeguridad!=null &&
              this.codigoTipoSeguridad.equals(other.getCodigoTipoSeguridad()))) &&
            ((this.codigoSerie==null && other.getCodigoSerie()==null) || 
             (this.codigoSerie!=null &&
              this.codigoSerie.equals(other.getCodigoSerie()))) &&
            ((this.codigoSubSerie==null && other.getCodigoSubSerie()==null) || 
             (this.codigoSubSerie!=null &&
              this.codigoSubSerie.equals(other.getCodigoSubSerie()))) &&
            ((this.loginUsuario==null && other.getLoginUsuario()==null) || 
             (this.loginUsuario!=null &&
              this.loginUsuario.equals(other.getLoginUsuario()))) &&
            ((this.radicacionAnterior==null && other.getRadicacionAnterior()==null) || 
             (this.radicacionAnterior!=null &&
              this.radicacionAnterior.equals(other.getRadicacionAnterior()))) &&
            ((this.nombreSerie==null && other.getNombreSerie()==null) || 
             (this.nombreSerie!=null &&
              this.nombreSerie.equals(other.getNombreSerie()))) &&
            ((this.nombreSubSerie==null && other.getNombreSubSerie()==null) || 
             (this.nombreSubSerie!=null &&
              this.nombreSubSerie.equals(other.getNombreSubSerie())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getAnexosFisicos() != null) {
            _hashCode += getAnexosFisicos().hashCode();
        }
        if (getAplicaCiudadCodigo() != null) {
            _hashCode += getAplicaCiudadCodigo().hashCode();
        }
        if (getAplicaDepartamentoCodigo() != null) {
            _hashCode += getAplicaDepartamentoCodigo().hashCode();
        }
        if (getAplicaPaisCodigo() != null) {
            _hashCode += getAplicaPaisCodigo().hashCode();
        }
        if (getAplicaEmail() != null) {
            _hashCode += getAplicaEmail().hashCode();
        }
        if (getAplicaDireccion() != null) {
            _hashCode += getAplicaDireccion().hashCode();
        }
        if (getAplicaNombre() != null) {
            _hashCode += getAplicaNombre().hashCode();
        }
        if (getAplicaTelefono() != null) {
            _hashCode += getAplicaTelefono().hashCode();
        }
        if (getAplicaIdentificacion() != null) {
            _hashCode += getAplicaIdentificacion().hashCode();
        }
        if (getAplicaTipoIdentificacionId() != null) {
            _hashCode += getAplicaTipoIdentificacionId().hashCode();
        }
        if (getAplicaTipoIdentificacionNombre() != null) {
            _hashCode += getAplicaTipoIdentificacionNombre().hashCode();
        }
        if (getParticularIdentificacion() != null) {
            _hashCode += getParticularIdentificacion().hashCode();
        }
        if (getParticularNombre() != null) {
            _hashCode += getParticularNombre().hashCode();
        }
        if (getParticularTipoIdentificacionId() != null) {
            _hashCode += getParticularTipoIdentificacionId().hashCode();
        }
        if (getParticularTipoIdentificacionNombre() != null) {
            _hashCode += getParticularTipoIdentificacionNombre().hashCode();
        }
        if (getParticularCiudadCodigo() != null) {
            _hashCode += getParticularCiudadCodigo().hashCode();
        }
        if (getParticularDepartamentoCodigo() != null) {
            _hashCode += getParticularDepartamentoCodigo().hashCode();
        }
        if (getParticularPaisCodigo() != null) {
            _hashCode += getParticularPaisCodigo().hashCode();
        }
        if (getParticularDireccion() != null) {
            _hashCode += getParticularDireccion().hashCode();
        }
        if (getParticularTelefono() != null) {
            _hashCode += getParticularTelefono().hashCode();
        }
        if (getParticularEmail() != null) {
            _hashCode += getParticularEmail().hashCode();
        }
        if (getDependenciaId() != null) {
            _hashCode += getDependenciaId().hashCode();
        }
        if (getDependenciaNombre() != null) {
            _hashCode += getDependenciaNombre().hashCode();
        }
        if (getEntregaFisica() != null) {
            _hashCode += getEntregaFisica().hashCode();
        }
        if (getFoliosNumero() != null) {
            _hashCode += getFoliosNumero().hashCode();
        }
        if (getReferenciaExterna() != null) {
            _hashCode += getReferenciaExterna().hashCode();
        }
        if (getCuadernoTipoId() != null) {
            _hashCode += getCuadernoTipoId().hashCode();
        }
        if (getCuadernoCodigo() != null) {
            _hashCode += getCuadernoCodigo().hashCode();
        }
        if (getDocumentalTipoCodigo() != null) {
            _hashCode += getDocumentalTipoCodigo().hashCode();
        }
        if (getTramiteId() != null) {
            _hashCode += getTramiteId().hashCode();
        }
        if (getTramiteCodigo() != null) {
            _hashCode += getTramiteCodigo().hashCode();
        }
        if (getExtensionArchivo() != null) {
            _hashCode += getExtensionArchivo().hashCode();
        }
        if (getCodigoMedioEnvio() != null) {
            _hashCode += getCodigoMedioEnvio().hashCode();
        }
        if (getCodigoTipoSeguridad() != null) {
            _hashCode += getCodigoTipoSeguridad().hashCode();
        }
        if (getCodigoSerie() != null) {
            _hashCode += getCodigoSerie().hashCode();
        }
        if (getCodigoSubSerie() != null) {
            _hashCode += getCodigoSubSerie().hashCode();
        }
        if (getLoginUsuario() != null) {
            _hashCode += getLoginUsuario().hashCode();
        }
        if (getRadicacionAnterior() != null) {
            _hashCode += getRadicacionAnterior().hashCode();
        }
        if (getNombreSerie() != null) {
            _hashCode += getNombreSerie().hashCode();
        }
        if (getNombreSubSerie() != null) {
            _hashCode += getNombreSubSerie().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RadicarEntrada.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">RadicarEntrada"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anexosFisicos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "AnexosFisicos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aplicaCiudadCodigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "AplicaCiudadCodigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aplicaDepartamentoCodigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "AplicaDepartamentoCodigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aplicaPaisCodigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "AplicaPaisCodigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aplicaEmail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "AplicaEmail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aplicaDireccion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "AplicaDireccion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aplicaNombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "AplicaNombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aplicaTelefono");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "AplicaTelefono"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aplicaIdentificacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "AplicaIdentificacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aplicaTipoIdentificacionId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "AplicaTipoIdentificacionId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aplicaTipoIdentificacionNombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "AplicaTipoIdentificacionNombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("particularIdentificacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ParticularIdentificacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("particularNombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ParticularNombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("particularTipoIdentificacionId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ParticularTipoIdentificacionId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("particularTipoIdentificacionNombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ParticularTipoIdentificacionNombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("particularCiudadCodigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ParticularCiudadCodigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("particularDepartamentoCodigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ParticularDepartamentoCodigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("particularPaisCodigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ParticularPaisCodigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("particularDireccion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ParticularDireccion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("particularTelefono");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ParticularTelefono"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("particularEmail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ParticularEmail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dependenciaId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "DependenciaId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dependenciaNombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "DependenciaNombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entregaFisica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "EntregaFisica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("foliosNumero");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FoliosNumero"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("referenciaExterna");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ReferenciaExterna"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cuadernoTipoId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CuadernoTipoId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cuadernoCodigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CuadernoCodigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentalTipoCodigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "DocumentalTipoCodigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tramiteId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TramiteId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tramiteCodigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TramiteCodigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extensionArchivo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ExtensionArchivo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoMedioEnvio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CodigoMedioEnvio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoTipoSeguridad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CodigoTipoSeguridad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoSerie");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CodigoSerie"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoSubSerie");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CodigoSubSerie"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loginUsuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "LoginUsuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("radicacionAnterior");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "RadicacionAnterior"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreSerie");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "NombreSerie"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreSubSerie");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "NombreSubSerie"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
