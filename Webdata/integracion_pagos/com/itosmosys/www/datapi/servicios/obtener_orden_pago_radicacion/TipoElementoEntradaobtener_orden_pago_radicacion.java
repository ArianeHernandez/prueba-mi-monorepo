/**
 * TipoElementoEntradaobtener_orden_pago_radicacion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.itosmosys.www.datapi.servicios.obtener_orden_pago_radicacion;

public class TipoElementoEntradaobtener_orden_pago_radicacion  implements java.io.Serializable {
    private java.lang.Integer p_EMPRESA_RADICACION;

    private java.lang.Integer p_VIGENCIA_RADICACION;

    private java.lang.Integer p_NUMERO_RADICACION;

    public TipoElementoEntradaobtener_orden_pago_radicacion() {
    }

    public TipoElementoEntradaobtener_orden_pago_radicacion(
           java.lang.Integer p_EMPRESA_RADICACION,
           java.lang.Integer p_VIGENCIA_RADICACION,
           java.lang.Integer p_NUMERO_RADICACION) {
           this.p_EMPRESA_RADICACION = p_EMPRESA_RADICACION;
           this.p_VIGENCIA_RADICACION = p_VIGENCIA_RADICACION;
           this.p_NUMERO_RADICACION = p_NUMERO_RADICACION;
    }


    /**
     * Gets the p_EMPRESA_RADICACION value for this TipoElementoEntradaobtener_orden_pago_radicacion.
     * 
     * @return p_EMPRESA_RADICACION
     */
    public java.lang.Integer getP_EMPRESA_RADICACION() {
        return p_EMPRESA_RADICACION;
    }


    /**
     * Sets the p_EMPRESA_RADICACION value for this TipoElementoEntradaobtener_orden_pago_radicacion.
     * 
     * @param p_EMPRESA_RADICACION
     */
    public void setP_EMPRESA_RADICACION(java.lang.Integer p_EMPRESA_RADICACION) {
        this.p_EMPRESA_RADICACION = p_EMPRESA_RADICACION;
    }


    /**
     * Gets the p_VIGENCIA_RADICACION value for this TipoElementoEntradaobtener_orden_pago_radicacion.
     * 
     * @return p_VIGENCIA_RADICACION
     */
    public java.lang.Integer getP_VIGENCIA_RADICACION() {
        return p_VIGENCIA_RADICACION;
    }


    /**
     * Sets the p_VIGENCIA_RADICACION value for this TipoElementoEntradaobtener_orden_pago_radicacion.
     * 
     * @param p_VIGENCIA_RADICACION
     */
    public void setP_VIGENCIA_RADICACION(java.lang.Integer p_VIGENCIA_RADICACION) {
        this.p_VIGENCIA_RADICACION = p_VIGENCIA_RADICACION;
    }


    /**
     * Gets the p_NUMERO_RADICACION value for this TipoElementoEntradaobtener_orden_pago_radicacion.
     * 
     * @return p_NUMERO_RADICACION
     */
    public java.lang.Integer getP_NUMERO_RADICACION() {
        return p_NUMERO_RADICACION;
    }


    /**
     * Sets the p_NUMERO_RADICACION value for this TipoElementoEntradaobtener_orden_pago_radicacion.
     * 
     * @param p_NUMERO_RADICACION
     */
    public void setP_NUMERO_RADICACION(java.lang.Integer p_NUMERO_RADICACION) {
        this.p_NUMERO_RADICACION = p_NUMERO_RADICACION;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoElementoEntradaobtener_orden_pago_radicacion)) return false;
        TipoElementoEntradaobtener_orden_pago_radicacion other = (TipoElementoEntradaobtener_orden_pago_radicacion) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.p_EMPRESA_RADICACION==null && other.getP_EMPRESA_RADICACION()==null) || 
             (this.p_EMPRESA_RADICACION!=null &&
              this.p_EMPRESA_RADICACION.equals(other.getP_EMPRESA_RADICACION()))) &&
            ((this.p_VIGENCIA_RADICACION==null && other.getP_VIGENCIA_RADICACION()==null) || 
             (this.p_VIGENCIA_RADICACION!=null &&
              this.p_VIGENCIA_RADICACION.equals(other.getP_VIGENCIA_RADICACION()))) &&
            ((this.p_NUMERO_RADICACION==null && other.getP_NUMERO_RADICACION()==null) || 
             (this.p_NUMERO_RADICACION!=null &&
              this.p_NUMERO_RADICACION.equals(other.getP_NUMERO_RADICACION())));
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
        if (getP_EMPRESA_RADICACION() != null) {
            _hashCode += getP_EMPRESA_RADICACION().hashCode();
        }
        if (getP_VIGENCIA_RADICACION() != null) {
            _hashCode += getP_VIGENCIA_RADICACION().hashCode();
        }
        if (getP_NUMERO_RADICACION() != null) {
            _hashCode += getP_NUMERO_RADICACION().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoElementoEntradaobtener_orden_pago_radicacion.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/obtener_orden_pago_radicacion", "tipoElementoEntradaobtener_orden_pago_radicacion"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_EMPRESA_RADICACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/obtener_orden_pago_radicacion", "P_EMPRESA_RADICACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_VIGENCIA_RADICACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/obtener_orden_pago_radicacion", "P_VIGENCIA_RADICACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_NUMERO_RADICACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/obtener_orden_pago_radicacion", "P_NUMERO_RADICACION"));
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
