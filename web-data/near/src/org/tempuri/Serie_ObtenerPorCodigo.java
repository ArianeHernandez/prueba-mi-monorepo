/**
 * Serie_ObtenerPorCodigo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class Serie_ObtenerPorCodigo  implements java.io.Serializable {
    private java.lang.Integer codigoSerie;

    public Serie_ObtenerPorCodigo() {
    }

    public Serie_ObtenerPorCodigo(
           java.lang.Integer codigoSerie) {
           this.codigoSerie = codigoSerie;
    }


    /**
     * Gets the codigoSerie value for this Serie_ObtenerPorCodigo.
     * 
     * @return codigoSerie
     */
    public java.lang.Integer getCodigoSerie() {
        return codigoSerie;
    }


    /**
     * Sets the codigoSerie value for this Serie_ObtenerPorCodigo.
     * 
     * @param codigoSerie
     */
    public void setCodigoSerie(java.lang.Integer codigoSerie) {
        this.codigoSerie = codigoSerie;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Serie_ObtenerPorCodigo)) return false;
        Serie_ObtenerPorCodigo other = (Serie_ObtenerPorCodigo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codigoSerie==null && other.getCodigoSerie()==null) || 
             (this.codigoSerie!=null &&
              this.codigoSerie.equals(other.getCodigoSerie())));
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
        if (getCodigoSerie() != null) {
            _hashCode += getCodigoSerie().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Serie_ObtenerPorCodigo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">Serie_ObtenerPorCodigo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoSerie");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "codigoSerie"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
