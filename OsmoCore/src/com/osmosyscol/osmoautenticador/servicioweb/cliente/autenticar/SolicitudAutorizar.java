/**
 * SolicitudAutorizar.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.osmoautenticador.servicioweb.cliente.autenticar;

@SuppressWarnings("serial")
public class SolicitudAutorizar implements java.io.Serializable {
	private java.lang.String aplicacion;

	private java.lang.String login;

	private java.lang.String url;

	public SolicitudAutorizar() {
	}

	public SolicitudAutorizar(java.lang.String aplicacion, java.lang.String login, java.lang.String url) {
		this.aplicacion = aplicacion;
		this.login = login;
		this.url = url;
	}

	/**
	 * Gets the aplicacion value for this SolicitudAutorizar.
	 * 
	 * @return aplicacion
	 */
	public java.lang.String getAplicacion() {
		return aplicacion;
	}

	/**
	 * Sets the aplicacion value for this SolicitudAutorizar.
	 * 
	 * @param aplicacion
	 */
	public void setAplicacion(java.lang.String aplicacion) {
		this.aplicacion = aplicacion;
	}

	/**
	 * Gets the login value for this SolicitudAutorizar.
	 * 
	 * @return login
	 */
	public java.lang.String getLogin() {
		return login;
	}

	/**
	 * Sets the login value for this SolicitudAutorizar.
	 * 
	 * @param login
	 */
	public void setLogin(java.lang.String login) {
		this.login = login;
	}

	/**
	 * Gets the url value for this SolicitudAutorizar.
	 * 
	 * @return url
	 */
	public java.lang.String getUrl() {
		return url;
	}

	/**
	 * Sets the url value for this SolicitudAutorizar.
	 * 
	 * @param url
	 */
	public void setUrl(java.lang.String url) {
		this.url = url;
	}

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof SolicitudAutorizar))
			return false;
		SolicitudAutorizar other = (SolicitudAutorizar) obj;
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true && ((this.aplicacion == null && other.getAplicacion() == null) || (this.aplicacion != null && this.aplicacion.equals(other.getAplicacion()))) && ((this.login == null && other.getLogin() == null) || (this.login != null && this.login.equals(other.getLogin()))) && ((this.url == null && other.getUrl() == null) || (this.url != null && this.url.equals(other.getUrl())));
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
		if (getAplicacion() != null) {
			_hashCode += getAplicacion().hashCode();
		}
		if (getLogin() != null) {
			_hashCode += getLogin().hashCode();
		}
		if (getUrl() != null) {
			_hashCode += getUrl().hashCode();
		}
		__hashCodeCalc = false;
		return _hashCode;
	}

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(SolicitudAutorizar.class, true);

	static {
		typeDesc.setXmlType(new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.osmosyscol.com", "SolicitudAutorizar"));
		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("aplicacion");
		elemField.setXmlName(new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.osmosyscol.com", "aplicacion"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("login");
		elemField.setXmlName(new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.osmosyscol.com", "login"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("url");
		elemField.setXmlName(new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.osmosyscol.com", "url"));
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
