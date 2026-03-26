/**
 * TipoElementoEntradaconsultar_contratos_vigentes.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.itosmosys.www.datapi.servicios.consultar_contratos_vigentes;

public class TipoElementoEntradaconsultar_contratos_vigentes  implements java.io.Serializable {
    private java.lang.Integer p_ID_SOLICITANTE;

    public TipoElementoEntradaconsultar_contratos_vigentes() {
    }

    public TipoElementoEntradaconsultar_contratos_vigentes(
           java.lang.Integer p_ID_SOLICITANTE) {
           this.p_ID_SOLICITANTE = p_ID_SOLICITANTE;
    }


    /**
     * Gets the p_ID_SOLICITANTE value for this TipoElementoEntradaconsultar_contratos_vigentes.
     * 
     * @return p_ID_SOLICITANTE
     */
    public java.lang.Integer getP_ID_SOLICITANTE() {
        return p_ID_SOLICITANTE;
    }


    /**
     * Sets the p_ID_SOLICITANTE value for this TipoElementoEntradaconsultar_contratos_vigentes.
     * 
     * @param p_ID_SOLICITANTE
     */
    public void setP_ID_SOLICITANTE(java.lang.Integer p_ID_SOLICITANTE) {
        this.p_ID_SOLICITANTE = p_ID_SOLICITANTE;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoElementoEntradaconsultar_contratos_vigentes)) return false;
        TipoElementoEntradaconsultar_contratos_vigentes other = (TipoElementoEntradaconsultar_contratos_vigentes) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.p_ID_SOLICITANTE==null && other.getP_ID_SOLICITANTE()==null) || 
             (this.p_ID_SOLICITANTE!=null &&
              this.p_ID_SOLICITANTE.equals(other.getP_ID_SOLICITANTE())));
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
        if (getP_ID_SOLICITANTE() != null) {
            _hashCode += getP_ID_SOLICITANTE().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoElementoEntradaconsultar_contratos_vigentes.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/consultar_contratos_vigentes", "tipoElementoEntradaconsultar_contratos_vigentes"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_ID_SOLICITANTE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/consultar_contratos_vigentes", "P_ID_SOLICITANTE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
