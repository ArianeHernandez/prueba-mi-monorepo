/**
 * ConsultarRadicaciones.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class ConsultarRadicaciones  implements java.io.Serializable {
    private java.lang.String codigoDependencia;

    private java.lang.Integer tipoDocumento;

    private java.lang.String fecha;

    public ConsultarRadicaciones() {
    }

    public ConsultarRadicaciones(
           java.lang.String codigoDependencia,
           java.lang.Integer tipoDocumento,
           java.lang.String fecha) {
           this.codigoDependencia = codigoDependencia;
           this.tipoDocumento = tipoDocumento;
           this.fecha = fecha;
    }


    /**
     * Gets the codigoDependencia value for this ConsultarRadicaciones.
     * 
     * @return codigoDependencia
     */
    public java.lang.String getCodigoDependencia() {
        return codigoDependencia;
    }


    /**
     * Sets the codigoDependencia value for this ConsultarRadicaciones.
     * 
     * @param codigoDependencia
     */
    public void setCodigoDependencia(java.lang.String codigoDependencia) {
        this.codigoDependencia = codigoDependencia;
    }


    /**
     * Gets the tipoDocumento value for this ConsultarRadicaciones.
     * 
     * @return tipoDocumento
     */
    public java.lang.Integer getTipoDocumento() {
        return tipoDocumento;
    }


    /**
     * Sets the tipoDocumento value for this ConsultarRadicaciones.
     * 
     * @param tipoDocumento
     */
    public void setTipoDocumento(java.lang.Integer tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }


    /**
     * Gets the fecha value for this ConsultarRadicaciones.
     * 
     * @return fecha
     */
    public java.lang.String getFecha() {
        return fecha;
    }


    /**
     * Sets the fecha value for this ConsultarRadicaciones.
     * 
     * @param fecha
     */
    public void setFecha(java.lang.String fecha) {
        this.fecha = fecha;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConsultarRadicaciones)) return false;
        ConsultarRadicaciones other = (ConsultarRadicaciones) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codigoDependencia==null && other.getCodigoDependencia()==null) || 
             (this.codigoDependencia!=null &&
              this.codigoDependencia.equals(other.getCodigoDependencia()))) &&
            ((this.tipoDocumento==null && other.getTipoDocumento()==null) || 
             (this.tipoDocumento!=null &&
              this.tipoDocumento.equals(other.getTipoDocumento()))) &&
            ((this.fecha==null && other.getFecha()==null) || 
             (this.fecha!=null &&
              this.fecha.equals(other.getFecha())));
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
        if (getCodigoDependencia() != null) {
            _hashCode += getCodigoDependencia().hashCode();
        }
        if (getTipoDocumento() != null) {
            _hashCode += getTipoDocumento().hashCode();
        }
        if (getFecha() != null) {
            _hashCode += getFecha().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConsultarRadicaciones.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">ConsultarRadicaciones"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoDependencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "codigoDependencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoDocumento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "tipoDocumento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecha");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "fecha"));
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
