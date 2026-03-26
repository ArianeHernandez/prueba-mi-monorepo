/**
 * Archivo_BajarV2Response.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class Archivo_BajarV2Response  implements java.io.Serializable {
    private byte[] archivo_BajarV2Result;

    public Archivo_BajarV2Response() {
    }

    public Archivo_BajarV2Response(
           byte[] archivo_BajarV2Result) {
           this.archivo_BajarV2Result = archivo_BajarV2Result;
    }


    /**
     * Gets the archivo_BajarV2Result value for this Archivo_BajarV2Response.
     * 
     * @return archivo_BajarV2Result
     */
    public byte[] getArchivo_BajarV2Result() {
        return archivo_BajarV2Result;
    }


    /**
     * Sets the archivo_BajarV2Result value for this Archivo_BajarV2Response.
     * 
     * @param archivo_BajarV2Result
     */
    public void setArchivo_BajarV2Result(byte[] archivo_BajarV2Result) {
        this.archivo_BajarV2Result = archivo_BajarV2Result;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Archivo_BajarV2Response)) return false;
        Archivo_BajarV2Response other = (Archivo_BajarV2Response) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.archivo_BajarV2Result==null && other.getArchivo_BajarV2Result()==null) || 
             (this.archivo_BajarV2Result!=null &&
              java.util.Arrays.equals(this.archivo_BajarV2Result, other.getArchivo_BajarV2Result())));
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
        if (getArchivo_BajarV2Result() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getArchivo_BajarV2Result());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getArchivo_BajarV2Result(), i);
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
        new org.apache.axis.description.TypeDesc(Archivo_BajarV2Response.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">Archivo_BajarV2Response"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("archivo_BajarV2Result");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Archivo_BajarV2Result"));
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
