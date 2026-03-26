/**
 * RequiereCTX_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.osmoautenticador.servicioweb.requiereCTX;

public class RequiereCTX_ServiceLocator extends org.apache.axis.client.Service implements com.osmosyscol.osmoautenticador.servicioweb.requiereCTX.RequiereCTX_Service {

    public RequiereCTX_ServiceLocator() {
    }


    public RequiereCTX_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public RequiereCTX_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for requiereCTXSOAP
    private java.lang.String requiereCTXSOAP_address = "http://127.0.0.1:8080/OsmoAutenticador/ws/requiereCTX";

    public java.lang.String getrequiereCTXSOAPAddress() {
        return requiereCTXSOAP_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String requiereCTXSOAPWSDDServiceName = "requiereCTXSOAP";

    public java.lang.String getrequiereCTXSOAPWSDDServiceName() {
        return requiereCTXSOAPWSDDServiceName;
    }

    public void setrequiereCTXSOAPWSDDServiceName(java.lang.String name) {
        requiereCTXSOAPWSDDServiceName = name;
    }

    public com.osmosyscol.osmoautenticador.servicioweb.requiereCTX.RequiereCTX_PortType getrequiereCTXSOAP() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(requiereCTXSOAP_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getrequiereCTXSOAP(endpoint);
    }

    public com.osmosyscol.osmoautenticador.servicioweb.requiereCTX.RequiereCTX_PortType getrequiereCTXSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.osmosyscol.osmoautenticador.servicioweb.requiereCTX.RequiereCTXSOAPStub _stub = new com.osmosyscol.osmoautenticador.servicioweb.requiereCTX.RequiereCTXSOAPStub(portAddress, this);
            _stub.setPortName(getrequiereCTXSOAPWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setrequiereCTXSOAPEndpointAddress(java.lang.String address) {
        requiereCTXSOAP_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.osmosyscol.osmoautenticador.servicioweb.requiereCTX.RequiereCTX_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.osmosyscol.osmoautenticador.servicioweb.requiereCTX.RequiereCTXSOAPStub _stub = new com.osmosyscol.osmoautenticador.servicioweb.requiereCTX.RequiereCTXSOAPStub(new java.net.URL(requiereCTXSOAP_address), this);
                _stub.setPortName(getrequiereCTXSOAPWSDDServiceName());
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
        if ("requiereCTXSOAP".equals(inputPortName)) {
            return getrequiereCTXSOAP();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/requiereCTX", "requiereCTX");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/requiereCTX", "requiereCTXSOAP"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("requiereCTXSOAP".equals(portName)) {
            setrequiereCTXSOAPEndpointAddress(address);
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
