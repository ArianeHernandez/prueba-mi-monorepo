/**
 * TipoDocumental_ObtenerPorEstadoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class TipoDocumental_ObtenerPorEstadoResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental[] tipoDocumental_ObtenerPorEstadoResult;

    public TipoDocumental_ObtenerPorEstadoResponse() {
    }

    public TipoDocumental_ObtenerPorEstadoResponse(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental[] tipoDocumental_ObtenerPorEstadoResult) {
           this.tipoDocumental_ObtenerPorEstadoResult = tipoDocumental_ObtenerPorEstadoResult;
    }


    /**
     * Gets the tipoDocumental_ObtenerPorEstadoResult value for this TipoDocumental_ObtenerPorEstadoResponse.
     * 
     * @return tipoDocumental_ObtenerPorEstadoResult
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental[] getTipoDocumental_ObtenerPorEstadoResult() {
        return tipoDocumental_ObtenerPorEstadoResult;
    }


    /**
     * Sets the tipoDocumental_ObtenerPorEstadoResult value for this TipoDocumental_ObtenerPorEstadoResponse.
     * 
     * @param tipoDocumental_ObtenerPorEstadoResult
     */
    public void setTipoDocumental_ObtenerPorEstadoResult(org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental[] tipoDocumental_ObtenerPorEstadoResult) {
        this.tipoDocumental_ObtenerPorEstadoResult = tipoDocumental_ObtenerPorEstadoResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoDocumental_ObtenerPorEstadoResponse)) return false;
        TipoDocumental_ObtenerPorEstadoResponse other = (TipoDocumental_ObtenerPorEstadoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tipoDocumental_ObtenerPorEstadoResult==null && other.getTipoDocumental_ObtenerPorEstadoResult()==null) || 
             (this.tipoDocumental_ObtenerPorEstadoResult!=null &&
              java.util.Arrays.equals(this.tipoDocumental_ObtenerPorEstadoResult, other.getTipoDocumental_ObtenerPorEstadoResult())));
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
        if (getTipoDocumental_ObtenerPorEstadoResult() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTipoDocumental_ObtenerPorEstadoResult());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTipoDocumental_ObtenerPorEstadoResult(), i);
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
        new org.apache.axis.description.TypeDesc(TipoDocumental_ObtenerPorEstadoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">TipoDocumental_ObtenerPorEstadoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoDocumental_ObtenerPorEstadoResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TipoDocumental_ObtenerPorEstadoResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "TipoDocumental"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "TipoDocumental"));
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
