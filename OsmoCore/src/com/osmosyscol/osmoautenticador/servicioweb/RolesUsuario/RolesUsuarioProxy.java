package com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario;

public class RolesUsuarioProxy implements com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.RolesUsuario_PortType {
  private String _endpoint = null;
  private com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.RolesUsuario_PortType rolesUsuario_PortType = null;
  
  public RolesUsuarioProxy() {
    _initRolesUsuarioProxy();
  }
  
  public RolesUsuarioProxy(String endpoint) {
    _endpoint = endpoint;
    _initRolesUsuarioProxy();
  }
  
  private void _initRolesUsuarioProxy() {
    try {
      rolesUsuario_PortType = (new com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.RolesUsuario_ServiceLocator()).getRolesUsuarioSOAP();
      if (rolesUsuario_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)rolesUsuario_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)rolesUsuario_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (rolesUsuario_PortType != null)
      ((javax.xml.rpc.Stub)rolesUsuario_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.RolesUsuario_PortType getRolesUsuario_PortType() {
    if (rolesUsuario_PortType == null)
      _initRolesUsuarioProxy();
    return rolesUsuario_PortType;
  }
  
  public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarDependencias[] listarDependencias(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradalistarDependencias entrada) throws java.rmi.RemoteException{
    if (rolesUsuario_PortType == null)
      _initRolesUsuarioProxy();
    return rolesUsuario_PortType.listarDependencias(entrada);
  }
  
  public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaautorizacion[] autorizacion(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradaautorizacion entrada) throws java.rmi.RemoteException{
    if (rolesUsuario_PortType == null)
      _initRolesUsuarioProxy();
    return rolesUsuario_PortType.autorizacion(entrada);
  }
  
  public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaeliminarRolesporLogin[] eliminarRolesporLogin(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradaeliminarRolesporLogin entrada) throws java.rmi.RemoteException{
    if (rolesUsuario_PortType == null)
      _initRolesUsuarioProxy();
    return rolesUsuario_PortType.eliminarRolesporLogin(entrada);
  }
  
  public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidainsertarRolesporLogin[] insertarRolesporLogin(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradainsertarRolesporLogin entrada) throws java.rmi.RemoteException{
    if (rolesUsuario_PortType == null)
      _initRolesUsuarioProxy();
    return rolesUsuario_PortType.insertarRolesporLogin(entrada);
  }
  
  public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarServicioRol[] listarServicioRol(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradalistarServicioRol entrada) throws java.rmi.RemoteException{
    if (rolesUsuario_PortType == null)
      _initRolesUsuarioProxy();
    return rolesUsuario_PortType.listarServicioRol(entrada);
  }
  
  public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarRolesporLogin[] listarRolesporLogin(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradalistarRolesporLogin entrada) throws java.rmi.RemoteException{
    if (rolesUsuario_PortType == null)
      _initRolesUsuarioProxy();
    return rolesUsuario_PortType.listarRolesporLogin(entrada);
  }
  
  public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarRolesUsuario[] listarRolesUsuario(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradalistarRolesUsuario entrada) throws java.rmi.RemoteException{
    if (rolesUsuario_PortType == null)
      _initRolesUsuarioProxy();
    return rolesUsuario_PortType.listarRolesUsuario(entrada);
  }
  
  public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarRoles[] listarRoles(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradalistarRoles entrada) throws java.rmi.RemoteException{
    if (rolesUsuario_PortType == null)
      _initRolesUsuarioProxy();
    return rolesUsuario_PortType.listarRoles(entrada);
  }
  
  public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaeliminarServiciosRol[] eliminarServiciosRol(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradaeliminarServiciosRol entrada) throws java.rmi.RemoteException{
    if (rolesUsuario_PortType == null)
      _initRolesUsuarioProxy();
    return rolesUsuario_PortType.eliminarServiciosRol(entrada);
  }
  
  public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaadicionarServicioRol[] adicionarServicioRol(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradaadicionarServicioRol entrada) throws java.rmi.RemoteException{
    if (rolesUsuario_PortType == null)
      _initRolesUsuarioProxy();
    return rolesUsuario_PortType.adicionarServicioRol(entrada);
  }
  
  public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaadicionarRol[] adicionarRol(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradaadicionarRol entrada) throws java.rmi.RemoteException{
    if (rolesUsuario_PortType == null)
      _initRolesUsuarioProxy();
    return rolesUsuario_PortType.adicionarRol(entrada);
  }
  
  public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaeliminarRol[] eliminarRol(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradaeliminarRol entrada) throws java.rmi.RemoteException{
    if (rolesUsuario_PortType == null)
      _initRolesUsuarioProxy();
    return rolesUsuario_PortType.eliminarRol(entrada);
  }
  
  public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidagetID[] getID(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradagetID entrada) throws java.rmi.RemoteException{
    if (rolesUsuario_PortType == null)
      _initRolesUsuarioProxy();
    return rolesUsuario_PortType.getID(entrada);
  }
  
  public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarServicios[] listarServicios(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradalistarServicios entrada) throws java.rmi.RemoteException{
    if (rolesUsuario_PortType == null)
      _initRolesUsuarioProxy();
    return rolesUsuario_PortType.listarServicios(entrada);
  }
  
  
}