/**
 * ObtenerSeriesPorCodigoTramiteResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class ObtenerSeriesPorCodigoTramiteResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfRespuestaGeneral_PHy_PfD4I obtenerSeriesPorCodigoTramiteResult;

    public ObtenerSeriesPorCodigoTramiteResponse() {
    }

    public ObtenerSeriesPorCodigoTramiteResponse(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfRespuestaGeneral_PHy_PfD4I obtenerSeriesPorCodigoTramiteResult) {
           this.obtenerSeriesPorCodigoTramiteResult = obtenerSeriesPorCodigoTramiteResult;
    }


    /**
     * Gets the obtenerSeriesPorCodigoTramiteResult value for this ObtenerSeriesPorCodigoTramiteResponse.
     * 
     * @return obtenerSeriesPorCodigoTramiteResult
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfRespuestaGeneral_PHy_PfD4I getObtenerSeriesPorCodigoTramiteResult() {
        return obtenerSeriesPorCodigoTramiteResult;
    }


    /**
     * Sets the obtenerSeriesPorCodigoTramiteResult value for this ObtenerSeriesPorCodigoTramiteResponse.
     * 
     * @param obtenerSeriesPorCodigoTramiteResult
     */
    public void setObtenerSeriesPorCodigoTramiteResult(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfRespuestaGeneral_PHy_PfD4I obtenerSeriesPorCodigoTramiteResult) {
        this.obtenerSeriesPorCodigoTramiteResult = obtenerSeriesPorCodigoTramiteResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ObtenerSeriesPorCodigoTramiteResponse)) return false;
        ObtenerSeriesPorCodigoTramiteResponse other = (ObtenerSeriesPorCodigoTramiteResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.obtenerSeriesPorCodigoTramiteResult==null && other.getObtenerSeriesPorCodigoTramiteResult()==null) || 
             (this.obtenerSeriesPorCodigoTramiteResult!=null &&
              this.obtenerSeriesPorCodigoTramiteResult.equals(other.getObtenerSeriesPorCodigoTramiteResult())));
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
        if (getObtenerSeriesPorCodigoTramiteResult() != null) {
            _hashCode += getObtenerSeriesPorCodigoTramiteResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ObtenerSeriesPorCodigoTramiteResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">ObtenerSeriesPorCodigoTramiteResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("obtenerSeriesPorCodigoTramiteResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ObtenerSeriesPorCodigoTramiteResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Comun", "RespuestaOfRespuestaGeneral_PHy_PfD4i"));
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
