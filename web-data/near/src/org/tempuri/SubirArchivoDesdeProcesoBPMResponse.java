/**
 * SubirArchivoDesdeProcesoBPMResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class SubirArchivoDesdeProcesoBPMResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass subirArchivoDesdeProcesoBPMResult;

    public SubirArchivoDesdeProcesoBPMResponse() {
    }

    public SubirArchivoDesdeProcesoBPMResponse(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass subirArchivoDesdeProcesoBPMResult) {
           this.subirArchivoDesdeProcesoBPMResult = subirArchivoDesdeProcesoBPMResult;
    }


    /**
     * Gets the subirArchivoDesdeProcesoBPMResult value for this SubirArchivoDesdeProcesoBPMResponse.
     * 
     * @return subirArchivoDesdeProcesoBPMResult
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass getSubirArchivoDesdeProcesoBPMResult() {
        return subirArchivoDesdeProcesoBPMResult;
    }


    /**
     * Sets the subirArchivoDesdeProcesoBPMResult value for this SubirArchivoDesdeProcesoBPMResponse.
     * 
     * @param subirArchivoDesdeProcesoBPMResult
     */
    public void setSubirArchivoDesdeProcesoBPMResult(org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass subirArchivoDesdeProcesoBPMResult) {
        this.subirArchivoDesdeProcesoBPMResult = subirArchivoDesdeProcesoBPMResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SubirArchivoDesdeProcesoBPMResponse)) return false;
        SubirArchivoDesdeProcesoBPMResponse other = (SubirArchivoDesdeProcesoBPMResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.subirArchivoDesdeProcesoBPMResult==null && other.getSubirArchivoDesdeProcesoBPMResult()==null) || 
             (this.subirArchivoDesdeProcesoBPMResult!=null &&
              this.subirArchivoDesdeProcesoBPMResult.equals(other.getSubirArchivoDesdeProcesoBPMResult())));
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
        if (getSubirArchivoDesdeProcesoBPMResult() != null) {
            _hashCode += getSubirArchivoDesdeProcesoBPMResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SubirArchivoDesdeProcesoBPMResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">SubirArchivoDesdeProcesoBPMResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subirArchivoDesdeProcesoBPMResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "SubirArchivoDesdeProcesoBPMResult"));
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
