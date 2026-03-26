/**
 * TipoElementoSalidarequiereCTX.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.osmoautenticador.servicioweb.requiereCTX;

public class TipoElementoSalidarequiereCTX  implements java.io.Serializable {
    private int id_servicio;

    private java.lang.String requiere;

    public TipoElementoSalidarequiereCTX() {
    }

    public TipoElementoSalidarequiereCTX(
           int id_servicio,
           java.lang.String requiere) {
           this.id_servicio = id_servicio;
           this.requiere = requiere;
    }


    /**
     * Gets the id_servicio value for this TipoElementoSalidarequiereCTX.
     * 
     * @return id_servicio
     */
    public int getId_servicio() {
        return id_servicio;
    }


    /**
     * Sets the id_servicio value for this TipoElementoSalidarequiereCTX.
     * 
     * @param id_servicio
     */
    public void setId_servicio(int id_servicio) {
        this.id_servicio = id_servicio;
    }


    /**
     * Gets the requiere value for this TipoElementoSalidarequiereCTX.
     * 
     * @return requiere
     */
    public java.lang.String getRequiere() {
        return requiere;
    }


    /**
     * Sets the requiere value for this TipoElementoSalidarequiereCTX.
     * 
     * @param requiere
     */
    public void setRequiere(java.lang.String requiere) {
        this.requiere = requiere;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoElementoSalidarequiereCTX)) return false;
        TipoElementoSalidarequiereCTX other = (TipoElementoSalidarequiereCTX) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.id_servicio == other.getId_servicio() &&
            ((this.requiere==null && other.getRequiere()==null) || 
             (this.requiere!=null &&
              this.requiere.equals(other.getRequiere())));
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
        _hashCode += getId_servicio();
        if (getRequiere() != null) {
            _hashCode += getRequiere().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoElementoSalidarequiereCTX.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/requiereCTX", "tipoElementoSalidarequiereCTX"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id_servicio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/requiereCTX", "id_servicio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requiere");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/requiereCTX", "requiere"));
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
