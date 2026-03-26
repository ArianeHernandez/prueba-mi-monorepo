/**
 * Aplicar_devolucion_sp_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.itosmosys.www.datapi.servicios.aplicar_devolucion_sp;

public class Aplicar_devolucion_sp_ServiceLocator extends org.apache.axis.client.Service implements com.itosmosys.www.datapi.servicios.aplicar_devolucion_sp.Aplicar_devolucion_sp_Service {

    public Aplicar_devolucion_sp_ServiceLocator() {
    }


    public Aplicar_devolucion_sp_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public Aplicar_devolucion_sp_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for aplicar_devolucion_spSOAP
    private java.lang.String aplicar_devolucion_spSOAP_address = "http://192.168.1.45:8080/DatapiGen/ws/aplicar_devolucion_sp";

    public java.lang.String getaplicar_devolucion_spSOAPAddress() {
        return aplicar_devolucion_spSOAP_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String aplicar_devolucion_spSOAPWSDDServiceName = "aplicar_devolucion_spSOAP";

    public java.lang.String getaplicar_devolucion_spSOAPWSDDServiceName() {
        return aplicar_devolucion_spSOAPWSDDServiceName;
    }

    public void setaplicar_devolucion_spSOAPWSDDServiceName(java.lang.String name) {
        aplicar_devolucion_spSOAPWSDDServiceName = name;
    }

    public com.itosmosys.www.datapi.servicios.aplicar_devolucion_sp.Aplicar_devolucion_sp_PortType getaplicar_devolucion_spSOAP() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(aplicar_devolucion_spSOAP_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getaplicar_devolucion_spSOAP(endpoint);
    }

    public com.itosmosys.www.datapi.servicios.aplicar_devolucion_sp.Aplicar_devolucion_sp_PortType getaplicar_devolucion_spSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.itosmosys.www.datapi.servicios.aplicar_devolucion_sp.Aplicar_devolucion_spSOAPStub _stub = new com.itosmosys.www.datapi.servicios.aplicar_devolucion_sp.Aplicar_devolucion_spSOAPStub(portAddress, this);
            _stub.setPortName(getaplicar_devolucion_spSOAPWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setaplicar_devolucion_spSOAPEndpointAddress(java.lang.String address) {
        aplicar_devolucion_spSOAP_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.itosmosys.www.datapi.servicios.aplicar_devolucion_sp.Aplicar_devolucion_sp_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.itosmosys.www.datapi.servicios.aplicar_devolucion_sp.Aplicar_devolucion_spSOAPStub _stub = new com.itosmosys.www.datapi.servicios.aplicar_devolucion_sp.Aplicar_devolucion_spSOAPStub(new java.net.URL(aplicar_devolucion_spSOAP_address), this);
                _stub.setPortName(getaplicar_devolucion_spSOAPWSDDServiceName());
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
        if ("aplicar_devolucion_spSOAP".equals(inputPortName)) {
            return getaplicar_devolucion_spSOAP();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/aplicar_devolucion_sp", "aplicar_devolucion_sp");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/aplicar_devolucion_sp", "aplicar_devolucion_spSOAP"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("aplicar_devolucion_spSOAP".equals(portName)) {
            setaplicar_devolucion_spSOAPEndpointAddress(address);
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
