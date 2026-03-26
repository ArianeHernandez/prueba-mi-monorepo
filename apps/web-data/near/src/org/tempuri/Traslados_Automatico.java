/**
 * Traslados_Automatico.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class Traslados_Automatico  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.TrasladosAutomatico_Input trasladosAutomatico_Input;

    public Traslados_Automatico() {
    }

    public Traslados_Automatico(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.TrasladosAutomatico_Input trasladosAutomatico_Input) {
           this.trasladosAutomatico_Input = trasladosAutomatico_Input;
    }


    /**
     * Gets the trasladosAutomatico_Input value for this Traslados_Automatico.
     * 
     * @return trasladosAutomatico_Input
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.TrasladosAutomatico_Input getTrasladosAutomatico_Input() {
        return trasladosAutomatico_Input;
    }


    /**
     * Sets the trasladosAutomatico_Input value for this Traslados_Automatico.
     * 
     * @param trasladosAutomatico_Input
     */
    public void setTrasladosAutomatico_Input(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.TrasladosAutomatico_Input trasladosAutomatico_Input) {
        this.trasladosAutomatico_Input = trasladosAutomatico_Input;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Traslados_Automatico)) return false;
        Traslados_Automatico other = (Traslados_Automatico) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.trasladosAutomatico_Input==null && other.getTrasladosAutomatico_Input()==null) || 
             (this.trasladosAutomatico_Input!=null &&
              this.trasladosAutomatico_Input.equals(other.getTrasladosAutomatico_Input())));
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
        if (getTrasladosAutomatico_Input() != null) {
            _hashCode += getTrasladosAutomatico_Input().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Traslados_Automatico.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">Traslados_Automatico"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trasladosAutomatico_Input");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "trasladosAutomatico_Input"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "TrasladosAutomatico_Input"));
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
