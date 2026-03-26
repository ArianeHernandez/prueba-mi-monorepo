/**
 * TipoElementoSalidalistarRolesporLogin.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario;

public class TipoElementoSalidalistarRolesporLogin  implements java.io.Serializable {
    private java.lang.String id_dependencia;

    private java.lang.String nombre;

    private java.lang.String id_rol;

    public TipoElementoSalidalistarRolesporLogin() {
    }

    public TipoElementoSalidalistarRolesporLogin(
           java.lang.String id_dependencia,
           java.lang.String nombre,
           java.lang.String id_rol) {
           this.id_dependencia = id_dependencia;
           this.nombre = nombre;
           this.id_rol = id_rol;
    }


    /**
     * Gets the id_dependencia value for this TipoElementoSalidalistarRolesporLogin.
     * 
     * @return id_dependencia
     */
    public java.lang.String getId_dependencia() {
        return id_dependencia;
    }


    /**
     * Sets the id_dependencia value for this TipoElementoSalidalistarRolesporLogin.
     * 
     * @param id_dependencia
     */
    public void setId_dependencia(java.lang.String id_dependencia) {
        this.id_dependencia = id_dependencia;
    }


    /**
     * Gets the nombre value for this TipoElementoSalidalistarRolesporLogin.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this TipoElementoSalidalistarRolesporLogin.
     * 
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the id_rol value for this TipoElementoSalidalistarRolesporLogin.
     * 
     * @return id_rol
     */
    public java.lang.String getId_rol() {
        return id_rol;
    }


    /**
     * Sets the id_rol value for this TipoElementoSalidalistarRolesporLogin.
     * 
     * @param id_rol
     */
    public void setId_rol(java.lang.String id_rol) {
        this.id_rol = id_rol;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoElementoSalidalistarRolesporLogin)) return false;
        TipoElementoSalidalistarRolesporLogin other = (TipoElementoSalidalistarRolesporLogin) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.id_dependencia==null && other.getId_dependencia()==null) || 
             (this.id_dependencia!=null &&
              this.id_dependencia.equals(other.getId_dependencia()))) &&
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
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
        if (getId_dependencia() != null) {
            _hashCode += getId_dependencia().hashCode();
        }
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        if (getId_rol() != null) {
            _hashCode += getId_rol().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoElementoSalidalistarRolesporLogin.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoSalidalistarRolesporLogin"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id_dependencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "id_dependencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id_rol");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "id_rol"));
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
