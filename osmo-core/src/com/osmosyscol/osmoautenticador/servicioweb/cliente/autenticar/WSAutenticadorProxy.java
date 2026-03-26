package com.osmosyscol.osmoautenticador.servicioweb.cliente.autenticar;

public class WSAutenticadorProxy implements com.osmosyscol.osmoautenticador.servicioweb.cliente.autenticar.WSAutenticador {
  private String _endpoint = null;
  private com.osmosyscol.osmoautenticador.servicioweb.cliente.autenticar.WSAutenticador wSAutenticador = null;
  
  public WSAutenticadorProxy() {
    _initWSAutenticadorProxy();
  }
  
  public WSAutenticadorProxy(String endpoint) {
    _endpoint = endpoint;
    _initWSAutenticadorProxy();
  }
  
  private void _initWSAutenticadorProxy() {
    try {
      wSAutenticador = (new com.osmosyscol.osmoautenticador.servicioweb.cliente.autenticar.WSAutenticadorServiceLocator()).getWSAutenticador();
      if (wSAutenticador != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wSAutenticador)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wSAutenticador)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wSAutenticador != null)
      ((javax.xml.rpc.Stub)wSAutenticador)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.osmosyscol.osmoautenticador.servicioweb.cliente.autenticar.WSAutenticador getWSAutenticador() {
    if (wSAutenticador == null)
      _initWSAutenticadorProxy();
    return wSAutenticador;
  }
  
  public com.osmosyscol.osmoautenticador.servicioweb.cliente.autenticar.RespuestaAutenticar autenticar(com.osmosyscol.osmoautenticador.servicioweb.cliente.autenticar.SolicitudAutenticar solicitudAutenticar) throws java.rmi.RemoteException{
    if (wSAutenticador == null)
      _initWSAutenticadorProxy();
    return wSAutenticador.autenticar(solicitudAutenticar);
  }
  
  public com.osmosyscol.osmoautenticador.servicioweb.cliente.autenticar.RespuestaAutorizar autorizar(com.osmosyscol.osmoautenticador.servicioweb.cliente.autenticar.SolicitudAutorizar solicitudAutorizar) throws java.rmi.RemoteException{
    if (wSAutenticador == null)
      _initWSAutenticadorProxy();
    return wSAutenticador.autorizar(solicitudAutorizar);
  }
  
  
}