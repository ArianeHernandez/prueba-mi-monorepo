/**
 * TipoElementoSalidaadicionarServicioRol.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario;

public class TipoElementoSalidaadicionarServicioRol  implements java.io.Serializable {
    private java.lang.Integer adicionado;

    public TipoElementoSalidaadicionarServicioRol() {
    }

    public TipoElementoSalidaadicionarServicioRol(
           java.lang.Integer adicionado) {
           this.adicionado = adicionado;
    }


    /**
     * Gets the adicionado value for this TipoElementoSalidaadicionarServicioRol.
     * 
     * @return adicionado
     */
    public java.lang.Integer getAdicionado() {
        return adicionado;
    }


    /**
     * Sets the adicionado value for this TipoElementoSalidaadicionarServicioRol.
     * 
     * @param adicionado
     */
    public void setAdicionado(java.lang.Integer adicionado) {
        this.adicionado = adicionado;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoElementoSalidaadicionarServicioRol)) return false;
        TipoElementoSalidaadicionarServicioRol other = (TipoElementoSalidaadicionarServicioRol) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.adicionado==null && other.getAdicionado()==null) || 
             (this.adicionado!=null &&
              this.adicionado.equals(other.getAdicionado())));
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
        if (getAdicionado() != null) {
            _hashCode += getAdicionado().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoElementoSalidaadicionarServicioRol.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoSalidaadicionarServicioRol"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("adicionado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "adicionado"));
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
