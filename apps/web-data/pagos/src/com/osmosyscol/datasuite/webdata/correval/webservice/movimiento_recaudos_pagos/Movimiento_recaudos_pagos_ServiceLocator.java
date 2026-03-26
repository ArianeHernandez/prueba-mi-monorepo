/**
 * Movimiento_recaudos_pagos_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.datasuite.webdata.correval.webservice.movimiento_recaudos_pagos;

public class Movimiento_recaudos_pagos_ServiceLocator extends org.apache.axis.client.Service implements com.osmosyscol.datasuite.webdata.correval.webservice.movimiento_recaudos_pagos.Movimiento_recaudos_pagos_Service {

    public Movimiento_recaudos_pagos_ServiceLocator() {
    }


    public Movimiento_recaudos_pagos_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public Movimiento_recaudos_pagos_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for movimiento_recaudos_pagosSOAP
    private java.lang.String movimiento_recaudos_pagosSOAP_address = "http://localhost:6060/SipPte/ws/movimiento_recaudos_pagos";

    public java.lang.String getmovimiento_recaudos_pagosSOAPAddress() {
        return movimiento_recaudos_pagosSOAP_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String movimiento_recaudos_pagosSOAPWSDDServiceName = "movimiento_recaudos_pagosSOAP";

    public java.lang.String getmovimiento_recaudos_pagosSOAPWSDDServiceName() {
        return movimiento_recaudos_pagosSOAPWSDDServiceName;
    }

    public void setmovimiento_recaudos_pagosSOAPWSDDServiceName(java.lang.String name) {
        movimiento_recaudos_pagosSOAPWSDDServiceName = name;
    }

    public com.osmosyscol.datasuite.webdata.correval.webservice.movimiento_recaudos_pagos.Movimiento_recaudos_pagos_PortType getmovimiento_recaudos_pagosSOAP() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(movimiento_recaudos_pagosSOAP_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getmovimiento_recaudos_pagosSOAP(endpoint);
    }

    public com.osmosyscol.datasuite.webdata.correval.webservice.movimiento_recaudos_pagos.Movimiento_recaudos_pagos_PortType getmovimiento_recaudos_pagosSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.osmosyscol.datasuite.webdata.correval.webservice.movimiento_recaudos_pagos.Movimiento_recaudos_pagosSOAPStub _stub = new com.osmosyscol.datasuite.webdata.correval.webservice.movimiento_recaudos_pagos.Movimiento_recaudos_pagosSOAPStub(portAddress, this);
            _stub.setPortName(getmovimiento_recaudos_pagosSOAPWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setmovimiento_recaudos_pagosSOAPEndpointAddress(java.lang.String address) {
        movimiento_recaudos_pagosSOAP_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.osmosyscol.datasuite.webdata.correval.webservice.movimiento_recaudos_pagos.Movimiento_recaudos_pagos_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.osmosyscol.datasuite.webdata.correval.webservice.movimiento_recaudos_pagos.Movimiento_recaudos_pagosSOAPStub _stub = new com.osmosyscol.datasuite.webdata.correval.webservice.movimiento_recaudos_pagos.Movimiento_recaudos_pagosSOAPStub(new java.net.URL(movimiento_recaudos_pagosSOAP_address), this);
                _stub.setPortName(getmovimiento_recaudos_pagosSOAPWSDDServiceName());
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
        if ("movimiento_recaudos_pagosSOAP".equals(inputPortName)) {
            return getmovimiento_recaudos_pagosSOAP();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/movimiento_recaudos_pagos", "movimiento_recaudos_pagos");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/movimiento_recaudos_pagos", "movimiento_recaudos_pagosSOAP"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("movimiento_recaudos_pagosSOAP".equals(portName)) {
            setmovimiento_recaudos_pagosSOAPEndpointAddress(address);
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
