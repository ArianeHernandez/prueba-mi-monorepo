/**
 * Perfil_ObtenerDocumentosResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class Perfil_ObtenerDocumentosResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.Documento[] perfil_ObtenerDocumentosResult;

    public Perfil_ObtenerDocumentosResponse() {
    }

    public Perfil_ObtenerDocumentosResponse(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.Documento[] perfil_ObtenerDocumentosResult) {
           this.perfil_ObtenerDocumentosResult = perfil_ObtenerDocumentosResult;
    }


    /**
     * Gets the perfil_ObtenerDocumentosResult value for this Perfil_ObtenerDocumentosResponse.
     * 
     * @return perfil_ObtenerDocumentosResult
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Documento[] getPerfil_ObtenerDocumentosResult() {
        return perfil_ObtenerDocumentosResult;
    }


    /**
     * Sets the perfil_ObtenerDocumentosResult value for this Perfil_ObtenerDocumentosResponse.
     * 
     * @param perfil_ObtenerDocumentosResult
     */
    public void setPerfil_ObtenerDocumentosResult(org.datacontract.schemas._2004._07.InteropSuper_Modelos.Documento[] perfil_ObtenerDocumentosResult) {
        this.perfil_ObtenerDocumentosResult = perfil_ObtenerDocumentosResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Perfil_ObtenerDocumentosResponse)) return false;
        Perfil_ObtenerDocumentosResponse other = (Perfil_ObtenerDocumentosResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.perfil_ObtenerDocumentosResult==null && other.getPerfil_ObtenerDocumentosResult()==null) || 
             (this.perfil_ObtenerDocumentosResult!=null &&
              java.util.Arrays.equals(this.perfil_ObtenerDocumentosResult, other.getPerfil_ObtenerDocumentosResult())));
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
        if (getPerfil_ObtenerDocumentosResult() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPerfil_ObtenerDocumentosResult());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPerfil_ObtenerDocumentosResult(), i);
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
        new org.apache.axis.description.TypeDesc(Perfil_ObtenerDocumentosResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">Perfil_ObtenerDocumentosResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("perfil_ObtenerDocumentosResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Perfil_ObtenerDocumentosResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Documento"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Documento"));
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
