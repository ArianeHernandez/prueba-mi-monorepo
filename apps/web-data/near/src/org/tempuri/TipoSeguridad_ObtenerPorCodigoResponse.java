/**
 * TipoSeguridad_ObtenerPorCodigoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class TipoSeguridad_ObtenerPorCodigoResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad tipoSeguridad_ObtenerPorCodigoResult;

    public TipoSeguridad_ObtenerPorCodigoResponse() {
    }

    public TipoSeguridad_ObtenerPorCodigoResponse(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad tipoSeguridad_ObtenerPorCodigoResult) {
           this.tipoSeguridad_ObtenerPorCodigoResult = tipoSeguridad_ObtenerPorCodigoResult;
    }


    /**
     * Gets the tipoSeguridad_ObtenerPorCodigoResult value for this TipoSeguridad_ObtenerPorCodigoResponse.
     * 
     * @return tipoSeguridad_ObtenerPorCodigoResult
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad getTipoSeguridad_ObtenerPorCodigoResult() {
        return tipoSeguridad_ObtenerPorCodigoResult;
    }


    /**
     * Sets the tipoSeguridad_ObtenerPorCodigoResult value for this TipoSeguridad_ObtenerPorCodigoResponse.
     * 
     * @param tipoSeguridad_ObtenerPorCodigoResult
     */
    public void setTipoSeguridad_ObtenerPorCodigoResult(org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad tipoSeguridad_ObtenerPorCodigoResult) {
        this.tipoSeguridad_ObtenerPorCodigoResult = tipoSeguridad_ObtenerPorCodigoResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoSeguridad_ObtenerPorCodigoResponse)) return false;
        TipoSeguridad_ObtenerPorCodigoResponse other = (TipoSeguridad_ObtenerPorCodigoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tipoSeguridad_ObtenerPorCodigoResult==null && other.getTipoSeguridad_ObtenerPorCodigoResult()==null) || 
             (this.tipoSeguridad_ObtenerPorCodigoResult!=null &&
              this.tipoSeguridad_ObtenerPorCodigoResult.equals(other.getTipoSeguridad_ObtenerPorCodigoResult())));
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
        if (getTipoSeguridad_ObtenerPorCodigoResult() != null) {
            _hashCode += getTipoSeguridad_ObtenerPorCodigoResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoSeguridad_ObtenerPorCodigoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">TipoSeguridad_ObtenerPorCodigoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoSeguridad_ObtenerPorCodigoResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TipoSeguridad_ObtenerPorCodigoResult"));
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
