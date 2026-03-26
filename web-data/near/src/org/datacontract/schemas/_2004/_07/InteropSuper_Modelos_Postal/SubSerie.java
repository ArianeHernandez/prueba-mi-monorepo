/**
 * SubSerie.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal;

public class SubSerie  implements java.io.Serializable {
    private java.lang.String actoAdministrativo;

    private java.lang.String caracteristicas;

    private java.lang.Integer codigoSubSerie;

    private java.lang.String estado;

    private java.lang.String motivoCreacion;

    private java.lang.String nombreCuaderno;

    private java.lang.String nombreSubSerie;

    private java.lang.String observaciones;

    public SubSerie() {
    }

    public SubSerie(
           java.lang.String actoAdministrativo,
           java.lang.String caracteristicas,
           java.lang.Integer codigoSubSerie,
           java.lang.String estado,
           java.lang.String motivoCreacion,
           java.lang.String nombreCuaderno,
           java.lang.String nombreSubSerie,
           java.lang.String observaciones) {
           this.actoAdministrativo = actoAdministrativo;
           this.caracteristicas = caracteristicas;
           this.codigoSubSerie = codigoSubSerie;
           this.estado = estado;
           this.motivoCreacion = motivoCreacion;
           this.nombreCuaderno = nombreCuaderno;
           this.nombreSubSerie = nombreSubSerie;
           this.observaciones = observaciones;
    }


    /**
     * Gets the actoAdministrativo value for this SubSerie.
     * 
     * @return actoAdministrativo
     */
    public java.lang.String getActoAdministrativo() {
        return actoAdministrativo;
    }


    /**
     * Sets the actoAdministrativo value for this SubSerie.
     * 
     * @param actoAdministrativo
     */
    public void setActoAdministrativo(java.lang.String actoAdministrativo) {
        this.actoAdministrativo = actoAdministrativo;
    }


    /**
     * Gets the caracteristicas value for this SubSerie.
     * 
     * @return caracteristicas
     */
    public java.lang.String getCaracteristicas() {
        return caracteristicas;
    }


    /**
     * Sets the caracteristicas value for this SubSerie.
     * 
     * @param caracteristicas
     */
    public void setCaracteristicas(java.lang.String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }


    /**
     * Gets the codigoSubSerie value for this SubSerie.
     * 
     * @return codigoSubSerie
     */
    public java.lang.Integer getCodigoSubSerie() {
        return codigoSubSerie;
    }


    /**
     * Sets the codigoSubSerie value for this SubSerie.
     * 
     * @param codigoSubSerie
     */
    public void setCodigoSubSerie(java.lang.Integer codigoSubSerie) {
        this.codigoSubSerie = codigoSubSerie;
    }


    /**
     * Gets the estado value for this SubSerie.
     * 
     * @return estado
     */
    public java.lang.String getEstado() {
        return estado;
    }


    /**
     * Sets the estado value for this SubSerie.
     * 
     * @param estado
     */
    public void setEstado(java.lang.String estado) {
        this.estado = estado;
    }


    /**
     * Gets the motivoCreacion value for this SubSerie.
     * 
     * @return motivoCreacion
     */
    public java.lang.String getMotivoCreacion() {
        return motivoCreacion;
    }


    /**
     * Sets the motivoCreacion value for this SubSerie.
     * 
     * @param motivoCreacion
     */
    public void setMotivoCreacion(java.lang.String motivoCreacion) {
        this.motivoCreacion = motivoCreacion;
    }


    /**
     * Gets the nombreCuaderno value for this SubSerie.
     * 
     * @return nombreCuaderno
     */
    public java.lang.String getNombreCuaderno() {
        return nombreCuaderno;
    }


    /**
     * Sets the nombreCuaderno value for this SubSerie.
     * 
     * @param nombreCuaderno
     */
    public void setNombreCuaderno(java.lang.String nombreCuaderno) {
        this.nombreCuaderno = nombreCuaderno;
    }


    /**
     * Gets the nombreSubSerie value for this SubSerie.
     * 
     * @return nombreSubSerie
     */
    public java.lang.String getNombreSubSerie() {
        return nombreSubSerie;
    }


    /**
     * Sets the nombreSubSerie value for this SubSerie.
     * 
     * @param nombreSubSerie
     */
    public void setNombreSubSerie(java.lang.String nombreSubSerie) {
        this.nombreSubSerie = nombreSubSerie;
    }


    /**
     * Gets the observaciones value for this SubSerie.
     * 
     * @return observaciones
     */
    public java.lang.String getObservaciones() {
        return observaciones;
    }


    /**
     * Sets the observaciones value for this SubSerie.
     * 
     * @param observaciones
     */
    public void setObservaciones(java.lang.String observaciones) {
        this.observaciones = observaciones;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SubSerie)) return false;
        SubSerie other = (SubSerie) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.actoAdministrativo==null && other.getActoAdministrativo()==null) || 
             (this.actoAdministrativo!=null &&
              this.actoAdministrativo.equals(other.getActoAdministrativo()))) &&
            ((this.caracteristicas==null && other.getCaracteristicas()==null) || 
             (this.caracteristicas!=null &&
              this.caracteristicas.equals(other.getCaracteristicas()))) &&
            ((this.codigoSubSerie==null && other.getCodigoSubSerie()==null) || 
             (this.codigoSubSerie!=null &&
              this.codigoSubSerie.equals(other.getCodigoSubSerie()))) &&
            ((this.estado==null && other.getEstado()==null) || 
             (this.estado!=null &&
              this.estado.equals(other.getEstado()))) &&
            ((this.motivoCreacion==null && other.getMotivoCreacion()==null) || 
             (this.motivoCreacion!=null &&
              this.motivoCreacion.equals(other.getMotivoCreacion()))) &&
            ((this.nombreCuaderno==null && other.getNombreCuaderno()==null) || 
             (this.nombreCuaderno!=null &&
              this.nombreCuaderno.equals(other.getNombreCuaderno()))) &&
            ((this.nombreSubSerie==null && other.getNombreSubSerie()==null) || 
             (this.nombreSubSerie!=null &&
              this.nombreSubSerie.equals(other.getNombreSubSerie()))) &&
            ((this.observaciones==null && other.getObservaciones()==null) || 
             (this.observaciones!=null &&
              this.observaciones.equals(other.getObservaciones())));
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
        if (getActoAdministrativo() != null) {
            _hashCode += getActoAdministrativo().hashCode();
        }
        if (getCaracteristicas() != null) {
            _hashCode += getCaracteristicas().hashCode();
        }
        if (getCodigoSubSerie() != null) {
            _hashCode += getCodigoSubSerie().hashCode();
        }
        if (getEstado() != null) {
            _hashCode += getEstado().hashCode();
        }
        if (getMotivoCreacion() != null) {
            _hashCode += getMotivoCreacion().hashCode();
        }
        if (getNombreCuaderno() != null) {
            _hashCode += getNombreCuaderno().hashCode();
        }
        if (getNombreSubSerie() != null) {
            _hashCode += getNombreSubSerie().hashCode();
        }
        if (getObservaciones() != null) {
            _hashCode += getObservaciones().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SubSerie.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "SubSerie"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("actoAdministrativo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "actoAdministrativo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("caracteristicas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "caracteristicas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoSubSerie");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "codigoSubSerie"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "estado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("motivoCreacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "motivoCreacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreCuaderno");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "nombreCuaderno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreSubSerie");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "nombreSubSerie"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("observaciones");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "observaciones"));
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
