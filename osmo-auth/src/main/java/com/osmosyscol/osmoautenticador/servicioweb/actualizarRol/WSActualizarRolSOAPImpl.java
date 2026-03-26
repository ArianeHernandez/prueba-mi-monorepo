/**
 * WSActualizarRolSOAPImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osmosyscol.osmoautenticador.servicioweb.actualizarRol;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.osmoautenticador.servicio.WSActualizarRol;

public class WSActualizarRolSOAPImpl implements com.osmosyscol.osmoautenticador.servicioweb.actualizarRol.WSActualizarRol_PortType{
    public java.lang.String[] crearRol(com.osmosyscol.osmoautenticador.servicioweb.actualizarRol.TipoRolDto rolDto, com.osmosyscol.osmoautenticador.servicioweb.actualizarRol.TipoServicioDto[] servicioDto, java.lang.String naturaleza) throws java.rmi.RemoteException {
    	SimpleLogger.setDebug(">> wsActualizarRol CrearRol");
        return WSActualizarRol.crearRol(rolDto, servicioDto,naturaleza);
    }

	public java.lang.String[] modificarRol(int idRol, com.osmosyscol.osmoautenticador.servicioweb.actualizarRol.TipoServicioDto[] servicioDto, java.lang.String naturaleza, java.lang.String accion) throws java.rmi.RemoteException {
		SimpleLogger.setDebug(">> wsActualizarRol ModificarRol");
		return WSActualizarRol.modificarRol(idRol, servicioDto, naturaleza,accion);
    }
}
