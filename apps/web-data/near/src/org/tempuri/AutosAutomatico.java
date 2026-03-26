/**
 * AutosAutomatico.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class AutosAutomatico  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ConsultaAutosControl consultaAutosControl;

    public AutosAutomatico() {
    }

    public AutosAutomatico(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ConsultaAutosControl consultaAutosControl) {
           this.consultaAutosControl = consultaAutosControl;
    }


    /**
     * Gets the consultaAutosControl value for this AutosAutomatico.
     * 
     * @return consultaAutosControl
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ConsultaAutosControl getConsultaAutosControl() {
        return consultaAutosControl;
    }


    /**
     * Sets the consultaAutosControl value for this AutosAutomatico.
     * 
     * @param consultaAutosControl
     */
    public void setConsultaAutosControl(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ConsultaAutosControl consultaAutosControl) {
        this.consultaAutosControl = consultaAutosControl;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AutosAutomatico)) return false;
        AutosAutomatico other = (AutosAutomatico) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.consultaAutosControl==null && other.getConsultaAutosControl()==null) || 
             (this.consultaAutosControl!=null &&
              this.consultaAutosControl.equals(other.getConsultaAutosControl())));
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
        if (getConsultaAutosControl() != null) {
            _hashCode += getConsultaAutosControl().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AutosAutomatico.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">AutosAutomatico"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("consultaAutosControl");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "consultaAutosControl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "ConsultaAutosControl"));
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
