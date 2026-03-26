/**
 * Borrador.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.InteropSuper_Modelos;

public class Borrador  extends org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion  implements java.io.Serializable {
    private java.lang.String anexosFisicos;

    private java.lang.String codigoCartaModelo;

    private java.lang.Long codigoCiudad;

    private java.lang.Long codigoDepartamento;

    private java.lang.Long codigoMedioDeEnvio;

    private java.lang.Long codigoPais;

    private java.lang.String codigoTipoCuaderno;

    private java.lang.String codigoTipoSeguridad;

    private java.lang.Long codigoTramite;

    private java.lang.String direccionCorresponsal;

    private java.lang.String documentoPrincipal;

    private java.lang.String[] documentosAnexos;

    private java.lang.String emailCorresponsal;

    private java.lang.Boolean entregaFisica;

    private java.lang.Long idDependencia;

    private java.lang.String identificacionAplicaA;

    private java.lang.String identificacionCorresponsal;

    private java.math.BigDecimal multa;

    private java.lang.String nombreCiudad;

    private java.lang.String nombreCorresponsal;

    private java.lang.String nombreDepartamento;

    private java.lang.String nombrePais;

    private java.lang.String radicacionAnteriorNumero;

    private java.lang.String referenciaExterna;

    private java.lang.String telefonoCorresponsal;

    private java.lang.Integer terminoDias;

    private java.lang.String tipo;

    private java.lang.String tipoCorresponsal;

    private java.lang.Long tipoIdentificacionAplicaA;

    private java.lang.Long tipoIdentificacionCorresponsal;

    private java.lang.String usuario;

    public Borrador() {
    }

    public Borrador(
           java.lang.String _anexosFisicos,
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.Particular aplicaA,
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.Corresponsal corresponsal,
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.Dependencia dependencia,
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.Dependencia dependenciaAsignada,
           java.lang.String _documentoPrincipal,
           java.lang.String[] _documentosAnexos,
           java.lang.Boolean _entregaFisica,
           java.lang.String estado,
           java.util.Calendar fecha,
           java.lang.Integer folios,
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.Funcionario funcionario,
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.Funcionario funcionarioAsignado,
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio medioDeEnvio,
           java.math.BigDecimal _multa,
           java.lang.String numero,
           java.lang.String _radicacionAnteriorNumero,
           java.lang.String _referenciaExterna,
           java.lang.Integer _terminoDias,
           java.util.Calendar terminoFecha,
           java.lang.String _tipo,
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoCuaderno tipoCuaderno,
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental tipoDocumental,
           java.lang.String tipoDocumentalConsecutivo,
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad tipoSeguridad,
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite tramite,
           java.lang.String _usuario,
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.ClasificacionDocumental clasificacionDocumental,
           java.lang.String anexosFisicos,
           java.lang.String codigoCartaModelo,
           java.lang.Long codigoCiudad,
           java.lang.Long codigoDepartamento,
           java.lang.Long codigoMedioDeEnvio,
           java.lang.Long codigoPais,
           java.lang.String codigoTipoCuaderno,
           java.lang.String codigoTipoSeguridad,
           java.lang.Long codigoTramite,
           java.lang.String direccionCorresponsal,
           java.lang.String documentoPrincipal,
           java.lang.String[] documentosAnexos,
           java.lang.String emailCorresponsal,
           java.lang.Boolean entregaFisica,
           java.lang.Long idDependencia,
           java.lang.String identificacionAplicaA,
           java.lang.String identificacionCorresponsal,
           java.math.BigDecimal multa,
           java.lang.String nombreCiudad,
           java.lang.String nombreCorresponsal,
           java.lang.String nombreDepartamento,
           java.lang.String nombrePais,
           java.lang.String radicacionAnteriorNumero,
           java.lang.String referenciaExterna,
           java.lang.String telefonoCorresponsal,
           java.lang.Integer terminoDias,
           java.lang.String tipo,
           java.lang.String tipoCorresponsal,
           java.lang.Long tipoIdentificacionAplicaA,
           java.lang.Long tipoIdentificacionCorresponsal,
           java.lang.String usuario) {
        super(
            _anexosFisicos,
            aplicaA,
            corresponsal,
            dependencia,
            dependenciaAsignada,
            _documentoPrincipal,
            _documentosAnexos,
            _entregaFisica,
            estado,
            fecha,
            folios,
            funcionario,
            funcionarioAsignado,
            medioDeEnvio,
            _multa,
            numero,
            _radicacionAnteriorNumero,
            _referenciaExterna,
            _terminoDias,
            terminoFecha,
            _tipo,
            tipoCuaderno,
            tipoDocumental,
            tipoDocumentalConsecutivo,
            tipoSeguridad,
            tramite,
            _usuario,
            clasificacionDocumental);
        this.anexosFisicos = anexosFisicos;
        this.codigoCartaModelo = codigoCartaModelo;
        this.codigoCiudad = codigoCiudad;
        this.codigoDepartamento = codigoDepartamento;
        this.codigoMedioDeEnvio = codigoMedioDeEnvio;
        this.codigoPais = codigoPais;
        this.codigoTipoCuaderno = codigoTipoCuaderno;
        this.codigoTipoSeguridad = codigoTipoSeguridad;
        this.codigoTramite = codigoTramite;
        this.direccionCorresponsal = direccionCorresponsal;
        this.documentoPrincipal = documentoPrincipal;
        this.documentosAnexos = documentosAnexos;
        this.emailCorresponsal = emailCorresponsal;
        this.entregaFisica = entregaFisica;
        this.idDependencia = idDependencia;
        this.identificacionAplicaA = identificacionAplicaA;
        this.identificacionCorresponsal = identificacionCorresponsal;
        this.multa = multa;
        this.nombreCiudad = nombreCiudad;
        this.nombreCorresponsal = nombreCorresponsal;
        this.nombreDepartamento = nombreDepartamento;
        this.nombrePais = nombrePais;
        this.radicacionAnteriorNumero = radicacionAnteriorNumero;
        this.referenciaExterna = referenciaExterna;
        this.telefonoCorresponsal = telefonoCorresponsal;
        this.terminoDias = terminoDias;
        this.tipo = tipo;
        this.tipoCorresponsal = tipoCorresponsal;
        this.tipoIdentificacionAplicaA = tipoIdentificacionAplicaA;
        this.tipoIdentificacionCorresponsal = tipoIdentificacionCorresponsal;
        this.usuario = usuario;
    }


    /**
     * Gets the anexosFisicos value for this Borrador.
     * 
     * @return anexosFisicos
     */
    public java.lang.String getAnexosFisicos() {
        return anexosFisicos;
    }


    /**
     * Sets the anexosFisicos value for this Borrador.
     * 
     * @param anexosFisicos
     */
    public void setAnexosFisicos(java.lang.String anexosFisicos) {
        this.anexosFisicos = anexosFisicos;
    }


    /**
     * Gets the codigoCartaModelo value for this Borrador.
     * 
     * @return codigoCartaModelo
     */
    public java.lang.String getCodigoCartaModelo() {
        return codigoCartaModelo;
    }


    /**
     * Sets the codigoCartaModelo value for this Borrador.
     * 
     * @param codigoCartaModelo
     */
    public void setCodigoCartaModelo(java.lang.String codigoCartaModelo) {
        this.codigoCartaModelo = codigoCartaModelo;
    }


    /**
     * Gets the codigoCiudad value for this Borrador.
     * 
     * @return codigoCiudad
     */
    public java.lang.Long getCodigoCiudad() {
        return codigoCiudad;
    }


    /**
     * Sets the codigoCiudad value for this Borrador.
     * 
     * @param codigoCiudad
     */
    public void setCodigoCiudad(java.lang.Long codigoCiudad) {
        this.codigoCiudad = codigoCiudad;
    }


    /**
     * Gets the codigoDepartamento value for this Borrador.
     * 
     * @return codigoDepartamento
     */
    public java.lang.Long getCodigoDepartamento() {
        return codigoDepartamento;
    }


    /**
     * Sets the codigoDepartamento value for this Borrador.
     * 
     * @param codigoDepartamento
     */
    public void setCodigoDepartamento(java.lang.Long codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }


    /**
     * Gets the codigoMedioDeEnvio value for this Borrador.
     * 
     * @return codigoMedioDeEnvio
     */
    public java.lang.Long getCodigoMedioDeEnvio() {
        return codigoMedioDeEnvio;
    }


    /**
     * Sets the codigoMedioDeEnvio value for this Borrador.
     * 
     * @param codigoMedioDeEnvio
     */
    public void setCodigoMedioDeEnvio(java.lang.Long codigoMedioDeEnvio) {
        this.codigoMedioDeEnvio = codigoMedioDeEnvio;
    }


    /**
     * Gets the codigoPais value for this Borrador.
     * 
     * @return codigoPais
     */
    public java.lang.Long getCodigoPais() {
        return codigoPais;
    }


    /**
     * Sets the codigoPais value for this Borrador.
     * 
     * @param codigoPais
     */
    public void setCodigoPais(java.lang.Long codigoPais) {
        this.codigoPais = codigoPais;
    }


    /**
     * Gets the codigoTipoCuaderno value for this Borrador.
     * 
     * @return codigoTipoCuaderno
     */
    public java.lang.String getCodigoTipoCuaderno() {
        return codigoTipoCuaderno;
    }


    /**
     * Sets the codigoTipoCuaderno value for this Borrador.
     * 
     * @param codigoTipoCuaderno
     */
    public void setCodigoTipoCuaderno(java.lang.String codigoTipoCuaderno) {
        this.codigoTipoCuaderno = codigoTipoCuaderno;
    }


    /**
     * Gets the codigoTipoSeguridad value for this Borrador.
     * 
     * @return codigoTipoSeguridad
     */
    public java.lang.String getCodigoTipoSeguridad() {
        return codigoTipoSeguridad;
    }


    /**
     * Sets the codigoTipoSeguridad value for this Borrador.
     * 
     * @param codigoTipoSeguridad
     */
    public void setCodigoTipoSeguridad(java.lang.String codigoTipoSeguridad) {
        this.codigoTipoSeguridad = codigoTipoSeguridad;
    }


    /**
     * Gets the codigoTramite value for this Borrador.
     * 
     * @return codigoTramite
     */
    public java.lang.Long getCodigoTramite() {
        return codigoTramite;
    }


    /**
     * Sets the codigoTramite value for this Borrador.
     * 
     * @param codigoTramite
     */
    public void setCodigoTramite(java.lang.Long codigoTramite) {
        this.codigoTramite = codigoTramite;
    }


    /**
     * Gets the direccionCorresponsal value for this Borrador.
     * 
     * @return direccionCorresponsal
     */
    public java.lang.String getDireccionCorresponsal() {
        return direccionCorresponsal;
    }


    /**
     * Sets the direccionCorresponsal value for this Borrador.
     * 
     * @param direccionCorresponsal
     */
    public void setDireccionCorresponsal(java.lang.String direccionCorresponsal) {
        this.direccionCorresponsal = direccionCorresponsal;
    }


    /**
     * Gets the documentoPrincipal value for this Borrador.
     * 
     * @return documentoPrincipal
     */
    public java.lang.String getDocumentoPrincipal() {
        return documentoPrincipal;
    }


    /**
     * Sets the documentoPrincipal value for this Borrador.
     * 
     * @param documentoPrincipal
     */
    public void setDocumentoPrincipal(java.lang.String documentoPrincipal) {
        this.documentoPrincipal = documentoPrincipal;
    }


    /**
     * Gets the documentosAnexos value for this Borrador.
     * 
     * @return documentosAnexos
     */
    public java.lang.String[] getDocumentosAnexos() {
        return documentosAnexos;
    }


    /**
     * Sets the documentosAnexos value for this Borrador.
     * 
     * @param documentosAnexos
     */
    public void setDocumentosAnexos(java.lang.String[] documentosAnexos) {
        this.documentosAnexos = documentosAnexos;
    }


    /**
     * Gets the emailCorresponsal value for this Borrador.
     * 
     * @return emailCorresponsal
     */
    public java.lang.String getEmailCorresponsal() {
        return emailCorresponsal;
    }


    /**
     * Sets the emailCorresponsal value for this Borrador.
     * 
     * @param emailCorresponsal
     */
    public void setEmailCorresponsal(java.lang.String emailCorresponsal) {
        this.emailCorresponsal = emailCorresponsal;
    }


    /**
     * Gets the entregaFisica value for this Borrador.
     * 
     * @return entregaFisica
     */
    public java.lang.Boolean getEntregaFisica() {
        return entregaFisica;
    }


    /**
     * Sets the entregaFisica value for this Borrador.
     * 
     * @param entregaFisica
     */
    public void setEntregaFisica(java.lang.Boolean entregaFisica) {
        this.entregaFisica = entregaFisica;
    }


    /**
     * Gets the idDependencia value for this Borrador.
     * 
     * @return idDependencia
     */
    public java.lang.Long getIdDependencia() {
        return idDependencia;
    }


    /**
     * Sets the idDependencia value for this Borrador.
     * 
     * @param idDependencia
     */
    public void setIdDependencia(java.lang.Long idDependencia) {
        this.idDependencia = idDependencia;
    }


    /**
     * Gets the identificacionAplicaA value for this Borrador.
     * 
     * @return identificacionAplicaA
     */
    public java.lang.String getIdentificacionAplicaA() {
        return identificacionAplicaA;
    }


    /**
     * Sets the identificacionAplicaA value for this Borrador.
     * 
     * @param identificacionAplicaA
     */
    public void setIdentificacionAplicaA(java.lang.String identificacionAplicaA) {
        this.identificacionAplicaA = identificacionAplicaA;
    }


    /**
     * Gets the identificacionCorresponsal value for this Borrador.
     * 
     * @return identificacionCorresponsal
     */
    public java.lang.String getIdentificacionCorresponsal() {
        return identificacionCorresponsal;
    }


    /**
     * Sets the identificacionCorresponsal value for this Borrador.
     * 
     * @param identificacionCorresponsal
     */
    public void setIdentificacionCorresponsal(java.lang.String identificacionCorresponsal) {
        this.identificacionCorresponsal = identificacionCorresponsal;
    }


    /**
     * Gets the multa value for this Borrador.
     * 
     * @return multa
     */
    public java.math.BigDecimal getMulta() {
        return multa;
    }


    /**
     * Sets the multa value for this Borrador.
     * 
     * @param multa
     */
    public void setMulta(java.math.BigDecimal multa) {
        this.multa = multa;
    }


    /**
     * Gets the nombreCiudad value for this Borrador.
     * 
     * @return nombreCiudad
     */
    public java.lang.String getNombreCiudad() {
        return nombreCiudad;
    }


    /**
     * Sets the nombreCiudad value for this Borrador.
     * 
     * @param nombreCiudad
     */
    public void setNombreCiudad(java.lang.String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }


    /**
     * Gets the nombreCorresponsal value for this Borrador.
     * 
     * @return nombreCorresponsal
     */
    public java.lang.String getNombreCorresponsal() {
        return nombreCorresponsal;
    }


    /**
     * Sets the nombreCorresponsal value for this Borrador.
     * 
     * @param nombreCorresponsal
     */
    public void setNombreCorresponsal(java.lang.String nombreCorresponsal) {
        this.nombreCorresponsal = nombreCorresponsal;
    }


    /**
     * Gets the nombreDepartamento value for this Borrador.
     * 
     * @return nombreDepartamento
     */
    public java.lang.String getNombreDepartamento() {
        return nombreDepartamento;
    }


    /**
     * Sets the nombreDepartamento value for this Borrador.
     * 
     * @param nombreDepartamento
     */
    public void setNombreDepartamento(java.lang.String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }


    /**
     * Gets the nombrePais value for this Borrador.
     * 
     * @return nombrePais
     */
    public java.lang.String getNombrePais() {
        return nombrePais;
    }


    /**
     * Sets the nombrePais value for this Borrador.
     * 
     * @param nombrePais
     */
    public void setNombrePais(java.lang.String nombrePais) {
        this.nombrePais = nombrePais;
    }


    /**
     * Gets the radicacionAnteriorNumero value for this Borrador.
     * 
     * @return radicacionAnteriorNumero
     */
    public java.lang.String getRadicacionAnteriorNumero() {
        return radicacionAnteriorNumero;
    }


    /**
     * Sets the radicacionAnteriorNumero value for this Borrador.
     * 
     * @param radicacionAnteriorNumero
     */
    public void setRadicacionAnteriorNumero(java.lang.String radicacionAnteriorNumero) {
        this.radicacionAnteriorNumero = radicacionAnteriorNumero;
    }


    /**
     * Gets the referenciaExterna value for this Borrador.
     * 
     * @return referenciaExterna
     */
    public java.lang.String getReferenciaExterna() {
        return referenciaExterna;
    }


    /**
     * Sets the referenciaExterna value for this Borrador.
     * 
     * @param referenciaExterna
     */
    public void setReferenciaExterna(java.lang.String referenciaExterna) {
        this.referenciaExterna = referenciaExterna;
    }


    /**
     * Gets the telefonoCorresponsal value for this Borrador.
     * 
     * @return telefonoCorresponsal
     */
    public java.lang.String getTelefonoCorresponsal() {
        return telefonoCorresponsal;
    }


    /**
     * Sets the telefonoCorresponsal value for this Borrador.
     * 
     * @param telefonoCorresponsal
     */
    public void setTelefonoCorresponsal(java.lang.String telefonoCorresponsal) {
        this.telefonoCorresponsal = telefonoCorresponsal;
    }


    /**
     * Gets the terminoDias value for this Borrador.
     * 
     * @return terminoDias
     */
    public java.lang.Integer getTerminoDias() {
        return terminoDias;
    }


    /**
     * Sets the terminoDias value for this Borrador.
     * 
     * @param terminoDias
     */
    public void setTerminoDias(java.lang.Integer terminoDias) {
        this.terminoDias = terminoDias;
    }


    /**
     * Gets the tipo value for this Borrador.
     * 
     * @return tipo
     */
    public java.lang.String getTipo() {
        return tipo;
    }


    /**
     * Sets the tipo value for this Borrador.
     * 
     * @param tipo
     */
    public void setTipo(java.lang.String tipo) {
        this.tipo = tipo;
    }


    /**
     * Gets the tipoCorresponsal value for this Borrador.
     * 
     * @return tipoCorresponsal
     */
    public java.lang.String getTipoCorresponsal() {
        return tipoCorresponsal;
    }


    /**
     * Sets the tipoCorresponsal value for this Borrador.
     * 
     * @param tipoCorresponsal
     */
    public void setTipoCorresponsal(java.lang.String tipoCorresponsal) {
        this.tipoCorresponsal = tipoCorresponsal;
    }


    /**
     * Gets the tipoIdentificacionAplicaA value for this Borrador.
     * 
     * @return tipoIdentificacionAplicaA
     */
    public java.lang.Long getTipoIdentificacionAplicaA() {
        return tipoIdentificacionAplicaA;
    }


    /**
     * Sets the tipoIdentificacionAplicaA value for this Borrador.
     * 
     * @param tipoIdentificacionAplicaA
     */
    public void setTipoIdentificacionAplicaA(java.lang.Long tipoIdentificacionAplicaA) {
        this.tipoIdentificacionAplicaA = tipoIdentificacionAplicaA;
    }


    /**
     * Gets the tipoIdentificacionCorresponsal value for this Borrador.
     * 
     * @return tipoIdentificacionCorresponsal
     */
    public java.lang.Long getTipoIdentificacionCorresponsal() {
        return tipoIdentificacionCorresponsal;
    }


    /**
     * Sets the tipoIdentificacionCorresponsal value for this Borrador.
     * 
     * @param tipoIdentificacionCorresponsal
     */
    public void setTipoIdentificacionCorresponsal(java.lang.Long tipoIdentificacionCorresponsal) {
        this.tipoIdentificacionCorresponsal = tipoIdentificacionCorresponsal;
    }


    /**
     * Gets the usuario value for this Borrador.
     * 
     * @return usuario
     */
    public java.lang.String getUsuario() {
        return usuario;
    }


    /**
     * Sets the usuario value for this Borrador.
     * 
     * @param usuario
     */
    public void setUsuario(java.lang.String usuario) {
        this.usuario = usuario;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Borrador)) return false;
        Borrador other = (Borrador) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.anexosFisicos==null && other.getAnexosFisicos()==null) || 
             (this.anexosFisicos!=null &&
              this.anexosFisicos.equals(other.getAnexosFisicos()))) &&
            ((this.codigoCartaModelo==null && other.getCodigoCartaModelo()==null) || 
             (this.codigoCartaModelo!=null &&
              this.codigoCartaModelo.equals(other.getCodigoCartaModelo()))) &&
            ((this.codigoCiudad==null && other.getCodigoCiudad()==null) || 
             (this.codigoCiudad!=null &&
              this.codigoCiudad.equals(other.getCodigoCiudad()))) &&
            ((this.codigoDepartamento==null && other.getCodigoDepartamento()==null) || 
             (this.codigoDepartamento!=null &&
              this.codigoDepartamento.equals(other.getCodigoDepartamento()))) &&
            ((this.codigoMedioDeEnvio==null && other.getCodigoMedioDeEnvio()==null) || 
             (this.codigoMedioDeEnvio!=null &&
              this.codigoMedioDeEnvio.equals(other.getCodigoMedioDeEnvio()))) &&
            ((this.codigoPais==null && other.getCodigoPais()==null) || 
             (this.codigoPais!=null &&
              this.codigoPais.equals(other.getCodigoPais()))) &&
            ((this.codigoTipoCuaderno==null && other.getCodigoTipoCuaderno()==null) || 
             (this.codigoTipoCuaderno!=null &&
              this.codigoTipoCuaderno.equals(other.getCodigoTipoCuaderno()))) &&
            ((this.codigoTipoSeguridad==null && other.getCodigoTipoSeguridad()==null) || 
             (this.codigoTipoSeguridad!=null &&
              this.codigoTipoSeguridad.equals(other.getCodigoTipoSeguridad()))) &&
            ((this.codigoTramite==null && other.getCodigoTramite()==null) || 
             (this.codigoTramite!=null &&
              this.codigoTramite.equals(other.getCodigoTramite()))) &&
            ((this.direccionCorresponsal==null && other.getDireccionCorresponsal()==null) || 
             (this.direccionCorresponsal!=null &&
              this.direccionCorresponsal.equals(other.getDireccionCorresponsal()))) &&
            ((this.documentoPrincipal==null && other.getDocumentoPrincipal()==null) || 
             (this.documentoPrincipal!=null &&
              this.documentoPrincipal.equals(other.getDocumentoPrincipal()))) &&
            ((this.documentosAnexos==null && other.getDocumentosAnexos()==null) || 
             (this.documentosAnexos!=null &&
              java.util.Arrays.equals(this.documentosAnexos, other.getDocumentosAnexos()))) &&
            ((this.emailCorresponsal==null && other.getEmailCorresponsal()==null) || 
             (this.emailCorresponsal!=null &&
              this.emailCorresponsal.equals(other.getEmailCorresponsal()))) &&
            ((this.entregaFisica==null && other.getEntregaFisica()==null) || 
             (this.entregaFisica!=null &&
              this.entregaFisica.equals(other.getEntregaFisica()))) &&
            ((this.idDependencia==null && other.getIdDependencia()==null) || 
             (this.idDependencia!=null &&
              this.idDependencia.equals(other.getIdDependencia()))) &&
            ((this.identificacionAplicaA==null && other.getIdentificacionAplicaA()==null) || 
             (this.identificacionAplicaA!=null &&
              this.identificacionAplicaA.equals(other.getIdentificacionAplicaA()))) &&
            ((this.identificacionCorresponsal==null && other.getIdentificacionCorresponsal()==null) || 
             (this.identificacionCorresponsal!=null &&
              this.identificacionCorresponsal.equals(other.getIdentificacionCorresponsal()))) &&
            ((this.multa==null && other.getMulta()==null) || 
             (this.multa!=null &&
              this.multa.equals(other.getMulta()))) &&
            ((this.nombreCiudad==null && other.getNombreCiudad()==null) || 
             (this.nombreCiudad!=null &&
              this.nombreCiudad.equals(other.getNombreCiudad()))) &&
            ((this.nombreCorresponsal==null && other.getNombreCorresponsal()==null) || 
             (this.nombreCorresponsal!=null &&
              this.nombreCorresponsal.equals(other.getNombreCorresponsal()))) &&
            ((this.nombreDepartamento==null && other.getNombreDepartamento()==null) || 
             (this.nombreDepartamento!=null &&
              this.nombreDepartamento.equals(other.getNombreDepartamento()))) &&
            ((this.nombrePais==null && other.getNombrePais()==null) || 
             (this.nombrePais!=null &&
              this.nombrePais.equals(other.getNombrePais()))) &&
            ((this.radicacionAnteriorNumero==null && other.getRadicacionAnteriorNumero()==null) || 
             (this.radicacionAnteriorNumero!=null &&
              this.radicacionAnteriorNumero.equals(other.getRadicacionAnteriorNumero()))) &&
            ((this.referenciaExterna==null && other.getReferenciaExterna()==null) || 
             (this.referenciaExterna!=null &&
              this.referenciaExterna.equals(other.getReferenciaExterna()))) &&
            ((this.telefonoCorresponsal==null && other.getTelefonoCorresponsal()==null) || 
             (this.telefonoCorresponsal!=null &&
              this.telefonoCorresponsal.equals(other.getTelefonoCorresponsal()))) &&
            ((this.terminoDias==null && other.getTerminoDias()==null) || 
             (this.terminoDias!=null &&
              this.terminoDias.equals(other.getTerminoDias()))) &&
            ((this.tipo==null && other.getTipo()==null) || 
             (this.tipo!=null &&
              this.tipo.equals(other.getTipo()))) &&
            ((this.tipoCorresponsal==null && other.getTipoCorresponsal()==null) || 
             (this.tipoCorresponsal!=null &&
              this.tipoCorresponsal.equals(other.getTipoCorresponsal()))) &&
            ((this.tipoIdentificacionAplicaA==null && other.getTipoIdentificacionAplicaA()==null) || 
             (this.tipoIdentificacionAplicaA!=null &&
              this.tipoIdentificacionAplicaA.equals(other.getTipoIdentificacionAplicaA()))) &&
            ((this.tipoIdentificacionCorresponsal==null && other.getTipoIdentificacionCorresponsal()==null) || 
             (this.tipoIdentificacionCorresponsal!=null &&
              this.tipoIdentificacionCorresponsal.equals(other.getTipoIdentificacionCorresponsal()))) &&
            ((this.usuario==null && other.getUsuario()==null) || 
             (this.usuario!=null &&
              this.usuario.equals(other.getUsuario())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getAnexosFisicos() != null) {
            _hashCode += getAnexosFisicos().hashCode();
        }
        if (getCodigoCartaModelo() != null) {
            _hashCode += getCodigoCartaModelo().hashCode();
        }
        if (getCodigoCiudad() != null) {
            _hashCode += getCodigoCiudad().hashCode();
        }
        if (getCodigoDepartamento() != null) {
            _hashCode += getCodigoDepartamento().hashCode();
        }
        if (getCodigoMedioDeEnvio() != null) {
            _hashCode += getCodigoMedioDeEnvio().hashCode();
        }
        if (getCodigoPais() != null) {
            _hashCode += getCodigoPais().hashCode();
        }
        if (getCodigoTipoCuaderno() != null) {
            _hashCode += getCodigoTipoCuaderno().hashCode();
        }
        if (getCodigoTipoSeguridad() != null) {
            _hashCode += getCodigoTipoSeguridad().hashCode();
        }
        if (getCodigoTramite() != null) {
            _hashCode += getCodigoTramite().hashCode();
        }
        if (getDireccionCorresponsal() != null) {
            _hashCode += getDireccionCorresponsal().hashCode();
        }
        if (getDocumentoPrincipal() != null) {
            _hashCode += getDocumentoPrincipal().hashCode();
        }
        if (getDocumentosAnexos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDocumentosAnexos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDocumentosAnexos(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getEmailCorresponsal() != null) {
            _hashCode += getEmailCorresponsal().hashCode();
        }
        if (getEntregaFisica() != null) {
            _hashCode += getEntregaFisica().hashCode();
        }
        if (getIdDependencia() != null) {
            _hashCode += getIdDependencia().hashCode();
        }
        if (getIdentificacionAplicaA() != null) {
            _hashCode += getIdentificacionAplicaA().hashCode();
        }
        if (getIdentificacionCorresponsal() != null) {
            _hashCode += getIdentificacionCorresponsal().hashCode();
        }
        if (getMulta() != null) {
            _hashCode += getMulta().hashCode();
        }
        if (getNombreCiudad() != null) {
            _hashCode += getNombreCiudad().hashCode();
        }
        if (getNombreCorresponsal() != null) {
            _hashCode += getNombreCorresponsal().hashCode();
        }
        if (getNombreDepartamento() != null) {
            _hashCode += getNombreDepartamento().hashCode();
        }
        if (getNombrePais() != null) {
            _hashCode += getNombrePais().hashCode();
        }
        if (getRadicacionAnteriorNumero() != null) {
            _hashCode += getRadicacionAnteriorNumero().hashCode();
        }
        if (getReferenciaExterna() != null) {
            _hashCode += getReferenciaExterna().hashCode();
        }
        if (getTelefonoCorresponsal() != null) {
            _hashCode += getTelefonoCorresponsal().hashCode();
        }
        if (getTerminoDias() != null) {
            _hashCode += getTerminoDias().hashCode();
        }
        if (getTipo() != null) {
            _hashCode += getTipo().hashCode();
        }
        if (getTipoCorresponsal() != null) {
            _hashCode += getTipoCorresponsal().hashCode();
        }
        if (getTipoIdentificacionAplicaA() != null) {
            _hashCode += getTipoIdentificacionAplicaA().hashCode();
        }
        if (getTipoIdentificacionCorresponsal() != null) {
            _hashCode += getTipoIdentificacionCorresponsal().hashCode();
        }
        if (getUsuario() != null) {
            _hashCode += getUsuario().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Borrador.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Borrador"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anexosFisicos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "anexosFisicos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoCartaModelo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "codigoCartaModelo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoCiudad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "codigoCiudad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoDepartamento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "codigoDepartamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoMedioDeEnvio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "codigoMedioDeEnvio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoPais");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "codigoPais"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoTipoCuaderno");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "codigoTipoCuaderno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoTipoSeguridad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "codigoTipoSeguridad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoTramite");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "codigoTramite"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("direccionCorresponsal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "direccionCorresponsal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentoPrincipal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "documentoPrincipal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentosAnexos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "documentosAnexos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emailCorresponsal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "emailCorresponsal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entregaFisica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "entregaFisica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idDependencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "idDependencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificacionAplicaA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "identificacionAplicaA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificacionCorresponsal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "identificacionCorresponsal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("multa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "multa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreCiudad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "nombreCiudad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreCorresponsal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "nombreCorresponsal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreDepartamento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "nombreDepartamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombrePais");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "nombrePais"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("radicacionAnteriorNumero");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "radicacionAnteriorNumero"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("referenciaExterna");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "referenciaExterna"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("telefonoCorresponsal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "telefonoCorresponsal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("terminoDias");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "terminoDias"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "tipo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoCorresponsal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "tipoCorresponsal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoIdentificacionAplicaA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "tipoIdentificacionAplicaA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoIdentificacionCorresponsal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "tipoIdentificacionCorresponsal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("usuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "usuario"));
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
