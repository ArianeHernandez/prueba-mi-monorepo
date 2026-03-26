/**
 * ConsultaEstadosResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class ConsultaEstadosResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos_BPM.RespuestaConsulta consultaEstadosResult;

    public ConsultaEstadosResponse() {
    }

    public ConsultaEstadosResponse(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos_BPM.RespuestaConsulta consultaEstadosResult) {
           this.consultaEstadosResult = consultaEstadosResult;
    }


    /**
     * Gets the consultaEstadosResult value for this ConsultaEstadosResponse.
     * 
     * @return consultaEstadosResult
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_BPM.RespuestaConsulta getConsultaEstadosResult() {
        return consultaEstadosResult;
    }


    /**
     * Sets the consultaEstadosResult value for this ConsultaEstadosResponse.
     * 
     * @param consultaEstadosResult
     */
    public void setConsultaEstadosResult(org.datacontract.schemas._2004._07.InteropSuper_Modelos_BPM.RespuestaConsulta consultaEstadosResult) {
        this.consultaEstadosResult = consultaEstadosResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConsultaEstadosResponse)) return false;
        ConsultaEstadosResponse other = (ConsultaEstadosResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.consultaEstadosResult==null && other.getConsultaEstadosResult()==null) || 
             (this.consultaEstadosResult!=null &&
              this.consultaEstadosResult.equals(other.getConsultaEstadosResult())));
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
        if (getConsultaEstadosResult() != null) {
            _hashCode += getConsultaEstadosResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConsultaEstadosResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">ConsultaEstadosResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("consultaEstadosResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ConsultaEstadosResult"));
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
