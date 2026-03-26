/**
 * TipoElementoEntradaactualizar_estado_orden_pago.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.itosmosys.www.datapi.servicios.actualizar_estado_orden_pago;

public class TipoElementoEntradaactualizar_estado_orden_pago  implements java.io.Serializable {
    private java.lang.Integer p_EMPRESA_ORDEN_PAGO;

    private java.lang.Integer p_VIGENCIA_ORDEN_PAGO;

    private java.lang.Integer p_NUMERO_ORDEN_PAGO;

    public TipoElementoEntradaactualizar_estado_orden_pago() {
    }

    public TipoElementoEntradaactualizar_estado_orden_pago(
           java.lang.Integer p_EMPRESA_ORDEN_PAGO,
           java.lang.Integer p_VIGENCIA_ORDEN_PAGO,
           java.lang.Integer p_NUMERO_ORDEN_PAGO) {
           this.p_EMPRESA_ORDEN_PAGO = p_EMPRESA_ORDEN_PAGO;
           this.p_VIGENCIA_ORDEN_PAGO = p_VIGENCIA_ORDEN_PAGO;
           this.p_NUMERO_ORDEN_PAGO = p_NUMERO_ORDEN_PAGO;
    }


    /**
     * Gets the p_EMPRESA_ORDEN_PAGO value for this TipoElementoEntradaactualizar_estado_orden_pago.
     * 
     * @return p_EMPRESA_ORDEN_PAGO
     */
    public java.lang.Integer getP_EMPRESA_ORDEN_PAGO() {
        return p_EMPRESA_ORDEN_PAGO;
    }


    /**
     * Sets the p_EMPRESA_ORDEN_PAGO value for this TipoElementoEntradaactualizar_estado_orden_pago.
     * 
     * @param p_EMPRESA_ORDEN_PAGO
     */
    public void setP_EMPRESA_ORDEN_PAGO(java.lang.Integer p_EMPRESA_ORDEN_PAGO) {
        this.p_EMPRESA_ORDEN_PAGO = p_EMPRESA_ORDEN_PAGO;
    }


    /**
     * Gets the p_VIGENCIA_ORDEN_PAGO value for this TipoElementoEntradaactualizar_estado_orden_pago.
     * 
     * @return p_VIGENCIA_ORDEN_PAGO
     */
    public java.lang.Integer getP_VIGENCIA_ORDEN_PAGO() {
        return p_VIGENCIA_ORDEN_PAGO;
    }


    /**
     * Sets the p_VIGENCIA_ORDEN_PAGO value for this TipoElementoEntradaactualizar_estado_orden_pago.
     * 
     * @param p_VIGENCIA_ORDEN_PAGO
     */
    public void setP_VIGENCIA_ORDEN_PAGO(java.lang.Integer p_VIGENCIA_ORDEN_PAGO) {
        this.p_VIGENCIA_ORDEN_PAGO = p_VIGENCIA_ORDEN_PAGO;
    }


    /**
     * Gets the p_NUMERO_ORDEN_PAGO value for this TipoElementoEntradaactualizar_estado_orden_pago.
     * 
     * @return p_NUMERO_ORDEN_PAGO
     */
    public java.lang.Integer getP_NUMERO_ORDEN_PAGO() {
        return p_NUMERO_ORDEN_PAGO;
    }


    /**
     * Sets the p_NUMERO_ORDEN_PAGO value for this TipoElementoEntradaactualizar_estado_orden_pago.
     * 
     * @param p_NUMERO_ORDEN_PAGO
     */
    public void setP_NUMERO_ORDEN_PAGO(java.lang.Integer p_NUMERO_ORDEN_PAGO) {
        this.p_NUMERO_ORDEN_PAGO = p_NUMERO_ORDEN_PAGO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoElementoEntradaactualizar_estado_orden_pago)) return false;
        TipoElementoEntradaactualizar_estado_orden_pago other = (TipoElementoEntradaactualizar_estado_orden_pago) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.p_EMPRESA_ORDEN_PAGO==null && other.getP_EMPRESA_ORDEN_PAGO()==null) || 
             (this.p_EMPRESA_ORDEN_PAGO!=null &&
              this.p_EMPRESA_ORDEN_PAGO.equals(other.getP_EMPRESA_ORDEN_PAGO()))) &&
            ((this.p_VIGENCIA_ORDEN_PAGO==null && other.getP_VIGENCIA_ORDEN_PAGO()==null) || 
             (this.p_VIGENCIA_ORDEN_PAGO!=null &&
              this.p_VIGENCIA_ORDEN_PAGO.equals(other.getP_VIGENCIA_ORDEN_PAGO()))) &&
            ((this.p_NUMERO_ORDEN_PAGO==null && other.getP_NUMERO_ORDEN_PAGO()==null) || 
             (this.p_NUMERO_ORDEN_PAGO!=null &&
              this.p_NUMERO_ORDEN_PAGO.equals(other.getP_NUMERO_ORDEN_PAGO())));
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
        if (getP_EMPRESA_ORDEN_PAGO() != null) {
            _hashCode += getP_EMPRESA_ORDEN_PAGO().hashCode();
        }
        if (getP_VIGENCIA_ORDEN_PAGO() != null) {
            _hashCode += getP_VIGENCIA_ORDEN_PAGO().hashCode();
        }
        if (getP_NUMERO_ORDEN_PAGO() != null) {
            _hashCode += getP_NUMERO_ORDEN_PAGO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoElementoEntradaactualizar_estado_orden_pago.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/actualizar_estado_orden_pago", "tipoElementoEntradaactualizar_estado_orden_pago"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_EMPRESA_ORDEN_PAGO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/actualizar_estado_orden_pago", "P_EMPRESA_ORDEN_PAGO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_VIGENCIA_ORDEN_PAGO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/actualizar_estado_orden_pago", "P_VIGENCIA_ORDEN_PAGO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_NUMERO_ORDEN_PAGO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/actualizar_estado_orden_pago", "P_NUMERO_ORDEN_PAGO"));
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
