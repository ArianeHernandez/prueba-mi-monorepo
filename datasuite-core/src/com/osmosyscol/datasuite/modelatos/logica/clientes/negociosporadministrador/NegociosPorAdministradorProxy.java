package com.osmosyscol.datasuite.modelatos.logica.clientes.negociosporadministrador;

public class NegociosPorAdministradorProxy implements com.osmosyscol.datasuite.modelatos.logica.clientes.negociosporadministrador.NegociosPorAdministrador_PortType {
  private String _endpoint = null;
  private com.osmosyscol.datasuite.modelatos.logica.clientes.negociosporadministrador.NegociosPorAdministrador_PortType negociosPorAdministrador_PortType = null;
  
  public NegociosPorAdministradorProxy() {
    _initNegociosPorAdministradorProxy();
  }
  
  public NegociosPorAdministradorProxy(String endpoint) {
    _endpoint = endpoint;
    _initNegociosPorAdministradorProxy();
  }
  
  private void _initNegociosPorAdministradorProxy() {
    try {
      negociosPorAdministrador_PortType = (new com.osmosyscol.datasuite.modelatos.logica.clientes.negociosporadministrador.NegociosPorAdministrador_ServiceLocator()).getNegociosPorAdministradorSOAP();
      if (negociosPorAdministrador_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)negociosPorAdministrador_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)negociosPorAdministrador_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (negociosPorAdministrador_PortType != null)
      ((javax.xml.rpc.Stub)negociosPorAdministrador_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.osmosyscol.datasuite.modelatos.logica.clientes.negociosporadministrador.NegociosPorAdministrador_PortType getNegociosPorAdministrador_PortType() {
    if (negociosPorAdministrador_PortType == null)
      _initNegociosPorAdministradorProxy();
    return negociosPorAdministrador_PortType;
  }
  
  public com.osmosyscol.datasuite.modelatos.logica.clientes.negociosporadministrador.TipoElementoSalidalista_usua_vs_fides[] lista_usua_vs_fides(com.osmosyscol.datasuite.modelatos.logica.clientes.negociosporadministrador.TipoEntradalista_usua_vs_fides entrada) throws java.rmi.RemoteException{
    if (negociosPorAdministrador_PortType == null)
      _initNegociosPorAdministradorProxy();
    return negociosPorAdministrador_PortType.lista_usua_vs_fides(entrada);
  }
  
  
}