/**
 * ObtenerInformacionEjecutoriasDetalleResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class ObtenerInformacionEjecutoriasDetalleResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos_Ejecutorias.EjecutoriaDetalle[] obtenerInformacionEjecutoriasDetalleResult;

    public ObtenerInformacionEjecutoriasDetalleResponse() {
    }

    public ObtenerInformacionEjecutoriasDetalleResponse(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos_Ejecutorias.EjecutoriaDetalle[] obtenerInformacionEjecutoriasDetalleResult) {
           this.obtenerInformacionEjecutoriasDetalleResult = obtenerInformacionEjecutoriasDetalleResult;
    }


    /**
     * Gets the obtenerInformacionEjecutoriasDetalleResult value for this ObtenerInformacionEjecutoriasDetalleResponse.
     * 
     * @return obtenerInformacionEjecutoriasDetalleResult
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Ejecutorias.EjecutoriaDetalle[] getObtenerInformacionEjecutoriasDetalleResult() {
        return obtenerInformacionEjecutoriasDetalleResult;
    }


    /**
     * Sets the obtenerInformacionEjecutoriasDetalleResult value for this ObtenerInformacionEjecutoriasDetalleResponse.
     * 
     * @param obtenerInformacionEjecutoriasDetalleResult
     */
    public void setObtenerInformacionEjecutoriasDetalleResult(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Ejecutorias.EjecutoriaDetalle[] obtenerInformacionEjecutoriasDetalleResult) {
        this.obtenerInformacionEjecutoriasDetalleResult = obtenerInformacionEjecutoriasDetalleResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ObtenerInformacionEjecutoriasDetalleResponse)) return false;
        ObtenerInformacionEjecutoriasDetalleResponse other = (ObtenerInformacionEjecutoriasDetalleResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.obtenerInformacionEjecutoriasDetalleResult==null && other.getObtenerInformacionEjecutoriasDetalleResult()==null) || 
             (this.obtenerInformacionEjecutoriasDetalleResult!=null &&
              java.util.Arrays.equals(this.obtenerInformacionEjecutoriasDetalleResult, other.getObtenerInformacionEjecutoriasDetalleResult())));
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
        if (getObtenerInformacionEjecutoriasDetalleResult() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getObtenerInformacionEjecutoriasDetalleResult());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getObtenerInformacionEjecutoriasDetalleResult(), i);
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
        new org.apache.axis.description.TypeDesc(ObtenerInformacionEjecutoriasDetalleResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">ObtenerInformacionEjecutoriasDetalleResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("obtenerInformacionEjecutoriasDetalleResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ObtenerInformacionEjecutoriasDetalleResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Ejecutorias", "EjecutoriaDetalle"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Ejecutorias", "EjecutoriaDetalle"));
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
