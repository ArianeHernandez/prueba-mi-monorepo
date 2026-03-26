/**
 * R_ENTRADAS_ALMACENArrayElement.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.itosmosys.www.datapi.servicios.consultar_entrada_almacen;

public class R_ENTRADAS_ALMACENArrayElement  implements java.io.Serializable {
    private java.lang.Integer NUMERO_ENTRADA;

    private java.lang.Integer EMPRESA_ENTRADA;

    private java.lang.String TIPO_ENTRADA;

    private java.lang.Integer PERIODO_ENTRADA;

    public R_ENTRADAS_ALMACENArrayElement() {
    }

    public R_ENTRADAS_ALMACENArrayElement(
           java.lang.Integer NUMERO_ENTRADA,
           java.lang.Integer EMPRESA_ENTRADA,
           java.lang.String TIPO_ENTRADA,
           java.lang.Integer PERIODO_ENTRADA) {
           this.NUMERO_ENTRADA = NUMERO_ENTRADA;
           this.EMPRESA_ENTRADA = EMPRESA_ENTRADA;
           this.TIPO_ENTRADA = TIPO_ENTRADA;
           this.PERIODO_ENTRADA = PERIODO_ENTRADA;
    }


    /**
     * Gets the NUMERO_ENTRADA value for this R_ENTRADAS_ALMACENArrayElement.
     * 
     * @return NUMERO_ENTRADA
     */
    public java.lang.Integer getNUMERO_ENTRADA() {
        return NUMERO_ENTRADA;
    }


    /**
     * Sets the NUMERO_ENTRADA value for this R_ENTRADAS_ALMACENArrayElement.
     * 
     * @param NUMERO_ENTRADA
     */
    public void setNUMERO_ENTRADA(java.lang.Integer NUMERO_ENTRADA) {
        this.NUMERO_ENTRADA = NUMERO_ENTRADA;
    }


    /**
     * Gets the EMPRESA_ENTRADA value for this R_ENTRADAS_ALMACENArrayElement.
     * 
     * @return EMPRESA_ENTRADA
     */
    public java.lang.Integer getEMPRESA_ENTRADA() {
        return EMPRESA_ENTRADA;
    }


    /**
     * Sets the EMPRESA_ENTRADA value for this R_ENTRADAS_ALMACENArrayElement.
     * 
     * @param EMPRESA_ENTRADA
     */
    public void setEMPRESA_ENTRADA(java.lang.Integer EMPRESA_ENTRADA) {
        this.EMPRESA_ENTRADA = EMPRESA_ENTRADA;
    }


    /**
     * Gets the TIPO_ENTRADA value for this R_ENTRADAS_ALMACENArrayElement.
     * 
     * @return TIPO_ENTRADA
     */
    public java.lang.String getTIPO_ENTRADA() {
        return TIPO_ENTRADA;
    }


    /**
     * Sets the TIPO_ENTRADA value for this R_ENTRADAS_ALMACENArrayElement.
     * 
     * @param TIPO_ENTRADA
     */
    public void setTIPO_ENTRADA(java.lang.String TIPO_ENTRADA) {
        this.TIPO_ENTRADA = TIPO_ENTRADA;
    }


    /**
     * Gets the PERIODO_ENTRADA value for this R_ENTRADAS_ALMACENArrayElement.
     * 
     * @return PERIODO_ENTRADA
     */
    public java.lang.Integer getPERIODO_ENTRADA() {
        return PERIODO_ENTRADA;
    }


    /**
     * Sets the PERIODO_ENTRADA value for this R_ENTRADAS_ALMACENArrayElement.
     * 
     * @param PERIODO_ENTRADA
     */
    public void setPERIODO_ENTRADA(java.lang.Integer PERIODO_ENTRADA) {
        this.PERIODO_ENTRADA = PERIODO_ENTRADA;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof R_ENTRADAS_ALMACENArrayElement)) return false;
        R_ENTRADAS_ALMACENArrayElement other = (R_ENTRADAS_ALMACENArrayElement) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.NUMERO_ENTRADA==null && other.getNUMERO_ENTRADA()==null) || 
             (this.NUMERO_ENTRADA!=null &&
              this.NUMERO_ENTRADA.equals(other.getNUMERO_ENTRADA()))) &&
            ((this.EMPRESA_ENTRADA==null && other.getEMPRESA_ENTRADA()==null) || 
             (this.EMPRESA_ENTRADA!=null &&
              this.EMPRESA_ENTRADA.equals(other.getEMPRESA_ENTRADA()))) &&
            ((this.TIPO_ENTRADA==null && other.getTIPO_ENTRADA()==null) || 
             (this.TIPO_ENTRADA!=null &&
              this.TIPO_ENTRADA.equals(other.getTIPO_ENTRADA()))) &&
            ((this.PERIODO_ENTRADA==null && other.getPERIODO_ENTRADA()==null) || 
             (this.PERIODO_ENTRADA!=null &&
              this.PERIODO_ENTRADA.equals(other.getPERIODO_ENTRADA())));
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
        if (getNUMERO_ENTRADA() != null) {
            _hashCode += getNUMERO_ENTRADA().hashCode();
        }
        if (getEMPRESA_ENTRADA() != null) {
            _hashCode += getEMPRESA_ENTRADA().hashCode();
        }
        if (getTIPO_ENTRADA() != null) {
            _hashCode += getTIPO_ENTRADA().hashCode();
        }
        if (getPERIODO_ENTRADA() != null) {
            _hashCode += getPERIODO_ENTRADA().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(R_ENTRADAS_ALMACENArrayElement.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/consultar_entrada_almacen", "R_ENTRADAS_ALMACENArrayElement"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUMERO_ENTRADA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/consultar_entrada_almacen", "NUMERO_ENTRADA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EMPRESA_ENTRADA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/consultar_entrada_almacen", "EMPRESA_ENTRADA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TIPO_ENTRADA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/consultar_entrada_almacen", "TIPO_ENTRADA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PERIODO_ENTRADA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/consultar_entrada_almacen", "PERIODO_ENTRADA"));
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
