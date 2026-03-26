/**
 * Subserie_ObtenerPorCodigo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class Subserie_ObtenerPorCodigo  implements java.io.Serializable {
    private java.lang.Integer codigoSubSerie;

    public Subserie_ObtenerPorCodigo() {
    }

    public Subserie_ObtenerPorCodigo(
           java.lang.Integer codigoSubSerie) {
           this.codigoSubSerie = codigoSubSerie;
    }


    /**
     * Gets the codigoSubSerie value for this Subserie_ObtenerPorCodigo.
     * 
     * @return codigoSubSerie
     */
    public java.lang.Integer getCodigoSubSerie() {
        return codigoSubSerie;
    }


    /**
     * Sets the codigoSubSerie value for this Subserie_ObtenerPorCodigo.
     * 
     * @param codigoSubSerie
     */
    public void setCodigoSubSerie(java.lang.Integer codigoSubSerie) {
        this.codigoSubSerie = codigoSubSerie;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Subserie_ObtenerPorCodigo)) return false;
        Subserie_ObtenerPorCodigo other = (Subserie_ObtenerPorCodigo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codigoSubSerie==null && other.getCodigoSubSerie()==null) || 
             (this.codigoSubSerie!=null &&
              this.codigoSubSerie.equals(other.getCodigoSubSerie())));
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
        if (getCodigoSubSerie() != null) {
            _hashCode += getCodigoSubSerie().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Subserie_ObtenerPorCodigo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">Subserie_ObtenerPorCodigo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoSubSerie");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "codigoSubSerie"));
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
