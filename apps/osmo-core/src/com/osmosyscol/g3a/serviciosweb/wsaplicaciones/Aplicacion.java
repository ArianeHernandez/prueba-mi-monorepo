/**
 * Aplicacion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.g3a.serviciosweb.wsaplicaciones;

import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.commons.utils.rsa.CoreRSA;
import com.osmosyscol.g3a.servicios.AutenticacionServicioG3A;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class Aplicacion  implements java.io.Serializable {
	
    private java.lang.String descripcion;

    private java.lang.String endpoint;

    private java.lang.Integer id_aplicacion;

    private java.lang.String nombre;

    private java.lang.String url_inicio;

    public Aplicacion() {
    }

    public Aplicacion(
           java.lang.String descripcion,
           java.lang.String endpoint,
           java.lang.Integer id_aplicacion,
           java.lang.String nombre,
           java.lang.String url_inicio) {
           this.descripcion = descripcion;
           this.endpoint = endpoint;
           this.id_aplicacion = id_aplicacion;
           this.nombre = nombre;
           this.url_inicio = url_inicio;
    }

    /**
     * por defecto trata de codificar la url, a menos que se indique que
     * no se debe encriptar
     * @return
     */
    public String getUrl() {
    	
    	
    	String encryptBackUrlDisabled = Boolean.FALSE.toString(); 
    	encryptBackUrlDisabled = ParametrosInicio.getProperty(AutenticacionServicioG3A.ZWT_SSO_BACKURL_ENCRYPTDISABLED);
    	
    	if( !StringUtils.esVerdad(encryptBackUrlDisabled)) {
    		// por defecto cuando g3a se encuentra funcionando, se encripta
        	return CoreRSA.cifrar(endpoint + "/" + url_inicio, CoreRSA.clave);
    	}

    	return endpoint + "/" + url_inicio;
	}

    /**
     * Gets the descripcion value for this Aplicacion.
     * 
     * @return descripcion
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this Aplicacion.
     * 
     * @param descripcion
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the endpoint value for this Aplicacion.
     * 
     * @return endpoint
     */
    public java.lang.String getEndpoint() {
        return endpoint;
    }


    /**
     * Sets the endpoint value for this Aplicacion.
     * 
     * @param endpoint
     */
    public void setEndpoint(java.lang.String endpoint) {
        this.endpoint = endpoint;
    }


    /**
     * Gets the id_aplicacion value for this Aplicacion.
     * 
     * @return id_aplicacion
     */
    public java.lang.Integer getId_aplicacion() {
        return id_aplicacion;
    }


    /**
     * Sets the id_aplicacion value for this Aplicacion.
     * 
     * @param id_aplicacion
     */
    public void setId_aplicacion(java.lang.Integer id_aplicacion) {
        this.id_aplicacion = id_aplicacion;
    }


    /**
     * Gets the nombre value for this Aplicacion.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this Aplicacion.
     * 
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the url_inicio value for this Aplicacion.
     * 
     * @return url_inicio
     */
    public java.lang.String getUrl_inicio() {
        return url_inicio;
    }


    /**
     * Sets the url_inicio value for this Aplicacion.
     * 
     * @param url_inicio
     */
    public void setUrl_inicio(java.lang.String url_inicio) {
        this.url_inicio = url_inicio;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Aplicacion)) return false;
        Aplicacion other = (Aplicacion) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            ((this.endpoint==null && other.getEndpoint()==null) || 
             (this.endpoint!=null &&
              this.endpoint.equals(other.getEndpoint()))) &&
            ((this.id_aplicacion==null && other.getId_aplicacion()==null) || 
             (this.id_aplicacion!=null &&
              this.id_aplicacion.equals(other.getId_aplicacion()))) &&
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            ((this.url_inicio==null && other.getUrl_inicio()==null) || 
             (this.url_inicio!=null &&
              this.url_inicio.equals(other.getUrl_inicio())));
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
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        if (getEndpoint() != null) {
            _hashCode += getEndpoint().hashCode();
        }
        if (getId_aplicacion() != null) {
            _hashCode += getId_aplicacion().hashCode();
        }
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        if (getUrl_inicio() != null) {
            _hashCode += getUrl_inicio().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Aplicacion.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dto.serviciosweb.g3a.itosmosys.com", "Aplicacion"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dto.serviciosweb.g3a.itosmosys.com", "descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endpoint");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dto.serviciosweb.g3a.itosmosys.com", "endpoint"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id_aplicacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dto.serviciosweb.g3a.itosmosys.com", "id_aplicacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dto.serviciosweb.g3a.itosmosys.com", "nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("url_inicio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dto.serviciosweb.g3a.itosmosys.com", "url_inicio"));
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
