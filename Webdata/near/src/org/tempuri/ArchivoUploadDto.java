/**
 * ArchivoUploadDto.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class ArchivoUploadDto  implements java.io.Serializable {
    private byte[] archivo;

    public ArchivoUploadDto() {
    }

    public ArchivoUploadDto(
           byte[] archivo) {
           this.archivo = archivo;
    }


    /**
     * Gets the archivo value for this ArchivoUploadDto.
     * 
     * @return archivo
     */
    public byte[] getArchivo() {
        return archivo;
    }


    /**
     * Sets the archivo value for this ArchivoUploadDto.
     * 
     * @param archivo
     */
    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ArchivoUploadDto)) return false;
        ArchivoUploadDto other = (ArchivoUploadDto) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.archivo==null && other.getArchivo()==null) || 
             (this.archivo!=null &&
              java.util.Arrays.equals(this.archivo, other.getArchivo())));
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
        if (getArchivo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getArchivo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getArchivo(), i);
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
        new org.apache.axis.description.TypeDesc(ArchivoUploadDto.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">ArchivoUploadDto"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("archivo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Archivo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
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
