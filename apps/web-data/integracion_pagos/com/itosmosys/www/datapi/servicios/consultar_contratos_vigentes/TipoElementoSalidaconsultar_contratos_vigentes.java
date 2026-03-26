/**
 * TipoElementoSalidaconsultar_contratos_vigentes.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.itosmosys.www.datapi.servicios.consultar_contratos_vigentes;

public class TipoElementoSalidaconsultar_contratos_vigentes  implements java.io.Serializable {
    private com.itosmosys.www.datapi.servicios.consultar_contratos_vigentes.R_CONTRATOSArrayElement[] r_CONTRATOS;

    public TipoElementoSalidaconsultar_contratos_vigentes() {
    }

    public TipoElementoSalidaconsultar_contratos_vigentes(
           com.itosmosys.www.datapi.servicios.consultar_contratos_vigentes.R_CONTRATOSArrayElement[] r_CONTRATOS) {
           this.r_CONTRATOS = r_CONTRATOS;
    }


    /**
     * Gets the r_CONTRATOS value for this TipoElementoSalidaconsultar_contratos_vigentes.
     * 
     * @return r_CONTRATOS
     */
    public com.itosmosys.www.datapi.servicios.consultar_contratos_vigentes.R_CONTRATOSArrayElement[] getR_CONTRATOS() {
        return r_CONTRATOS;
    }


    /**
     * Sets the r_CONTRATOS value for this TipoElementoSalidaconsultar_contratos_vigentes.
     * 
     * @param r_CONTRATOS
     */
    public void setR_CONTRATOS(com.itosmosys.www.datapi.servicios.consultar_contratos_vigentes.R_CONTRATOSArrayElement[] r_CONTRATOS) {
        this.r_CONTRATOS = r_CONTRATOS;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoElementoSalidaconsultar_contratos_vigentes)) return false;
        TipoElementoSalidaconsultar_contratos_vigentes other = (TipoElementoSalidaconsultar_contratos_vigentes) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.r_CONTRATOS==null && other.getR_CONTRATOS()==null) || 
             (this.r_CONTRATOS!=null &&
              java.util.Arrays.equals(this.r_CONTRATOS, other.getR_CONTRATOS())));
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
        if (getR_CONTRATOS() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getR_CONTRATOS());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getR_CONTRATOS(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoElementoSalidaconsultar_contratos_vigentes.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/consultar_contratos_vigentes", "tipoElementoSalidaconsultar_contratos_vigentes"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("r_CONTRATOS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/consultar_contratos_vigentes", "R_CONTRATOS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/consultar_contratos_vigentes", "R_CONTRATOSArrayElement"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/consultar_contratos_vigentes", "R_CONTRATOSElemento"));
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
