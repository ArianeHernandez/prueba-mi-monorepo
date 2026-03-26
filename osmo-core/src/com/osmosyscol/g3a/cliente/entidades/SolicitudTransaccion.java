/**
 * SolicitudTransaccion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.g3a.cliente.entidades;

public class SolicitudTransaccion  implements java.io.Serializable {
    private com.osmosyscol.g3a.cliente.entidades.Operacion operacion;

    private com.osmosyscol.g3a.cliente.entidades.SessionTicket ticket;

    public SolicitudTransaccion() {
    }

    public SolicitudTransaccion(
           com.osmosyscol.g3a.cliente.entidades.Operacion operacion,
           com.osmosyscol.g3a.cliente.entidades.SessionTicket ticket) {
           this.operacion = operacion;
           this.ticket = ticket;
    }


    /**
     * Gets the operacion value for this SolicitudTransaccion.
     * 
     * @return operacion
     */
    public com.osmosyscol.g3a.cliente.entidades.Operacion getOperacion() {
        return operacion;
    }


    /**
     * Sets the operacion value for this SolicitudTransaccion.
     * 
     * @param operacion
     */
    public void setOperacion(com.osmosyscol.g3a.cliente.entidades.Operacion operacion) {
        this.operacion = operacion;
    }


    /**
     * Gets the ticket value for this SolicitudTransaccion.
     * 
     * @return ticket
     */
    public com.osmosyscol.g3a.cliente.entidades.SessionTicket getTicket() {
        return ticket;
    }


    /**
     * Sets the ticket value for this SolicitudTransaccion.
     * 
     * @param ticket
     */
    public void setTicket(com.osmosyscol.g3a.cliente.entidades.SessionTicket ticket) {
        this.ticket = ticket;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SolicitudTransaccion)) return false;
        SolicitudTransaccion other = (SolicitudTransaccion) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.operacion==null && other.getOperacion()==null) || 
             (this.operacion!=null &&
              this.operacion.equals(other.getOperacion()))) &&
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
        if (getOperacion() != null) {
            _hashCode += getOperacion().hashCode();
        }
        if (getTicket() != null) {
            _hashCode += getTicket().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SolicitudTransaccion.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "SolicitudTransaccion"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("operacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "operacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "Operacion"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ticket");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "ticket"));
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
