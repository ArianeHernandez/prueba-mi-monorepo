/**
 * Cuaderno_ObtenerPorCodigoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class Cuaderno_ObtenerPorCodigoResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos_Entidades.Cuaderno cuaderno_ObtenerPorCodigoResult;

    public Cuaderno_ObtenerPorCodigoResponse() {
    }

    public Cuaderno_ObtenerPorCodigoResponse(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos_Entidades.Cuaderno cuaderno_ObtenerPorCodigoResult) {
           this.cuaderno_ObtenerPorCodigoResult = cuaderno_ObtenerPorCodigoResult;
    }


    /**
     * Gets the cuaderno_ObtenerPorCodigoResult value for this Cuaderno_ObtenerPorCodigoResponse.
     * 
     * @return cuaderno_ObtenerPorCodigoResult
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Entidades.Cuaderno getCuaderno_ObtenerPorCodigoResult() {
        return cuaderno_ObtenerPorCodigoResult;
    }


    /**
     * Sets the cuaderno_ObtenerPorCodigoResult value for this Cuaderno_ObtenerPorCodigoResponse.
     * 
     * @param cuaderno_ObtenerPorCodigoResult
     */
    public void setCuaderno_ObtenerPorCodigoResult(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Entidades.Cuaderno cuaderno_ObtenerPorCodigoResult) {
        this.cuaderno_ObtenerPorCodigoResult = cuaderno_ObtenerPorCodigoResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Cuaderno_ObtenerPorCodigoResponse)) return false;
        Cuaderno_ObtenerPorCodigoResponse other = (Cuaderno_ObtenerPorCodigoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cuaderno_ObtenerPorCodigoResult==null && other.getCuaderno_ObtenerPorCodigoResult()==null) || 
             (this.cuaderno_ObtenerPorCodigoResult!=null &&
              this.cuaderno_ObtenerPorCodigoResult.equals(other.getCuaderno_ObtenerPorCodigoResult())));
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
        if (getCuaderno_ObtenerPorCodigoResult() != null) {
            _hashCode += getCuaderno_ObtenerPorCodigoResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Cuaderno_ObtenerPorCodigoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">Cuaderno_ObtenerPorCodigoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cuaderno_ObtenerPorCodigoResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Cuaderno_ObtenerPorCodigoResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Entidades", "Cuaderno"));
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
