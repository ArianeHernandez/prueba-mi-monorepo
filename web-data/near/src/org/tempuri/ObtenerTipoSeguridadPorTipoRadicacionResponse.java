/**
 * ObtenerTipoSeguridadPorTipoRadicacionResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class ObtenerTipoSeguridadPorTipoRadicacionResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados.ResultadoOfDocumentoTipoSeguridadCloudUyyCEAza obtenerTipoSeguridadPorTipoRadicacionResult;

    public ObtenerTipoSeguridadPorTipoRadicacionResponse() {
    }

    public ObtenerTipoSeguridadPorTipoRadicacionResponse(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados.ResultadoOfDocumentoTipoSeguridadCloudUyyCEAza obtenerTipoSeguridadPorTipoRadicacionResult) {
           this.obtenerTipoSeguridadPorTipoRadicacionResult = obtenerTipoSeguridadPorTipoRadicacionResult;
    }


    /**
     * Gets the obtenerTipoSeguridadPorTipoRadicacionResult value for this ObtenerTipoSeguridadPorTipoRadicacionResponse.
     * 
     * @return obtenerTipoSeguridadPorTipoRadicacionResult
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados.ResultadoOfDocumentoTipoSeguridadCloudUyyCEAza getObtenerTipoSeguridadPorTipoRadicacionResult() {
        return obtenerTipoSeguridadPorTipoRadicacionResult;
    }


    /**
     * Sets the obtenerTipoSeguridadPorTipoRadicacionResult value for this ObtenerTipoSeguridadPorTipoRadicacionResponse.
     * 
     * @param obtenerTipoSeguridadPorTipoRadicacionResult
     */
    public void setObtenerTipoSeguridadPorTipoRadicacionResult(org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados.ResultadoOfDocumentoTipoSeguridadCloudUyyCEAza obtenerTipoSeguridadPorTipoRadicacionResult) {
        this.obtenerTipoSeguridadPorTipoRadicacionResult = obtenerTipoSeguridadPorTipoRadicacionResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ObtenerTipoSeguridadPorTipoRadicacionResponse)) return false;
        ObtenerTipoSeguridadPorTipoRadicacionResponse other = (ObtenerTipoSeguridadPorTipoRadicacionResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.obtenerTipoSeguridadPorTipoRadicacionResult==null && other.getObtenerTipoSeguridadPorTipoRadicacionResult()==null) || 
             (this.obtenerTipoSeguridadPorTipoRadicacionResult!=null &&
              this.obtenerTipoSeguridadPorTipoRadicacionResult.equals(other.getObtenerTipoSeguridadPorTipoRadicacionResult())));
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
        if (getObtenerTipoSeguridadPorTipoRadicacionResult() != null) {
            _hashCode += getObtenerTipoSeguridadPorTipoRadicacionResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ObtenerTipoSeguridadPorTipoRadicacionResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">ObtenerTipoSeguridadPorTipoRadicacionResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("obtenerTipoSeguridadPorTipoRadicacionResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ObtenerTipoSeguridadPorTipoRadicacionResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.SIGS.Resultados", "ResultadoOfDocumentoTipoSeguridadCloudUyyCEAza"));
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
