/**
 * TipoElementoSalidamovimiento_recaudos_pagos.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.datasuite.webdata.correval.webservice.movimiento_recaudos_pagos;

public class TipoElementoSalidamovimiento_recaudos_pagos  implements java.io.Serializable {
    private java.lang.String p_CAUSAL_DE_RECHAZO;

    private java.lang.String p_CODIGO_REFERENCIA;

    private int p_ESTADO;

    private java.util.Date p_FECHA_APLICACION;

    public TipoElementoSalidamovimiento_recaudos_pagos() {
    }

    public TipoElementoSalidamovimiento_recaudos_pagos(
           java.lang.String p_CAUSAL_DE_RECHAZO,
           java.lang.String p_CODIGO_REFERENCIA,
           int p_ESTADO,
           java.util.Date p_FECHA_APLICACION) {
           this.p_CAUSAL_DE_RECHAZO = p_CAUSAL_DE_RECHAZO;
           this.p_CODIGO_REFERENCIA = p_CODIGO_REFERENCIA;
           this.p_ESTADO = p_ESTADO;
           this.p_FECHA_APLICACION = p_FECHA_APLICACION;
    }


    /**
     * Gets the p_CAUSAL_DE_RECHAZO value for this TipoElementoSalidamovimiento_recaudos_pagos.
     * 
     * @return p_CAUSAL_DE_RECHAZO
     */
    public java.lang.String getP_CAUSAL_DE_RECHAZO() {
        return p_CAUSAL_DE_RECHAZO;
    }


    /**
     * Sets the p_CAUSAL_DE_RECHAZO value for this TipoElementoSalidamovimiento_recaudos_pagos.
     * 
     * @param p_CAUSAL_DE_RECHAZO
     */
    public void setP_CAUSAL_DE_RECHAZO(java.lang.String p_CAUSAL_DE_RECHAZO) {
        this.p_CAUSAL_DE_RECHAZO = p_CAUSAL_DE_RECHAZO;
    }


    /**
     * Gets the p_CODIGO_REFERENCIA value for this TipoElementoSalidamovimiento_recaudos_pagos.
     * 
     * @return p_CODIGO_REFERENCIA
     */
    public java.lang.String getP_CODIGO_REFERENCIA() {
        return p_CODIGO_REFERENCIA;
    }


    /**
     * Sets the p_CODIGO_REFERENCIA value for this TipoElementoSalidamovimiento_recaudos_pagos.
     * 
     * @param p_CODIGO_REFERENCIA
     */
    public void setP_CODIGO_REFERENCIA(java.lang.String p_CODIGO_REFERENCIA) {
        this.p_CODIGO_REFERENCIA = p_CODIGO_REFERENCIA;
    }


    /**
     * Gets the p_ESTADO value for this TipoElementoSalidamovimiento_recaudos_pagos.
     * 
     * @return p_ESTADO
     */
    public int getP_ESTADO() {
        return p_ESTADO;
    }


    /**
     * Sets the p_ESTADO value for this TipoElementoSalidamovimiento_recaudos_pagos.
     * 
     * @param p_ESTADO
     */
    public void setP_ESTADO(int p_ESTADO) {
        this.p_ESTADO = p_ESTADO;
    }


    /**
     * Gets the p_FECHA_APLICACION value for this TipoElementoSalidamovimiento_recaudos_pagos.
     * 
     * @return p_FECHA_APLICACION
     */
    public java.util.Date getP_FECHA_APLICACION() {
        return p_FECHA_APLICACION;
    }


    /**
     * Sets the p_FECHA_APLICACION value for this TipoElementoSalidamovimiento_recaudos_pagos.
     * 
     * @param p_FECHA_APLICACION
     */
    public void setP_FECHA_APLICACION(java.util.Date p_FECHA_APLICACION) {
        this.p_FECHA_APLICACION = p_FECHA_APLICACION;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoElementoSalidamovimiento_recaudos_pagos)) return false;
        TipoElementoSalidamovimiento_recaudos_pagos other = (TipoElementoSalidamovimiento_recaudos_pagos) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.p_CAUSAL_DE_RECHAZO==null && other.getP_CAUSAL_DE_RECHAZO()==null) || 
             (this.p_CAUSAL_DE_RECHAZO!=null &&
              this.p_CAUSAL_DE_RECHAZO.equals(other.getP_CAUSAL_DE_RECHAZO()))) &&
            ((this.p_CODIGO_REFERENCIA==null && other.getP_CODIGO_REFERENCIA()==null) || 
             (this.p_CODIGO_REFERENCIA!=null &&
              this.p_CODIGO_REFERENCIA.equals(other.getP_CODIGO_REFERENCIA()))) &&
            this.p_ESTADO == other.getP_ESTADO() &&
            ((this.p_FECHA_APLICACION==null && other.getP_FECHA_APLICACION()==null) || 
             (this.p_FECHA_APLICACION!=null &&
              this.p_FECHA_APLICACION.equals(other.getP_FECHA_APLICACION())));
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
        if (getP_CAUSAL_DE_RECHAZO() != null) {
            _hashCode += getP_CAUSAL_DE_RECHAZO().hashCode();
        }
        if (getP_CODIGO_REFERENCIA() != null) {
            _hashCode += getP_CODIGO_REFERENCIA().hashCode();
        }
        _hashCode += getP_ESTADO();
        if (getP_FECHA_APLICACION() != null) {
            _hashCode += getP_FECHA_APLICACION().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoElementoSalidamovimiento_recaudos_pagos.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/movimiento_recaudos_pagos", "tipoElementoSalidamovimiento_recaudos_pagos"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_CAUSAL_DE_RECHAZO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/movimiento_recaudos_pagos", "P_CAUSAL_DE_RECHAZO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_CODIGO_REFERENCIA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/movimiento_recaudos_pagos", "P_CODIGO_REFERENCIA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_ESTADO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/movimiento_recaudos_pagos", "P_ESTADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_FECHA_APLICACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/movimiento_recaudos_pagos", "P_FECHA_APLICACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
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
