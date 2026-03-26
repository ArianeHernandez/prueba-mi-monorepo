/**
 * Tramite_ObtenerPorCodigoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class Tramite_ObtenerPorCodigoResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite tramite_ObtenerPorCodigoResult;

    public Tramite_ObtenerPorCodigoResponse() {
    }

    public Tramite_ObtenerPorCodigoResponse(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite tramite_ObtenerPorCodigoResult) {
           this.tramite_ObtenerPorCodigoResult = tramite_ObtenerPorCodigoResult;
    }


    /**
     * Gets the tramite_ObtenerPorCodigoResult value for this Tramite_ObtenerPorCodigoResponse.
     * 
     * @return tramite_ObtenerPorCodigoResult
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite getTramite_ObtenerPorCodigoResult() {
        return tramite_ObtenerPorCodigoResult;
    }


    /**
     * Sets the tramite_ObtenerPorCodigoResult value for this Tramite_ObtenerPorCodigoResponse.
     * 
     * @param tramite_ObtenerPorCodigoResult
     */
    public void setTramite_ObtenerPorCodigoResult(org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite tramite_ObtenerPorCodigoResult) {
        this.tramite_ObtenerPorCodigoResult = tramite_ObtenerPorCodigoResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Tramite_ObtenerPorCodigoResponse)) return false;
        Tramite_ObtenerPorCodigoResponse other = (Tramite_ObtenerPorCodigoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tramite_ObtenerPorCodigoResult==null && other.getTramite_ObtenerPorCodigoResult()==null) || 
             (this.tramite_ObtenerPorCodigoResult!=null &&
              this.tramite_ObtenerPorCodigoResult.equals(other.getTramite_ObtenerPorCodigoResult())));
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
        if (getTramite_ObtenerPorCodigoResult() != null) {
            _hashCode += getTramite_ObtenerPorCodigoResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Tramite_ObtenerPorCodigoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">Tramite_ObtenerPorCodigoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tramite_ObtenerPorCodigoResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Tramite_ObtenerPorCodigoResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "Tramite"));
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
