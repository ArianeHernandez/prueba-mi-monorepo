/**
 * Radicacion_Obtener_DatosBasicosResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class Radicacion_Obtener_DatosBasicosResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos_Entidades.RadicacionDatosBasicos radicacion_Obtener_DatosBasicosResult;

    public Radicacion_Obtener_DatosBasicosResponse() {
    }

    public Radicacion_Obtener_DatosBasicosResponse(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos_Entidades.RadicacionDatosBasicos radicacion_Obtener_DatosBasicosResult) {
           this.radicacion_Obtener_DatosBasicosResult = radicacion_Obtener_DatosBasicosResult;
    }


    /**
     * Gets the radicacion_Obtener_DatosBasicosResult value for this Radicacion_Obtener_DatosBasicosResponse.
     * 
     * @return radicacion_Obtener_DatosBasicosResult
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Entidades.RadicacionDatosBasicos getRadicacion_Obtener_DatosBasicosResult() {
        return radicacion_Obtener_DatosBasicosResult;
    }


    /**
     * Sets the radicacion_Obtener_DatosBasicosResult value for this Radicacion_Obtener_DatosBasicosResponse.
     * 
     * @param radicacion_Obtener_DatosBasicosResult
     */
    public void setRadicacion_Obtener_DatosBasicosResult(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Entidades.RadicacionDatosBasicos radicacion_Obtener_DatosBasicosResult) {
        this.radicacion_Obtener_DatosBasicosResult = radicacion_Obtener_DatosBasicosResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Radicacion_Obtener_DatosBasicosResponse)) return false;
        Radicacion_Obtener_DatosBasicosResponse other = (Radicacion_Obtener_DatosBasicosResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.radicacion_Obtener_DatosBasicosResult==null && other.getRadicacion_Obtener_DatosBasicosResult()==null) || 
             (this.radicacion_Obtener_DatosBasicosResult!=null &&
              this.radicacion_Obtener_DatosBasicosResult.equals(other.getRadicacion_Obtener_DatosBasicosResult())));
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
        if (getRadicacion_Obtener_DatosBasicosResult() != null) {
            _hashCode += getRadicacion_Obtener_DatosBasicosResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Radicacion_Obtener_DatosBasicosResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">Radicacion_Obtener_DatosBasicosResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("radicacion_Obtener_DatosBasicosResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Radicacion_Obtener_DatosBasicosResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Entidades", "RadicacionDatosBasicos"));
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
