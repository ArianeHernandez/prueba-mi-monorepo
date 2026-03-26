/**
 * TipoElementoEntradaconsulta_sarlaft.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.itosmosys.www.datapi.servicios.consulta_sarlaft;

public class TipoElementoEntradaconsulta_sarlaft  implements java.io.Serializable {
    private java.lang.String ENT_TIPO_IDENTIFICACION;

    private java.lang.String ENT_NUMERO_IDENTIFICACION;

    private java.lang.String ENT_NOMBRE_COMPLETO;

    public TipoElementoEntradaconsulta_sarlaft() {
    }

    public TipoElementoEntradaconsulta_sarlaft(
           java.lang.String ENT_TIPO_IDENTIFICACION,
           java.lang.String ENT_NUMERO_IDENTIFICACION,
           java.lang.String ENT_NOMBRE_COMPLETO) {
           this.ENT_TIPO_IDENTIFICACION = ENT_TIPO_IDENTIFICACION;
           this.ENT_NUMERO_IDENTIFICACION = ENT_NUMERO_IDENTIFICACION;
           this.ENT_NOMBRE_COMPLETO = ENT_NOMBRE_COMPLETO;
    }


    /**
     * Gets the ENT_TIPO_IDENTIFICACION value for this TipoElementoEntradaconsulta_sarlaft.
     * 
     * @return ENT_TIPO_IDENTIFICACION
     */
    public java.lang.String getENT_TIPO_IDENTIFICACION() {
        return ENT_TIPO_IDENTIFICACION;
    }


    /**
     * Sets the ENT_TIPO_IDENTIFICACION value for this TipoElementoEntradaconsulta_sarlaft.
     * 
     * @param ENT_TIPO_IDENTIFICACION
     */
    public void setENT_TIPO_IDENTIFICACION(java.lang.String ENT_TIPO_IDENTIFICACION) {
        this.ENT_TIPO_IDENTIFICACION = ENT_TIPO_IDENTIFICACION;
    }


    /**
     * Gets the ENT_NUMERO_IDENTIFICACION value for this TipoElementoEntradaconsulta_sarlaft.
     * 
     * @return ENT_NUMERO_IDENTIFICACION
     */
    public java.lang.String getENT_NUMERO_IDENTIFICACION() {
        return ENT_NUMERO_IDENTIFICACION;
    }


    /**
     * Sets the ENT_NUMERO_IDENTIFICACION value for this TipoElementoEntradaconsulta_sarlaft.
     * 
     * @param ENT_NUMERO_IDENTIFICACION
     */
    public void setENT_NUMERO_IDENTIFICACION(java.lang.String ENT_NUMERO_IDENTIFICACION) {
        this.ENT_NUMERO_IDENTIFICACION = ENT_NUMERO_IDENTIFICACION;
    }


    /**
     * Gets the ENT_NOMBRE_COMPLETO value for this TipoElementoEntradaconsulta_sarlaft.
     * 
     * @return ENT_NOMBRE_COMPLETO
     */
    public java.lang.String getENT_NOMBRE_COMPLETO() {
        return ENT_NOMBRE_COMPLETO;
    }


    /**
     * Sets the ENT_NOMBRE_COMPLETO value for this TipoElementoEntradaconsulta_sarlaft.
     * 
     * @param ENT_NOMBRE_COMPLETO
     */
    public void setENT_NOMBRE_COMPLETO(java.lang.String ENT_NOMBRE_COMPLETO) {
        this.ENT_NOMBRE_COMPLETO = ENT_NOMBRE_COMPLETO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoElementoEntradaconsulta_sarlaft)) return false;
        TipoElementoEntradaconsulta_sarlaft other = (TipoElementoEntradaconsulta_sarlaft) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ENT_TIPO_IDENTIFICACION==null && other.getENT_TIPO_IDENTIFICACION()==null) || 
             (this.ENT_TIPO_IDENTIFICACION!=null &&
              this.ENT_TIPO_IDENTIFICACION.equals(other.getENT_TIPO_IDENTIFICACION()))) &&
            ((this.ENT_NUMERO_IDENTIFICACION==null && other.getENT_NUMERO_IDENTIFICACION()==null) || 
             (this.ENT_NUMERO_IDENTIFICACION!=null &&
              this.ENT_NUMERO_IDENTIFICACION.equals(other.getENT_NUMERO_IDENTIFICACION()))) &&
            ((this.ENT_NOMBRE_COMPLETO==null && other.getENT_NOMBRE_COMPLETO()==null) || 
             (this.ENT_NOMBRE_COMPLETO!=null &&
              this.ENT_NOMBRE_COMPLETO.equals(other.getENT_NOMBRE_COMPLETO())));
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
        if (getENT_TIPO_IDENTIFICACION() != null) {
            _hashCode += getENT_TIPO_IDENTIFICACION().hashCode();
        }
        if (getENT_NUMERO_IDENTIFICACION() != null) {
            _hashCode += getENT_NUMERO_IDENTIFICACION().hashCode();
        }
        if (getENT_NOMBRE_COMPLETO() != null) {
            _hashCode += getENT_NOMBRE_COMPLETO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoElementoEntradaconsulta_sarlaft.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/consulta_sarlaft", "tipoElementoEntradaconsulta_sarlaft"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ENT_TIPO_IDENTIFICACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/consulta_sarlaft", "ENT_TIPO_IDENTIFICACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ENT_NUMERO_IDENTIFICACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/consulta_sarlaft", "ENT_NUMERO_IDENTIFICACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ENT_NOMBRE_COMPLETO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/consulta_sarlaft", "ENT_NOMBRE_COMPLETO"));
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
