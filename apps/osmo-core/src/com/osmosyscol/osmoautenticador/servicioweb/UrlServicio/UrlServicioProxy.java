package com.osmosyscol.osmoautenticador.servicioweb.UrlServicio;

public class UrlServicioProxy implements com.osmosyscol.osmoautenticador.servicioweb.UrlServicio.UrlServicio_PortType {
  private String _endpoint = null;
  private com.osmosyscol.osmoautenticador.servicioweb.UrlServicio.UrlServicio_PortType urlServicio_PortType = null;
  
  public UrlServicioProxy() {
    _initUrlServicioProxy();
  }
  
  public UrlServicioProxy(String endpoint) {
    _endpoint = endpoint;
    _initUrlServicioProxy();
  }
  
  private void _initUrlServicioProxy() {
    try {
      urlServicio_PortType = (new com.osmosyscol.osmoautenticador.servicioweb.UrlServicio.UrlServicio_ServiceLocator()).getUrlServicioSOAP();
      if (urlServicio_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)urlServicio_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)urlServicio_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (urlServicio_PortType != null)
      ((javax.xml.rpc.Stub)urlServicio_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.osmosyscol.osmoautenticador.servicioweb.UrlServicio.UrlServicio_PortType getUrlServicio_PortType() {
    if (urlServicio_PortType == null)
      _initUrlServicioProxy();
    return urlServicio_PortType;
  }
  
  public com.osmosyscol.osmoautenticador.servicioweb.UrlServicio.TipoElementoSalidaobtenerUrlServicios[] obtenerUrlServicios(com.osmosyscol.osmoautenticador.servicioweb.UrlServicio.TipoEntradaobtenerUrlServicios entrada) throws java.rmi.RemoteException{
    if (urlServicio_PortType == null)
      _initUrlServicioProxy();
    return urlServicio_PortType.obtenerUrlServicios(entrada);
  }
  
  
}