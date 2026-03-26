/**
 * ConsultaTraslados.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class ConsultaTraslados  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ConsultaTrasladosInput consultaTrasladosInput;

    public ConsultaTraslados() {
    }

    public ConsultaTraslados(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ConsultaTrasladosInput consultaTrasladosInput) {
           this.consultaTrasladosInput = consultaTrasladosInput;
    }


    /**
     * Gets the consultaTrasladosInput value for this ConsultaTraslados.
     * 
     * @return consultaTrasladosInput
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ConsultaTrasladosInput getConsultaTrasladosInput() {
        return consultaTrasladosInput;
    }


    /**
     * Sets the consultaTrasladosInput value for this ConsultaTraslados.
     * 
     * @param consultaTrasladosInput
     */
    public void setConsultaTrasladosInput(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ConsultaTrasladosInput consultaTrasladosInput) {
        this.consultaTrasladosInput = consultaTrasladosInput;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConsultaTraslados)) return false;
        ConsultaTraslados other = (ConsultaTraslados) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.consultaTrasladosInput==null && other.getConsultaTrasladosInput()==null) || 
             (this.consultaTrasladosInput!=null &&
              this.consultaTrasladosInput.equals(other.getConsultaTrasladosInput())));
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
        if (getConsultaTrasladosInput() != null) {
            _hashCode += getConsultaTrasladosInput().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConsultaTraslados.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">ConsultaTraslados"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("consultaTrasladosInput");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "consultaTrasladosInput"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "ConsultaTrasladosInput"));
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
