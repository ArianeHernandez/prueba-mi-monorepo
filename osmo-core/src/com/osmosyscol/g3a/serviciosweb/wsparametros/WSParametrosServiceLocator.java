/**
 * WSParametrosServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.g3a.serviciosweb.wsparametros;

public class WSParametrosServiceLocator extends org.apache.axis.client.Service implements com.osmosyscol.g3a.serviciosweb.wsparametros.WSParametrosService {

    public WSParametrosServiceLocator() {
    }


    public WSParametrosServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WSParametrosServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WSParametros
    private java.lang.String WSParametros_address = "http://192.168.60.222:8080/G3A/services/WSParametros";

    public java.lang.String getWSParametrosAddress() {
        return WSParametros_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WSParametrosWSDDServiceName = "WSParametros";

    public java.lang.String getWSParametrosWSDDServiceName() {
        return WSParametrosWSDDServiceName;
    }

    public void setWSParametrosWSDDServiceName(java.lang.String name) {
        WSParametrosWSDDServiceName = name;
    }

    public com.osmosyscol.g3a.serviciosweb.wsparametros.WSParametros getWSParametros() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WSParametros_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWSParametros(endpoint);
    }

    public com.osmosyscol.g3a.serviciosweb.wsparametros.WSParametros getWSParametros(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.osmosyscol.g3a.serviciosweb.wsparametros.WSParametrosSoapBindingStub _stub = new com.osmosyscol.g3a.serviciosweb.wsparametros.WSParametrosSoapBindingStub(portAddress, this);
            _stub.setPortName(getWSParametrosWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWSParametrosEndpointAddress(java.lang.String address) {
        WSParametros_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.osmosyscol.g3a.serviciosweb.wsparametros.WSParametros.class.isAssignableFrom(serviceEndpointInterface)) {
                com.osmosyscol.g3a.serviciosweb.wsparametros.WSParametrosSoapBindingStub _stub = new com.osmosyscol.g3a.serviciosweb.wsparametros.WSParametrosSoapBindingStub(new java.net.URL(WSParametros_address), this);
                _stub.setPortName(getWSParametrosWSDDServiceName());
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
        if ("WSParametros".equals(inputPortName)) {
            return getWSParametros();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://serviciosweb.g3a.itosmosys.com", "WSParametrosService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://serviciosweb.g3a.itosmosys.com", "WSParametros"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WSParametros".equals(portName)) {
            setWSParametrosEndpointAddress(address);
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
