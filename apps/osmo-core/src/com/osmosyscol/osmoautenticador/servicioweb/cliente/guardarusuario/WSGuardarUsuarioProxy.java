package com.osmosyscol.osmoautenticador.servicioweb.cliente.guardarusuario;

public class WSGuardarUsuarioProxy implements com.osmosyscol.osmoautenticador.servicioweb.cliente.guardarusuario.WSGuardarUsuario {
  private String _endpoint = null;
  private com.osmosyscol.osmoautenticador.servicioweb.cliente.guardarusuario.WSGuardarUsuario wSGuardarUsuario = null;
  
  public WSGuardarUsuarioProxy() {
    _initWSGuardarUsuarioProxy();
  }
  
  public WSGuardarUsuarioProxy(String endpoint) {
    _endpoint = endpoint;
    _initWSGuardarUsuarioProxy();
  }
  
  private void _initWSGuardarUsuarioProxy() {
    try {
      wSGuardarUsuario = (new com.osmosyscol.osmoautenticador.servicioweb.cliente.guardarusuario.WSGuardarUsuarioServiceLocator()).getWSGuardarUsuario();
      if (wSGuardarUsuario != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wSGuardarUsuario)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wSGuardarUsuario)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wSGuardarUsuario != null)
      ((javax.xml.rpc.Stub)wSGuardarUsuario)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.osmosyscol.osmoautenticador.servicioweb.cliente.guardarusuario.WSGuardarUsuario getWSGuardarUsuario() {
    if (wSGuardarUsuario == null)
      _initWSGuardarUsuarioProxy();
    return wSGuardarUsuario;
  }
  
  public com.osmosyscol.osmoautenticador.servicioweb.cliente.guardarusuario.RespuestaGuardarUsuario guardarUsuario(com.osmosyscol.osmoautenticador.servicioweb.cliente.guardarusuario.SolicitudGuardarUsuario solicitudGuardarUsuario) throws java.rmi.RemoteException{
    if (wSGuardarUsuario == null)
      _initWSGuardarUsuarioProxy();
    return wSGuardarUsuario.guardarUsuario(solicitudGuardarUsuario);
  }
  
  
}