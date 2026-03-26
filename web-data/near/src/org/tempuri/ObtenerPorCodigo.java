/**
 * ObtenerPorCodigo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class ObtenerPorCodigo  implements java.io.Serializable {
    private java.lang.String codigoCarta;

    public ObtenerPorCodigo() {
    }

    public ObtenerPorCodigo(
           java.lang.String codigoCarta) {
           this.codigoCarta = codigoCarta;
    }


    /**
     * Gets the codigoCarta value for this ObtenerPorCodigo.
     * 
     * @return codigoCarta
     */
    public java.lang.String getCodigoCarta() {
        return codigoCarta;
    }


    /**
     * Sets the codigoCarta value for this ObtenerPorCodigo.
     * 
     * @param codigoCarta
     */
    public void setCodigoCarta(java.lang.String codigoCarta) {
        this.codigoCarta = codigoCarta;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ObtenerPorCodigo)) return false;
        ObtenerPorCodigo other = (ObtenerPorCodigo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codigoCarta==null && other.getCodigoCarta()==null) || 
             (this.codigoCarta!=null &&
              this.codigoCarta.equals(other.getCodigoCarta())));
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
        if (getCodigoCarta() != null) {
            _hashCode += getCodigoCarta().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ObtenerPorCodigo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">ObtenerPorCodigo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoCarta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "codigoCarta"));
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
