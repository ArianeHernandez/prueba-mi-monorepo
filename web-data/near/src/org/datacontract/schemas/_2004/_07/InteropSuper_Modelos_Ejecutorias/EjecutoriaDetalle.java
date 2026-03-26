/**
 * EjecutoriaDetalle.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.InteropSuper_Modelos_Ejecutorias;

public class EjecutoriaDetalle  implements java.io.Serializable {
    private java.lang.Integer COD_TRAMITE;

    private java.lang.String DESCRIPTION;

    private java.util.Calendar FECHA_RADICACION;

    private java.util.Calendar HORA_RADICACION;

    private java.lang.String MULTA;

    private java.lang.String NOMBRE_TRAMITE;

    private java.lang.String NUM_CONSEC_ESPECI;

    private java.lang.String NUM_RADICA;

    private java.lang.String expediente;

    public EjecutoriaDetalle() {
    }

    public EjecutoriaDetalle(
           java.lang.Integer COD_TRAMITE,
           java.lang.String DESCRIPTION,
           java.util.Calendar FECHA_RADICACION,
           java.util.Calendar HORA_RADICACION,
           java.lang.String MULTA,
           java.lang.String NOMBRE_TRAMITE,
           java.lang.String NUM_CONSEC_ESPECI,
           java.lang.String NUM_RADICA,
           java.lang.String expediente) {
           this.COD_TRAMITE = COD_TRAMITE;
           this.DESCRIPTION = DESCRIPTION;
           this.FECHA_RADICACION = FECHA_RADICACION;
           this.HORA_RADICACION = HORA_RADICACION;
           this.MULTA = MULTA;
           this.NOMBRE_TRAMITE = NOMBRE_TRAMITE;
           this.NUM_CONSEC_ESPECI = NUM_CONSEC_ESPECI;
           this.NUM_RADICA = NUM_RADICA;
           this.expediente = expediente;
    }


    /**
     * Gets the COD_TRAMITE value for this EjecutoriaDetalle.
     * 
     * @return COD_TRAMITE
     */
    public java.lang.Integer getCOD_TRAMITE() {
        return COD_TRAMITE;
    }


    /**
     * Sets the COD_TRAMITE value for this EjecutoriaDetalle.
     * 
     * @param COD_TRAMITE
     */
    public void setCOD_TRAMITE(java.lang.Integer COD_TRAMITE) {
        this.COD_TRAMITE = COD_TRAMITE;
    }


    /**
     * Gets the DESCRIPTION value for this EjecutoriaDetalle.
     * 
     * @return DESCRIPTION
     */
    public java.lang.String getDESCRIPTION() {
        return DESCRIPTION;
    }


    /**
     * Sets the DESCRIPTION value for this EjecutoriaDetalle.
     * 
     * @param DESCRIPTION
     */
    public void setDESCRIPTION(java.lang.String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }


    /**
     * Gets the FECHA_RADICACION value for this EjecutoriaDetalle.
     * 
     * @return FECHA_RADICACION
     */
    public java.util.Calendar getFECHA_RADICACION() {
        return FECHA_RADICACION;
    }


    /**
     * Sets the FECHA_RADICACION value for this EjecutoriaDetalle.
     * 
     * @param FECHA_RADICACION
     */
    public void setFECHA_RADICACION(java.util.Calendar FECHA_RADICACION) {
        this.FECHA_RADICACION = FECHA_RADICACION;
    }


    /**
     * Gets the HORA_RADICACION value for this EjecutoriaDetalle.
     * 
     * @return HORA_RADICACION
     */
    public java.util.Calendar getHORA_RADICACION() {
        return HORA_RADICACION;
    }


    /**
     * Sets the HORA_RADICACION value for this EjecutoriaDetalle.
     * 
     * @param HORA_RADICACION
     */
    public void setHORA_RADICACION(java.util.Calendar HORA_RADICACION) {
        this.HORA_RADICACION = HORA_RADICACION;
    }


    /**
     * Gets the MULTA value for this EjecutoriaDetalle.
     * 
     * @return MULTA
     */
    public java.lang.String getMULTA() {
        return MULTA;
    }


    /**
     * Sets the MULTA value for this EjecutoriaDetalle.
     * 
     * @param MULTA
     */
    public void setMULTA(java.lang.String MULTA) {
        this.MULTA = MULTA;
    }


    /**
     * Gets the NOMBRE_TRAMITE value for this EjecutoriaDetalle.
     * 
     * @return NOMBRE_TRAMITE
     */
    public java.lang.String getNOMBRE_TRAMITE() {
        return NOMBRE_TRAMITE;
    }


    /**
     * Sets the NOMBRE_TRAMITE value for this EjecutoriaDetalle.
     * 
     * @param NOMBRE_TRAMITE
     */
    public void setNOMBRE_TRAMITE(java.lang.String NOMBRE_TRAMITE) {
        this.NOMBRE_TRAMITE = NOMBRE_TRAMITE;
    }


    /**
     * Gets the NUM_CONSEC_ESPECI value for this EjecutoriaDetalle.
     * 
     * @return NUM_CONSEC_ESPECI
     */
    public java.lang.String getNUM_CONSEC_ESPECI() {
        return NUM_CONSEC_ESPECI;
    }


    /**
     * Sets the NUM_CONSEC_ESPECI value for this EjecutoriaDetalle.
     * 
     * @param NUM_CONSEC_ESPECI
     */
    public void setNUM_CONSEC_ESPECI(java.lang.String NUM_CONSEC_ESPECI) {
        this.NUM_CONSEC_ESPECI = NUM_CONSEC_ESPECI;
    }


    /**
     * Gets the NUM_RADICA value for this EjecutoriaDetalle.
     * 
     * @return NUM_RADICA
     */
    public java.lang.String getNUM_RADICA() {
        return NUM_RADICA;
    }


    /**
     * Sets the NUM_RADICA value for this EjecutoriaDetalle.
     * 
     * @param NUM_RADICA
     */
    public void setNUM_RADICA(java.lang.String NUM_RADICA) {
        this.NUM_RADICA = NUM_RADICA;
    }


    /**
     * Gets the expediente value for this EjecutoriaDetalle.
     * 
     * @return expediente
     */
    public java.lang.String getExpediente() {
        return expediente;
    }


    /**
     * Sets the expediente value for this EjecutoriaDetalle.
     * 
     * @param expediente
     */
    public void setExpediente(java.lang.String expediente) {
        this.expediente = expediente;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EjecutoriaDetalle)) return false;
        EjecutoriaDetalle other = (EjecutoriaDetalle) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.COD_TRAMITE==null && other.getCOD_TRAMITE()==null) || 
             (this.COD_TRAMITE!=null &&
              this.COD_TRAMITE.equals(other.getCOD_TRAMITE()))) &&
            ((this.DESCRIPTION==null && other.getDESCRIPTION()==null) || 
             (this.DESCRIPTION!=null &&
              this.DESCRIPTION.equals(other.getDESCRIPTION()))) &&
            ((this.FECHA_RADICACION==null && other.getFECHA_RADICACION()==null) || 
             (this.FECHA_RADICACION!=null &&
              this.FECHA_RADICACION.equals(other.getFECHA_RADICACION()))) &&
            ((this.HORA_RADICACION==null && other.getHORA_RADICACION()==null) || 
             (this.HORA_RADICACION!=null &&
              this.HORA_RADICACION.equals(other.getHORA_RADICACION()))) &&
            ((this.MULTA==null && other.getMULTA()==null) || 
             (this.MULTA!=null &&
              this.MULTA.equals(other.getMULTA()))) &&
            ((this.NOMBRE_TRAMITE==null && other.getNOMBRE_TRAMITE()==null) || 
             (this.NOMBRE_TRAMITE!=null &&
              this.NOMBRE_TRAMITE.equals(other.getNOMBRE_TRAMITE()))) &&
            ((this.NUM_CONSEC_ESPECI==null && other.getNUM_CONSEC_ESPECI()==null) || 
             (this.NUM_CONSEC_ESPECI!=null &&
              this.NUM_CONSEC_ESPECI.equals(other.getNUM_CONSEC_ESPECI()))) &&
            ((this.NUM_RADICA==null && other.getNUM_RADICA()==null) || 
             (this.NUM_RADICA!=null &&
              this.NUM_RADICA.equals(other.getNUM_RADICA()))) &&
            ((this.expediente==null && other.getExpediente()==null) || 
             (this.expediente!=null &&
              this.expediente.equals(other.getExpediente())));
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
        if (getCOD_TRAMITE() != null) {
            _hashCode += getCOD_TRAMITE().hashCode();
        }
        if (getDESCRIPTION() != null) {
            _hashCode += getDESCRIPTION().hashCode();
        }
        if (getFECHA_RADICACION() != null) {
            _hashCode += getFECHA_RADICACION().hashCode();
        }
        if (getHORA_RADICACION() != null) {
            _hashCode += getHORA_RADICACION().hashCode();
        }
        if (getMULTA() != null) {
            _hashCode += getMULTA().hashCode();
        }
        if (getNOMBRE_TRAMITE() != null) {
            _hashCode += getNOMBRE_TRAMITE().hashCode();
        }
        if (getNUM_CONSEC_ESPECI() != null) {
            _hashCode += getNUM_CONSEC_ESPECI().hashCode();
        }
        if (getNUM_RADICA() != null) {
            _hashCode += getNUM_RADICA().hashCode();
        }
        if (getExpediente() != null) {
            _hashCode += getExpediente().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EjecutoriaDetalle.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Ejecutorias", "EjecutoriaDetalle"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("COD_TRAMITE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Ejecutorias", "COD_TRAMITE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DESCRIPTION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Ejecutorias", "DESCRIPTION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FECHA_RADICACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Ejecutorias", "FECHA_RADICACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("HORA_RADICACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Ejecutorias", "HORA_RADICACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MULTA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Ejecutorias", "MULTA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NOMBRE_TRAMITE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Ejecutorias", "NOMBRE_TRAMITE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUM_CONSEC_ESPECI");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Ejecutorias", "NUM_CONSEC_ESPECI"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUM_RADICA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Ejecutorias", "NUM_RADICA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("expediente");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Ejecutorias", "expediente"));
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
