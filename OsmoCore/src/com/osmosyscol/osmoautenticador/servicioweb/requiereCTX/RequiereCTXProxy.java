package com.osmosyscol.osmoautenticador.servicioweb.requiereCTX;

public class RequiereCTXProxy implements com.osmosyscol.osmoautenticador.servicioweb.requiereCTX.RequiereCTX_PortType {
  private String _endpoint = null;
  private com.osmosyscol.osmoautenticador.servicioweb.requiereCTX.RequiereCTX_PortType requiereCTX_PortType = null;
  
  public RequiereCTXProxy() {
    _initRequiereCTXProxy();
  }
  
  public RequiereCTXProxy(String endpoint) {
    _endpoint = endpoint;
    _initRequiereCTXProxy();
  }
  
  private void _initRequiereCTXProxy() {
    try {
      requiereCTX_PortType = (new com.osmosyscol.osmoautenticador.servicioweb.requiereCTX.RequiereCTX_ServiceLocator()).getrequiereCTXSOAP();
      if (requiereCTX_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)requiereCTX_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)requiereCTX_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (requiereCTX_PortType != null)
      ((javax.xml.rpc.Stub)requiereCTX_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.osmosyscol.osmoautenticador.servicioweb.requiereCTX.RequiereCTX_PortType getRequiereCTX_PortType() {
    if (requiereCTX_PortType == null)
      _initRequiereCTXProxy();
    return requiereCTX_PortType;
  }
  
  public com.osmosyscol.osmoautenticador.servicioweb.requiereCTX.TipoElementoSalidarequiereCTX[] requiereCTX(com.osmosyscol.osmoautenticador.servicioweb.requiereCTX.TipoEntradarequiereCTX entrada) throws java.rmi.RemoteException{
    if (requiereCTX_PortType == null)
      _initRequiereCTXProxy();
    return requiereCTX_PortType.requiereCTX(entrada);
  }
  
  
}