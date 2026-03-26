/**
 * Operacion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.g3a.cliente.entidades;

public class Operacion  implements java.io.Serializable {
    private long id;

    private int tipoOperacion;

    private java.lang.String urlRetornoCancelacion;

    private java.lang.String urlRetornoOperacion;

    public Operacion() {
    }

    public Operacion(
           long id,
           int tipoOperacion,
           java.lang.String urlRetornoCancelacion,
           java.lang.String urlRetornoOperacion) {
           this.id = id;
           this.tipoOperacion = tipoOperacion;
           this.urlRetornoCancelacion = urlRetornoCancelacion;
           this.urlRetornoOperacion = urlRetornoOperacion;
    }


    /**
     * Gets the id value for this Operacion.
     * 
     * @return id
     */
    public long getId() {
        return id;
    }


    /**
     * Sets the id value for this Operacion.
     * 
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }


    /**
     * Gets the tipoOperacion value for this Operacion.
     * 
     * @return tipoOperacion
     */
    public int getTipoOperacion() {
        return tipoOperacion;
    }


    /**
     * Sets the tipoOperacion value for this Operacion.
     * 
     * @param tipoOperacion
     */
    public void setTipoOperacion(int tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }


    /**
     * Gets the urlRetornoCancelacion value for this Operacion.
     * 
     * @return urlRetornoCancelacion
     */
    public java.lang.String getUrlRetornoCancelacion() {
        return urlRetornoCancelacion;
    }


    /**
     * Sets the urlRetornoCancelacion value for this Operacion.
     * 
     * @param urlRetornoCancelacion
     */
    public void setUrlRetornoCancelacion(java.lang.String urlRetornoCancelacion) {
        this.urlRetornoCancelacion = urlRetornoCancelacion;
    }


    /**
     * Gets the urlRetornoOperacion value for this Operacion.
     * 
     * @return urlRetornoOperacion
     */
    public java.lang.String getUrlRetornoOperacion() {
        return urlRetornoOperacion;
    }


    /**
     * Sets the urlRetornoOperacion value for this Operacion.
     * 
     * @param urlRetornoOperacion
     */
    public void setUrlRetornoOperacion(java.lang.String urlRetornoOperacion) {
        this.urlRetornoOperacion = urlRetornoOperacion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Operacion)) return false;
        Operacion other = (Operacion) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.id == other.getId() &&
            this.tipoOperacion == other.getTipoOperacion() &&
            ((this.urlRetornoCancelacion==null && other.getUrlRetornoCancelacion()==null) || 
             (this.urlRetornoCancelacion!=null &&
              this.urlRetornoCancelacion.equals(other.getUrlRetornoCancelacion()))) &&
            ((this.urlRetornoOperacion==null && other.getUrlRetornoOperacion()==null) || 
             (this.urlRetornoOperacion!=null &&
              this.urlRetornoOperacion.equals(other.getUrlRetornoOperacion())));
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
        _hashCode += new Long(getId()).hashCode();
        _hashCode += getTipoOperacion();
        if (getUrlRetornoCancelacion() != null) {
            _hashCode += getUrlRetornoCancelacion().hashCode();
        }
        if (getUrlRetornoOperacion() != null) {
            _hashCode += getUrlRetornoOperacion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Operacion.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "Operacion"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoOperacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "tipoOperacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("urlRetornoCancelacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "urlRetornoCancelacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("urlRetornoOperacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "urlRetornoOperacion"));
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
