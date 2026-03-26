package com.osmosyscol.datasuite.wsclient.procedimientolv;

public class ProcedimientoLVProxy implements com.osmosyscol.datasuite.wsclient.procedimientolv.ProcedimientoLV {
  private String _endpoint = null;
  private com.osmosyscol.datasuite.wsclient.procedimientolv.ProcedimientoLV procedimientoLV = null;
  
  public ProcedimientoLVProxy() {
    _initProcedimientoLVProxy();
  }
  
  public ProcedimientoLVProxy(String endpoint) {
    _endpoint = endpoint;
    _initProcedimientoLVProxy();
  }
  
  private void _initProcedimientoLVProxy() {
    try {
      procedimientoLV = (new com.osmosyscol.datasuite.wsclient.procedimientolv.ProcedimientoLVServiceLocator()).getProcedimientoLV();
      if (procedimientoLV != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)procedimientoLV)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)procedimientoLV)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (procedimientoLV != null)
      ((javax.xml.rpc.Stub)procedimientoLV)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.osmosyscol.datasuite.wsclient.procedimientolv.ProcedimientoLV getProcedimientoLV() {
    if (procedimientoLV == null)
      _initProcedimientoLVProxy();
    return procedimientoLV;
  }
  
  public com.osmosyscol.datasuite.wsclient.procedimientolv.Valor[] ejecutarProcedimiento(java.lang.String consulta, java.lang.String id, java.lang.String nombre) throws java.rmi.RemoteException{
    if (procedimientoLV == null)
      _initProcedimientoLVProxy();
    return procedimientoLV.ejecutarProcedimiento(consulta, id, nombre);
  }
  
  
}