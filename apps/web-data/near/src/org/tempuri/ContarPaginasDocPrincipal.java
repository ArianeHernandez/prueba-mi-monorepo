/**
 * ContarPaginasDocPrincipal.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class ContarPaginasDocPrincipal  implements java.io.Serializable {
    private java.lang.String referencia;

    private java.lang.String nombreBiblioteca;

    public ContarPaginasDocPrincipal() {
    }

    public ContarPaginasDocPrincipal(
           java.lang.String referencia,
           java.lang.String nombreBiblioteca) {
           this.referencia = referencia;
           this.nombreBiblioteca = nombreBiblioteca;
    }


    /**
     * Gets the referencia value for this ContarPaginasDocPrincipal.
     * 
     * @return referencia
     */
    public java.lang.String getReferencia() {
        return referencia;
    }


    /**
     * Sets the referencia value for this ContarPaginasDocPrincipal.
     * 
     * @param referencia
     */
    public void setReferencia(java.lang.String referencia) {
        this.referencia = referencia;
    }


    /**
     * Gets the nombreBiblioteca value for this ContarPaginasDocPrincipal.
     * 
     * @return nombreBiblioteca
     */
    public java.lang.String getNombreBiblioteca() {
        return nombreBiblioteca;
    }


    /**
     * Sets the nombreBiblioteca value for this ContarPaginasDocPrincipal.
     * 
     * @param nombreBiblioteca
     */
    public void setNombreBiblioteca(java.lang.String nombreBiblioteca) {
        this.nombreBiblioteca = nombreBiblioteca;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ContarPaginasDocPrincipal)) return false;
        ContarPaginasDocPrincipal other = (ContarPaginasDocPrincipal) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.referencia==null && other.getReferencia()==null) || 
             (this.referencia!=null &&
              this.referencia.equals(other.getReferencia()))) &&
            ((this.nombreBiblioteca==null && other.getNombreBiblioteca()==null) || 
             (this.nombreBiblioteca!=null &&
              this.nombreBiblioteca.equals(other.getNombreBiblioteca())));
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
        if (getReferencia() != null) {
            _hashCode += getReferencia().hashCode();
        }
        if (getNombreBiblioteca() != null) {
            _hashCode += getNombreBiblioteca().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ContarPaginasDocPrincipal.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">ContarPaginasDocPrincipal"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("referencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Referencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreBiblioteca");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "NombreBiblioteca"));
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
