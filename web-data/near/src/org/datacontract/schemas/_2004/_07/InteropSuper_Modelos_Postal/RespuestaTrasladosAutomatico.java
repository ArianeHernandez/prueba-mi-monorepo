/**
 * RespuestaTrasladosAutomatico.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal;

public class RespuestaTrasladosAutomatico  extends org.datacontract.schemas._2004._07.InteropSuper_Modelos.RespuestaBase  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.Traslados_Automatico[] listaTrasladosAutomatico;

    public RespuestaTrasladosAutomatico() {
    }

    public RespuestaTrasladosAutomatico(
           java.lang.Integer cantidadRegistros,
           java.lang.String mensajeError,
           org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.Traslados_Automatico[] listaTrasladosAutomatico) {
        super(
            cantidadRegistros,
            mensajeError);
        this.listaTrasladosAutomatico = listaTrasladosAutomatico;
    }


    /**
     * Gets the listaTrasladosAutomatico value for this RespuestaTrasladosAutomatico.
     * 
     * @return listaTrasladosAutomatico
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.Traslados_Automatico[] getListaTrasladosAutomatico() {
        return listaTrasladosAutomatico;
    }


    /**
     * Sets the listaTrasladosAutomatico value for this RespuestaTrasladosAutomatico.
     * 
     * @param listaTrasladosAutomatico
     */
    public void setListaTrasladosAutomatico(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.Traslados_Automatico[] listaTrasladosAutomatico) {
        this.listaTrasladosAutomatico = listaTrasladosAutomatico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RespuestaTrasladosAutomatico)) return false;
        RespuestaTrasladosAutomatico other = (RespuestaTrasladosAutomatico) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.listaTrasladosAutomatico==null && other.getListaTrasladosAutomatico()==null) || 
             (this.listaTrasladosAutomatico!=null &&
              java.util.Arrays.equals(this.listaTrasladosAutomatico, other.getListaTrasladosAutomatico())));
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
        if (getListaTrasladosAutomatico() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaTrasladosAutomatico());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaTrasladosAutomatico(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RespuestaTrasladosAutomatico.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "RespuestaTrasladosAutomatico"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaTrasladosAutomatico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "ListaTrasladosAutomatico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "Traslados_Automatico"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "Traslados_Automatico"));
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
