/**
 * WSGuardarUsuarioServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.osmoautenticador.servicioweb.cliente.guardarusuario;

@SuppressWarnings("serial")
public class WSGuardarUsuarioServiceLocator extends org.apache.axis.client.Service implements com.osmosyscol.osmoautenticador.servicioweb.cliente.guardarusuario.WSGuardarUsuarioService {

	public WSGuardarUsuarioServiceLocator() {
	}

	public WSGuardarUsuarioServiceLocator(org.apache.axis.EngineConfiguration config) {
		super(config);
	}

	public WSGuardarUsuarioServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
		super(wsdlLoc, sName);
	}

	// Use to get a proxy class for WSGuardarUsuario
	private java.lang.String WSGuardarUsuario_address = "http://localhost:8080/OsmoAutenticador/services/WSGuardarUsuario";

	public java.lang.String getWSGuardarUsuarioAddress() {
		return WSGuardarUsuario_address;
	}

	// The WSDD service name defaults to the port name.
	private java.lang.String WSGuardarUsuarioWSDDServiceName = "WSGuardarUsuario";

	public java.lang.String getWSGuardarUsuarioWSDDServiceName() {
		return WSGuardarUsuarioWSDDServiceName;
	}

	public void setWSGuardarUsuarioWSDDServiceName(java.lang.String name) {
		WSGuardarUsuarioWSDDServiceName = name;
	}

	public com.osmosyscol.osmoautenticador.servicioweb.cliente.guardarusuario.WSGuardarUsuario getWSGuardarUsuario() throws javax.xml.rpc.ServiceException {
		java.net.URL endpoint;
		try {
			endpoint = new java.net.URL(WSGuardarUsuario_address);
		} catch (java.net.MalformedURLException e) {
			throw new javax.xml.rpc.ServiceException(e);
		}
		return getWSGuardarUsuario(endpoint);
	}

	public com.osmosyscol.osmoautenticador.servicioweb.cliente.guardarusuario.WSGuardarUsuario getWSGuardarUsuario(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
		try {
			com.osmosyscol.osmoautenticador.servicioweb.cliente.guardarusuario.WSGuardarUsuarioSoapBindingStub _stub = new com.osmosyscol.osmoautenticador.servicioweb.cliente.guardarusuario.WSGuardarUsuarioSoapBindingStub(portAddress, this);
			_stub.setPortName(getWSGuardarUsuarioWSDDServiceName());
			return _stub;
		} catch (org.apache.axis.AxisFault e) {
			return null;
		}
	}

	public void setWSGuardarUsuarioEndpointAddress(java.lang.String address) {
		WSGuardarUsuario_address = address;
	}

	/**
	 * For the given interface, get the stub implementation. If this service has
	 * no port for the given interface, then ServiceException is thrown.
	 */
	@SuppressWarnings("unchecked")
	public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
		try {
			if (com.osmosyscol.osmoautenticador.servicioweb.cliente.guardarusuario.WSGuardarUsuario.class.isAssignableFrom(serviceEndpointInterface)) {
				com.osmosyscol.osmoautenticador.servicioweb.cliente.guardarusuario.WSGuardarUsuarioSoapBindingStub _stub = new com.osmosyscol.osmoautenticador.servicioweb.cliente.guardarusuario.WSGuardarUsuarioSoapBindingStub(new java.net.URL(WSGuardarUsuario_address), this);
				_stub.setPortName(getWSGuardarUsuarioWSDDServiceName());
				return _stub;
			}
		} catch (java.lang.Throwable t) {
			throw new javax.xml.rpc.ServiceException(t);
		}
		throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
	}

	/**
	 * For the given interface, get the stub implementation. If this service has
	 * no port for the given interface, then ServiceException is thrown.
	 */
	@SuppressWarnings("unchecked")
	public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
		if (portName == null) {
			return getPort(serviceEndpointInterface);
		}
		java.lang.String inputPortName = portName.getLocalPart();
		if ("WSGuardarUsuario".equals(inputPortName)) {
			return getWSGuardarUsuario();
		} else {
			java.rmi.Remote _stub = getPort(serviceEndpointInterface);
			((org.apache.axis.client.Stub) _stub).setPortName(portName);
			return _stub;
		}
	}

	public javax.xml.namespace.QName getServiceName() {
		return new javax.xml.namespace.QName("http://guardarusuario.servicioweb.osmoautenticador.osmosyscol.com", "WSGuardarUsuarioService");
	}

	@SuppressWarnings("unchecked")
	private java.util.HashSet ports = null;

	@SuppressWarnings("unchecked")
	public java.util.Iterator getPorts() {
		if (ports == null) {
			ports = new java.util.HashSet();
			ports.add(new javax.xml.namespace.QName("http://guardarusuario.servicioweb.osmoautenticador.osmosyscol.com", "WSGuardarUsuario"));
		}
		return ports.iterator();
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {

		if ("WSGuardarUsuario".equals(portName)) {
			setWSGuardarUsuarioEndpointAddress(address);
		} else { // Unknown Port Name
			throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
		}
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
		setEndpointAddress(portName.getLocalPart(), address);
	}

}
