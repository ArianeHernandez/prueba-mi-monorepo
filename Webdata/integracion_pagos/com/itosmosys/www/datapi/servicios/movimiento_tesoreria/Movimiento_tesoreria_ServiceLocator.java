/**
 * Movimiento_tesoreria_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.itosmosys.www.datapi.servicios.movimiento_tesoreria;

public class Movimiento_tesoreria_ServiceLocator extends org.apache.axis.client.Service implements com.itosmosys.www.datapi.servicios.movimiento_tesoreria.Movimiento_tesoreria_Service {

    public Movimiento_tesoreria_ServiceLocator() {
    }


    public Movimiento_tesoreria_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public Movimiento_tesoreria_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for movimiento_tesoreriaSOAP
    private java.lang.String movimiento_tesoreriaSOAP_address = "http://127.0.0.1:8080/DatapiGen/ws/movimiento_tesoreria";

    public java.lang.String getmovimiento_tesoreriaSOAPAddress() {
        return movimiento_tesoreriaSOAP_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String movimiento_tesoreriaSOAPWSDDServiceName = "movimiento_tesoreriaSOAP";

    public java.lang.String getmovimiento_tesoreriaSOAPWSDDServiceName() {
        return movimiento_tesoreriaSOAPWSDDServiceName;
    }

    public void setmovimiento_tesoreriaSOAPWSDDServiceName(java.lang.String name) {
        movimiento_tesoreriaSOAPWSDDServiceName = name;
    }

    public com.itosmosys.www.datapi.servicios.movimiento_tesoreria.Movimiento_tesoreria_PortType getmovimiento_tesoreriaSOAP() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(movimiento_tesoreriaSOAP_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getmovimiento_tesoreriaSOAP(endpoint);
    }

    public com.itosmosys.www.datapi.servicios.movimiento_tesoreria.Movimiento_tesoreria_PortType getmovimiento_tesoreriaSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.itosmosys.www.datapi.servicios.movimiento_tesoreria.Movimiento_tesoreriaSOAPStub _stub = new com.itosmosys.www.datapi.servicios.movimiento_tesoreria.Movimiento_tesoreriaSOAPStub(portAddress, this);
            _stub.setPortName(getmovimiento_tesoreriaSOAPWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setmovimiento_tesoreriaSOAPEndpointAddress(java.lang.String address) {
        movimiento_tesoreriaSOAP_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.itosmosys.www.datapi.servicios.movimiento_tesoreria.Movimiento_tesoreria_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.itosmosys.www.datapi.servicios.movimiento_tesoreria.Movimiento_tesoreriaSOAPStub _stub = new com.itosmosys.www.datapi.servicios.movimiento_tesoreria.Movimiento_tesoreriaSOAPStub(new java.net.URL(movimiento_tesoreriaSOAP_address), this);
                _stub.setPortName(getmovimiento_tesoreriaSOAPWSDDServiceName());
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
        if ("movimiento_tesoreriaSOAP".equals(inputPortName)) {
            return getmovimiento_tesoreriaSOAP();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/movimiento_tesoreria", "movimiento_tesoreria");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/movimiento_tesoreria", "movimiento_tesoreriaSOAP"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("movimiento_tesoreriaSOAP".equals(portName)) {
            setmovimiento_tesoreriaSOAPEndpointAddress(address);
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
