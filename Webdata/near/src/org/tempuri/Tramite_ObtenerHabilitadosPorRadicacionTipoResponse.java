/**
 * Tramite_ObtenerHabilitadosPorRadicacionTipoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class Tramite_ObtenerHabilitadosPorRadicacionTipoResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite[] tramite_ObtenerHabilitadosPorRadicacionTipoResult;

    public Tramite_ObtenerHabilitadosPorRadicacionTipoResponse() {
    }

    public Tramite_ObtenerHabilitadosPorRadicacionTipoResponse(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite[] tramite_ObtenerHabilitadosPorRadicacionTipoResult) {
           this.tramite_ObtenerHabilitadosPorRadicacionTipoResult = tramite_ObtenerHabilitadosPorRadicacionTipoResult;
    }


    /**
     * Gets the tramite_ObtenerHabilitadosPorRadicacionTipoResult value for this Tramite_ObtenerHabilitadosPorRadicacionTipoResponse.
     * 
     * @return tramite_ObtenerHabilitadosPorRadicacionTipoResult
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite[] getTramite_ObtenerHabilitadosPorRadicacionTipoResult() {
        return tramite_ObtenerHabilitadosPorRadicacionTipoResult;
    }


    /**
     * Sets the tramite_ObtenerHabilitadosPorRadicacionTipoResult value for this Tramite_ObtenerHabilitadosPorRadicacionTipoResponse.
     * 
     * @param tramite_ObtenerHabilitadosPorRadicacionTipoResult
     */
    public void setTramite_ObtenerHabilitadosPorRadicacionTipoResult(org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite[] tramite_ObtenerHabilitadosPorRadicacionTipoResult) {
        this.tramite_ObtenerHabilitadosPorRadicacionTipoResult = tramite_ObtenerHabilitadosPorRadicacionTipoResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Tramite_ObtenerHabilitadosPorRadicacionTipoResponse)) return false;
        Tramite_ObtenerHabilitadosPorRadicacionTipoResponse other = (Tramite_ObtenerHabilitadosPorRadicacionTipoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tramite_ObtenerHabilitadosPorRadicacionTipoResult==null && other.getTramite_ObtenerHabilitadosPorRadicacionTipoResult()==null) || 
             (this.tramite_ObtenerHabilitadosPorRadicacionTipoResult!=null &&
              java.util.Arrays.equals(this.tramite_ObtenerHabilitadosPorRadicacionTipoResult, other.getTramite_ObtenerHabilitadosPorRadicacionTipoResult())));
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
        if (getTramite_ObtenerHabilitadosPorRadicacionTipoResult() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTramite_ObtenerHabilitadosPorRadicacionTipoResult());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTramite_ObtenerHabilitadosPorRadicacionTipoResult(), i);
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
        new org.apache.axis.description.TypeDesc(Tramite_ObtenerHabilitadosPorRadicacionTipoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">Tramite_ObtenerHabilitadosPorRadicacionTipoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tramite_ObtenerHabilitadosPorRadicacionTipoResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Tramite_ObtenerHabilitadosPorRadicacionTipoResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Tramite"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Tramite"));
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
