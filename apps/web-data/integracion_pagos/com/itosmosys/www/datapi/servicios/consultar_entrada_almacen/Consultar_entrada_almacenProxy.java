package com.itosmosys.www.datapi.servicios.consultar_entrada_almacen;

public class Consultar_entrada_almacenProxy implements com.itosmosys.www.datapi.servicios.consultar_entrada_almacen.Consultar_entrada_almacen_PortType {
  private String _endpoint = null;
  private com.itosmosys.www.datapi.servicios.consultar_entrada_almacen.Consultar_entrada_almacen_PortType consultar_entrada_almacen_PortType = null;
  
  public Consultar_entrada_almacenProxy() {
    _initConsultar_entrada_almacenProxy();
  }
  
  public Consultar_entrada_almacenProxy(String endpoint) {
    _endpoint = endpoint;
    _initConsultar_entrada_almacenProxy();
  }
  
  private void _initConsultar_entrada_almacenProxy() {
    try {
      consultar_entrada_almacen_PortType = (new com.itosmosys.www.datapi.servicios.consultar_entrada_almacen.Consultar_entrada_almacen_ServiceLocator()).getconsultar_entrada_almacenSOAP();
      if (consultar_entrada_almacen_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)consultar_entrada_almacen_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)consultar_entrada_almacen_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (consultar_entrada_almacen_PortType != null)
      ((javax.xml.rpc.Stub)consultar_entrada_almacen_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.itosmosys.www.datapi.servicios.consultar_entrada_almacen.Consultar_entrada_almacen_PortType getConsultar_entrada_almacen_PortType() {
    if (consultar_entrada_almacen_PortType == null)
      _initConsultar_entrada_almacenProxy();
    return consultar_entrada_almacen_PortType;
  }
  
  public com.itosmosys.www.datapi.servicios.consultar_entrada_almacen.TipoElementoSalidaconsultar_entrada_almacen[] consultar_entrada_almacen(com.itosmosys.www.datapi.servicios.consultar_entrada_almacen.TipoEntradaconsultar_entrada_almacen entrada) throws java.rmi.RemoteException{
    if (consultar_entrada_almacen_PortType == null)
      _initConsultar_entrada_almacenProxy();
    return consultar_entrada_almacen_PortType.consultar_entrada_almacen(entrada);
  }
  
  
}