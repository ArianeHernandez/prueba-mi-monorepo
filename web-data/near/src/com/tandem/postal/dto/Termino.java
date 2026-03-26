/**
 * Termino.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tandem.postal.dto;

public class Termino  implements java.io.Serializable {
    private java.lang.Integer dias;

    private java.lang.Boolean esModificable;

    public Termino() {
    }

    public Termino(
           java.lang.Integer dias,
           java.lang.Boolean esModificable) {
           this.dias = dias;
           this.esModificable = esModificable;
    }


    /**
     * Gets the dias value for this Termino.
     * 
     * @return dias
     */
    public java.lang.Integer getDias() {
        return dias;
    }


    /**
     * Sets the dias value for this Termino.
     * 
     * @param dias
     */
    public void setDias(java.lang.Integer dias) {
        this.dias = dias;
    }


    /**
     * Gets the esModificable value for this Termino.
     * 
     * @return esModificable
     */
    public java.lang.Boolean getEsModificable() {
        return esModificable;
    }


    /**
     * Sets the esModificable value for this Termino.
     * 
     * @param esModificable
     */
    public void setEsModificable(java.lang.Boolean esModificable) {
        this.esModificable = esModificable;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Termino)) return false;
        Termino other = (Termino) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.dias==null && other.getDias()==null) || 
             (this.dias!=null &&
              this.dias.equals(other.getDias()))) &&
            ((this.esModificable==null && other.getEsModificable()==null) || 
             (this.esModificable!=null &&
              this.esModificable.equals(other.getEsModificable())));
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
        if (getDias() != null) {
            _hashCode += getDias().hashCode();
        }
        if (getEsModificable() != null) {
            _hashCode += getEsModificable().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Termino.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "Termino"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dias");
        elemField.setXmlName(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "Dias"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esModificable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "EsModificable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
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
