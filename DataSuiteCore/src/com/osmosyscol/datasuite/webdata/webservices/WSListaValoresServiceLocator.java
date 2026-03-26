/**
 * WSListaValoresServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.datasuite.webdata.webservices;

public class WSListaValoresServiceLocator extends org.apache.axis.client.Service implements com.osmosyscol.datasuite.webdata.webservices.WSListaValoresService {

    public WSListaValoresServiceLocator() {
    }


    public WSListaValoresServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WSListaValoresServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WSListaValores
    private java.lang.String WSListaValores_address = "http://192.168.60.109:8080/WebData/services/WSListaValores";

    public java.lang.String getWSListaValoresAddress() {
        return WSListaValores_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WSListaValoresWSDDServiceName = "WSListaValores";

    public java.lang.String getWSListaValoresWSDDServiceName() {
        return WSListaValoresWSDDServiceName;
    }

    public void setWSListaValoresWSDDServiceName(java.lang.String name) {
        WSListaValoresWSDDServiceName = name;
    }

    public com.osmosyscol.datasuite.webdata.webservices.WSListaValores getWSListaValores() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WSListaValores_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWSListaValores(endpoint);
    }

    public com.osmosyscol.datasuite.webdata.webservices.WSListaValores getWSListaValores(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.osmosyscol.datasuite.webdata.webservices.WSListaValoresSoapBindingStub _stub = new com.osmosyscol.datasuite.webdata.webservices.WSListaValoresSoapBindingStub(portAddress, this);
            _stub.setPortName(getWSListaValoresWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWSListaValoresEndpointAddress(java.lang.String address) {
        WSListaValores_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.osmosyscol.datasuite.webdata.webservices.WSListaValores.class.isAssignableFrom(serviceEndpointInterface)) {
                com.osmosyscol.datasuite.webdata.webservices.WSListaValoresSoapBindingStub _stub = new com.osmosyscol.datasuite.webdata.webservices.WSListaValoresSoapBindingStub(new java.net.URL(WSListaValores_address), this);
                _stub.setPortName(getWSListaValoresWSDDServiceName());
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
        if ("WSListaValores".equals(inputPortName)) {
            return getWSListaValores();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://webservices.webdata.datasuite.osmosyscol.com", "WSListaValoresService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://webservices.webdata.datasuite.osmosyscol.com", "WSListaValores"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WSListaValores".equals(portName)) {
            setWSListaValoresEndpointAddress(address);
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
