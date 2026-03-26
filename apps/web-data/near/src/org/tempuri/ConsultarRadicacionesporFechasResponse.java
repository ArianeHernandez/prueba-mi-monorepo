/**
 * ConsultarRadicacionesporFechasResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class ConsultarRadicacionesporFechasResponse  implements java.io.Serializable {
    private java.lang.Boolean consultarRadicacionesporFechasResult;

    public ConsultarRadicacionesporFechasResponse() {
    }

    public ConsultarRadicacionesporFechasResponse(
           java.lang.Boolean consultarRadicacionesporFechasResult) {
           this.consultarRadicacionesporFechasResult = consultarRadicacionesporFechasResult;
    }


    /**
     * Gets the consultarRadicacionesporFechasResult value for this ConsultarRadicacionesporFechasResponse.
     * 
     * @return consultarRadicacionesporFechasResult
     */
    public java.lang.Boolean getConsultarRadicacionesporFechasResult() {
        return consultarRadicacionesporFechasResult;
    }


    /**
     * Sets the consultarRadicacionesporFechasResult value for this ConsultarRadicacionesporFechasResponse.
     * 
     * @param consultarRadicacionesporFechasResult
     */
    public void setConsultarRadicacionesporFechasResult(java.lang.Boolean consultarRadicacionesporFechasResult) {
        this.consultarRadicacionesporFechasResult = consultarRadicacionesporFechasResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConsultarRadicacionesporFechasResponse)) return false;
        ConsultarRadicacionesporFechasResponse other = (ConsultarRadicacionesporFechasResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.consultarRadicacionesporFechasResult==null && other.getConsultarRadicacionesporFechasResult()==null) || 
             (this.consultarRadicacionesporFechasResult!=null &&
              this.consultarRadicacionesporFechasResult.equals(other.getConsultarRadicacionesporFechasResult())));
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
        if (getConsultarRadicacionesporFechasResult() != null) {
            _hashCode += getConsultarRadicacionesporFechasResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConsultarRadicacionesporFechasResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">ConsultarRadicacionesporFechasResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("consultarRadicacionesporFechasResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ConsultarRadicacionesporFechasResult"));
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
