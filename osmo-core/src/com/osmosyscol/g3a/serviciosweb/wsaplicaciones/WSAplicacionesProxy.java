package com.osmosyscol.g3a.serviciosweb.wsaplicaciones;

public class WSAplicacionesProxy implements com.osmosyscol.g3a.serviciosweb.wsaplicaciones.WSAplicaciones {
  private String _endpoint = null;
  private com.osmosyscol.g3a.serviciosweb.wsaplicaciones.WSAplicaciones wSAplicaciones = null;
  
  public WSAplicacionesProxy() {
    _initWSAplicacionesProxy();
  }
  
  public WSAplicacionesProxy(String endpoint) {
    _endpoint = endpoint;
    _initWSAplicacionesProxy();
  }
  
  private void _initWSAplicacionesProxy() {
    try {
      wSAplicaciones = (new com.osmosyscol.g3a.serviciosweb.wsaplicaciones.WSAplicacionesServiceLocator()).getWSAplicaciones();
      if (wSAplicaciones != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wSAplicaciones)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wSAplicaciones)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wSAplicaciones != null)
      ((javax.xml.rpc.Stub)wSAplicaciones)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.osmosyscol.g3a.serviciosweb.wsaplicaciones.WSAplicaciones getWSAplicaciones() {
    if (wSAplicaciones == null)
      _initWSAplicacionesProxy();
    return wSAplicaciones;
  }
  
  public com.osmosyscol.g3a.serviciosweb.wsaplicaciones.Aplicacion[] obtenerAplicaciones(java.lang.String login) throws java.rmi.RemoteException{
    if (wSAplicaciones == null)
      _initWSAplicacionesProxy();
    return wSAplicaciones.obtenerAplicaciones(login);
  }
  
  
}