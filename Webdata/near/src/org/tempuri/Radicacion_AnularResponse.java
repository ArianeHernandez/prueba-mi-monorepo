/**
 * Radicacion_AnularResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class Radicacion_AnularResponse  implements java.io.Serializable {
    private java.lang.String radicacion_AnularResult;

    public Radicacion_AnularResponse() {
    }

    public Radicacion_AnularResponse(
           java.lang.String radicacion_AnularResult) {
           this.radicacion_AnularResult = radicacion_AnularResult;
    }


    /**
     * Gets the radicacion_AnularResult value for this Radicacion_AnularResponse.
     * 
     * @return radicacion_AnularResult
     */
    public java.lang.String getRadicacion_AnularResult() {
        return radicacion_AnularResult;
    }


    /**
     * Sets the radicacion_AnularResult value for this Radicacion_AnularResponse.
     * 
     * @param radicacion_AnularResult
     */
    public void setRadicacion_AnularResult(java.lang.String radicacion_AnularResult) {
        this.radicacion_AnularResult = radicacion_AnularResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Radicacion_AnularResponse)) return false;
        Radicacion_AnularResponse other = (Radicacion_AnularResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.radicacion_AnularResult==null && other.getRadicacion_AnularResult()==null) || 
             (this.radicacion_AnularResult!=null &&
              this.radicacion_AnularResult.equals(other.getRadicacion_AnularResult())));
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
        if (getRadicacion_AnularResult() != null) {
            _hashCode += getRadicacion_AnularResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Radicacion_AnularResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">Radicacion_AnularResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("radicacion_AnularResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Radicacion_AnularResult"));
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
