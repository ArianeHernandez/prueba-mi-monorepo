/**
 * RolArrayElement.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario;

public class RolArrayElement  implements java.io.Serializable {
    private java.lang.Integer r_eliminados;

    public RolArrayElement() {
    }

    public RolArrayElement(
           java.lang.Integer r_eliminados) {
           this.r_eliminados = r_eliminados;
    }


    /**
     * Gets the r_eliminados value for this RolArrayElement.
     * 
     * @return r_eliminados
     */
    public java.lang.Integer getR_eliminados() {
        return r_eliminados;
    }


    /**
     * Sets the r_eliminados value for this RolArrayElement.
     * 
     * @param r_eliminados
     */
    public void setR_eliminados(java.lang.Integer r_eliminados) {
        this.r_eliminados = r_eliminados;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RolArrayElement)) return false;
        RolArrayElement other = (RolArrayElement) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.r_eliminados==null && other.getR_eliminados()==null) || 
             (this.r_eliminados!=null &&
              this.r_eliminados.equals(other.getR_eliminados())));
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
        if (getR_eliminados() != null) {
            _hashCode += getR_eliminados().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RolArrayElement.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "rolArrayElement"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("r_eliminados");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/RolesUsuario", "r_eliminados"));
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
