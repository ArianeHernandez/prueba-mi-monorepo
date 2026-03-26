package com.itosmosys.www.datapi.servicios.aplicar_devolucion_sp;

public class Aplicar_devolucion_spProxy implements com.itosmosys.www.datapi.servicios.aplicar_devolucion_sp.Aplicar_devolucion_sp_PortType {
  private String _endpoint = null;
  private com.itosmosys.www.datapi.servicios.aplicar_devolucion_sp.Aplicar_devolucion_sp_PortType aplicar_devolucion_sp_PortType = null;
  
  public Aplicar_devolucion_spProxy() {
    _initAplicar_devolucion_spProxy();
  }
  
  public Aplicar_devolucion_spProxy(String endpoint) {
    _endpoint = endpoint;
    _initAplicar_devolucion_spProxy();
  }
  
  private void _initAplicar_devolucion_spProxy() {
    try {
      aplicar_devolucion_sp_PortType = (new com.itosmosys.www.datapi.servicios.aplicar_devolucion_sp.Aplicar_devolucion_sp_ServiceLocator()).getaplicar_devolucion_spSOAP();
      if (aplicar_devolucion_sp_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)aplicar_devolucion_sp_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)aplicar_devolucion_sp_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (aplicar_devolucion_sp_PortType != null)
      ((javax.xml.rpc.Stub)aplicar_devolucion_sp_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.itosmosys.www.datapi.servicios.aplicar_devolucion_sp.Aplicar_devolucion_sp_PortType getAplicar_devolucion_sp_PortType() {
    if (aplicar_devolucion_sp_PortType == null)
      _initAplicar_devolucion_spProxy();
    return aplicar_devolucion_sp_PortType;
  }
  
  public com.itosmosys.www.datapi.servicios.aplicar_devolucion_sp.TipoElementoSalidaaplicar_devolucion_sp[] aplicar_devolucion_sp(com.itosmosys.www.datapi.servicios.aplicar_devolucion_sp.TipoEntradaaplicar_devolucion_sp entrada) throws java.rmi.RemoteException{
    if (aplicar_devolucion_sp_PortType == null)
      _initAplicar_devolucion_spProxy();
    return aplicar_devolucion_sp_PortType.aplicar_devolucion_sp(entrada);
  }
  
  
}