/**
 * RespuestaConsultaMaestra.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal;

public class RespuestaConsultaMaestra  extends org.datacontract.schemas._2004._07.InteropSuper_Modelos.RespuestaBase  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ConsultaMaestra[] result;

    private java.lang.Integer totalData;

    public RespuestaConsultaMaestra() {
    }

    public RespuestaConsultaMaestra(
           java.lang.Integer cantidadRegistros,
           java.lang.String mensajeError,
           org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ConsultaMaestra[] result,
           java.lang.Integer totalData) {
        super(
            cantidadRegistros,
            mensajeError);
        this.result = result;
        this.totalData = totalData;
    }


    /**
     * Gets the result value for this RespuestaConsultaMaestra.
     * 
     * @return result
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ConsultaMaestra[] getResult() {
        return result;
    }


    /**
     * Sets the result value for this RespuestaConsultaMaestra.
     * 
     * @param result
     */
    public void setResult(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ConsultaMaestra[] result) {
        this.result = result;
    }


    /**
     * Gets the totalData value for this RespuestaConsultaMaestra.
     * 
     * @return totalData
     */
    public java.lang.Integer getTotalData() {
        return totalData;
    }


    /**
     * Sets the totalData value for this RespuestaConsultaMaestra.
     * 
     * @param totalData
     */
    public void setTotalData(java.lang.Integer totalData) {
        this.totalData = totalData;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RespuestaConsultaMaestra)) return false;
        RespuestaConsultaMaestra other = (RespuestaConsultaMaestra) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.result==null && other.getResult()==null) || 
             (this.result!=null &&
              java.util.Arrays.equals(this.result, other.getResult()))) &&
            ((this.totalData==null && other.getTotalData()==null) || 
             (this.totalData!=null &&
              this.totalData.equals(other.getTotalData())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getResult() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResult());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResult(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTotalData() != null) {
            _hashCode += getTotalData().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RespuestaConsultaMaestra.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "RespuestaConsultaMaestra"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("result");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "result"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "ConsultaMaestra"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "ConsultaMaestra"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "totalData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
