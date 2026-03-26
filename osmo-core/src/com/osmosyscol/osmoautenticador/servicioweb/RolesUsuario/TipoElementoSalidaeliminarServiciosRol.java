/**
 * TipoElementoSalidaeliminarServiciosRol.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario;

public class TipoElementoSalidaeliminarServiciosRol  implements java.io.Serializable {
    private java.lang.Integer eliminados;

    public TipoElementoSalidaeliminarServiciosRol() {
    }

    public TipoElementoSalidaeliminarServiciosRol(
           java.lang.Integer eliminados) {
           this.eliminados = eliminados;
    }


    /**
     * Gets the eliminados value for this TipoElementoSalidaeliminarServiciosRol.
     * 
     * @return eliminados
     */
    public java.lang.Integer getEliminados() {
        return eliminados;
    }


    /**
     * Sets the eliminados value for this TipoElementoSalidaeliminarServiciosRol.
     * 
     * @param eliminados
     */
    public void setEliminados(java.lang.Integer eliminados) {
        this.eliminados = eliminados;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoElementoSalidaeliminarServiciosRol)) return false;
        TipoElementoSalidaeliminarServiciosRol other = (TipoElementoSalidaeliminarServiciosRol) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.eliminados==null && other.getEliminados()==null) || 
             (this.eliminados!=null &&
              this.eliminados.equals(other.getEliminados())));
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
        if (getEliminados() != null) {
            _hashCode += getEliminados().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoElementoSalidaeliminarServiciosRol.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoSalidaeliminarServiciosRol"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("eliminados");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "eliminados"));
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
