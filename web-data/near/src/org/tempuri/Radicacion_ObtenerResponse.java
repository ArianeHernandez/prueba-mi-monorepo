/**
 * Radicacion_ObtenerResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class Radicacion_ObtenerResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion radicacion_ObtenerResult;

    public Radicacion_ObtenerResponse() {
    }

    public Radicacion_ObtenerResponse(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion radicacion_ObtenerResult) {
           this.radicacion_ObtenerResult = radicacion_ObtenerResult;
    }


    /**
     * Gets the radicacion_ObtenerResult value for this Radicacion_ObtenerResponse.
     * 
     * @return radicacion_ObtenerResult
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion getRadicacion_ObtenerResult() {
        return radicacion_ObtenerResult;
    }


    /**
     * Sets the radicacion_ObtenerResult value for this Radicacion_ObtenerResponse.
     * 
     * @param radicacion_ObtenerResult
     */
    public void setRadicacion_ObtenerResult(org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion radicacion_ObtenerResult) {
        this.radicacion_ObtenerResult = radicacion_ObtenerResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Radicacion_ObtenerResponse)) return false;
        Radicacion_ObtenerResponse other = (Radicacion_ObtenerResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.radicacion_ObtenerResult==null && other.getRadicacion_ObtenerResult()==null) || 
             (this.radicacion_ObtenerResult!=null &&
              this.radicacion_ObtenerResult.equals(other.getRadicacion_ObtenerResult())));
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
        if (getRadicacion_ObtenerResult() != null) {
            _hashCode += getRadicacion_ObtenerResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Radicacion_ObtenerResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">Radicacion_ObtenerResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("radicacion_ObtenerResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Radicacion_ObtenerResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Radicacion"));
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
