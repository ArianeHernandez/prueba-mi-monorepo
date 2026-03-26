/**
 * EnrutarRadicacion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class EnrutarRadicacion  implements java.io.Serializable {
    private java.lang.String radicacionNumero;

    private java.lang.String usuarioEnrutaCodigo;

    private java.lang.Long dependenciaDestinoCodigo;

    private java.lang.String usuarioDestinoCodigo;

    private java.lang.String asunto;

    public EnrutarRadicacion() {
    }

    public EnrutarRadicacion(
           java.lang.String radicacionNumero,
           java.lang.String usuarioEnrutaCodigo,
           java.lang.Long dependenciaDestinoCodigo,
           java.lang.String usuarioDestinoCodigo,
           java.lang.String asunto) {
           this.radicacionNumero = radicacionNumero;
           this.usuarioEnrutaCodigo = usuarioEnrutaCodigo;
           this.dependenciaDestinoCodigo = dependenciaDestinoCodigo;
           this.usuarioDestinoCodigo = usuarioDestinoCodigo;
           this.asunto = asunto;
    }


    /**
     * Gets the radicacionNumero value for this EnrutarRadicacion.
     * 
     * @return radicacionNumero
     */
    public java.lang.String getRadicacionNumero() {
        return radicacionNumero;
    }


    /**
     * Sets the radicacionNumero value for this EnrutarRadicacion.
     * 
     * @param radicacionNumero
     */
    public void setRadicacionNumero(java.lang.String radicacionNumero) {
        this.radicacionNumero = radicacionNumero;
    }


    /**
     * Gets the usuarioEnrutaCodigo value for this EnrutarRadicacion.
     * 
     * @return usuarioEnrutaCodigo
     */
    public java.lang.String getUsuarioEnrutaCodigo() {
        return usuarioEnrutaCodigo;
    }


    /**
     * Sets the usuarioEnrutaCodigo value for this EnrutarRadicacion.
     * 
     * @param usuarioEnrutaCodigo
     */
    public void setUsuarioEnrutaCodigo(java.lang.String usuarioEnrutaCodigo) {
        this.usuarioEnrutaCodigo = usuarioEnrutaCodigo;
    }


    /**
     * Gets the dependenciaDestinoCodigo value for this EnrutarRadicacion.
     * 
     * @return dependenciaDestinoCodigo
     */
    public java.lang.Long getDependenciaDestinoCodigo() {
        return dependenciaDestinoCodigo;
    }


    /**
     * Sets the dependenciaDestinoCodigo value for this EnrutarRadicacion.
     * 
     * @param dependenciaDestinoCodigo
     */
    public void setDependenciaDestinoCodigo(java.lang.Long dependenciaDestinoCodigo) {
        this.dependenciaDestinoCodigo = dependenciaDestinoCodigo;
    }


    /**
     * Gets the usuarioDestinoCodigo value for this EnrutarRadicacion.
     * 
     * @return usuarioDestinoCodigo
     */
    public java.lang.String getUsuarioDestinoCodigo() {
        return usuarioDestinoCodigo;
    }


    /**
     * Sets the usuarioDestinoCodigo value for this EnrutarRadicacion.
     * 
     * @param usuarioDestinoCodigo
     */
    public void setUsuarioDestinoCodigo(java.lang.String usuarioDestinoCodigo) {
        this.usuarioDestinoCodigo = usuarioDestinoCodigo;
    }


    /**
     * Gets the asunto value for this EnrutarRadicacion.
     * 
     * @return asunto
     */
    public java.lang.String getAsunto() {
        return asunto;
    }


    /**
     * Sets the asunto value for this EnrutarRadicacion.
     * 
     * @param asunto
     */
    public void setAsunto(java.lang.String asunto) {
        this.asunto = asunto;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EnrutarRadicacion)) return false;
        EnrutarRadicacion other = (EnrutarRadicacion) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.radicacionNumero==null && other.getRadicacionNumero()==null) || 
             (this.radicacionNumero!=null &&
              this.radicacionNumero.equals(other.getRadicacionNumero()))) &&
            ((this.usuarioEnrutaCodigo==null && other.getUsuarioEnrutaCodigo()==null) || 
             (this.usuarioEnrutaCodigo!=null &&
              this.usuarioEnrutaCodigo.equals(other.getUsuarioEnrutaCodigo()))) &&
            ((this.dependenciaDestinoCodigo==null && other.getDependenciaDestinoCodigo()==null) || 
             (this.dependenciaDestinoCodigo!=null &&
              this.dependenciaDestinoCodigo.equals(other.getDependenciaDestinoCodigo()))) &&
            ((this.usuarioDestinoCodigo==null && other.getUsuarioDestinoCodigo()==null) || 
             (this.usuarioDestinoCodigo!=null &&
              this.usuarioDestinoCodigo.equals(other.getUsuarioDestinoCodigo()))) &&
            ((this.asunto==null && other.getAsunto()==null) || 
             (this.asunto!=null &&
              this.asunto.equals(other.getAsunto())));
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
        if (getRadicacionNumero() != null) {
            _hashCode += getRadicacionNumero().hashCode();
        }
        if (getUsuarioEnrutaCodigo() != null) {
            _hashCode += getUsuarioEnrutaCodigo().hashCode();
        }
        if (getDependenciaDestinoCodigo() != null) {
            _hashCode += getDependenciaDestinoCodigo().hashCode();
        }
        if (getUsuarioDestinoCodigo() != null) {
            _hashCode += getUsuarioDestinoCodigo().hashCode();
        }
        if (getAsunto() != null) {
            _hashCode += getAsunto().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EnrutarRadicacion.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">EnrutarRadicacion"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("radicacionNumero");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "radicacionNumero"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("usuarioEnrutaCodigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "usuarioEnrutaCodigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dependenciaDestinoCodigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "dependenciaDestinoCodigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("usuarioDestinoCodigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "usuarioDestinoCodigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("asunto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "asunto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
