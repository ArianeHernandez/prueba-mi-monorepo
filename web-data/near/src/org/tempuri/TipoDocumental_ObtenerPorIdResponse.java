/**
 * TipoDocumental_ObtenerPorIdResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class TipoDocumental_ObtenerPorIdResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental tipoDocumental_ObtenerPorIdResult;

    public TipoDocumental_ObtenerPorIdResponse() {
    }

    public TipoDocumental_ObtenerPorIdResponse(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental tipoDocumental_ObtenerPorIdResult) {
           this.tipoDocumental_ObtenerPorIdResult = tipoDocumental_ObtenerPorIdResult;
    }


    /**
     * Gets the tipoDocumental_ObtenerPorIdResult value for this TipoDocumental_ObtenerPorIdResponse.
     * 
     * @return tipoDocumental_ObtenerPorIdResult
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental getTipoDocumental_ObtenerPorIdResult() {
        return tipoDocumental_ObtenerPorIdResult;
    }


    /**
     * Sets the tipoDocumental_ObtenerPorIdResult value for this TipoDocumental_ObtenerPorIdResponse.
     * 
     * @param tipoDocumental_ObtenerPorIdResult
     */
    public void setTipoDocumental_ObtenerPorIdResult(org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental tipoDocumental_ObtenerPorIdResult) {
        this.tipoDocumental_ObtenerPorIdResult = tipoDocumental_ObtenerPorIdResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoDocumental_ObtenerPorIdResponse)) return false;
        TipoDocumental_ObtenerPorIdResponse other = (TipoDocumental_ObtenerPorIdResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tipoDocumental_ObtenerPorIdResult==null && other.getTipoDocumental_ObtenerPorIdResult()==null) || 
             (this.tipoDocumental_ObtenerPorIdResult!=null &&
              this.tipoDocumental_ObtenerPorIdResult.equals(other.getTipoDocumental_ObtenerPorIdResult())));
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
        if (getTipoDocumental_ObtenerPorIdResult() != null) {
            _hashCode += getTipoDocumental_ObtenerPorIdResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoDocumental_ObtenerPorIdResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">TipoDocumental_ObtenerPorIdResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoDocumental_ObtenerPorIdResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TipoDocumental_ObtenerPorIdResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "TipoDocumental"));
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
