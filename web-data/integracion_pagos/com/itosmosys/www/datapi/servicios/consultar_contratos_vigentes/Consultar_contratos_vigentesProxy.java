package com.itosmosys.www.datapi.servicios.consultar_contratos_vigentes;

public class Consultar_contratos_vigentesProxy implements com.itosmosys.www.datapi.servicios.consultar_contratos_vigentes.Consultar_contratos_vigentes_PortType {
  private String _endpoint = null;
  private com.itosmosys.www.datapi.servicios.consultar_contratos_vigentes.Consultar_contratos_vigentes_PortType consultar_contratos_vigentes_PortType = null;
  
  public Consultar_contratos_vigentesProxy() {
    _initConsultar_contratos_vigentesProxy();
  }
  
  public Consultar_contratos_vigentesProxy(String endpoint) {
    _endpoint = endpoint;
    _initConsultar_contratos_vigentesProxy();
  }
  
  private void _initConsultar_contratos_vigentesProxy() {
    try {
      consultar_contratos_vigentes_PortType = (new com.itosmosys.www.datapi.servicios.consultar_contratos_vigentes.Consultar_contratos_vigentes_ServiceLocator()).getconsultar_contratos_vigentesSOAP();
      if (consultar_contratos_vigentes_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)consultar_contratos_vigentes_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)consultar_contratos_vigentes_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (consultar_contratos_vigentes_PortType != null)
      ((javax.xml.rpc.Stub)consultar_contratos_vigentes_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.itosmosys.www.datapi.servicios.consultar_contratos_vigentes.Consultar_contratos_vigentes_PortType getConsultar_contratos_vigentes_PortType() {
    if (consultar_contratos_vigentes_PortType == null)
      _initConsultar_contratos_vigentesProxy();
    return consultar_contratos_vigentes_PortType;
  }
  
  public com.itosmosys.www.datapi.servicios.consultar_contratos_vigentes.TipoElementoSalidaconsultar_contratos_vigentes[] consultar_contratos_vigentes(com.itosmosys.www.datapi.servicios.consultar_contratos_vigentes.TipoEntradaconsultar_contratos_vigentes entrada) throws java.rmi.RemoteException{
    if (consultar_contratos_vigentes_PortType == null)
      _initConsultar_contratos_vigentesProxy();
    return consultar_contratos_vigentes_PortType.consultar_contratos_vigentes(entrada);
  }
  
  
}