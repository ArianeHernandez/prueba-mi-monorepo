/**
 * WSAutenticadorServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.osmoautenticador.servicioweb.cliente.autenticar;

@SuppressWarnings("serial")
public class WSAutenticadorServiceLocator extends org.apache.axis.client.Service implements com.osmosyscol.osmoautenticador.servicioweb.cliente.autenticar.WSAutenticadorService {

	public WSAutenticadorServiceLocator() {
	}

	public WSAutenticadorServiceLocator(org.apache.axis.EngineConfiguration config) {
		super(config);
	}

	public WSAutenticadorServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
		super(wsdlLoc, sName);
	}

	// Use to get a proxy class for WSAutenticador
	private java.lang.String WSAutenticador_address = "http://localhost:8080/OsmoAutenticador/services/WSAutenticador";

	public java.lang.String getWSAutenticadorAddress() {
		return WSAutenticador_address;
	}

	// The WSDD service name defaults to the port name.
	private java.lang.String WSAutenticadorWSDDServiceName = "WSAutenticador";

	public java.lang.String getWSAutenticadorWSDDServiceName() {
		return WSAutenticadorWSDDServiceName;
	}

	public void setWSAutenticadorWSDDServiceName(java.lang.String name) {
		WSAutenticadorWSDDServiceName = name;
	}

	public com.osmosyscol.osmoautenticador.servicioweb.cliente.autenticar.WSAutenticador getWSAutenticador() throws javax.xml.rpc.ServiceException {
		java.net.URL endpoint;
		try {
			endpoint = new java.net.URL(WSAutenticador_address);
		} catch (java.net.MalformedURLException e) {
			throw new javax.xml.rpc.ServiceException(e);
		}
		return getWSAutenticador(endpoint);
	}

	public com.osmosyscol.osmoautenticador.servicioweb.cliente.autenticar.WSAutenticador getWSAutenticador(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
		try {
			com.osmosyscol.osmoautenticador.servicioweb.cliente.autenticar.WSAutenticadorSoapBindingStub _stub = new com.osmosyscol.osmoautenticador.servicioweb.cliente.autenticar.WSAutenticadorSoapBindingStub(portAddress, this);
			_stub.setPortName(getWSAutenticadorWSDDServiceName());
			return _stub;
		} catch (org.apache.axis.AxisFault e) {
			return null;
		}
	}

	public void setWSAutenticadorEndpointAddress(java.lang.String address) {
		WSAutenticador_address = address;
	}

	/**
	 * For the given interface, get the stub implementation. If this service has
	 * no port for the given interface, then ServiceException is thrown.
	 */
	@SuppressWarnings("unchecked")
	public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
		try {
			if (com.osmosyscol.osmoautenticador.servicioweb.cliente.autenticar.WSAutenticador.class.isAssignableFrom(serviceEndpointInterface)) {
				com.osmosyscol.osmoautenticador.servicioweb.cliente.autenticar.WSAutenticadorSoapBindingStub _stub = new com.osmosyscol.osmoautenticador.servicioweb.cliente.autenticar.WSAutenticadorSoapBindingStub(new java.net.URL(WSAutenticador_address), this);
				_stub.setPortName(getWSAutenticadorWSDDServiceName());
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
		if ("WSAutenticador".equals(inputPortName)) {
			return getWSAutenticador();
		} else {
			java.rmi.Remote _stub = getPort(serviceEndpointInterface);
			((org.apache.axis.client.Stub) _stub).setPortName(portName);
			return _stub;
		}
	}

	public javax.xml.namespace.QName getServiceName() {
		return new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.osmosyscol.com", "WSAutenticadorService");
	}

	@SuppressWarnings("unchecked")
	private java.util.HashSet ports = null;

	@SuppressWarnings("unchecked")
	public java.util.Iterator getPorts() {
		if (ports == null) {
			ports = new java.util.HashSet();
			ports.add(new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.osmosyscol.com", "WSAutenticador"));
		}
		return ports.iterator();
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {

		if ("WSAutenticador".equals(portName)) {
			setWSAutenticadorEndpointAddress(address);
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
