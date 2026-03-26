/**
 * SerieActivas_ObtenerPorDependenciaResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class SerieActivas_ObtenerPorDependenciaResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOftramiteSeriesPzrEYndD serieActivas_ObtenerPorDependenciaResult;

    public SerieActivas_ObtenerPorDependenciaResponse() {
    }

    public SerieActivas_ObtenerPorDependenciaResponse(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOftramiteSeriesPzrEYndD serieActivas_ObtenerPorDependenciaResult) {
           this.serieActivas_ObtenerPorDependenciaResult = serieActivas_ObtenerPorDependenciaResult;
    }


    /**
     * Gets the serieActivas_ObtenerPorDependenciaResult value for this SerieActivas_ObtenerPorDependenciaResponse.
     * 
     * @return serieActivas_ObtenerPorDependenciaResult
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOftramiteSeriesPzrEYndD getSerieActivas_ObtenerPorDependenciaResult() {
        return serieActivas_ObtenerPorDependenciaResult;
    }


    /**
     * Sets the serieActivas_ObtenerPorDependenciaResult value for this SerieActivas_ObtenerPorDependenciaResponse.
     * 
     * @param serieActivas_ObtenerPorDependenciaResult
     */
    public void setSerieActivas_ObtenerPorDependenciaResult(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOftramiteSeriesPzrEYndD serieActivas_ObtenerPorDependenciaResult) {
        this.serieActivas_ObtenerPorDependenciaResult = serieActivas_ObtenerPorDependenciaResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SerieActivas_ObtenerPorDependenciaResponse)) return false;
        SerieActivas_ObtenerPorDependenciaResponse other = (SerieActivas_ObtenerPorDependenciaResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.serieActivas_ObtenerPorDependenciaResult==null && other.getSerieActivas_ObtenerPorDependenciaResult()==null) || 
             (this.serieActivas_ObtenerPorDependenciaResult!=null &&
              this.serieActivas_ObtenerPorDependenciaResult.equals(other.getSerieActivas_ObtenerPorDependenciaResult())));
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
        if (getSerieActivas_ObtenerPorDependenciaResult() != null) {
            _hashCode += getSerieActivas_ObtenerPorDependenciaResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SerieActivas_ObtenerPorDependenciaResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">SerieActivas_ObtenerPorDependenciaResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serieActivas_ObtenerPorDependenciaResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "SerieActivas_ObtenerPorDependenciaResult"));
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
