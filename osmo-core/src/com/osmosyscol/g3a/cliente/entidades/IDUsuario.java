/**
 * IDUsuario.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.g3a.cliente.entidades;

public class IDUsuario  implements java.io.Serializable {
    private java.lang.String alcanceUsuario;

    private java.lang.String naturaleza;

    private java.lang.String numeroIdCliente;

    private java.lang.String numeroIdUsuario;

    private java.lang.String tipoIdCliente;

    private java.lang.String tipoIdUsuario;

    public IDUsuario() {
    }

    public IDUsuario(
           java.lang.String alcanceUsuario,
           java.lang.String naturaleza,
           java.lang.String numeroIdCliente,
           java.lang.String numeroIdUsuario,
           java.lang.String tipoIdCliente,
           java.lang.String tipoIdUsuario) {
           this.alcanceUsuario = alcanceUsuario;
           this.naturaleza = naturaleza;
           this.numeroIdCliente = numeroIdCliente;
           this.numeroIdUsuario = numeroIdUsuario;
           this.tipoIdCliente = tipoIdCliente;
           this.tipoIdUsuario = tipoIdUsuario;
    }


    /**
     * Gets the alcanceUsuario value for this IDUsuario.
     * 
     * @return alcanceUsuario
     */
    public java.lang.String getAlcanceUsuario() {
        return alcanceUsuario;
    }


    /**
     * Sets the alcanceUsuario value for this IDUsuario.
     * 
     * @param alcanceUsuario
     */
    public void setAlcanceUsuario(java.lang.String alcanceUsuario) {
        this.alcanceUsuario = alcanceUsuario;
    }


    /**
     * Gets the naturaleza value for this IDUsuario.
     * 
     * @return naturaleza
     */
    public java.lang.String getNaturaleza() {
        return naturaleza;
    }


    /**
     * Sets the naturaleza value for this IDUsuario.
     * 
     * @param naturaleza
     */
    public void setNaturaleza(java.lang.String naturaleza) {
        this.naturaleza = naturaleza;
    }


    /**
     * Gets the numeroIdCliente value for this IDUsuario.
     * 
     * @return numeroIdCliente
     */
    public java.lang.String getNumeroIdCliente() {
        return numeroIdCliente;
    }


    /**
     * Sets the numeroIdCliente value for this IDUsuario.
     * 
     * @param numeroIdCliente
     */
    public void setNumeroIdCliente(java.lang.String numeroIdCliente) {
        this.numeroIdCliente = numeroIdCliente;
    }


    /**
     * Gets the numeroIdUsuario value for this IDUsuario.
     * 
     * @return numeroIdUsuario
     */
    public java.lang.String getNumeroIdUsuario() {
        return numeroIdUsuario;
    }


    /**
     * Sets the numeroIdUsuario value for this IDUsuario.
     * 
     * @param numeroIdUsuario
     */
    public void setNumeroIdUsuario(java.lang.String numeroIdUsuario) {
        this.numeroIdUsuario = numeroIdUsuario;
    }


    /**
     * Gets the tipoIdCliente value for this IDUsuario.
     * 
     * @return tipoIdCliente
     */
    public java.lang.String getTipoIdCliente() {
        return tipoIdCliente;
    }


    /**
     * Sets the tipoIdCliente value for this IDUsuario.
     * 
     * @param tipoIdCliente
     */
    public void setTipoIdCliente(java.lang.String tipoIdCliente) {
        this.tipoIdCliente = tipoIdCliente;
    }


    /**
     * Gets the tipoIdUsuario value for this IDUsuario.
     * 
     * @return tipoIdUsuario
     */
    public java.lang.String getTipoIdUsuario() {
        return tipoIdUsuario;
    }


    /**
     * Sets the tipoIdUsuario value for this IDUsuario.
     * 
     * @param tipoIdUsuario
     */
    public void setTipoIdUsuario(java.lang.String tipoIdUsuario) {
        this.tipoIdUsuario = tipoIdUsuario;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IDUsuario)) return false;
        IDUsuario other = (IDUsuario) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.alcanceUsuario==null && other.getAlcanceUsuario()==null) || 
             (this.alcanceUsuario!=null &&
              this.alcanceUsuario.equals(other.getAlcanceUsuario()))) &&
            ((this.naturaleza==null && other.getNaturaleza()==null) || 
             (this.naturaleza!=null &&
              this.naturaleza.equals(other.getNaturaleza()))) &&
            ((this.numeroIdCliente==null && other.getNumeroIdCliente()==null) || 
             (this.numeroIdCliente!=null &&
              this.numeroIdCliente.equals(other.getNumeroIdCliente()))) &&
            ((this.numeroIdUsuario==null && other.getNumeroIdUsuario()==null) || 
             (this.numeroIdUsuario!=null &&
              this.numeroIdUsuario.equals(other.getNumeroIdUsuario()))) &&
            ((this.tipoIdCliente==null && other.getTipoIdCliente()==null) || 
             (this.tipoIdCliente!=null &&
              this.tipoIdCliente.equals(other.getTipoIdCliente()))) &&
            ((this.tipoIdUsuario==null && other.getTipoIdUsuario()==null) || 
             (this.tipoIdUsuario!=null &&
              this.tipoIdUsuario.equals(other.getTipoIdUsuario())));
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
        if (getAlcanceUsuario() != null) {
            _hashCode += getAlcanceUsuario().hashCode();
        }
        if (getNaturaleza() != null) {
            _hashCode += getNaturaleza().hashCode();
        }
        if (getNumeroIdCliente() != null) {
            _hashCode += getNumeroIdCliente().hashCode();
        }
        if (getNumeroIdUsuario() != null) {
            _hashCode += getNumeroIdUsuario().hashCode();
        }
        if (getTipoIdCliente() != null) {
            _hashCode += getTipoIdCliente().hashCode();
        }
        if (getTipoIdUsuario() != null) {
            _hashCode += getTipoIdUsuario().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IDUsuario.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "IDUsuario"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("alcanceUsuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "alcanceUsuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("naturaleza");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "naturaleza"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroIdCliente");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "numeroIdCliente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroIdUsuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "numeroIdUsuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoIdCliente");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "tipoIdCliente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoIdUsuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "tipoIdUsuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
