/**
 * TipoElementoEntradasp_act_movimientos.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.datasuite.webdata.correval.webservice.sp_act_movimientos;

public class TipoElementoEntradasp_act_movimientos  implements java.io.Serializable {
    private int p_PERIODO;

    private int p_LOTE;

    private java.lang.String p_TIPO;

    public TipoElementoEntradasp_act_movimientos() {
    }

    public TipoElementoEntradasp_act_movimientos(
           int p_PERIODO,
           int p_LOTE,
           java.lang.String p_TIPO) {
           this.p_PERIODO = p_PERIODO;
           this.p_LOTE = p_LOTE;
           this.p_TIPO = p_TIPO;
    }


    /**
     * Gets the p_PERIODO value for this TipoElementoEntradasp_act_movimientos.
     * 
     * @return p_PERIODO
     */
    public int getP_PERIODO() {
        return p_PERIODO;
    }


    /**
     * Sets the p_PERIODO value for this TipoElementoEntradasp_act_movimientos.
     * 
     * @param p_PERIODO
     */
    public void setP_PERIODO(int p_PERIODO) {
        this.p_PERIODO = p_PERIODO;
    }


    /**
     * Gets the p_LOTE value for this TipoElementoEntradasp_act_movimientos.
     * 
     * @return p_LOTE
     */
    public int getP_LOTE() {
        return p_LOTE;
    }


    /**
     * Sets the p_LOTE value for this TipoElementoEntradasp_act_movimientos.
     * 
     * @param p_LOTE
     */
    public void setP_LOTE(int p_LOTE) {
        this.p_LOTE = p_LOTE;
    }


    /**
     * Gets the p_TIPO value for this TipoElementoEntradasp_act_movimientos.
     * 
     * @return p_TIPO
     */
    public java.lang.String getP_TIPO() {
        return p_TIPO;
    }


    /**
     * Sets the p_TIPO value for this TipoElementoEntradasp_act_movimientos.
     * 
     * @param p_TIPO
     */
    public void setP_TIPO(java.lang.String p_TIPO) {
        this.p_TIPO = p_TIPO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoElementoEntradasp_act_movimientos)) return false;
        TipoElementoEntradasp_act_movimientos other = (TipoElementoEntradasp_act_movimientos) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.p_PERIODO == other.getP_PERIODO() &&
            this.p_LOTE == other.getP_LOTE() &&
            ((this.p_TIPO==null && other.getP_TIPO()==null) || 
             (this.p_TIPO!=null &&
              this.p_TIPO.equals(other.getP_TIPO())));
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
        _hashCode += getP_PERIODO();
        _hashCode += getP_LOTE();
        if (getP_TIPO() != null) {
            _hashCode += getP_TIPO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoElementoEntradasp_act_movimientos.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/sp_act_movimientos", "tipoElementoEntradasp_act_movimientos"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_PERIODO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/sp_act_movimientos", "P_PERIODO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_LOTE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/sp_act_movimientos", "P_LOTE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_TIPO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/sp_act_movimientos", "P_TIPO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
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
