/**
 * RespuestaAutenticar.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.osmoautenticador.servicioweb.cliente.autenticar;

@SuppressWarnings("serial")
public class RespuestaAutenticar implements java.io.Serializable {
	private java.lang.Boolean autenticado;

	public RespuestaAutenticar() {
	}

	public RespuestaAutenticar(java.lang.Boolean autenticado) {
		this.autenticado = autenticado;
	}

	/**
	 * Gets the autenticado value for this RespuestaAutenticar.
	 * 
	 * @return autenticado
	 */
	public java.lang.Boolean getAutenticado() {
		return autenticado;
	}

	/**
	 * Sets the autenticado value for this RespuestaAutenticar.
	 * 
	 * @param autenticado
	 */
	public void setAutenticado(java.lang.Boolean autenticado) {
		this.autenticado = autenticado;
	}

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof RespuestaAutenticar))
			return false;
		RespuestaAutenticar other = (RespuestaAutenticar) obj;
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true && ((this.autenticado == null && other.getAutenticado() == null) || (this.autenticado != null && this.autenticado.equals(other.getAutenticado())));
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
		if (getAutenticado() != null) {
			_hashCode += getAutenticado().hashCode();
		}
		__hashCodeCalc = false;
		return _hashCode;
	}

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(RespuestaAutenticar.class, true);

	static {
		typeDesc.setXmlType(new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.osmosyscol.com", "RespuestaAutenticar"));
		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("autenticado");
		elemField.setXmlName(new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.osmosyscol.com", "autenticado"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
	@SuppressWarnings("unchecked")
	public static org.apache.axis.encoding.Serializer getSerializer(java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType) {
		return new org.apache.axis.encoding.ser.BeanSerializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Get Custom Deserializer
	 */
	@SuppressWarnings("unchecked")
	public static org.apache.axis.encoding.Deserializer getDeserializer(java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType) {
		return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
	}

}
