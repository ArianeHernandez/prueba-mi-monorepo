/**
 * Radicacion_ModificarPlanoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class Radicacion_ModificarPlanoResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados.ResultadoSimpleOfRadicacionPlanoPzrEYndD radicacion_ModificarPlanoResult;

    public Radicacion_ModificarPlanoResponse() {
    }

    public Radicacion_ModificarPlanoResponse(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados.ResultadoSimpleOfRadicacionPlanoPzrEYndD radicacion_ModificarPlanoResult) {
           this.radicacion_ModificarPlanoResult = radicacion_ModificarPlanoResult;
    }


    /**
     * Gets the radicacion_ModificarPlanoResult value for this Radicacion_ModificarPlanoResponse.
     * 
     * @return radicacion_ModificarPlanoResult
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados.ResultadoSimpleOfRadicacionPlanoPzrEYndD getRadicacion_ModificarPlanoResult() {
        return radicacion_ModificarPlanoResult;
    }


    /**
     * Sets the radicacion_ModificarPlanoResult value for this Radicacion_ModificarPlanoResponse.
     * 
     * @param radicacion_ModificarPlanoResult
     */
    public void setRadicacion_ModificarPlanoResult(org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados.ResultadoSimpleOfRadicacionPlanoPzrEYndD radicacion_ModificarPlanoResult) {
        this.radicacion_ModificarPlanoResult = radicacion_ModificarPlanoResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Radicacion_ModificarPlanoResponse)) return false;
        Radicacion_ModificarPlanoResponse other = (Radicacion_ModificarPlanoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.radicacion_ModificarPlanoResult==null && other.getRadicacion_ModificarPlanoResult()==null) || 
             (this.radicacion_ModificarPlanoResult!=null &&
              this.radicacion_ModificarPlanoResult.equals(other.getRadicacion_ModificarPlanoResult())));
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
        if (getRadicacion_ModificarPlanoResult() != null) {
            _hashCode += getRadicacion_ModificarPlanoResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Radicacion_ModificarPlanoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">Radicacion_ModificarPlanoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("radicacion_ModificarPlanoResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Radicacion_ModificarPlanoResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.SIGS.Resultados", "ResultadoSimpleOfRadicacionPlanoPzrEYndD"));
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
