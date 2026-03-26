package com.osmosyscol.g3a.cliente;

public class WSAutenticarPTXProxy implements com.osmosyscol.g3a.cliente.WSAutenticarPTX {
  private String _endpoint = null;
  private com.osmosyscol.g3a.cliente.WSAutenticarPTX wSAutenticarPTX = null;
  
  public WSAutenticarPTXProxy() {
    _initWSAutenticarPTXProxy();
  }
  
  public WSAutenticarPTXProxy(String endpoint) {
    _endpoint = endpoint;
    _initWSAutenticarPTXProxy();
  }
  
  private void _initWSAutenticarPTXProxy() {
    try {
      wSAutenticarPTX = (new com.osmosyscol.g3a.cliente.WSAutenticarPTXServiceLocator()).getWSAutenticarPTX();
      if (wSAutenticarPTX != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wSAutenticarPTX)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wSAutenticarPTX)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wSAutenticarPTX != null)
      ((javax.xml.rpc.Stub)wSAutenticarPTX)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.osmosyscol.g3a.cliente.WSAutenticarPTX getWSAutenticarPTX() {
    if (wSAutenticarPTX == null)
      _initWSAutenticarPTXProxy();
    return wSAutenticarPTX;
  }
  
  public com.osmosyscol.g3a.cliente.RespuestaAutenticarPTX autenticar(com.osmosyscol.g3a.cliente.EntradaAutenticarPTX entrada) throws java.rmi.RemoteException{
    if (wSAutenticarPTX == null)
      _initWSAutenticarPTXProxy();
    return wSAutenticarPTX.autenticar(entrada);
  }
  
  public com.osmosyscol.g3a.cliente.RespuestaAutorizarPTX autorizar(com.osmosyscol.g3a.cliente.EntradaAutorizarPTX entrada) throws java.rmi.RemoteException{
    if (wSAutenticarPTX == null)
      _initWSAutenticarPTXProxy();
    return wSAutenticarPTX.autorizar(entrada);
  }
  
  public boolean cerrarSesion(com.osmosyscol.g3a.cliente.EntradaAutorizarPTX entrada) throws java.rmi.RemoteException{
    if (wSAutenticarPTX == null)
      _initWSAutenticarPTXProxy();
    return wSAutenticarPTX.cerrarSesion(entrada);
  }
  
  public boolean perderSesion(com.osmosyscol.g3a.cliente.EntradaAutorizarPTX entrada) throws java.rmi.RemoteException{
    if (wSAutenticarPTX == null)
      _initWSAutenticarPTXProxy();
    return wSAutenticarPTX.perderSesion(entrada);
  }
  
  public com.osmosyscol.g3a.cliente.entidades.RespuestaOperacion iniciarTransaccion(com.osmosyscol.g3a.cliente.entidades.SolicitudTransaccion entrada) throws java.rmi.RemoteException{
    if (wSAutenticarPTX == null)
      _initWSAutenticarPTXProxy();
    return wSAutenticarPTX.iniciarTransaccion(entrada);
  }
  
  public com.osmosyscol.g3a.cliente.entidades.RespuestaOperacion validarPermisosTransaccion(com.osmosyscol.g3a.cliente.entidades.SolicitudTransaccion solicitud) throws java.rmi.RemoteException{
    if (wSAutenticarPTX == null)
      _initWSAutenticarPTXProxy();
    return wSAutenticarPTX.validarPermisosTransaccion(solicitud);
  }
  
  public com.osmosyscol.g3a.cliente.entidades.RespuestaOperacion solicitarActivacion(com.osmosyscol.g3a.cliente.entidades.SolicitudActivacion solicitud) throws java.rmi.RemoteException{
    if (wSAutenticarPTX == null)
      _initWSAutenticarPTXProxy();
    return wSAutenticarPTX.solicitarActivacion(solicitud);
  }
  
  
}