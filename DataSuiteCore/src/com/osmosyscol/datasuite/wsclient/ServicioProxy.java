package com.osmosyscol.datasuite.wsclient;

public class ServicioProxy implements com.osmosyscol.datasuite.wsclient.Servicio {
  private String _endpoint = null;
  private com.osmosyscol.datasuite.wsclient.Servicio servicio = null;
  
  public ServicioProxy() {
    _initServicioProxy();
  }
  
  public ServicioProxy(String endpoint) {
    _endpoint = endpoint;
    _initServicioProxy();
  }
  
  private void _initServicioProxy() {
    try {
      servicio = (new com.osmosyscol.datasuite.wsclient.ServicioServiceLocator()).getServicio();
      if (servicio != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)servicio)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)servicio)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (servicio != null)
      ((javax.xml.rpc.Stub)servicio)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.osmosyscol.datasuite.wsclient.Servicio getServicio() {
    if (servicio == null)
      _initServicioProxy();
    return servicio;
  }
  
  public boolean operacion() throws java.rmi.RemoteException{
    if (servicio == null)
      _initServicioProxy();
    return servicio.operacion();
  }
  
  
}