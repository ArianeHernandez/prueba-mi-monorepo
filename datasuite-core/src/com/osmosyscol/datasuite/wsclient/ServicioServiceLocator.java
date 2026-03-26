/**
 * ServicioServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.datasuite.wsclient;

public class ServicioServiceLocator extends org.apache.axis.client.Service implements com.osmosyscol.datasuite.wsclient.ServicioService {

    public ServicioServiceLocator() {
    }


    public ServicioServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ServicioServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for Servicio
    private java.lang.String Servicio_address = "http://localhost:8080/WebService/services/Servicio";

    public java.lang.String getServicioAddress() {
        return Servicio_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ServicioWSDDServiceName = "Servicio";

    public java.lang.String getServicioWSDDServiceName() {
        return ServicioWSDDServiceName;
    }

    public void setServicioWSDDServiceName(java.lang.String name) {
        ServicioWSDDServiceName = name;
    }

    public com.osmosyscol.datasuite.wsclient.Servicio getServicio() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(Servicio_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getServicio(endpoint);
    }

    public com.osmosyscol.datasuite.wsclient.Servicio getServicio(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.osmosyscol.datasuite.wsclient.ServicioSoapBindingStub _stub = new com.osmosyscol.datasuite.wsclient.ServicioSoapBindingStub(portAddress, this);
            _stub.setPortName(getServicioWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setServicioEndpointAddress(java.lang.String address) {
        Servicio_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.osmosyscol.datasuite.wsclient.Servicio.class.isAssignableFrom(serviceEndpointInterface)) {
                com.osmosyscol.datasuite.wsclient.ServicioSoapBindingStub _stub = new com.osmosyscol.datasuite.wsclient.ServicioSoapBindingStub(new java.net.URL(Servicio_address), this);
                _stub.setPortName(getServicioWSDDServiceName());
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
        if ("Servicio".equals(inputPortName)) {
            return getServicio();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://test.datasuite.com", "ServicioService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://test.datasuite.com", "Servicio"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("Servicio".equals(portName)) {
            setServicioEndpointAddress(address);
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
