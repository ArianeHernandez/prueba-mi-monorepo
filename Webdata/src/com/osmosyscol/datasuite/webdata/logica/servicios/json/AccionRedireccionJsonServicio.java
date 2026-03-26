package com.osmosyscol.datasuite.webdata.logica.servicios.json;

import java.util.List;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.logica.dto.AccionRedireccion;
import com.osmosyscol.datasuite.modelatos.logica.servicios.AccionRedireccionServicio;

public class AccionRedireccionJsonServicio implements JsonService{

	private Session session;
	
	@Override
	public void setSession(Session session) {
		 this.session = session;
	}

	public Integer obtenerSiguienteId () {
		return AccionRedireccionServicio.getInstance().obtenerSiguienteId();
	}
	
	public AccionRedireccion obtenerAccionRedireccionPorId (Integer id_accion_redireccion) {
		return AccionRedireccionServicio.getInstance().obtenerAccionRedireccionPorId(id_accion_redireccion);
	}
	
	public List<AccionRedireccion> obtenerAccionesRedireccionPorInstancia (Integer id_instancia) {
		return AccionRedireccionServicio.getInstance().obtenerAccionesRedireccionPorInstancia(id_instancia);
	}
	
	public Boolean guardarAccionRedireccion (AccionRedireccion accionRedireccion) {
		return AccionRedireccionServicio.getInstance().guardarAccionRedireccion(accionRedireccion);
	}
	
	public Boolean borrarAccionRedireccion (Integer id_accion_redireccion) {
		return AccionRedireccionServicio.getInstance().borrarAccionRedireccion(id_accion_redireccion);
	}
	
	public Boolean actualizarNombreAccionRedireccion (Integer id_accion_redireccion, String nombre) {
		return AccionRedireccionServicio.getInstance().actualizarNombreAccionRedireccion(id_accion_redireccion, nombre);
	}
	
	public Boolean actualizarEndpointAccionRedireccion (Integer id_accion_redireccion, String endpoint) {
		return AccionRedireccionServicio.getInstance().actualizarEndpointAccionRedireccion(id_accion_redireccion, endpoint);
	}
	
	public Boolean actualizarAccionRedireccion (AccionRedireccion accionRedireccion) {
		return AccionRedireccionServicio.getInstance().actualizarAccionRedireccion(accionRedireccion);
	}
}
