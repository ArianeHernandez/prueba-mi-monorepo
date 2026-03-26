/**
 * WSActualizarRol_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.osmoautenticador.servicioweb.actualizarRol;

public class WSActualizarRol_ServiceLocator extends org.apache.axis.client.Service implements com.osmosyscol.osmoautenticador.servicioweb.actualizarRol.WSActualizarRol_Service {

    public WSActualizarRol_ServiceLocator() {
    }


    public WSActualizarRol_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WSActualizarRol_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WSActualizarRolSOAP
    private java.lang.String WSActualizarRolSOAP_address = "http://127.0.0.1:8080/OsmoAutenticador/services2/WSActualizarRol";

    public java.lang.String getWSActualizarRolSOAPAddress() {
        return WSActualizarRolSOAP_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WSActualizarRolSOAPWSDDServiceName = "WSActualizarRolSOAP";

    public java.lang.String getWSActualizarRolSOAPWSDDServiceName() {
        return WSActualizarRolSOAPWSDDServiceName;
    }

    public void setWSActualizarRolSOAPWSDDServiceName(java.lang.String name) {
        WSActualizarRolSOAPWSDDServiceName = name;
    }

    public com.osmosyscol.osmoautenticador.servicioweb.actualizarRol.WSActualizarRol_PortType getWSActualizarRolSOAP() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WSActualizarRolSOAP_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWSActualizarRolSOAP(endpoint);
    }

    public com.osmosyscol.osmoautenticador.servicioweb.actualizarRol.WSActualizarRol_PortType getWSActualizarRolSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.osmosyscol.osmoautenticador.servicioweb.actualizarRol.WSActualizarRolSOAPSoapBindingStub _stub = new com.osmosyscol.osmoautenticador.servicioweb.actualizarRol.WSActualizarRolSOAPSoapBindingStub(portAddress, this);
            _stub.setPortName(getWSActualizarRolSOAPWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWSActualizarRolSOAPEndpointAddress(java.lang.String address) {
        WSActualizarRolSOAP_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.osmosyscol.osmoautenticador.servicioweb.actualizarRol.WSActualizarRol_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.osmosyscol.osmoautenticador.servicioweb.actualizarRol.WSActualizarRolSOAPSoapBindingStub _stub = new com.osmosyscol.osmoautenticador.servicioweb.actualizarRol.WSActualizarRolSOAPSoapBindingStub(new java.net.URL(WSActualizarRolSOAP_address), this);
                _stub.setPortName(getWSActualizarRolSOAPWSDDServiceName());
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
        if ("WSActualizarRolSOAP".equals(inputPortName)) {
            return getWSActualizarRolSOAP();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://actualizarRol.servicioweb.osmoautenticador.osmosyscol.com", "WSActualizarRol");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://actualizarRol.servicioweb.osmoautenticador.osmosyscol.com", "WSActualizarRolSOAP"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WSActualizarRolSOAP".equals(portName)) {
            setWSActualizarRolSOAPEndpointAddress(address);
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
