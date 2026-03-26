/**
 * Borrador.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tandem.postal.dto;

public class Borrador  implements java.io.Serializable {
    private java.lang.String anexosFisicos;

    private com.tandem.postal.dto.Particular aplicaA;

    private com.tandem.postal.dto.CartaModelo cartaModelo;

    private com.tandem.postal.dto.Corresponsal corresponsal;

    private com.tandem.postal.dto.Dependencia dependencia;

    private com.tandem.postal.dto.Dependencia dependenciaAsignada;

    private java.lang.String documentoPrincipal;

    private java.lang.String[] documentosAnexos;

    private java.lang.Boolean entregaFisica;

    private java.util.Calendar fecha;

    private com.tandem.postal.dto.Funcionario funcionario;

    private com.tandem.postal.dto.Funcionario funcionarioAsignado;

    private com.tandem.postal.dto.MedioDeEnvio medioDeEnvio;

    private java.math.BigDecimal multa;

    private java.lang.String numero;

    private java.lang.String radicacionAnteriorNumero;

    private java.lang.String referenciaExterna;

    private java.lang.Integer terminoDias;

    private java.lang.String tipo;

    private com.tandem.postal.dto.TipoCuaderno tipoCuaderno;

    private com.tandem.postal.dto.DocumentoTipoSeguridad tipoSeguridad;

    private com.tandem.postal.dto.Tramite tramite;

    private java.lang.String usuario;

    public Borrador() {
    }

    public Borrador(
           java.lang.String anexosFisicos,
           com.tandem.postal.dto.Particular aplicaA,
           com.tandem.postal.dto.CartaModelo cartaModelo,
           com.tandem.postal.dto.Corresponsal corresponsal,
           com.tandem.postal.dto.Dependencia dependencia,
           com.tandem.postal.dto.Dependencia dependenciaAsignada,
           java.lang.String documentoPrincipal,
           java.lang.String[] documentosAnexos,
           java.lang.Boolean entregaFisica,
           java.util.Calendar fecha,
           com.tandem.postal.dto.Funcionario funcionario,
           com.tandem.postal.dto.Funcionario funcionarioAsignado,
           com.tandem.postal.dto.MedioDeEnvio medioDeEnvio,
           java.math.BigDecimal multa,
           java.lang.String numero,
           java.lang.String radicacionAnteriorNumero,
           java.lang.String referenciaExterna,
           java.lang.Integer terminoDias,
           java.lang.String tipo,
           com.tandem.postal.dto.TipoCuaderno tipoCuaderno,
           com.tandem.postal.dto.DocumentoTipoSeguridad tipoSeguridad,
           com.tandem.postal.dto.Tramite tramite,
           java.lang.String usuario) {
           this.anexosFisicos = anexosFisicos;
           this.aplicaA = aplicaA;
           this.cartaModelo = cartaModelo;
           this.corresponsal = corresponsal;
           this.dependencia = dependencia;
           this.dependenciaAsignada = dependenciaAsignada;
           this.documentoPrincipal = documentoPrincipal;
           this.documentosAnexos = documentosAnexos;
           this.entregaFisica = entregaFisica;
           this.fecha = fecha;
           this.funcionario = funcionario;
           this.funcionarioAsignado = funcionarioAsignado;
           this.medioDeEnvio = medioDeEnvio;
           this.multa = multa;
           this.numero = numero;
           this.radicacionAnteriorNumero = radicacionAnteriorNumero;
           this.referenciaExterna = referenciaExterna;
           this.terminoDias = terminoDias;
           this.tipo = tipo;
           this.tipoCuaderno = tipoCuaderno;
           this.tipoSeguridad = tipoSeguridad;
           this.tramite = tramite;
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
     * Gets the aplicaA value for this Borrador.
     * 
     * @return aplicaA
     */
    public com.tandem.postal.dto.Particular getAplicaA() {
        return aplicaA;
    }


    /**
     * Sets the aplicaA value for this Borrador.
     * 
     * @param aplicaA
     */
    public void setAplicaA(com.tandem.postal.dto.Particular aplicaA) {
        this.aplicaA = aplicaA;
    }


    /**
     * Gets the cartaModelo value for this Borrador.
     * 
     * @return cartaModelo
     */
    public com.tandem.postal.dto.CartaModelo getCartaModelo() {
        return cartaModelo;
    }


    /**
     * Sets the cartaModelo value for this Borrador.
     * 
     * @param cartaModelo
     */
    public void setCartaModelo(com.tandem.postal.dto.CartaModelo cartaModelo) {
        this.cartaModelo = cartaModelo;
    }


    /**
     * Gets the corresponsal value for this Borrador.
     * 
     * @return corresponsal
     */
    public com.tandem.postal.dto.Corresponsal getCorresponsal() {
        return corresponsal;
    }


    /**
     * Sets the corresponsal value for this Borrador.
     * 
     * @param corresponsal
     */
    public void setCorresponsal(com.tandem.postal.dto.Corresponsal corresponsal) {
        this.corresponsal = corresponsal;
    }


    /**
     * Gets the dependencia value for this Borrador.
     * 
     * @return dependencia
     */
    public com.tandem.postal.dto.Dependencia getDependencia() {
        return dependencia;
    }


    /**
     * Sets the dependencia value for this Borrador.
     * 
     * @param dependencia
     */
    public void setDependencia(com.tandem.postal.dto.Dependencia dependencia) {
        this.dependencia = dependencia;
    }


    /**
     * Gets the dependenciaAsignada value for this Borrador.
     * 
     * @return dependenciaAsignada
     */
    public com.tandem.postal.dto.Dependencia getDependenciaAsignada() {
        return dependenciaAsignada;
    }


    /**
     * Sets the dependenciaAsignada value for this Borrador.
     * 
     * @param dependenciaAsignada
     */
    public void setDependenciaAsignada(com.tandem.postal.dto.Dependencia dependenciaAsignada) {
        this.dependenciaAsignada = dependenciaAsignada;
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
     * Gets the fecha value for this Borrador.
     * 
     * @return fecha
     */
    public java.util.Calendar getFecha() {
        return fecha;
    }


    /**
     * Sets the fecha value for this Borrador.
     * 
     * @param fecha
     */
    public void setFecha(java.util.Calendar fecha) {
        this.fecha = fecha;
    }


    /**
     * Gets the funcionario value for this Borrador.
     * 
     * @return funcionario
     */
    public com.tandem.postal.dto.Funcionario getFuncionario() {
        return funcionario;
    }


    /**
     * Sets the funcionario value for this Borrador.
     * 
     * @param funcionario
     */
    public void setFuncionario(com.tandem.postal.dto.Funcionario funcionario) {
        this.funcionario = funcionario;
    }


    /**
     * Gets the funcionarioAsignado value for this Borrador.
     * 
     * @return funcionarioAsignado
     */
    public com.tandem.postal.dto.Funcionario getFuncionarioAsignado() {
        return funcionarioAsignado;
    }


    /**
     * Sets the funcionarioAsignado value for this Borrador.
     * 
     * @param funcionarioAsignado
     */
    public void setFuncionarioAsignado(com.tandem.postal.dto.Funcionario funcionarioAsignado) {
        this.funcionarioAsignado = funcionarioAsignado;
    }


    /**
     * Gets the medioDeEnvio value for this Borrador.
     * 
     * @return medioDeEnvio
     */
    public com.tandem.postal.dto.MedioDeEnvio getMedioDeEnvio() {
        return medioDeEnvio;
    }


    /**
     * Sets the medioDeEnvio value for this Borrador.
     * 
     * @param medioDeEnvio
     */
    public void setMedioDeEnvio(com.tandem.postal.dto.MedioDeEnvio medioDeEnvio) {
        this.medioDeEnvio = medioDeEnvio;
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
     * Gets the numero value for this Borrador.
     * 
     * @return numero
     */
    public java.lang.String getNumero() {
        return numero;
    }


    /**
     * Sets the numero value for this Borrador.
     * 
     * @param numero
     */
    public void setNumero(java.lang.String numero) {
        this.numero = numero;
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
     * Gets the tipoCuaderno value for this Borrador.
     * 
     * @return tipoCuaderno
     */
    public com.tandem.postal.dto.TipoCuaderno getTipoCuaderno() {
        return tipoCuaderno;
    }


    /**
     * Sets the tipoCuaderno value for this Borrador.
     * 
     * @param tipoCuaderno
     */
    public void setTipoCuaderno(com.tandem.postal.dto.TipoCuaderno tipoCuaderno) {
        this.tipoCuaderno = tipoCuaderno;
    }


    /**
     * Gets the tipoSeguridad value for this Borrador.
     * 
     * @return tipoSeguridad
     */
    public com.tandem.postal.dto.DocumentoTipoSeguridad getTipoSeguridad() {
        return tipoSeguridad;
    }


    /**
     * Sets the tipoSeguridad value for this Borrador.
     * 
     * @param tipoSeguridad
     */
    public void setTipoSeguridad(com.tandem.postal.dto.DocumentoTipoSeguridad tipoSeguridad) {
        this.tipoSeguridad = tipoSeguridad;
    }


    /**
     * Gets the tramite value for this Borrador.
     * 
     * @return tramite
     */
    public com.tandem.postal.dto.Tramite getTramite() {
        return tramite;
    }


    /**
     * Sets the tramite value for this Borrador.
     * 
     * @param tramite
     */
    public void setTramite(com.tandem.postal.dto.Tramite tramite) {
        this.tramite = tramite;
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
        _equals = true && 
            ((this.anexosFisicos==null && other.getAnexosFisicos()==null) || 
             (this.anexosFisicos!=null &&
              this.anexosFisicos.equals(other.getAnexosFisicos()))) &&
            ((this.aplicaA==null && other.getAplicaA()==null) || 
             (this.aplicaA!=null &&
              this.aplicaA.equals(other.getAplicaA()))) &&
            ((this.cartaModelo==null && other.getCartaModelo()==null) || 
             (this.cartaModelo!=null &&
              this.cartaModelo.equals(other.getCartaModelo()))) &&
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
            ((this.fecha==null && other.getFecha()==null) || 
             (this.fecha!=null &&
              this.fecha.equals(other.getFecha()))) &&
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
            ((this.tipo==null && other.getTipo()==null) || 
             (this.tipo!=null &&
              this.tipo.equals(other.getTipo()))) &&
            ((this.tipoCuaderno==null && other.getTipoCuaderno()==null) || 
             (this.tipoCuaderno!=null &&
              this.tipoCuaderno.equals(other.getTipoCuaderno()))) &&
            ((this.tipoSeguridad==null && other.getTipoSeguridad()==null) || 
             (this.tipoSeguridad!=null &&
              this.tipoSeguridad.equals(other.getTipoSeguridad()))) &&
            ((this.tramite==null && other.getTramite()==null) || 
             (this.tramite!=null &&
              this.tramite.equals(other.getTramite()))) &&
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
        int _hashCode = 1;
        if (getAnexosFisicos() != null) {
            _hashCode += getAnexosFisicos().hashCode();
        }
        if (getAplicaA() != null) {
            _hashCode += getAplicaA().hashCode();
        }
        if (getCartaModelo() != null) {
            _hashCode += getCartaModelo().hashCode();
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
        if (getFecha() != null) {
            _hashCode += getFecha().hashCode();
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
        if (getTipo() != null) {
            _hashCode += getTipo().hashCode();
        }
        if (getTipoCuaderno() != null) {
            _hashCode += getTipoCuaderno().hashCode();
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Borrador.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "Borrador"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anexosFisicos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "AnexosFisicos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aplicaA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "AplicaA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "Particular"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cartaModelo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "CartaModelo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "CartaModelo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("corresponsal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "Corresponsal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "Corresponsal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dependencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "Dependencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "Dependencia"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dependenciaAsignada");
        elemField.setXmlName(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "DependenciaAsignada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "Dependencia"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentoPrincipal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "DocumentoPrincipal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentosAnexos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "DocumentosAnexos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entregaFisica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "EntregaFisica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecha");
        elemField.setXmlName(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "Fecha"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("funcionario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "Funcionario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "Funcionario"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("funcionarioAsignado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "FuncionarioAsignado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "Funcionario"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("medioDeEnvio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "MedioDeEnvio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "MedioDeEnvio"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("multa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "Multa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numero");
        elemField.setXmlName(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "Numero"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("radicacionAnteriorNumero");
        elemField.setXmlName(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "RadicacionAnteriorNumero"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("referenciaExterna");
        elemField.setXmlName(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "ReferenciaExterna"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("terminoDias");
        elemField.setXmlName(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "TerminoDias"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "Tipo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoCuaderno");
        elemField.setXmlName(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "TipoCuaderno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "TipoCuaderno"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoSeguridad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "TipoSeguridad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "DocumentoTipoSeguridad"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tramite");
        elemField.setXmlName(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "Tramite"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "Tramite"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("usuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "Usuario"));
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
