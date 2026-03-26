/**
 * TipoDocumental_ObtenerPorDependenciaResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class TipoDocumental_ObtenerPorDependenciaResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental[] tipoDocumental_ObtenerPorDependenciaResult;

    public TipoDocumental_ObtenerPorDependenciaResponse() {
    }

    public TipoDocumental_ObtenerPorDependenciaResponse(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental[] tipoDocumental_ObtenerPorDependenciaResult) {
           this.tipoDocumental_ObtenerPorDependenciaResult = tipoDocumental_ObtenerPorDependenciaResult;
    }


    /**
     * Gets the tipoDocumental_ObtenerPorDependenciaResult value for this TipoDocumental_ObtenerPorDependenciaResponse.
     * 
     * @return tipoDocumental_ObtenerPorDependenciaResult
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental[] getTipoDocumental_ObtenerPorDependenciaResult() {
        return tipoDocumental_ObtenerPorDependenciaResult;
    }


    /**
     * Sets the tipoDocumental_ObtenerPorDependenciaResult value for this TipoDocumental_ObtenerPorDependenciaResponse.
     * 
     * @param tipoDocumental_ObtenerPorDependenciaResult
     */
    public void setTipoDocumental_ObtenerPorDependenciaResult(org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental[] tipoDocumental_ObtenerPorDependenciaResult) {
        this.tipoDocumental_ObtenerPorDependenciaResult = tipoDocumental_ObtenerPorDependenciaResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoDocumental_ObtenerPorDependenciaResponse)) return false;
        TipoDocumental_ObtenerPorDependenciaResponse other = (TipoDocumental_ObtenerPorDependenciaResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tipoDocumental_ObtenerPorDependenciaResult==null && other.getTipoDocumental_ObtenerPorDependenciaResult()==null) || 
             (this.tipoDocumental_ObtenerPorDependenciaResult!=null &&
              java.util.Arrays.equals(this.tipoDocumental_ObtenerPorDependenciaResult, other.getTipoDocumental_ObtenerPorDependenciaResult())));
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
        if (getTipoDocumental_ObtenerPorDependenciaResult() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTipoDocumental_ObtenerPorDependenciaResult());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTipoDocumental_ObtenerPorDependenciaResult(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoDocumental_ObtenerPorDependenciaResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">TipoDocumental_ObtenerPorDependenciaResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoDocumental_ObtenerPorDependenciaResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TipoDocumental_ObtenerPorDependenciaResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "TipoDocumental"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "TipoDocumental"));
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
