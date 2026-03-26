package com.osmosyscol.datasuite.webdata.correval.webservice.movimiento_recaudos_pagos;

public class Movimiento_recaudos_pagosProxy implements com.osmosyscol.datasuite.webdata.correval.webservice.movimiento_recaudos_pagos.Movimiento_recaudos_pagos_PortType {
  private String _endpoint = null;
  private com.osmosyscol.datasuite.webdata.correval.webservice.movimiento_recaudos_pagos.Movimiento_recaudos_pagos_PortType movimiento_recaudos_pagos_PortType = null;
  
  public Movimiento_recaudos_pagosProxy() {
    _initMovimiento_recaudos_pagosProxy();
  }
  
  public Movimiento_recaudos_pagosProxy(String endpoint) {
    _endpoint = endpoint;
    _initMovimiento_recaudos_pagosProxy();
  }
  
  private void _initMovimiento_recaudos_pagosProxy() {
    try {
      movimiento_recaudos_pagos_PortType = (new com.osmosyscol.datasuite.webdata.correval.webservice.movimiento_recaudos_pagos.Movimiento_recaudos_pagos_ServiceLocator()).getmovimiento_recaudos_pagosSOAP();
      if (movimiento_recaudos_pagos_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)movimiento_recaudos_pagos_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)movimiento_recaudos_pagos_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (movimiento_recaudos_pagos_PortType != null)
      ((javax.xml.rpc.Stub)movimiento_recaudos_pagos_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.osmosyscol.datasuite.webdata.correval.webservice.movimiento_recaudos_pagos.Movimiento_recaudos_pagos_PortType getMovimiento_recaudos_pagos_PortType() {
    if (movimiento_recaudos_pagos_PortType == null)
      _initMovimiento_recaudos_pagosProxy();
    return movimiento_recaudos_pagos_PortType;
  }
  
  public com.osmosyscol.datasuite.webdata.correval.webservice.movimiento_recaudos_pagos.TipoElementoSalidamovimiento_recaudos_pagos[] movimiento_recaudos_pagos(com.osmosyscol.datasuite.webdata.correval.webservice.movimiento_recaudos_pagos.TipoEntradamovimiento_recaudos_pagos entrada) throws java.rmi.RemoteException{
    if (movimiento_recaudos_pagos_PortType == null)
      _initMovimiento_recaudos_pagosProxy();
    return movimiento_recaudos_pagos_PortType.movimiento_recaudos_pagos(entrada);
  }
  
  
}