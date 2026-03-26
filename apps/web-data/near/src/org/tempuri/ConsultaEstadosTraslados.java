/**
 * ConsultaEstadosTraslados.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class ConsultaEstadosTraslados  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ConsultaEstadosTraslados filtro;

    public ConsultaEstadosTraslados() {
    }

    public ConsultaEstadosTraslados(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ConsultaEstadosTraslados filtro) {
           this.filtro = filtro;
    }


    /**
     * Gets the filtro value for this ConsultaEstadosTraslados.
     * 
     * @return filtro
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ConsultaEstadosTraslados getFiltro() {
        return filtro;
    }


    /**
     * Sets the filtro value for this ConsultaEstadosTraslados.
     * 
     * @param filtro
     */
    public void setFiltro(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ConsultaEstadosTraslados filtro) {
        this.filtro = filtro;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConsultaEstadosTraslados)) return false;
        ConsultaEstadosTraslados other = (ConsultaEstadosTraslados) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.filtro==null && other.getFiltro()==null) || 
             (this.filtro!=null &&
              this.filtro.equals(other.getFiltro())));
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
        if (getFiltro() != null) {
            _hashCode += getFiltro().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConsultaEstadosTraslados.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">ConsultaEstadosTraslados"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("filtro");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "filtro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "ConsultaEstadosTraslados"));
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
