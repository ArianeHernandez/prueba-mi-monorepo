/**
 * ConsultaEstadosTraslados.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal;

public class ConsultaEstadosTraslados  implements java.io.Serializable {
    private java.lang.String codTramite;

    private java.lang.String codigoDependencia;

    private java.lang.String codigoTipoDocumento;

    private java.lang.String codigoTipoSeguridad;

    public ConsultaEstadosTraslados() {
    }

    public ConsultaEstadosTraslados(
           java.lang.String codTramite,
           java.lang.String codigoDependencia,
           java.lang.String codigoTipoDocumento,
           java.lang.String codigoTipoSeguridad) {
           this.codTramite = codTramite;
           this.codigoDependencia = codigoDependencia;
           this.codigoTipoDocumento = codigoTipoDocumento;
           this.codigoTipoSeguridad = codigoTipoSeguridad;
    }


    /**
     * Gets the codTramite value for this ConsultaEstadosTraslados.
     * 
     * @return codTramite
     */
    public java.lang.String getCodTramite() {
        return codTramite;
    }


    /**
     * Sets the codTramite value for this ConsultaEstadosTraslados.
     * 
     * @param codTramite
     */
    public void setCodTramite(java.lang.String codTramite) {
        this.codTramite = codTramite;
    }


    /**
     * Gets the codigoDependencia value for this ConsultaEstadosTraslados.
     * 
     * @return codigoDependencia
     */
    public java.lang.String getCodigoDependencia() {
        return codigoDependencia;
    }


    /**
     * Sets the codigoDependencia value for this ConsultaEstadosTraslados.
     * 
     * @param codigoDependencia
     */
    public void setCodigoDependencia(java.lang.String codigoDependencia) {
        this.codigoDependencia = codigoDependencia;
    }


    /**
     * Gets the codigoTipoDocumento value for this ConsultaEstadosTraslados.
     * 
     * @return codigoTipoDocumento
     */
    public java.lang.String getCodigoTipoDocumento() {
        return codigoTipoDocumento;
    }


    /**
     * Sets the codigoTipoDocumento value for this ConsultaEstadosTraslados.
     * 
     * @param codigoTipoDocumento
     */
    public void setCodigoTipoDocumento(java.lang.String codigoTipoDocumento) {
        this.codigoTipoDocumento = codigoTipoDocumento;
    }


    /**
     * Gets the codigoTipoSeguridad value for this ConsultaEstadosTraslados.
     * 
     * @return codigoTipoSeguridad
     */
    public java.lang.String getCodigoTipoSeguridad() {
        return codigoTipoSeguridad;
    }


    /**
     * Sets the codigoTipoSeguridad value for this ConsultaEstadosTraslados.
     * 
     * @param codigoTipoSeguridad
     */
    public void setCodigoTipoSeguridad(java.lang.String codigoTipoSeguridad) {
        this.codigoTipoSeguridad = codigoTipoSeguridad;
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
            ((this.codTramite==null && other.getCodTramite()==null) || 
             (this.codTramite!=null &&
              this.codTramite.equals(other.getCodTramite()))) &&
            ((this.codigoDependencia==null && other.getCodigoDependencia()==null) || 
             (this.codigoDependencia!=null &&
              this.codigoDependencia.equals(other.getCodigoDependencia()))) &&
            ((this.codigoTipoDocumento==null && other.getCodigoTipoDocumento()==null) || 
             (this.codigoTipoDocumento!=null &&
              this.codigoTipoDocumento.equals(other.getCodigoTipoDocumento()))) &&
            ((this.codigoTipoSeguridad==null && other.getCodigoTipoSeguridad()==null) || 
             (this.codigoTipoSeguridad!=null &&
              this.codigoTipoSeguridad.equals(other.getCodigoTipoSeguridad())));
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
        if (getCodTramite() != null) {
            _hashCode += getCodTramite().hashCode();
        }
        if (getCodigoDependencia() != null) {
            _hashCode += getCodigoDependencia().hashCode();
        }
        if (getCodigoTipoDocumento() != null) {
            _hashCode += getCodigoTipoDocumento().hashCode();
        }
        if (getCodigoTipoSeguridad() != null) {
            _hashCode += getCodigoTipoSeguridad().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConsultaEstadosTraslados.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "ConsultaEstadosTraslados"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codTramite");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "codTramite"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoDependencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "codigoDependencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoTipoDocumento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "codigoTipoDocumento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoTipoSeguridad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "codigoTipoSeguridad"));
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
