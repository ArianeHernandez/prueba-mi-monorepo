/**
 * ObtenerPorCodigoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class ObtenerPorCodigoResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.CartaModelo obtenerPorCodigoResult;

    public ObtenerPorCodigoResponse() {
    }

    public ObtenerPorCodigoResponse(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.CartaModelo obtenerPorCodigoResult) {
           this.obtenerPorCodigoResult = obtenerPorCodigoResult;
    }


    /**
     * Gets the obtenerPorCodigoResult value for this ObtenerPorCodigoResponse.
     * 
     * @return obtenerPorCodigoResult
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.CartaModelo getObtenerPorCodigoResult() {
        return obtenerPorCodigoResult;
    }


    /**
     * Sets the obtenerPorCodigoResult value for this ObtenerPorCodigoResponse.
     * 
     * @param obtenerPorCodigoResult
     */
    public void setObtenerPorCodigoResult(org.datacontract.schemas._2004._07.InteropSuper_Modelos.CartaModelo obtenerPorCodigoResult) {
        this.obtenerPorCodigoResult = obtenerPorCodigoResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ObtenerPorCodigoResponse)) return false;
        ObtenerPorCodigoResponse other = (ObtenerPorCodigoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.obtenerPorCodigoResult==null && other.getObtenerPorCodigoResult()==null) || 
             (this.obtenerPorCodigoResult!=null &&
              this.obtenerPorCodigoResult.equals(other.getObtenerPorCodigoResult())));
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
        if (getObtenerPorCodigoResult() != null) {
            _hashCode += getObtenerPorCodigoResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ObtenerPorCodigoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">ObtenerPorCodigoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("obtenerPorCodigoResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ObtenerPorCodigoResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "CartaModelo"));
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
