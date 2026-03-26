/**
 * Radicacion_RadicarInterna.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class Radicacion_RadicarInterna  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.RadicacionInterna radicacionInterna;

    public Radicacion_RadicarInterna() {
    }

    public Radicacion_RadicarInterna(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.RadicacionInterna radicacionInterna) {
           this.radicacionInterna = radicacionInterna;
    }


    /**
     * Gets the radicacionInterna value for this Radicacion_RadicarInterna.
     * 
     * @return radicacionInterna
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.RadicacionInterna getRadicacionInterna() {
        return radicacionInterna;
    }


    /**
     * Sets the radicacionInterna value for this Radicacion_RadicarInterna.
     * 
     * @param radicacionInterna
     */
    public void setRadicacionInterna(org.datacontract.schemas._2004._07.InteropSuper_Modelos.RadicacionInterna radicacionInterna) {
        this.radicacionInterna = radicacionInterna;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Radicacion_RadicarInterna)) return false;
        Radicacion_RadicarInterna other = (Radicacion_RadicarInterna) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.radicacionInterna==null && other.getRadicacionInterna()==null) || 
             (this.radicacionInterna!=null &&
              this.radicacionInterna.equals(other.getRadicacionInterna())));
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
        if (getRadicacionInterna() != null) {
            _hashCode += getRadicacionInterna().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Radicacion_RadicarInterna.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">Radicacion_RadicarInterna"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("radicacionInterna");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "radicacionInterna"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "RadicacionInterna"));
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
