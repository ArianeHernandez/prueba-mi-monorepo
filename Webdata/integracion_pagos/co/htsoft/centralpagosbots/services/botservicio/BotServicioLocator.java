/**
 * BotServicioLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.htsoft.centralpagosbots.services.botservicio;

public class BotServicioLocator extends org.apache.axis.client.Service implements co.htsoft.centralpagosbots.services.botservicio.BotServicio {

    public BotServicioLocator() {
    }


    public BotServicioLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public BotServicioLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for BotServicioHttpSoap11Endpoint
    private java.lang.String BotServicioHttpSoap11Endpoint_address = "http://127.0.0.1:9090/CentralPagosBots/services/BotServicio.BotServicioHttpSoap11Endpoint/";

    public java.lang.String getBotServicioHttpSoap11EndpointAddress() {
        return BotServicioHttpSoap11Endpoint_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String BotServicioHttpSoap11EndpointWSDDServiceName = "BotServicioHttpSoap11Endpoint";

    public java.lang.String getBotServicioHttpSoap11EndpointWSDDServiceName() {
        return BotServicioHttpSoap11EndpointWSDDServiceName;
    }

    public void setBotServicioHttpSoap11EndpointWSDDServiceName(java.lang.String name) {
        BotServicioHttpSoap11EndpointWSDDServiceName = name;
    }

    public co.htsoft.centralpagosbots.services.botservicio.BotServicioPortType getBotServicioHttpSoap11Endpoint() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BotServicioHttpSoap11Endpoint_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBotServicioHttpSoap11Endpoint(endpoint);
    }

    public co.htsoft.centralpagosbots.services.botservicio.BotServicioPortType getBotServicioHttpSoap11Endpoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            co.htsoft.centralpagosbots.services.botservicio.BotServicioSoap11BindingStub _stub = new co.htsoft.centralpagosbots.services.botservicio.BotServicioSoap11BindingStub(portAddress, this);
            _stub.setPortName(getBotServicioHttpSoap11EndpointWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBotServicioHttpSoap11EndpointEndpointAddress(java.lang.String address) {
        BotServicioHttpSoap11Endpoint_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (co.htsoft.centralpagosbots.services.botservicio.BotServicioPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                co.htsoft.centralpagosbots.services.botservicio.BotServicioSoap11BindingStub _stub = new co.htsoft.centralpagosbots.services.botservicio.BotServicioSoap11BindingStub(new java.net.URL(BotServicioHttpSoap11Endpoint_address), this);
                _stub.setPortName(getBotServicioHttpSoap11EndpointWSDDServiceName());
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
        if ("BotServicioHttpSoap11Endpoint".equals(inputPortName)) {
            return getBotServicioHttpSoap11Endpoint();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://services.centralpagosbots.htsoft.co", "BotServicio");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://services.centralpagosbots.htsoft.co", "BotServicioHttpSoap11Endpoint"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("BotServicioHttpSoap11Endpoint".equals(portName)) {
            setBotServicioHttpSoap11EndpointEndpointAddress(address);
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
