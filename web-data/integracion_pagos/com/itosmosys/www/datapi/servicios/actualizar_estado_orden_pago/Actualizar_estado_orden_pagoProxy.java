package com.itosmosys.www.datapi.servicios.actualizar_estado_orden_pago;

public class Actualizar_estado_orden_pagoProxy implements com.itosmosys.www.datapi.servicios.actualizar_estado_orden_pago.Actualizar_estado_orden_pago_PortType {
  private String _endpoint = null;
  private com.itosmosys.www.datapi.servicios.actualizar_estado_orden_pago.Actualizar_estado_orden_pago_PortType actualizar_estado_orden_pago_PortType = null;
  
  public Actualizar_estado_orden_pagoProxy() {
    _initActualizar_estado_orden_pagoProxy();
  }
  
  public Actualizar_estado_orden_pagoProxy(String endpoint) {
    _endpoint = endpoint;
    _initActualizar_estado_orden_pagoProxy();
  }
  
  private void _initActualizar_estado_orden_pagoProxy() {
    try {
      actualizar_estado_orden_pago_PortType = (new com.itosmosys.www.datapi.servicios.actualizar_estado_orden_pago.Actualizar_estado_orden_pago_ServiceLocator()).getactualizar_estado_orden_pagoSOAP();
      if (actualizar_estado_orden_pago_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)actualizar_estado_orden_pago_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)actualizar_estado_orden_pago_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (actualizar_estado_orden_pago_PortType != null)
      ((javax.xml.rpc.Stub)actualizar_estado_orden_pago_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.itosmosys.www.datapi.servicios.actualizar_estado_orden_pago.Actualizar_estado_orden_pago_PortType getActualizar_estado_orden_pago_PortType() {
    if (actualizar_estado_orden_pago_PortType == null)
      _initActualizar_estado_orden_pagoProxy();
    return actualizar_estado_orden_pago_PortType;
  }
  
  public com.itosmosys.www.datapi.servicios.actualizar_estado_orden_pago.TipoElementoSalidaactualizar_estado_orden_pago[] actualizar_estado_orden_pago(com.itosmosys.www.datapi.servicios.actualizar_estado_orden_pago.TipoEntradaactualizar_estado_orden_pago entrada) throws java.rmi.RemoteException{
    if (actualizar_estado_orden_pago_PortType == null)
      _initActualizar_estado_orden_pagoProxy();
    return actualizar_estado_orden_pago_PortType.actualizar_estado_orden_pago(entrada);
  }
  
  
}