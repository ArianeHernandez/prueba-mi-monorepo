/**
 * ProcedimientoLVServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.datasuite.wsclient.procedimientolv;

public class ProcedimientoLVServiceLocator extends org.apache.axis.client.Service implements com.osmosyscol.datasuite.wsclient.procedimientolv.ProcedimientoLVService {

    public ProcedimientoLVServiceLocator() {
    }


    public ProcedimientoLVServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ProcedimientoLVServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ProcedimientoLV
    private java.lang.String ProcedimientoLV_address = "http://localhost:8080/SipPagos/services/ProcedimientoLV";

    public java.lang.String getProcedimientoLVAddress() {
        return ProcedimientoLV_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ProcedimientoLVWSDDServiceName = "ProcedimientoLV";

    public java.lang.String getProcedimientoLVWSDDServiceName() {
        return ProcedimientoLVWSDDServiceName;
    }

    public void setProcedimientoLVWSDDServiceName(java.lang.String name) {
        ProcedimientoLVWSDDServiceName = name;
    }

    public com.osmosyscol.datasuite.wsclient.procedimientolv.ProcedimientoLV getProcedimientoLV() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ProcedimientoLV_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getProcedimientoLV(endpoint);
    }

    public com.osmosyscol.datasuite.wsclient.procedimientolv.ProcedimientoLV getProcedimientoLV(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.osmosyscol.datasuite.wsclient.procedimientolv.ProcedimientoLVSoapBindingStub _stub = new com.osmosyscol.datasuite.wsclient.procedimientolv.ProcedimientoLVSoapBindingStub(portAddress, this);
            _stub.setPortName(getProcedimientoLVWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setProcedimientoLVEndpointAddress(java.lang.String address) {
        ProcedimientoLV_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.osmosyscol.datasuite.wsclient.procedimientolv.ProcedimientoLV.class.isAssignableFrom(serviceEndpointInterface)) {
                com.osmosyscol.datasuite.wsclient.procedimientolv.ProcedimientoLVSoapBindingStub _stub = new com.osmosyscol.datasuite.wsclient.procedimientolv.ProcedimientoLVSoapBindingStub(new java.net.URL(ProcedimientoLV_address), this);
                _stub.setPortName(getProcedimientoLVWSDDServiceName());
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
        if ("ProcedimientoLV".equals(inputPortName)) {
            return getProcedimientoLV();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://procedimientolv.webservice.sippagos.osmosyscol.com", "ProcedimientoLVService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://procedimientolv.webservice.sippagos.osmosyscol.com", "ProcedimientoLV"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ProcedimientoLV".equals(portName)) {
            setProcedimientoLVEndpointAddress(address);
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
