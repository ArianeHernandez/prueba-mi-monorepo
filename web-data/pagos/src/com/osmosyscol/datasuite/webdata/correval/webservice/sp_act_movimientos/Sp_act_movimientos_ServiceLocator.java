/**
 * Sp_act_movimientos_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.datasuite.webdata.correval.webservice.sp_act_movimientos;

public class Sp_act_movimientos_ServiceLocator extends org.apache.axis.client.Service implements com.osmosyscol.datasuite.webdata.correval.webservice.sp_act_movimientos.Sp_act_movimientos_Service {

    public Sp_act_movimientos_ServiceLocator() {
    }


    public Sp_act_movimientos_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public Sp_act_movimientos_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for sp_act_movimientosSOAP
    private java.lang.String sp_act_movimientosSOAP_address = "http://localhost:6060/SipPte/ws/sp_act_movimientos";

    public java.lang.String getsp_act_movimientosSOAPAddress() {
        return sp_act_movimientosSOAP_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String sp_act_movimientosSOAPWSDDServiceName = "sp_act_movimientosSOAP";

    public java.lang.String getsp_act_movimientosSOAPWSDDServiceName() {
        return sp_act_movimientosSOAPWSDDServiceName;
    }

    public void setsp_act_movimientosSOAPWSDDServiceName(java.lang.String name) {
        sp_act_movimientosSOAPWSDDServiceName = name;
    }

    public com.osmosyscol.datasuite.webdata.correval.webservice.sp_act_movimientos.Sp_act_movimientos_PortType getsp_act_movimientosSOAP() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(sp_act_movimientosSOAP_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getsp_act_movimientosSOAP(endpoint);
    }

    public com.osmosyscol.datasuite.webdata.correval.webservice.sp_act_movimientos.Sp_act_movimientos_PortType getsp_act_movimientosSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.osmosyscol.datasuite.webdata.correval.webservice.sp_act_movimientos.Sp_act_movimientosSOAPStub _stub = new com.osmosyscol.datasuite.webdata.correval.webservice.sp_act_movimientos.Sp_act_movimientosSOAPStub(portAddress, this);
            _stub.setPortName(getsp_act_movimientosSOAPWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setsp_act_movimientosSOAPEndpointAddress(java.lang.String address) {
        sp_act_movimientosSOAP_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.osmosyscol.datasuite.webdata.correval.webservice.sp_act_movimientos.Sp_act_movimientos_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.osmosyscol.datasuite.webdata.correval.webservice.sp_act_movimientos.Sp_act_movimientosSOAPStub _stub = new com.osmosyscol.datasuite.webdata.correval.webservice.sp_act_movimientos.Sp_act_movimientosSOAPStub(new java.net.URL(sp_act_movimientosSOAP_address), this);
                _stub.setPortName(getsp_act_movimientosSOAPWSDDServiceName());
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
        if ("sp_act_movimientosSOAP".equals(inputPortName)) {
            return getsp_act_movimientosSOAP();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/sp_act_movimientos", "sp_act_movimientos");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.itosmosys.com/datapi/servicios/sp_act_movimientos", "sp_act_movimientosSOAP"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("sp_act_movimientosSOAP".equals(portName)) {
            setsp_act_movimientosSOAPEndpointAddress(address);
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
