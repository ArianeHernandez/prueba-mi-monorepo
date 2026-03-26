/**
 * SerieActivas_ObtenerPorDependencia.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class SerieActivas_ObtenerPorDependencia  implements java.io.Serializable {
    private java.lang.Integer codigoDependencia;

    public SerieActivas_ObtenerPorDependencia() {
    }

    public SerieActivas_ObtenerPorDependencia(
           java.lang.Integer codigoDependencia) {
           this.codigoDependencia = codigoDependencia;
    }


    /**
     * Gets the codigoDependencia value for this SerieActivas_ObtenerPorDependencia.
     * 
     * @return codigoDependencia
     */
    public java.lang.Integer getCodigoDependencia() {
        return codigoDependencia;
    }


    /**
     * Sets the codigoDependencia value for this SerieActivas_ObtenerPorDependencia.
     * 
     * @param codigoDependencia
     */
    public void setCodigoDependencia(java.lang.Integer codigoDependencia) {
        this.codigoDependencia = codigoDependencia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SerieActivas_ObtenerPorDependencia)) return false;
        SerieActivas_ObtenerPorDependencia other = (SerieActivas_ObtenerPorDependencia) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codigoDependencia==null && other.getCodigoDependencia()==null) || 
             (this.codigoDependencia!=null &&
              this.codigoDependencia.equals(other.getCodigoDependencia())));
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
        if (getCodigoDependencia() != null) {
            _hashCode += getCodigoDependencia().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SerieActivas_ObtenerPorDependencia.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">SerieActivas_ObtenerPorDependencia"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoDependencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "codigoDependencia"));
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
