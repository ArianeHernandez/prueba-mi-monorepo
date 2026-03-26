package com.osmosyscol.osmoautenticador.servicioweb.informacionPersona;

public class InformacionPersonaProxy implements com.osmosyscol.osmoautenticador.servicioweb.informacionPersona.InformacionPersona_PortType {
  private String _endpoint = null;
  private com.osmosyscol.osmoautenticador.servicioweb.informacionPersona.InformacionPersona_PortType informacionPersona_PortType = null;
  
  public InformacionPersonaProxy() {
    _initInformacionPersonaProxy();
  }
  
  public InformacionPersonaProxy(String endpoint) {
    _endpoint = endpoint;
    _initInformacionPersonaProxy();
  }
  
  private void _initInformacionPersonaProxy() {
    try {
      informacionPersona_PortType = (new com.osmosyscol.osmoautenticador.servicioweb.informacionPersona.InformacionPersona_ServiceLocator()).getinformacionPersonaSOAP();
      if (informacionPersona_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)informacionPersona_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)informacionPersona_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (informacionPersona_PortType != null)
      ((javax.xml.rpc.Stub)informacionPersona_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.osmosyscol.osmoautenticador.servicioweb.informacionPersona.InformacionPersona_PortType getInformacionPersona_PortType() {
    if (informacionPersona_PortType == null)
      _initInformacionPersonaProxy();
    return informacionPersona_PortType;
  }
  
  public com.osmosyscol.osmoautenticador.servicioweb.informacionPersona.TipoElementoSalidainformacionPersona[] informacionPersona(com.osmosyscol.osmoautenticador.servicioweb.informacionPersona.TipoEntradainformacionPersona entrada) throws java.rmi.RemoteException{
    if (informacionPersona_PortType == null)
      _initInformacionPersonaProxy();
    return informacionPersona_PortType.informacionPersona(entrada);
  }
  
  
}