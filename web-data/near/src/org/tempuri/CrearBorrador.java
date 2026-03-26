/**
 * CrearBorrador.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class CrearBorrador  implements java.io.Serializable {
    private com.tandem.postal.dto.Borrador borradorData;

    public CrearBorrador() {
    }

    public CrearBorrador(
           com.tandem.postal.dto.Borrador borradorData) {
           this.borradorData = borradorData;
    }


    /**
     * Gets the borradorData value for this CrearBorrador.
     * 
     * @return borradorData
     */
    public com.tandem.postal.dto.Borrador getBorradorData() {
        return borradorData;
    }


    /**
     * Sets the borradorData value for this CrearBorrador.
     * 
     * @param borradorData
     */
    public void setBorradorData(com.tandem.postal.dto.Borrador borradorData) {
        this.borradorData = borradorData;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CrearBorrador)) return false;
        CrearBorrador other = (CrearBorrador) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.borradorData==null && other.getBorradorData()==null) || 
             (this.borradorData!=null &&
              this.borradorData.equals(other.getBorradorData())));
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
        if (getBorradorData() != null) {
            _hashCode += getBorradorData().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CrearBorrador.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">CrearBorrador"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("borradorData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "borradorData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "Borrador"));
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
