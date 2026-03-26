/**
 * ConsultaTrasladosResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class ConsultaTrasladosResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos_BPM.RespuestaConsulta consultaTrasladosResult;

    public ConsultaTrasladosResponse() {
    }

    public ConsultaTrasladosResponse(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos_BPM.RespuestaConsulta consultaTrasladosResult) {
           this.consultaTrasladosResult = consultaTrasladosResult;
    }


    /**
     * Gets the consultaTrasladosResult value for this ConsultaTrasladosResponse.
     * 
     * @return consultaTrasladosResult
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_BPM.RespuestaConsulta getConsultaTrasladosResult() {
        return consultaTrasladosResult;
    }


    /**
     * Sets the consultaTrasladosResult value for this ConsultaTrasladosResponse.
     * 
     * @param consultaTrasladosResult
     */
    public void setConsultaTrasladosResult(org.datacontract.schemas._2004._07.InteropSuper_Modelos_BPM.RespuestaConsulta consultaTrasladosResult) {
        this.consultaTrasladosResult = consultaTrasladosResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConsultaTrasladosResponse)) return false;
        ConsultaTrasladosResponse other = (ConsultaTrasladosResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.consultaTrasladosResult==null && other.getConsultaTrasladosResult()==null) || 
             (this.consultaTrasladosResult!=null &&
              this.consultaTrasladosResult.equals(other.getConsultaTrasladosResult())));
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
        if (getConsultaTrasladosResult() != null) {
            _hashCode += getConsultaTrasladosResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConsultaTrasladosResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">ConsultaTrasladosResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("consultaTrasladosResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ConsultaTrasladosResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.BPM", "RespuestaConsulta"));
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
