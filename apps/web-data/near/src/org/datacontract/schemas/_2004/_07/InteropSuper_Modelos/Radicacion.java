/**
 * Radicacion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.InteropSuper_Modelos;

public class Radicacion  implements java.io.Serializable {
    private java.lang.String anexosFisicos;

    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.Particular aplicaA;

    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.Corresponsal corresponsal;

    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.Dependencia dependencia;

    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.Dependencia dependenciaAsignada;

    private java.lang.String documentoPrincipal;

    private java.lang.String[] documentosAnexos;

    private java.lang.Boolean entregaFisica;

    private java.lang.String estado;

    private java.util.Calendar fecha;

    private java.lang.Integer folios;

    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.Funcionario funcionario;

    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.Funcionario funcionarioAsignado;

    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio medioDeEnvio;

    private java.math.BigDecimal multa;

    private java.lang.String numero;

    private java.lang.String radicacionAnteriorNumero;

    private java.lang.String referenciaExterna;

    private java.lang.Integer terminoDias;

    private java.util.Calendar terminoFecha;

    private java.lang.String tipo;

    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoCuaderno tipoCuaderno;

    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental tipoDocumental;

    private java.lang.String tipoDocumentalConsecutivo;

    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad tipoSeguridad;

    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite tramite;

    private java.lang.String usuario;

    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.ClasificacionDocumental clasificacionDocumental;

    public Radicacion() {
    }

    public Radicacion(
           java.lang.String anexosFisicos,
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.Particular aplicaA,
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.Corresponsal corresponsal,
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.Dependencia dependencia,
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.Dependencia dependenciaAsignada,
           java.lang.String documentoPrincipal,
           java.lang.String[] documentosAnexos,
           java.lang.Boolean entregaFisica,
           java.lang.String estado,
           java.util.Calendar fecha,
           java.lang.Integer folios,
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.Funcionario funcionario,
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.Funcionario funcionarioAsignado,
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio medioDeEnvio,
           java.math.BigDecimal multa,
           java.lang.String numero,
           java.lang.String radicacionAnteriorNumero,
           java.lang.String referenciaExterna,
           java.lang.Integer terminoDias,
           java.util.Calendar terminoFecha,
           java.lang.String tipo,
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoCuaderno tipoCuaderno,
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental tipoDocumental,
           java.lang.String tipoDocumentalConsecutivo,
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad tipoSeguridad,
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite tramite,
           java.lang.String usuario,
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.ClasificacionDocumental clasificacionDocumental) {
           this.anexosFisicos = anexosFisicos;
           this.aplicaA = aplicaA;
           this.corresponsal = corresponsal;
           this.dependencia = dependencia;
           this.dependenciaAsignada = dependenciaAsignada;
           this.documentoPrincipal = documentoPrincipal;
           this.documentosAnexos = documentosAnexos;
           this.entregaFisica = entregaFisica;
           this.estado = estado;
           this.fecha = fecha;
           this.folios = folios;
           this.funcionario = funcionario;
           this.funcionarioAsignado = funcionarioAsignado;
           this.medioDeEnvio = medioDeEnvio;
           this.multa = multa;
           this.numero = numero;
           this.radicacionAnteriorNumero = radicacionAnteriorNumero;
           this.referenciaExterna = referenciaExterna;
           this.terminoDias = terminoDias;
           this.terminoFecha = terminoFecha;
           this.tipo = tipo;
           this.tipoCuaderno = tipoCuaderno;
           this.tipoDocumental = tipoDocumental;
           this.tipoDocumentalConsecutivo = tipoDocumentalConsecutivo;
           this.tipoSeguridad = tipoSeguridad;
           this.tramite = tramite;
           this.usuario = usuario;
           this.clasificacionDocumental = clasificacionDocumental;
    }


    /**
     * Gets the anexosFisicos value for this Radicacion.
     * 
     * @return anexosFisicos
     */
    public java.lang.String getAnexosFisicos() {
        return anexosFisicos;
    }


    /**
     * Sets the anexosFisicos value for this Radicacion.
     * 
     * @param anexosFisicos
     */
    public void setAnexosFisicos(java.lang.String anexosFisicos) {
        this.anexosFisicos = anexosFisicos;
    }


    /**
     * Gets the aplicaA value for this Radicacion.
     * 
     * @return aplicaA
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Particular getAplicaA() {
        return aplicaA;
    }


    /**
     * Sets the aplicaA value for this Radicacion.
     * 
     * @param aplicaA
     */
    public void setAplicaA(org.datacontract.schemas._2004._07.InteropSuper_Modelos.Particular aplicaA) {
        this.aplicaA = aplicaA;
    }


    /**
     * Gets the corresponsal value for this Radicacion.
     * 
     * @return corresponsal
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Corresponsal getCorresponsal() {
        return corresponsal;
    }


    /**
     * Sets the corresponsal value for this Radicacion.
     * 
     * @param corresponsal
     */
    public void setCorresponsal(org.datacontract.schemas._2004._07.InteropSuper_Modelos.Corresponsal corresponsal) {
        this.corresponsal = corresponsal;
    }


    /**
     * Gets the dependencia value for this Radicacion.
     * 
     * @return dependencia
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Dependencia getDependencia() {
        return dependencia;
    }


    /**
     * Sets the dependencia value for this Radicacion.
     * 
     * @param dependencia
     */
    public void setDependencia(org.datacontract.schemas._2004._07.InteropSuper_Modelos.Dependencia dependencia) {
        this.dependencia = dependencia;
    }


    /**
     * Gets the dependenciaAsignada value for this Radicacion.
     * 
     * @return dependenciaAsignada
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Dependencia getDependenciaAsignada() {
        return dependenciaAsignada;
    }


    /**
     * Sets the dependenciaAsignada value for this Radicacion.
     * 
     * @param dependenciaAsignada
     */
    public void setDependenciaAsignada(org.datacontract.schemas._2004._07.InteropSuper_Modelos.Dependencia dependenciaAsignada) {
        this.dependenciaAsignada = dependenciaAsignada;
    }


    /**
     * Gets the documentoPrincipal value for this Radicacion.
     * 
     * @return documentoPrincipal
     */
    public java.lang.String getDocumentoPrincipal() {
        return documentoPrincipal;
    }


    /**
     * Sets the documentoPrincipal value for this Radicacion.
     * 
     * @param documentoPrincipal
     */
    public void setDocumentoPrincipal(java.lang.String documentoPrincipal) {
        this.documentoPrincipal = documentoPrincipal;
    }


    /**
     * Gets the documentosAnexos value for this Radicacion.
     * 
     * @return documentosAnexos
     */
    public java.lang.String[] getDocumentosAnexos() {
        return documentosAnexos;
    }


    /**
     * Sets the documentosAnexos value for this Radicacion.
     * 
     * @param documentosAnexos
     */
    public void setDocumentosAnexos(java.lang.String[] documentosAnexos) {
        this.documentosAnexos = documentosAnexos;
    }


    /**
     * Gets the entregaFisica value for this Radicacion.
     * 
     * @return entregaFisica
     */
    public java.lang.Boolean getEntregaFisica() {
        return entregaFisica;
    }


    /**
     * Sets the entregaFisica value for this Radicacion.
     * 
     * @param entregaFisica
     */
    public void setEntregaFisica(java.lang.Boolean entregaFisica) {
        this.entregaFisica = entregaFisica;
    }


    /**
     * Gets the estado value for this Radicacion.
     * 
     * @return estado
     */
    public java.lang.String getEstado() {
        return estado;
    }


    /**
     * Sets the estado value for this Radicacion.
     * 
     * @param estado
     */
    public void setEstado(java.lang.String estado) {
        this.estado = estado;
    }


    /**
     * Gets the fecha value for this Radicacion.
     * 
     * @return fecha
     */
    public java.util.Calendar getFecha() {
        return fecha;
    }


    /**
     * Sets the fecha value for this Radicacion.
     * 
     * @param fecha
     */
    public void setFecha(java.util.Calendar fecha) {
        this.fecha = fecha;
    }


    /**
     * Gets the folios value for this Radicacion.
     * 
     * @return folios
     */
    public java.lang.Integer getFolios() {
        return folios;
    }


    /**
     * Sets the folios value for this Radicacion.
     * 
     * @param folios
     */
    public void setFolios(java.lang.Integer folios) {
        this.folios = folios;
    }


    /**
     * Gets the funcionario value for this Radicacion.
     * 
     * @return funcionario
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Funcionario getFuncionario() {
        return funcionario;
    }


    /**
     * Sets the funcionario value for this Radicacion.
     * 
     * @param funcionario
     */
    public void setFuncionario(org.datacontract.schemas._2004._07.InteropSuper_Modelos.Funcionario funcionario) {
        this.funcionario = funcionario;
    }


    /**
     * Gets the funcionarioAsignado value for this Radicacion.
     * 
     * @return funcionarioAsignado
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Funcionario getFuncionarioAsignado() {
        return funcionarioAsignado;
    }


    /**
     * Sets the funcionarioAsignado value for this Radicacion.
     * 
     * @param funcionarioAsignado
     */
    public void setFuncionarioAsignado(org.datacontract.schemas._2004._07.InteropSuper_Modelos.Funcionario funcionarioAsignado) {
        this.funcionarioAsignado = funcionarioAsignado;
    }


    /**
     * Gets the medioDeEnvio value for this Radicacion.
     * 
     * @return medioDeEnvio
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio getMedioDeEnvio() {
        return medioDeEnvio;
    }


    /**
     * Sets the medioDeEnvio value for this Radicacion.
     * 
     * @param medioDeEnvio
     */
    public void setMedioDeEnvio(org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio medioDeEnvio) {
        this.medioDeEnvio = medioDeEnvio;
    }


    /**
     * Gets the multa value for this Radicacion.
     * 
     * @return multa
     */
    public java.math.BigDecimal getMulta() {
        return multa;
    }


    /**
     * Sets the multa value for this Radicacion.
     * 
     * @param multa
     */
    public void setMulta(java.math.BigDecimal multa) {
        this.multa = multa;
    }


    /**
     * Gets the numero value for this Radicacion.
     * 
     * @return numero
     */
    public java.lang.String getNumero() {
        return numero;
    }


    /**
     * Sets the numero value for this Radicacion.
     * 
     * @param numero
     */
    public void setNumero(java.lang.String numero) {
        this.numero = numero;
    }


    /**
     * Gets the radicacionAnteriorNumero value for this Radicacion.
     * 
     * @return radicacionAnteriorNumero
     */
    public java.lang.String getRadicacionAnteriorNumero() {
        return radicacionAnteriorNumero;
    }


    /**
     * Sets the radicacionAnteriorNumero value for this Radicacion.
     * 
     * @param radicacionAnteriorNumero
     */
    public void setRadicacionAnteriorNumero(java.lang.String radicacionAnteriorNumero) {
        this.radicacionAnteriorNumero = radicacionAnteriorNumero;
    }


    /**
     * Gets the referenciaExterna value for this Radicacion.
     * 
     * @return referenciaExterna
     */
    public java.lang.String getReferenciaExterna() {
        return referenciaExterna;
    }


    /**
     * Sets the referenciaExterna value for this Radicacion.
     * 
     * @param referenciaExterna
     */
    public void setReferenciaExterna(java.lang.String referenciaExterna) {
        this.referenciaExterna = referenciaExterna;
    }


    /**
     * Gets the terminoDias value for this Radicacion.
     * 
     * @return terminoDias
     */
    public java.lang.Integer getTerminoDias() {
        return terminoDias;
    }


    /**
     * Sets the terminoDias value for this Radicacion.
     * 
     * @param terminoDias
     */
    public void setTerminoDias(java.lang.Integer terminoDias) {
        this.terminoDias = terminoDias;
    }


    /**
     * Gets the terminoFecha value for this Radicacion.
     * 
     * @return terminoFecha
     */
    public java.util.Calendar getTerminoFecha() {
        return terminoFecha;
    }


    /**
     * Sets the terminoFecha value for this Radicacion.
     * 
     * @param terminoFecha
     */
    public void setTerminoFecha(java.util.Calendar terminoFecha) {
        this.terminoFecha = terminoFecha;
    }


    /**
     * Gets the tipo value for this Radicacion.
     * 
     * @return tipo
     */
    public java.lang.String getTipo() {
        return tipo;
    }


    /**
     * Sets the tipo value for this Radicacion.
     * 
     * @param tipo
     */
    public void setTipo(java.lang.String tipo) {
        this.tipo = tipo;
    }


    /**
     * Gets the tipoCuaderno value for this Radicacion.
     * 
     * @return tipoCuaderno
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoCuaderno getTipoCuaderno() {
        return tipoCuaderno;
    }


    /**
     * Sets the tipoCuaderno value for this Radicacion.
     * 
     * @param tipoCuaderno
     */
    public void setTipoCuaderno(org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoCuaderno tipoCuaderno) {
        this.tipoCuaderno = tipoCuaderno;
    }


    /**
     * Gets the tipoDocumental value for this Radicacion.
     * 
     * @return tipoDocumental
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental getTipoDocumental() {
        return tipoDocumental;
    }


    /**
     * Sets the tipoDocumental value for this Radicacion.
     * 
     * @param tipoDocumental
     */
    public void setTipoDocumental(org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental tipoDocumental) {
        this.tipoDocumental = tipoDocumental;
    }


    /**
     * Gets the tipoDocumentalConsecutivo value for this Radicacion.
     * 
     * @return tipoDocumentalConsecutivo
     */
    public java.lang.String getTipoDocumentalConsecutivo() {
        return tipoDocumentalConsecutivo;
    }


    /**
     * Sets the tipoDocumentalConsecutivo value for this Radicacion.
     * 
     * @param tipoDocumentalConsecutivo
     */
    public void setTipoDocumentalConsecutivo(java.lang.String tipoDocumentalConsecutivo) {
        this.tipoDocumentalConsecutivo = tipoDocumentalConsecutivo;
    }


    /**
     * Gets the tipoSeguridad value for this Radicacion.
     * 
     * @return tipoSeguridad
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad getTipoSeguridad() {
        return tipoSeguridad;
    }


    /**
     * Sets the tipoSeguridad value for this Radicacion.
     * 
     * @param tipoSeguridad
     */
    public void setTipoSeguridad(org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad tipoSeguridad) {
        this.tipoSeguridad = tipoSeguridad;
    }


    /**
     * Gets the tramite value for this Radicacion.
     * 
     * @return tramite
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite getTramite() {
        return tramite;
    }


    /**
     * Sets the tramite value for this Radicacion.
     * 
     * @param tramite
     */
    public void setTramite(org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite tramite) {
        this.tramite = tramite;
    }


    /**
     * Gets the usuario value for this Radicacion.
     * 
     * @return usuario
     */
    public java.lang.String getUsuario() {
        return usuario;
    }


    /**
     * Sets the usuario value for this Radicacion.
     * 
     * @param usuario
     */
    public void setUsuario(java.lang.String usuario) {
        this.usuario = usuario;
    }


    /**
     * Gets the clasificacionDocumental value for this Radicacion.
     * 
     * @return clasificacionDocumental
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.ClasificacionDocumental getClasificacionDocumental() {
        return clasificacionDocumental;
    }


    /**
     * Sets the clasificacionDocumental value for this Radicacion.
     * 
     * @param clasificacionDocumental
     */
    public void setClasificacionDocumental(org.datacontract.schemas._2004._07.InteropSuper_Modelos.ClasificacionDocumental clasificacionDocumental) {
        this.clasificacionDocumental = clasificacionDocumental;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Radicacion)) return false;
        Radicacion other = (Radicacion) obj;
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
            ((this.aplicaA==null && other.getAplicaA()==null) || 
             (this.aplicaA!=null &&
              this.aplicaA.equals(other.getAplicaA()))) &&
            ((this.corresponsal==null && other.getCorresponsal()==null) || 
             (this.corresponsal!=null &&
              this.corresponsal.equals(other.getCorresponsal()))) &&
            ((this.dependencia==null && other.getDependencia()==null) || 
             (this.dependencia!=null &&
              this.dependencia.equals(other.getDependencia()))) &&
            ((this.dependenciaAsignada==null && other.getDependenciaAsignada()==null) || 
             (this.dependenciaAsignada!=null &&
              this.dependenciaAsignada.equals(other.getDependenciaAsignada()))) &&
            ((this.documentoPrincipal==null && other.getDocumentoPrincipal()==null) || 
             (this.documentoPrincipal!=null &&
              this.documentoPrincipal.equals(other.getDocumentoPrincipal()))) &&
            ((this.documentosAnexos==null && other.getDocumentosAnexos()==null) || 
             (this.documentosAnexos!=null &&
              java.util.Arrays.equals(this.documentosAnexos, other.getDocumentosAnexos()))) &&
            ((this.entregaFisica==null && other.getEntregaFisica()==null) || 
             (this.entregaFisica!=null &&
              this.entregaFisica.equals(other.getEntregaFisica()))) &&
            ((this.estado==null && other.getEstado()==null) || 
             (this.estado!=null &&
              this.estado.equals(other.getEstado()))) &&
            ((this.fecha==null && other.getFecha()==null) || 
             (this.fecha!=null &&
              this.fecha.equals(other.getFecha()))) &&
            ((this.folios==null && other.getFolios()==null) || 
             (this.folios!=null &&
              this.folios.equals(other.getFolios()))) &&
            ((this.funcionario==null && other.getFuncionario()==null) || 
             (this.funcionario!=null &&
              this.funcionario.equals(other.getFuncionario()))) &&
            ((this.funcionarioAsignado==null && other.getFuncionarioAsignado()==null) || 
             (this.funcionarioAsignado!=null &&
              this.funcionarioAsignado.equals(other.getFuncionarioAsignado()))) &&
            ((this.medioDeEnvio==null && other.getMedioDeEnvio()==null) || 
             (this.medioDeEnvio!=null &&
              this.medioDeEnvio.equals(other.getMedioDeEnvio()))) &&
            ((this.multa==null && other.getMulta()==null) || 
             (this.multa!=null &&
              this.multa.equals(other.getMulta()))) &&
            ((this.numero==null && other.getNumero()==null) || 
             (this.numero!=null &&
              this.numero.equals(other.getNumero()))) &&
            ((this.radicacionAnteriorNumero==null && other.getRadicacionAnteriorNumero()==null) || 
             (this.radicacionAnteriorNumero!=null &&
              this.radicacionAnteriorNumero.equals(other.getRadicacionAnteriorNumero()))) &&
            ((this.referenciaExterna==null && other.getReferenciaExterna()==null) || 
             (this.referenciaExterna!=null &&
              this.referenciaExterna.equals(other.getReferenciaExterna()))) &&
            ((this.terminoDias==null && other.getTerminoDias()==null) || 
             (this.terminoDias!=null &&
              this.terminoDias.equals(other.getTerminoDias()))) &&
            ((this.terminoFecha==null && other.getTerminoFecha()==null) || 
             (this.terminoFecha!=null &&
              this.terminoFecha.equals(other.getTerminoFecha()))) &&
            ((this.tipo==null && other.getTipo()==null) || 
             (this.tipo!=null &&
              this.tipo.equals(other.getTipo()))) &&
            ((this.tipoCuaderno==null && other.getTipoCuaderno()==null) || 
             (this.tipoCuaderno!=null &&
              this.tipoCuaderno.equals(other.getTipoCuaderno()))) &&
            ((this.tipoDocumental==null && other.getTipoDocumental()==null) || 
             (this.tipoDocumental!=null &&
              this.tipoDocumental.equals(other.getTipoDocumental()))) &&
            ((this.tipoDocumentalConsecutivo==null && other.getTipoDocumentalConsecutivo()==null) || 
             (this.tipoDocumentalConsecutivo!=null &&
              this.tipoDocumentalConsecutivo.equals(other.getTipoDocumentalConsecutivo()))) &&
            ((this.tipoSeguridad==null && other.getTipoSeguridad()==null) || 
             (this.tipoSeguridad!=null &&
              this.tipoSeguridad.equals(other.getTipoSeguridad()))) &&
            ((this.tramite==null && other.getTramite()==null) || 
             (this.tramite!=null &&
              this.tramite.equals(other.getTramite()))) &&
            ((this.usuario==null && other.getUsuario()==null) || 
             (this.usuario!=null &&
              this.usuario.equals(other.getUsuario()))) &&
            ((this.clasificacionDocumental==null && other.getClasificacionDocumental()==null) || 
             (this.clasificacionDocumental!=null &&
              this.clasificacionDocumental.equals(other.getClasificacionDocumental())));
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
        if (getAplicaA() != null) {
            _hashCode += getAplicaA().hashCode();
        }
        if (getCorresponsal() != null) {
            _hashCode += getCorresponsal().hashCode();
        }
        if (getDependencia() != null) {
            _hashCode += getDependencia().hashCode();
        }
        if (getDependenciaAsignada() != null) {
            _hashCode += getDependenciaAsignada().hashCode();
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
        if (getEntregaFisica() != null) {
            _hashCode += getEntregaFisica().hashCode();
        }
        if (getEstado() != null) {
            _hashCode += getEstado().hashCode();
        }
        if (getFecha() != null) {
            _hashCode += getFecha().hashCode();
        }
        if (getFolios() != null) {
            _hashCode += getFolios().hashCode();
        }
        if (getFuncionario() != null) {
            _hashCode += getFuncionario().hashCode();
        }
        if (getFuncionarioAsignado() != null) {
            _hashCode += getFuncionarioAsignado().hashCode();
        }
        if (getMedioDeEnvio() != null) {
            _hashCode += getMedioDeEnvio().hashCode();
        }
        if (getMulta() != null) {
            _hashCode += getMulta().hashCode();
        }
        if (getNumero() != null) {
            _hashCode += getNumero().hashCode();
        }
        if (getRadicacionAnteriorNumero() != null) {
            _hashCode += getRadicacionAnteriorNumero().hashCode();
        }
        if (getReferenciaExterna() != null) {
            _hashCode += getReferenciaExterna().hashCode();
        }
        if (getTerminoDias() != null) {
            _hashCode += getTerminoDias().hashCode();
        }
        if (getTerminoFecha() != null) {
            _hashCode += getTerminoFecha().hashCode();
        }
        if (getTipo() != null) {
            _hashCode += getTipo().hashCode();
        }
        if (getTipoCuaderno() != null) {
            _hashCode += getTipoCuaderno().hashCode();
        }
        if (getTipoDocumental() != null) {
            _hashCode += getTipoDocumental().hashCode();
        }
        if (getTipoDocumentalConsecutivo() != null) {
            _hashCode += getTipoDocumentalConsecutivo().hashCode();
        }
        if (getTipoSeguridad() != null) {
            _hashCode += getTipoSeguridad().hashCode();
        }
        if (getTramite() != null) {
            _hashCode += getTramite().hashCode();
        }
        if (getUsuario() != null) {
            _hashCode += getUsuario().hashCode();
        }
        if (getClasificacionDocumental() != null) {
            _hashCode += getClasificacionDocumental().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Radicacion.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Radicacion"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anexosFisicos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "AnexosFisicos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aplicaA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "AplicaA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Particular"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("corresponsal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Corresponsal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Corresponsal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dependencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Dependencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Dependencia"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dependenciaAsignada");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "DependenciaAsignada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Dependencia"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentoPrincipal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "DocumentoPrincipal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentosAnexos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "DocumentosAnexos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entregaFisica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "EntregaFisica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Estado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecha");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Fecha"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("folios");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Folios"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("funcionario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Funcionario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Funcionario"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("funcionarioAsignado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "FuncionarioAsignado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Funcionario"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("medioDeEnvio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "MedioDeEnvio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "MedioDeEnvio"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("multa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Multa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numero");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Numero"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("radicacionAnteriorNumero");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "RadicacionAnteriorNumero"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("referenciaExterna");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "ReferenciaExterna"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("terminoDias");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "TerminoDias"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("terminoFecha");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "TerminoFecha"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Tipo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoCuaderno");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "TipoCuaderno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "TipoCuaderno"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoDocumental");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "TipoDocumental"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "TipoDocumental"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoDocumentalConsecutivo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "TipoDocumentalConsecutivo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoSeguridad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "TipoSeguridad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "DocumentoTipoSeguridad"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tramite");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Tramite"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Tramite"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("usuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Usuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clasificacionDocumental");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "clasificacionDocumental"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "clasificacionDocumental"));
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
