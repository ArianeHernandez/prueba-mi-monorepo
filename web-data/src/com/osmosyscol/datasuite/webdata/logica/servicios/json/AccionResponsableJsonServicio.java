package com.osmosyscol.datasuite.webdata.logica.servicios.json;

import java.util.List;
import java.util.Map;

import org.apache.cocoon.environment.Session;

import co.htsoft.commons.lang.P;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.logica.dto.Rol;
import com.osmosyscol.datasuite.logica.servicios.RolServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.AccionResponsableServicio;
import com.osmosyscol.datasuite.near.servicios.ReporteSolicitudNEARServicio;

public class AccionResponsableJsonServicio implements JsonService {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public List<Rol> obtenerRolesActivosDisponiblesAccion(Integer id_accion) {
		P.println(id_accion);
		return AccionResponsableServicio.getInstance().obtenerRolesActivosDisponiblesAccion(id_accion);
	}

	public List<Rol> obtenerRolesPorAccion( Integer id_accion ) {
		P.println(id_accion);
		return AccionResponsableServicio.getInstance().obtenerRolesPorAccion(id_accion);
	}

	public Boolean insertarAccionRol(Integer id_rol, Integer id_accion) {
		P.println(id_rol, id_accion);
		return AccionResponsableServicio.getInstance().insertarAccionRol(id_rol, id_accion);
	}

	public Boolean borrarAccionRol(Integer id_campo,	Integer id_accion) {
		P.println(id_campo, id_accion);
		return AccionResponsableServicio.getInstance().borrarAccionRol(id_campo, id_accion);
	}
	
	public Rol obtenerRol(Integer id_rol) {
		P.println(id_rol);
		return RolServicio.getInstance().obtenerRol(id_rol);
	}

	public Boolean guardarAccionResponsable(Integer id_carga, Integer id_accion, Integer id_persona) {
		P.println(id_carga, id_accion, id_persona);
		return AccionResponsableServicio.getInstance().guardarAccionResponsable(id_carga, id_accion, id_persona);
	}
	
	public List<Persona> obtenerListaResponsablesPorAccion(Integer id_accion){
		return  AccionResponsableServicio.getInstance().obtenerListaResponsablesPorAccion(id_accion);
	}
	
	public boolean tieneRolesAsociados(Integer id_accion){
		return  AccionResponsableServicio.getInstance().tieneRolesAsociados(id_accion);
	}
	
	public Integer obtenerResponsable(Integer id_carga, Integer id_instancia){
		return AccionResponsableServicio.getInstance().obtenerResponsable(id_carga, id_instancia);
	}
	
	public List<Persona> obtenerResponsablesAsignados(){
		return AccionResponsableServicio.getInstance().obtenerResponsablesAsignados();
	}
	
	public Map<String, Object> obtenerInfoResponsableCarga(Integer id_carga){
		return AccionResponsableServicio.getInstance().obtenerInfoResponsableCarga(id_carga);
	}
	
	public boolean guardarResponsableCarga(Integer id_persona, Integer id_carga, Integer id_instancia){
		return AccionResponsableServicio.getInstance().guardarResponsableCarga(id_persona, id_carga, id_instancia);
	}
	
}
