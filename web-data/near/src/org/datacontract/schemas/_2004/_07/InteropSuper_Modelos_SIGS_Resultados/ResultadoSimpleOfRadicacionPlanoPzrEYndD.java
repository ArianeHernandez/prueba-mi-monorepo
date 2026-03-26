/**
 * ResultadoSimpleOfRadicacionPlanoPzrEYndD.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados;

public class ResultadoSimpleOfRadicacionPlanoPzrEYndD  extends org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados.ResultadoBase  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.InteropSuper_Modelos.RadicacionPlano elemento;

    public ResultadoSimpleOfRadicacionPlanoPzrEYndD() {
    }

    public ResultadoSimpleOfRadicacionPlanoPzrEYndD(
           java.lang.Boolean estado,
           java.lang.String mensaje,
           java.lang.Integer totalRegistros,
           org.datacontract.schemas._2004._07.InteropSuper_Modelos.RadicacionPlano elemento) {
        super(
            estado,
            mensaje,
            totalRegistros);
        this.elemento = elemento;
    }


    /**
     * Gets the elemento value for this ResultadoSimpleOfRadicacionPlanoPzrEYndD.
     * 
     * @return elemento
     */
    public org.datacontract.schemas._2004._07.InteropSuper_Modelos.RadicacionPlano getElemento() {
        return elemento;
    }


    /**
     * Sets the elemento value for this ResultadoSimpleOfRadicacionPlanoPzrEYndD.
     * 
     * @param elemento
     */
    public void setElemento(org.datacontract.schemas._2004._07.InteropSuper_Modelos.RadicacionPlano elemento) {
        this.elemento = elemento;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoSimpleOfRadicacionPlanoPzrEYndD)) return false;
        ResultadoSimpleOfRadicacionPlanoPzrEYndD other = (ResultadoSimpleOfRadicacionPlanoPzrEYndD) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.elemento==null && other.getElemento()==null) || 
             (this.elemento!=null &&
              this.elemento.equals(other.getElemento())));
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
        if (getElemento() != null) {
            _hashCode += getElemento().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoSimpleOfRadicacionPlanoPzrEYndD.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.SIGS.Resultados", "ResultadoSimpleOfRadicacionPlanoPzrEYndD"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elemento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos.SIGS.Resultados", "Elemento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/InteropSuper.Modelos", "RadicacionPlano"));
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
