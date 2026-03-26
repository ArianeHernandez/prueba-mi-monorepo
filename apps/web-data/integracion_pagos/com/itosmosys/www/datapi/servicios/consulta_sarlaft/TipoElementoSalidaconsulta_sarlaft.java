/**
 * TipoElementoSalidaconsulta_sarlaft.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.itosmosys.www.datapi.servicios.consulta_sarlaft;

public class TipoElementoSalidaconsulta_sarlaft  implements java.io.Serializable {
    private com.itosmosys.www.datapi.servicios.consulta_sarlaft.LISTASArrayElement[] LISTAS;

    public TipoElementoSalidaconsulta_sarlaft() {
    }

    public TipoElementoSalidaconsulta_sarlaft(
           com.itosmosys.www.datapi.servicios.consulta_sarlaft.LISTASArrayElement[] LISTAS) {
           this.LISTAS = LISTAS;
    }


    /**
     * Gets the LISTAS value for this TipoElementoSalidaconsulta_sarlaft.
     * 
     * @return LISTAS
     */
    public com.itosmosys.www.datapi.servicios.consulta_sarlaft.LISTASArrayElement[] getLISTAS() {
        return LISTAS;
    }


    /**
     * Sets the LISTAS value for this TipoElementoSalidaconsulta_sarlaft.
     * 
     * @param LISTAS
     */
    public void setLISTAS(com.itosmosys.www.datapi.servicios.consulta_sarlaft.LISTASArrayElement[] LISTAS) {
        this.LISTAS = LISTAS;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoElementoSalidaconsulta_sarlaft)) return false;
        TipoElementoSalidaconsulta_sarlaft other = (TipoElementoSalidaconsulta_sarlaft) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.LISTAS==null && other.getLISTAS()==null) || 
             (this.LISTAS!=null &&
              java.util.Arrays.equals(this.LISTAS, other.getLISTAS())));
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
        if (getLISTAS() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getLISTAS());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getLISTAS(), i);
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
        new org.apache.axis.description.TypeDesc(TipoElementoSalidaconsulta_sarlaft.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/consulta_sarlaft", "tipoElementoSalidaconsulta_sarlaft"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LISTAS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/consulta_sarlaft", "LISTAS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/consulta_sarlaft", "LISTASArrayElement"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/consulta_sarlaft", "LISTASElemento"));
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
