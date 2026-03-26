package com.osmosyscol.datasuite.webdata.correval.webservice.sp_act_movimientos;

public class Sp_act_movimientosProxy implements com.osmosyscol.datasuite.webdata.correval.webservice.sp_act_movimientos.Sp_act_movimientos_PortType {
  private String _endpoint = null;
  private com.osmosyscol.datasuite.webdata.correval.webservice.sp_act_movimientos.Sp_act_movimientos_PortType sp_act_movimientos_PortType = null;
  
  public Sp_act_movimientosProxy() {
    _initSp_act_movimientosProxy();
  }
  
  public Sp_act_movimientosProxy(String endpoint) {
    _endpoint = endpoint;
    _initSp_act_movimientosProxy();
  }
  
  private void _initSp_act_movimientosProxy() {
    try {
      sp_act_movimientos_PortType = (new com.osmosyscol.datasuite.webdata.correval.webservice.sp_act_movimientos.Sp_act_movimientos_ServiceLocator()).getsp_act_movimientosSOAP();
      if (sp_act_movimientos_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sp_act_movimientos_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sp_act_movimientos_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sp_act_movimientos_PortType != null)
      ((javax.xml.rpc.Stub)sp_act_movimientos_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.osmosyscol.datasuite.webdata.correval.webservice.sp_act_movimientos.Sp_act_movimientos_PortType getSp_act_movimientos_PortType() {
    if (sp_act_movimientos_PortType == null)
      _initSp_act_movimientosProxy();
    return sp_act_movimientos_PortType;
  }
  
  public com.osmosyscol.datasuite.webdata.correval.webservice.sp_act_movimientos.TipoElementoSalidasp_act_movimientos[] sp_act_movimientos(com.osmosyscol.datasuite.webdata.correval.webservice.sp_act_movimientos.TipoEntradasp_act_movimientos entrada) throws java.rmi.RemoteException{
    if (sp_act_movimientos_PortType == null)
      _initSp_act_movimientosProxy();
    return sp_act_movimientos_PortType.sp_act_movimientos(entrada);
  }
  
  
}