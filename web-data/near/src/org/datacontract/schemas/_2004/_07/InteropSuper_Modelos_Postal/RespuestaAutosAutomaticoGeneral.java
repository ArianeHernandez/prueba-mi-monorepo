/**
 * RespuestaAutosAutomaticoGeneral.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal;

public class RespuestaAutosAutomaticoGeneral  extends org.datacontract.schemas._2004._07.InteropSuper_Modelos.RespuestaBase  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.AutosAutomaticoGeneral[] listaAutosAutomatico;

    public RespuestaAutosAutomaticoGeneral() {
    }

    public RespuestaAutosAutomaticoGeneral(
           java.lang.Integer cantidadRegistros,
           java.lang.String mensajeError,
           org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.AutosAutomaticoGeneral[] listaAutosAutomatico) {
        super(
            cantidadRegistros,
            mensajeError);
        this.listaAutosAutomatico = listaAutosAutomatico;
    }


    /**
     * Gets the listaAutosAutomatico value for this RespuestaAutosAutomaticoGeneral.
     * 
     * @return listaAutosAutomatico
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.AutosAutomaticoGeneral[] getListaAutosAutomatico() {
        return listaAutosAutomatico;
    }


    /**
     * Sets the listaAutosAutomatico value for this RespuestaAutosAutomaticoGeneral.
     * 
     * @param listaAutosAutomatico
     */
    public void setListaAutosAutomatico(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.AutosAutomaticoGeneral[] listaAutosAutomatico) {
        this.listaAutosAutomatico = listaAutosAutomatico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RespuestaAutosAutomaticoGeneral)) return false;
        RespuestaAutosAutomaticoGeneral other = (RespuestaAutosAutomaticoGeneral) obj;
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
        new org.apache.axis.description.TypeDesc(RespuestaAutosAutomaticoGeneral.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "RespuestaAutosAutomaticoGeneral"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaAutosAutomatico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "ListaAutosAutomatico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "AutosAutomaticoGeneral"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "AutosAutomaticoGeneral"));
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
