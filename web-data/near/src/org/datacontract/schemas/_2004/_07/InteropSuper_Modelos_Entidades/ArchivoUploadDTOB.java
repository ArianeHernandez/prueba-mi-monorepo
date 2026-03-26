/**
 * ArchivoUploadDTOB.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.InteropSuper_Modelos_Entidades;

public class ArchivoUploadDTOB  implements java.io.Serializable {
    private java.lang.String archivoBase64;

    private java.lang.String extension;

    private java.lang.String radicacion;

    private java.lang.String tipoDocumento;

    private java.lang.String totalDocumentos;

    public ArchivoUploadDTOB() {
    }

    public ArchivoUploadDTOB(
           java.lang.String archivoBase64,
           java.lang.String extension,
           java.lang.String radicacion,
           java.lang.String tipoDocumento,
           java.lang.String totalDocumentos) {
           this.archivoBase64 = archivoBase64;
           this.extension = extension;
           this.radicacion = radicacion;
           this.tipoDocumento = tipoDocumento;
           this.totalDocumentos = totalDocumentos;
    }


    /**
     * Gets the archivoBase64 value for this ArchivoUploadDTOB.
     * 
     * @return archivoBase64
     */
    public java.lang.String getArchivoBase64() {
        return archivoBase64;
    }


    /**
     * Sets the archivoBase64 value for this ArchivoUploadDTOB.
     * 
     * @param archivoBase64
     */
    public void setArchivoBase64(java.lang.String archivoBase64) {
        this.archivoBase64 = archivoBase64;
    }


    /**
     * Gets the extension value for this ArchivoUploadDTOB.
     * 
     * @return extension
     */
    public java.lang.String getExtension() {
        return extension;
    }


    /**
     * Sets the extension value for this ArchivoUploadDTOB.
     * 
     * @param extension
     */
    public void setExtension(java.lang.String extension) {
        this.extension = extension;
    }


    /**
     * Gets the radicacion value for this ArchivoUploadDTOB.
     * 
     * @return radicacion
     */
    public java.lang.String getRadicacion() {
        return radicacion;
    }


    /**
     * Sets the radicacion value for this ArchivoUploadDTOB.
     * 
     * @param radicacion
     */
    public void setRadicacion(java.lang.String radicacion) {
        this.radicacion = radicacion;
    }


    /**
     * Gets the tipoDocumento value for this ArchivoUploadDTOB.
     * 
     * @return tipoDocumento
     */
    public java.lang.String getTipoDocumento() {
        return tipoDocumento;
    }


    /**
     * Sets the tipoDocumento value for this ArchivoUploadDTOB.
     * 
     * @param tipoDocumento
     */
    public void setTipoDocumento(java.lang.String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }


    /**
     * Gets the totalDocumentos value for this ArchivoUploadDTOB.
     * 
     * @return totalDocumentos
     */
    public java.lang.String getTotalDocumentos() {
        return totalDocumentos;
    }


    /**
     * Sets the totalDocumentos value for this ArchivoUploadDTOB.
     * 
     * @param totalDocumentos
     */
    public void setTotalDocumentos(java.lang.String totalDocumentos) {
        this.totalDocumentos = totalDocumentos;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ArchivoUploadDTOB)) return false;
        ArchivoUploadDTOB other = (ArchivoUploadDTOB) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.archivoBase64==null && other.getArchivoBase64()==null) || 
             (this.archivoBase64!=null &&
              this.archivoBase64.equals(other.getArchivoBase64()))) &&
            ((this.extension==null && other.getExtension()==null) || 
             (this.extension!=null &&
              this.extension.equals(other.getExtension()))) &&
            ((this.radicacion==null && other.getRadicacion()==null) || 
             (this.radicacion!=null &&
              this.radicacion.equals(other.getRadicacion()))) &&
            ((this.tipoDocumento==null && other.getTipoDocumento()==null) || 
             (this.tipoDocumento!=null &&
              this.tipoDocumento.equals(other.getTipoDocumento()))) &&
            ((this.totalDocumentos==null && other.getTotalDocumentos()==null) || 
             (this.totalDocumentos!=null &&
              this.totalDocumentos.equals(other.getTotalDocumentos())));
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
        if (getArchivoBase64() != null) {
            _hashCode += getArchivoBase64().hashCode();
        }
        if (getExtension() != null) {
            _hashCode += getExtension().hashCode();
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ArchivoUploadDTOB.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Entidades", "ArchivoUploadDTOB"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("archivoBase64");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Entidades", "ArchivoBase64"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extension");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Entidades", "Extension"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("radicacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Entidades", "Radicacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoDocumento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Entidades", "TipoDocumento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalDocumentos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Entidades", "TotalDocumentos"));
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
