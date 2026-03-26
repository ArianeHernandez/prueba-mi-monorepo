/**
 * LISTASArrayElement.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.itosmosys.www.datapi.servicios.consulta_sarlaft;

public class LISTASArrayElement  implements java.io.Serializable {
    private java.lang.String p_NOMBRE;

    private java.lang.String p_PORCENTAJE_COINCIDENCIA;

    public LISTASArrayElement() {
    }

    public LISTASArrayElement(
           java.lang.String p_NOMBRE,
           java.lang.String p_PORCENTAJE_COINCIDENCIA) {
           this.p_NOMBRE = p_NOMBRE;
           this.p_PORCENTAJE_COINCIDENCIA = p_PORCENTAJE_COINCIDENCIA;
    }


    /**
     * Gets the p_NOMBRE value for this LISTASArrayElement.
     * 
     * @return p_NOMBRE
     */
    public java.lang.String getP_NOMBRE() {
        return p_NOMBRE;
    }


    /**
     * Sets the p_NOMBRE value for this LISTASArrayElement.
     * 
     * @param p_NOMBRE
     */
    public void setP_NOMBRE(java.lang.String p_NOMBRE) {
        this.p_NOMBRE = p_NOMBRE;
    }


    /**
     * Gets the p_PORCENTAJE_COINCIDENCIA value for this LISTASArrayElement.
     * 
     * @return p_PORCENTAJE_COINCIDENCIA
     */
    public java.lang.String getP_PORCENTAJE_COINCIDENCIA() {
        return p_PORCENTAJE_COINCIDENCIA;
    }


    /**
     * Sets the p_PORCENTAJE_COINCIDENCIA value for this LISTASArrayElement.
     * 
     * @param p_PORCENTAJE_COINCIDENCIA
     */
    public void setP_PORCENTAJE_COINCIDENCIA(java.lang.String p_PORCENTAJE_COINCIDENCIA) {
        this.p_PORCENTAJE_COINCIDENCIA = p_PORCENTAJE_COINCIDENCIA;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LISTASArrayElement)) return false;
        LISTASArrayElement other = (LISTASArrayElement) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.p_NOMBRE==null && other.getP_NOMBRE()==null) || 
             (this.p_NOMBRE!=null &&
              this.p_NOMBRE.equals(other.getP_NOMBRE()))) &&
            ((this.p_PORCENTAJE_COINCIDENCIA==null && other.getP_PORCENTAJE_COINCIDENCIA()==null) || 
             (this.p_PORCENTAJE_COINCIDENCIA!=null &&
              this.p_PORCENTAJE_COINCIDENCIA.equals(other.getP_PORCENTAJE_COINCIDENCIA())));
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
        if (getP_NOMBRE() != null) {
            _hashCode += getP_NOMBRE().hashCode();
        }
        if (getP_PORCENTAJE_COINCIDENCIA() != null) {
            _hashCode += getP_PORCENTAJE_COINCIDENCIA().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LISTASArrayElement.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/consulta_sarlaft", "LISTASArrayElement"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_NOMBRE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/consulta_sarlaft", "P_NOMBRE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_PORCENTAJE_COINCIDENCIA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/consulta_sarlaft", "P_PORCENTAJE_COINCIDENCIA"));
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
