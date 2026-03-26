/**
 * TipoElementoSalidasp_act_movimientos.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.datasuite.webdata.correval.webservice.sp_act_movimientos;

public class TipoElementoSalidasp_act_movimientos  implements java.io.Serializable {
    private int p_NUMERO;

    private java.lang.String OBJ_MENSAJE;

    private int OBJ_CODIGO;

    public TipoElementoSalidasp_act_movimientos() {
    }

    public TipoElementoSalidasp_act_movimientos(
           int p_NUMERO,
           java.lang.String OBJ_MENSAJE,
           int OBJ_CODIGO) {
           this.p_NUMERO = p_NUMERO;
           this.OBJ_MENSAJE = OBJ_MENSAJE;
           this.OBJ_CODIGO = OBJ_CODIGO;
    }


    /**
     * Gets the p_NUMERO value for this TipoElementoSalidasp_act_movimientos.
     * 
     * @return p_NUMERO
     */
    public int getP_NUMERO() {
        return p_NUMERO;
    }


    /**
     * Sets the p_NUMERO value for this TipoElementoSalidasp_act_movimientos.
     * 
     * @param p_NUMERO
     */
    public void setP_NUMERO(int p_NUMERO) {
        this.p_NUMERO = p_NUMERO;
    }


    /**
     * Gets the OBJ_MENSAJE value for this TipoElementoSalidasp_act_movimientos.
     * 
     * @return OBJ_MENSAJE
     */
    public java.lang.String getOBJ_MENSAJE() {
        return OBJ_MENSAJE;
    }


    /**
     * Sets the OBJ_MENSAJE value for this TipoElementoSalidasp_act_movimientos.
     * 
     * @param OBJ_MENSAJE
     */
    public void setOBJ_MENSAJE(java.lang.String OBJ_MENSAJE) {
        this.OBJ_MENSAJE = OBJ_MENSAJE;
    }


    /**
     * Gets the OBJ_CODIGO value for this TipoElementoSalidasp_act_movimientos.
     * 
     * @return OBJ_CODIGO
     */
    public int getOBJ_CODIGO() {
        return OBJ_CODIGO;
    }


    /**
     * Sets the OBJ_CODIGO value for this TipoElementoSalidasp_act_movimientos.
     * 
     * @param OBJ_CODIGO
     */
    public void setOBJ_CODIGO(int OBJ_CODIGO) {
        this.OBJ_CODIGO = OBJ_CODIGO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoElementoSalidasp_act_movimientos)) return false;
        TipoElementoSalidasp_act_movimientos other = (TipoElementoSalidasp_act_movimientos) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.p_NUMERO == other.getP_NUMERO() &&
            ((this.OBJ_MENSAJE==null && other.getOBJ_MENSAJE()==null) || 
             (this.OBJ_MENSAJE!=null &&
              this.OBJ_MENSAJE.equals(other.getOBJ_MENSAJE()))) &&
            this.OBJ_CODIGO == other.getOBJ_CODIGO();
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
        _hashCode += getP_NUMERO();
        if (getOBJ_MENSAJE() != null) {
            _hashCode += getOBJ_MENSAJE().hashCode();
        }
        _hashCode += getOBJ_CODIGO();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoElementoSalidasp_act_movimientos.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/sp_act_movimientos", "tipoElementoSalidasp_act_movimientos"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_NUMERO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/sp_act_movimientos", "P_NUMERO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OBJ_MENSAJE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/sp_act_movimientos", "OBJ_MENSAJE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OBJ_CODIGO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/sp_act_movimientos", "OBJ_CODIGO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
