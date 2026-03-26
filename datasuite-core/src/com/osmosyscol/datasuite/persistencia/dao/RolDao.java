package com.osmosyscol.datasuite.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.logica.dto.Rol;

public interface RolDao {

	public Boolean guardarRol(Rol rol);
	
	public Rol obtenerRol(Integer id_rol);
	
	public List<Rol> obtenerRolesHijos(Integer id_rol_padre);
	
	public Boolean desactivarRol(Integer id_rol);
	
	public Boolean activarRol(Integer id_rol);
	
	public Boolean actualizarRol(Rol rol);
	
	public Boolean eliminarRol(Integer id_rol);
	
	public List<Rol> obtenerTodosLosRoles(Integer id_usuario);
	
	public List<Rol> obtenerRolesActivos();

	public List<Rol> obtenerRolesPorPersona(Integer idPersona);

	public Boolean guardarRolesAdministrativo(Integer idAdministrativo, List<Integer> roles);
	
	public Integer totalRolesActivos();
	
	public Integer totalPersonasPorRol(Integer id_rol);
	
	public Boolean eliminarRelacionesRolAdministrativoPorRol(Integer id_rol);
	
	public Boolean eliminarRelacionesRolServicioPorRol(Integer id_rol);
	
	public Boolean eliminarRelacionesRolInstanciaPorRol(Integer id_rol);
	
	public Boolean eliminarRelacionesRolHijoPorRolPadre(Integer id_rol);
	
	public List<Rol> obtenerRolesPorAdministrativoInstancia(Integer id_administrativo, Integer id_instancia);
}
