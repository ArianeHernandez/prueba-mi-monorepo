package com.osmosyscol.datasuite.correval.listascautelares;

public class ApplicationServicesPortTypeProxy implements com.osmosyscol.datasuite.correval.listascautelares.ApplicationServicesPortType {
  private String _endpoint = null;
  private com.osmosyscol.datasuite.correval.listascautelares.ApplicationServicesPortType applicationServicesPortType = null;
  
  public ApplicationServicesPortTypeProxy() {
    _initApplicationServicesPortTypeProxy();
  }
  
  public ApplicationServicesPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initApplicationServicesPortTypeProxy();
  }
  
  private void _initApplicationServicesPortTypeProxy() {
    try {
      applicationServicesPortType = (new com.osmosyscol.datasuite.correval.listascautelares.ApplicationServicesLocator()).getApplicationServicesPort();
      if (applicationServicesPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)applicationServicesPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)applicationServicesPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (applicationServicesPortType != null)
      ((javax.xml.rpc.Stub)applicationServicesPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.osmosyscol.datasuite.correval.listascautelares.ApplicationServicesPortType getApplicationServicesPortType() {
    if (applicationServicesPortType == null)
      _initApplicationServicesPortTypeProxy();
    return applicationServicesPortType;
  }
  
  public com.osmosyscol.datasuite.correval.listascautelares.Resultado f_VERIFICATERCERO(java.lang.String param1, java.lang.String param2, java.lang.String param3) throws java.rmi.RemoteException{
    if (applicationServicesPortType == null)
      _initApplicationServicesPortTypeProxy();
    return applicationServicesPortType.f_VERIFICATERCERO(param1, param2, param3);
  }
  
  
}