package com.osmosyscol.datasuite.webdata.correval.webservice.bancos;

public class BANCOSProxy implements com.osmosyscol.datasuite.webdata.correval.webservice.bancos.BANCOS_PortType {
  private String _endpoint = null;
  private com.osmosyscol.datasuite.webdata.correval.webservice.bancos.BANCOS_PortType bANCOS_PortType = null;
  
  public BANCOSProxy() {
    _initBANCOSProxy();
  }
  
  public BANCOSProxy(String endpoint) {
    _endpoint = endpoint;
    _initBANCOSProxy();
  }
  
  private void _initBANCOSProxy() {
    try {
      bANCOS_PortType = (new com.osmosyscol.datasuite.webdata.correval.webservice.bancos.BANCOS_ServiceLocator()).getBANCOSSOAP();
      if (bANCOS_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)bANCOS_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)bANCOS_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (bANCOS_PortType != null)
      ((javax.xml.rpc.Stub)bANCOS_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.osmosyscol.datasuite.webdata.correval.webservice.bancos.BANCOS_PortType getBANCOS_PortType() {
    if (bANCOS_PortType == null)
      _initBANCOSProxy();
    return bANCOS_PortType;
  }
  
  public com.osmosyscol.datasuite.webdata.correval.webservice.bancos.TipoElementoSalidabancos[] bancos(com.osmosyscol.datasuite.webdata.correval.webservice.bancos.TipoEntradabancos entrada) throws java.rmi.RemoteException{
    if (bANCOS_PortType == null)
      _initBANCOSProxy();
    return bANCOS_PortType.bancos(entrada);
  }
  
  
}