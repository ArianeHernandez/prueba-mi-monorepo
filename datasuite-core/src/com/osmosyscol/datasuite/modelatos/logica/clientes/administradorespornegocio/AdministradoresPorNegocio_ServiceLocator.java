/**
 * AdministradoresPorNegocio_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.datasuite.modelatos.logica.clientes.administradorespornegocio;

public class AdministradoresPorNegocio_ServiceLocator extends org.apache.axis.client.Service implements com.osmosyscol.datasuite.modelatos.logica.clientes.administradorespornegocio.AdministradoresPorNegocio_Service {

    public AdministradoresPorNegocio_ServiceLocator() {
    }


    public AdministradoresPorNegocio_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public AdministradoresPorNegocio_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for AdministradoresPorNegocioSOAP
    private java.lang.String AdministradoresPorNegocioSOAP_address = "http://localhost:7001/SipPagos/ws/AdministradoresPorNegocio";

    public java.lang.String getAdministradoresPorNegocioSOAPAddress() {
        return AdministradoresPorNegocioSOAP_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String AdministradoresPorNegocioSOAPWSDDServiceName = "AdministradoresPorNegocioSOAP";

    public java.lang.String getAdministradoresPorNegocioSOAPWSDDServiceName() {
        return AdministradoresPorNegocioSOAPWSDDServiceName;
    }

    public void setAdministradoresPorNegocioSOAPWSDDServiceName(java.lang.String name) {
        AdministradoresPorNegocioSOAPWSDDServiceName = name;
    }

    public com.osmosyscol.datasuite.modelatos.logica.clientes.administradorespornegocio.AdministradoresPorNegocio_PortType getAdministradoresPorNegocioSOAP() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(AdministradoresPorNegocioSOAP_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getAdministradoresPorNegocioSOAP(endpoint);
    }

    public com.osmosyscol.datasuite.modelatos.logica.clientes.administradorespornegocio.AdministradoresPorNegocio_PortType getAdministradoresPorNegocioSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.osmosyscol.datasuite.modelatos.logica.clientes.administradorespornegocio.AdministradoresPorNegocioSOAPStub _stub = new com.osmosyscol.datasuite.modelatos.logica.clientes.administradorespornegocio.AdministradoresPorNegocioSOAPStub(portAddress, this);
            _stub.setPortName(getAdministradoresPorNegocioSOAPWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setAdministradoresPorNegocioSOAPEndpointAddress(java.lang.String address) {
        AdministradoresPorNegocioSOAP_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.osmosyscol.datasuite.modelatos.logica.clientes.administradorespornegocio.AdministradoresPorNegocio_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.osmosyscol.datasuite.modelatos.logica.clientes.administradorespornegocio.AdministradoresPorNegocioSOAPStub _stub = new com.osmosyscol.datasuite.modelatos.logica.clientes.administradorespornegocio.AdministradoresPorNegocioSOAPStub(new java.net.URL(AdministradoresPorNegocioSOAP_address), this);
                _stub.setPortName(getAdministradoresPorNegocioSOAPWSDDServiceName());
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
        if ("AdministradoresPorNegocioSOAP".equals(inputPortName)) {
            return getAdministradoresPorNegocioSOAP();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/AdministradoresPorNegocio", "AdministradoresPorNegocio");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/AdministradoresPorNegocio", "AdministradoresPorNegocioSOAP"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("AdministradoresPorNegocioSOAP".equals(portName)) {
            setAdministradoresPorNegocioSOAPEndpointAddress(address);
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
