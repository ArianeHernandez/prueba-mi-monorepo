/**
 * SolicitudCambiarClave.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.osmoautenticador.servicioweb.cliente.cambiarclave;

@SuppressWarnings("serial")
public class SolicitudCambiarClave implements java.io.Serializable {
	private java.lang.String aplicacion;

	private java.lang.String clave;

	private java.lang.String clave_nueva;

	private java.lang.String login;

	private java.lang.String tipoclave;

	public SolicitudCambiarClave() {
	}

	public SolicitudCambiarClave(java.lang.String aplicacion, java.lang.String clave, java.lang.String clave_nueva, java.lang.String login, java.lang.String tipoclave) {
		this.aplicacion = aplicacion;
		this.clave = clave;
		this.clave_nueva = clave_nueva;
		this.login = login;
		this.tipoclave = tipoclave;
	}

	/**
	 * Gets the aplicacion value for this SolicitudCambiarClave.
	 * 
	 * @return aplicacion
	 */
	public java.lang.String getAplicacion() {
		return aplicacion;
	}

	/**
	 * Sets the aplicacion value for this SolicitudCambiarClave.
	 * 
	 * @param aplicacion
	 */
	public void setAplicacion(java.lang.String aplicacion) {
		this.aplicacion = aplicacion;
	}

	/**
	 * Gets the clave value for this SolicitudCambiarClave.
	 * 
	 * @return clave
	 */
	public java.lang.String getClave() {
		return clave;
	}

	/**
	 * Sets the clave value for this SolicitudCambiarClave.
	 * 
	 * @param clave
	 */
	public void setClave(java.lang.String clave) {
		this.clave = clave;
	}

	/**
	 * Gets the clave_nueva value for this SolicitudCambiarClave.
	 * 
	 * @return clave_nueva
	 */
	public java.lang.String getClave_nueva() {
		return clave_nueva;
	}

	/**
	 * Sets the clave_nueva value for this SolicitudCambiarClave.
	 * 
	 * @param clave_nueva
	 */
	public void setClave_nueva(java.lang.String clave_nueva) {
		this.clave_nueva = clave_nueva;
	}

	/**
	 * Gets the login value for this SolicitudCambiarClave.
	 * 
	 * @return login
	 */
	public java.lang.String getLogin() {
		return login;
	}

	/**
	 * Sets the login value for this SolicitudCambiarClave.
	 * 
	 * @param login
	 */
	public void setLogin(java.lang.String login) {
		this.login = login;
	}

	/**
	 * Gets the tipoclave value for this SolicitudCambiarClave.
	 * 
	 * @return tipoclave
	 */
	public java.lang.String getTipoclave() {
		return tipoclave;
	}

	/**
	 * Sets the tipoclave value for this SolicitudCambiarClave.
	 * 
	 * @param tipoclave
	 */
	public void setTipoclave(java.lang.String tipoclave) {
		this.tipoclave = tipoclave;
	}

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof SolicitudCambiarClave))
			return false;
		SolicitudCambiarClave other = (SolicitudCambiarClave) obj;
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true && ((this.aplicacion == null && other.getAplicacion() == null) || (this.aplicacion != null && this.aplicacion.equals(other.getAplicacion()))) && ((this.clave == null && other.getClave() == null) || (this.clave != null && this.clave.equals(other.getClave())))
				&& ((this.clave_nueva == null && other.getClave_nueva() == null) || (this.clave_nueva != null && this.clave_nueva.equals(other.getClave_nueva()))) && ((this.login == null && other.getLogin() == null) || (this.login != null && this.login.equals(other.getLogin())))
				&& ((this.tipoclave == null && other.getTipoclave() == null) || (this.tipoclave != null && this.tipoclave.equals(other.getTipoclave())));
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
		if (getClave() != null) {
			_hashCode += getClave().hashCode();
		}
		if (getClave_nueva() != null) {
			_hashCode += getClave_nueva().hashCode();
		}
		if (getLogin() != null) {
			_hashCode += getLogin().hashCode();
		}
		if (getTipoclave() != null) {
			_hashCode += getTipoclave().hashCode();
		}
		__hashCodeCalc = false;
		return _hashCode;
	}

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(SolicitudCambiarClave.class, true);

	static {
		typeDesc.setXmlType(new javax.xml.namespace.QName("http://cambiarclave.servicioweb.osmoautenticador.osmosyscol.com", "SolicitudCambiarClave"));
		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("aplicacion");
		elemField.setXmlName(new javax.xml.namespace.QName("http://cambiarclave.servicioweb.osmoautenticador.osmosyscol.com", "aplicacion"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("clave");
		elemField.setXmlName(new javax.xml.namespace.QName("http://cambiarclave.servicioweb.osmoautenticador.osmosyscol.com", "clave"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("clave_nueva");
		elemField.setXmlName(new javax.xml.namespace.QName("http://cambiarclave.servicioweb.osmoautenticador.osmosyscol.com", "clave_nueva"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("login");
		elemField.setXmlName(new javax.xml.namespace.QName("http://cambiarclave.servicioweb.osmoautenticador.osmosyscol.com", "login"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoclave");
		elemField.setXmlName(new javax.xml.namespace.QName("http://cambiarclave.servicioweb.osmoautenticador.osmosyscol.com", "tipoclave"));
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
