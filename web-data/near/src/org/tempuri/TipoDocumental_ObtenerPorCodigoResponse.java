/**
 * TipoDocumental_ObtenerPorCodigoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class TipoDocumental_ObtenerPorCodigoResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental tipoDocumental_ObtenerPorCodigoResult;

    public TipoDocumental_ObtenerPorCodigoResponse() {
    }

    public TipoDocumental_ObtenerPorCodigoResponse(
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental tipoDocumental_ObtenerPorCodigoResult) {
           this.tipoDocumental_ObtenerPorCodigoResult = tipoDocumental_ObtenerPorCodigoResult;
    }


    /**
     * Gets the tipoDocumental_ObtenerPorCodigoResult value for this TipoDocumental_ObtenerPorCodigoResponse.
     * 
     * @return tipoDocumental_ObtenerPorCodigoResult
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental getTipoDocumental_ObtenerPorCodigoResult() {
        return tipoDocumental_ObtenerPorCodigoResult;
    }


    /**
     * Sets the tipoDocumental_ObtenerPorCodigoResult value for this TipoDocumental_ObtenerPorCodigoResponse.
     * 
     * @param tipoDocumental_ObtenerPorCodigoResult
     */
    public void setTipoDocumental_ObtenerPorCodigoResult(org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental tipoDocumental_ObtenerPorCodigoResult) {
        this.tipoDocumental_ObtenerPorCodigoResult = tipoDocumental_ObtenerPorCodigoResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoDocumental_ObtenerPorCodigoResponse)) return false;
        TipoDocumental_ObtenerPorCodigoResponse other = (TipoDocumental_ObtenerPorCodigoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tipoDocumental_ObtenerPorCodigoResult==null && other.getTipoDocumental_ObtenerPorCodigoResult()==null) || 
             (this.tipoDocumental_ObtenerPorCodigoResult!=null &&
              this.tipoDocumental_ObtenerPorCodigoResult.equals(other.getTipoDocumental_ObtenerPorCodigoResult())));
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
        if (getTipoDocumental_ObtenerPorCodigoResult() != null) {
            _hashCode += getTipoDocumental_ObtenerPorCodigoResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoDocumental_ObtenerPorCodigoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">TipoDocumental_ObtenerPorCodigoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoDocumental_ObtenerPorCodigoResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TipoDocumental_ObtenerPorCodigoResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "TipoDocumental"));
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
