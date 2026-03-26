/**
 * Radicacion_RadicarMasivoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class Radicacion_RadicarMasivoResponse  implements java.io.Serializable {
    private java.lang.String radicacion_RadicarMasivoResult;

    public Radicacion_RadicarMasivoResponse() {
    }

    public Radicacion_RadicarMasivoResponse(
           java.lang.String radicacion_RadicarMasivoResult) {
           this.radicacion_RadicarMasivoResult = radicacion_RadicarMasivoResult;
    }


    /**
     * Gets the radicacion_RadicarMasivoResult value for this Radicacion_RadicarMasivoResponse.
     * 
     * @return radicacion_RadicarMasivoResult
     */
    public java.lang.String getRadicacion_RadicarMasivoResult() {
        return radicacion_RadicarMasivoResult;
    }


    /**
     * Sets the radicacion_RadicarMasivoResult value for this Radicacion_RadicarMasivoResponse.
     * 
     * @param radicacion_RadicarMasivoResult
     */
    public void setRadicacion_RadicarMasivoResult(java.lang.String radicacion_RadicarMasivoResult) {
        this.radicacion_RadicarMasivoResult = radicacion_RadicarMasivoResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Radicacion_RadicarMasivoResponse)) return false;
        Radicacion_RadicarMasivoResponse other = (Radicacion_RadicarMasivoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.radicacion_RadicarMasivoResult==null && other.getRadicacion_RadicarMasivoResult()==null) || 
             (this.radicacion_RadicarMasivoResult!=null &&
              this.radicacion_RadicarMasivoResult.equals(other.getRadicacion_RadicarMasivoResult())));
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
        if (getRadicacion_RadicarMasivoResult() != null) {
            _hashCode += getRadicacion_RadicarMasivoResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Radicacion_RadicarMasivoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">Radicacion_RadicarMasivoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("radicacion_RadicarMasivoResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Radicacion_RadicarMasivoResult"));
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
