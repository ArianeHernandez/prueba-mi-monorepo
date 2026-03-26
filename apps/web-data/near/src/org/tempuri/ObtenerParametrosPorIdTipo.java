/**
 * ObtenerParametrosPorIdTipo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class ObtenerParametrosPorIdTipo  implements java.io.Serializable {
    private java.lang.Integer idTipo;

    public ObtenerParametrosPorIdTipo() {
    }

    public ObtenerParametrosPorIdTipo(
           java.lang.Integer idTipo) {
           this.idTipo = idTipo;
    }


    /**
     * Gets the idTipo value for this ObtenerParametrosPorIdTipo.
     * 
     * @return idTipo
     */
    public java.lang.Integer getIdTipo() {
        return idTipo;
    }


    /**
     * Sets the idTipo value for this ObtenerParametrosPorIdTipo.
     * 
     * @param idTipo
     */
    public void setIdTipo(java.lang.Integer idTipo) {
        this.idTipo = idTipo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ObtenerParametrosPorIdTipo)) return false;
        ObtenerParametrosPorIdTipo other = (ObtenerParametrosPorIdTipo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idTipo==null && other.getIdTipo()==null) || 
             (this.idTipo!=null &&
              this.idTipo.equals(other.getIdTipo())));
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
        if (getIdTipo() != null) {
            _hashCode += getIdTipo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ObtenerParametrosPorIdTipo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">ObtenerParametrosPorIdTipo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTipo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "idTipo"));
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
