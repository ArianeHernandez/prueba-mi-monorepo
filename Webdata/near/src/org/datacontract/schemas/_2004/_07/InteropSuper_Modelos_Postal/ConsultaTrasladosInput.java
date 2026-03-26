/**
 * ConsultaTrasladosInput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal;

public class ConsultaTrasladosInput  implements java.io.Serializable {
    private java.lang.String codDependencia;

    private java.util.Calendar fechaDia;

    private java.util.Calendar fechaInicioConsulta;

    private java.lang.Integer idProceso;

    private java.lang.String refBase;

    private java.lang.String prmCodProceso;

    public ConsultaTrasladosInput() {
    }

    public ConsultaTrasladosInput(
           java.lang.String codDependencia,
           java.util.Calendar fechaDia,
           java.util.Calendar fechaInicioConsulta,
           java.lang.Integer idProceso,
           java.lang.String refBase,
           java.lang.String prmCodProceso) {
           this.codDependencia = codDependencia;
           this.fechaDia = fechaDia;
           this.fechaInicioConsulta = fechaInicioConsulta;
           this.idProceso = idProceso;
           this.refBase = refBase;
           this.prmCodProceso = prmCodProceso;
    }


    /**
     * Gets the codDependencia value for this ConsultaTrasladosInput.
     * 
     * @return codDependencia
     */
    public java.lang.String getCodDependencia() {
        return codDependencia;
    }


    /**
     * Sets the codDependencia value for this ConsultaTrasladosInput.
     * 
     * @param codDependencia
     */
    public void setCodDependencia(java.lang.String codDependencia) {
        this.codDependencia = codDependencia;
    }


    /**
     * Gets the fechaDia value for this ConsultaTrasladosInput.
     * 
     * @return fechaDia
     */
    public java.util.Calendar getFechaDia() {
        return fechaDia;
    }


    /**
     * Sets the fechaDia value for this ConsultaTrasladosInput.
     * 
     * @param fechaDia
     */
    public void setFechaDia(java.util.Calendar fechaDia) {
        this.fechaDia = fechaDia;
    }


    /**
     * Gets the fechaInicioConsulta value for this ConsultaTrasladosInput.
     * 
     * @return fechaInicioConsulta
     */
    public java.util.Calendar getFechaInicioConsulta() {
        return fechaInicioConsulta;
    }


    /**
     * Sets the fechaInicioConsulta value for this ConsultaTrasladosInput.
     * 
     * @param fechaInicioConsulta
     */
    public void setFechaInicioConsulta(java.util.Calendar fechaInicioConsulta) {
        this.fechaInicioConsulta = fechaInicioConsulta;
    }


    /**
     * Gets the idProceso value for this ConsultaTrasladosInput.
     * 
     * @return idProceso
     */
    public java.lang.Integer getIdProceso() {
        return idProceso;
    }


    /**
     * Sets the idProceso value for this ConsultaTrasladosInput.
     * 
     * @param idProceso
     */
    public void setIdProceso(java.lang.Integer idProceso) {
        this.idProceso = idProceso;
    }


    /**
     * Gets the refBase value for this ConsultaTrasladosInput.
     * 
     * @return refBase
     */
    public java.lang.String getRefBase() {
        return refBase;
    }


    /**
     * Sets the refBase value for this ConsultaTrasladosInput.
     * 
     * @param refBase
     */
    public void setRefBase(java.lang.String refBase) {
        this.refBase = refBase;
    }


    /**
     * Gets the prmCodProceso value for this ConsultaTrasladosInput.
     * 
     * @return prmCodProceso
     */
    public java.lang.String getPrmCodProceso() {
        return prmCodProceso;
    }


    /**
     * Sets the prmCodProceso value for this ConsultaTrasladosInput.
     * 
     * @param prmCodProceso
     */
    public void setPrmCodProceso(java.lang.String prmCodProceso) {
        this.prmCodProceso = prmCodProceso;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConsultaTrasladosInput)) return false;
        ConsultaTrasladosInput other = (ConsultaTrasladosInput) obj;
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
            ((this.fechaInicioConsulta==null && other.getFechaInicioConsulta()==null) || 
             (this.fechaInicioConsulta!=null &&
              this.fechaInicioConsulta.equals(other.getFechaInicioConsulta()))) &&
            ((this.idProceso==null && other.getIdProceso()==null) || 
             (this.idProceso!=null &&
              this.idProceso.equals(other.getIdProceso()))) &&
            ((this.refBase==null && other.getRefBase()==null) || 
             (this.refBase!=null &&
              this.refBase.equals(other.getRefBase()))) &&
            ((this.prmCodProceso==null && other.getPrmCodProceso()==null) || 
             (this.prmCodProceso!=null &&
              this.prmCodProceso.equals(other.getPrmCodProceso())));
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
        if (getFechaInicioConsulta() != null) {
            _hashCode += getFechaInicioConsulta().hashCode();
        }
        if (getIdProceso() != null) {
            _hashCode += getIdProceso().hashCode();
        }
        if (getRefBase() != null) {
            _hashCode += getRefBase().hashCode();
        }
        if (getPrmCodProceso() != null) {
            _hashCode += getPrmCodProceso().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConsultaTrasladosInput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "ConsultaTrasladosInput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codDependencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "CodDependencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaDia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "FechaDia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaInicioConsulta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "FechaInicioConsulta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idProceso");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "IdProceso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("refBase");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "RefBase"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prmCodProceso");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "prmCodProceso"));
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
