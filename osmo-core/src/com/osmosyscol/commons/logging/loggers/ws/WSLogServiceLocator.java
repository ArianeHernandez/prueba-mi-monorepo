/**
 * WSLogServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.commons.logging.loggers.ws;

public class WSLogServiceLocator extends org.apache.axis.client.Service implements com.osmosyscol.commons.logging.loggers.ws.WSLogService {

	public WSLogServiceLocator() {
	}


	public WSLogServiceLocator(org.apache.axis.EngineConfiguration config) {
		super(config);
	}

	public WSLogServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
		super(wsdlLoc, sName);
	}

	// Use to get a proxy class for WSLogPort
	private java.lang.String WSLogPort_address = "http://127.0.0.1:8080/OsmoAutenticador/WSLog";

	public java.lang.String getWSLogPortAddress() {
		return WSLogPort_address;
	}

	// The WSDD service name defaults to the port name.
	private java.lang.String WSLogPortWSDDServiceName = "WSLogPort";

	public java.lang.String getWSLogPortWSDDServiceName() {
		return WSLogPortWSDDServiceName;
	}

	public void setWSLogPortWSDDServiceName(java.lang.String name) {
		WSLogPortWSDDServiceName = name;
	}

	public com.osmosyscol.commons.logging.loggers.ws.WSLog getWSLogPort() throws javax.xml.rpc.ServiceException {
		java.net.URL endpoint;
		try {
			endpoint = new java.net.URL(WSLogPort_address);
		}
		catch (java.net.MalformedURLException e) {
			throw new javax.xml.rpc.ServiceException(e);
		}
		return getWSLogPort(endpoint);
	}

	public com.osmosyscol.commons.logging.loggers.ws.WSLog getWSLogPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
		try {
			com.osmosyscol.commons.logging.loggers.ws.WSLogPortBindingStub _stub = new com.osmosyscol.commons.logging.loggers.ws.WSLogPortBindingStub(portAddress, this);
			_stub.setPortName(getWSLogPortWSDDServiceName());
			return _stub;
		}
		catch (org.apache.axis.AxisFault e) {
			return null;
		}
	}

	public void setWSLogPortEndpointAddress(java.lang.String address) {
		WSLogPort_address = address;
	}

	/**
	 * For the given interface, get the stub implementation.
	 * If this service has no port for the given interface,
	 * then ServiceException is thrown.
	 */
	public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
		try {
			if (com.osmosyscol.commons.logging.loggers.ws.WSLog.class.isAssignableFrom(serviceEndpointInterface)) {
				com.osmosyscol.commons.logging.loggers.ws.WSLogPortBindingStub _stub = new com.osmosyscol.commons.logging.loggers.ws.WSLogPortBindingStub(new java.net.URL(WSLogPort_address), this);
				_stub.setPortName(getWSLogPortWSDDServiceName());
				return _stub;
			}
		}
		catch (java.lang.Throwable t) {
			throw new javax.xml.rpc.ServiceException(t);
		}
		throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
	}

	/**
	 * For the given interface, get the stub implementation.
	 * If this service has no port for the given interface,
	 * then ServiceException is thrown.
	 */
	public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
		if (portName == null) {
			return getPort(serviceEndpointInterface);
		}
		java.lang.String inputPortName = portName.getLocalPart();
		if ("WSLogPort".equals(inputPortName)) {
			return getWSLogPort();
		}
		else  {
			java.rmi.Remote _stub = getPort(serviceEndpointInterface);
			((org.apache.axis.client.Stub) _stub).setPortName(portName);
			return _stub;
		}
	}

	public javax.xml.namespace.QName getServiceName() {
		return new javax.xml.namespace.QName("http://log.servicioweb.osmoautenticador.nzwt.it.com/", "WSLogService");
	}

	private java.util.HashSet ports = null;

	public java.util.Iterator getPorts() {
		if (ports == null) {
			ports = new java.util.HashSet();
			ports.add(new javax.xml.namespace.QName("http://log.servicioweb.osmoautenticador.nzwt.it.com/", "WSLogPort"));
		}
		return ports.iterator();
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {

		if ("WSLogPort".equals(portName)) {
			setWSLogPortEndpointAddress(address);
		}
		else 
		{ // Unknown Port Name
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
