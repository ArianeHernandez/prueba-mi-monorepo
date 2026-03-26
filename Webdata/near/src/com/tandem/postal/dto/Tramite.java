/**
 * Tramite.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tandem.postal.dto;

public class Tramite  implements java.io.Serializable {
    private java.lang.Long codigo;

    private java.lang.Long id;

    private java.lang.String nombre;

    private com.tandem.postal.dto.Proceso proceso;

    private com.tandem.postal.dto.Termino termino;

    private com.microsoft.schemas._2003._10.Serialization.Arrays.ArrayOfKeyValueOfstringDocumentoTipoSeguridad8P61K7EwKeyValueOfstringDocumentoTipoSeguridad8P61K7Ew[] tipoSeguridad;

    public Tramite() {
    }

    public Tramite(
           java.lang.Long codigo,
           java.lang.Long id,
           java.lang.String nombre,
           com.tandem.postal.dto.Proceso proceso,
           com.tandem.postal.dto.Termino termino,
           com.microsoft.schemas._2003._10.Serialization.Arrays.ArrayOfKeyValueOfstringDocumentoTipoSeguridad8P61K7EwKeyValueOfstringDocumentoTipoSeguridad8P61K7Ew[] tipoSeguridad) {
           this.codigo = codigo;
           this.id = id;
           this.nombre = nombre;
           this.proceso = proceso;
           this.termino = termino;
           this.tipoSeguridad = tipoSeguridad;
    }


    /**
     * Gets the codigo value for this Tramite.
     * 
     * @return codigo
     */
    public java.lang.Long getCodigo() {
        return codigo;
    }


    /**
     * Sets the codigo value for this Tramite.
     * 
     * @param codigo
     */
    public void setCodigo(java.lang.Long codigo) {
        this.codigo = codigo;
    }


    /**
     * Gets the id value for this Tramite.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this Tramite.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the nombre value for this Tramite.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this Tramite.
     * 
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the proceso value for this Tramite.
     * 
     * @return proceso
     */
    public com.tandem.postal.dto.Proceso getProceso() {
        return proceso;
    }


    /**
     * Sets the proceso value for this Tramite.
     * 
     * @param proceso
     */
    public void setProceso(com.tandem.postal.dto.Proceso proceso) {
        this.proceso = proceso;
    }


    /**
     * Gets the termino value for this Tramite.
     * 
     * @return termino
     */
    public com.tandem.postal.dto.Termino getTermino() {
        return termino;
    }


    /**
     * Sets the termino value for this Tramite.
     * 
     * @param termino
     */
    public void setTermino(com.tandem.postal.dto.Termino termino) {
        this.termino = termino;
    }


    /**
     * Gets the tipoSeguridad value for this Tramite.
     * 
     * @return tipoSeguridad
     */
    public com.microsoft.schemas._2003._10.Serialization.Arrays.ArrayOfKeyValueOfstringDocumentoTipoSeguridad8P61K7EwKeyValueOfstringDocumentoTipoSeguridad8P61K7Ew[] getTipoSeguridad() {
        return tipoSeguridad;
    }


    /**
     * Sets the tipoSeguridad value for this Tramite.
     * 
     * @param tipoSeguridad
     */
    public void setTipoSeguridad(com.microsoft.schemas._2003._10.Serialization.Arrays.ArrayOfKeyValueOfstringDocumentoTipoSeguridad8P61K7EwKeyValueOfstringDocumentoTipoSeguridad8P61K7Ew[] tipoSeguridad) {
        this.tipoSeguridad = tipoSeguridad;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Tramite)) return false;
        Tramite other = (Tramite) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codigo==null && other.getCodigo()==null) || 
             (this.codigo!=null &&
              this.codigo.equals(other.getCodigo()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            ((this.proceso==null && other.getProceso()==null) || 
             (this.proceso!=null &&
              this.proceso.equals(other.getProceso()))) &&
            ((this.termino==null && other.getTermino()==null) || 
             (this.termino!=null &&
              this.termino.equals(other.getTermino()))) &&
            ((this.tipoSeguridad==null && other.getTipoSeguridad()==null) || 
             (this.tipoSeguridad!=null &&
              java.util.Arrays.equals(this.tipoSeguridad, other.getTipoSeguridad())));
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
        if (getCodigo() != null) {
            _hashCode += getCodigo().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        if (getProceso() != null) {
            _hashCode += getProceso().hashCode();
        }
        if (getTermino() != null) {
            _hashCode += getTermino().hashCode();
        }
        if (getTipoSeguridad() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTipoSeguridad());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTipoSeguridad(), i);
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
        new org.apache.axis.description.TypeDesc(Tramite.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "Tramite"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "Codigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "Id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "Nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("proceso");
        elemField.setXmlName(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "Proceso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "Proceso"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("termino");
        elemField.setXmlName(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "Termino"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "Termino"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoSeguridad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://postal.tandem.com/dto/", "TipoSeguridad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", ">ArrayOfKeyValueOfstringDocumentoTipoSeguridad8p61K7ew>KeyValueOfstringDocumentoTipoSeguridad8p61K7ew"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "KeyValueOfstringDocumentoTipoSeguridad8p61K7ew"));
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
