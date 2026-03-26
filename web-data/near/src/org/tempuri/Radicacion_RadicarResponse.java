/**
 * Radicacion_RadicarResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class Radicacion_RadicarResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion radicacion_RadicarResult;

    public Radicacion_RadicarResponse() {
    }

    public Radicacion_RadicarResponse(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion radicacion_RadicarResult) {
           this.radicacion_RadicarResult = radicacion_RadicarResult;
    }


    /**
     * Gets the radicacion_RadicarResult value for this Radicacion_RadicarResponse.
     * 
     * @return radicacion_RadicarResult
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion getRadicacion_RadicarResult() {
        return radicacion_RadicarResult;
    }


    /**
     * Sets the radicacion_RadicarResult value for this Radicacion_RadicarResponse.
     * 
     * @param radicacion_RadicarResult
     */
    public void setRadicacion_RadicarResult(org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion radicacion_RadicarResult) {
        this.radicacion_RadicarResult = radicacion_RadicarResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Radicacion_RadicarResponse)) return false;
        Radicacion_RadicarResponse other = (Radicacion_RadicarResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.radicacion_RadicarResult==null && other.getRadicacion_RadicarResult()==null) || 
             (this.radicacion_RadicarResult!=null &&
              this.radicacion_RadicarResult.equals(other.getRadicacion_RadicarResult())));
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
        if (getRadicacion_RadicarResult() != null) {
            _hashCode += getRadicacion_RadicarResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Radicacion_RadicarResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">Radicacion_RadicarResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("radicacion_RadicarResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Radicacion_RadicarResult"));
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
