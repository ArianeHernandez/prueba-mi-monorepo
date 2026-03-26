/**
 * TipoElementoEntradaadicionarServicioRol.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario;

public class TipoElementoEntradaadicionarServicioRol  implements java.io.Serializable {
    private java.lang.Integer id_servicio;

    private java.lang.Integer id_rol;

    public TipoElementoEntradaadicionarServicioRol() {
    }

    public TipoElementoEntradaadicionarServicioRol(
           java.lang.Integer id_servicio,
           java.lang.Integer id_rol) {
           this.id_servicio = id_servicio;
           this.id_rol = id_rol;
    }


    /**
     * Gets the id_servicio value for this TipoElementoEntradaadicionarServicioRol.
     * 
     * @return id_servicio
     */
    public java.lang.Integer getId_servicio() {
        return id_servicio;
    }


    /**
     * Sets the id_servicio value for this TipoElementoEntradaadicionarServicioRol.
     * 
     * @param id_servicio
     */
    public void setId_servicio(java.lang.Integer id_servicio) {
        this.id_servicio = id_servicio;
    }


    /**
     * Gets the id_rol value for this TipoElementoEntradaadicionarServicioRol.
     * 
     * @return id_rol
     */
    public java.lang.Integer getId_rol() {
        return id_rol;
    }


    /**
     * Sets the id_rol value for this TipoElementoEntradaadicionarServicioRol.
     * 
     * @param id_rol
     */
    public void setId_rol(java.lang.Integer id_rol) {
        this.id_rol = id_rol;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoElementoEntradaadicionarServicioRol)) return false;
        TipoElementoEntradaadicionarServicioRol other = (TipoElementoEntradaadicionarServicioRol) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.id_servicio==null && other.getId_servicio()==null) || 
             (this.id_servicio!=null &&
              this.id_servicio.equals(other.getId_servicio()))) &&
            ((this.id_rol==null && other.getId_rol()==null) || 
             (this.id_rol!=null &&
              this.id_rol.equals(other.getId_rol())));
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
        if (getId_servicio() != null) {
            _hashCode += getId_servicio().hashCode();
        }
        if (getId_rol() != null) {
            _hashCode += getId_rol().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoElementoEntradaadicionarServicioRol.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoEntradaadicionarServicioRol"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id_servicio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "id_servicio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id_rol");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "id_rol"));
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
