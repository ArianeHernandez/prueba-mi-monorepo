/**
 * RespuestaConsultaPostal.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal;

public class RespuestaConsultaPostal  extends org.datacontract.schemas._2004._07.InteropSuper_Modelos.RespuestaBase  implements java.io.Serializable {
    private java.lang.Integer codigoDevolucion;

    private java.lang.String mensajeDevolucion;

    public RespuestaConsultaPostal() {
    }

    public RespuestaConsultaPostal(
           java.lang.Integer cantidadRegistros,
           java.lang.String mensajeError,
           java.lang.Integer codigoDevolucion,
           java.lang.String mensajeDevolucion) {
        super(
            cantidadRegistros,
            mensajeError);
        this.codigoDevolucion = codigoDevolucion;
        this.mensajeDevolucion = mensajeDevolucion;
    }


    /**
     * Gets the codigoDevolucion value for this RespuestaConsultaPostal.
     * 
     * @return codigoDevolucion
     */
    public java.lang.Integer getCodigoDevolucion() {
        return codigoDevolucion;
    }


    /**
     * Sets the codigoDevolucion value for this RespuestaConsultaPostal.
     * 
     * @param codigoDevolucion
     */
    public void setCodigoDevolucion(java.lang.Integer codigoDevolucion) {
        this.codigoDevolucion = codigoDevolucion;
    }


    /**
     * Gets the mensajeDevolucion value for this RespuestaConsultaPostal.
     * 
     * @return mensajeDevolucion
     */
    public java.lang.String getMensajeDevolucion() {
        return mensajeDevolucion;
    }


    /**
     * Sets the mensajeDevolucion value for this RespuestaConsultaPostal.
     * 
     * @param mensajeDevolucion
     */
    public void setMensajeDevolucion(java.lang.String mensajeDevolucion) {
        this.mensajeDevolucion = mensajeDevolucion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RespuestaConsultaPostal)) return false;
        RespuestaConsultaPostal other = (RespuestaConsultaPostal) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.codigoDevolucion==null && other.getCodigoDevolucion()==null) || 
             (this.codigoDevolucion!=null &&
              this.codigoDevolucion.equals(other.getCodigoDevolucion()))) &&
            ((this.mensajeDevolucion==null && other.getMensajeDevolucion()==null) || 
             (this.mensajeDevolucion!=null &&
              this.mensajeDevolucion.equals(other.getMensajeDevolucion())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getCodigoDevolucion() != null) {
            _hashCode += getCodigoDevolucion().hashCode();
        }
        if (getMensajeDevolucion() != null) {
            _hashCode += getMensajeDevolucion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RespuestaConsultaPostal.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "RespuestaConsultaPostal"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoDevolucion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "CodigoDevolucion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mensajeDevolucion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "MensajeDevolucion"));
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
