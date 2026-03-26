/**
 * DatosConsultaRadicado.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.InteropSuper_Modelos_BPM;

public class DatosConsultaRadicado  implements java.io.Serializable {
    private java.lang.String codDependencia;

    private java.lang.String filtroConsulta;

    private java.lang.Integer idProceso;

    private java.lang.String nroRadicado;

    private java.lang.String refBase;

    public DatosConsultaRadicado() {
    }

    public DatosConsultaRadicado(
           java.lang.String codDependencia,
           java.lang.String filtroConsulta,
           java.lang.Integer idProceso,
           java.lang.String nroRadicado,
           java.lang.String refBase) {
           this.codDependencia = codDependencia;
           this.filtroConsulta = filtroConsulta;
           this.idProceso = idProceso;
           this.nroRadicado = nroRadicado;
           this.refBase = refBase;
    }


    /**
     * Gets the codDependencia value for this DatosConsultaRadicado.
     * 
     * @return codDependencia
     */
    public java.lang.String getCodDependencia() {
        return codDependencia;
    }


    /**
     * Sets the codDependencia value for this DatosConsultaRadicado.
     * 
     * @param codDependencia
     */
    public void setCodDependencia(java.lang.String codDependencia) {
        this.codDependencia = codDependencia;
    }


    /**
     * Gets the filtroConsulta value for this DatosConsultaRadicado.
     * 
     * @return filtroConsulta
     */
    public java.lang.String getFiltroConsulta() {
        return filtroConsulta;
    }


    /**
     * Sets the filtroConsulta value for this DatosConsultaRadicado.
     * 
     * @param filtroConsulta
     */
    public void setFiltroConsulta(java.lang.String filtroConsulta) {
        this.filtroConsulta = filtroConsulta;
    }


    /**
     * Gets the idProceso value for this DatosConsultaRadicado.
     * 
     * @return idProceso
     */
    public java.lang.Integer getIdProceso() {
        return idProceso;
    }


    /**
     * Sets the idProceso value for this DatosConsultaRadicado.
     * 
     * @param idProceso
     */
    public void setIdProceso(java.lang.Integer idProceso) {
        this.idProceso = idProceso;
    }


    /**
     * Gets the nroRadicado value for this DatosConsultaRadicado.
     * 
     * @return nroRadicado
     */
    public java.lang.String getNroRadicado() {
        return nroRadicado;
    }


    /**
     * Sets the nroRadicado value for this DatosConsultaRadicado.
     * 
     * @param nroRadicado
     */
    public void setNroRadicado(java.lang.String nroRadicado) {
        this.nroRadicado = nroRadicado;
    }


    /**
     * Gets the refBase value for this DatosConsultaRadicado.
     * 
     * @return refBase
     */
    public java.lang.String getRefBase() {
        return refBase;
    }


    /**
     * Sets the refBase value for this DatosConsultaRadicado.
     * 
     * @param refBase
     */
    public void setRefBase(java.lang.String refBase) {
        this.refBase = refBase;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DatosConsultaRadicado)) return false;
        DatosConsultaRadicado other = (DatosConsultaRadicado) obj;
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
            ((this.filtroConsulta==null && other.getFiltroConsulta()==null) || 
             (this.filtroConsulta!=null &&
              this.filtroConsulta.equals(other.getFiltroConsulta()))) &&
            ((this.idProceso==null && other.getIdProceso()==null) || 
             (this.idProceso!=null &&
              this.idProceso.equals(other.getIdProceso()))) &&
            ((this.nroRadicado==null && other.getNroRadicado()==null) || 
             (this.nroRadicado!=null &&
              this.nroRadicado.equals(other.getNroRadicado()))) &&
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
        if (getFiltroConsulta() != null) {
            _hashCode += getFiltroConsulta().hashCode();
        }
        if (getIdProceso() != null) {
            _hashCode += getIdProceso().hashCode();
        }
        if (getNroRadicado() != null) {
            _hashCode += getNroRadicado().hashCode();
        }
        if (getRefBase() != null) {
            _hashCode += getRefBase().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DatosConsultaRadicado.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.BPM", "DatosConsultaRadicado"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codDependencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.BPM", "CodDependencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
        elemField.setFieldName("nroRadicado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.BPM", "NroRadicado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
