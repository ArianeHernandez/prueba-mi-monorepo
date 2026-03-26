/**
 * RespuestaAutenticarPTX.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.g3a.cliente;

public class RespuestaAutenticarPTX  implements java.io.Serializable {
    private boolean autenticado;

    private com.osmosyscol.g3a.cliente.entidades.SessionTicket ticket;

    private com.osmosyscol.g3a.cliente.entidades.Usuario usr;

    public RespuestaAutenticarPTX() {
    }

    public RespuestaAutenticarPTX(
           boolean autenticado,
           com.osmosyscol.g3a.cliente.entidades.SessionTicket ticket,
           com.osmosyscol.g3a.cliente.entidades.Usuario usr) {
           this.autenticado = autenticado;
           this.ticket = ticket;
           this.usr = usr;
    }


    /**
     * Gets the autenticado value for this RespuestaAutenticarPTX.
     * 
     * @return autenticado
     */
    public boolean isAutenticado() {
        return autenticado;
    }


    /**
     * Sets the autenticado value for this RespuestaAutenticarPTX.
     * 
     * @param autenticado
     */
    public void setAutenticado(boolean autenticado) {
        this.autenticado = autenticado;
    }


    /**
     * Gets the ticket value for this RespuestaAutenticarPTX.
     * 
     * @return ticket
     */
    public com.osmosyscol.g3a.cliente.entidades.SessionTicket getTicket() {
        return ticket;
    }


    /**
     * Sets the ticket value for this RespuestaAutenticarPTX.
     * 
     * @param ticket
     */
    public void setTicket(com.osmosyscol.g3a.cliente.entidades.SessionTicket ticket) {
        this.ticket = ticket;
    }


    /**
     * Gets the usr value for this RespuestaAutenticarPTX.
     * 
     * @return usr
     */
    public com.osmosyscol.g3a.cliente.entidades.Usuario getUsr() {
        return usr;
    }


    /**
     * Sets the usr value for this RespuestaAutenticarPTX.
     * 
     * @param usr
     */
    public void setUsr(com.osmosyscol.g3a.cliente.entidades.Usuario usr) {
        this.usr = usr;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RespuestaAutenticarPTX)) return false;
        RespuestaAutenticarPTX other = (RespuestaAutenticarPTX) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.autenticado == other.isAutenticado() &&
            ((this.ticket==null && other.getTicket()==null) || 
             (this.ticket!=null &&
              this.ticket.equals(other.getTicket()))) &&
            ((this.usr==null && other.getUsr()==null) || 
             (this.usr!=null &&
              this.usr.equals(other.getUsr())));
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
        _hashCode += (isAutenticado() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getTicket() != null) {
            _hashCode += getTicket().hashCode();
        }
        if (getUsr() != null) {
            _hashCode += getUsr().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RespuestaAutenticarPTX.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.nzwt.it.com", "RespuestaAutenticarPTX"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("autenticado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.nzwt.it.com", "autenticado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ticket");
        elemField.setXmlName(new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.nzwt.it.com", "ticket"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "SessionTicket"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("usr");
        elemField.setXmlName(new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.nzwt.it.com", "usr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://entidades.servicioweb.osmoautenticador.nzwt.it.com", "Usuario"));
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
