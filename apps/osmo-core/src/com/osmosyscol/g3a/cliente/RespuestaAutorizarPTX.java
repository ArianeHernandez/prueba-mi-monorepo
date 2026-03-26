/**
 * RespuestaAutorizarPTX.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.g3a.cliente;

public class RespuestaAutorizarPTX  implements java.io.Serializable {
    private boolean autorizado;

    private com.osmosyscol.g3a.cliente.entidades.SessionTicket ticket;

    public RespuestaAutorizarPTX() {
    }

    public RespuestaAutorizarPTX(
           boolean autorizado,
           com.osmosyscol.g3a.cliente.entidades.SessionTicket ticket) {
           this.autorizado = autorizado;
           this.ticket = ticket;
    }


    /**
     * Gets the autorizado value for this RespuestaAutorizarPTX.
     * 
     * @return autorizado
     */
    public boolean isAutorizado() {
        return autorizado;
    }


    /**
     * Sets the autorizado value for this RespuestaAutorizarPTX.
     * 
     * @param autorizado
     */
    public void setAutorizado(boolean autorizado) {
        this.autorizado = autorizado;
    }


    /**
     * Gets the ticket value for this RespuestaAutorizarPTX.
     * 
     * @return ticket
     */
    public com.osmosyscol.g3a.cliente.entidades.SessionTicket getTicket() {
        return ticket;
    }


    /**
     * Sets the ticket value for this RespuestaAutorizarPTX.
     * 
     * @param ticket
     */
    public void setTicket(com.osmosyscol.g3a.cliente.entidades.SessionTicket ticket) {
        this.ticket = ticket;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RespuestaAutorizarPTX)) return false;
        RespuestaAutorizarPTX other = (RespuestaAutorizarPTX) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.autorizado == other.isAutorizado() &&
            ((this.ticket==null && other.getTicket()==null) || 
             (this.ticket!=null &&
              this.ticket.equals(other.getTicket())));
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
        _hashCode += (isAutorizado() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getTicket() != null) {
            _hashCode += getTicket().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RespuestaAutorizarPTX.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.nzwt.it.com", "RespuestaAutorizarPTX"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("autorizado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.nzwt.it.com", "autorizado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ticket");
        elemField.setXmlName(new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.nzwt.it.com", "ticket"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "SessionTicket"));
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
