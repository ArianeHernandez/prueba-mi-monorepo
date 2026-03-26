/**
 * DatosConsultaEstados.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.InteropSuper_Modelos_BPM;

public class DatosConsultaEstados  implements java.io.Serializable {
    private java.lang.String codDependencia;

    private java.util.Calendar fechaDia;

    private java.util.Calendar fechaFinal;

    private java.lang.String filtroConsulta;

    private java.lang.Integer idProceso;

    private java.lang.String refBase;

    public DatosConsultaEstados() {
    }

    public DatosConsultaEstados(
           java.lang.String codDependencia,
           java.util.Calendar fechaDia,
           java.util.Calendar fechaFinal,
           java.lang.String filtroConsulta,
           java.lang.Integer idProceso,
           java.lang.String refBase) {
           this.codDependencia = codDependencia;
           this.fechaDia = fechaDia;
           this.fechaFinal = fechaFinal;
           this.filtroConsulta = filtroConsulta;
           this.idProceso = idProceso;
           this.refBase = refBase;
    }


    /**
     * Gets the codDependencia value for this DatosConsultaEstados.
     * 
     * @return codDependencia
     */
    public java.lang.String getCodDependencia() {
        return codDependencia;
    }


    /**
     * Sets the codDependencia value for this DatosConsultaEstados.
     * 
     * @param codDependencia
     */
    public void setCodDependencia(java.lang.String codDependencia) {
        this.codDependencia = codDependencia;
    }


    /**
     * Gets the fechaDia value for this DatosConsultaEstados.
     * 
     * @return fechaDia
     */
    public java.util.Calendar getFechaDia() {
        return fechaDia;
    }


    /**
     * Sets the fechaDia value for this DatosConsultaEstados.
     * 
     * @param fechaDia
     */
    public void setFechaDia(java.util.Calendar fechaDia) {
        this.fechaDia = fechaDia;
    }


    /**
     * Gets the fechaFinal value for this DatosConsultaEstados.
     * 
     * @return fechaFinal
     */
    public java.util.Calendar getFechaFinal() {
        return fechaFinal;
    }


    /**
     * Sets the fechaFinal value for this DatosConsultaEstados.
     * 
     * @param fechaFinal
     */
    public void setFechaFinal(java.util.Calendar fechaFinal) {
        this.fechaFinal = fechaFinal;
    }


    /**
     * Gets the filtroConsulta value for this DatosConsultaEstados.
     * 
     * @return filtroConsulta
     */
    public java.lang.String getFiltroConsulta() {
        return filtroConsulta;
    }


    /**
     * Sets the filtroConsulta value for this DatosConsultaEstados.
     * 
     * @param filtroConsulta
     */
    public void setFiltroConsulta(java.lang.String filtroConsulta) {
        this.filtroConsulta = filtroConsulta;
    }


    /**
     * Gets the idProceso value for this DatosConsultaEstados.
     * 
     * @return idProceso
     */
    public java.lang.Integer getIdProceso() {
        return idProceso;
    }


    /**
     * Sets the idProceso value for this DatosConsultaEstados.
     * 
     * @param idProceso
     */
    public void setIdProceso(java.lang.Integer idProceso) {
        this.idProceso = idProceso;
    }


    /**
     * Gets the refBase value for this DatosConsultaEstados.
     * 
     * @return refBase
     */
    public java.lang.String getRefBase() {
        return refBase;
    }


    /**
     * Sets the refBase value for this DatosConsultaEstados.
     * 
     * @param refBase
     */
    public void setRefBase(java.lang.String refBase) {
        this.refBase = refBase;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DatosConsultaEstados)) return false;
        DatosConsultaEstados other = (DatosConsultaEstados) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codDependencia==null && other.getCodDependencia()==null) || 
             (this.codDependencia!=null &&
              this.codDependencia.equals(other.getCodDependencia()))) &&
            ((this.fechaDia==null && other.getFechaDia()==null) || 
             (this.fechaDia!=null &&
              this.fechaDia.equals(other.getFechaDia()))) &&
            ((this.fechaFinal==null && other.getFechaFinal()==null) || 
             (this.fechaFinal!=null &&
              this.fechaFinal.equals(other.getFechaFinal()))) &&
            ((this.filtroConsulta==null && other.getFiltroConsulta()==null) || 
             (this.filtroConsulta!=null &&
              this.filtroConsulta.equals(other.getFiltroConsulta()))) &&
            ((this.idProceso==null && other.getIdProceso()==null) || 
             (this.idProceso!=null &&
              this.idProceso.equals(other.getIdProceso()))) &&
            ((this.refBase==null && other.getRefBase()==null) || 
             (this.refBase!=null &&
              this.refBase.equals(other.getRefBase())));
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
        if (getCodDependencia() != null) {
            _hashCode += getCodDependencia().hashCode();
        }
        if (getFechaDia() != null) {
            _hashCode += getFechaDia().hashCode();
        }
        if (getFechaFinal() != null) {
            _hashCode += getFechaFinal().hashCode();
        }
        if (getFiltroConsulta() != null) {
            _hashCode += getFiltroConsulta().hashCode();
        }
        if (getIdProceso() != null) {
            _hashCode += getIdProceso().hashCode();
        }
        if (getRefBase() != null) {
            _hashCode += getRefBase().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DatosConsultaEstados.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.BPM", "DatosConsultaEstados"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codDependencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.BPM", "CodDependencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaDia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.BPM", "FechaDia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaFinal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.BPM", "FechaFinal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("filtroConsulta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.BPM", "FiltroConsulta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idProceso");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.BPM", "IdProceso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("refBase");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.BPM", "RefBase"));
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
