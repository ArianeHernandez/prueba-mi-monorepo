package com.itosmosys.www.datapi.servicios.consulta_sarlaft;

public class Consulta_sarlaftProxy implements com.itosmosys.www.datapi.servicios.consulta_sarlaft.Consulta_sarlaft_PortType {
  private String _endpoint = null;
  private com.itosmosys.www.datapi.servicios.consulta_sarlaft.Consulta_sarlaft_PortType consulta_sarlaft_PortType = null;
  
  public Consulta_sarlaftProxy() {
    _initConsulta_sarlaftProxy();
  }
  
  public Consulta_sarlaftProxy(String endpoint) {
    _endpoint = endpoint;
    _initConsulta_sarlaftProxy();
  }
  
  private void _initConsulta_sarlaftProxy() {
    try {
      consulta_sarlaft_PortType = (new com.itosmosys.www.datapi.servicios.consulta_sarlaft.Consulta_sarlaft_ServiceLocator()).getconsulta_sarlaftSOAP();
      if (consulta_sarlaft_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)consulta_sarlaft_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)consulta_sarlaft_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (consulta_sarlaft_PortType != null)
      ((javax.xml.rpc.Stub)consulta_sarlaft_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.itosmosys.www.datapi.servicios.consulta_sarlaft.Consulta_sarlaft_PortType getConsulta_sarlaft_PortType() {
    if (consulta_sarlaft_PortType == null)
      _initConsulta_sarlaftProxy();
    return consulta_sarlaft_PortType;
  }
  
  public com.itosmosys.www.datapi.servicios.consulta_sarlaft.TipoElementoSalidaconsulta_sarlaft[] consulta_sarlaft(com.itosmosys.www.datapi.servicios.consulta_sarlaft.TipoEntradaconsulta_sarlaft entrada) throws java.rmi.RemoteException{
    if (consulta_sarlaft_PortType == null)
      _initConsulta_sarlaftProxy();
    return consulta_sarlaft_PortType.consulta_sarlaft(entrada);
  }
  
  
}