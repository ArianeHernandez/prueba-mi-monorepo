/**
 * TipoElementoSalidaaplicar_debito.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.itosmosys.www.datapi.servicios.aplicar_debito;

public class TipoElementoSalidaaplicar_debito  implements java.io.Serializable {
    private java.lang.String NUMERO_CONSECUTIVO;

    private java.lang.String DESCRIPCION_ERROR;

    private java.lang.String CODIGO_ERROR;

    public TipoElementoSalidaaplicar_debito() {
    }

    public TipoElementoSalidaaplicar_debito(
           java.lang.String NUMERO_CONSECUTIVO,
           java.lang.String DESCRIPCION_ERROR,
           java.lang.String CODIGO_ERROR) {
           this.NUMERO_CONSECUTIVO = NUMERO_CONSECUTIVO;
           this.DESCRIPCION_ERROR = DESCRIPCION_ERROR;
           this.CODIGO_ERROR = CODIGO_ERROR;
    }


    /**
     * Gets the NUMERO_CONSECUTIVO value for this TipoElementoSalidaaplicar_debito.
     * 
     * @return NUMERO_CONSECUTIVO
     */
    public java.lang.String getNUMERO_CONSECUTIVO() {
        return NUMERO_CONSECUTIVO;
    }


    /**
     * Sets the NUMERO_CONSECUTIVO value for this TipoElementoSalidaaplicar_debito.
     * 
     * @param NUMERO_CONSECUTIVO
     */
    public void setNUMERO_CONSECUTIVO(java.lang.String NUMERO_CONSECUTIVO) {
        this.NUMERO_CONSECUTIVO = NUMERO_CONSECUTIVO;
    }


    /**
     * Gets the DESCRIPCION_ERROR value for this TipoElementoSalidaaplicar_debito.
     * 
     * @return DESCRIPCION_ERROR
     */
    public java.lang.String getDESCRIPCION_ERROR() {
        return DESCRIPCION_ERROR;
    }


    /**
     * Sets the DESCRIPCION_ERROR value for this TipoElementoSalidaaplicar_debito.
     * 
     * @param DESCRIPCION_ERROR
     */
    public void setDESCRIPCION_ERROR(java.lang.String DESCRIPCION_ERROR) {
        this.DESCRIPCION_ERROR = DESCRIPCION_ERROR;
    }


    /**
     * Gets the CODIGO_ERROR value for this TipoElementoSalidaaplicar_debito.
     * 
     * @return CODIGO_ERROR
     */
    public java.lang.String getCODIGO_ERROR() {
        return CODIGO_ERROR;
    }


    /**
     * Sets the CODIGO_ERROR value for this TipoElementoSalidaaplicar_debito.
     * 
     * @param CODIGO_ERROR
     */
    public void setCODIGO_ERROR(java.lang.String CODIGO_ERROR) {
        this.CODIGO_ERROR = CODIGO_ERROR;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoElementoSalidaaplicar_debito)) return false;
        TipoElementoSalidaaplicar_debito other = (TipoElementoSalidaaplicar_debito) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.NUMERO_CONSECUTIVO==null && other.getNUMERO_CONSECUTIVO()==null) || 
             (this.NUMERO_CONSECUTIVO!=null &&
              this.NUMERO_CONSECUTIVO.equals(other.getNUMERO_CONSECUTIVO()))) &&
            ((this.DESCRIPCION_ERROR==null && other.getDESCRIPCION_ERROR()==null) || 
             (this.DESCRIPCION_ERROR!=null &&
              this.DESCRIPCION_ERROR.equals(other.getDESCRIPCION_ERROR()))) &&
            ((this.CODIGO_ERROR==null && other.getCODIGO_ERROR()==null) || 
             (this.CODIGO_ERROR!=null &&
              this.CODIGO_ERROR.equals(other.getCODIGO_ERROR())));
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
        if (getNUMERO_CONSECUTIVO() != null) {
            _hashCode += getNUMERO_CONSECUTIVO().hashCode();
        }
        if (getDESCRIPCION_ERROR() != null) {
            _hashCode += getDESCRIPCION_ERROR().hashCode();
        }
        if (getCODIGO_ERROR() != null) {
            _hashCode += getCODIGO_ERROR().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoElementoSalidaaplicar_debito.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/aplicar_debito", "tipoElementoSalidaaplicar_debito"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUMERO_CONSECUTIVO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/aplicar_debito", "NUMERO_CONSECUTIVO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DESCRIPCION_ERROR");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/aplicar_debito", "DESCRIPCION_ERROR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODIGO_ERROR");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/aplicar_debito", "CODIGO_ERROR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
