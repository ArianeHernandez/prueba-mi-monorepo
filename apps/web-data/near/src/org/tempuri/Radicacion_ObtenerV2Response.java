/**
 * Radicacion_ObtenerV2Response.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class Radicacion_ObtenerV2Response  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion radicacion_ObtenerV2Result;

    public Radicacion_ObtenerV2Response() {
    }

    public Radicacion_ObtenerV2Response(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion radicacion_ObtenerV2Result) {
           this.radicacion_ObtenerV2Result = radicacion_ObtenerV2Result;
    }


    /**
     * Gets the radicacion_ObtenerV2Result value for this Radicacion_ObtenerV2Response.
     * 
     * @return radicacion_ObtenerV2Result
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion getRadicacion_ObtenerV2Result() {
        return radicacion_ObtenerV2Result;
    }


    /**
     * Sets the radicacion_ObtenerV2Result value for this Radicacion_ObtenerV2Response.
     * 
     * @param radicacion_ObtenerV2Result
     */
    public void setRadicacion_ObtenerV2Result(org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion radicacion_ObtenerV2Result) {
        this.radicacion_ObtenerV2Result = radicacion_ObtenerV2Result;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Radicacion_ObtenerV2Response)) return false;
        Radicacion_ObtenerV2Response other = (Radicacion_ObtenerV2Response) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.radicacion_ObtenerV2Result==null && other.getRadicacion_ObtenerV2Result()==null) || 
             (this.radicacion_ObtenerV2Result!=null &&
              this.radicacion_ObtenerV2Result.equals(other.getRadicacion_ObtenerV2Result())));
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
        if (getRadicacion_ObtenerV2Result() != null) {
            _hashCode += getRadicacion_ObtenerV2Result().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Radicacion_ObtenerV2Response.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">Radicacion_ObtenerV2Response"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("radicacion_ObtenerV2Result");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Radicacion_ObtenerV2Result"));
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
