/**
 * Radicacion_ObtenerPlanoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class Radicacion_ObtenerPlanoResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.RadicacionPlano radicacion_ObtenerPlanoResult;

    public Radicacion_ObtenerPlanoResponse() {
    }

    public Radicacion_ObtenerPlanoResponse(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.RadicacionPlano radicacion_ObtenerPlanoResult) {
           this.radicacion_ObtenerPlanoResult = radicacion_ObtenerPlanoResult;
    }


    /**
     * Gets the radicacion_ObtenerPlanoResult value for this Radicacion_ObtenerPlanoResponse.
     * 
     * @return radicacion_ObtenerPlanoResult
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.RadicacionPlano getRadicacion_ObtenerPlanoResult() {
        return radicacion_ObtenerPlanoResult;
    }


    /**
     * Sets the radicacion_ObtenerPlanoResult value for this Radicacion_ObtenerPlanoResponse.
     * 
     * @param radicacion_ObtenerPlanoResult
     */
    public void setRadicacion_ObtenerPlanoResult(org.datacontract.schemas._2004._07.InteropSuper_Modelos.RadicacionPlano radicacion_ObtenerPlanoResult) {
        this.radicacion_ObtenerPlanoResult = radicacion_ObtenerPlanoResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Radicacion_ObtenerPlanoResponse)) return false;
        Radicacion_ObtenerPlanoResponse other = (Radicacion_ObtenerPlanoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.radicacion_ObtenerPlanoResult==null && other.getRadicacion_ObtenerPlanoResult()==null) || 
             (this.radicacion_ObtenerPlanoResult!=null &&
              this.radicacion_ObtenerPlanoResult.equals(other.getRadicacion_ObtenerPlanoResult())));
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
        if (getRadicacion_ObtenerPlanoResult() != null) {
            _hashCode += getRadicacion_ObtenerPlanoResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Radicacion_ObtenerPlanoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">Radicacion_ObtenerPlanoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("radicacion_ObtenerPlanoResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Radicacion_ObtenerPlanoResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "RadicacionPlano"));
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
