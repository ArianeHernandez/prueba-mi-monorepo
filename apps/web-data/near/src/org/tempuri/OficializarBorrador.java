/**
 * OficializarBorrador.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class OficializarBorrador  implements java.io.Serializable {
    private java.lang.String numeroBorrador;

    private java.lang.String funcionarioFirmaCerificaciones;

    public OficializarBorrador() {
    }

    public OficializarBorrador(
           java.lang.String numeroBorrador,
           java.lang.String funcionarioFirmaCerificaciones) {
           this.numeroBorrador = numeroBorrador;
           this.funcionarioFirmaCerificaciones = funcionarioFirmaCerificaciones;
    }


    /**
     * Gets the numeroBorrador value for this OficializarBorrador.
     * 
     * @return numeroBorrador
     */
    public java.lang.String getNumeroBorrador() {
        return numeroBorrador;
    }


    /**
     * Sets the numeroBorrador value for this OficializarBorrador.
     * 
     * @param numeroBorrador
     */
    public void setNumeroBorrador(java.lang.String numeroBorrador) {
        this.numeroBorrador = numeroBorrador;
    }


    /**
     * Gets the funcionarioFirmaCerificaciones value for this OficializarBorrador.
     * 
     * @return funcionarioFirmaCerificaciones
     */
    public java.lang.String getFuncionarioFirmaCerificaciones() {
        return funcionarioFirmaCerificaciones;
    }


    /**
     * Sets the funcionarioFirmaCerificaciones value for this OficializarBorrador.
     * 
     * @param funcionarioFirmaCerificaciones
     */
    public void setFuncionarioFirmaCerificaciones(java.lang.String funcionarioFirmaCerificaciones) {
        this.funcionarioFirmaCerificaciones = funcionarioFirmaCerificaciones;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OficializarBorrador)) return false;
        OficializarBorrador other = (OficializarBorrador) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.numeroBorrador==null && other.getNumeroBorrador()==null) || 
             (this.numeroBorrador!=null &&
              this.numeroBorrador.equals(other.getNumeroBorrador()))) &&
            ((this.funcionarioFirmaCerificaciones==null && other.getFuncionarioFirmaCerificaciones()==null) || 
             (this.funcionarioFirmaCerificaciones!=null &&
              this.funcionarioFirmaCerificaciones.equals(other.getFuncionarioFirmaCerificaciones())));
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
        if (getNumeroBorrador() != null) {
            _hashCode += getNumeroBorrador().hashCode();
        }
        if (getFuncionarioFirmaCerificaciones() != null) {
            _hashCode += getFuncionarioFirmaCerificaciones().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OficializarBorrador.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">OficializarBorrador"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroBorrador");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "numeroBorrador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("funcionarioFirmaCerificaciones");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "funcionarioFirmaCerificaciones"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
