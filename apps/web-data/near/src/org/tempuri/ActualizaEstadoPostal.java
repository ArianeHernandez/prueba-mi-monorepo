/**
 * ActualizaEstadoPostal.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class ActualizaEstadoPostal  implements java.io.Serializable {
    private java.lang.String numRadicado;

    private java.lang.Integer codigoTramite;

    private java.lang.Integer tipoDocumento;

    public ActualizaEstadoPostal() {
    }

    public ActualizaEstadoPostal(
           java.lang.String numRadicado,
           java.lang.Integer codigoTramite,
           java.lang.Integer tipoDocumento) {
           this.numRadicado = numRadicado;
           this.codigoTramite = codigoTramite;
           this.tipoDocumento = tipoDocumento;
    }


    /**
     * Gets the numRadicado value for this ActualizaEstadoPostal.
     * 
     * @return numRadicado
     */
    public java.lang.String getNumRadicado() {
        return numRadicado;
    }


    /**
     * Sets the numRadicado value for this ActualizaEstadoPostal.
     * 
     * @param numRadicado
     */
    public void setNumRadicado(java.lang.String numRadicado) {
        this.numRadicado = numRadicado;
    }


    /**
     * Gets the codigoTramite value for this ActualizaEstadoPostal.
     * 
     * @return codigoTramite
     */
    public java.lang.Integer getCodigoTramite() {
        return codigoTramite;
    }


    /**
     * Sets the codigoTramite value for this ActualizaEstadoPostal.
     * 
     * @param codigoTramite
     */
    public void setCodigoTramite(java.lang.Integer codigoTramite) {
        this.codigoTramite = codigoTramite;
    }


    /**
     * Gets the tipoDocumento value for this ActualizaEstadoPostal.
     * 
     * @return tipoDocumento
     */
    public java.lang.Integer getTipoDocumento() {
        return tipoDocumento;
    }


    /**
     * Sets the tipoDocumento value for this ActualizaEstadoPostal.
     * 
     * @param tipoDocumento
     */
    public void setTipoDocumento(java.lang.Integer tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ActualizaEstadoPostal)) return false;
        ActualizaEstadoPostal other = (ActualizaEstadoPostal) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.numRadicado==null && other.getNumRadicado()==null) || 
             (this.numRadicado!=null &&
              this.numRadicado.equals(other.getNumRadicado()))) &&
            ((this.codigoTramite==null && other.getCodigoTramite()==null) || 
             (this.codigoTramite!=null &&
              this.codigoTramite.equals(other.getCodigoTramite()))) &&
            ((this.tipoDocumento==null && other.getTipoDocumento()==null) || 
             (this.tipoDocumento!=null &&
              this.tipoDocumento.equals(other.getTipoDocumento())));
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
        if (getNumRadicado() != null) {
            _hashCode += getNumRadicado().hashCode();
        }
        if (getCodigoTramite() != null) {
            _hashCode += getCodigoTramite().hashCode();
        }
        if (getTipoDocumento() != null) {
            _hashCode += getTipoDocumento().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ActualizaEstadoPostal.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">ActualizaEstadoPostal"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numRadicado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "numRadicado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoTramite");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "codigoTramite"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoDocumento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "tipoDocumento"));
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
