/**
 * R_ORDENES_PAGOArrayElement.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.itosmosys.www.datapi.servicios.obtener_orden_pago_radicacion;

public class R_ORDENES_PAGOArrayElement  implements java.io.Serializable {
    private java.lang.Integer NUMERO_ORDEN_PAGO;

    public R_ORDENES_PAGOArrayElement() {
    }

    public R_ORDENES_PAGOArrayElement(
           java.lang.Integer NUMERO_ORDEN_PAGO) {
           this.NUMERO_ORDEN_PAGO = NUMERO_ORDEN_PAGO;
    }


    /**
     * Gets the NUMERO_ORDEN_PAGO value for this R_ORDENES_PAGOArrayElement.
     * 
     * @return NUMERO_ORDEN_PAGO
     */
    public java.lang.Integer getNUMERO_ORDEN_PAGO() {
        return NUMERO_ORDEN_PAGO;
    }


    /**
     * Sets the NUMERO_ORDEN_PAGO value for this R_ORDENES_PAGOArrayElement.
     * 
     * @param NUMERO_ORDEN_PAGO
     */
    public void setNUMERO_ORDEN_PAGO(java.lang.Integer NUMERO_ORDEN_PAGO) {
        this.NUMERO_ORDEN_PAGO = NUMERO_ORDEN_PAGO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof R_ORDENES_PAGOArrayElement)) return false;
        R_ORDENES_PAGOArrayElement other = (R_ORDENES_PAGOArrayElement) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.NUMERO_ORDEN_PAGO==null && other.getNUMERO_ORDEN_PAGO()==null) || 
             (this.NUMERO_ORDEN_PAGO!=null &&
              this.NUMERO_ORDEN_PAGO.equals(other.getNUMERO_ORDEN_PAGO())));
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
        if (getNUMERO_ORDEN_PAGO() != null) {
            _hashCode += getNUMERO_ORDEN_PAGO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(R_ORDENES_PAGOArrayElement.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/obtener_orden_pago_radicacion", "R_ORDENES_PAGOArrayElement"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUMERO_ORDEN_PAGO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/obtener_orden_pago_radicacion", "NUMERO_ORDEN_PAGO"));
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
