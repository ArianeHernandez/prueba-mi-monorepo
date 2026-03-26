/**
 * RadicacionDatosBasicos.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.InteropSuper_Modelos_Entidades;

public class RadicacionDatosBasicos  implements java.io.Serializable {
    private java.util.Calendar fechaCreacion;

    private java.lang.Integer folios;

    private java.lang.Long idDependencia;

    private java.lang.String identificacion;

    private java.lang.String nombreDependencia;

    private java.lang.String nombreEntidad;

    private java.lang.Long tipoIdentificacion;

    public RadicacionDatosBasicos() {
    }

    public RadicacionDatosBasicos(
           java.util.Calendar fechaCreacion,
           java.lang.Integer folios,
           java.lang.Long idDependencia,
           java.lang.String identificacion,
           java.lang.String nombreDependencia,
           java.lang.String nombreEntidad,
           java.lang.Long tipoIdentificacion) {
           this.fechaCreacion = fechaCreacion;
           this.folios = folios;
           this.idDependencia = idDependencia;
           this.identificacion = identificacion;
           this.nombreDependencia = nombreDependencia;
           this.nombreEntidad = nombreEntidad;
           this.tipoIdentificacion = tipoIdentificacion;
    }


    /**
     * Gets the fechaCreacion value for this RadicacionDatosBasicos.
     * 
     * @return fechaCreacion
     */
    public java.util.Calendar getFechaCreacion() {
        return fechaCreacion;
    }


    /**
     * Sets the fechaCreacion value for this RadicacionDatosBasicos.
     * 
     * @param fechaCreacion
     */
    public void setFechaCreacion(java.util.Calendar fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }


    /**
     * Gets the folios value for this RadicacionDatosBasicos.
     * 
     * @return folios
     */
    public java.lang.Integer getFolios() {
        return folios;
    }


    /**
     * Sets the folios value for this RadicacionDatosBasicos.
     * 
     * @param folios
     */
    public void setFolios(java.lang.Integer folios) {
        this.folios = folios;
    }


    /**
     * Gets the idDependencia value for this RadicacionDatosBasicos.
     * 
     * @return idDependencia
     */
    public java.lang.Long getIdDependencia() {
        return idDependencia;
    }


    /**
     * Sets the idDependencia value for this RadicacionDatosBasicos.
     * 
     * @param idDependencia
     */
    public void setIdDependencia(java.lang.Long idDependencia) {
        this.idDependencia = idDependencia;
    }


    /**
     * Gets the identificacion value for this RadicacionDatosBasicos.
     * 
     * @return identificacion
     */
    public java.lang.String getIdentificacion() {
        return identificacion;
    }


    /**
     * Sets the identificacion value for this RadicacionDatosBasicos.
     * 
     * @param identificacion
     */
    public void setIdentificacion(java.lang.String identificacion) {
        this.identificacion = identificacion;
    }


    /**
     * Gets the nombreDependencia value for this RadicacionDatosBasicos.
     * 
     * @return nombreDependencia
     */
    public java.lang.String getNombreDependencia() {
        return nombreDependencia;
    }


    /**
     * Sets the nombreDependencia value for this RadicacionDatosBasicos.
     * 
     * @param nombreDependencia
     */
    public void setNombreDependencia(java.lang.String nombreDependencia) {
        this.nombreDependencia = nombreDependencia;
    }


    /**
     * Gets the nombreEntidad value for this RadicacionDatosBasicos.
     * 
     * @return nombreEntidad
     */
    public java.lang.String getNombreEntidad() {
        return nombreEntidad;
    }


    /**
     * Sets the nombreEntidad value for this RadicacionDatosBasicos.
     * 
     * @param nombreEntidad
     */
    public void setNombreEntidad(java.lang.String nombreEntidad) {
        this.nombreEntidad = nombreEntidad;
    }


    /**
     * Gets the tipoIdentificacion value for this RadicacionDatosBasicos.
     * 
     * @return tipoIdentificacion
     */
    public java.lang.Long getTipoIdentificacion() {
        return tipoIdentificacion;
    }


    /**
     * Sets the tipoIdentificacion value for this RadicacionDatosBasicos.
     * 
     * @param tipoIdentificacion
     */
    public void setTipoIdentificacion(java.lang.Long tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RadicacionDatosBasicos)) return false;
        RadicacionDatosBasicos other = (RadicacionDatosBasicos) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fechaCreacion==null && other.getFechaCreacion()==null) || 
             (this.fechaCreacion!=null &&
              this.fechaCreacion.equals(other.getFechaCreacion()))) &&
            ((this.folios==null && other.getFolios()==null) || 
             (this.folios!=null &&
              this.folios.equals(other.getFolios()))) &&
            ((this.idDependencia==null && other.getIdDependencia()==null) || 
             (this.idDependencia!=null &&
              this.idDependencia.equals(other.getIdDependencia()))) &&
            ((this.identificacion==null && other.getIdentificacion()==null) || 
             (this.identificacion!=null &&
              this.identificacion.equals(other.getIdentificacion()))) &&
            ((this.nombreDependencia==null && other.getNombreDependencia()==null) || 
             (this.nombreDependencia!=null &&
              this.nombreDependencia.equals(other.getNombreDependencia()))) &&
            ((this.nombreEntidad==null && other.getNombreEntidad()==null) || 
             (this.nombreEntidad!=null &&
              this.nombreEntidad.equals(other.getNombreEntidad()))) &&
            ((this.tipoIdentificacion==null && other.getTipoIdentificacion()==null) || 
             (this.tipoIdentificacion!=null &&
              this.tipoIdentificacion.equals(other.getTipoIdentificacion())));
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
        if (getFechaCreacion() != null) {
            _hashCode += getFechaCreacion().hashCode();
        }
        if (getFolios() != null) {
            _hashCode += getFolios().hashCode();
        }
        if (getIdDependencia() != null) {
            _hashCode += getIdDependencia().hashCode();
        }
        if (getIdentificacion() != null) {
            _hashCode += getIdentificacion().hashCode();
        }
        if (getNombreDependencia() != null) {
            _hashCode += getNombreDependencia().hashCode();
        }
        if (getNombreEntidad() != null) {
            _hashCode += getNombreEntidad().hashCode();
        }
        if (getTipoIdentificacion() != null) {
            _hashCode += getTipoIdentificacion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RadicacionDatosBasicos.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Entidades", "RadicacionDatosBasicos"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaCreacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Entidades", "FechaCreacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("folios");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Entidades", "Folios"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idDependencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Entidades", "IdDependencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Entidades", "Identificacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreDependencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Entidades", "NombreDependencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreEntidad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Entidades", "NombreEntidad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoIdentificacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Entidades", "TipoIdentificacion"));
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
