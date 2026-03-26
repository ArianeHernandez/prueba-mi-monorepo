/**
 * InformacionPersona_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.osmoautenticador.servicioweb.informacionPersona;

public class InformacionPersona_ServiceLocator extends org.apache.axis.client.Service implements com.osmosyscol.osmoautenticador.servicioweb.informacionPersona.InformacionPersona_Service {

    public InformacionPersona_ServiceLocator() {
    }


    public InformacionPersona_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public InformacionPersona_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for informacionPersonaSOAP
    private java.lang.String informacionPersonaSOAP_address = "http://192.168.60.134:8080/OsmoAutenticador/ws/informacionPersona";

    public java.lang.String getinformacionPersonaSOAPAddress() {
        return informacionPersonaSOAP_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String informacionPersonaSOAPWSDDServiceName = "informacionPersonaSOAP";

    public java.lang.String getinformacionPersonaSOAPWSDDServiceName() {
        return informacionPersonaSOAPWSDDServiceName;
    }

    public void setinformacionPersonaSOAPWSDDServiceName(java.lang.String name) {
        informacionPersonaSOAPWSDDServiceName = name;
    }

    public com.osmosyscol.osmoautenticador.servicioweb.informacionPersona.InformacionPersona_PortType getinformacionPersonaSOAP() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(informacionPersonaSOAP_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getinformacionPersonaSOAP(endpoint);
    }

    public com.osmosyscol.osmoautenticador.servicioweb.informacionPersona.InformacionPersona_PortType getinformacionPersonaSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.osmosyscol.osmoautenticador.servicioweb.informacionPersona.InformacionPersonaSOAPStub _stub = new com.osmosyscol.osmoautenticador.servicioweb.informacionPersona.InformacionPersonaSOAPStub(portAddress, this);
            _stub.setPortName(getinformacionPersonaSOAPWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setinformacionPersonaSOAPEndpointAddress(java.lang.String address) {
        informacionPersonaSOAP_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.osmosyscol.osmoautenticador.servicioweb.informacionPersona.InformacionPersona_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.osmosyscol.osmoautenticador.servicioweb.informacionPersona.InformacionPersonaSOAPStub _stub = new com.osmosyscol.osmoautenticador.servicioweb.informacionPersona.InformacionPersonaSOAPStub(new java.net.URL(informacionPersonaSOAP_address), this);
                _stub.setPortName(getinformacionPersonaSOAPWSDDServiceName());
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
        if ("informacionPersonaSOAP".equals(inputPortName)) {
            return getinformacionPersonaSOAP();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/informacionPersona", "informacionPersona");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/informacionPersona", "informacionPersonaSOAP"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("informacionPersonaSOAP".equals(portName)) {
            setinformacionPersonaSOAPEndpointAddress(address);
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
