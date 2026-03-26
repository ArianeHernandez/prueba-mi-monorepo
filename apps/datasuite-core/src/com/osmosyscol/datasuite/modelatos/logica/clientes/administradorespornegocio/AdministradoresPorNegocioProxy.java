package com.osmosyscol.datasuite.modelatos.logica.clientes.administradorespornegocio;

public class AdministradoresPorNegocioProxy implements com.osmosyscol.datasuite.modelatos.logica.clientes.administradorespornegocio.AdministradoresPorNegocio_PortType {
  private String _endpoint = null;
  private com.osmosyscol.datasuite.modelatos.logica.clientes.administradorespornegocio.AdministradoresPorNegocio_PortType administradoresPorNegocio_PortType = null;
  
  public AdministradoresPorNegocioProxy() {
    _initAdministradoresPorNegocioProxy();
  }
  
  public AdministradoresPorNegocioProxy(String endpoint) {
    _endpoint = endpoint;
    _initAdministradoresPorNegocioProxy();
  }
  
  private void _initAdministradoresPorNegocioProxy() {
    try {
      administradoresPorNegocio_PortType = (new com.osmosyscol.datasuite.modelatos.logica.clientes.administradorespornegocio.AdministradoresPorNegocio_ServiceLocator()).getAdministradoresPorNegocioSOAP();
      if (administradoresPorNegocio_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)administradoresPorNegocio_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)administradoresPorNegocio_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (administradoresPorNegocio_PortType != null)
      ((javax.xml.rpc.Stub)administradoresPorNegocio_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.osmosyscol.datasuite.modelatos.logica.clientes.administradorespornegocio.AdministradoresPorNegocio_PortType getAdministradoresPorNegocio_PortType() {
    if (administradoresPorNegocio_PortType == null)
      _initAdministradoresPorNegocioProxy();
    return administradoresPorNegocio_PortType;
  }
  
  public com.osmosyscol.datasuite.modelatos.logica.clientes.administradorespornegocio.TipoElementoSalidalista_negocios_vs_usua[] lista_negocios_vs_usua(com.osmosyscol.datasuite.modelatos.logica.clientes.administradorespornegocio.TipoEntradalista_negocios_vs_usua entrada) throws java.rmi.RemoteException{
    if (administradoresPorNegocio_PortType == null)
      _initAdministradoresPorNegocioProxy();
    return administradoresPorNegocio_PortType.lista_negocios_vs_usua(entrada);
  }
  
  
}