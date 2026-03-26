/**
 * ClasificacionDocumental.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.InteropSuper_Modelos;

public class ClasificacionDocumental  implements java.io.Serializable {
    private java.lang.String codigoSerie;

    private java.lang.String codigoSubSerie;

    private java.lang.String nombreSerie;

    private java.lang.String nombreSubSerie;

    public ClasificacionDocumental() {
    }

    public ClasificacionDocumental(
           java.lang.String codigoSerie,
           java.lang.String codigoSubSerie,
           java.lang.String nombreSerie,
           java.lang.String nombreSubSerie) {
           this.codigoSerie = codigoSerie;
           this.codigoSubSerie = codigoSubSerie;
           this.nombreSerie = nombreSerie;
           this.nombreSubSerie = nombreSubSerie;
    }


    /**
     * Gets the codigoSerie value for this ClasificacionDocumental.
     * 
     * @return codigoSerie
     */
    public java.lang.String getCodigoSerie() {
        return codigoSerie;
    }


    /**
     * Sets the codigoSerie value for this ClasificacionDocumental.
     * 
     * @param codigoSerie
     */
    public void setCodigoSerie(java.lang.String codigoSerie) {
        this.codigoSerie = codigoSerie;
    }


    /**
     * Gets the codigoSubSerie value for this ClasificacionDocumental.
     * 
     * @return codigoSubSerie
     */
    public java.lang.String getCodigoSubSerie() {
        return codigoSubSerie;
    }


    /**
     * Sets the codigoSubSerie value for this ClasificacionDocumental.
     * 
     * @param codigoSubSerie
     */
    public void setCodigoSubSerie(java.lang.String codigoSubSerie) {
        this.codigoSubSerie = codigoSubSerie;
    }


    /**
     * Gets the nombreSerie value for this ClasificacionDocumental.
     * 
     * @return nombreSerie
     */
    public java.lang.String getNombreSerie() {
        return nombreSerie;
    }


    /**
     * Sets the nombreSerie value for this ClasificacionDocumental.
     * 
     * @param nombreSerie
     */
    public void setNombreSerie(java.lang.String nombreSerie) {
        this.nombreSerie = nombreSerie;
    }


    /**
     * Gets the nombreSubSerie value for this ClasificacionDocumental.
     * 
     * @return nombreSubSerie
     */
    public java.lang.String getNombreSubSerie() {
        return nombreSubSerie;
    }


    /**
     * Sets the nombreSubSerie value for this ClasificacionDocumental.
     * 
     * @param nombreSubSerie
     */
    public void setNombreSubSerie(java.lang.String nombreSubSerie) {
        this.nombreSubSerie = nombreSubSerie;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ClasificacionDocumental)) return false;
        ClasificacionDocumental other = (ClasificacionDocumental) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codigoSerie==null && other.getCodigoSerie()==null) || 
             (this.codigoSerie!=null &&
              this.codigoSerie.equals(other.getCodigoSerie()))) &&
            ((this.codigoSubSerie==null && other.getCodigoSubSerie()==null) || 
             (this.codigoSubSerie!=null &&
              this.codigoSubSerie.equals(other.getCodigoSubSerie()))) &&
            ((this.nombreSerie==null && other.getNombreSerie()==null) || 
             (this.nombreSerie!=null &&
              this.nombreSerie.equals(other.getNombreSerie()))) &&
            ((this.nombreSubSerie==null && other.getNombreSubSerie()==null) || 
             (this.nombreSubSerie!=null &&
              this.nombreSubSerie.equals(other.getNombreSubSerie())));
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
        if (getCodigoSerie() != null) {
            _hashCode += getCodigoSerie().hashCode();
        }
        if (getCodigoSubSerie() != null) {
            _hashCode += getCodigoSubSerie().hashCode();
        }
        if (getNombreSerie() != null) {
            _hashCode += getNombreSerie().hashCode();
        }
        if (getNombreSubSerie() != null) {
            _hashCode += getNombreSubSerie().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ClasificacionDocumental.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "clasificacionDocumental"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoSerie");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "codigoSerie"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoSubSerie");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "codigoSubSerie"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreSerie");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "nombreSerie"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreSubSerie");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "nombreSubSerie"));
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
