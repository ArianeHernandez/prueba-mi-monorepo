/**
 * ConsultaAutosControlEA.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal;

public class ConsultaAutosControlEA  implements java.io.Serializable {
    private java.util.Calendar fechaFinal;

    private java.util.Calendar fechaInicial;

    private java.lang.String horaFinal;

    private java.lang.String horaInicial;

    public ConsultaAutosControlEA() {
    }

    public ConsultaAutosControlEA(
           java.util.Calendar fechaFinal,
           java.util.Calendar fechaInicial,
           java.lang.String horaFinal,
           java.lang.String horaInicial) {
           this.fechaFinal = fechaFinal;
           this.fechaInicial = fechaInicial;
           this.horaFinal = horaFinal;
           this.horaInicial = horaInicial;
    }


    /**
     * Gets the fechaFinal value for this ConsultaAutosControlEA.
     * 
     * @return fechaFinal
     */
    public java.util.Calendar getFechaFinal() {
        return fechaFinal;
    }


    /**
     * Sets the fechaFinal value for this ConsultaAutosControlEA.
     * 
     * @param fechaFinal
     */
    public void setFechaFinal(java.util.Calendar fechaFinal) {
        this.fechaFinal = fechaFinal;
    }


    /**
     * Gets the fechaInicial value for this ConsultaAutosControlEA.
     * 
     * @return fechaInicial
     */
    public java.util.Calendar getFechaInicial() {
        return fechaInicial;
    }


    /**
     * Sets the fechaInicial value for this ConsultaAutosControlEA.
     * 
     * @param fechaInicial
     */
    public void setFechaInicial(java.util.Calendar fechaInicial) {
        this.fechaInicial = fechaInicial;
    }


    /**
     * Gets the horaFinal value for this ConsultaAutosControlEA.
     * 
     * @return horaFinal
     */
    public java.lang.String getHoraFinal() {
        return horaFinal;
    }


    /**
     * Sets the horaFinal value for this ConsultaAutosControlEA.
     * 
     * @param horaFinal
     */
    public void setHoraFinal(java.lang.String horaFinal) {
        this.horaFinal = horaFinal;
    }


    /**
     * Gets the horaInicial value for this ConsultaAutosControlEA.
     * 
     * @return horaInicial
     */
    public java.lang.String getHoraInicial() {
        return horaInicial;
    }


    /**
     * Sets the horaInicial value for this ConsultaAutosControlEA.
     * 
     * @param horaInicial
     */
    public void setHoraInicial(java.lang.String horaInicial) {
        this.horaInicial = horaInicial;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConsultaAutosControlEA)) return false;
        ConsultaAutosControlEA other = (ConsultaAutosControlEA) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fechaFinal==null && other.getFechaFinal()==null) || 
             (this.fechaFinal!=null &&
              this.fechaFinal.equals(other.getFechaFinal()))) &&
            ((this.fechaInicial==null && other.getFechaInicial()==null) || 
             (this.fechaInicial!=null &&
              this.fechaInicial.equals(other.getFechaInicial()))) &&
            ((this.horaFinal==null && other.getHoraFinal()==null) || 
             (this.horaFinal!=null &&
              this.horaFinal.equals(other.getHoraFinal()))) &&
            ((this.horaInicial==null && other.getHoraInicial()==null) || 
             (this.horaInicial!=null &&
              this.horaInicial.equals(other.getHoraInicial())));
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
        if (getFechaFinal() != null) {
            _hashCode += getFechaFinal().hashCode();
        }
        if (getFechaInicial() != null) {
            _hashCode += getFechaInicial().hashCode();
        }
        if (getHoraFinal() != null) {
            _hashCode += getHoraFinal().hashCode();
        }
        if (getHoraInicial() != null) {
            _hashCode += getHoraInicial().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConsultaAutosControlEA.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "ConsultaAutosControlEA"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaFinal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "FechaFinal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaInicial");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "FechaInicial"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("horaFinal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "HoraFinal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("horaInicial");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "HoraInicial"));
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
