/**
 * ConsultaAutos.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class ConsultaAutos  implements java.io.Serializable {
    private java.lang.String tipoDocumento;

    private java.lang.String tipoNotificacion;

    private java.util.Calendar fechaEstado;

    public ConsultaAutos() {
    }

    public ConsultaAutos(
           java.lang.String tipoDocumento,
           java.lang.String tipoNotificacion,
           java.util.Calendar fechaEstado) {
           this.tipoDocumento = tipoDocumento;
           this.tipoNotificacion = tipoNotificacion;
           this.fechaEstado = fechaEstado;
    }


    /**
     * Gets the tipoDocumento value for this ConsultaAutos.
     * 
     * @return tipoDocumento
     */
    public java.lang.String getTipoDocumento() {
        return tipoDocumento;
    }


    /**
     * Sets the tipoDocumento value for this ConsultaAutos.
     * 
     * @param tipoDocumento
     */
    public void setTipoDocumento(java.lang.String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }


    /**
     * Gets the tipoNotificacion value for this ConsultaAutos.
     * 
     * @return tipoNotificacion
     */
    public java.lang.String getTipoNotificacion() {
        return tipoNotificacion;
    }


    /**
     * Sets the tipoNotificacion value for this ConsultaAutos.
     * 
     * @param tipoNotificacion
     */
    public void setTipoNotificacion(java.lang.String tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
    }


    /**
     * Gets the fechaEstado value for this ConsultaAutos.
     * 
     * @return fechaEstado
     */
    public java.util.Calendar getFechaEstado() {
        return fechaEstado;
    }


    /**
     * Sets the fechaEstado value for this ConsultaAutos.
     * 
     * @param fechaEstado
     */
    public void setFechaEstado(java.util.Calendar fechaEstado) {
        this.fechaEstado = fechaEstado;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConsultaAutos)) return false;
        ConsultaAutos other = (ConsultaAutos) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tipoDocumento==null && other.getTipoDocumento()==null) || 
             (this.tipoDocumento!=null &&
              this.tipoDocumento.equals(other.getTipoDocumento()))) &&
            ((this.tipoNotificacion==null && other.getTipoNotificacion()==null) || 
             (this.tipoNotificacion!=null &&
              this.tipoNotificacion.equals(other.getTipoNotificacion()))) &&
            ((this.fechaEstado==null && other.getFechaEstado()==null) || 
             (this.fechaEstado!=null &&
              this.fechaEstado.equals(other.getFechaEstado())));
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
        if (getTipoDocumento() != null) {
            _hashCode += getTipoDocumento().hashCode();
        }
        if (getTipoNotificacion() != null) {
            _hashCode += getTipoNotificacion().hashCode();
        }
        if (getFechaEstado() != null) {
            _hashCode += getFechaEstado().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConsultaAutos.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">ConsultaAutos"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoDocumento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "tipoDocumento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoNotificacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "tipoNotificacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaEstado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "fechaEstado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
