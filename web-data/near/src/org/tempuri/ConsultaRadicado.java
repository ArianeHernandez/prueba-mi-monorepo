/**
 * ConsultaRadicado.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class ConsultaRadicado  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos_BPM.DatosConsultaRadicado datosConsultaRadicado;

    public ConsultaRadicado() {
    }

    public ConsultaRadicado(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos_BPM.DatosConsultaRadicado datosConsultaRadicado) {
           this.datosConsultaRadicado = datosConsultaRadicado;
    }


    /**
     * Gets the datosConsultaRadicado value for this ConsultaRadicado.
     * 
     * @return datosConsultaRadicado
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_BPM.DatosConsultaRadicado getDatosConsultaRadicado() {
        return datosConsultaRadicado;
    }


    /**
     * Sets the datosConsultaRadicado value for this ConsultaRadicado.
     * 
     * @param datosConsultaRadicado
     */
    public void setDatosConsultaRadicado(org.datacontract.schemas._2004._07.InteropSuper_Modelos_BPM.DatosConsultaRadicado datosConsultaRadicado) {
        this.datosConsultaRadicado = datosConsultaRadicado;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConsultaRadicado)) return false;
        ConsultaRadicado other = (ConsultaRadicado) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.datosConsultaRadicado==null && other.getDatosConsultaRadicado()==null) || 
             (this.datosConsultaRadicado!=null &&
              this.datosConsultaRadicado.equals(other.getDatosConsultaRadicado())));
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
        if (getDatosConsultaRadicado() != null) {
            _hashCode += getDatosConsultaRadicado().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConsultaRadicado.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">ConsultaRadicado"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datosConsultaRadicado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "datosConsultaRadicado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.BPM", "DatosConsultaRadicado"));
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
