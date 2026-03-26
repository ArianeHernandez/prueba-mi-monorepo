/**
 * TipoElementoSalidaobtener_orden_pago_radicacion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.itosmosys.www.datapi.servicios.obtener_orden_pago_radicacion;

public class TipoElementoSalidaobtener_orden_pago_radicacion  implements java.io.Serializable {
    private com.itosmosys.www.datapi.servicios.obtener_orden_pago_radicacion.R_ORDENES_PAGOArrayElement[] r_ORDENES_PAGO;

    public TipoElementoSalidaobtener_orden_pago_radicacion() {
    }

    public TipoElementoSalidaobtener_orden_pago_radicacion(
           com.itosmosys.www.datapi.servicios.obtener_orden_pago_radicacion.R_ORDENES_PAGOArrayElement[] r_ORDENES_PAGO) {
           this.r_ORDENES_PAGO = r_ORDENES_PAGO;
    }


    /**
     * Gets the r_ORDENES_PAGO value for this TipoElementoSalidaobtener_orden_pago_radicacion.
     * 
     * @return r_ORDENES_PAGO
     */
    public com.itosmosys.www.datapi.servicios.obtener_orden_pago_radicacion.R_ORDENES_PAGOArrayElement[] getR_ORDENES_PAGO() {
        return r_ORDENES_PAGO;
    }


    /**
     * Sets the r_ORDENES_PAGO value for this TipoElementoSalidaobtener_orden_pago_radicacion.
     * 
     * @param r_ORDENES_PAGO
     */
    public void setR_ORDENES_PAGO(com.itosmosys.www.datapi.servicios.obtener_orden_pago_radicacion.R_ORDENES_PAGOArrayElement[] r_ORDENES_PAGO) {
        this.r_ORDENES_PAGO = r_ORDENES_PAGO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoElementoSalidaobtener_orden_pago_radicacion)) return false;
        TipoElementoSalidaobtener_orden_pago_radicacion other = (TipoElementoSalidaobtener_orden_pago_radicacion) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.r_ORDENES_PAGO==null && other.getR_ORDENES_PAGO()==null) || 
             (this.r_ORDENES_PAGO!=null &&
              java.util.Arrays.equals(this.r_ORDENES_PAGO, other.getR_ORDENES_PAGO())));
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
        if (getR_ORDENES_PAGO() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getR_ORDENES_PAGO());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getR_ORDENES_PAGO(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoElementoSalidaobtener_orden_pago_radicacion.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/obtener_orden_pago_radicacion", "tipoElementoSalidaobtener_orden_pago_radicacion"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("r_ORDENES_PAGO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/obtener_orden_pago_radicacion", "R_ORDENES_PAGO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/obtener_orden_pago_radicacion", "R_ORDENES_PAGOArrayElement"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/obtener_orden_pago_radicacion", "R_ORDENES_PAGOElemento"));
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
