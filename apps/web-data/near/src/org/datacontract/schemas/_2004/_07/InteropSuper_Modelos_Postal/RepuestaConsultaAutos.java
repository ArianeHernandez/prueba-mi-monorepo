/**
 * RepuestaConsultaAutos.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal;

public class RepuestaConsultaAutos  extends org.datacontract.schemas._2004._07.InteropSuper_Modelos.RespuestaBase  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.C_AutosControl[] listaAutosControl;

    private org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.C_AutosMercantil[] listaAutosMercantil;

    public RepuestaConsultaAutos() {
    }

    public RepuestaConsultaAutos(
           java.lang.Integer cantidadRegistros,
           java.lang.String mensajeError,
           org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.C_AutosControl[] listaAutosControl,
           org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.C_AutosMercantil[] listaAutosMercantil) {
        super(
            cantidadRegistros,
            mensajeError);
        this.listaAutosControl = listaAutosControl;
        this.listaAutosMercantil = listaAutosMercantil;
    }


    /**
     * Gets the listaAutosControl value for this RepuestaConsultaAutos.
     * 
     * @return listaAutosControl
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.C_AutosControl[] getListaAutosControl() {
        return listaAutosControl;
    }


    /**
     * Sets the listaAutosControl value for this RepuestaConsultaAutos.
     * 
     * @param listaAutosControl
     */
    public void setListaAutosControl(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.C_AutosControl[] listaAutosControl) {
        this.listaAutosControl = listaAutosControl;
    }


    /**
     * Gets the listaAutosMercantil value for this RepuestaConsultaAutos.
     * 
     * @return listaAutosMercantil
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.C_AutosMercantil[] getListaAutosMercantil() {
        return listaAutosMercantil;
    }


    /**
     * Sets the listaAutosMercantil value for this RepuestaConsultaAutos.
     * 
     * @param listaAutosMercantil
     */
    public void setListaAutosMercantil(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.C_AutosMercantil[] listaAutosMercantil) {
        this.listaAutosMercantil = listaAutosMercantil;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RepuestaConsultaAutos)) return false;
        RepuestaConsultaAutos other = (RepuestaConsultaAutos) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.listaAutosControl==null && other.getListaAutosControl()==null) || 
             (this.listaAutosControl!=null &&
              java.util.Arrays.equals(this.listaAutosControl, other.getListaAutosControl()))) &&
            ((this.listaAutosMercantil==null && other.getListaAutosMercantil()==null) || 
             (this.listaAutosMercantil!=null &&
              java.util.Arrays.equals(this.listaAutosMercantil, other.getListaAutosMercantil())));
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
        if (getListaAutosControl() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaAutosControl());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaAutosControl(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getListaAutosMercantil() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaAutosMercantil());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaAutosMercantil(), i);
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
        new org.apache.axis.description.TypeDesc(RepuestaConsultaAutos.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "RepuestaConsultaAutos"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaAutosControl");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "listaAutosControl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "C_AutosControl"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "C_AutosControl"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaAutosMercantil");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "listaAutosMercantil"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "C_AutosMercantil"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Postal", "C_AutosMercantil"));
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
