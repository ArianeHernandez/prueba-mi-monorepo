/**
 * TipoEntradasp_act_movimientos.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.datasuite.webdata.correval.webservice.sp_act_movimientos;

public class TipoEntradasp_act_movimientos  implements java.io.Serializable {
    private com.osmosyscol.datasuite.webdata.correval.webservice.sp_act_movimientos.TipoElementoEntradasp_act_movimientos elementoEntrada;

    public TipoEntradasp_act_movimientos() {
    }

    public TipoEntradasp_act_movimientos(
           com.osmosyscol.datasuite.webdata.correval.webservice.sp_act_movimientos.TipoElementoEntradasp_act_movimientos elementoEntrada) {
           this.elementoEntrada = elementoEntrada;
    }


    /**
     * Gets the elementoEntrada value for this TipoEntradasp_act_movimientos.
     * 
     * @return elementoEntrada
     */
    public com.osmosyscol.datasuite.webdata.correval.webservice.sp_act_movimientos.TipoElementoEntradasp_act_movimientos getElementoEntrada() {
        return elementoEntrada;
    }


    /**
     * Sets the elementoEntrada value for this TipoEntradasp_act_movimientos.
     * 
     * @param elementoEntrada
     */
    public void setElementoEntrada(com.osmosyscol.datasuite.webdata.correval.webservice.sp_act_movimientos.TipoElementoEntradasp_act_movimientos elementoEntrada) {
        this.elementoEntrada = elementoEntrada;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoEntradasp_act_movimientos)) return false;
        TipoEntradasp_act_movimientos other = (TipoEntradasp_act_movimientos) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.elementoEntrada==null && other.getElementoEntrada()==null) || 
             (this.elementoEntrada!=null &&
              this.elementoEntrada.equals(other.getElementoEntrada())));
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
        if (getElementoEntrada() != null) {
            _hashCode += getElementoEntrada().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoEntradasp_act_movimientos.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/sp_act_movimientos", "tipoEntradasp_act_movimientos"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elementoEntrada");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/sp_act_movimientos", "elementoEntrada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/sp_act_movimientos", "tipoElementoEntradasp_act_movimientos"));
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
