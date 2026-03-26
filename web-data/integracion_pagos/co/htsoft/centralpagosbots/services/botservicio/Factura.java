/**
 * Factura.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.htsoft.centralpagosbots.services.botservicio;

public class Factura  implements java.io.Serializable {
    private java.util.Date fecha_factura;

    private java.lang.Integer numero_factura;

    private java.lang.String prefijo_factura;

    private java.lang.Double valor;

    private java.lang.Double valor_iva;

    public Factura() {
    }

    public Factura(
           java.util.Date fecha_factura,
           java.lang.Integer numero_factura,
           java.lang.String prefijo_factura,
           java.lang.Double valor,
           java.lang.Double valor_iva) {
           this.fecha_factura = fecha_factura;
           this.numero_factura = numero_factura;
           this.prefijo_factura = prefijo_factura;
           this.valor = valor;
           this.valor_iva = valor_iva;
    }


    /**
     * Gets the fecha_factura value for this Factura.
     * 
     * @return fecha_factura
     */
    public java.util.Date getFecha_factura() {
        return fecha_factura;
    }


    /**
     * Sets the fecha_factura value for this Factura.
     * 
     * @param fecha_factura
     */
    public void setFecha_factura(java.util.Date fecha_factura) {
        this.fecha_factura = fecha_factura;
    }


    /**
     * Gets the numero_factura value for this Factura.
     * 
     * @return numero_factura
     */
    public java.lang.Integer getNumero_factura() {
        return numero_factura;
    }


    /**
     * Sets the numero_factura value for this Factura.
     * 
     * @param numero_factura
     */
    public void setNumero_factura(java.lang.Integer numero_factura) {
        this.numero_factura = numero_factura;
    }


    /**
     * Gets the prefijo_factura value for this Factura.
     * 
     * @return prefijo_factura
     */
    public java.lang.String getPrefijo_factura() {
        return prefijo_factura;
    }


    /**
     * Sets the prefijo_factura value for this Factura.
     * 
     * @param prefijo_factura
     */
    public void setPrefijo_factura(java.lang.String prefijo_factura) {
        this.prefijo_factura = prefijo_factura;
    }


    /**
     * Gets the valor value for this Factura.
     * 
     * @return valor
     */
    public java.lang.Double getValor() {
        return valor;
    }


    /**
     * Sets the valor value for this Factura.
     * 
     * @param valor
     */
    public void setValor(java.lang.Double valor) {
        this.valor = valor;
    }


    /**
     * Gets the valor_iva value for this Factura.
     * 
     * @return valor_iva
     */
    public java.lang.Double getValor_iva() {
        return valor_iva;
    }


    /**
     * Sets the valor_iva value for this Factura.
     * 
     * @param valor_iva
     */
    public void setValor_iva(java.lang.Double valor_iva) {
        this.valor_iva = valor_iva;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Factura)) return false;
        Factura other = (Factura) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fecha_factura==null && other.getFecha_factura()==null) || 
             (this.fecha_factura!=null &&
              this.fecha_factura.equals(other.getFecha_factura()))) &&
            ((this.numero_factura==null && other.getNumero_factura()==null) || 
             (this.numero_factura!=null &&
              this.numero_factura.equals(other.getNumero_factura()))) &&
            ((this.prefijo_factura==null && other.getPrefijo_factura()==null) || 
             (this.prefijo_factura!=null &&
              this.prefijo_factura.equals(other.getPrefijo_factura()))) &&
            ((this.valor==null && other.getValor()==null) || 
             (this.valor!=null &&
              this.valor.equals(other.getValor()))) &&
            ((this.valor_iva==null && other.getValor_iva()==null) || 
             (this.valor_iva!=null &&
              this.valor_iva.equals(other.getValor_iva())));
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
        if (getFecha_factura() != null) {
            _hashCode += getFecha_factura().hashCode();
        }
        if (getNumero_factura() != null) {
            _hashCode += getNumero_factura().hashCode();
        }
        if (getPrefijo_factura() != null) {
            _hashCode += getPrefijo_factura().hashCode();
        }
        if (getValor() != null) {
            _hashCode += getValor().hashCode();
        }
        if (getValor_iva() != null) {
            _hashCode += getValor_iva().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Factura.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://bots.centralpagosbots.htsoft.co/xsd", "Factura"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecha_factura");
        elemField.setXmlName(new javax.xml.namespace.QName("http://bots.centralpagosbots.htsoft.co/xsd", "fecha_factura"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numero_factura");
        elemField.setXmlName(new javax.xml.namespace.QName("http://bots.centralpagosbots.htsoft.co/xsd", "numero_factura"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prefijo_factura");
        elemField.setXmlName(new javax.xml.namespace.QName("http://bots.centralpagosbots.htsoft.co/xsd", "prefijo_factura"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://bots.centralpagosbots.htsoft.co/xsd", "valor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valor_iva");
        elemField.setXmlName(new javax.xml.namespace.QName("http://bots.centralpagosbots.htsoft.co/xsd", "valor_iva"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
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
