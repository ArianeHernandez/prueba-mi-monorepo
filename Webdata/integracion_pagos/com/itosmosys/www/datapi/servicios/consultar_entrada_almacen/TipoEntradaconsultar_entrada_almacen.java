/**
 * TipoEntradaconsultar_entrada_almacen.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.itosmosys.www.datapi.servicios.consultar_entrada_almacen;

public class TipoEntradaconsultar_entrada_almacen  implements java.io.Serializable {
    private com.itosmosys.www.datapi.servicios.consultar_entrada_almacen.TipoElementoEntradaconsultar_entrada_almacen elementoEntrada;

    public TipoEntradaconsultar_entrada_almacen() {
    }

    public TipoEntradaconsultar_entrada_almacen(
           com.itosmosys.www.datapi.servicios.consultar_entrada_almacen.TipoElementoEntradaconsultar_entrada_almacen elementoEntrada) {
           this.elementoEntrada = elementoEntrada;
    }


    /**
     * Gets the elementoEntrada value for this TipoEntradaconsultar_entrada_almacen.
     * 
     * @return elementoEntrada
     */
    public com.itosmosys.www.datapi.servicios.consultar_entrada_almacen.TipoElementoEntradaconsultar_entrada_almacen getElementoEntrada() {
        return elementoEntrada;
    }


    /**
     * Sets the elementoEntrada value for this TipoEntradaconsultar_entrada_almacen.
     * 
     * @param elementoEntrada
     */
    public void setElementoEntrada(com.itosmosys.www.datapi.servicios.consultar_entrada_almacen.TipoElementoEntradaconsultar_entrada_almacen elementoEntrada) {
        this.elementoEntrada = elementoEntrada;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoEntradaconsultar_entrada_almacen)) return false;
        TipoEntradaconsultar_entrada_almacen other = (TipoEntradaconsultar_entrada_almacen) obj;
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
        new org.apache.axis.description.TypeDesc(TipoEntradaconsultar_entrada_almacen.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/consultar_entrada_almacen", "tipoEntradaconsultar_entrada_almacen"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elementoEntrada");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/consultar_entrada_almacen", "elementoEntrada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/consultar_entrada_almacen", "tipoElementoEntradaconsultar_entrada_almacen"));
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
