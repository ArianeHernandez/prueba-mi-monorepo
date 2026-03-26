/**
 * TipoEntradamovimiento_tesoreria.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.itosmosys.www.datapi.servicios.movimiento_tesoreria;

public class TipoEntradamovimiento_tesoreria  implements java.io.Serializable {
    private com.itosmosys.www.datapi.servicios.movimiento_tesoreria.TipoElementoEntradamovimiento_tesoreria elementoEntrada;

    public TipoEntradamovimiento_tesoreria() {
    }

    public TipoEntradamovimiento_tesoreria(
           com.itosmosys.www.datapi.servicios.movimiento_tesoreria.TipoElementoEntradamovimiento_tesoreria elementoEntrada) {
           this.elementoEntrada = elementoEntrada;
    }


    /**
     * Gets the elementoEntrada value for this TipoEntradamovimiento_tesoreria.
     * 
     * @return elementoEntrada
     */
    public com.itosmosys.www.datapi.servicios.movimiento_tesoreria.TipoElementoEntradamovimiento_tesoreria getElementoEntrada() {
        return elementoEntrada;
    }


    /**
     * Sets the elementoEntrada value for this TipoEntradamovimiento_tesoreria.
     * 
     * @param elementoEntrada
     */
    public void setElementoEntrada(com.itosmosys.www.datapi.servicios.movimiento_tesoreria.TipoElementoEntradamovimiento_tesoreria elementoEntrada) {
        this.elementoEntrada = elementoEntrada;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoEntradamovimiento_tesoreria)) return false;
        TipoEntradamovimiento_tesoreria other = (TipoEntradamovimiento_tesoreria) obj;
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
        new org.apache.axis.description.TypeDesc(TipoEntradamovimiento_tesoreria.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/movimiento_tesoreria", "tipoEntradamovimiento_tesoreria"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elementoEntrada");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/movimiento_tesoreria", "elementoEntrada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/movimiento_tesoreria", "tipoElementoEntradamovimiento_tesoreria"));
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
