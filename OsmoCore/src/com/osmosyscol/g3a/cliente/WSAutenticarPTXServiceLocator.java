/**
 * WSAutenticarPTXServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.g3a.cliente;

public class WSAutenticarPTXServiceLocator extends org.apache.axis.client.Service implements com.osmosyscol.g3a.cliente.WSAutenticarPTXService {

    public WSAutenticarPTXServiceLocator() {
    }


    public WSAutenticarPTXServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WSAutenticarPTXServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WSAutenticarPTX
    private java.lang.String WSAutenticarPTX_address = "http://127.0.0.1:8080/OsmoAutenticador/services2/WSAutenticarPTX";

    public java.lang.String getWSAutenticarPTXAddress() {
        return WSAutenticarPTX_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WSAutenticarPTXWSDDServiceName = "WSAutenticarPTX";

    public java.lang.String getWSAutenticarPTXWSDDServiceName() {
        return WSAutenticarPTXWSDDServiceName;
    }

    public void setWSAutenticarPTXWSDDServiceName(java.lang.String name) {
        WSAutenticarPTXWSDDServiceName = name;
    }

    public com.osmosyscol.g3a.cliente.WSAutenticarPTX getWSAutenticarPTX() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WSAutenticarPTX_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWSAutenticarPTX(endpoint);
    }

    public com.osmosyscol.g3a.cliente.WSAutenticarPTX getWSAutenticarPTX(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.osmosyscol.g3a.cliente.WSAutenticarPTXSoapBindingStub _stub = new com.osmosyscol.g3a.cliente.WSAutenticarPTXSoapBindingStub(portAddress, this);
            _stub.setPortName(getWSAutenticarPTXWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWSAutenticarPTXEndpointAddress(java.lang.String address) {
        WSAutenticarPTX_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.osmosyscol.g3a.cliente.WSAutenticarPTX.class.isAssignableFrom(serviceEndpointInterface)) {
                com.osmosyscol.g3a.cliente.WSAutenticarPTXSoapBindingStub _stub = new com.osmosyscol.g3a.cliente.WSAutenticarPTXSoapBindingStub(new java.net.URL(WSAutenticarPTX_address), this);
                _stub.setPortName(getWSAutenticarPTXWSDDServiceName());
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
        if ("WSAutenticarPTX".equals(inputPortName)) {
            return getWSAutenticarPTX();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.nzwt.it.com", "WSAutenticarPTXService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://autenticar.servicioweb.osmoautenticador.nzwt.it.com", "WSAutenticarPTX"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WSAutenticarPTX".equals(portName)) {
            setWSAutenticarPTXEndpointAddress(address);
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
