/**
 * TipoElementoSalidalistarRoles.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario;

public class TipoElementoSalidalistarRoles  implements java.io.Serializable {
    private java.lang.String nombre;

    private java.lang.String id_rol;

    private com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.ServiciosArrayElement[] servicios;

    private java.lang.String dependencia;

    public TipoElementoSalidalistarRoles() {
    }

    public TipoElementoSalidalistarRoles(
           java.lang.String nombre,
           java.lang.String id_rol,
           com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.ServiciosArrayElement[] servicios,
           java.lang.String dependencia) {
           this.nombre = nombre;
           this.id_rol = id_rol;
           this.servicios = servicios;
           this.dependencia = dependencia;
    }


    /**
     * Gets the nombre value for this TipoElementoSalidalistarRoles.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this TipoElementoSalidalistarRoles.
     * 
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the id_rol value for this TipoElementoSalidalistarRoles.
     * 
     * @return id_rol
     */
    public java.lang.String getId_rol() {
        return id_rol;
    }


    /**
     * Sets the id_rol value for this TipoElementoSalidalistarRoles.
     * 
     * @param id_rol
     */
    public void setId_rol(java.lang.String id_rol) {
        this.id_rol = id_rol;
    }


    /**
     * Gets the servicios value for this TipoElementoSalidalistarRoles.
     * 
     * @return servicios
     */
    public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.ServiciosArrayElement[] getServicios() {
        return servicios;
    }


    /**
     * Sets the servicios value for this TipoElementoSalidalistarRoles.
     * 
     * @param servicios
     */
    public void setServicios(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.ServiciosArrayElement[] servicios) {
        this.servicios = servicios;
    }


    /**
     * Gets the dependencia value for this TipoElementoSalidalistarRoles.
     * 
     * @return dependencia
     */
    public java.lang.String getDependencia() {
        return dependencia;
    }


    /**
     * Sets the dependencia value for this TipoElementoSalidalistarRoles.
     * 
     * @param dependencia
     */
    public void setDependencia(java.lang.String dependencia) {
        this.dependencia = dependencia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoElementoSalidalistarRoles)) return false;
        TipoElementoSalidalistarRoles other = (TipoElementoSalidalistarRoles) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            ((this.id_rol==null && other.getId_rol()==null) || 
             (this.id_rol!=null &&
              this.id_rol.equals(other.getId_rol()))) &&
            ((this.servicios==null && other.getServicios()==null) || 
             (this.servicios!=null &&
              java.util.Arrays.equals(this.servicios, other.getServicios()))) &&
            ((this.dependencia==null && other.getDependencia()==null) || 
             (this.dependencia!=null &&
              this.dependencia.equals(other.getDependencia())));
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
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        if (getId_rol() != null) {
            _hashCode += getId_rol().hashCode();
        }
        if (getServicios() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getServicios());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getServicios(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDependencia() != null) {
            _hashCode += getDependencia().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoElementoSalidalistarRoles.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoSalidalistarRoles"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("servicios");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "servicios"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "serviciosArrayElement"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "serviciosElemento"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dependencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "dependencia"));
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
