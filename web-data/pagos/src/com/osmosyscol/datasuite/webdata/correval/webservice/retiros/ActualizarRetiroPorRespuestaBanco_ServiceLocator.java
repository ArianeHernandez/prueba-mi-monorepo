/**
 * ActualizarRetiroPorRespuestaBanco_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.datasuite.webdata.correval.webservice.retiros;

public class ActualizarRetiroPorRespuestaBanco_ServiceLocator extends org.apache.axis.client.Service implements com.osmosyscol.datasuite.webdata.correval.webservice.retiros.ActualizarRetiroPorRespuestaBanco_Service {

    public ActualizarRetiroPorRespuestaBanco_ServiceLocator() {
    }


    public ActualizarRetiroPorRespuestaBanco_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ActualizarRetiroPorRespuestaBanco_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ActualizarRetiroPorRespuestaBancoSOAP
    private java.lang.String ActualizarRetiroPorRespuestaBancoSOAP_address = "http://127.0.0.1:9090/Modelatos/ws/ActualizarRetiroPorRespuestaBanco";

    public java.lang.String getActualizarRetiroPorRespuestaBancoSOAPAddress() {
        return ActualizarRetiroPorRespuestaBancoSOAP_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ActualizarRetiroPorRespuestaBancoSOAPWSDDServiceName = "ActualizarRetiroPorRespuestaBancoSOAP";

    public java.lang.String getActualizarRetiroPorRespuestaBancoSOAPWSDDServiceName() {
        return ActualizarRetiroPorRespuestaBancoSOAPWSDDServiceName;
    }

    public void setActualizarRetiroPorRespuestaBancoSOAPWSDDServiceName(java.lang.String name) {
        ActualizarRetiroPorRespuestaBancoSOAPWSDDServiceName = name;
    }

    public com.osmosyscol.datasuite.webdata.correval.webservice.retiros.ActualizarRetiroPorRespuestaBanco_PortType getActualizarRetiroPorRespuestaBancoSOAP() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ActualizarRetiroPorRespuestaBancoSOAP_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getActualizarRetiroPorRespuestaBancoSOAP(endpoint);
    }

    public com.osmosyscol.datasuite.webdata.correval.webservice.retiros.ActualizarRetiroPorRespuestaBanco_PortType getActualizarRetiroPorRespuestaBancoSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.osmosyscol.datasuite.webdata.correval.webservice.retiros.ActualizarRetiroPorRespuestaBancoSOAPStub _stub = new com.osmosyscol.datasuite.webdata.correval.webservice.retiros.ActualizarRetiroPorRespuestaBancoSOAPStub(portAddress, this);
            _stub.setPortName(getActualizarRetiroPorRespuestaBancoSOAPWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setActualizarRetiroPorRespuestaBancoSOAPEndpointAddress(java.lang.String address) {
        ActualizarRetiroPorRespuestaBancoSOAP_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.osmosyscol.datasuite.webdata.correval.webservice.retiros.ActualizarRetiroPorRespuestaBanco_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.osmosyscol.datasuite.webdata.correval.webservice.retiros.ActualizarRetiroPorRespuestaBancoSOAPStub _stub = new com.osmosyscol.datasuite.webdata.correval.webservice.retiros.ActualizarRetiroPorRespuestaBancoSOAPStub(new java.net.URL(ActualizarRetiroPorRespuestaBancoSOAP_address), this);
                _stub.setPortName(getActualizarRetiroPorRespuestaBancoSOAPWSDDServiceName());
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
        if ("ActualizarRetiroPorRespuestaBancoSOAP".equals(inputPortName)) {
            return getActualizarRetiroPorRespuestaBancoSOAP();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/ActualizarRetiroPorRespuestaBanco", "ActualizarRetiroPorRespuestaBanco");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/ActualizarRetiroPorRespuestaBanco", "ActualizarRetiroPorRespuestaBancoSOAP"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ActualizarRetiroPorRespuestaBancoSOAP".equals(portName)) {
            setActualizarRetiroPorRespuestaBancoSOAPEndpointAddress(address);
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
