/**
 * TipoSeguridad_ObtenerHabilitadosResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class TipoSeguridad_ObtenerHabilitadosResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad[] tipoSeguridad_ObtenerHabilitadosResult;

    public TipoSeguridad_ObtenerHabilitadosResponse() {
    }

    public TipoSeguridad_ObtenerHabilitadosResponse(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad[] tipoSeguridad_ObtenerHabilitadosResult) {
           this.tipoSeguridad_ObtenerHabilitadosResult = tipoSeguridad_ObtenerHabilitadosResult;
    }


    /**
     * Gets the tipoSeguridad_ObtenerHabilitadosResult value for this TipoSeguridad_ObtenerHabilitadosResponse.
     * 
     * @return tipoSeguridad_ObtenerHabilitadosResult
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad[] getTipoSeguridad_ObtenerHabilitadosResult() {
        return tipoSeguridad_ObtenerHabilitadosResult;
    }


    /**
     * Sets the tipoSeguridad_ObtenerHabilitadosResult value for this TipoSeguridad_ObtenerHabilitadosResponse.
     * 
     * @param tipoSeguridad_ObtenerHabilitadosResult
     */
    public void setTipoSeguridad_ObtenerHabilitadosResult(org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad[] tipoSeguridad_ObtenerHabilitadosResult) {
        this.tipoSeguridad_ObtenerHabilitadosResult = tipoSeguridad_ObtenerHabilitadosResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoSeguridad_ObtenerHabilitadosResponse)) return false;
        TipoSeguridad_ObtenerHabilitadosResponse other = (TipoSeguridad_ObtenerHabilitadosResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tipoSeguridad_ObtenerHabilitadosResult==null && other.getTipoSeguridad_ObtenerHabilitadosResult()==null) || 
             (this.tipoSeguridad_ObtenerHabilitadosResult!=null &&
              java.util.Arrays.equals(this.tipoSeguridad_ObtenerHabilitadosResult, other.getTipoSeguridad_ObtenerHabilitadosResult())));
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
        if (getTipoSeguridad_ObtenerHabilitadosResult() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTipoSeguridad_ObtenerHabilitadosResult());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTipoSeguridad_ObtenerHabilitadosResult(), i);
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
        new org.apache.axis.description.TypeDesc(TipoSeguridad_ObtenerHabilitadosResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">TipoSeguridad_ObtenerHabilitadosResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoSeguridad_ObtenerHabilitadosResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TipoSeguridad_ObtenerHabilitadosResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "DocumentoTipoSeguridad"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "DocumentoTipoSeguridad"));
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
