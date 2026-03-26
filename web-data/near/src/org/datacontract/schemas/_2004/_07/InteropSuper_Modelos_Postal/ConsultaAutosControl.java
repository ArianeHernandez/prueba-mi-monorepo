/**
 * ConsultaAutosControl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal;

public class ConsultaAutosControl  implements java.io.Serializable {
    private java.lang.String dependencia;

    private java.util.Calendar fechaFinal;

    private java.util.Calendar fechaInicial;

    private java.lang.String filtroConsulta;

    private java.lang.String radicado;

    public ConsultaAutosControl() {
    }

    public ConsultaAutosControl(
           java.lang.String dependencia,
           java.util.Calendar fechaFinal,
           java.util.Calendar fechaInicial,
           java.lang.String filtroConsulta,
           java.lang.String radicado) {
           this.dependencia = dependencia;
           this.fechaFinal = fechaFinal;
           this.fechaInicial = fechaInicial;
           this.filtroConsulta = filtroConsulta;
           this.radicado = radicado;
    }


    /**
     * Gets the dependencia value for this ConsultaAutosControl.
     * 
     * @return dependencia
     */
    public java.lang.String getDependencia() {
        return dependencia;
    }


    /**
     * Sets the dependencia value for this ConsultaAutosControl.
     * 
     * @param dependencia
     */
    public void setDependencia(java.lang.String dependencia) {
        this.dependencia = dependencia;
    }


    /**
     * Gets the fechaFinal value for this ConsultaAutosControl.
     * 
     * @return fechaFinal
     */
    public java.util.Calendar getFechaFinal() {
        return fechaFinal;
    }


    /**
     * Sets the fechaFinal value for this ConsultaAutosControl.
     * 
     * @param fechaFinal
     */
    public void setFechaFinal(java.util.Calendar fechaFinal) {
        this.fechaFinal = fechaFinal;
    }


    /**
     * Gets the fechaInicial value for this ConsultaAutosControl.
     * 
     * @return fechaInicial
     */
    public java.util.Calendar getFechaInicial() {
        return fechaInicial;
    }


    /**
     * Sets the fechaInicial value for this ConsultaAutosControl.
     * 
     * @param fechaInicial
     */
    public void setFechaInicial(java.util.Calendar fechaInicial) {
        this.fechaInicial = fechaInicial;
    }


    /**
     * Gets the filtroConsulta value for this ConsultaAutosControl.
     * 
     * @return filtroConsulta
     */
    public java.lang.String getFiltroConsulta() {
        return filtroConsulta;
    }


    /**
     * Sets the filtroConsulta value for this ConsultaAutosControl.
     * 
     * @param filtroConsulta
     */
    public void setFiltroConsulta(java.lang.String filtroConsulta) {
        this.filtroConsulta = filtroConsulta;
    }


    /**
     * Gets the radicado value for this ConsultaAutosControl.
     * 
     * @return radicado
     */
    public java.lang.String getRadicado() {
        return radicado;
    }


    /**
     * Sets the radicado value for this ConsultaAutosControl.
     * 
     * @param radicado
     */
    public void setRadicado(java.lang.String radicado) {
        this.radicado = radicado;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConsultaAutosControl)) return false;
        ConsultaAutosControl other = (ConsultaAutosControl) obj;
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
            ((this.fechaFinal==null && other.getFechaFinal()==null) || 
             (this.fechaFinal!=null &&
              this.fechaFinal.equals(other.getFechaFinal()))) &&
            ((this.fechaInicial==null && other.getFechaInicial()==null) || 
             (this.fechaInicial!=null &&
              this.fechaInicial.equals(other.getFechaInicial()))) &&
            ((this.filtroConsulta==null && other.getFiltroConsulta()==null) || 
             (this.filtroConsulta!=null &&
              this.filtroConsulta.equals(other.getFiltroConsulta()))) &&
            ((this.radicado==null && other.getRadicado()==null) || 
             (this.radicado!=null &&
              this.radicado.equals(other.getRadicado())));
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
        if (getFechaFinal() != null) {
            _hashCode += getFechaFinal().hashCode();
        }
        if (getFechaInicial() != null) {
            _hashCode += getFechaInicial().hashCode();
        }
        if (getFiltroConsulta() != null) {
            _hashCode += getFiltroConsulta().hashCode();
        }
        if (getRadicado() != null) {
            _hashCode += getRadicado().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConsultaAutosControl.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "ConsultaAutosControl"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dependencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "Dependencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaFinal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "FechaFinal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaInicial");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "FechaInicial"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("filtroConsulta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "FiltroConsulta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("radicado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "Radicado"));
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
