/**
 * Archivo_BajarResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class Archivo_BajarResponse  implements java.io.Serializable {
    private byte[] archivo_BajarResult;

    public Archivo_BajarResponse() {
    }

    public Archivo_BajarResponse(
           byte[] archivo_BajarResult) {
           this.archivo_BajarResult = archivo_BajarResult;
    }


    /**
     * Gets the archivo_BajarResult value for this Archivo_BajarResponse.
     * 
     * @return archivo_BajarResult
     */
    public byte[] getArchivo_BajarResult() {
        return archivo_BajarResult;
    }


    /**
     * Sets the archivo_BajarResult value for this Archivo_BajarResponse.
     * 
     * @param archivo_BajarResult
     */
    public void setArchivo_BajarResult(byte[] archivo_BajarResult) {
        this.archivo_BajarResult = archivo_BajarResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Archivo_BajarResponse)) return false;
        Archivo_BajarResponse other = (Archivo_BajarResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.archivo_BajarResult==null && other.getArchivo_BajarResult()==null) || 
             (this.archivo_BajarResult!=null &&
              java.util.Arrays.equals(this.archivo_BajarResult, other.getArchivo_BajarResult())));
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
        if (getArchivo_BajarResult() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getArchivo_BajarResult());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getArchivo_BajarResult(), i);
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
        new org.apache.axis.description.TypeDesc(Archivo_BajarResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">Archivo_BajarResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("archivo_BajarResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Archivo_BajarResult"));
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
