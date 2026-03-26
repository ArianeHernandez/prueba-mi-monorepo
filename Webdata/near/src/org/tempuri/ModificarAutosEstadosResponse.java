/**
 * ModificarAutosEstadosResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class ModificarAutosEstadosResponse  implements java.io.Serializable {
    private java.lang.Integer modificarAutosEstadosResult;

    public ModificarAutosEstadosResponse() {
    }

    public ModificarAutosEstadosResponse(
           java.lang.Integer modificarAutosEstadosResult) {
           this.modificarAutosEstadosResult = modificarAutosEstadosResult;
    }


    /**
     * Gets the modificarAutosEstadosResult value for this ModificarAutosEstadosResponse.
     * 
     * @return modificarAutosEstadosResult
     */
    public java.lang.Integer getModificarAutosEstadosResult() {
        return modificarAutosEstadosResult;
    }


    /**
     * Sets the modificarAutosEstadosResult value for this ModificarAutosEstadosResponse.
     * 
     * @param modificarAutosEstadosResult
     */
    public void setModificarAutosEstadosResult(java.lang.Integer modificarAutosEstadosResult) {
        this.modificarAutosEstadosResult = modificarAutosEstadosResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ModificarAutosEstadosResponse)) return false;
        ModificarAutosEstadosResponse other = (ModificarAutosEstadosResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.modificarAutosEstadosResult==null && other.getModificarAutosEstadosResult()==null) || 
             (this.modificarAutosEstadosResult!=null &&
              this.modificarAutosEstadosResult.equals(other.getModificarAutosEstadosResult())));
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
        if (getModificarAutosEstadosResult() != null) {
            _hashCode += getModificarAutosEstadosResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ModificarAutosEstadosResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">ModificarAutosEstadosResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modificarAutosEstadosResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ModificarAutosEstadosResult"));
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
