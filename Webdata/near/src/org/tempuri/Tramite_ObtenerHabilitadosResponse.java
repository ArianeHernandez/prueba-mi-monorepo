/**
 * Tramite_ObtenerHabilitadosResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class Tramite_ObtenerHabilitadosResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite[] tramite_ObtenerHabilitadosResult;

    public Tramite_ObtenerHabilitadosResponse() {
    }

    public Tramite_ObtenerHabilitadosResponse(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite[] tramite_ObtenerHabilitadosResult) {
           this.tramite_ObtenerHabilitadosResult = tramite_ObtenerHabilitadosResult;
    }


    /**
     * Gets the tramite_ObtenerHabilitadosResult value for this Tramite_ObtenerHabilitadosResponse.
     * 
     * @return tramite_ObtenerHabilitadosResult
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite[] getTramite_ObtenerHabilitadosResult() {
        return tramite_ObtenerHabilitadosResult;
    }


    /**
     * Sets the tramite_ObtenerHabilitadosResult value for this Tramite_ObtenerHabilitadosResponse.
     * 
     * @param tramite_ObtenerHabilitadosResult
     */
    public void setTramite_ObtenerHabilitadosResult(org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite[] tramite_ObtenerHabilitadosResult) {
        this.tramite_ObtenerHabilitadosResult = tramite_ObtenerHabilitadosResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Tramite_ObtenerHabilitadosResponse)) return false;
        Tramite_ObtenerHabilitadosResponse other = (Tramite_ObtenerHabilitadosResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tramite_ObtenerHabilitadosResult==null && other.getTramite_ObtenerHabilitadosResult()==null) || 
             (this.tramite_ObtenerHabilitadosResult!=null &&
              java.util.Arrays.equals(this.tramite_ObtenerHabilitadosResult, other.getTramite_ObtenerHabilitadosResult())));
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
        if (getTramite_ObtenerHabilitadosResult() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTramite_ObtenerHabilitadosResult());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTramite_ObtenerHabilitadosResult(), i);
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
        new org.apache.axis.description.TypeDesc(Tramite_ObtenerHabilitadosResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">Tramite_ObtenerHabilitadosResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tramite_ObtenerHabilitadosResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Tramite_ObtenerHabilitadosResult"));
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
