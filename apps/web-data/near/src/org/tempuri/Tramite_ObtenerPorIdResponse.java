/**
 * Tramite_ObtenerPorIdResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class Tramite_ObtenerPorIdResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite tramite_ObtenerPorIdResult;

    public Tramite_ObtenerPorIdResponse() {
    }

    public Tramite_ObtenerPorIdResponse(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite tramite_ObtenerPorIdResult) {
           this.tramite_ObtenerPorIdResult = tramite_ObtenerPorIdResult;
    }


    /**
     * Gets the tramite_ObtenerPorIdResult value for this Tramite_ObtenerPorIdResponse.
     * 
     * @return tramite_ObtenerPorIdResult
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite getTramite_ObtenerPorIdResult() {
        return tramite_ObtenerPorIdResult;
    }


    /**
     * Sets the tramite_ObtenerPorIdResult value for this Tramite_ObtenerPorIdResponse.
     * 
     * @param tramite_ObtenerPorIdResult
     */
    public void setTramite_ObtenerPorIdResult(org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite tramite_ObtenerPorIdResult) {
        this.tramite_ObtenerPorIdResult = tramite_ObtenerPorIdResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Tramite_ObtenerPorIdResponse)) return false;
        Tramite_ObtenerPorIdResponse other = (Tramite_ObtenerPorIdResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tramite_ObtenerPorIdResult==null && other.getTramite_ObtenerPorIdResult()==null) || 
             (this.tramite_ObtenerPorIdResult!=null &&
              this.tramite_ObtenerPorIdResult.equals(other.getTramite_ObtenerPorIdResult())));
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
        if (getTramite_ObtenerPorIdResult() != null) {
            _hashCode += getTramite_ObtenerPorIdResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Tramite_ObtenerPorIdResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">Tramite_ObtenerPorIdResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tramite_ObtenerPorIdResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Tramite_ObtenerPorIdResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Tramite"));
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
