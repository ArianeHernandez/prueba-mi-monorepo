/**
 * ActualizarFoliosRadicacionResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class ActualizarFoliosRadicacionResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass actualizarFoliosRadicacionResult;

    public ActualizarFoliosRadicacionResponse() {
    }

    public ActualizarFoliosRadicacionResponse(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass actualizarFoliosRadicacionResult) {
           this.actualizarFoliosRadicacionResult = actualizarFoliosRadicacionResult;
    }


    /**
     * Gets the actualizarFoliosRadicacionResult value for this ActualizarFoliosRadicacionResponse.
     * 
     * @return actualizarFoliosRadicacionResult
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass getActualizarFoliosRadicacionResult() {
        return actualizarFoliosRadicacionResult;
    }


    /**
     * Sets the actualizarFoliosRadicacionResult value for this ActualizarFoliosRadicacionResponse.
     * 
     * @param actualizarFoliosRadicacionResult
     */
    public void setActualizarFoliosRadicacionResult(org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass actualizarFoliosRadicacionResult) {
        this.actualizarFoliosRadicacionResult = actualizarFoliosRadicacionResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ActualizarFoliosRadicacionResponse)) return false;
        ActualizarFoliosRadicacionResponse other = (ActualizarFoliosRadicacionResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.actualizarFoliosRadicacionResult==null && other.getActualizarFoliosRadicacionResult()==null) || 
             (this.actualizarFoliosRadicacionResult!=null &&
              this.actualizarFoliosRadicacionResult.equals(other.getActualizarFoliosRadicacionResult())));
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
        if (getActualizarFoliosRadicacionResult() != null) {
            _hashCode += getActualizarFoliosRadicacionResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ActualizarFoliosRadicacionResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">ActualizarFoliosRadicacionResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("actualizarFoliosRadicacionResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ActualizarFoliosRadicacionResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "iBPMWSDevolucionClass"));
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
