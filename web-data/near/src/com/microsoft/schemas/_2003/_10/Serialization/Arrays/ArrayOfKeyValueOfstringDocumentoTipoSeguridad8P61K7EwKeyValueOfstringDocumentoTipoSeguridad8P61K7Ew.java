/**
 * ArrayOfKeyValueOfstringDocumentoTipoSeguridad8P61K7EwKeyValueOfstringDocumentoTipoSeguridad8P61K7Ew.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.schemas._2003._10.Serialization.Arrays;

public class ArrayOfKeyValueOfstringDocumentoTipoSeguridad8P61K7EwKeyValueOfstringDocumentoTipoSeguridad8P61K7Ew  implements java.io.Serializable {
    private java.lang.String key;

    private com.tandem.postal.dto.DocumentoTipoSeguridad value;

    public ArrayOfKeyValueOfstringDocumentoTipoSeguridad8P61K7EwKeyValueOfstringDocumentoTipoSeguridad8P61K7Ew() {
    }

    public ArrayOfKeyValueOfstringDocumentoTipoSeguridad8P61K7EwKeyValueOfstringDocumentoTipoSeguridad8P61K7Ew(
           java.lang.String key,
           com.tandem.postal.dto.DocumentoTipoSeguridad value) {
           this.key = key;
           this.value = value;
    }


    /**
     * Gets the key value for this ArrayOfKeyValueOfstringDocumentoTipoSeguridad8P61K7EwKeyValueOfstringDocumentoTipoSeguridad8P61K7Ew.
     * 
     * @return key
     */
    public java.lang.String getKey() {
        return key;
    }


    /**
     * Sets the key value for this ArrayOfKeyValueOfstringDocumentoTipoSeguridad8P61K7EwKeyValueOfstringDocumentoTipoSeguridad8P61K7Ew.
     * 
     * @param key
     */
    public void setKey(java.lang.String key) {
        this.key = key;
    }


    /**
     * Gets the value value for this ArrayOfKeyValueOfstringDocumentoTipoSeguridad8P61K7EwKeyValueOfstringDocumentoTipoSeguridad8P61K7Ew.
     * 
     * @return value
     */
    public com.tandem.postal.dto.DocumentoTipoSeguridad getValue() {
        return value;
    }


    /**
     * Sets the value value for this ArrayOfKeyValueOfstringDocumentoTipoSeguridad8P61K7EwKeyValueOfstringDocumentoTipoSeguridad8P61K7Ew.
     * 
     * @param value
     */
    public void setValue(com.tandem.postal.dto.DocumentoTipoSeguridad value) {
        this.value = value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ArrayOfKeyValueOfstringDocumentoTipoSeguridad8P61K7EwKeyValueOfstringDocumentoTipoSeguridad8P61K7Ew)) return false;
        ArrayOfKeyValueOfstringDocumentoTipoSeguridad8P61K7EwKeyValueOfstringDocumentoTipoSeguridad8P61K7Ew other = (ArrayOfKeyValueOfstringDocumentoTipoSeguridad8P61K7EwKeyValueOfstringDocumentoTipoSeguridad8P61K7Ew) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.key==null && other.getKey()==null) || 
             (this.key!=null &&
              this.key.equals(other.getKey()))) &&
            ((this.value==null && other.getValue()==null) || 
             (this.value!=null &&
              this.value.equals(other.getValue())));
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
        if (getKey() != null) {
            _hashCode += getKey().hashCode();
        }
        if (getValue() != null) {
            _hashCode += getValue().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ArrayOfKeyValueOfstringDocumentoTipoSeguridad8P61K7EwKeyValueOfstringDocumentoTipoSeguridad8P61K7Ew.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", ">ArrayOfKeyValueOfstringDocumentoTipoSeguridad8p61K7ew>KeyValueOfstringDocumentoTipoSeguridad8p61K7ew"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("key");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "Key"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("value");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "Value"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "DocumentoTipoSeguridad"));
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
