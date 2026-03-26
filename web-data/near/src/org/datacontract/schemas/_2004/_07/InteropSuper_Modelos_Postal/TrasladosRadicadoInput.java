/**
 * TrasladosRadicadoInput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal;

public class TrasladosRadicadoInput  implements java.io.Serializable {
    private java.lang.Integer idProceso;

    private java.lang.String nroRadicado;

    private java.lang.String refBase;

    private java.lang.String prmTablaGC;

    private java.lang.String prmTablaPanel;

    public TrasladosRadicadoInput() {
    }

    public TrasladosRadicadoInput(
           java.lang.Integer idProceso,
           java.lang.String nroRadicado,
           java.lang.String refBase,
           java.lang.String prmTablaGC,
           java.lang.String prmTablaPanel) {
           this.idProceso = idProceso;
           this.nroRadicado = nroRadicado;
           this.refBase = refBase;
           this.prmTablaGC = prmTablaGC;
           this.prmTablaPanel = prmTablaPanel;
    }


    /**
     * Gets the idProceso value for this TrasladosRadicadoInput.
     * 
     * @return idProceso
     */
    public java.lang.Integer getIdProceso() {
        return idProceso;
    }


    /**
     * Sets the idProceso value for this TrasladosRadicadoInput.
     * 
     * @param idProceso
     */
    public void setIdProceso(java.lang.Integer idProceso) {
        this.idProceso = idProceso;
    }


    /**
     * Gets the nroRadicado value for this TrasladosRadicadoInput.
     * 
     * @return nroRadicado
     */
    public java.lang.String getNroRadicado() {
        return nroRadicado;
    }


    /**
     * Sets the nroRadicado value for this TrasladosRadicadoInput.
     * 
     * @param nroRadicado
     */
    public void setNroRadicado(java.lang.String nroRadicado) {
        this.nroRadicado = nroRadicado;
    }


    /**
     * Gets the refBase value for this TrasladosRadicadoInput.
     * 
     * @return refBase
     */
    public java.lang.String getRefBase() {
        return refBase;
    }


    /**
     * Sets the refBase value for this TrasladosRadicadoInput.
     * 
     * @param refBase
     */
    public void setRefBase(java.lang.String refBase) {
        this.refBase = refBase;
    }


    /**
     * Gets the prmTablaGC value for this TrasladosRadicadoInput.
     * 
     * @return prmTablaGC
     */
    public java.lang.String getPrmTablaGC() {
        return prmTablaGC;
    }


    /**
     * Sets the prmTablaGC value for this TrasladosRadicadoInput.
     * 
     * @param prmTablaGC
     */
    public void setPrmTablaGC(java.lang.String prmTablaGC) {
        this.prmTablaGC = prmTablaGC;
    }


    /**
     * Gets the prmTablaPanel value for this TrasladosRadicadoInput.
     * 
     * @return prmTablaPanel
     */
    public java.lang.String getPrmTablaPanel() {
        return prmTablaPanel;
    }


    /**
     * Sets the prmTablaPanel value for this TrasladosRadicadoInput.
     * 
     * @param prmTablaPanel
     */
    public void setPrmTablaPanel(java.lang.String prmTablaPanel) {
        this.prmTablaPanel = prmTablaPanel;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TrasladosRadicadoInput)) return false;
        TrasladosRadicadoInput other = (TrasladosRadicadoInput) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idProceso==null && other.getIdProceso()==null) || 
             (this.idProceso!=null &&
              this.idProceso.equals(other.getIdProceso()))) &&
            ((this.nroRadicado==null && other.getNroRadicado()==null) || 
             (this.nroRadicado!=null &&
              this.nroRadicado.equals(other.getNroRadicado()))) &&
            ((this.refBase==null && other.getRefBase()==null) || 
             (this.refBase!=null &&
              this.refBase.equals(other.getRefBase()))) &&
            ((this.prmTablaGC==null && other.getPrmTablaGC()==null) || 
             (this.prmTablaGC!=null &&
              this.prmTablaGC.equals(other.getPrmTablaGC()))) &&
            ((this.prmTablaPanel==null && other.getPrmTablaPanel()==null) || 
             (this.prmTablaPanel!=null &&
              this.prmTablaPanel.equals(other.getPrmTablaPanel())));
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
        if (getIdProceso() != null) {
            _hashCode += getIdProceso().hashCode();
        }
        if (getNroRadicado() != null) {
            _hashCode += getNroRadicado().hashCode();
        }
        if (getRefBase() != null) {
            _hashCode += getRefBase().hashCode();
        }
        if (getPrmTablaGC() != null) {
            _hashCode += getPrmTablaGC().hashCode();
        }
        if (getPrmTablaPanel() != null) {
            _hashCode += getPrmTablaPanel().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TrasladosRadicadoInput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "TrasladosRadicadoInput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idProceso");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "IdProceso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nroRadicado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "NroRadicado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("refBase");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "RefBase"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prmTablaGC");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "prmTablaGC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prmTablaPanel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "prmTablaPanel"));
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
