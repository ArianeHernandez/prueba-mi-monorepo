/**
 * TipoDocumental_ObtenerTodosResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class TipoDocumental_ObtenerTodosResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental[] tipoDocumental_ObtenerTodosResult;

    public TipoDocumental_ObtenerTodosResponse() {
    }

    public TipoDocumental_ObtenerTodosResponse(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental[] tipoDocumental_ObtenerTodosResult) {
           this.tipoDocumental_ObtenerTodosResult = tipoDocumental_ObtenerTodosResult;
    }


    /**
     * Gets the tipoDocumental_ObtenerTodosResult value for this TipoDocumental_ObtenerTodosResponse.
     * 
     * @return tipoDocumental_ObtenerTodosResult
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental[] getTipoDocumental_ObtenerTodosResult() {
        return tipoDocumental_ObtenerTodosResult;
    }


    /**
     * Sets the tipoDocumental_ObtenerTodosResult value for this TipoDocumental_ObtenerTodosResponse.
     * 
     * @param tipoDocumental_ObtenerTodosResult
     */
    public void setTipoDocumental_ObtenerTodosResult(org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental[] tipoDocumental_ObtenerTodosResult) {
        this.tipoDocumental_ObtenerTodosResult = tipoDocumental_ObtenerTodosResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoDocumental_ObtenerTodosResponse)) return false;
        TipoDocumental_ObtenerTodosResponse other = (TipoDocumental_ObtenerTodosResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tipoDocumental_ObtenerTodosResult==null && other.getTipoDocumental_ObtenerTodosResult()==null) || 
             (this.tipoDocumental_ObtenerTodosResult!=null &&
              java.util.Arrays.equals(this.tipoDocumental_ObtenerTodosResult, other.getTipoDocumental_ObtenerTodosResult())));
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
        if (getTipoDocumental_ObtenerTodosResult() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTipoDocumental_ObtenerTodosResult());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTipoDocumental_ObtenerTodosResult(), i);
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
        new org.apache.axis.description.TypeDesc(TipoDocumental_ObtenerTodosResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">TipoDocumental_ObtenerTodosResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoDocumental_ObtenerTodosResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TipoDocumental_ObtenerTodosResult"));
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
