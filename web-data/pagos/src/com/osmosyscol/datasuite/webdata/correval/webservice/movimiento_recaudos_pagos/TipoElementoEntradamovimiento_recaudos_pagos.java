/**
 * TipoElementoEntradamovimiento_recaudos_pagos.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.datasuite.webdata.correval.webservice.movimiento_recaudos_pagos;

public class TipoElementoEntradamovimiento_recaudos_pagos  implements java.io.Serializable {
    private int p_CODIGO_TRANSACCION;

    private int p_NUMERO_LOTE;

    private int p_VALOR_TOTAL;

    public TipoElementoEntradamovimiento_recaudos_pagos() {
    }

    public TipoElementoEntradamovimiento_recaudos_pagos(
           int p_CODIGO_TRANSACCION,
           int p_NUMERO_LOTE,
           int p_VALOR_TOTAL) {
           this.p_CODIGO_TRANSACCION = p_CODIGO_TRANSACCION;
           this.p_NUMERO_LOTE = p_NUMERO_LOTE;
           this.p_VALOR_TOTAL = p_VALOR_TOTAL;
    }


    /**
     * Gets the p_CODIGO_TRANSACCION value for this TipoElementoEntradamovimiento_recaudos_pagos.
     * 
     * @return p_CODIGO_TRANSACCION
     */
    public int getP_CODIGO_TRANSACCION() {
        return p_CODIGO_TRANSACCION;
    }


    /**
     * Sets the p_CODIGO_TRANSACCION value for this TipoElementoEntradamovimiento_recaudos_pagos.
     * 
     * @param p_CODIGO_TRANSACCION
     */
    public void setP_CODIGO_TRANSACCION(int p_CODIGO_TRANSACCION) {
        this.p_CODIGO_TRANSACCION = p_CODIGO_TRANSACCION;
    }


    /**
     * Gets the p_NUMERO_LOTE value for this TipoElementoEntradamovimiento_recaudos_pagos.
     * 
     * @return p_NUMERO_LOTE
     */
    public int getP_NUMERO_LOTE() {
        return p_NUMERO_LOTE;
    }


    /**
     * Sets the p_NUMERO_LOTE value for this TipoElementoEntradamovimiento_recaudos_pagos.
     * 
     * @param p_NUMERO_LOTE
     */
    public void setP_NUMERO_LOTE(int p_NUMERO_LOTE) {
        this.p_NUMERO_LOTE = p_NUMERO_LOTE;
    }


    /**
     * Gets the p_VALOR_TOTAL value for this TipoElementoEntradamovimiento_recaudos_pagos.
     * 
     * @return p_VALOR_TOTAL
     */
    public int getP_VALOR_TOTAL() {
        return p_VALOR_TOTAL;
    }


    /**
     * Sets the p_VALOR_TOTAL value for this TipoElementoEntradamovimiento_recaudos_pagos.
     * 
     * @param p_VALOR_TOTAL
     */
    public void setP_VALOR_TOTAL(int p_VALOR_TOTAL) {
        this.p_VALOR_TOTAL = p_VALOR_TOTAL;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoElementoEntradamovimiento_recaudos_pagos)) return false;
        TipoElementoEntradamovimiento_recaudos_pagos other = (TipoElementoEntradamovimiento_recaudos_pagos) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.p_CODIGO_TRANSACCION == other.getP_CODIGO_TRANSACCION() &&
            this.p_NUMERO_LOTE == other.getP_NUMERO_LOTE() &&
            this.p_VALOR_TOTAL == other.getP_VALOR_TOTAL();
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
        _hashCode += getP_CODIGO_TRANSACCION();
        _hashCode += getP_NUMERO_LOTE();
        _hashCode += getP_VALOR_TOTAL();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoElementoEntradamovimiento_recaudos_pagos.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/movimiento_recaudos_pagos", "tipoElementoEntradamovimiento_recaudos_pagos"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_CODIGO_TRANSACCION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/movimiento_recaudos_pagos", "P_CODIGO_TRANSACCION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_NUMERO_LOTE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/movimiento_recaudos_pagos", "P_NUMERO_LOTE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_VALOR_TOTAL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/movimiento_recaudos_pagos", "P_VALOR_TOTAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
