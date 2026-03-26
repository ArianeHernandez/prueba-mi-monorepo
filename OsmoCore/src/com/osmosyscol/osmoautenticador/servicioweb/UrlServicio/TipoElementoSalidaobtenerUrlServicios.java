/**
 * TipoElementoSalidaobtenerUrlServicios.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.osmoautenticador.servicioweb.UrlServicio;

public class TipoElementoSalidaobtenerUrlServicios  implements java.io.Serializable {
    private java.lang.String url_servicio;

    private java.lang.String nombre_servicio;

    private int id_rol;

    private int id_servicio;

    private java.lang.String nombre_rol;

    public TipoElementoSalidaobtenerUrlServicios() {
    }

    public TipoElementoSalidaobtenerUrlServicios(
           java.lang.String url_servicio,
           java.lang.String nombre_servicio,
           int id_rol,
           int id_servicio,
           java.lang.String nombre_rol) {
           this.url_servicio = url_servicio;
           this.nombre_servicio = nombre_servicio;
           this.id_rol = id_rol;
           this.id_servicio = id_servicio;
           this.nombre_rol = nombre_rol;
    }


    /**
     * Gets the url_servicio value for this TipoElementoSalidaobtenerUrlServicios.
     * 
     * @return url_servicio
     */
    public java.lang.String getUrl_servicio() {
        return url_servicio;
    }


    /**
     * Sets the url_servicio value for this TipoElementoSalidaobtenerUrlServicios.
     * 
     * @param url_servicio
     */
    public void setUrl_servicio(java.lang.String url_servicio) {
        this.url_servicio = url_servicio;
    }


    /**
     * Gets the nombre_servicio value for this TipoElementoSalidaobtenerUrlServicios.
     * 
     * @return nombre_servicio
     */
    public java.lang.String getNombre_servicio() {
        return nombre_servicio;
    }


    /**
     * Sets the nombre_servicio value for this TipoElementoSalidaobtenerUrlServicios.
     * 
     * @param nombre_servicio
     */
    public void setNombre_servicio(java.lang.String nombre_servicio) {
        this.nombre_servicio = nombre_servicio;
    }


    /**
     * Gets the id_rol value for this TipoElementoSalidaobtenerUrlServicios.
     * 
     * @return id_rol
     */
    public int getId_rol() {
        return id_rol;
    }


    /**
     * Sets the id_rol value for this TipoElementoSalidaobtenerUrlServicios.
     * 
     * @param id_rol
     */
    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }


    /**
     * Gets the id_servicio value for this TipoElementoSalidaobtenerUrlServicios.
     * 
     * @return id_servicio
     */
    public int getId_servicio() {
        return id_servicio;
    }


    /**
     * Sets the id_servicio value for this TipoElementoSalidaobtenerUrlServicios.
     * 
     * @param id_servicio
     */
    public void setId_servicio(int id_servicio) {
        this.id_servicio = id_servicio;
    }


    /**
     * Gets the nombre_rol value for this TipoElementoSalidaobtenerUrlServicios.
     * 
     * @return nombre_rol
     */
    public java.lang.String getNombre_rol() {
        return nombre_rol;
    }


    /**
     * Sets the nombre_rol value for this TipoElementoSalidaobtenerUrlServicios.
     * 
     * @param nombre_rol
     */
    public void setNombre_rol(java.lang.String nombre_rol) {
        this.nombre_rol = nombre_rol;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoElementoSalidaobtenerUrlServicios)) return false;
        TipoElementoSalidaobtenerUrlServicios other = (TipoElementoSalidaobtenerUrlServicios) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.url_servicio==null && other.getUrl_servicio()==null) || 
             (this.url_servicio!=null &&
              this.url_servicio.equals(other.getUrl_servicio()))) &&
            ((this.nombre_servicio==null && other.getNombre_servicio()==null) || 
             (this.nombre_servicio!=null &&
              this.nombre_servicio.equals(other.getNombre_servicio()))) &&
            this.id_rol == other.getId_rol() &&
            this.id_servicio == other.getId_servicio() &&
            ((this.nombre_rol==null && other.getNombre_rol()==null) || 
             (this.nombre_rol!=null &&
              this.nombre_rol.equals(other.getNombre_rol())));
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
        if (getUrl_servicio() != null) {
            _hashCode += getUrl_servicio().hashCode();
        }
        if (getNombre_servicio() != null) {
            _hashCode += getNombre_servicio().hashCode();
        }
        _hashCode += getId_rol();
        _hashCode += getId_servicio();
        if (getNombre_rol() != null) {
            _hashCode += getNombre_rol().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoElementoSalidaobtenerUrlServicios.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/UrlServicio", "tipoElementoSalidaobtenerUrlServicios"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("url_servicio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "url_servicio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre_servicio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombre_servicio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id_rol");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id_rol"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id_servicio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id_servicio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre_rol");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombre_rol"));
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
