/**
 * TipoElementoSalidaactualizar_estado_orden_pago.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.itosmosys.www.datapi.servicios.actualizar_estado_orden_pago;

public class TipoElementoSalidaactualizar_estado_orden_pago  implements java.io.Serializable {
    private java.lang.String r_ESTADO_ORDEN_PAGO;

    public TipoElementoSalidaactualizar_estado_orden_pago() {
    }

    public TipoElementoSalidaactualizar_estado_orden_pago(
           java.lang.String r_ESTADO_ORDEN_PAGO) {
           this.r_ESTADO_ORDEN_PAGO = r_ESTADO_ORDEN_PAGO;
    }


    /**
     * Gets the r_ESTADO_ORDEN_PAGO value for this TipoElementoSalidaactualizar_estado_orden_pago.
     * 
     * @return r_ESTADO_ORDEN_PAGO
     */
    public java.lang.String getR_ESTADO_ORDEN_PAGO() {
        return r_ESTADO_ORDEN_PAGO;
    }


    /**
     * Sets the r_ESTADO_ORDEN_PAGO value for this TipoElementoSalidaactualizar_estado_orden_pago.
     * 
     * @param r_ESTADO_ORDEN_PAGO
     */
    public void setR_ESTADO_ORDEN_PAGO(java.lang.String r_ESTADO_ORDEN_PAGO) {
        this.r_ESTADO_ORDEN_PAGO = r_ESTADO_ORDEN_PAGO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoElementoSalidaactualizar_estado_orden_pago)) return false;
        TipoElementoSalidaactualizar_estado_orden_pago other = (TipoElementoSalidaactualizar_estado_orden_pago) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.r_ESTADO_ORDEN_PAGO==null && other.getR_ESTADO_ORDEN_PAGO()==null) || 
             (this.r_ESTADO_ORDEN_PAGO!=null &&
              this.r_ESTADO_ORDEN_PAGO.equals(other.getR_ESTADO_ORDEN_PAGO())));
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
        if (getR_ESTADO_ORDEN_PAGO() != null) {
            _hashCode += getR_ESTADO_ORDEN_PAGO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoElementoSalidaactualizar_estado_orden_pago.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/actualizar_estado_orden_pago", "tipoElementoSalidaactualizar_estado_orden_pago"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("r_ESTADO_ORDEN_PAGO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/actualizar_estado_orden_pago", "R_ESTADO_ORDEN_PAGO"));
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
