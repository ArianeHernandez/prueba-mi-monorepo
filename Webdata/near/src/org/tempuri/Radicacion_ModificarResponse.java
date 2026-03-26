/**
 * Radicacion_ModificarResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class Radicacion_ModificarResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion radicacion_ModificarResult;

    public Radicacion_ModificarResponse() {
    }

    public Radicacion_ModificarResponse(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion radicacion_ModificarResult) {
           this.radicacion_ModificarResult = radicacion_ModificarResult;
    }


    /**
     * Gets the radicacion_ModificarResult value for this Radicacion_ModificarResponse.
     * 
     * @return radicacion_ModificarResult
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion getRadicacion_ModificarResult() {
        return radicacion_ModificarResult;
    }


    /**
     * Sets the radicacion_ModificarResult value for this Radicacion_ModificarResponse.
     * 
     * @param radicacion_ModificarResult
     */
    public void setRadicacion_ModificarResult(org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion radicacion_ModificarResult) {
        this.radicacion_ModificarResult = radicacion_ModificarResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Radicacion_ModificarResponse)) return false;
        Radicacion_ModificarResponse other = (Radicacion_ModificarResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.radicacion_ModificarResult==null && other.getRadicacion_ModificarResult()==null) || 
             (this.radicacion_ModificarResult!=null &&
              this.radicacion_ModificarResult.equals(other.getRadicacion_ModificarResult())));
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
        if (getRadicacion_ModificarResult() != null) {
            _hashCode += getRadicacion_ModificarResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Radicacion_ModificarResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">Radicacion_ModificarResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("radicacion_ModificarResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Radicacion_ModificarResult"));
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
