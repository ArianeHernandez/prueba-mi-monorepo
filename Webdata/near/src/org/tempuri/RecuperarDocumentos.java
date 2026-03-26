/**
 * RecuperarDocumentos.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class RecuperarDocumentos  implements java.io.Serializable {
    private java.lang.String refBase;

    private java.lang.Integer idTerminoBiblioteca;

    private java.lang.String numeroRadicado;

    private java.lang.Boolean todos;

    public RecuperarDocumentos() {
    }

    public RecuperarDocumentos(
           java.lang.String refBase,
           java.lang.Integer idTerminoBiblioteca,
           java.lang.String numeroRadicado,
           java.lang.Boolean todos) {
           this.refBase = refBase;
           this.idTerminoBiblioteca = idTerminoBiblioteca;
           this.numeroRadicado = numeroRadicado;
           this.todos = todos;
    }


    /**
     * Gets the refBase value for this RecuperarDocumentos.
     * 
     * @return refBase
     */
    public java.lang.String getRefBase() {
        return refBase;
    }


    /**
     * Sets the refBase value for this RecuperarDocumentos.
     * 
     * @param refBase
     */
    public void setRefBase(java.lang.String refBase) {
        this.refBase = refBase;
    }


    /**
     * Gets the idTerminoBiblioteca value for this RecuperarDocumentos.
     * 
     * @return idTerminoBiblioteca
     */
    public java.lang.Integer getIdTerminoBiblioteca() {
        return idTerminoBiblioteca;
    }


    /**
     * Sets the idTerminoBiblioteca value for this RecuperarDocumentos.
     * 
     * @param idTerminoBiblioteca
     */
    public void setIdTerminoBiblioteca(java.lang.Integer idTerminoBiblioteca) {
        this.idTerminoBiblioteca = idTerminoBiblioteca;
    }


    /**
     * Gets the numeroRadicado value for this RecuperarDocumentos.
     * 
     * @return numeroRadicado
     */
    public java.lang.String getNumeroRadicado() {
        return numeroRadicado;
    }


    /**
     * Sets the numeroRadicado value for this RecuperarDocumentos.
     * 
     * @param numeroRadicado
     */
    public void setNumeroRadicado(java.lang.String numeroRadicado) {
        this.numeroRadicado = numeroRadicado;
    }


    /**
     * Gets the todos value for this RecuperarDocumentos.
     * 
     * @return todos
     */
    public java.lang.Boolean getTodos() {
        return todos;
    }


    /**
     * Sets the todos value for this RecuperarDocumentos.
     * 
     * @param todos
     */
    public void setTodos(java.lang.Boolean todos) {
        this.todos = todos;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RecuperarDocumentos)) return false;
        RecuperarDocumentos other = (RecuperarDocumentos) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.refBase==null && other.getRefBase()==null) || 
             (this.refBase!=null &&
              this.refBase.equals(other.getRefBase()))) &&
            ((this.idTerminoBiblioteca==null && other.getIdTerminoBiblioteca()==null) || 
             (this.idTerminoBiblioteca!=null &&
              this.idTerminoBiblioteca.equals(other.getIdTerminoBiblioteca()))) &&
            ((this.numeroRadicado==null && other.getNumeroRadicado()==null) || 
             (this.numeroRadicado!=null &&
              this.numeroRadicado.equals(other.getNumeroRadicado()))) &&
            ((this.todos==null && other.getTodos()==null) || 
             (this.todos!=null &&
              this.todos.equals(other.getTodos())));
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
        if (getRefBase() != null) {
            _hashCode += getRefBase().hashCode();
        }
        if (getIdTerminoBiblioteca() != null) {
            _hashCode += getIdTerminoBiblioteca().hashCode();
        }
        if (getNumeroRadicado() != null) {
            _hashCode += getNumeroRadicado().hashCode();
        }
        if (getTodos() != null) {
            _hashCode += getTodos().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RecuperarDocumentos.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">RecuperarDocumentos"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("refBase");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "RefBase"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTerminoBiblioteca");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "IdTerminoBiblioteca"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroRadicado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "NumeroRadicado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("todos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Todos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
