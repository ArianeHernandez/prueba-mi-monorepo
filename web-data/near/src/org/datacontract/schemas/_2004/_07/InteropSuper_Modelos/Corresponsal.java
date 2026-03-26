/**
 * Corresponsal.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.InteropSuper_Modelos;

public class Corresponsal  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.Dependencia dependencia;

    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.Particular particular;

    private java.lang.String tipo;

    public Corresponsal() {
    }

    public Corresponsal(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.Dependencia dependencia,
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.Particular particular,
           java.lang.String tipo) {
           this.dependencia = dependencia;
           this.particular = particular;
           this.tipo = tipo;
    }


    /**
     * Gets the dependencia value for this Corresponsal.
     * 
     * @return dependencia
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Dependencia getDependencia() {
        return dependencia;
    }


    /**
     * Sets the dependencia value for this Corresponsal.
     * 
     * @param dependencia
     */
    public void setDependencia(org.datacontract.schemas._2004._07.InteropSuper_Modelos.Dependencia dependencia) {
        this.dependencia = dependencia;
    }


    /**
     * Gets the particular value for this Corresponsal.
     * 
     * @return particular
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Particular getParticular() {
        return particular;
    }


    /**
     * Sets the particular value for this Corresponsal.
     * 
     * @param particular
     */
    public void setParticular(org.datacontract.schemas._2004._07.InteropSuper_Modelos.Particular particular) {
        this.particular = particular;
    }


    /**
     * Gets the tipo value for this Corresponsal.
     * 
     * @return tipo
     */
    public java.lang.String getTipo() {
        return tipo;
    }


    /**
     * Sets the tipo value for this Corresponsal.
     * 
     * @param tipo
     */
    public void setTipo(java.lang.String tipo) {
        this.tipo = tipo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Corresponsal)) return false;
        Corresponsal other = (Corresponsal) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.dependencia==null && other.getDependencia()==null) || 
             (this.dependencia!=null &&
              this.dependencia.equals(other.getDependencia()))) &&
            ((this.particular==null && other.getParticular()==null) || 
             (this.particular!=null &&
              this.particular.equals(other.getParticular()))) &&
            ((this.tipo==null && other.getTipo()==null) || 
             (this.tipo!=null &&
              this.tipo.equals(other.getTipo())));
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
        if (getDependencia() != null) {
            _hashCode += getDependencia().hashCode();
        }
        if (getParticular() != null) {
            _hashCode += getParticular().hashCode();
        }
        if (getTipo() != null) {
            _hashCode += getTipo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Corresponsal.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Corresponsal"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dependencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Dependencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Dependencia"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("particular");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Particular"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Particular"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Tipo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
