package com.osmosyscol.datasuite.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.logica.dto.Rol;



public interface AccionResponsableDao {

	List<Rol> obtenerRolesPorAccion(Integer id_accion);

	List<Rol> obtenerRolesActivosDisponiblesAccion(Integer id_accion);

	Boolean insertarAccionRol(Integer id_rol, Integer id_accion);

	Boolean borrarAccionRol(Integer id_rol, Integer id_accion);

	List<Persona> obtenerListaResponsablesPorAccion(Integer id_accion);

	Boolean guardarAccionResponsable(Integer id_carga, Integer id_instancia, Integer id_persona);

	Integer obtenerResponsableAdmin(Integer id_carga, Integer id_instancia);

	boolean actualizarResponsable(Integer id_carga, Integer id_instancia, Integer id_persona);

	Integer obtenerResponsable(Integer id_carga, Integer id_instancia);
	
	List<Integer> obtenerResponsablesPorCarga (Integer id_carga);
	
}

