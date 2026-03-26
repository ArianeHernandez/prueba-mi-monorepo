/**
 * TipoElementoEntradaconsultar_entrada_almacen.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.itosmosys.www.datapi.servicios.consultar_entrada_almacen;

public class TipoElementoEntradaconsultar_entrada_almacen  implements java.io.Serializable {
    private java.lang.Integer p_ID_SOLICITANTE;

    private java.lang.Integer p_NUMERO_FACTURA;

    private java.util.Date p_FECHA_FACTURA;

    private java.lang.String p_TIPO_CONTRATO;

    private java.lang.Integer p_NUMERO_CONTRATO;

    public TipoElementoEntradaconsultar_entrada_almacen() {
    }

    public TipoElementoEntradaconsultar_entrada_almacen(
           java.lang.Integer p_ID_SOLICITANTE,
           java.lang.Integer p_NUMERO_FACTURA,
           java.util.Date p_FECHA_FACTURA,
           java.lang.String p_TIPO_CONTRATO,
           java.lang.Integer p_NUMERO_CONTRATO) {
           this.p_ID_SOLICITANTE = p_ID_SOLICITANTE;
           this.p_NUMERO_FACTURA = p_NUMERO_FACTURA;
           this.p_FECHA_FACTURA = p_FECHA_FACTURA;
           this.p_TIPO_CONTRATO = p_TIPO_CONTRATO;
           this.p_NUMERO_CONTRATO = p_NUMERO_CONTRATO;
    }


    /**
     * Gets the p_ID_SOLICITANTE value for this TipoElementoEntradaconsultar_entrada_almacen.
     * 
     * @return p_ID_SOLICITANTE
     */
    public java.lang.Integer getP_ID_SOLICITANTE() {
        return p_ID_SOLICITANTE;
    }


    /**
     * Sets the p_ID_SOLICITANTE value for this TipoElementoEntradaconsultar_entrada_almacen.
     * 
     * @param p_ID_SOLICITANTE
     */
    public void setP_ID_SOLICITANTE(java.lang.Integer p_ID_SOLICITANTE) {
        this.p_ID_SOLICITANTE = p_ID_SOLICITANTE;
    }


    /**
     * Gets the p_NUMERO_FACTURA value for this TipoElementoEntradaconsultar_entrada_almacen.
     * 
     * @return p_NUMERO_FACTURA
     */
    public java.lang.Integer getP_NUMERO_FACTURA() {
        return p_NUMERO_FACTURA;
    }


    /**
     * Sets the p_NUMERO_FACTURA value for this TipoElementoEntradaconsultar_entrada_almacen.
     * 
     * @param p_NUMERO_FACTURA
     */
    public void setP_NUMERO_FACTURA(java.lang.Integer p_NUMERO_FACTURA) {
        this.p_NUMERO_FACTURA = p_NUMERO_FACTURA;
    }


    /**
     * Gets the p_FECHA_FACTURA value for this TipoElementoEntradaconsultar_entrada_almacen.
     * 
     * @return p_FECHA_FACTURA
     */
    public java.util.Date getP_FECHA_FACTURA() {
        return p_FECHA_FACTURA;
    }


    /**
     * Sets the p_FECHA_FACTURA value for this TipoElementoEntradaconsultar_entrada_almacen.
     * 
     * @param p_FECHA_FACTURA
     */
    public void setP_FECHA_FACTURA(java.util.Date p_FECHA_FACTURA) {
        this.p_FECHA_FACTURA = p_FECHA_FACTURA;
    }


    /**
     * Gets the p_TIPO_CONTRATO value for this TipoElementoEntradaconsultar_entrada_almacen.
     * 
     * @return p_TIPO_CONTRATO
     */
    public java.lang.String getP_TIPO_CONTRATO() {
        return p_TIPO_CONTRATO;
    }


    /**
     * Sets the p_TIPO_CONTRATO value for this TipoElementoEntradaconsultar_entrada_almacen.
     * 
     * @param p_TIPO_CONTRATO
     */
    public void setP_TIPO_CONTRATO(java.lang.String p_TIPO_CONTRATO) {
        this.p_TIPO_CONTRATO = p_TIPO_CONTRATO;
    }


    /**
     * Gets the p_NUMERO_CONTRATO value for this TipoElementoEntradaconsultar_entrada_almacen.
     * 
     * @return p_NUMERO_CONTRATO
     */
    public java.lang.Integer getP_NUMERO_CONTRATO() {
        return p_NUMERO_CONTRATO;
    }


    /**
     * Sets the p_NUMERO_CONTRATO value for this TipoElementoEntradaconsultar_entrada_almacen.
     * 
     * @param p_NUMERO_CONTRATO
     */
    public void setP_NUMERO_CONTRATO(java.lang.Integer p_NUMERO_CONTRATO) {
        this.p_NUMERO_CONTRATO = p_NUMERO_CONTRATO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoElementoEntradaconsultar_entrada_almacen)) return false;
        TipoElementoEntradaconsultar_entrada_almacen other = (TipoElementoEntradaconsultar_entrada_almacen) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.p_ID_SOLICITANTE==null && other.getP_ID_SOLICITANTE()==null) || 
             (this.p_ID_SOLICITANTE!=null &&
              this.p_ID_SOLICITANTE.equals(other.getP_ID_SOLICITANTE()))) &&
            ((this.p_NUMERO_FACTURA==null && other.getP_NUMERO_FACTURA()==null) || 
             (this.p_NUMERO_FACTURA!=null &&
              this.p_NUMERO_FACTURA.equals(other.getP_NUMERO_FACTURA()))) &&
            ((this.p_FECHA_FACTURA==null && other.getP_FECHA_FACTURA()==null) || 
             (this.p_FECHA_FACTURA!=null &&
              this.p_FECHA_FACTURA.equals(other.getP_FECHA_FACTURA()))) &&
            ((this.p_TIPO_CONTRATO==null && other.getP_TIPO_CONTRATO()==null) || 
             (this.p_TIPO_CONTRATO!=null &&
              this.p_TIPO_CONTRATO.equals(other.getP_TIPO_CONTRATO()))) &&
            ((this.p_NUMERO_CONTRATO==null && other.getP_NUMERO_CONTRATO()==null) || 
             (this.p_NUMERO_CONTRATO!=null &&
              this.p_NUMERO_CONTRATO.equals(other.getP_NUMERO_CONTRATO())));
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
        if (getP_ID_SOLICITANTE() != null) {
            _hashCode += getP_ID_SOLICITANTE().hashCode();
        }
        if (getP_NUMERO_FACTURA() != null) {
            _hashCode += getP_NUMERO_FACTURA().hashCode();
        }
        if (getP_FECHA_FACTURA() != null) {
            _hashCode += getP_FECHA_FACTURA().hashCode();
        }
        if (getP_TIPO_CONTRATO() != null) {
            _hashCode += getP_TIPO_CONTRATO().hashCode();
        }
        if (getP_NUMERO_CONTRATO() != null) {
            _hashCode += getP_NUMERO_CONTRATO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoElementoEntradaconsultar_entrada_almacen.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/consultar_entrada_almacen", "tipoElementoEntradaconsultar_entrada_almacen"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_ID_SOLICITANTE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/consultar_entrada_almacen", "P_ID_SOLICITANTE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_NUMERO_FACTURA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/consultar_entrada_almacen", "P_NUMERO_FACTURA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_FECHA_FACTURA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/consultar_entrada_almacen", "P_FECHA_FACTURA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_TIPO_CONTRATO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/consultar_entrada_almacen", "P_TIPO_CONTRATO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_NUMERO_CONTRATO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/consultar_entrada_almacen", "P_NUMERO_CONTRATO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
