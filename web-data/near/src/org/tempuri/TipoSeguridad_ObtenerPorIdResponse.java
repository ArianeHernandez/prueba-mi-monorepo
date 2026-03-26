/**
 * TipoSeguridad_ObtenerPorIdResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class TipoSeguridad_ObtenerPorIdResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad tipoSeguridad_ObtenerPorIdResult;

    public TipoSeguridad_ObtenerPorIdResponse() {
    }

    public TipoSeguridad_ObtenerPorIdResponse(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad tipoSeguridad_ObtenerPorIdResult) {
           this.tipoSeguridad_ObtenerPorIdResult = tipoSeguridad_ObtenerPorIdResult;
    }


    /**
     * Gets the tipoSeguridad_ObtenerPorIdResult value for this TipoSeguridad_ObtenerPorIdResponse.
     * 
     * @return tipoSeguridad_ObtenerPorIdResult
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad getTipoSeguridad_ObtenerPorIdResult() {
        return tipoSeguridad_ObtenerPorIdResult;
    }


    /**
     * Sets the tipoSeguridad_ObtenerPorIdResult value for this TipoSeguridad_ObtenerPorIdResponse.
     * 
     * @param tipoSeguridad_ObtenerPorIdResult
     */
    public void setTipoSeguridad_ObtenerPorIdResult(org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad tipoSeguridad_ObtenerPorIdResult) {
        this.tipoSeguridad_ObtenerPorIdResult = tipoSeguridad_ObtenerPorIdResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoSeguridad_ObtenerPorIdResponse)) return false;
        TipoSeguridad_ObtenerPorIdResponse other = (TipoSeguridad_ObtenerPorIdResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tipoSeguridad_ObtenerPorIdResult==null && other.getTipoSeguridad_ObtenerPorIdResult()==null) || 
             (this.tipoSeguridad_ObtenerPorIdResult!=null &&
              this.tipoSeguridad_ObtenerPorIdResult.equals(other.getTipoSeguridad_ObtenerPorIdResult())));
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
        if (getTipoSeguridad_ObtenerPorIdResult() != null) {
            _hashCode += getTipoSeguridad_ObtenerPorIdResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoSeguridad_ObtenerPorIdResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">TipoSeguridad_ObtenerPorIdResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoSeguridad_ObtenerPorIdResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TipoSeguridad_ObtenerPorIdResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "DocumentoTipoSeguridad"));
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
