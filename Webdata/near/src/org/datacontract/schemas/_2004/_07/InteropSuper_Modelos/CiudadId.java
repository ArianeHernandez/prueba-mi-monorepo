/**
 * CiudadId.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.InteropSuper_Modelos;

public class CiudadId  implements java.io.Serializable {
    private java.lang.Long ciudadCodigo;

    private java.lang.Long departamentoCodigo;

    private java.lang.Long paisCodigo;

    public CiudadId() {
    }

    public CiudadId(
           java.lang.Long ciudadCodigo,
           java.lang.Long departamentoCodigo,
           java.lang.Long paisCodigo) {
           this.ciudadCodigo = ciudadCodigo;
           this.departamentoCodigo = departamentoCodigo;
           this.paisCodigo = paisCodigo;
    }


    /**
     * Gets the ciudadCodigo value for this CiudadId.
     * 
     * @return ciudadCodigo
     */
    public java.lang.Long getCiudadCodigo() {
        return ciudadCodigo;
    }


    /**
     * Sets the ciudadCodigo value for this CiudadId.
     * 
     * @param ciudadCodigo
     */
    public void setCiudadCodigo(java.lang.Long ciudadCodigo) {
        this.ciudadCodigo = ciudadCodigo;
    }


    /**
     * Gets the departamentoCodigo value for this CiudadId.
     * 
     * @return departamentoCodigo
     */
    public java.lang.Long getDepartamentoCodigo() {
        return departamentoCodigo;
    }


    /**
     * Sets the departamentoCodigo value for this CiudadId.
     * 
     * @param departamentoCodigo
     */
    public void setDepartamentoCodigo(java.lang.Long departamentoCodigo) {
        this.departamentoCodigo = departamentoCodigo;
    }


    /**
     * Gets the paisCodigo value for this CiudadId.
     * 
     * @return paisCodigo
     */
    public java.lang.Long getPaisCodigo() {
        return paisCodigo;
    }


    /**
     * Sets the paisCodigo value for this CiudadId.
     * 
     * @param paisCodigo
     */
    public void setPaisCodigo(java.lang.Long paisCodigo) {
        this.paisCodigo = paisCodigo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CiudadId)) return false;
        CiudadId other = (CiudadId) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ciudadCodigo==null && other.getCiudadCodigo()==null) || 
             (this.ciudadCodigo!=null &&
              this.ciudadCodigo.equals(other.getCiudadCodigo()))) &&
            ((this.departamentoCodigo==null && other.getDepartamentoCodigo()==null) || 
             (this.departamentoCodigo!=null &&
              this.departamentoCodigo.equals(other.getDepartamentoCodigo()))) &&
            ((this.paisCodigo==null && other.getPaisCodigo()==null) || 
             (this.paisCodigo!=null &&
              this.paisCodigo.equals(other.getPaisCodigo())));
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
        if (getCiudadCodigo() != null) {
            _hashCode += getCiudadCodigo().hashCode();
        }
        if (getDepartamentoCodigo() != null) {
            _hashCode += getDepartamentoCodigo().hashCode();
        }
        if (getPaisCodigo() != null) {
            _hashCode += getPaisCodigo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CiudadId.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "CiudadId"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ciudadCodigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "CiudadCodigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("departamentoCodigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "DepartamentoCodigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paisCodigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "PaisCodigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
