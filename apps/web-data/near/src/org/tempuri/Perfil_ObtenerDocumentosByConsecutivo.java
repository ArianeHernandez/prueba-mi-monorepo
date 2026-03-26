/**
 * Perfil_ObtenerDocumentosByConsecutivo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class Perfil_ObtenerDocumentosByConsecutivo  implements java.io.Serializable {
    private java.lang.String numero;

    private java.lang.String consecutivo;

    public Perfil_ObtenerDocumentosByConsecutivo() {
    }

    public Perfil_ObtenerDocumentosByConsecutivo(
           java.lang.String numero,
           java.lang.String consecutivo) {
           this.numero = numero;
           this.consecutivo = consecutivo;
    }


    /**
     * Gets the numero value for this Perfil_ObtenerDocumentosByConsecutivo.
     * 
     * @return numero
     */
    public java.lang.String getNumero() {
        return numero;
    }


    /**
     * Sets the numero value for this Perfil_ObtenerDocumentosByConsecutivo.
     * 
     * @param numero
     */
    public void setNumero(java.lang.String numero) {
        this.numero = numero;
    }


    /**
     * Gets the consecutivo value for this Perfil_ObtenerDocumentosByConsecutivo.
     * 
     * @return consecutivo
     */
    public java.lang.String getConsecutivo() {
        return consecutivo;
    }


    /**
     * Sets the consecutivo value for this Perfil_ObtenerDocumentosByConsecutivo.
     * 
     * @param consecutivo
     */
    public void setConsecutivo(java.lang.String consecutivo) {
        this.consecutivo = consecutivo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Perfil_ObtenerDocumentosByConsecutivo)) return false;
        Perfil_ObtenerDocumentosByConsecutivo other = (Perfil_ObtenerDocumentosByConsecutivo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.numero==null && other.getNumero()==null) || 
             (this.numero!=null &&
              this.numero.equals(other.getNumero()))) &&
            ((this.consecutivo==null && other.getConsecutivo()==null) || 
             (this.consecutivo!=null &&
              this.consecutivo.equals(other.getConsecutivo())));
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
        if (getNumero() != null) {
            _hashCode += getNumero().hashCode();
        }
        if (getConsecutivo() != null) {
            _hashCode += getConsecutivo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Perfil_ObtenerDocumentosByConsecutivo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">Perfil_ObtenerDocumentosByConsecutivo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numero");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "numero"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("consecutivo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "consecutivo"));
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
