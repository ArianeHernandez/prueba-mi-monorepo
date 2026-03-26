/**
 * ResultadoOfMedioDeEnvioCloudUyyCEAza.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados;

public class ResultadoOfMedioDeEnvioCloudUyyCEAza  extends org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados.ResultadoBase  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos_Entidades.MedioDeEnvioCloud[] elementos;

    public ResultadoOfMedioDeEnvioCloudUyyCEAza() {
    }

    public ResultadoOfMedioDeEnvioCloudUyyCEAza(
           java.lang.Boolean estado,
           java.lang.String mensaje,
           java.lang.Integer totalRegistros,
           org.datacontract.schemas._2004._07.InteropSuper_Modelos_Entidades.MedioDeEnvioCloud[] elementos) {
        super(
            estado,
            mensaje,
            totalRegistros);
        this.elementos = elementos;
    }


    /**
     * Gets the elementos value for this ResultadoOfMedioDeEnvioCloudUyyCEAza.
     * 
     * @return elementos
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Entidades.MedioDeEnvioCloud[] getElementos() {
        return elementos;
    }


    /**
     * Sets the elementos value for this ResultadoOfMedioDeEnvioCloudUyyCEAza.
     * 
     * @param elementos
     */
    public void setElementos(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Entidades.MedioDeEnvioCloud[] elementos) {
        this.elementos = elementos;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoOfMedioDeEnvioCloudUyyCEAza)) return false;
        ResultadoOfMedioDeEnvioCloudUyyCEAza other = (ResultadoOfMedioDeEnvioCloudUyyCEAza) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.elementos==null && other.getElementos()==null) || 
             (this.elementos!=null &&
              java.util.Arrays.equals(this.elementos, other.getElementos())));
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
        if (getElementos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getElementos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getElementos(), i);
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
        new org.apache.axis.description.TypeDesc(ResultadoOfMedioDeEnvioCloudUyyCEAza.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.SIGS.Resultados", "ResultadoOfMedioDeEnvioCloudUyyCEAza"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elementos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.SIGS.Resultados", "Elementos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Entidades", "MedioDeEnvioCloud"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.Entidades", "MedioDeEnvioCloud"));
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
