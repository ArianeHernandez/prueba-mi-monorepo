/**
 * RespuestaAutosAutomatico.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal;

public class RespuestaAutosAutomatico  extends org.datacontract.schemas._2004._07.InteropSuper_Modelos.RespuestaBase  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.AutosAutomatico[] listaAutosAutomatico;

    public RespuestaAutosAutomatico() {
    }

    public RespuestaAutosAutomatico(
           java.lang.Integer cantidadRegistros,
           java.lang.String mensajeError,
           org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.AutosAutomatico[] listaAutosAutomatico) {
        super(
            cantidadRegistros,
            mensajeError);
        this.listaAutosAutomatico = listaAutosAutomatico;
    }


    /**
     * Gets the listaAutosAutomatico value for this RespuestaAutosAutomatico.
     * 
     * @return listaAutosAutomatico
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.AutosAutomatico[] getListaAutosAutomatico() {
        return listaAutosAutomatico;
    }


    /**
     * Sets the listaAutosAutomatico value for this RespuestaAutosAutomatico.
     * 
     * @param listaAutosAutomatico
     */
    public void setListaAutosAutomatico(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.AutosAutomatico[] listaAutosAutomatico) {
        this.listaAutosAutomatico = listaAutosAutomatico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RespuestaAutosAutomatico)) return false;
        RespuestaAutosAutomatico other = (RespuestaAutosAutomatico) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.listaAutosAutomatico==null && other.getListaAutosAutomatico()==null) || 
             (this.listaAutosAutomatico!=null &&
              java.util.Arrays.equals(this.listaAutosAutomatico, other.getListaAutosAutomatico())));
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
        if (getListaAutosAutomatico() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaAutosAutomatico());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaAutosAutomatico(), i);
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
        new org.apache.axis.description.TypeDesc(RespuestaAutosAutomatico.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "RespuestaAutosAutomatico"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaAutosAutomatico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "ListaAutosAutomatico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "AutosAutomatico"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "AutosAutomatico"));
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
