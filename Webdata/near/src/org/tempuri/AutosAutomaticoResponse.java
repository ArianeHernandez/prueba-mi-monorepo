/**
 * AutosAutomaticoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class AutosAutomaticoResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaAutosAutomaticoGeneral autosAutomaticoResult;

    public AutosAutomaticoResponse() {
    }

    public AutosAutomaticoResponse(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaAutosAutomaticoGeneral autosAutomaticoResult) {
           this.autosAutomaticoResult = autosAutomaticoResult;
    }


    /**
     * Gets the autosAutomaticoResult value for this AutosAutomaticoResponse.
     * 
     * @return autosAutomaticoResult
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaAutosAutomaticoGeneral getAutosAutomaticoResult() {
        return autosAutomaticoResult;
    }


    /**
     * Sets the autosAutomaticoResult value for this AutosAutomaticoResponse.
     * 
     * @param autosAutomaticoResult
     */
    public void setAutosAutomaticoResult(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaAutosAutomaticoGeneral autosAutomaticoResult) {
        this.autosAutomaticoResult = autosAutomaticoResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AutosAutomaticoResponse)) return false;
        AutosAutomaticoResponse other = (AutosAutomaticoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.autosAutomaticoResult==null && other.getAutosAutomaticoResult()==null) || 
             (this.autosAutomaticoResult!=null &&
              this.autosAutomaticoResult.equals(other.getAutosAutomaticoResult())));
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
        if (getAutosAutomaticoResult() != null) {
            _hashCode += getAutosAutomaticoResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AutosAutomaticoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">AutosAutomaticoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("autosAutomaticoResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "AutosAutomaticoResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "RespuestaAutosAutomaticoGeneral"));
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
