/**
 * TipoEntradamovimiento_recaudos_pagos.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.datasuite.webdata.correval.webservice.movimiento_recaudos_pagos;

public class TipoEntradamovimiento_recaudos_pagos  implements java.io.Serializable {
    private com.osmosyscol.datasuite.webdata.correval.webservice.movimiento_recaudos_pagos.TipoElementoEntradamovimiento_recaudos_pagos elementoEntrada;

    public TipoEntradamovimiento_recaudos_pagos() {
    }

    public TipoEntradamovimiento_recaudos_pagos(
           com.osmosyscol.datasuite.webdata.correval.webservice.movimiento_recaudos_pagos.TipoElementoEntradamovimiento_recaudos_pagos elementoEntrada) {
           this.elementoEntrada = elementoEntrada;
    }


    /**
     * Gets the elementoEntrada value for this TipoEntradamovimiento_recaudos_pagos.
     * 
     * @return elementoEntrada
     */
    public com.osmosyscol.datasuite.webdata.correval.webservice.movimiento_recaudos_pagos.TipoElementoEntradamovimiento_recaudos_pagos getElementoEntrada() {
        return elementoEntrada;
    }


    /**
     * Sets the elementoEntrada value for this TipoEntradamovimiento_recaudos_pagos.
     * 
     * @param elementoEntrada
     */
    public void setElementoEntrada(com.osmosyscol.datasuite.webdata.correval.webservice.movimiento_recaudos_pagos.TipoElementoEntradamovimiento_recaudos_pagos elementoEntrada) {
        this.elementoEntrada = elementoEntrada;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoEntradamovimiento_recaudos_pagos)) return false;
        TipoEntradamovimiento_recaudos_pagos other = (TipoEntradamovimiento_recaudos_pagos) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.elementoEntrada==null && other.getElementoEntrada()==null) || 
             (this.elementoEntrada!=null &&
              this.elementoEntrada.equals(other.getElementoEntrada())));
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
        if (getElementoEntrada() != null) {
            _hashCode += getElementoEntrada().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoEntradamovimiento_recaudos_pagos.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/movimiento_recaudos_pagos", "tipoEntradamovimiento_recaudos_pagos"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elementoEntrada");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/movimiento_recaudos_pagos", "elementoEntrada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/movimiento_recaudos_pagos", "tipoElementoEntradamovimiento_recaudos_pagos"));
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
