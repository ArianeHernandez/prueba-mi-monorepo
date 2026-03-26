/**
 * ObtenerTipoCanalPorTipoRadicacion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class ObtenerTipoCanalPorTipoRadicacion  implements java.io.Serializable {
    private java.lang.String tipoRadicacion;

    public ObtenerTipoCanalPorTipoRadicacion() {
    }

    public ObtenerTipoCanalPorTipoRadicacion(
           java.lang.String tipoRadicacion) {
           this.tipoRadicacion = tipoRadicacion;
    }


    /**
     * Gets the tipoRadicacion value for this ObtenerTipoCanalPorTipoRadicacion.
     * 
     * @return tipoRadicacion
     */
    public java.lang.String getTipoRadicacion() {
        return tipoRadicacion;
    }


    /**
     * Sets the tipoRadicacion value for this ObtenerTipoCanalPorTipoRadicacion.
     * 
     * @param tipoRadicacion
     */
    public void setTipoRadicacion(java.lang.String tipoRadicacion) {
        this.tipoRadicacion = tipoRadicacion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ObtenerTipoCanalPorTipoRadicacion)) return false;
        ObtenerTipoCanalPorTipoRadicacion other = (ObtenerTipoCanalPorTipoRadicacion) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tipoRadicacion==null && other.getTipoRadicacion()==null) || 
             (this.tipoRadicacion!=null &&
              this.tipoRadicacion.equals(other.getTipoRadicacion())));
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
        if (getTipoRadicacion() != null) {
            _hashCode += getTipoRadicacion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ObtenerTipoCanalPorTipoRadicacion.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">ObtenerTipoCanalPorTipoRadicacion"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoRadicacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "tipoRadicacion"));
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
