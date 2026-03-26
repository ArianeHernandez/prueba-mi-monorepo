/**
 * MedioEnvio_ObtenerPorIdResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class MedioEnvio_ObtenerPorIdResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio medioEnvio_ObtenerPorIdResult;

    public MedioEnvio_ObtenerPorIdResponse() {
    }

    public MedioEnvio_ObtenerPorIdResponse(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio medioEnvio_ObtenerPorIdResult) {
           this.medioEnvio_ObtenerPorIdResult = medioEnvio_ObtenerPorIdResult;
    }


    /**
     * Gets the medioEnvio_ObtenerPorIdResult value for this MedioEnvio_ObtenerPorIdResponse.
     * 
     * @return medioEnvio_ObtenerPorIdResult
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio getMedioEnvio_ObtenerPorIdResult() {
        return medioEnvio_ObtenerPorIdResult;
    }


    /**
     * Sets the medioEnvio_ObtenerPorIdResult value for this MedioEnvio_ObtenerPorIdResponse.
     * 
     * @param medioEnvio_ObtenerPorIdResult
     */
    public void setMedioEnvio_ObtenerPorIdResult(org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio medioEnvio_ObtenerPorIdResult) {
        this.medioEnvio_ObtenerPorIdResult = medioEnvio_ObtenerPorIdResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MedioEnvio_ObtenerPorIdResponse)) return false;
        MedioEnvio_ObtenerPorIdResponse other = (MedioEnvio_ObtenerPorIdResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.medioEnvio_ObtenerPorIdResult==null && other.getMedioEnvio_ObtenerPorIdResult()==null) || 
             (this.medioEnvio_ObtenerPorIdResult!=null &&
              this.medioEnvio_ObtenerPorIdResult.equals(other.getMedioEnvio_ObtenerPorIdResult())));
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
        if (getMedioEnvio_ObtenerPorIdResult() != null) {
            _hashCode += getMedioEnvio_ObtenerPorIdResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MedioEnvio_ObtenerPorIdResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">MedioEnvio_ObtenerPorIdResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("medioEnvio_ObtenerPorIdResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "MedioEnvio_ObtenerPorIdResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "MedioDeEnvio"));
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
