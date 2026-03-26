/**
 * ConsultaEstadosTrasladosResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class ConsultaEstadosTrasladosResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaConsultaEstadosTraslados consultaEstadosTrasladosResult;

    public ConsultaEstadosTrasladosResponse() {
    }

    public ConsultaEstadosTrasladosResponse(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaConsultaEstadosTraslados consultaEstadosTrasladosResult) {
           this.consultaEstadosTrasladosResult = consultaEstadosTrasladosResult;
    }


    /**
     * Gets the consultaEstadosTrasladosResult value for this ConsultaEstadosTrasladosResponse.
     * 
     * @return consultaEstadosTrasladosResult
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaConsultaEstadosTraslados getConsultaEstadosTrasladosResult() {
        return consultaEstadosTrasladosResult;
    }


    /**
     * Sets the consultaEstadosTrasladosResult value for this ConsultaEstadosTrasladosResponse.
     * 
     * @param consultaEstadosTrasladosResult
     */
    public void setConsultaEstadosTrasladosResult(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaConsultaEstadosTraslados consultaEstadosTrasladosResult) {
        this.consultaEstadosTrasladosResult = consultaEstadosTrasladosResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConsultaEstadosTrasladosResponse)) return false;
        ConsultaEstadosTrasladosResponse other = (ConsultaEstadosTrasladosResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.consultaEstadosTrasladosResult==null && other.getConsultaEstadosTrasladosResult()==null) || 
             (this.consultaEstadosTrasladosResult!=null &&
              this.consultaEstadosTrasladosResult.equals(other.getConsultaEstadosTrasladosResult())));
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
        if (getConsultaEstadosTrasladosResult() != null) {
            _hashCode += getConsultaEstadosTrasladosResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConsultaEstadosTrasladosResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">ConsultaEstadosTrasladosResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("consultaEstadosTrasladosResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ConsultaEstadosTrasladosResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "RespuestaConsultaEstadosTraslados"));
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
