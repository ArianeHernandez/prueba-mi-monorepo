/**
 * ApplicationServicesLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.datasuite.correval.listascautelares;

public class ApplicationServicesLocator extends org.apache.axis.client.Service implements com.osmosyscol.datasuite.correval.listascautelares.ApplicationServices {

    public ApplicationServicesLocator() {
    }


    public ApplicationServicesLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ApplicationServicesLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ApplicationServicesPort
    private java.lang.String ApplicationServicesPort_address = "http://190.145.62.66/websvc/SOAP_VerificaTercero.php";

    public java.lang.String getApplicationServicesPortAddress() {
        return ApplicationServicesPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ApplicationServicesPortWSDDServiceName = "ApplicationServicesPort";

    public java.lang.String getApplicationServicesPortWSDDServiceName() {
        return ApplicationServicesPortWSDDServiceName;
    }

    public void setApplicationServicesPortWSDDServiceName(java.lang.String name) {
        ApplicationServicesPortWSDDServiceName = name;
    }

    public com.osmosyscol.datasuite.correval.listascautelares.ApplicationServicesPortType getApplicationServicesPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ApplicationServicesPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getApplicationServicesPort(endpoint);
    }

    public com.osmosyscol.datasuite.correval.listascautelares.ApplicationServicesPortType getApplicationServicesPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.osmosyscol.datasuite.correval.listascautelares.ApplicationServicesBindingStub _stub = new com.osmosyscol.datasuite.correval.listascautelares.ApplicationServicesBindingStub(portAddress, this);
            _stub.setPortName(getApplicationServicesPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setApplicationServicesPortEndpointAddress(java.lang.String address) {
        ApplicationServicesPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.osmosyscol.datasuite.correval.listascautelares.ApplicationServicesPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.osmosyscol.datasuite.correval.listascautelares.ApplicationServicesBindingStub _stub = new com.osmosyscol.datasuite.correval.listascautelares.ApplicationServicesBindingStub(new java.net.URL(ApplicationServicesPort_address), this);
                _stub.setPortName(getApplicationServicesPortWSDDServiceName());
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
        if ("ApplicationServicesPort".equals(inputPortName)) {
            return getApplicationServicesPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://190.145.62.66/soap/ApplicationServices", "ApplicationServices");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://190.145.62.66/soap/ApplicationServices", "ApplicationServicesPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ApplicationServicesPort".equals(portName)) {
            setApplicationServicesPortEndpointAddress(address);
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
