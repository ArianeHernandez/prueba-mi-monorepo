/**
 * Consultar_entrada_almacen_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.itosmosys.www.datapi.servicios.consultar_entrada_almacen;

public class Consultar_entrada_almacen_ServiceLocator extends org.apache.axis.client.Service implements com.itosmosys.www.datapi.servicios.consultar_entrada_almacen.Consultar_entrada_almacen_Service {

    public Consultar_entrada_almacen_ServiceLocator() {
    }


    public Consultar_entrada_almacen_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public Consultar_entrada_almacen_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for consultar_entrada_almacenSOAP
    private java.lang.String consultar_entrada_almacenSOAP_address = "http://hts-001:8080/SipPte/ws/consultar_entrada_almacen";

    public java.lang.String getconsultar_entrada_almacenSOAPAddress() {
        return consultar_entrada_almacenSOAP_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String consultar_entrada_almacenSOAPWSDDServiceName = "consultar_entrada_almacenSOAP";

    public java.lang.String getconsultar_entrada_almacenSOAPWSDDServiceName() {
        return consultar_entrada_almacenSOAPWSDDServiceName;
    }

    public void setconsultar_entrada_almacenSOAPWSDDServiceName(java.lang.String name) {
        consultar_entrada_almacenSOAPWSDDServiceName = name;
    }

    public com.itosmosys.www.datapi.servicios.consultar_entrada_almacen.Consultar_entrada_almacen_PortType getconsultar_entrada_almacenSOAP() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(consultar_entrada_almacenSOAP_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getconsultar_entrada_almacenSOAP(endpoint);
    }

    public com.itosmosys.www.datapi.servicios.consultar_entrada_almacen.Consultar_entrada_almacen_PortType getconsultar_entrada_almacenSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.itosmosys.www.datapi.servicios.consultar_entrada_almacen.Consultar_entrada_almacenSOAPStub _stub = new com.itosmosys.www.datapi.servicios.consultar_entrada_almacen.Consultar_entrada_almacenSOAPStub(portAddress, this);
            _stub.setPortName(getconsultar_entrada_almacenSOAPWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setconsultar_entrada_almacenSOAPEndpointAddress(java.lang.String address) {
        consultar_entrada_almacenSOAP_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.itosmosys.www.datapi.servicios.consultar_entrada_almacen.Consultar_entrada_almacen_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.itosmosys.www.datapi.servicios.consultar_entrada_almacen.Consultar_entrada_almacenSOAPStub _stub = new com.itosmosys.www.datapi.servicios.consultar_entrada_almacen.Consultar_entrada_almacenSOAPStub(new java.net.URL(consultar_entrada_almacenSOAP_address), this);
                _stub.setPortName(getconsultar_entrada_almacenSOAPWSDDServiceName());
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
        if ("consultar_entrada_almacenSOAP".equals(inputPortName)) {
            return getconsultar_entrada_almacenSOAP();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/consultar_entrada_almacen", "consultar_entrada_almacen");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/consultar_entrada_almacen", "consultar_entrada_almacenSOAP"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("consultar_entrada_almacenSOAP".equals(portName)) {
            setconsultar_entrada_almacenSOAPEndpointAddress(address);
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
