/**
 * TipoElementoSalidainformacionPersona.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.osmoautenticador.servicioweb.informacionPersona;

public class TipoElementoSalidainformacionPersona  implements java.io.Serializable {
    private java.lang.String segundo_apellido;

    private java.lang.String primer_apellido;

    private java.lang.String naturaleza;

    private java.lang.String correo;

    private java.lang.String segundo_nombre;

    private java.lang.String primer_nombre;

    private java.lang.String telefono;

    private java.lang.String numero_documento;

    public TipoElementoSalidainformacionPersona() {
    }

    public TipoElementoSalidainformacionPersona(
           java.lang.String segundo_apellido,
           java.lang.String primer_apellido,
           java.lang.String naturaleza,
           java.lang.String correo,
           java.lang.String segundo_nombre,
           java.lang.String primer_nombre,
           java.lang.String telefono,
           java.lang.String numero_documento) {
           this.segundo_apellido = segundo_apellido;
           this.primer_apellido = primer_apellido;
           this.naturaleza = naturaleza;
           this.correo = correo;
           this.segundo_nombre = segundo_nombre;
           this.primer_nombre = primer_nombre;
           this.telefono = telefono;
           this.numero_documento = numero_documento;
    }


    /**
     * Gets the segundo_apellido value for this TipoElementoSalidainformacionPersona.
     * 
     * @return segundo_apellido
     */
    public java.lang.String getSegundo_apellido() {
        return segundo_apellido;
    }


    /**
     * Sets the segundo_apellido value for this TipoElementoSalidainformacionPersona.
     * 
     * @param segundo_apellido
     */
    public void setSegundo_apellido(java.lang.String segundo_apellido) {
        this.segundo_apellido = segundo_apellido;
    }


    /**
     * Gets the primer_apellido value for this TipoElementoSalidainformacionPersona.
     * 
     * @return primer_apellido
     */
    public java.lang.String getPrimer_apellido() {
        return primer_apellido;
    }


    /**
     * Sets the primer_apellido value for this TipoElementoSalidainformacionPersona.
     * 
     * @param primer_apellido
     */
    public void setPrimer_apellido(java.lang.String primer_apellido) {
        this.primer_apellido = primer_apellido;
    }


    /**
     * Gets the naturaleza value for this TipoElementoSalidainformacionPersona.
     * 
     * @return naturaleza
     */
    public java.lang.String getNaturaleza() {
        return naturaleza;
    }


    /**
     * Sets the naturaleza value for this TipoElementoSalidainformacionPersona.
     * 
     * @param naturaleza
     */
    public void setNaturaleza(java.lang.String naturaleza) {
        this.naturaleza = naturaleza;
    }


    /**
     * Gets the correo value for this TipoElementoSalidainformacionPersona.
     * 
     * @return correo
     */
    public java.lang.String getCorreo() {
        return correo;
    }


    /**
     * Sets the correo value for this TipoElementoSalidainformacionPersona.
     * 
     * @param correo
     */
    public void setCorreo(java.lang.String correo) {
        this.correo = correo;
    }


    /**
     * Gets the segundo_nombre value for this TipoElementoSalidainformacionPersona.
     * 
     * @return segundo_nombre
     */
    public java.lang.String getSegundo_nombre() {
        return segundo_nombre;
    }


    /**
     * Sets the segundo_nombre value for this TipoElementoSalidainformacionPersona.
     * 
     * @param segundo_nombre
     */
    public void setSegundo_nombre(java.lang.String segundo_nombre) {
        this.segundo_nombre = segundo_nombre;
    }


    /**
     * Gets the primer_nombre value for this TipoElementoSalidainformacionPersona.
     * 
     * @return primer_nombre
     */
    public java.lang.String getPrimer_nombre() {
        return primer_nombre;
    }


    /**
     * Sets the primer_nombre value for this TipoElementoSalidainformacionPersona.
     * 
     * @param primer_nombre
     */
    public void setPrimer_nombre(java.lang.String primer_nombre) {
        this.primer_nombre = primer_nombre;
    }


    /**
     * Gets the telefono value for this TipoElementoSalidainformacionPersona.
     * 
     * @return telefono
     */
    public java.lang.String getTelefono() {
        return telefono;
    }


    /**
     * Sets the telefono value for this TipoElementoSalidainformacionPersona.
     * 
     * @param telefono
     */
    public void setTelefono(java.lang.String telefono) {
        this.telefono = telefono;
    }


    /**
     * Gets the numero_documento value for this TipoElementoSalidainformacionPersona.
     * 
     * @return numero_documento
     */
    public java.lang.String getNumero_documento() {
        return numero_documento;
    }


    /**
     * Sets the numero_documento value for this TipoElementoSalidainformacionPersona.
     * 
     * @param numero_documento
     */
    public void setNumero_documento(java.lang.String numero_documento) {
        this.numero_documento = numero_documento;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoElementoSalidainformacionPersona)) return false;
        TipoElementoSalidainformacionPersona other = (TipoElementoSalidainformacionPersona) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.segundo_apellido==null && other.getSegundo_apellido()==null) || 
             (this.segundo_apellido!=null &&
              this.segundo_apellido.equals(other.getSegundo_apellido()))) &&
            ((this.primer_apellido==null && other.getPrimer_apellido()==null) || 
             (this.primer_apellido!=null &&
              this.primer_apellido.equals(other.getPrimer_apellido()))) &&
            ((this.naturaleza==null && other.getNaturaleza()==null) || 
             (this.naturaleza!=null &&
              this.naturaleza.equals(other.getNaturaleza()))) &&
            ((this.correo==null && other.getCorreo()==null) || 
             (this.correo!=null &&
              this.correo.equals(other.getCorreo()))) &&
            ((this.segundo_nombre==null && other.getSegundo_nombre()==null) || 
             (this.segundo_nombre!=null &&
              this.segundo_nombre.equals(other.getSegundo_nombre()))) &&
            ((this.primer_nombre==null && other.getPrimer_nombre()==null) || 
             (this.primer_nombre!=null &&
              this.primer_nombre.equals(other.getPrimer_nombre()))) &&
            ((this.telefono==null && other.getTelefono()==null) || 
             (this.telefono!=null &&
              this.telefono.equals(other.getTelefono()))) &&
            ((this.numero_documento==null && other.getNumero_documento()==null) || 
             (this.numero_documento!=null &&
              this.numero_documento.equals(other.getNumero_documento())));
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
        if (getSegundo_apellido() != null) {
            _hashCode += getSegundo_apellido().hashCode();
        }
        if (getPrimer_apellido() != null) {
            _hashCode += getPrimer_apellido().hashCode();
        }
        if (getNaturaleza() != null) {
            _hashCode += getNaturaleza().hashCode();
        }
        if (getCorreo() != null) {
            _hashCode += getCorreo().hashCode();
        }
        if (getSegundo_nombre() != null) {
            _hashCode += getSegundo_nombre().hashCode();
        }
        if (getPrimer_nombre() != null) {
            _hashCode += getPrimer_nombre().hashCode();
        }
        if (getTelefono() != null) {
            _hashCode += getTelefono().hashCode();
        }
        if (getNumero_documento() != null) {
            _hashCode += getNumero_documento().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoElementoSalidainformacionPersona.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/informacionPersona", "tipoElementoSalidainformacionPersona"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("segundo_apellido");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/informacionPersona", "segundo_apellido"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("primer_apellido");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/informacionPersona", "primer_apellido"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("naturaleza");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/informacionPersona", "naturaleza"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("correo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/informacionPersona", "correo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("segundo_nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/informacionPersona", "segundo_nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("primer_nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/informacionPersona", "primer_nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("telefono");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/informacionPersona", "telefono"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numero_documento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/informacionPersona", "numero_documento"));
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
