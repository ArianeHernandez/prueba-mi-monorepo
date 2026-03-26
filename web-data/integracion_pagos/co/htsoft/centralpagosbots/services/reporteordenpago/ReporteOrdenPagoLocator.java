/**
 * ReporteOrdenPagoLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.htsoft.centralpagosbots.services.reporteordenpago;

public class ReporteOrdenPagoLocator extends org.apache.axis.client.Service implements co.htsoft.centralpagosbots.services.reporteordenpago.ReporteOrdenPago {

    public ReporteOrdenPagoLocator() {
    }


    public ReporteOrdenPagoLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ReporteOrdenPagoLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ReporteOrdenPagoHttpSoap11Endpoint
    private java.lang.String ReporteOrdenPagoHttpSoap11Endpoint_address = "http://192.168.6.54:9090/CentralPagosBots/services/ReporteOrdenPago.ReporteOrdenPagoHttpSoap11Endpoint/";

    public java.lang.String getReporteOrdenPagoHttpSoap11EndpointAddress() {
        return ReporteOrdenPagoHttpSoap11Endpoint_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ReporteOrdenPagoHttpSoap11EndpointWSDDServiceName = "ReporteOrdenPagoHttpSoap11Endpoint";

    public java.lang.String getReporteOrdenPagoHttpSoap11EndpointWSDDServiceName() {
        return ReporteOrdenPagoHttpSoap11EndpointWSDDServiceName;
    }

    public void setReporteOrdenPagoHttpSoap11EndpointWSDDServiceName(java.lang.String name) {
        ReporteOrdenPagoHttpSoap11EndpointWSDDServiceName = name;
    }

    public co.htsoft.centralpagosbots.services.reporteordenpago.ReporteOrdenPagoPortType getReporteOrdenPagoHttpSoap11Endpoint() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ReporteOrdenPagoHttpSoap11Endpoint_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getReporteOrdenPagoHttpSoap11Endpoint(endpoint);
    }

    public co.htsoft.centralpagosbots.services.reporteordenpago.ReporteOrdenPagoPortType getReporteOrdenPagoHttpSoap11Endpoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            co.htsoft.centralpagosbots.services.reporteordenpago.ReporteOrdenPagoSoap11BindingStub _stub = new co.htsoft.centralpagosbots.services.reporteordenpago.ReporteOrdenPagoSoap11BindingStub(portAddress, this);
            _stub.setPortName(getReporteOrdenPagoHttpSoap11EndpointWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setReporteOrdenPagoHttpSoap11EndpointEndpointAddress(java.lang.String address) {
        ReporteOrdenPagoHttpSoap11Endpoint_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (co.htsoft.centralpagosbots.services.reporteordenpago.ReporteOrdenPagoPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                co.htsoft.centralpagosbots.services.reporteordenpago.ReporteOrdenPagoSoap11BindingStub _stub = new co.htsoft.centralpagosbots.services.reporteordenpago.ReporteOrdenPagoSoap11BindingStub(new java.net.URL(ReporteOrdenPagoHttpSoap11Endpoint_address), this);
                _stub.setPortName(getReporteOrdenPagoHttpSoap11EndpointWSDDServiceName());
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
        if ("ReporteOrdenPagoHttpSoap11Endpoint".equals(inputPortName)) {
            return getReporteOrdenPagoHttpSoap11Endpoint();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://services.centralpagosbots.htsoft.co", "ReporteOrdenPago");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://services.centralpagosbots.htsoft.co", "ReporteOrdenPagoHttpSoap11Endpoint"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ReporteOrdenPagoHttpSoap11Endpoint".equals(portName)) {
            setReporteOrdenPagoHttpSoap11EndpointEndpointAddress(address);
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
