/**
 * SubirArchivoDesdeProcesoBPMV2Response.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class SubirArchivoDesdeProcesoBPMV2Response  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass subirArchivoDesdeProcesoBPMV2Result;

    public SubirArchivoDesdeProcesoBPMV2Response() {
    }

    public SubirArchivoDesdeProcesoBPMV2Response(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass subirArchivoDesdeProcesoBPMV2Result) {
           this.subirArchivoDesdeProcesoBPMV2Result = subirArchivoDesdeProcesoBPMV2Result;
    }


    /**
     * Gets the subirArchivoDesdeProcesoBPMV2Result value for this SubirArchivoDesdeProcesoBPMV2Response.
     * 
     * @return subirArchivoDesdeProcesoBPMV2Result
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass getSubirArchivoDesdeProcesoBPMV2Result() {
        return subirArchivoDesdeProcesoBPMV2Result;
    }


    /**
     * Sets the subirArchivoDesdeProcesoBPMV2Result value for this SubirArchivoDesdeProcesoBPMV2Response.
     * 
     * @param subirArchivoDesdeProcesoBPMV2Result
     */
    public void setSubirArchivoDesdeProcesoBPMV2Result(org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass subirArchivoDesdeProcesoBPMV2Result) {
        this.subirArchivoDesdeProcesoBPMV2Result = subirArchivoDesdeProcesoBPMV2Result;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SubirArchivoDesdeProcesoBPMV2Response)) return false;
        SubirArchivoDesdeProcesoBPMV2Response other = (SubirArchivoDesdeProcesoBPMV2Response) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.subirArchivoDesdeProcesoBPMV2Result==null && other.getSubirArchivoDesdeProcesoBPMV2Result()==null) || 
             (this.subirArchivoDesdeProcesoBPMV2Result!=null &&
              this.subirArchivoDesdeProcesoBPMV2Result.equals(other.getSubirArchivoDesdeProcesoBPMV2Result())));
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
        if (getSubirArchivoDesdeProcesoBPMV2Result() != null) {
            _hashCode += getSubirArchivoDesdeProcesoBPMV2Result().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SubirArchivoDesdeProcesoBPMV2Response.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">SubirArchivoDesdeProcesoBPMV2Response"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subirArchivoDesdeProcesoBPMV2Result");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "SubirArchivoDesdeProcesoBPMV2Result"));
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
