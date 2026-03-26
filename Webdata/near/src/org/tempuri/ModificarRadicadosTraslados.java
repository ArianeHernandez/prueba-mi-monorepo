/**
 * ModificarRadicadosTraslados.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class ModificarRadicadosTraslados  implements java.io.Serializable {
    private java.lang.Integer idProceso;

    public ModificarRadicadosTraslados() {
    }

    public ModificarRadicadosTraslados(
           java.lang.Integer idProceso) {
           this.idProceso = idProceso;
    }


    /**
     * Gets the idProceso value for this ModificarRadicadosTraslados.
     * 
     * @return idProceso
     */
    public java.lang.Integer getIdProceso() {
        return idProceso;
    }


    /**
     * Sets the idProceso value for this ModificarRadicadosTraslados.
     * 
     * @param idProceso
     */
    public void setIdProceso(java.lang.Integer idProceso) {
        this.idProceso = idProceso;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ModificarRadicadosTraslados)) return false;
        ModificarRadicadosTraslados other = (ModificarRadicadosTraslados) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idProceso==null && other.getIdProceso()==null) || 
             (this.idProceso!=null &&
              this.idProceso.equals(other.getIdProceso())));
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
        if (getIdProceso() != null) {
            _hashCode += getIdProceso().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ModificarRadicadosTraslados.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">ModificarRadicadosTraslados"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idProceso");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "idProceso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
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
