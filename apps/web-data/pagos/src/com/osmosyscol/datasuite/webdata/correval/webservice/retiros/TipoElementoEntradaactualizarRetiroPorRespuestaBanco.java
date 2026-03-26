/**
 * TipoElementoEntradaactualizarRetiroPorRespuestaBanco.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.datasuite.webdata.correval.webservice.retiros;

public class TipoElementoEntradaactualizarRetiroPorRespuestaBanco  implements java.io.Serializable {
    private java.lang.String codigo_referencia;

    private java.lang.Boolean rechazado;

    private java.lang.String estado_respuesta;

    private java.lang.String tipo_actualizacion;

    public TipoElementoEntradaactualizarRetiroPorRespuestaBanco() {
    }

    public TipoElementoEntradaactualizarRetiroPorRespuestaBanco(
           java.lang.String codigo_referencia,
           java.lang.Boolean rechazado,
           java.lang.String estado_respuesta,
           java.lang.String tipo_actualizacion) {
           this.codigo_referencia = codigo_referencia;
           this.rechazado = rechazado;
           this.estado_respuesta = estado_respuesta;
           this.tipo_actualizacion = tipo_actualizacion;
    }


    /**
     * Gets the codigo_referencia value for this TipoElementoEntradaactualizarRetiroPorRespuestaBanco.
     * 
     * @return codigo_referencia
     */
    public java.lang.String getCodigo_referencia() {
        return codigo_referencia;
    }


    /**
     * Sets the codigo_referencia value for this TipoElementoEntradaactualizarRetiroPorRespuestaBanco.
     * 
     * @param codigo_referencia
     */
    public void setCodigo_referencia(java.lang.String codigo_referencia) {
        this.codigo_referencia = codigo_referencia;
    }


    /**
     * Gets the rechazado value for this TipoElementoEntradaactualizarRetiroPorRespuestaBanco.
     * 
     * @return rechazado
     */
    public java.lang.Boolean getRechazado() {
        return rechazado;
    }


    /**
     * Sets the rechazado value for this TipoElementoEntradaactualizarRetiroPorRespuestaBanco.
     * 
     * @param rechazado
     */
    public void setRechazado(java.lang.Boolean rechazado) {
        this.rechazado = rechazado;
    }


    /**
     * Gets the estado_respuesta value for this TipoElementoEntradaactualizarRetiroPorRespuestaBanco.
     * 
     * @return estado_respuesta
     */
    public java.lang.String getEstado_respuesta() {
        return estado_respuesta;
    }


    /**
     * Sets the estado_respuesta value for this TipoElementoEntradaactualizarRetiroPorRespuestaBanco.
     * 
     * @param estado_respuesta
     */
    public void setEstado_respuesta(java.lang.String estado_respuesta) {
        this.estado_respuesta = estado_respuesta;
    }


    /**
     * Gets the tipo_actualizacion value for this TipoElementoEntradaactualizarRetiroPorRespuestaBanco.
     * 
     * @return tipo_actualizacion
     */
    public java.lang.String getTipo_actualizacion() {
        return tipo_actualizacion;
    }


    /**
     * Sets the tipo_actualizacion value for this TipoElementoEntradaactualizarRetiroPorRespuestaBanco.
     * 
     * @param tipo_actualizacion
     */
    public void setTipo_actualizacion(java.lang.String tipo_actualizacion) {
        this.tipo_actualizacion = tipo_actualizacion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoElementoEntradaactualizarRetiroPorRespuestaBanco)) return false;
        TipoElementoEntradaactualizarRetiroPorRespuestaBanco other = (TipoElementoEntradaactualizarRetiroPorRespuestaBanco) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codigo_referencia==null && other.getCodigo_referencia()==null) || 
             (this.codigo_referencia!=null &&
              this.codigo_referencia.equals(other.getCodigo_referencia()))) &&
            ((this.rechazado==null && other.getRechazado()==null) || 
             (this.rechazado!=null &&
              this.rechazado.equals(other.getRechazado()))) &&
            ((this.estado_respuesta==null && other.getEstado_respuesta()==null) || 
             (this.estado_respuesta!=null &&
              this.estado_respuesta.equals(other.getEstado_respuesta()))) &&
            ((this.tipo_actualizacion==null && other.getTipo_actualizacion()==null) || 
             (this.tipo_actualizacion!=null &&
              this.tipo_actualizacion.equals(other.getTipo_actualizacion())));
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
        if (getCodigo_referencia() != null) {
            _hashCode += getCodigo_referencia().hashCode();
        }
        if (getRechazado() != null) {
            _hashCode += getRechazado().hashCode();
        }
        if (getEstado_respuesta() != null) {
            _hashCode += getEstado_respuesta().hashCode();
        }
        if (getTipo_actualizacion() != null) {
            _hashCode += getTipo_actualizacion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoElementoEntradaactualizarRetiroPorRespuestaBanco.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/ActualizarRetiroPorRespuestaBanco", "tipoElementoEntradaactualizarRetiroPorRespuestaBanco"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigo_referencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/ActualizarRetiroPorRespuestaBanco", "codigo_referencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rechazado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/ActualizarRetiroPorRespuestaBanco", "rechazado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estado_respuesta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/ActualizarRetiroPorRespuestaBanco", "estado_respuesta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipo_actualizacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/ActualizarRetiroPorRespuestaBanco", "tipo_actualizacion"));
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
