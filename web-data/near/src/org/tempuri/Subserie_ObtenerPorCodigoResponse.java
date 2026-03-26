/**
 * Subserie_ObtenerPorCodigoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class Subserie_ObtenerPorCodigoResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfSubSerie_PHy_PfD4I subserie_ObtenerPorCodigoResult;

    public Subserie_ObtenerPorCodigoResponse() {
    }

    public Subserie_ObtenerPorCodigoResponse(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfSubSerie_PHy_PfD4I subserie_ObtenerPorCodigoResult) {
           this.subserie_ObtenerPorCodigoResult = subserie_ObtenerPorCodigoResult;
    }


    /**
     * Gets the subserie_ObtenerPorCodigoResult value for this Subserie_ObtenerPorCodigoResponse.
     * 
     * @return subserie_ObtenerPorCodigoResult
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfSubSerie_PHy_PfD4I getSubserie_ObtenerPorCodigoResult() {
        return subserie_ObtenerPorCodigoResult;
    }


    /**
     * Sets the subserie_ObtenerPorCodigoResult value for this Subserie_ObtenerPorCodigoResponse.
     * 
     * @param subserie_ObtenerPorCodigoResult
     */
    public void setSubserie_ObtenerPorCodigoResult(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfSubSerie_PHy_PfD4I subserie_ObtenerPorCodigoResult) {
        this.subserie_ObtenerPorCodigoResult = subserie_ObtenerPorCodigoResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Subserie_ObtenerPorCodigoResponse)) return false;
        Subserie_ObtenerPorCodigoResponse other = (Subserie_ObtenerPorCodigoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.subserie_ObtenerPorCodigoResult==null && other.getSubserie_ObtenerPorCodigoResult()==null) || 
             (this.subserie_ObtenerPorCodigoResult!=null &&
              this.subserie_ObtenerPorCodigoResult.equals(other.getSubserie_ObtenerPorCodigoResult())));
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
        if (getSubserie_ObtenerPorCodigoResult() != null) {
            _hashCode += getSubserie_ObtenerPorCodigoResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Subserie_ObtenerPorCodigoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">Subserie_ObtenerPorCodigoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subserie_ObtenerPorCodigoResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Subserie_ObtenerPorCodigoResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Comun", "RespuestaOfSubSerie_PHy_PfD4i"));
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
