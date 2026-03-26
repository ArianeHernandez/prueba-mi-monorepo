/**
 * Serie_ObtenerPorCodigoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class Serie_ObtenerPorCodigoResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOftramiteSeriesPzrEYndD serie_ObtenerPorCodigoResult;

    public Serie_ObtenerPorCodigoResponse() {
    }

    public Serie_ObtenerPorCodigoResponse(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOftramiteSeriesPzrEYndD serie_ObtenerPorCodigoResult) {
           this.serie_ObtenerPorCodigoResult = serie_ObtenerPorCodigoResult;
    }


    /**
     * Gets the serie_ObtenerPorCodigoResult value for this Serie_ObtenerPorCodigoResponse.
     * 
     * @return serie_ObtenerPorCodigoResult
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOftramiteSeriesPzrEYndD getSerie_ObtenerPorCodigoResult() {
        return serie_ObtenerPorCodigoResult;
    }


    /**
     * Sets the serie_ObtenerPorCodigoResult value for this Serie_ObtenerPorCodigoResponse.
     * 
     * @param serie_ObtenerPorCodigoResult
     */
    public void setSerie_ObtenerPorCodigoResult(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOftramiteSeriesPzrEYndD serie_ObtenerPorCodigoResult) {
        this.serie_ObtenerPorCodigoResult = serie_ObtenerPorCodigoResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Serie_ObtenerPorCodigoResponse)) return false;
        Serie_ObtenerPorCodigoResponse other = (Serie_ObtenerPorCodigoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.serie_ObtenerPorCodigoResult==null && other.getSerie_ObtenerPorCodigoResult()==null) || 
             (this.serie_ObtenerPorCodigoResult!=null &&
              this.serie_ObtenerPorCodigoResult.equals(other.getSerie_ObtenerPorCodigoResult())));
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
        if (getSerie_ObtenerPorCodigoResult() != null) {
            _hashCode += getSerie_ObtenerPorCodigoResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Serie_ObtenerPorCodigoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">Serie_ObtenerPorCodigoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serie_ObtenerPorCodigoResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Serie_ObtenerPorCodigoResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Comun", "RespuestaOftramite.SeriesPzrEYndD"));
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
