package com.osmosyscol.osmoautenticador.servicioweb.actualizarRol;

import com.osmosyscol.commons.log.SimpleLogger;

public class WSActualizarRolProxy implements com.osmosyscol.osmoautenticador.servicioweb.actualizarRol.WSActualizarRol_PortType {
  private String _endpoint = null;
  private com.osmosyscol.osmoautenticador.servicioweb.actualizarRol.WSActualizarRol_PortType wSActualizarRol_PortType = null;
  
  public WSActualizarRolProxy() {
    _initWSActualizarRolProxy();
  }
  
  public WSActualizarRolProxy(String endpoint) {
    _endpoint = endpoint;
    _initWSActualizarRolProxy();
  }
  
  private void _initWSActualizarRolProxy() {
    try {
      wSActualizarRol_PortType = (new com.osmosyscol.osmoautenticador.servicioweb.actualizarRol.WSActualizarRol_ServiceLocator()).getWSActualizarRolSOAP();
      if (wSActualizarRol_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wSActualizarRol_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wSActualizarRol_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {
    	SimpleLogger.setError("Error al inicializar WSACTUALIZARROL", serviceException);
    }
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wSActualizarRol_PortType != null)
      ((javax.xml.rpc.Stub)wSActualizarRol_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.osmosyscol.osmoautenticador.servicioweb.actualizarRol.WSActualizarRol_PortType getWSActualizarRol_PortType() {
    if (wSActualizarRol_PortType == null)
      _initWSActualizarRolProxy();
    return wSActualizarRol_PortType;
  }
  
  public java.lang.String[] crearRol(com.osmosyscol.osmoautenticador.servicioweb.actualizarRol.TipoRolDto rolDto, com.osmosyscol.osmoautenticador.servicioweb.actualizarRol.TipoServicioDto[] servicioDto, java.lang.String naturaleza) throws java.rmi.RemoteException{
    if (wSActualizarRol_PortType == null)
      _initWSActualizarRolProxy();
    return wSActualizarRol_PortType.crearRol(rolDto, servicioDto, naturaleza);
  }
  
  public java.lang.String[] modificarRol(int idRol, com.osmosyscol.osmoautenticador.servicioweb.actualizarRol.TipoServicioDto[] servicioDto, java.lang.String naturaleza, java.lang.String accion) throws java.rmi.RemoteException{
    if (wSActualizarRol_PortType == null)
      _initWSActualizarRolProxy();
    return wSActualizarRol_PortType.modificarRol(idRol, servicioDto, naturaleza, accion);
  }
  
  
}