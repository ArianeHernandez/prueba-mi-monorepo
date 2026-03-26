/**
 * TrasladosAutomatico_Input.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal;

public class TrasladosAutomatico_Input  implements java.io.Serializable {
    private java.lang.Integer destinoIdentiFiltro;

    private java.lang.String fechafinal;

    private java.lang.String fechainicial;

    private java.lang.String prmCodProceso;

    private java.lang.String radicado;

    public TrasladosAutomatico_Input() {
    }

    public TrasladosAutomatico_Input(
           java.lang.Integer destinoIdentiFiltro,
           java.lang.String fechafinal,
           java.lang.String fechainicial,
           java.lang.String prmCodProceso,
           java.lang.String radicado) {
           this.destinoIdentiFiltro = destinoIdentiFiltro;
           this.fechafinal = fechafinal;
           this.fechainicial = fechainicial;
           this.prmCodProceso = prmCodProceso;
           this.radicado = radicado;
    }


    /**
     * Gets the destinoIdentiFiltro value for this TrasladosAutomatico_Input.
     * 
     * @return destinoIdentiFiltro
     */
    public java.lang.Integer getDestinoIdentiFiltro() {
        return destinoIdentiFiltro;
    }


    /**
     * Sets the destinoIdentiFiltro value for this TrasladosAutomatico_Input.
     * 
     * @param destinoIdentiFiltro
     */
    public void setDestinoIdentiFiltro(java.lang.Integer destinoIdentiFiltro) {
        this.destinoIdentiFiltro = destinoIdentiFiltro;
    }


    /**
     * Gets the fechafinal value for this TrasladosAutomatico_Input.
     * 
     * @return fechafinal
     */
    public java.lang.String getFechafinal() {
        return fechafinal;
    }


    /**
     * Sets the fechafinal value for this TrasladosAutomatico_Input.
     * 
     * @param fechafinal
     */
    public void setFechafinal(java.lang.String fechafinal) {
        this.fechafinal = fechafinal;
    }


    /**
     * Gets the fechainicial value for this TrasladosAutomatico_Input.
     * 
     * @return fechainicial
     */
    public java.lang.String getFechainicial() {
        return fechainicial;
    }


    /**
     * Sets the fechainicial value for this TrasladosAutomatico_Input.
     * 
     * @param fechainicial
     */
    public void setFechainicial(java.lang.String fechainicial) {
        this.fechainicial = fechainicial;
    }


    /**
     * Gets the prmCodProceso value for this TrasladosAutomatico_Input.
     * 
     * @return prmCodProceso
     */
    public java.lang.String getPrmCodProceso() {
        return prmCodProceso;
    }


    /**
     * Sets the prmCodProceso value for this TrasladosAutomatico_Input.
     * 
     * @param prmCodProceso
     */
    public void setPrmCodProceso(java.lang.String prmCodProceso) {
        this.prmCodProceso = prmCodProceso;
    }


    /**
     * Gets the radicado value for this TrasladosAutomatico_Input.
     * 
     * @return radicado
     */
    public java.lang.String getRadicado() {
        return radicado;
    }


    /**
     * Sets the radicado value for this TrasladosAutomatico_Input.
     * 
     * @param radicado
     */
    public void setRadicado(java.lang.String radicado) {
        this.radicado = radicado;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TrasladosAutomatico_Input)) return false;
        TrasladosAutomatico_Input other = (TrasladosAutomatico_Input) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.destinoIdentiFiltro==null && other.getDestinoIdentiFiltro()==null) || 
             (this.destinoIdentiFiltro!=null &&
              this.destinoIdentiFiltro.equals(other.getDestinoIdentiFiltro()))) &&
            ((this.fechafinal==null && other.getFechafinal()==null) || 
             (this.fechafinal!=null &&
              this.fechafinal.equals(other.getFechafinal()))) &&
            ((this.fechainicial==null && other.getFechainicial()==null) || 
             (this.fechainicial!=null &&
              this.fechainicial.equals(other.getFechainicial()))) &&
            ((this.prmCodProceso==null && other.getPrmCodProceso()==null) || 
             (this.prmCodProceso!=null &&
              this.prmCodProceso.equals(other.getPrmCodProceso()))) &&
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
        if (getDestinoIdentiFiltro() != null) {
            _hashCode += getDestinoIdentiFiltro().hashCode();
        }
        if (getFechafinal() != null) {
            _hashCode += getFechafinal().hashCode();
        }
        if (getFechainicial() != null) {
            _hashCode += getFechainicial().hashCode();
        }
        if (getPrmCodProceso() != null) {
            _hashCode += getPrmCodProceso().hashCode();
        }
        if (getRadicado() != null) {
            _hashCode += getRadicado().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TrasladosAutomatico_Input.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "TrasladosAutomatico_Input"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destinoIdentiFiltro");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "destinoIdentiFiltro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechafinal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "fechafinal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechainicial");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "fechainicial"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("radicado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "radicado"));
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
