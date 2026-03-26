/**
 * Traslados_AutomaticoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class Traslados_AutomaticoResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaTrasladosAutomatico traslados_AutomaticoResult;

    public Traslados_AutomaticoResponse() {
    }

    public Traslados_AutomaticoResponse(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaTrasladosAutomatico traslados_AutomaticoResult) {
           this.traslados_AutomaticoResult = traslados_AutomaticoResult;
    }


    /**
     * Gets the traslados_AutomaticoResult value for this Traslados_AutomaticoResponse.
     * 
     * @return traslados_AutomaticoResult
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaTrasladosAutomatico getTraslados_AutomaticoResult() {
        return traslados_AutomaticoResult;
    }


    /**
     * Sets the traslados_AutomaticoResult value for this Traslados_AutomaticoResponse.
     * 
     * @param traslados_AutomaticoResult
     */
    public void setTraslados_AutomaticoResult(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaTrasladosAutomatico traslados_AutomaticoResult) {
        this.traslados_AutomaticoResult = traslados_AutomaticoResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Traslados_AutomaticoResponse)) return false;
        Traslados_AutomaticoResponse other = (Traslados_AutomaticoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.traslados_AutomaticoResult==null && other.getTraslados_AutomaticoResult()==null) || 
             (this.traslados_AutomaticoResult!=null &&
              this.traslados_AutomaticoResult.equals(other.getTraslados_AutomaticoResult())));
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
        if (getTraslados_AutomaticoResult() != null) {
            _hashCode += getTraslados_AutomaticoResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Traslados_AutomaticoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">Traslados_AutomaticoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("traslados_AutomaticoResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Traslados_AutomaticoResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "RespuestaTrasladosAutomatico"));
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
