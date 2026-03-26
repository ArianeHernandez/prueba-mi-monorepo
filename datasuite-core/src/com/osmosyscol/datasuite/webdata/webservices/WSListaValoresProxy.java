package com.osmosyscol.datasuite.webdata.webservices;

public class WSListaValoresProxy implements com.osmosyscol.datasuite.webdata.webservices.WSListaValores {
  private String _endpoint = null;
  private com.osmosyscol.datasuite.webdata.webservices.WSListaValores wSListaValores = null;
  
  public WSListaValoresProxy() {
    _initWSListaValoresProxy();
  }
  
  public WSListaValoresProxy(String endpoint) {
    _endpoint = endpoint;
    _initWSListaValoresProxy();
  }
  
  private void _initWSListaValoresProxy() {
    try {
      wSListaValores = (new com.osmosyscol.datasuite.webdata.webservices.WSListaValoresServiceLocator()).getWSListaValores();
      if (wSListaValores != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wSListaValores)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wSListaValores)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wSListaValores != null)
      ((javax.xml.rpc.Stub)wSListaValores)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.osmosyscol.datasuite.webdata.webservices.WSListaValores getWSListaValores() {
    if (wSListaValores == null)
      _initWSListaValoresProxy();
    return wSListaValores;
  }
  
  public boolean actualizarListaValores(int idListaValores) throws java.rmi.RemoteException{
    if (wSListaValores == null)
      _initWSListaValoresProxy();
    return wSListaValores.actualizarListaValores(idListaValores);
  }
  
  
}