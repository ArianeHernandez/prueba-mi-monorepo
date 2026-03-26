/**
 * TipoElementoSalidaconsultar_entrada_almacen.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.itosmosys.www.datapi.servicios.consultar_entrada_almacen;

public class TipoElementoSalidaconsultar_entrada_almacen  implements java.io.Serializable {
    private com.itosmosys.www.datapi.servicios.consultar_entrada_almacen.R_ENTRADAS_ALMACENArrayElement[] r_ENTRADAS_ALMACEN;

    public TipoElementoSalidaconsultar_entrada_almacen() {
    }

    public TipoElementoSalidaconsultar_entrada_almacen(
           com.itosmosys.www.datapi.servicios.consultar_entrada_almacen.R_ENTRADAS_ALMACENArrayElement[] r_ENTRADAS_ALMACEN) {
           this.r_ENTRADAS_ALMACEN = r_ENTRADAS_ALMACEN;
    }


    /**
     * Gets the r_ENTRADAS_ALMACEN value for this TipoElementoSalidaconsultar_entrada_almacen.
     * 
     * @return r_ENTRADAS_ALMACEN
     */
    public com.itosmosys.www.datapi.servicios.consultar_entrada_almacen.R_ENTRADAS_ALMACENArrayElement[] getR_ENTRADAS_ALMACEN() {
        return r_ENTRADAS_ALMACEN;
    }


    /**
     * Sets the r_ENTRADAS_ALMACEN value for this TipoElementoSalidaconsultar_entrada_almacen.
     * 
     * @param r_ENTRADAS_ALMACEN
     */
    public void setR_ENTRADAS_ALMACEN(com.itosmosys.www.datapi.servicios.consultar_entrada_almacen.R_ENTRADAS_ALMACENArrayElement[] r_ENTRADAS_ALMACEN) {
        this.r_ENTRADAS_ALMACEN = r_ENTRADAS_ALMACEN;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoElementoSalidaconsultar_entrada_almacen)) return false;
        TipoElementoSalidaconsultar_entrada_almacen other = (TipoElementoSalidaconsultar_entrada_almacen) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.r_ENTRADAS_ALMACEN==null && other.getR_ENTRADAS_ALMACEN()==null) || 
             (this.r_ENTRADAS_ALMACEN!=null &&
              java.util.Arrays.equals(this.r_ENTRADAS_ALMACEN, other.getR_ENTRADAS_ALMACEN())));
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
        if (getR_ENTRADAS_ALMACEN() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getR_ENTRADAS_ALMACEN());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getR_ENTRADAS_ALMACEN(), i);
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
        new org.apache.axis.description.TypeDesc(TipoElementoSalidaconsultar_entrada_almacen.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/consultar_entrada_almacen", "tipoElementoSalidaconsultar_entrada_almacen"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("r_ENTRADAS_ALMACEN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/consultar_entrada_almacen", "R_ENTRADAS_ALMACEN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/consultar_entrada_almacen", "R_ENTRADAS_ALMACENArrayElement"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/consultar_entrada_almacen", "R_ENTRADAS_ALMACENElemento"));
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
