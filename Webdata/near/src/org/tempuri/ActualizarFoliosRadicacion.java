/**
 * ActualizarFoliosRadicacion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class ActualizarFoliosRadicacion  implements java.io.Serializable {
    private java.lang.String radicado;

    private java.lang.Integer numeroFolios;

    public ActualizarFoliosRadicacion() {
    }

    public ActualizarFoliosRadicacion(
           java.lang.String radicado,
           java.lang.Integer numeroFolios) {
           this.radicado = radicado;
           this.numeroFolios = numeroFolios;
    }


    /**
     * Gets the radicado value for this ActualizarFoliosRadicacion.
     * 
     * @return radicado
     */
    public java.lang.String getRadicado() {
        return radicado;
    }


    /**
     * Sets the radicado value for this ActualizarFoliosRadicacion.
     * 
     * @param radicado
     */
    public void setRadicado(java.lang.String radicado) {
        this.radicado = radicado;
    }


    /**
     * Gets the numeroFolios value for this ActualizarFoliosRadicacion.
     * 
     * @return numeroFolios
     */
    public java.lang.Integer getNumeroFolios() {
        return numeroFolios;
    }


    /**
     * Sets the numeroFolios value for this ActualizarFoliosRadicacion.
     * 
     * @param numeroFolios
     */
    public void setNumeroFolios(java.lang.Integer numeroFolios) {
        this.numeroFolios = numeroFolios;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ActualizarFoliosRadicacion)) return false;
        ActualizarFoliosRadicacion other = (ActualizarFoliosRadicacion) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.radicado==null && other.getRadicado()==null) || 
             (this.radicado!=null &&
              this.radicado.equals(other.getRadicado()))) &&
            ((this.numeroFolios==null && other.getNumeroFolios()==null) || 
             (this.numeroFolios!=null &&
              this.numeroFolios.equals(other.getNumeroFolios())));
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
        if (getRadicado() != null) {
            _hashCode += getRadicado().hashCode();
        }
        if (getNumeroFolios() != null) {
            _hashCode += getNumeroFolios().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ActualizarFoliosRadicacion.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">ActualizarFoliosRadicacion"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("radicado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Radicado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroFolios");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "NumeroFolios"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
