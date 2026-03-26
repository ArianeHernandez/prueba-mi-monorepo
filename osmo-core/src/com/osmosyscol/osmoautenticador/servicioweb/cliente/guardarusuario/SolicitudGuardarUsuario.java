/**
 * SolicitudGuardarUsuario.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.osmoautenticador.servicioweb.cliente.guardarusuario;

@SuppressWarnings("serial")
public class SolicitudGuardarUsuario implements java.io.Serializable {
	private java.lang.String clave;

	private java.lang.String login;

	private int[] roles;

	public SolicitudGuardarUsuario() {
	}

	public SolicitudGuardarUsuario(java.lang.String clave, java.lang.String login, int[] roles) {
		this.clave = clave;
		this.login = login;
		this.roles = roles;
	}

	/**
	 * Gets the clave value for this SolicitudGuardarUsuario.
	 * 
	 * @return clave
	 */
	public java.lang.String getClave() {
		return clave;
	}

	/**
	 * Sets the clave value for this SolicitudGuardarUsuario.
	 * 
	 * @param clave
	 */
	public void setClave(java.lang.String clave) {
		this.clave = clave;
	}

	/**
	 * Gets the login value for this SolicitudGuardarUsuario.
	 * 
	 * @return login
	 */
	public java.lang.String getLogin() {
		return login;
	}

	/**
	 * Sets the login value for this SolicitudGuardarUsuario.
	 * 
	 * @param login
	 */
	public void setLogin(java.lang.String login) {
		this.login = login;
	}

	/**
	 * Gets the roles value for this SolicitudGuardarUsuario.
	 * 
	 * @return roles
	 */
	public int[] getRoles() {
		return roles;
	}

	/**
	 * Sets the roles value for this SolicitudGuardarUsuario.
	 * 
	 * @param roles
	 */
	public void setRoles(int[] roles) {
		this.roles = roles;
	}

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof SolicitudGuardarUsuario))
			return false;
		SolicitudGuardarUsuario other = (SolicitudGuardarUsuario) obj;
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true && ((this.clave == null && other.getClave() == null) || (this.clave != null && this.clave.equals(other.getClave()))) && ((this.login == null && other.getLogin() == null) || (this.login != null && this.login.equals(other.getLogin()))) && ((this.roles == null && other.getRoles() == null) || (this.roles != null && java.util.Arrays.equals(this.roles, other.getRoles())));
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
		if (getClave() != null) {
			_hashCode += getClave().hashCode();
		}
		if (getLogin() != null) {
			_hashCode += getLogin().hashCode();
		}
		if (getRoles() != null) {
			for (int i = 0; i < java.lang.reflect.Array.getLength(getRoles()); i++) {
				java.lang.Object obj = java.lang.reflect.Array.get(getRoles(), i);
				if (obj != null && !obj.getClass().isArray()) {
					_hashCode += obj.hashCode();
				}
			}
		}
		__hashCodeCalc = false;
		return _hashCode;
	}

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(SolicitudGuardarUsuario.class, true);

	static {
		typeDesc.setXmlType(new javax.xml.namespace.QName("http://guardarusuario.servicioweb.osmoautenticador.osmosyscol.com", "SolicitudGuardarUsuario"));
		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("clave");
		elemField.setXmlName(new javax.xml.namespace.QName("http://guardarusuario.servicioweb.osmoautenticador.osmosyscol.com", "clave"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("login");
		elemField.setXmlName(new javax.xml.namespace.QName("http://guardarusuario.servicioweb.osmoautenticador.osmosyscol.com", "login"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("roles");
		elemField.setXmlName(new javax.xml.namespace.QName("http://guardarusuario.servicioweb.osmoautenticador.osmosyscol.com", "roles"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
		elemField.setNillable(true);
		elemField.setItemQName(new javax.xml.namespace.QName("http://guardarusuario.servicioweb.osmoautenticador.osmosyscol.com", "item"));
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
