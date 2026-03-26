/**
 * Aplicar_debito_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.itosmosys.www.datapi.servicios.aplicar_debito;

public class Aplicar_debito_ServiceLocator extends org.apache.axis.client.Service implements com.itosmosys.www.datapi.servicios.aplicar_debito.Aplicar_debito_Service {

    public Aplicar_debito_ServiceLocator() {
    }


    public Aplicar_debito_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public Aplicar_debito_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for aplicar_debitoSOAP
    private java.lang.String aplicar_debitoSOAP_address = "http://127.0.0.1:8080/DatapiGen/ws/aplicar_debito";

    public java.lang.String getaplicar_debitoSOAPAddress() {
        return aplicar_debitoSOAP_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String aplicar_debitoSOAPWSDDServiceName = "aplicar_debitoSOAP";

    public java.lang.String getaplicar_debitoSOAPWSDDServiceName() {
        return aplicar_debitoSOAPWSDDServiceName;
    }

    public void setaplicar_debitoSOAPWSDDServiceName(java.lang.String name) {
        aplicar_debitoSOAPWSDDServiceName = name;
    }

    public com.itosmosys.www.datapi.servicios.aplicar_debito.Aplicar_debito_PortType getaplicar_debitoSOAP() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(aplicar_debitoSOAP_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getaplicar_debitoSOAP(endpoint);
    }

    public com.itosmosys.www.datapi.servicios.aplicar_debito.Aplicar_debito_PortType getaplicar_debitoSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.itosmosys.www.datapi.servicios.aplicar_debito.Aplicar_debitoSOAPStub _stub = new com.itosmosys.www.datapi.servicios.aplicar_debito.Aplicar_debitoSOAPStub(portAddress, this);
            _stub.setPortName(getaplicar_debitoSOAPWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setaplicar_debitoSOAPEndpointAddress(java.lang.String address) {
        aplicar_debitoSOAP_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.itosmosys.www.datapi.servicios.aplicar_debito.Aplicar_debito_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.itosmosys.www.datapi.servicios.aplicar_debito.Aplicar_debitoSOAPStub _stub = new com.itosmosys.www.datapi.servicios.aplicar_debito.Aplicar_debitoSOAPStub(new java.net.URL(aplicar_debitoSOAP_address), this);
                _stub.setPortName(getaplicar_debitoSOAPWSDDServiceName());
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
        if ("aplicar_debitoSOAP".equals(inputPortName)) {
            return getaplicar_debitoSOAP();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/aplicar_debito", "aplicar_debito");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/aplicar_debito", "aplicar_debitoSOAP"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("aplicar_debitoSOAP".equals(portName)) {
            setaplicar_debitoSOAPEndpointAddress(address);
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
