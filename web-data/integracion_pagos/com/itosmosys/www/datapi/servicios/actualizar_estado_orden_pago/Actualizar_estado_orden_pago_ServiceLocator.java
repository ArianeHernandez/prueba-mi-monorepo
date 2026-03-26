/**
 * Actualizar_estado_orden_pago_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.itosmosys.www.datapi.servicios.actualizar_estado_orden_pago;

public class Actualizar_estado_orden_pago_ServiceLocator extends org.apache.axis.client.Service implements com.itosmosys.www.datapi.servicios.actualizar_estado_orden_pago.Actualizar_estado_orden_pago_Service {

    public Actualizar_estado_orden_pago_ServiceLocator() {
    }


    public Actualizar_estado_orden_pago_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public Actualizar_estado_orden_pago_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for actualizar_estado_orden_pagoSOAP
    private java.lang.String actualizar_estado_orden_pagoSOAP_address = "http://hts-001:8080/SipPte/ws/actualizar_estado_orden_pago";

    public java.lang.String getactualizar_estado_orden_pagoSOAPAddress() {
        return actualizar_estado_orden_pagoSOAP_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String actualizar_estado_orden_pagoSOAPWSDDServiceName = "actualizar_estado_orden_pagoSOAP";

    public java.lang.String getactualizar_estado_orden_pagoSOAPWSDDServiceName() {
        return actualizar_estado_orden_pagoSOAPWSDDServiceName;
    }

    public void setactualizar_estado_orden_pagoSOAPWSDDServiceName(java.lang.String name) {
        actualizar_estado_orden_pagoSOAPWSDDServiceName = name;
    }

    public com.itosmosys.www.datapi.servicios.actualizar_estado_orden_pago.Actualizar_estado_orden_pago_PortType getactualizar_estado_orden_pagoSOAP() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(actualizar_estado_orden_pagoSOAP_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getactualizar_estado_orden_pagoSOAP(endpoint);
    }

    public com.itosmosys.www.datapi.servicios.actualizar_estado_orden_pago.Actualizar_estado_orden_pago_PortType getactualizar_estado_orden_pagoSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.itosmosys.www.datapi.servicios.actualizar_estado_orden_pago.Actualizar_estado_orden_pagoSOAPStub _stub = new com.itosmosys.www.datapi.servicios.actualizar_estado_orden_pago.Actualizar_estado_orden_pagoSOAPStub(portAddress, this);
            _stub.setPortName(getactualizar_estado_orden_pagoSOAPWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setactualizar_estado_orden_pagoSOAPEndpointAddress(java.lang.String address) {
        actualizar_estado_orden_pagoSOAP_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.itosmosys.www.datapi.servicios.actualizar_estado_orden_pago.Actualizar_estado_orden_pago_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.itosmosys.www.datapi.servicios.actualizar_estado_orden_pago.Actualizar_estado_orden_pagoSOAPStub _stub = new com.itosmosys.www.datapi.servicios.actualizar_estado_orden_pago.Actualizar_estado_orden_pagoSOAPStub(new java.net.URL(actualizar_estado_orden_pagoSOAP_address), this);
                _stub.setPortName(getactualizar_estado_orden_pagoSOAPWSDDServiceName());
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
        if ("actualizar_estado_orden_pagoSOAP".equals(inputPortName)) {
            return getactualizar_estado_orden_pagoSOAP();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/actualizar_estado_orden_pago", "actualizar_estado_orden_pago");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/actualizar_estado_orden_pago", "actualizar_estado_orden_pagoSOAP"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("actualizar_estado_orden_pagoSOAP".equals(portName)) {
            setactualizar_estado_orden_pagoSOAPEndpointAddress(address);
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
