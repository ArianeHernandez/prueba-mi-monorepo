/**
 * MedioEnvio_ObtenerPorCodigoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class MedioEnvio_ObtenerPorCodigoResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio medioEnvio_ObtenerPorCodigoResult;

    public MedioEnvio_ObtenerPorCodigoResponse() {
    }

    public MedioEnvio_ObtenerPorCodigoResponse(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio medioEnvio_ObtenerPorCodigoResult) {
           this.medioEnvio_ObtenerPorCodigoResult = medioEnvio_ObtenerPorCodigoResult;
    }


    /**
     * Gets the medioEnvio_ObtenerPorCodigoResult value for this MedioEnvio_ObtenerPorCodigoResponse.
     * 
     * @return medioEnvio_ObtenerPorCodigoResult
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio getMedioEnvio_ObtenerPorCodigoResult() {
        return medioEnvio_ObtenerPorCodigoResult;
    }


    /**
     * Sets the medioEnvio_ObtenerPorCodigoResult value for this MedioEnvio_ObtenerPorCodigoResponse.
     * 
     * @param medioEnvio_ObtenerPorCodigoResult
     */
    public void setMedioEnvio_ObtenerPorCodigoResult(org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio medioEnvio_ObtenerPorCodigoResult) {
        this.medioEnvio_ObtenerPorCodigoResult = medioEnvio_ObtenerPorCodigoResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MedioEnvio_ObtenerPorCodigoResponse)) return false;
        MedioEnvio_ObtenerPorCodigoResponse other = (MedioEnvio_ObtenerPorCodigoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.medioEnvio_ObtenerPorCodigoResult==null && other.getMedioEnvio_ObtenerPorCodigoResult()==null) || 
             (this.medioEnvio_ObtenerPorCodigoResult!=null &&
              this.medioEnvio_ObtenerPorCodigoResult.equals(other.getMedioEnvio_ObtenerPorCodigoResult())));
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
        if (getMedioEnvio_ObtenerPorCodigoResult() != null) {
            _hashCode += getMedioEnvio_ObtenerPorCodigoResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MedioEnvio_ObtenerPorCodigoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">MedioEnvio_ObtenerPorCodigoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("medioEnvio_ObtenerPorCodigoResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "MedioEnvio_ObtenerPorCodigoResult"));
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
