package com.osmosyscol.datasuite.modelatos.logica.clientes.listanegocios;

public class ListaNegociosProxy implements com.osmosyscol.datasuite.modelatos.logica.clientes.listanegocios.ListaNegocios_PortType {
  private String _endpoint = null;
  private com.osmosyscol.datasuite.modelatos.logica.clientes.listanegocios.ListaNegocios_PortType listaNegocios_PortType = null;
  
  public ListaNegociosProxy() {
    _initListaNegociosProxy();
  }
  
  public ListaNegociosProxy(String endpoint) {
    _endpoint = endpoint;
    _initListaNegociosProxy();
  }
  
  private void _initListaNegociosProxy() {
    try {
      listaNegocios_PortType = (new com.osmosyscol.datasuite.modelatos.logica.clientes.listanegocios.ListaNegocios_ServiceLocator()).getListaNegociosSOAP();
      if (listaNegocios_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)listaNegocios_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)listaNegocios_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (listaNegocios_PortType != null)
      ((javax.xml.rpc.Stub)listaNegocios_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.osmosyscol.datasuite.modelatos.logica.clientes.listanegocios.ListaNegocios_PortType getListaNegocios_PortType() {
    if (listaNegocios_PortType == null)
      _initListaNegociosProxy();
    return listaNegocios_PortType;
  }
  
  public com.osmosyscol.datasuite.modelatos.logica.clientes.listanegocios.TipoElementoSalidalista_negocios[] lista_negocios(com.osmosyscol.datasuite.modelatos.logica.clientes.listanegocios.TipoEntradalista_negocios entrada) throws java.rmi.RemoteException{
    if (listaNegocios_PortType == null)
      _initListaNegociosProxy();
    return listaNegocios_PortType.lista_negocios(entrada);
  }
  
  
}