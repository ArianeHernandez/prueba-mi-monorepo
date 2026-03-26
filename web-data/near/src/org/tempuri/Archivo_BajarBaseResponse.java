/**
 * Archivo_BajarBaseResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class Archivo_BajarBaseResponse  implements java.io.Serializable {
    private java.lang.String archivo_BajarBaseResult;

    public Archivo_BajarBaseResponse() {
    }

    public Archivo_BajarBaseResponse(
           java.lang.String archivo_BajarBaseResult) {
           this.archivo_BajarBaseResult = archivo_BajarBaseResult;
    }


    /**
     * Gets the archivo_BajarBaseResult value for this Archivo_BajarBaseResponse.
     * 
     * @return archivo_BajarBaseResult
     */
    public java.lang.String getArchivo_BajarBaseResult() {
        return archivo_BajarBaseResult;
    }


    /**
     * Sets the archivo_BajarBaseResult value for this Archivo_BajarBaseResponse.
     * 
     * @param archivo_BajarBaseResult
     */
    public void setArchivo_BajarBaseResult(java.lang.String archivo_BajarBaseResult) {
        this.archivo_BajarBaseResult = archivo_BajarBaseResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Archivo_BajarBaseResponse)) return false;
        Archivo_BajarBaseResponse other = (Archivo_BajarBaseResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.archivo_BajarBaseResult==null && other.getArchivo_BajarBaseResult()==null) || 
             (this.archivo_BajarBaseResult!=null &&
              this.archivo_BajarBaseResult.equals(other.getArchivo_BajarBaseResult())));
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
        if (getArchivo_BajarBaseResult() != null) {
            _hashCode += getArchivo_BajarBaseResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Archivo_BajarBaseResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">Archivo_BajarBaseResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("archivo_BajarBaseResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Archivo_BajarBaseResult"));
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
