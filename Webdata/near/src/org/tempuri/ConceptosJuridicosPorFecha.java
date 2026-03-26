/**
 * ConceptosJuridicosPorFecha.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class ConceptosJuridicosPorFecha  implements java.io.Serializable {
    private java.util.Calendar fechaInicial;

    private java.util.Calendar fechaFinal;

    private java.lang.String numRad;

    private java.lang.String consec;

    public ConceptosJuridicosPorFecha() {
    }

    public ConceptosJuridicosPorFecha(
           java.util.Calendar fechaInicial,
           java.util.Calendar fechaFinal,
           java.lang.String numRad,
           java.lang.String consec) {
           this.fechaInicial = fechaInicial;
           this.fechaFinal = fechaFinal;
           this.numRad = numRad;
           this.consec = consec;
    }


    /**
     * Gets the fechaInicial value for this ConceptosJuridicosPorFecha.
     * 
     * @return fechaInicial
     */
    public java.util.Calendar getFechaInicial() {
        return fechaInicial;
    }


    /**
     * Sets the fechaInicial value for this ConceptosJuridicosPorFecha.
     * 
     * @param fechaInicial
     */
    public void setFechaInicial(java.util.Calendar fechaInicial) {
        this.fechaInicial = fechaInicial;
    }


    /**
     * Gets the fechaFinal value for this ConceptosJuridicosPorFecha.
     * 
     * @return fechaFinal
     */
    public java.util.Calendar getFechaFinal() {
        return fechaFinal;
    }


    /**
     * Sets the fechaFinal value for this ConceptosJuridicosPorFecha.
     * 
     * @param fechaFinal
     */
    public void setFechaFinal(java.util.Calendar fechaFinal) {
        this.fechaFinal = fechaFinal;
    }


    /**
     * Gets the numRad value for this ConceptosJuridicosPorFecha.
     * 
     * @return numRad
     */
    public java.lang.String getNumRad() {
        return numRad;
    }


    /**
     * Sets the numRad value for this ConceptosJuridicosPorFecha.
     * 
     * @param numRad
     */
    public void setNumRad(java.lang.String numRad) {
        this.numRad = numRad;
    }


    /**
     * Gets the consec value for this ConceptosJuridicosPorFecha.
     * 
     * @return consec
     */
    public java.lang.String getConsec() {
        return consec;
    }


    /**
     * Sets the consec value for this ConceptosJuridicosPorFecha.
     * 
     * @param consec
     */
    public void setConsec(java.lang.String consec) {
        this.consec = consec;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConceptosJuridicosPorFecha)) return false;
        ConceptosJuridicosPorFecha other = (ConceptosJuridicosPorFecha) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fechaInicial==null && other.getFechaInicial()==null) || 
             (this.fechaInicial!=null &&
              this.fechaInicial.equals(other.getFechaInicial()))) &&
            ((this.fechaFinal==null && other.getFechaFinal()==null) || 
             (this.fechaFinal!=null &&
              this.fechaFinal.equals(other.getFechaFinal()))) &&
            ((this.numRad==null && other.getNumRad()==null) || 
             (this.numRad!=null &&
              this.numRad.equals(other.getNumRad()))) &&
            ((this.consec==null && other.getConsec()==null) || 
             (this.consec!=null &&
              this.consec.equals(other.getConsec())));
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
        if (getFechaInicial() != null) {
            _hashCode += getFechaInicial().hashCode();
        }
        if (getFechaFinal() != null) {
            _hashCode += getFechaFinal().hashCode();
        }
        if (getNumRad() != null) {
            _hashCode += getNumRad().hashCode();
        }
        if (getConsec() != null) {
            _hashCode += getConsec().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConceptosJuridicosPorFecha.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">ConceptosJuridicosPorFecha"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaInicial");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "fechaInicial"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaFinal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "fechaFinal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numRad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "numRad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("consec");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "consec"));
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
