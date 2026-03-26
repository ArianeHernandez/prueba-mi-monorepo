package com.itosmosys.www.datapi.servicios.aplicar_debito;

public class Aplicar_debitoProxy implements com.itosmosys.www.datapi.servicios.aplicar_debito.Aplicar_debito_PortType {
  private String _endpoint = null;
  private com.itosmosys.www.datapi.servicios.aplicar_debito.Aplicar_debito_PortType aplicar_debito_PortType = null;
  
  public Aplicar_debitoProxy() {
    _initAplicar_debitoProxy();
  }
  
  public Aplicar_debitoProxy(String endpoint) {
    _endpoint = endpoint;
    _initAplicar_debitoProxy();
  }
  
  private void _initAplicar_debitoProxy() {
    try {
      aplicar_debito_PortType = (new com.itosmosys.www.datapi.servicios.aplicar_debito.Aplicar_debito_ServiceLocator()).getaplicar_debitoSOAP();
      if (aplicar_debito_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)aplicar_debito_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)aplicar_debito_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (aplicar_debito_PortType != null)
      ((javax.xml.rpc.Stub)aplicar_debito_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.itosmosys.www.datapi.servicios.aplicar_debito.Aplicar_debito_PortType getAplicar_debito_PortType() {
    if (aplicar_debito_PortType == null)
      _initAplicar_debitoProxy();
    return aplicar_debito_PortType;
  }
  
  public com.itosmosys.www.datapi.servicios.aplicar_debito.TipoElementoSalidaaplicar_debito[] aplicar_debito(com.itosmosys.www.datapi.servicios.aplicar_debito.TipoEntradaaplicar_debito entrada) throws java.rmi.RemoteException{
    if (aplicar_debito_PortType == null)
      _initAplicar_debitoProxy();
    return aplicar_debito_PortType.aplicar_debito(entrada);
  }
  
  
}