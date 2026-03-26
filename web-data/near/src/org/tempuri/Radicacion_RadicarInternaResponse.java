/**
 * Radicacion_RadicarInternaResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class Radicacion_RadicarInternaResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaRadicacionInterna radicacion_RadicarInternaResult;

    public Radicacion_RadicarInternaResponse() {
    }

    public Radicacion_RadicarInternaResponse(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaRadicacionInterna radicacion_RadicarInternaResult) {
           this.radicacion_RadicarInternaResult = radicacion_RadicarInternaResult;
    }


    /**
     * Gets the radicacion_RadicarInternaResult value for this Radicacion_RadicarInternaResponse.
     * 
     * @return radicacion_RadicarInternaResult
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaRadicacionInterna getRadicacion_RadicarInternaResult() {
        return radicacion_RadicarInternaResult;
    }


    /**
     * Sets the radicacion_RadicarInternaResult value for this Radicacion_RadicarInternaResponse.
     * 
     * @param radicacion_RadicarInternaResult
     */
    public void setRadicacion_RadicarInternaResult(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaRadicacionInterna radicacion_RadicarInternaResult) {
        this.radicacion_RadicarInternaResult = radicacion_RadicarInternaResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Radicacion_RadicarInternaResponse)) return false;
        Radicacion_RadicarInternaResponse other = (Radicacion_RadicarInternaResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.radicacion_RadicarInternaResult==null && other.getRadicacion_RadicarInternaResult()==null) || 
             (this.radicacion_RadicarInternaResult!=null &&
              this.radicacion_RadicarInternaResult.equals(other.getRadicacion_RadicarInternaResult())));
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
        if (getRadicacion_RadicarInternaResult() != null) {
            _hashCode += getRadicacion_RadicarInternaResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Radicacion_RadicarInternaResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">Radicacion_RadicarInternaResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("radicacion_RadicarInternaResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Radicacion_RadicarInternaResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "RespuestaRadicacionInterna"));
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
