/**
 * MedioEnvio_ObtenerTodosResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class MedioEnvio_ObtenerTodosResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio[] medioEnvio_ObtenerTodosResult;

    public MedioEnvio_ObtenerTodosResponse() {
    }

    public MedioEnvio_ObtenerTodosResponse(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio[] medioEnvio_ObtenerTodosResult) {
           this.medioEnvio_ObtenerTodosResult = medioEnvio_ObtenerTodosResult;
    }


    /**
     * Gets the medioEnvio_ObtenerTodosResult value for this MedioEnvio_ObtenerTodosResponse.
     * 
     * @return medioEnvio_ObtenerTodosResult
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio[] getMedioEnvio_ObtenerTodosResult() {
        return medioEnvio_ObtenerTodosResult;
    }


    /**
     * Sets the medioEnvio_ObtenerTodosResult value for this MedioEnvio_ObtenerTodosResponse.
     * 
     * @param medioEnvio_ObtenerTodosResult
     */
    public void setMedioEnvio_ObtenerTodosResult(org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio[] medioEnvio_ObtenerTodosResult) {
        this.medioEnvio_ObtenerTodosResult = medioEnvio_ObtenerTodosResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MedioEnvio_ObtenerTodosResponse)) return false;
        MedioEnvio_ObtenerTodosResponse other = (MedioEnvio_ObtenerTodosResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.medioEnvio_ObtenerTodosResult==null && other.getMedioEnvio_ObtenerTodosResult()==null) || 
             (this.medioEnvio_ObtenerTodosResult!=null &&
              java.util.Arrays.equals(this.medioEnvio_ObtenerTodosResult, other.getMedioEnvio_ObtenerTodosResult())));
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
        if (getMedioEnvio_ObtenerTodosResult() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMedioEnvio_ObtenerTodosResult());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMedioEnvio_ObtenerTodosResult(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MedioEnvio_ObtenerTodosResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">MedioEnvio_ObtenerTodosResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("medioEnvio_ObtenerTodosResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "MedioEnvio_ObtenerTodosResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "MedioDeEnvio"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "MedioDeEnvio"));
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
