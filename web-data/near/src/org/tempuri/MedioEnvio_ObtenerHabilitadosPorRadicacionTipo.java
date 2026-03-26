/**
 * MedioEnvio_ObtenerHabilitadosPorRadicacionTipo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class MedioEnvio_ObtenerHabilitadosPorRadicacionTipo  implements java.io.Serializable {
    private java.lang.String radicacionTipo;

    public MedioEnvio_ObtenerHabilitadosPorRadicacionTipo() {
    }

    public MedioEnvio_ObtenerHabilitadosPorRadicacionTipo(
           java.lang.String radicacionTipo) {
           this.radicacionTipo = radicacionTipo;
    }


    /**
     * Gets the radicacionTipo value for this MedioEnvio_ObtenerHabilitadosPorRadicacionTipo.
     * 
     * @return radicacionTipo
     */
    public java.lang.String getRadicacionTipo() {
        return radicacionTipo;
    }


    /**
     * Sets the radicacionTipo value for this MedioEnvio_ObtenerHabilitadosPorRadicacionTipo.
     * 
     * @param radicacionTipo
     */
    public void setRadicacionTipo(java.lang.String radicacionTipo) {
        this.radicacionTipo = radicacionTipo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MedioEnvio_ObtenerHabilitadosPorRadicacionTipo)) return false;
        MedioEnvio_ObtenerHabilitadosPorRadicacionTipo other = (MedioEnvio_ObtenerHabilitadosPorRadicacionTipo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.radicacionTipo==null && other.getRadicacionTipo()==null) || 
             (this.radicacionTipo!=null &&
              this.radicacionTipo.equals(other.getRadicacionTipo())));
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
        if (getRadicacionTipo() != null) {
            _hashCode += getRadicacionTipo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MedioEnvio_ObtenerHabilitadosPorRadicacionTipo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">MedioEnvio_ObtenerHabilitadosPorRadicacionTipo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("radicacionTipo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "radicacionTipo"));
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
