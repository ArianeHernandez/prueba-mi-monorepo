package com.osmosyscol.datasuite.webdata.correval.webservice.retiros;

public class ActualizarRetiroPorRespuestaBancoProxy implements com.osmosyscol.datasuite.webdata.correval.webservice.retiros.ActualizarRetiroPorRespuestaBanco_PortType {
  private String _endpoint = null;
  private com.osmosyscol.datasuite.webdata.correval.webservice.retiros.ActualizarRetiroPorRespuestaBanco_PortType actualizarRetiroPorRespuestaBanco_PortType = null;
  
  public ActualizarRetiroPorRespuestaBancoProxy() {
    _initActualizarRetiroPorRespuestaBancoProxy();
  }
  
  public ActualizarRetiroPorRespuestaBancoProxy(String endpoint) {
    _endpoint = endpoint;
    _initActualizarRetiroPorRespuestaBancoProxy();
  }
  
  private void _initActualizarRetiroPorRespuestaBancoProxy() {
    try {
      actualizarRetiroPorRespuestaBanco_PortType = (new com.osmosyscol.datasuite.webdata.correval.webservice.retiros.ActualizarRetiroPorRespuestaBanco_ServiceLocator()).getActualizarRetiroPorRespuestaBancoSOAP();
      if (actualizarRetiroPorRespuestaBanco_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)actualizarRetiroPorRespuestaBanco_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)actualizarRetiroPorRespuestaBanco_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (actualizarRetiroPorRespuestaBanco_PortType != null)
      ((javax.xml.rpc.Stub)actualizarRetiroPorRespuestaBanco_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.osmosyscol.datasuite.webdata.correval.webservice.retiros.ActualizarRetiroPorRespuestaBanco_PortType getActualizarRetiroPorRespuestaBanco_PortType() {
    if (actualizarRetiroPorRespuestaBanco_PortType == null)
      _initActualizarRetiroPorRespuestaBancoProxy();
    return actualizarRetiroPorRespuestaBanco_PortType;
  }
  
  public com.osmosyscol.datasuite.webdata.correval.webservice.retiros.TipoElementoSalidaactualizarRetiroPorRespuestaBanco[] actualizarRetiroPorRespuestaBanco(com.osmosyscol.datasuite.webdata.correval.webservice.retiros.TipoEntradaactualizarRetiroPorRespuestaBanco entrada) throws java.rmi.RemoteException{
    if (actualizarRetiroPorRespuestaBanco_PortType == null)
      _initActualizarRetiroPorRespuestaBancoProxy();
    return actualizarRetiroPorRespuestaBanco_PortType.actualizarRetiroPorRespuestaBanco(entrada);
  }
  
  
}