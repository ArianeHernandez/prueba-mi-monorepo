package com.osmosyscol.g3a.serviciosweb.wsparametros;

public class WSParametrosProxy implements com.osmosyscol.g3a.serviciosweb.wsparametros.WSParametros {
  private String _endpoint = null;
  private com.osmosyscol.g3a.serviciosweb.wsparametros.WSParametros wSParametros = null;
  
  public WSParametrosProxy() {
    _initWSParametrosProxy();
  }
  
  public WSParametrosProxy(String endpoint) {
    _endpoint = endpoint;
    _initWSParametrosProxy();
  }
  
  private void _initWSParametrosProxy() {
    try {
      wSParametros = (new com.osmosyscol.g3a.serviciosweb.wsparametros.WSParametrosServiceLocator()).getWSParametros();
      if (wSParametros != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wSParametros)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wSParametros)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wSParametros != null)
      ((javax.xml.rpc.Stub)wSParametros)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.osmosyscol.g3a.serviciosweb.wsparametros.WSParametros getWSParametros() {
    if (wSParametros == null)
      _initWSParametrosProxy();
    return wSParametros;
  }
  
  public int obtenerTiempoMaximoSesion() throws java.rmi.RemoteException{
    if (wSParametros == null)
      _initWSParametrosProxy();
    return wSParametros.obtenerTiempoMaximoSesion();
  }
  
  
}