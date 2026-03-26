/**
 * WSAutenticarPTX.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.g3a.cliente;

public interface WSAutenticarPTX extends java.rmi.Remote {
    public com.osmosyscol.g3a.cliente.RespuestaAutenticarPTX autenticar(com.osmosyscol.g3a.cliente.EntradaAutenticarPTX entrada) throws java.rmi.RemoteException;
    public com.osmosyscol.g3a.cliente.RespuestaAutorizarPTX autorizar(com.osmosyscol.g3a.cliente.EntradaAutorizarPTX entrada) throws java.rmi.RemoteException;
    public boolean cerrarSesion(com.osmosyscol.g3a.cliente.EntradaAutorizarPTX entrada) throws java.rmi.RemoteException;
    public boolean perderSesion(com.osmosyscol.g3a.cliente.EntradaAutorizarPTX entrada) throws java.rmi.RemoteException;
    public com.osmosyscol.g3a.cliente.entidades.RespuestaOperacion iniciarTransaccion(com.osmosyscol.g3a.cliente.entidades.SolicitudTransaccion entrada) throws java.rmi.RemoteException;
    public com.osmosyscol.g3a.cliente.entidades.RespuestaOperacion validarPermisosTransaccion(com.osmosyscol.g3a.cliente.entidades.SolicitudTransaccion solicitud) throws java.rmi.RemoteException;
    public com.osmosyscol.g3a.cliente.entidades.RespuestaOperacion solicitarActivacion(com.osmosyscol.g3a.cliente.entidades.SolicitudActivacion solicitud) throws java.rmi.RemoteException;
}
