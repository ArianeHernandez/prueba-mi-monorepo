/**
 * SubirArchivoDesdeProcesoBPM.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class SubirArchivoDesdeProcesoBPM  implements java.io.Serializable {
    private java.lang.String refBase;

    private java.lang.String nombreTermino;

    private java.lang.String radicacion;

    private java.lang.String tipoDocumento;

    private java.lang.String totalDocumentos;

    private java.lang.String uploadBy;

    public SubirArchivoDesdeProcesoBPM() {
    }

    public SubirArchivoDesdeProcesoBPM(
           java.lang.String refBase,
           java.lang.String nombreTermino,
           java.lang.String radicacion,
           java.lang.String tipoDocumento,
           java.lang.String totalDocumentos,
           java.lang.String uploadBy) {
           this.refBase = refBase;
           this.nombreTermino = nombreTermino;
           this.radicacion = radicacion;
           this.tipoDocumento = tipoDocumento;
           this.totalDocumentos = totalDocumentos;
           this.uploadBy = uploadBy;
    }


    /**
     * Gets the refBase value for this SubirArchivoDesdeProcesoBPM.
     * 
     * @return refBase
     */
    public java.lang.String getRefBase() {
        return refBase;
    }


    /**
     * Sets the refBase value for this SubirArchivoDesdeProcesoBPM.
     * 
     * @param refBase
     */
    public void setRefBase(java.lang.String refBase) {
        this.refBase = refBase;
    }


    /**
     * Gets the nombreTermino value for this SubirArchivoDesdeProcesoBPM.
     * 
     * @return nombreTermino
     */
    public java.lang.String getNombreTermino() {
        return nombreTermino;
    }


    /**
     * Sets the nombreTermino value for this SubirArchivoDesdeProcesoBPM.
     * 
     * @param nombreTermino
     */
    public void setNombreTermino(java.lang.String nombreTermino) {
        this.nombreTermino = nombreTermino;
    }


    /**
     * Gets the radicacion value for this SubirArchivoDesdeProcesoBPM.
     * 
     * @return radicacion
     */
    public java.lang.String getRadicacion() {
        return radicacion;
    }


    /**
     * Sets the radicacion value for this SubirArchivoDesdeProcesoBPM.
     * 
     * @param radicacion
     */
    public void setRadicacion(java.lang.String radicacion) {
        this.radicacion = radicacion;
    }


    /**
     * Gets the tipoDocumento value for this SubirArchivoDesdeProcesoBPM.
     * 
     * @return tipoDocumento
     */
    public java.lang.String getTipoDocumento() {
        return tipoDocumento;
    }


    /**
     * Sets the tipoDocumento value for this SubirArchivoDesdeProcesoBPM.
     * 
     * @param tipoDocumento
     */
    public void setTipoDocumento(java.lang.String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }


    /**
     * Gets the totalDocumentos value for this SubirArchivoDesdeProcesoBPM.
     * 
     * @return totalDocumentos
     */
    public java.lang.String getTotalDocumentos() {
        return totalDocumentos;
    }


    /**
     * Sets the totalDocumentos value for this SubirArchivoDesdeProcesoBPM.
     * 
     * @param totalDocumentos
     */
    public void setTotalDocumentos(java.lang.String totalDocumentos) {
        this.totalDocumentos = totalDocumentos;
    }


    /**
     * Gets the uploadBy value for this SubirArchivoDesdeProcesoBPM.
     * 
     * @return uploadBy
     */
    public java.lang.String getUploadBy() {
        return uploadBy;
    }


    /**
     * Sets the uploadBy value for this SubirArchivoDesdeProcesoBPM.
     * 
     * @param uploadBy
     */
    public void setUploadBy(java.lang.String uploadBy) {
        this.uploadBy = uploadBy;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SubirArchivoDesdeProcesoBPM)) return false;
        SubirArchivoDesdeProcesoBPM other = (SubirArchivoDesdeProcesoBPM) obj;
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
              this.tipoDocumento.equals(other.getTipoDocumento()))) &&
            ((this.totalDocumentos==null && other.getTotalDocumentos()==null) || 
             (this.totalDocumentos!=null &&
              this.totalDocumentos.equals(other.getTotalDocumentos()))) &&
            ((this.uploadBy==null && other.getUploadBy()==null) || 
             (this.uploadBy!=null &&
              this.uploadBy.equals(other.getUploadBy())));
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
        if (getTotalDocumentos() != null) {
            _hashCode += getTotalDocumentos().hashCode();
        }
        if (getUploadBy() != null) {
            _hashCode += getUploadBy().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SubirArchivoDesdeProcesoBPM.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">SubirArchivoDesdeProcesoBPM"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalDocumentos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TotalDocumentos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uploadBy");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "UploadBy"));
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
