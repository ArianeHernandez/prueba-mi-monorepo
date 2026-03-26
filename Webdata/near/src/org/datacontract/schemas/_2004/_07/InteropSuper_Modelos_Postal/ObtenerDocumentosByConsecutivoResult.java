/**
 * ObtenerDocumentosByConsecutivoResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal;

public class ObtenerDocumentosByConsecutivoResult  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.Documento[] documentos;

    private java.lang.String numeroRadicado;

    private java.lang.Integer totalDocumentos;

    public ObtenerDocumentosByConsecutivoResult() {
    }

    public ObtenerDocumentosByConsecutivoResult(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.Documento[] documentos,
           java.lang.String numeroRadicado,
           java.lang.Integer totalDocumentos) {
           this.documentos = documentos;
           this.numeroRadicado = numeroRadicado;
           this.totalDocumentos = totalDocumentos;
    }


    /**
     * Gets the documentos value for this ObtenerDocumentosByConsecutivoResult.
     * 
     * @return documentos
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Documento[] getDocumentos() {
        return documentos;
    }


    /**
     * Sets the documentos value for this ObtenerDocumentosByConsecutivoResult.
     * 
     * @param documentos
     */
    public void setDocumentos(org.datacontract.schemas._2004._07.InteropSuper_Modelos.Documento[] documentos) {
        this.documentos = documentos;
    }


    /**
     * Gets the numeroRadicado value for this ObtenerDocumentosByConsecutivoResult.
     * 
     * @return numeroRadicado
     */
    public java.lang.String getNumeroRadicado() {
        return numeroRadicado;
    }


    /**
     * Sets the numeroRadicado value for this ObtenerDocumentosByConsecutivoResult.
     * 
     * @param numeroRadicado
     */
    public void setNumeroRadicado(java.lang.String numeroRadicado) {
        this.numeroRadicado = numeroRadicado;
    }


    /**
     * Gets the totalDocumentos value for this ObtenerDocumentosByConsecutivoResult.
     * 
     * @return totalDocumentos
     */
    public java.lang.Integer getTotalDocumentos() {
        return totalDocumentos;
    }


    /**
     * Sets the totalDocumentos value for this ObtenerDocumentosByConsecutivoResult.
     * 
     * @param totalDocumentos
     */
    public void setTotalDocumentos(java.lang.Integer totalDocumentos) {
        this.totalDocumentos = totalDocumentos;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ObtenerDocumentosByConsecutivoResult)) return false;
        ObtenerDocumentosByConsecutivoResult other = (ObtenerDocumentosByConsecutivoResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.documentos==null && other.getDocumentos()==null) || 
             (this.documentos!=null &&
              java.util.Arrays.equals(this.documentos, other.getDocumentos()))) &&
            ((this.numeroRadicado==null && other.getNumeroRadicado()==null) || 
             (this.numeroRadicado!=null &&
              this.numeroRadicado.equals(other.getNumeroRadicado()))) &&
            ((this.totalDocumentos==null && other.getTotalDocumentos()==null) || 
             (this.totalDocumentos!=null &&
              this.totalDocumentos.equals(other.getTotalDocumentos())));
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
        if (getDocumentos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDocumentos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDocumentos(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getNumeroRadicado() != null) {
            _hashCode += getNumeroRadicado().hashCode();
        }
        if (getTotalDocumentos() != null) {
            _hashCode += getTotalDocumentos().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ObtenerDocumentosByConsecutivoResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "ObtenerDocumentosByConsecutivoResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "Documentos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Documento"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Documento"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroRadicado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "NumeroRadicado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalDocumentos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "TotalDocumentos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
