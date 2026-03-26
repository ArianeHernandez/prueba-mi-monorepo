/**
 * BANCOS_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.datasuite.webdata.correval.webservice.bancos;

public class BANCOS_ServiceLocator extends org.apache.axis.client.Service implements com.osmosyscol.datasuite.webdata.correval.webservice.bancos.BANCOS_Service {

    public BANCOS_ServiceLocator() {
    }


    public BANCOS_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public BANCOS_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for BANCOSSOAP
    private java.lang.String BANCOSSOAP_address = "http://localhost:8080/Modelatos/ws/BANCOS";

    public java.lang.String getBANCOSSOAPAddress() {
        return BANCOSSOAP_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String BANCOSSOAPWSDDServiceName = "BANCOSSOAP";

    public java.lang.String getBANCOSSOAPWSDDServiceName() {
        return BANCOSSOAPWSDDServiceName;
    }

    public void setBANCOSSOAPWSDDServiceName(java.lang.String name) {
        BANCOSSOAPWSDDServiceName = name;
    }

    public com.osmosyscol.datasuite.webdata.correval.webservice.bancos.BANCOS_PortType getBANCOSSOAP() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BANCOSSOAP_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBANCOSSOAP(endpoint);
    }

    public com.osmosyscol.datasuite.webdata.correval.webservice.bancos.BANCOS_PortType getBANCOSSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.osmosyscol.datasuite.webdata.correval.webservice.bancos.BANCOSSOAPStub _stub = new com.osmosyscol.datasuite.webdata.correval.webservice.bancos.BANCOSSOAPStub(portAddress, this);
            _stub.setPortName(getBANCOSSOAPWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBANCOSSOAPEndpointAddress(java.lang.String address) {
        BANCOSSOAP_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.osmosyscol.datasuite.webdata.correval.webservice.bancos.BANCOS_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.osmosyscol.datasuite.webdata.correval.webservice.bancos.BANCOSSOAPStub _stub = new com.osmosyscol.datasuite.webdata.correval.webservice.bancos.BANCOSSOAPStub(new java.net.URL(BANCOSSOAP_address), this);
                _stub.setPortName(getBANCOSSOAPWSDDServiceName());
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
        if ("BANCOSSOAP".equals(inputPortName)) {
            return getBANCOSSOAP();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/BANCOS", "BANCOS");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/BANCOS", "BANCOSSOAP"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("BANCOSSOAP".equals(portName)) {
            setBANCOSSOAPEndpointAddress(address);
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
