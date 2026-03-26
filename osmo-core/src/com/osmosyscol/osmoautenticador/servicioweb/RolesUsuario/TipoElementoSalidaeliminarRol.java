/**
 * TipoElementoSalidaeliminarRol.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario;

public class TipoElementoSalidaeliminarRol  implements java.io.Serializable {
    private com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.ServiciosArrayElement[] servicios;

    private java.lang.Integer eliminado;

    private com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.UsuariosArrayElement[] usuarios;

    private com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.RolArrayElement[] rol;

    public TipoElementoSalidaeliminarRol() {
    }

    public TipoElementoSalidaeliminarRol(
           com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.ServiciosArrayElement[] servicios,
           java.lang.Integer eliminado,
           com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.UsuariosArrayElement[] usuarios,
           com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.RolArrayElement[] rol) {
           this.servicios = servicios;
           this.eliminado = eliminado;
           this.usuarios = usuarios;
           this.rol = rol;
    }


    /**
     * Gets the servicios value for this TipoElementoSalidaeliminarRol.
     * 
     * @return servicios
     */
    public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.ServiciosArrayElement[] getServicios() {
        return servicios;
    }


    /**
     * Sets the servicios value for this TipoElementoSalidaeliminarRol.
     * 
     * @param servicios
     */
    public void setServicios(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.ServiciosArrayElement[] servicios) {
        this.servicios = servicios;
    }


    /**
     * Gets the eliminado value for this TipoElementoSalidaeliminarRol.
     * 
     * @return eliminado
     */
    public java.lang.Integer getEliminado() {
        return eliminado;
    }


    /**
     * Sets the eliminado value for this TipoElementoSalidaeliminarRol.
     * 
     * @param eliminado
     */
    public void setEliminado(java.lang.Integer eliminado) {
        this.eliminado = eliminado;
    }


    /**
     * Gets the usuarios value for this TipoElementoSalidaeliminarRol.
     * 
     * @return usuarios
     */
    public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.UsuariosArrayElement[] getUsuarios() {
        return usuarios;
    }


    /**
     * Sets the usuarios value for this TipoElementoSalidaeliminarRol.
     * 
     * @param usuarios
     */
    public void setUsuarios(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.UsuariosArrayElement[] usuarios) {
        this.usuarios = usuarios;
    }


    /**
     * Gets the rol value for this TipoElementoSalidaeliminarRol.
     * 
     * @return rol
     */
    public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.RolArrayElement[] getRol() {
        return rol;
    }


    /**
     * Sets the rol value for this TipoElementoSalidaeliminarRol.
     * 
     * @param rol
     */
    public void setRol(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.RolArrayElement[] rol) {
        this.rol = rol;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoElementoSalidaeliminarRol)) return false;
        TipoElementoSalidaeliminarRol other = (TipoElementoSalidaeliminarRol) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.servicios==null && other.getServicios()==null) || 
             (this.servicios!=null &&
              java.util.Arrays.equals(this.servicios, other.getServicios()))) &&
            ((this.eliminado==null && other.getEliminado()==null) || 
             (this.eliminado!=null &&
              this.eliminado.equals(other.getEliminado()))) &&
            ((this.usuarios==null && other.getUsuarios()==null) || 
             (this.usuarios!=null &&
              java.util.Arrays.equals(this.usuarios, other.getUsuarios()))) &&
            ((this.rol==null && other.getRol()==null) || 
             (this.rol!=null &&
              java.util.Arrays.equals(this.rol, other.getRol())));
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
        if (getEliminado() != null) {
            _hashCode += getEliminado().hashCode();
        }
        if (getUsuarios() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUsuarios());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUsuarios(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getRol() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRol());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRol(), i);
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
        new org.apache.axis.description.TypeDesc(TipoElementoSalidaeliminarRol.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "tipoElementoSalidaeliminarRol"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("servicios");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "servicios"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "serviciosArrayElement"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "serviciosElemento"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("eliminado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "eliminado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("usuarios");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "usuarios"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "usuariosArrayElement"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "usuariosElemento"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rol");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "rol"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "rolArrayElement"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "rolElemento"));
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
