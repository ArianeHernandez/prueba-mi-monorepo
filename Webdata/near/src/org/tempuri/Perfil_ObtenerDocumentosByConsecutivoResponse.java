/**
 * Perfil_ObtenerDocumentosByConsecutivoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class Perfil_ObtenerDocumentosByConsecutivoResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ObtenerDocumentosByConsecutivoResult perfil_ObtenerDocumentosByConsecutivoResult;

    public Perfil_ObtenerDocumentosByConsecutivoResponse() {
    }

    public Perfil_ObtenerDocumentosByConsecutivoResponse(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ObtenerDocumentosByConsecutivoResult perfil_ObtenerDocumentosByConsecutivoResult) {
           this.perfil_ObtenerDocumentosByConsecutivoResult = perfil_ObtenerDocumentosByConsecutivoResult;
    }


    /**
     * Gets the perfil_ObtenerDocumentosByConsecutivoResult value for this Perfil_ObtenerDocumentosByConsecutivoResponse.
     * 
     * @return perfil_ObtenerDocumentosByConsecutivoResult
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ObtenerDocumentosByConsecutivoResult getPerfil_ObtenerDocumentosByConsecutivoResult() {
        return perfil_ObtenerDocumentosByConsecutivoResult;
    }


    /**
     * Sets the perfil_ObtenerDocumentosByConsecutivoResult value for this Perfil_ObtenerDocumentosByConsecutivoResponse.
     * 
     * @param perfil_ObtenerDocumentosByConsecutivoResult
     */
    public void setPerfil_ObtenerDocumentosByConsecutivoResult(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ObtenerDocumentosByConsecutivoResult perfil_ObtenerDocumentosByConsecutivoResult) {
        this.perfil_ObtenerDocumentosByConsecutivoResult = perfil_ObtenerDocumentosByConsecutivoResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Perfil_ObtenerDocumentosByConsecutivoResponse)) return false;
        Perfil_ObtenerDocumentosByConsecutivoResponse other = (Perfil_ObtenerDocumentosByConsecutivoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.perfil_ObtenerDocumentosByConsecutivoResult==null && other.getPerfil_ObtenerDocumentosByConsecutivoResult()==null) || 
             (this.perfil_ObtenerDocumentosByConsecutivoResult!=null &&
              this.perfil_ObtenerDocumentosByConsecutivoResult.equals(other.getPerfil_ObtenerDocumentosByConsecutivoResult())));
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
        if (getPerfil_ObtenerDocumentosByConsecutivoResult() != null) {
            _hashCode += getPerfil_ObtenerDocumentosByConsecutivoResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Perfil_ObtenerDocumentosByConsecutivoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">Perfil_ObtenerDocumentosByConsecutivoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("perfil_ObtenerDocumentosByConsecutivoResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Perfil_ObtenerDocumentosByConsecutivoResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "ObtenerDocumentosByConsecutivoResult"));
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
