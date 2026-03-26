/**
 * ConsultarRadicacionesResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class ConsultarRadicacionesResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados.ResultadoOfPROFILEOyDEkVga consultarRadicacionesResult;

    public ConsultarRadicacionesResponse() {
    }

    public ConsultarRadicacionesResponse(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados.ResultadoOfPROFILEOyDEkVga consultarRadicacionesResult) {
           this.consultarRadicacionesResult = consultarRadicacionesResult;
    }


    /**
     * Gets the consultarRadicacionesResult value for this ConsultarRadicacionesResponse.
     * 
     * @return consultarRadicacionesResult
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados.ResultadoOfPROFILEOyDEkVga getConsultarRadicacionesResult() {
        return consultarRadicacionesResult;
    }


    /**
     * Sets the consultarRadicacionesResult value for this ConsultarRadicacionesResponse.
     * 
     * @param consultarRadicacionesResult
     */
    public void setConsultarRadicacionesResult(org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados.ResultadoOfPROFILEOyDEkVga consultarRadicacionesResult) {
        this.consultarRadicacionesResult = consultarRadicacionesResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConsultarRadicacionesResponse)) return false;
        ConsultarRadicacionesResponse other = (ConsultarRadicacionesResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.consultarRadicacionesResult==null && other.getConsultarRadicacionesResult()==null) || 
             (this.consultarRadicacionesResult!=null &&
              this.consultarRadicacionesResult.equals(other.getConsultarRadicacionesResult())));
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
        if (getConsultarRadicacionesResult() != null) {
            _hashCode += getConsultarRadicacionesResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConsultarRadicacionesResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">ConsultarRadicacionesResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("consultarRadicacionesResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ConsultarRadicacionesResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.SIGS.Resultados", "ResultadoOfPROFILEOyDEkVga"));
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
