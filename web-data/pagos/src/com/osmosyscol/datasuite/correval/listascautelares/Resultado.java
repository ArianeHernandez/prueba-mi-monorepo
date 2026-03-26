/**
 * Resultado.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.datasuite.correval.listascautelares;

public class Resultado  implements java.io.Serializable {
    private java.lang.String encontrado;

    private java.lang.String metodo;

    private java.lang.String listas;

    public Resultado() {
    }

    public Resultado(
           java.lang.String encontrado,
           java.lang.String metodo,
           java.lang.String listas) {
           this.encontrado = encontrado;
           this.metodo = metodo;
           this.listas = listas;
    }


    /**
     * Gets the encontrado value for this Resultado.
     * 
     * @return encontrado
     */
    public java.lang.String getEncontrado() {
        return encontrado;
    }


    /**
     * Sets the encontrado value for this Resultado.
     * 
     * @param encontrado
     */
    public void setEncontrado(java.lang.String encontrado) {
        this.encontrado = encontrado;
    }


    /**
     * Gets the metodo value for this Resultado.
     * 
     * @return metodo
     */
    public java.lang.String getMetodo() {
        return metodo;
    }


    /**
     * Sets the metodo value for this Resultado.
     * 
     * @param metodo
     */
    public void setMetodo(java.lang.String metodo) {
        this.metodo = metodo;
    }


    /**
     * Gets the listas value for this Resultado.
     * 
     * @return listas
     */
    public java.lang.String getListas() {
        return listas;
    }


    /**
     * Sets the listas value for this Resultado.
     * 
     * @param listas
     */
    public void setListas(java.lang.String listas) {
        this.listas = listas;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Resultado)) return false;
        Resultado other = (Resultado) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.encontrado==null && other.getEncontrado()==null) || 
             (this.encontrado!=null &&
              this.encontrado.equals(other.getEncontrado()))) &&
            ((this.metodo==null && other.getMetodo()==null) || 
             (this.metodo!=null &&
              this.metodo.equals(other.getMetodo()))) &&
            ((this.listas==null && other.getListas()==null) || 
             (this.listas!=null &&
              this.listas.equals(other.getListas())));
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
        if (getEncontrado() != null) {
            _hashCode += getEncontrado().hashCode();
        }
        if (getMetodo() != null) {
            _hashCode += getMetodo().hashCode();
        }
        if (getListas() != null) {
            _hashCode += getListas().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Resultado.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://190.145.62.66/soap/ApplicationServices", "Resultado"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("encontrado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Encontrado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("metodo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Metodo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Listas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
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
