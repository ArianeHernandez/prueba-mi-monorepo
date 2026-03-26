package com.osmosyscol.osmoautenticador.servicioweb.cambiarclave;

public class WSCambiarClaveProxy implements com.osmosyscol.osmoautenticador.servicioweb.cambiarclave.WSCambiarClave {
  private String _endpoint = null;
  private com.osmosyscol.osmoautenticador.servicioweb.cambiarclave.WSCambiarClave wSCambiarClave = null;
  
  public WSCambiarClaveProxy() {
    _initWSCambiarClaveProxy();
  }
  
  public WSCambiarClaveProxy(String endpoint) {
    _endpoint = endpoint;
    _initWSCambiarClaveProxy();
  }
  
  private void _initWSCambiarClaveProxy() {
    try {
      wSCambiarClave = (new com.osmosyscol.osmoautenticador.servicioweb.cambiarclave.WSCambiarClaveServiceLocator()).getWSCambiarClave();
      if (wSCambiarClave != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wSCambiarClave)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wSCambiarClave)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wSCambiarClave != null)
      ((javax.xml.rpc.Stub)wSCambiarClave)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.osmosyscol.osmoautenticador.servicioweb.cambiarclave.WSCambiarClave getWSCambiarClave() {
    if (wSCambiarClave == null)
      _initWSCambiarClaveProxy();
    return wSCambiarClave;
  }
  
  public com.osmosyscol.osmoautenticador.servicioweb.cambiarclave.RespuestaCambiarClave cambiarclave(com.osmosyscol.osmoautenticador.servicioweb.cambiarclave.SolicitudCambiarClave solicitudCambiarClave) throws java.rmi.RemoteException{
    if (wSCambiarClave == null)
      _initWSCambiarClaveProxy();
    return wSCambiarClave.cambiarclave(solicitudCambiarClave);
  }
  
  
}