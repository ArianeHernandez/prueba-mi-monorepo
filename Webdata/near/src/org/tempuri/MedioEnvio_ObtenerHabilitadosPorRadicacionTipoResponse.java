/**
 * MedioEnvio_ObtenerHabilitadosPorRadicacionTipoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class MedioEnvio_ObtenerHabilitadosPorRadicacionTipoResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio[] medioEnvio_ObtenerHabilitadosPorRadicacionTipoResult;

    public MedioEnvio_ObtenerHabilitadosPorRadicacionTipoResponse() {
    }

    public MedioEnvio_ObtenerHabilitadosPorRadicacionTipoResponse(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio[] medioEnvio_ObtenerHabilitadosPorRadicacionTipoResult) {
           this.medioEnvio_ObtenerHabilitadosPorRadicacionTipoResult = medioEnvio_ObtenerHabilitadosPorRadicacionTipoResult;
    }


    /**
     * Gets the medioEnvio_ObtenerHabilitadosPorRadicacionTipoResult value for this MedioEnvio_ObtenerHabilitadosPorRadicacionTipoResponse.
     * 
     * @return medioEnvio_ObtenerHabilitadosPorRadicacionTipoResult
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio[] getMedioEnvio_ObtenerHabilitadosPorRadicacionTipoResult() {
        return medioEnvio_ObtenerHabilitadosPorRadicacionTipoResult;
    }


    /**
     * Sets the medioEnvio_ObtenerHabilitadosPorRadicacionTipoResult value for this MedioEnvio_ObtenerHabilitadosPorRadicacionTipoResponse.
     * 
     * @param medioEnvio_ObtenerHabilitadosPorRadicacionTipoResult
     */
    public void setMedioEnvio_ObtenerHabilitadosPorRadicacionTipoResult(org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio[] medioEnvio_ObtenerHabilitadosPorRadicacionTipoResult) {
        this.medioEnvio_ObtenerHabilitadosPorRadicacionTipoResult = medioEnvio_ObtenerHabilitadosPorRadicacionTipoResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MedioEnvio_ObtenerHabilitadosPorRadicacionTipoResponse)) return false;
        MedioEnvio_ObtenerHabilitadosPorRadicacionTipoResponse other = (MedioEnvio_ObtenerHabilitadosPorRadicacionTipoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.medioEnvio_ObtenerHabilitadosPorRadicacionTipoResult==null && other.getMedioEnvio_ObtenerHabilitadosPorRadicacionTipoResult()==null) || 
             (this.medioEnvio_ObtenerHabilitadosPorRadicacionTipoResult!=null &&
              java.util.Arrays.equals(this.medioEnvio_ObtenerHabilitadosPorRadicacionTipoResult, other.getMedioEnvio_ObtenerHabilitadosPorRadicacionTipoResult())));
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
        if (getMedioEnvio_ObtenerHabilitadosPorRadicacionTipoResult() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMedioEnvio_ObtenerHabilitadosPorRadicacionTipoResult());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMedioEnvio_ObtenerHabilitadosPorRadicacionTipoResult(), i);
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
        new org.apache.axis.description.TypeDesc(MedioEnvio_ObtenerHabilitadosPorRadicacionTipoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">MedioEnvio_ObtenerHabilitadosPorRadicacionTipoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("medioEnvio_ObtenerHabilitadosPorRadicacionTipoResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "MedioEnvio_ObtenerHabilitadosPorRadicacionTipoResult"));
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
