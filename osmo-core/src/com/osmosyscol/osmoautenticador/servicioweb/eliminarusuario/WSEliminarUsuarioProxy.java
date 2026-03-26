package com.osmosyscol.osmoautenticador.servicioweb.eliminarusuario;

public class WSEliminarUsuarioProxy implements com.osmosyscol.osmoautenticador.servicioweb.eliminarusuario.WSEliminarUsuario {
  private String _endpoint = null;
  private com.osmosyscol.osmoautenticador.servicioweb.eliminarusuario.WSEliminarUsuario wSEliminarUsuario = null;
  
  public WSEliminarUsuarioProxy() {
    _initWSEliminarUsuarioProxy();
  }
  
  public WSEliminarUsuarioProxy(String endpoint) {
    _endpoint = endpoint;
    _initWSEliminarUsuarioProxy();
  }
  
  private void _initWSEliminarUsuarioProxy() {
    try {
      wSEliminarUsuario = (new com.osmosyscol.osmoautenticador.servicioweb.eliminarusuario.WSEliminarUsuarioServiceLocator()).getWSEliminarUsuario();
      if (wSEliminarUsuario != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wSEliminarUsuario)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wSEliminarUsuario)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wSEliminarUsuario != null)
      ((javax.xml.rpc.Stub)wSEliminarUsuario)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.osmosyscol.osmoautenticador.servicioweb.eliminarusuario.WSEliminarUsuario getWSEliminarUsuario() {
    if (wSEliminarUsuario == null)
      _initWSEliminarUsuarioProxy();
    return wSEliminarUsuario;
  }
  
  public com.osmosyscol.osmoautenticador.servicioweb.eliminarusuario.RespuestaEliminarUsuario eliminarUsuario(java.lang.String login) throws java.rmi.RemoteException{
    if (wSEliminarUsuario == null)
      _initWSEliminarUsuarioProxy();
    return wSEliminarUsuario.eliminarUsuario(login);
  }
  
  
}