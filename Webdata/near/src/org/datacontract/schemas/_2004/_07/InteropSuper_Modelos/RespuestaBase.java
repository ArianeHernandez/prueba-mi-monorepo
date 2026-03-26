/**
 * RespuestaBase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.InteropSuper_Modelos;

public class RespuestaBase  implements java.io.Serializable {
    private java.lang.Integer cantidadRegistros;

    private java.lang.String mensajeError;

    public RespuestaBase() {
    }

    public RespuestaBase(
           java.lang.Integer cantidadRegistros,
           java.lang.String mensajeError) {
           this.cantidadRegistros = cantidadRegistros;
           this.mensajeError = mensajeError;
    }


    /**
     * Gets the cantidadRegistros value for this RespuestaBase.
     * 
     * @return cantidadRegistros
     */
    public java.lang.Integer getCantidadRegistros() {
        return cantidadRegistros;
    }


    /**
     * Sets the cantidadRegistros value for this RespuestaBase.
     * 
     * @param cantidadRegistros
     */
    public void setCantidadRegistros(java.lang.Integer cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }


    /**
     * Gets the mensajeError value for this RespuestaBase.
     * 
     * @return mensajeError
     */
    public java.lang.String getMensajeError() {
        return mensajeError;
    }


    /**
     * Sets the mensajeError value for this RespuestaBase.
     * 
     * @param mensajeError
     */
    public void setMensajeError(java.lang.String mensajeError) {
        this.mensajeError = mensajeError;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RespuestaBase)) return false;
        RespuestaBase other = (RespuestaBase) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cantidadRegistros==null && other.getCantidadRegistros()==null) || 
             (this.cantidadRegistros!=null &&
              this.cantidadRegistros.equals(other.getCantidadRegistros()))) &&
            ((this.mensajeError==null && other.getMensajeError()==null) || 
             (this.mensajeError!=null &&
              this.mensajeError.equals(other.getMensajeError())));
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
        if (getCantidadRegistros() != null) {
            _hashCode += getCantidadRegistros().hashCode();
        }
        if (getMensajeError() != null) {
            _hashCode += getMensajeError().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RespuestaBase.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "RespuestaBase"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cantidadRegistros");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "CantidadRegistros"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mensajeError");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "MensajeError"));
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
