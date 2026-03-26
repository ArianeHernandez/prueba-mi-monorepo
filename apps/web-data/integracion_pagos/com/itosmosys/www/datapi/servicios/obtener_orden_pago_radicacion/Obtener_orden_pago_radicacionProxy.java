package com.itosmosys.www.datapi.servicios.obtener_orden_pago_radicacion;

public class Obtener_orden_pago_radicacionProxy implements com.itosmosys.www.datapi.servicios.obtener_orden_pago_radicacion.Obtener_orden_pago_radicacion_PortType {
  private String _endpoint = null;
  private com.itosmosys.www.datapi.servicios.obtener_orden_pago_radicacion.Obtener_orden_pago_radicacion_PortType obtener_orden_pago_radicacion_PortType = null;
  
  public Obtener_orden_pago_radicacionProxy() {
    _initObtener_orden_pago_radicacionProxy();
  }
  
  public Obtener_orden_pago_radicacionProxy(String endpoint) {
    _endpoint = endpoint;
    _initObtener_orden_pago_radicacionProxy();
  }
  
  private void _initObtener_orden_pago_radicacionProxy() {
    try {
      obtener_orden_pago_radicacion_PortType = (new com.itosmosys.www.datapi.servicios.obtener_orden_pago_radicacion.Obtener_orden_pago_radicacion_ServiceLocator()).getobtener_orden_pago_radicacionSOAP();
      if (obtener_orden_pago_radicacion_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)obtener_orden_pago_radicacion_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)obtener_orden_pago_radicacion_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (obtener_orden_pago_radicacion_PortType != null)
      ((javax.xml.rpc.Stub)obtener_orden_pago_radicacion_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.itosmosys.www.datapi.servicios.obtener_orden_pago_radicacion.Obtener_orden_pago_radicacion_PortType getObtener_orden_pago_radicacion_PortType() {
    if (obtener_orden_pago_radicacion_PortType == null)
      _initObtener_orden_pago_radicacionProxy();
    return obtener_orden_pago_radicacion_PortType;
  }
  
  public com.itosmosys.www.datapi.servicios.obtener_orden_pago_radicacion.TipoElementoSalidaobtener_orden_pago_radicacion[] obtener_orden_pago_radicacion(com.itosmosys.www.datapi.servicios.obtener_orden_pago_radicacion.TipoEntradaobtener_orden_pago_radicacion entrada) throws java.rmi.RemoteException{
    if (obtener_orden_pago_radicacion_PortType == null)
      _initObtener_orden_pago_radicacionProxy();
    return obtener_orden_pago_radicacion_PortType.obtener_orden_pago_radicacion(entrada);
  }
  
  
}