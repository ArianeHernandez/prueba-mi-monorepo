/**
 * Obtener_orden_pago_radicacion_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.itosmosys.www.datapi.servicios.obtener_orden_pago_radicacion;

public class Obtener_orden_pago_radicacion_ServiceLocator extends org.apache.axis.client.Service implements com.itosmosys.www.datapi.servicios.obtener_orden_pago_radicacion.Obtener_orden_pago_radicacion_Service {

    public Obtener_orden_pago_radicacion_ServiceLocator() {
    }


    public Obtener_orden_pago_radicacion_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public Obtener_orden_pago_radicacion_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for obtener_orden_pago_radicacionSOAP
    private java.lang.String obtener_orden_pago_radicacionSOAP_address = "http://127.0.0.1:8080/SipPte/ws/obtener_orden_pago_radicacion";

    public java.lang.String getobtener_orden_pago_radicacionSOAPAddress() {
        return obtener_orden_pago_radicacionSOAP_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String obtener_orden_pago_radicacionSOAPWSDDServiceName = "obtener_orden_pago_radicacionSOAP";

    public java.lang.String getobtener_orden_pago_radicacionSOAPWSDDServiceName() {
        return obtener_orden_pago_radicacionSOAPWSDDServiceName;
    }

    public void setobtener_orden_pago_radicacionSOAPWSDDServiceName(java.lang.String name) {
        obtener_orden_pago_radicacionSOAPWSDDServiceName = name;
    }

    public com.itosmosys.www.datapi.servicios.obtener_orden_pago_radicacion.Obtener_orden_pago_radicacion_PortType getobtener_orden_pago_radicacionSOAP() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(obtener_orden_pago_radicacionSOAP_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getobtener_orden_pago_radicacionSOAP(endpoint);
    }

    public com.itosmosys.www.datapi.servicios.obtener_orden_pago_radicacion.Obtener_orden_pago_radicacion_PortType getobtener_orden_pago_radicacionSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.itosmosys.www.datapi.servicios.obtener_orden_pago_radicacion.Obtener_orden_pago_radicacionSOAPStub _stub = new com.itosmosys.www.datapi.servicios.obtener_orden_pago_radicacion.Obtener_orden_pago_radicacionSOAPStub(portAddress, this);
            _stub.setPortName(getobtener_orden_pago_radicacionSOAPWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setobtener_orden_pago_radicacionSOAPEndpointAddress(java.lang.String address) {
        obtener_orden_pago_radicacionSOAP_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.itosmosys.www.datapi.servicios.obtener_orden_pago_radicacion.Obtener_orden_pago_radicacion_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.itosmosys.www.datapi.servicios.obtener_orden_pago_radicacion.Obtener_orden_pago_radicacionSOAPStub _stub = new com.itosmosys.www.datapi.servicios.obtener_orden_pago_radicacion.Obtener_orden_pago_radicacionSOAPStub(new java.net.URL(obtener_orden_pago_radicacionSOAP_address), this);
                _stub.setPortName(getobtener_orden_pago_radicacionSOAPWSDDServiceName());
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
        if ("obtener_orden_pago_radicacionSOAP".equals(inputPortName)) {
            return getobtener_orden_pago_radicacionSOAP();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/obtener_orden_pago_radicacion", "obtener_orden_pago_radicacion");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/obtener_orden_pago_radicacion", "obtener_orden_pago_radicacionSOAP"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("obtener_orden_pago_radicacionSOAP".equals(portName)) {
            setobtener_orden_pago_radicacionSOAPEndpointAddress(address);
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
