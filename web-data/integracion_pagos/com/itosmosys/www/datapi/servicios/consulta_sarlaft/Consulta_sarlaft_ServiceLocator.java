/**
 * Consulta_sarlaft_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.itosmosys.www.datapi.servicios.consulta_sarlaft;

public class Consulta_sarlaft_ServiceLocator extends org.apache.axis.client.Service implements com.itosmosys.www.datapi.servicios.consulta_sarlaft.Consulta_sarlaft_Service {

    public Consulta_sarlaft_ServiceLocator() {
    }


    public Consulta_sarlaft_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public Consulta_sarlaft_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for consulta_sarlaftSOAP
    private java.lang.String consulta_sarlaftSOAP_address = "http://127.0.0.1:8080/DatapiGen/ws/consulta_sarlaft";

    public java.lang.String getconsulta_sarlaftSOAPAddress() {
        return consulta_sarlaftSOAP_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String consulta_sarlaftSOAPWSDDServiceName = "consulta_sarlaftSOAP";

    public java.lang.String getconsulta_sarlaftSOAPWSDDServiceName() {
        return consulta_sarlaftSOAPWSDDServiceName;
    }

    public void setconsulta_sarlaftSOAPWSDDServiceName(java.lang.String name) {
        consulta_sarlaftSOAPWSDDServiceName = name;
    }

    public com.itosmosys.www.datapi.servicios.consulta_sarlaft.Consulta_sarlaft_PortType getconsulta_sarlaftSOAP() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(consulta_sarlaftSOAP_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getconsulta_sarlaftSOAP(endpoint);
    }

    public com.itosmosys.www.datapi.servicios.consulta_sarlaft.Consulta_sarlaft_PortType getconsulta_sarlaftSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.itosmosys.www.datapi.servicios.consulta_sarlaft.Consulta_sarlaftSOAPStub _stub = new com.itosmosys.www.datapi.servicios.consulta_sarlaft.Consulta_sarlaftSOAPStub(portAddress, this);
            _stub.setPortName(getconsulta_sarlaftSOAPWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setconsulta_sarlaftSOAPEndpointAddress(java.lang.String address) {
        consulta_sarlaftSOAP_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.itosmosys.www.datapi.servicios.consulta_sarlaft.Consulta_sarlaft_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.itosmosys.www.datapi.servicios.consulta_sarlaft.Consulta_sarlaftSOAPStub _stub = new com.itosmosys.www.datapi.servicios.consulta_sarlaft.Consulta_sarlaftSOAPStub(new java.net.URL(consulta_sarlaftSOAP_address), this);
                _stub.setPortName(getconsulta_sarlaftSOAPWSDDServiceName());
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
        if ("consulta_sarlaftSOAP".equals(inputPortName)) {
            return getconsulta_sarlaftSOAP();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/consulta_sarlaft", "consulta_sarlaft");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/consulta_sarlaft", "consulta_sarlaftSOAP"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("consulta_sarlaftSOAP".equals(portName)) {
            setconsulta_sarlaftSOAPEndpointAddress(address);
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
