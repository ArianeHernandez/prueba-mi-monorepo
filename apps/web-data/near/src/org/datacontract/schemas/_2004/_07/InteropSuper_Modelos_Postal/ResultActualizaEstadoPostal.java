/**
 * ResultActualizaEstadoPostal.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal;

public class ResultActualizaEstadoPostal  implements java.io.Serializable {
    private java.lang.Boolean actualizacionCodigoTramite;

    private java.lang.Boolean actualizacionTipoDocumento;

    private java.lang.String fallas;

    private java.lang.String mensaje;

    public ResultActualizaEstadoPostal() {
    }

    public ResultActualizaEstadoPostal(
           java.lang.Boolean actualizacionCodigoTramite,
           java.lang.Boolean actualizacionTipoDocumento,
           java.lang.String fallas,
           java.lang.String mensaje) {
           this.actualizacionCodigoTramite = actualizacionCodigoTramite;
           this.actualizacionTipoDocumento = actualizacionTipoDocumento;
           this.fallas = fallas;
           this.mensaje = mensaje;
    }


    /**
     * Gets the actualizacionCodigoTramite value for this ResultActualizaEstadoPostal.
     * 
     * @return actualizacionCodigoTramite
     */
    public java.lang.Boolean getActualizacionCodigoTramite() {
        return actualizacionCodigoTramite;
    }


    /**
     * Sets the actualizacionCodigoTramite value for this ResultActualizaEstadoPostal.
     * 
     * @param actualizacionCodigoTramite
     */
    public void setActualizacionCodigoTramite(java.lang.Boolean actualizacionCodigoTramite) {
        this.actualizacionCodigoTramite = actualizacionCodigoTramite;
    }


    /**
     * Gets the actualizacionTipoDocumento value for this ResultActualizaEstadoPostal.
     * 
     * @return actualizacionTipoDocumento
     */
    public java.lang.Boolean getActualizacionTipoDocumento() {
        return actualizacionTipoDocumento;
    }


    /**
     * Sets the actualizacionTipoDocumento value for this ResultActualizaEstadoPostal.
     * 
     * @param actualizacionTipoDocumento
     */
    public void setActualizacionTipoDocumento(java.lang.Boolean actualizacionTipoDocumento) {
        this.actualizacionTipoDocumento = actualizacionTipoDocumento;
    }


    /**
     * Gets the fallas value for this ResultActualizaEstadoPostal.
     * 
     * @return fallas
     */
    public java.lang.String getFallas() {
        return fallas;
    }


    /**
     * Sets the fallas value for this ResultActualizaEstadoPostal.
     * 
     * @param fallas
     */
    public void setFallas(java.lang.String fallas) {
        this.fallas = fallas;
    }


    /**
     * Gets the mensaje value for this ResultActualizaEstadoPostal.
     * 
     * @return mensaje
     */
    public java.lang.String getMensaje() {
        return mensaje;
    }


    /**
     * Sets the mensaje value for this ResultActualizaEstadoPostal.
     * 
     * @param mensaje
     */
    public void setMensaje(java.lang.String mensaje) {
        this.mensaje = mensaje;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultActualizaEstadoPostal)) return false;
        ResultActualizaEstadoPostal other = (ResultActualizaEstadoPostal) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.actualizacionCodigoTramite==null && other.getActualizacionCodigoTramite()==null) || 
             (this.actualizacionCodigoTramite!=null &&
              this.actualizacionCodigoTramite.equals(other.getActualizacionCodigoTramite()))) &&
            ((this.actualizacionTipoDocumento==null && other.getActualizacionTipoDocumento()==null) || 
             (this.actualizacionTipoDocumento!=null &&
              this.actualizacionTipoDocumento.equals(other.getActualizacionTipoDocumento()))) &&
            ((this.fallas==null && other.getFallas()==null) || 
             (this.fallas!=null &&
              this.fallas.equals(other.getFallas()))) &&
            ((this.mensaje==null && other.getMensaje()==null) || 
             (this.mensaje!=null &&
              this.mensaje.equals(other.getMensaje())));
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
        if (getActualizacionCodigoTramite() != null) {
            _hashCode += getActualizacionCodigoTramite().hashCode();
        }
        if (getActualizacionTipoDocumento() != null) {
            _hashCode += getActualizacionTipoDocumento().hashCode();
        }
        if (getFallas() != null) {
            _hashCode += getFallas().hashCode();
        }
        if (getMensaje() != null) {
            _hashCode += getMensaje().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultActualizaEstadoPostal.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "ResultActualizaEstadoPostal"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("actualizacionCodigoTramite");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "ActualizacionCodigoTramite"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("actualizacionTipoDocumento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "ActualizacionTipoDocumento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fallas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "Fallas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mensaje");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "Mensaje"));
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
