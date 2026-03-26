/**
 * RolesUsuario_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario;

public interface RolesUsuario_PortType extends java.rmi.Remote {
    public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarDependencias[] listarDependencias(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradalistarDependencias entrada) throws java.rmi.RemoteException;
    public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaautorizacion[] autorizacion(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradaautorizacion entrada) throws java.rmi.RemoteException;
    public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaeliminarRolesporLogin[] eliminarRolesporLogin(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradaeliminarRolesporLogin entrada) throws java.rmi.RemoteException;
    public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidainsertarRolesporLogin[] insertarRolesporLogin(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradainsertarRolesporLogin entrada) throws java.rmi.RemoteException;
    public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarServicioRol[] listarServicioRol(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradalistarServicioRol entrada) throws java.rmi.RemoteException;
    public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarRolesporLogin[] listarRolesporLogin(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradalistarRolesporLogin entrada) throws java.rmi.RemoteException;
    public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarRolesUsuario[] listarRolesUsuario(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradalistarRolesUsuario entrada) throws java.rmi.RemoteException;
    public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarRoles[] listarRoles(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradalistarRoles entrada) throws java.rmi.RemoteException;
    public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaeliminarServiciosRol[] eliminarServiciosRol(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradaeliminarServiciosRol entrada) throws java.rmi.RemoteException;
    public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaadicionarServicioRol[] adicionarServicioRol(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradaadicionarServicioRol entrada) throws java.rmi.RemoteException;
    public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaadicionarRol[] adicionarRol(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradaadicionarRol entrada) throws java.rmi.RemoteException;
    public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidaeliminarRol[] eliminarRol(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradaeliminarRol entrada) throws java.rmi.RemoteException;
    public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidagetID[] getID(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradagetID entrada) throws java.rmi.RemoteException;
    public com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarServicios[] listarServicios(com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoEntradalistarServicios entrada) throws java.rmi.RemoteException;
}
