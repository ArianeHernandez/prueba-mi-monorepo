/**
 * WSAutenticador.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.osmoautenticador.servicioweb.cliente.autenticar;

public interface WSAutenticador extends java.rmi.Remote {
    public com.osmosyscol.osmoautenticador.servicioweb.cliente.autenticar.RespuestaAutenticar autenticar(com.osmosyscol.osmoautenticador.servicioweb.cliente.autenticar.SolicitudAutenticar solicitudAutenticar) throws java.rmi.RemoteException;
    public com.osmosyscol.osmoautenticador.servicioweb.cliente.autenticar.RespuestaAutorizar autorizar(com.osmosyscol.osmoautenticador.servicioweb.cliente.autenticar.SolicitudAutorizar solicitudAutorizar) throws java.rmi.RemoteException;
}
