/**
 * TipoSeguridad_ObtenerTodosResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class TipoSeguridad_ObtenerTodosResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad[] tipoSeguridad_ObtenerTodosResult;

    public TipoSeguridad_ObtenerTodosResponse() {
    }

    public TipoSeguridad_ObtenerTodosResponse(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad[] tipoSeguridad_ObtenerTodosResult) {
           this.tipoSeguridad_ObtenerTodosResult = tipoSeguridad_ObtenerTodosResult;
    }


    /**
     * Gets the tipoSeguridad_ObtenerTodosResult value for this TipoSeguridad_ObtenerTodosResponse.
     * 
     * @return tipoSeguridad_ObtenerTodosResult
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad[] getTipoSeguridad_ObtenerTodosResult() {
        return tipoSeguridad_ObtenerTodosResult;
    }


    /**
     * Sets the tipoSeguridad_ObtenerTodosResult value for this TipoSeguridad_ObtenerTodosResponse.
     * 
     * @param tipoSeguridad_ObtenerTodosResult
     */
    public void setTipoSeguridad_ObtenerTodosResult(org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad[] tipoSeguridad_ObtenerTodosResult) {
        this.tipoSeguridad_ObtenerTodosResult = tipoSeguridad_ObtenerTodosResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoSeguridad_ObtenerTodosResponse)) return false;
        TipoSeguridad_ObtenerTodosResponse other = (TipoSeguridad_ObtenerTodosResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tipoSeguridad_ObtenerTodosResult==null && other.getTipoSeguridad_ObtenerTodosResult()==null) || 
             (this.tipoSeguridad_ObtenerTodosResult!=null &&
              java.util.Arrays.equals(this.tipoSeguridad_ObtenerTodosResult, other.getTipoSeguridad_ObtenerTodosResult())));
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
        if (getTipoSeguridad_ObtenerTodosResult() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTipoSeguridad_ObtenerTodosResult());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTipoSeguridad_ObtenerTodosResult(), i);
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
        new org.apache.axis.description.TypeDesc(TipoSeguridad_ObtenerTodosResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">TipoSeguridad_ObtenerTodosResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoSeguridad_ObtenerTodosResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TipoSeguridad_ObtenerTodosResult"));
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
