/**
 * IBPMWSDevolucionClass.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.InteropSuper_Modelos;

public class IBPMWSDevolucionClass  implements java.io.Serializable {
    private java.lang.Integer codigoDevolucion;

    private java.lang.String mensajeDevolucion;

    private java.lang.String totalDocumentos;

    private java.lang.String uploadBy;

    public IBPMWSDevolucionClass() {
    }

    public IBPMWSDevolucionClass(
           java.lang.Integer codigoDevolucion,
           java.lang.String mensajeDevolucion,
           java.lang.String totalDocumentos,
           java.lang.String uploadBy) {
           this.codigoDevolucion = codigoDevolucion;
           this.mensajeDevolucion = mensajeDevolucion;
           this.totalDocumentos = totalDocumentos;
           this.uploadBy = uploadBy;
    }


    /**
     * Gets the codigoDevolucion value for this IBPMWSDevolucionClass.
     * 
     * @return codigoDevolucion
     */
    public java.lang.Integer getCodigoDevolucion() {
        return codigoDevolucion;
    }


    /**
     * Sets the codigoDevolucion value for this IBPMWSDevolucionClass.
     * 
     * @param codigoDevolucion
     */
    public void setCodigoDevolucion(java.lang.Integer codigoDevolucion) {
        this.codigoDevolucion = codigoDevolucion;
    }


    /**
     * Gets the mensajeDevolucion value for this IBPMWSDevolucionClass.
     * 
     * @return mensajeDevolucion
     */
    public java.lang.String getMensajeDevolucion() {
        return mensajeDevolucion;
    }


    /**
     * Sets the mensajeDevolucion value for this IBPMWSDevolucionClass.
     * 
     * @param mensajeDevolucion
     */
    public void setMensajeDevolucion(java.lang.String mensajeDevolucion) {
        this.mensajeDevolucion = mensajeDevolucion;
    }


    /**
     * Gets the totalDocumentos value for this IBPMWSDevolucionClass.
     * 
     * @return totalDocumentos
     */
    public java.lang.String getTotalDocumentos() {
        return totalDocumentos;
    }


    /**
     * Sets the totalDocumentos value for this IBPMWSDevolucionClass.
     * 
     * @param totalDocumentos
     */
    public void setTotalDocumentos(java.lang.String totalDocumentos) {
        this.totalDocumentos = totalDocumentos;
    }


    /**
     * Gets the uploadBy value for this IBPMWSDevolucionClass.
     * 
     * @return uploadBy
     */
    public java.lang.String getUploadBy() {
        return uploadBy;
    }


    /**
     * Sets the uploadBy value for this IBPMWSDevolucionClass.
     * 
     * @param uploadBy
     */
    public void setUploadBy(java.lang.String uploadBy) {
        this.uploadBy = uploadBy;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IBPMWSDevolucionClass)) return false;
        IBPMWSDevolucionClass other = (IBPMWSDevolucionClass) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codigoDevolucion==null && other.getCodigoDevolucion()==null) || 
             (this.codigoDevolucion!=null &&
              this.codigoDevolucion.equals(other.getCodigoDevolucion()))) &&
            ((this.mensajeDevolucion==null && other.getMensajeDevolucion()==null) || 
             (this.mensajeDevolucion!=null &&
              this.mensajeDevolucion.equals(other.getMensajeDevolucion()))) &&
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
        if (getCodigoDevolucion() != null) {
            _hashCode += getCodigoDevolucion().hashCode();
        }
        if (getMensajeDevolucion() != null) {
            _hashCode += getMensajeDevolucion().hashCode();
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
        new org.apache.axis.description.TypeDesc(IBPMWSDevolucionClass.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "iBPMWSDevolucionClass"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoDevolucion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "CodigoDevolucion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mensajeDevolucion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "MensajeDevolucion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalDocumentos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "TotalDocumentos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uploadBy");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "UploadBy"));
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
