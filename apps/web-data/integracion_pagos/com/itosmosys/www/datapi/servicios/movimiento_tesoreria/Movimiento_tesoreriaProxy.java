package com.itosmosys.www.datapi.servicios.movimiento_tesoreria;

public class Movimiento_tesoreriaProxy implements com.itosmosys.www.datapi.servicios.movimiento_tesoreria.Movimiento_tesoreria_PortType {
  private String _endpoint = null;
  private com.itosmosys.www.datapi.servicios.movimiento_tesoreria.Movimiento_tesoreria_PortType movimiento_tesoreria_PortType = null;
  
  public Movimiento_tesoreriaProxy() {
    _initMovimiento_tesoreriaProxy();
  }
  
  public Movimiento_tesoreriaProxy(String endpoint) {
    _endpoint = endpoint;
    _initMovimiento_tesoreriaProxy();
  }
  
  private void _initMovimiento_tesoreriaProxy() {
    try {
      movimiento_tesoreria_PortType = (new com.itosmosys.www.datapi.servicios.movimiento_tesoreria.Movimiento_tesoreria_ServiceLocator()).getmovimiento_tesoreriaSOAP();
      if (movimiento_tesoreria_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)movimiento_tesoreria_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)movimiento_tesoreria_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (movimiento_tesoreria_PortType != null)
      ((javax.xml.rpc.Stub)movimiento_tesoreria_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.itosmosys.www.datapi.servicios.movimiento_tesoreria.Movimiento_tesoreria_PortType getMovimiento_tesoreria_PortType() {
    if (movimiento_tesoreria_PortType == null)
      _initMovimiento_tesoreriaProxy();
    return movimiento_tesoreria_PortType;
  }
  
  public com.itosmosys.www.datapi.servicios.movimiento_tesoreria.TipoElementoSalidamovimiento_tesoreria[] movimiento_tesoreria(com.itosmosys.www.datapi.servicios.movimiento_tesoreria.TipoEntradamovimiento_tesoreria entrada) throws java.rmi.RemoteException{
    if (movimiento_tesoreria_PortType == null)
      _initMovimiento_tesoreriaProxy();
    return movimiento_tesoreria_PortType.movimiento_tesoreria(entrada);
  }
  
  
}