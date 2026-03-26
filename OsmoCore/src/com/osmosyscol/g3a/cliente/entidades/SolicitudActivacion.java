/**
 * SolicitudActivacion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.g3a.cliente.entidades;

public class SolicitudActivacion  implements java.io.Serializable {
    private com.osmosyscol.g3a.cliente.entidades.IDUsuario idUsuario;

    private com.osmosyscol.g3a.cliente.entidades.SessionTicket ticket;

    public SolicitudActivacion() {
    }

    public SolicitudActivacion(
           com.osmosyscol.g3a.cliente.entidades.IDUsuario idUsuario,
           com.osmosyscol.g3a.cliente.entidades.SessionTicket ticket) {
           this.idUsuario = idUsuario;
           this.ticket = ticket;
    }


    /**
     * Gets the idUsuario value for this SolicitudActivacion.
     * 
     * @return idUsuario
     */
    public com.osmosyscol.g3a.cliente.entidades.IDUsuario getIdUsuario() {
        return idUsuario;
    }


    /**
     * Sets the idUsuario value for this SolicitudActivacion.
     * 
     * @param idUsuario
     */
    public void setIdUsuario(com.osmosyscol.g3a.cliente.entidades.IDUsuario idUsuario) {
        this.idUsuario = idUsuario;
    }


    /**
     * Gets the ticket value for this SolicitudActivacion.
     * 
     * @return ticket
     */
    public com.osmosyscol.g3a.cliente.entidades.SessionTicket getTicket() {
        return ticket;
    }


    /**
     * Sets the ticket value for this SolicitudActivacion.
     * 
     * @param ticket
     */
    public void setTicket(com.osmosyscol.g3a.cliente.entidades.SessionTicket ticket) {
        this.ticket = ticket;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SolicitudActivacion)) return false;
        SolicitudActivacion other = (SolicitudActivacion) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idUsuario==null && other.getIdUsuario()==null) || 
             (this.idUsuario!=null &&
              this.idUsuario.equals(other.getIdUsuario()))) &&
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
        if (getIdUsuario() != null) {
            _hashCode += getIdUsuario().hashCode();
        }
        if (getTicket() != null) {
            _hashCode += getTicket().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SolicitudActivacion.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "SolicitudActivacion"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idUsuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "idUsuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "IDUsuario"));
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
