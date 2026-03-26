/**
 * RadicacionSalida.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.InteropSuper_Modelos;

public class RadicacionSalida  extends org.datacontract.schemas._2004._07.InteropSuper_Modelos.RadicacionBase  implements java.io.Serializable {
    private java.lang.Long dependenciaAsignadaId;

    private java.lang.String dependenciaAsignadaNombre;

    private java.lang.String funcionarioAsignadoId;

    private java.lang.String funcionarioId;

    public RadicacionSalida() {
    }

    public RadicacionSalida(
           java.lang.String anexosFisicos,
           java.lang.Integer aplicaCiudadCodigo,
           java.lang.Integer aplicaDepartamentoCodigo,
           java.lang.String aplicaDireccion,
           java.lang.String aplicaEmail,
           java.lang.String aplicaIdentificacion,
           java.lang.String aplicaNombre,
           java.lang.Integer aplicaPaisCodigo,
           java.lang.String aplicaTelefono,
           java.lang.Integer aplicaTipoIdentificacionId,
           java.lang.String aplicaTipoIdentificacionNombre,
           java.lang.Integer codigoMedioEnvio,
           java.lang.String codigoSerie,
           java.lang.String codigoSubSerie,
           java.lang.String codigoTipoSeguridad,
           java.lang.String cuadernoCodigo,
           java.lang.Integer cuadernoTipoId,
           java.lang.Integer dependenciaId,
           java.lang.String dependenciaNombre,
           java.lang.String documentalTipoCodigo,
           java.lang.Integer documentalTipoId,
           java.lang.Boolean entregaFisica,
           java.lang.String extensionArchivo,
           java.lang.Integer foliosNumero,
           java.lang.String loginUsuario,
           java.lang.String nombreSerie,
           java.lang.String nombreSubSerie,
           java.lang.Integer particularCiudadCodigo,
           java.lang.Integer particularDepartamentoCodigo,
           java.lang.String particularDireccion,
           java.lang.String particularEmail,
           java.lang.String particularIdentificacion,
           java.lang.String particularNombre,
           java.lang.Integer particularPaisCodigo,
           java.lang.String particularTelefono,
           java.lang.Integer particularTipoIdentificacionId,
           java.lang.String particularTipoIdentificacionNombre,
           java.lang.String radicacionAnterior,
           java.lang.String referenciaExterna,
           java.lang.Long tramiteCodigo,
           java.lang.Integer tramiteId,
           java.lang.Long dependenciaAsignadaId,
           java.lang.String dependenciaAsignadaNombre,
           java.lang.String funcionarioAsignadoId,
           java.lang.String funcionarioId) {
        super(
            anexosFisicos,
            aplicaCiudadCodigo,
            aplicaDepartamentoCodigo,
            aplicaDireccion,
            aplicaEmail,
            aplicaIdentificacion,
            aplicaNombre,
            aplicaPaisCodigo,
            aplicaTelefono,
            aplicaTipoIdentificacionId,
            aplicaTipoIdentificacionNombre,
            codigoMedioEnvio,
            codigoSerie,
            codigoSubSerie,
            codigoTipoSeguridad,
            cuadernoCodigo,
            cuadernoTipoId,
            dependenciaId,
            dependenciaNombre,
            documentalTipoCodigo,
            documentalTipoId,
            entregaFisica,
            extensionArchivo,
            foliosNumero,
            loginUsuario,
            nombreSerie,
            nombreSubSerie,
            particularCiudadCodigo,
            particularDepartamentoCodigo,
            particularDireccion,
            particularEmail,
            particularIdentificacion,
            particularNombre,
            particularPaisCodigo,
            particularTelefono,
            particularTipoIdentificacionId,
            particularTipoIdentificacionNombre,
            radicacionAnterior,
            referenciaExterna,
            tramiteCodigo,
            tramiteId);
        this.dependenciaAsignadaId = dependenciaAsignadaId;
        this.dependenciaAsignadaNombre = dependenciaAsignadaNombre;
        this.funcionarioAsignadoId = funcionarioAsignadoId;
        this.funcionarioId = funcionarioId;
    }


    /**
     * Gets the dependenciaAsignadaId value for this RadicacionSalida.
     * 
     * @return dependenciaAsignadaId
     */
    public java.lang.Long getDependenciaAsignadaId() {
        return dependenciaAsignadaId;
    }


    /**
     * Sets the dependenciaAsignadaId value for this RadicacionSalida.
     * 
     * @param dependenciaAsignadaId
     */
    public void setDependenciaAsignadaId(java.lang.Long dependenciaAsignadaId) {
        this.dependenciaAsignadaId = dependenciaAsignadaId;
    }


    /**
     * Gets the dependenciaAsignadaNombre value for this RadicacionSalida.
     * 
     * @return dependenciaAsignadaNombre
     */
    public java.lang.String getDependenciaAsignadaNombre() {
        return dependenciaAsignadaNombre;
    }


    /**
     * Sets the dependenciaAsignadaNombre value for this RadicacionSalida.
     * 
     * @param dependenciaAsignadaNombre
     */
    public void setDependenciaAsignadaNombre(java.lang.String dependenciaAsignadaNombre) {
        this.dependenciaAsignadaNombre = dependenciaAsignadaNombre;
    }


    /**
     * Gets the funcionarioAsignadoId value for this RadicacionSalida.
     * 
     * @return funcionarioAsignadoId
     */
    public java.lang.String getFuncionarioAsignadoId() {
        return funcionarioAsignadoId;
    }


    /**
     * Sets the funcionarioAsignadoId value for this RadicacionSalida.
     * 
     * @param funcionarioAsignadoId
     */
    public void setFuncionarioAsignadoId(java.lang.String funcionarioAsignadoId) {
        this.funcionarioAsignadoId = funcionarioAsignadoId;
    }


    /**
     * Gets the funcionarioId value for this RadicacionSalida.
     * 
     * @return funcionarioId
     */
    public java.lang.String getFuncionarioId() {
        return funcionarioId;
    }


    /**
     * Sets the funcionarioId value for this RadicacionSalida.
     * 
     * @param funcionarioId
     */
    public void setFuncionarioId(java.lang.String funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RadicacionSalida)) return false;
        RadicacionSalida other = (RadicacionSalida) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.dependenciaAsignadaId==null && other.getDependenciaAsignadaId()==null) || 
             (this.dependenciaAsignadaId!=null &&
              this.dependenciaAsignadaId.equals(other.getDependenciaAsignadaId()))) &&
            ((this.dependenciaAsignadaNombre==null && other.getDependenciaAsignadaNombre()==null) || 
             (this.dependenciaAsignadaNombre!=null &&
              this.dependenciaAsignadaNombre.equals(other.getDependenciaAsignadaNombre()))) &&
            ((this.funcionarioAsignadoId==null && other.getFuncionarioAsignadoId()==null) || 
             (this.funcionarioAsignadoId!=null &&
              this.funcionarioAsignadoId.equals(other.getFuncionarioAsignadoId()))) &&
            ((this.funcionarioId==null && other.getFuncionarioId()==null) || 
             (this.funcionarioId!=null &&
              this.funcionarioId.equals(other.getFuncionarioId())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getDependenciaAsignadaId() != null) {
            _hashCode += getDependenciaAsignadaId().hashCode();
        }
        if (getDependenciaAsignadaNombre() != null) {
            _hashCode += getDependenciaAsignadaNombre().hashCode();
        }
        if (getFuncionarioAsignadoId() != null) {
            _hashCode += getFuncionarioAsignadoId().hashCode();
        }
        if (getFuncionarioId() != null) {
            _hashCode += getFuncionarioId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RadicacionSalida.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "RadicacionSalida"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dependenciaAsignadaId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "DependenciaAsignadaId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dependenciaAsignadaNombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "DependenciaAsignadaNombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("funcionarioAsignadoId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "FuncionarioAsignadoId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("funcionarioId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "FuncionarioId"));
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
