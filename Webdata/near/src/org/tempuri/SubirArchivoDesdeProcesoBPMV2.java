/**
 * SubirArchivoDesdeProcesoBPMV2.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class SubirArchivoDesdeProcesoBPMV2  implements java.io.Serializable {
    private java.lang.String refBase;

    private java.lang.String nombreTermino;

    private java.lang.String radicacion;

    private java.lang.String tipoDocumento;

    public SubirArchivoDesdeProcesoBPMV2() {
    }

    public SubirArchivoDesdeProcesoBPMV2(
           java.lang.String refBase,
           java.lang.String nombreTermino,
           java.lang.String radicacion,
           java.lang.String tipoDocumento) {
           this.refBase = refBase;
           this.nombreTermino = nombreTermino;
           this.radicacion = radicacion;
           this.tipoDocumento = tipoDocumento;
    }


    /**
     * Gets the refBase value for this SubirArchivoDesdeProcesoBPMV2.
     * 
     * @return refBase
     */
    public java.lang.String getRefBase() {
        return refBase;
    }


    /**
     * Sets the refBase value for this SubirArchivoDesdeProcesoBPMV2.
     * 
     * @param refBase
     */
    public void setRefBase(java.lang.String refBase) {
        this.refBase = refBase;
    }


    /**
     * Gets the nombreTermino value for this SubirArchivoDesdeProcesoBPMV2.
     * 
     * @return nombreTermino
     */
    public java.lang.String getNombreTermino() {
        return nombreTermino;
    }


    /**
     * Sets the nombreTermino value for this SubirArchivoDesdeProcesoBPMV2.
     * 
     * @param nombreTermino
     */
    public void setNombreTermino(java.lang.String nombreTermino) {
        this.nombreTermino = nombreTermino;
    }


    /**
     * Gets the radicacion value for this SubirArchivoDesdeProcesoBPMV2.
     * 
     * @return radicacion
     */
    public java.lang.String getRadicacion() {
        return radicacion;
    }


    /**
     * Sets the radicacion value for this SubirArchivoDesdeProcesoBPMV2.
     * 
     * @param radicacion
     */
    public void setRadicacion(java.lang.String radicacion) {
        this.radicacion = radicacion;
    }


    /**
     * Gets the tipoDocumento value for this SubirArchivoDesdeProcesoBPMV2.
     * 
     * @return tipoDocumento
     */
    public java.lang.String getTipoDocumento() {
        return tipoDocumento;
    }


    /**
     * Sets the tipoDocumento value for this SubirArchivoDesdeProcesoBPMV2.
     * 
     * @param tipoDocumento
     */
    public void setTipoDocumento(java.lang.String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SubirArchivoDesdeProcesoBPMV2)) return false;
        SubirArchivoDesdeProcesoBPMV2 other = (SubirArchivoDesdeProcesoBPMV2) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.refBase==null && other.getRefBase()==null) || 
             (this.refBase!=null &&
              this.refBase.equals(other.getRefBase()))) &&
            ((this.nombreTermino==null && other.getNombreTermino()==null) || 
             (this.nombreTermino!=null &&
              this.nombreTermino.equals(other.getNombreTermino()))) &&
            ((this.radicacion==null && other.getRadicacion()==null) || 
             (this.radicacion!=null &&
              this.radicacion.equals(other.getRadicacion()))) &&
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
        if (getRefBase() != null) {
            _hashCode += getRefBase().hashCode();
        }
        if (getNombreTermino() != null) {
            _hashCode += getNombreTermino().hashCode();
        }
        if (getRadicacion() != null) {
            _hashCode += getRadicacion().hashCode();
        }
        if (getTipoDocumento() != null) {
            _hashCode += getTipoDocumento().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SubirArchivoDesdeProcesoBPMV2.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">SubirArchivoDesdeProcesoBPMV2"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("refBase");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "RefBase"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreTermino");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "NombreTermino"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("radicacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Radicacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoDocumento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TipoDocumento"));
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
