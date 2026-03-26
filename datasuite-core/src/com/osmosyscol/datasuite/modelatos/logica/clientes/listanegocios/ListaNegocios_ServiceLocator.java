/**
 * ListaNegocios_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.datasuite.modelatos.logica.clientes.listanegocios;

public class ListaNegocios_ServiceLocator extends org.apache.axis.client.Service implements com.osmosyscol.datasuite.modelatos.logica.clientes.listanegocios.ListaNegocios_Service {

    public ListaNegocios_ServiceLocator() {
    }


    public ListaNegocios_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ListaNegocios_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ListaNegociosSOAP
    private java.lang.String ListaNegociosSOAP_address = "http://localhost:7001/SipPagos/ws/ListaNegocios";

    public java.lang.String getListaNegociosSOAPAddress() {
        return ListaNegociosSOAP_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ListaNegociosSOAPWSDDServiceName = "ListaNegociosSOAP";

    public java.lang.String getListaNegociosSOAPWSDDServiceName() {
        return ListaNegociosSOAPWSDDServiceName;
    }

    public void setListaNegociosSOAPWSDDServiceName(java.lang.String name) {
        ListaNegociosSOAPWSDDServiceName = name;
    }

    public com.osmosyscol.datasuite.modelatos.logica.clientes.listanegocios.ListaNegocios_PortType getListaNegociosSOAP() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ListaNegociosSOAP_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getListaNegociosSOAP(endpoint);
    }

    public com.osmosyscol.datasuite.modelatos.logica.clientes.listanegocios.ListaNegocios_PortType getListaNegociosSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.osmosyscol.datasuite.modelatos.logica.clientes.listanegocios.ListaNegociosSOAPStub _stub = new com.osmosyscol.datasuite.modelatos.logica.clientes.listanegocios.ListaNegociosSOAPStub(portAddress, this);
            _stub.setPortName(getListaNegociosSOAPWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setListaNegociosSOAPEndpointAddress(java.lang.String address) {
        ListaNegociosSOAP_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.osmosyscol.datasuite.modelatos.logica.clientes.listanegocios.ListaNegocios_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.osmosyscol.datasuite.modelatos.logica.clientes.listanegocios.ListaNegociosSOAPStub _stub = new com.osmosyscol.datasuite.modelatos.logica.clientes.listanegocios.ListaNegociosSOAPStub(new java.net.URL(ListaNegociosSOAP_address), this);
                _stub.setPortName(getListaNegociosSOAPWSDDServiceName());
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
        if ("ListaNegociosSOAP".equals(inputPortName)) {
            return getListaNegociosSOAP();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/ListaNegocios", "ListaNegocios");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/ListaNegocios", "ListaNegociosSOAP"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ListaNegociosSOAP".equals(portName)) {
            setListaNegociosSOAPEndpointAddress(address);
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
