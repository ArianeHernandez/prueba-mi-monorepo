/**
 * RespuestaGuardarUsuario.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.osmoautenticador.servicioweb.cliente.guardarusuario;

@SuppressWarnings("serial")
public class RespuestaGuardarUsuario implements java.io.Serializable {
	private java.lang.Boolean exitoso;

	private java.lang.String mensaje;

	public RespuestaGuardarUsuario() {
	}

	public RespuestaGuardarUsuario(java.lang.Boolean exitoso, java.lang.String mensaje) {
		this.exitoso = exitoso;
		this.mensaje = mensaje;
	}

	/**
	 * Gets the exitoso value for this RespuestaGuardarUsuario.
	 * 
	 * @return exitoso
	 */
	public java.lang.Boolean getExitoso() {
		return exitoso;
	}

	/**
	 * Sets the exitoso value for this RespuestaGuardarUsuario.
	 * 
	 * @param exitoso
	 */
	public void setExitoso(java.lang.Boolean exitoso) {
		this.exitoso = exitoso;
	}

	/**
	 * Gets the mensaje value for this RespuestaGuardarUsuario.
	 * 
	 * @return mensaje
	 */
	public java.lang.String getMensaje() {
		return mensaje;
	}

	/**
	 * Sets the mensaje value for this RespuestaGuardarUsuario.
	 * 
	 * @param mensaje
	 */
	public void setMensaje(java.lang.String mensaje) {
		this.mensaje = mensaje;
	}

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof RespuestaGuardarUsuario))
			return false;
		RespuestaGuardarUsuario other = (RespuestaGuardarUsuario) obj;
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true && ((this.exitoso == null && other.getExitoso() == null) || (this.exitoso != null && this.exitoso.equals(other.getExitoso()))) && ((this.mensaje == null && other.getMensaje() == null) || (this.mensaje != null && this.mensaje.equals(other.getMensaje())));
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
		if (getExitoso() != null) {
			_hashCode += getExitoso().hashCode();
		}
		if (getMensaje() != null) {
			_hashCode += getMensaje().hashCode();
		}
		__hashCodeCalc = false;
		return _hashCode;
	}

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(RespuestaGuardarUsuario.class, true);

	static {
		typeDesc.setXmlType(new javax.xml.namespace.QName("http://guardarusuario.servicioweb.osmoautenticador.osmosyscol.com", "RespuestaGuardarUsuario"));
		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("exitoso");
		elemField.setXmlName(new javax.xml.namespace.QName("http://guardarusuario.servicioweb.osmoautenticador.osmosyscol.com", "exitoso"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("mensaje");
		elemField.setXmlName(new javax.xml.namespace.QName("http://guardarusuario.servicioweb.osmoautenticador.osmosyscol.com", "mensaje"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
