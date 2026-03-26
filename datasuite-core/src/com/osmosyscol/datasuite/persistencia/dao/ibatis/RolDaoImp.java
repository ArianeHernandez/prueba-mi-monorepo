package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.logica.dto.Rol;
import com.osmosyscol.datasuite.persistencia.dao.RolDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class RolDaoImp extends BaseSqlMapDao implements RolDao {

	public RolDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	public Boolean guardarRol(Rol rol) {
		insert("Rol.crearRol", rol);
		return true;

	}

	public Rol obtenerRol(Integer id_rol) {
		return (Rol)queryForObject("Rol.obtenerRol", id_rol);
	}

	@SuppressWarnings("unchecked")
	public List<Rol> obtenerRolesHijos(Integer id_rol_padre) {
		return queryForList("Rol.obtenerRolesHijos", id_rol_padre);
	}

	public Boolean desactivarRol(Integer id_rol) {
		update("Rol.desactivarRol", id_rol);
		return true;

	}

	public Boolean activarRol(Integer id_rol) {
		update("Rol.activarRol", id_rol);
		return true;

	}

	public Boolean actualizarRol(Rol rol) {
		update("Rol.actualizarRol", rol);
		return true;

	}

	public Boolean eliminarRol(Integer id_rol) {
		delete("Rol.eliminarRol", id_rol);
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<Rol> obtenerRolesActivos() {
		return queryForList("Rol.obtenerRolesActivos",null);
	}

	@SuppressWarnings("unchecked")
	public List<Rol> obtenerRolesPorPersona(Integer idPersona) {

		return queryForList("Rol.obtenerRolesPorPersona", idPersona);
	}

	public Boolean eliminarRolesAdministrativo(Integer idAdministrativo) {
		delete("Rol.eliminarRolesAdministrativo", idAdministrativo);
		return true;
	}

	public Boolean guardarRolesAdministrativo(Integer idAdministrativo, List<Integer> roles) {
		eliminarRolesAdministrativo(idAdministrativo);
		
		if (roles !=null) {
			for (Integer rol : roles) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id_administrativo", idAdministrativo);
				map.put("id_rol", rol);
				insert("Rol.guardarRolAdministrativo", map);
			}
		}
		
		return true;
	}

	public Integer totalRolesActivos() {
		return (Integer)queryForObject("Rol.totalRolesActivos",null);
	}

	@SuppressWarnings("unchecked")
	public List<Rol> obtenerTodosLosRoles(Integer id_usuario) {
		return queryForList("Rol.obtenerTodosLosRoles",id_usuario);
	}

	public Integer totalPersonasPorRol(Integer id_rol) {
		return (Integer)queryForObject("Rol.totalPersonasPorRol",id_rol);
	}

	public Boolean eliminarRelacionesRolAdministrativoPorRol(Integer id_rol) {
		delete("Rol.eliminarRelacionesRolAdministrativoPorRol", id_rol);
		return true;
	}

	public Boolean eliminarRelacionesRolServicioPorRol(Integer id_rol) {
		delete("Rol.eliminarRelacionesRolServicioPorRol", id_rol);
		return true;
	}

	public Boolean eliminarRelacionesRolInstanciaPorRol(Integer id_rol) {
		delete("Rol.eliminarRelacionesRolInstanciaPorRol", id_rol);
		return true;
	}

	public Boolean eliminarRelacionesRolHijoPorRolPadre(Integer id_rol) {
		
		update("Rol.eliminarRelacionesRolHijoPorRolPadre", id_rol);
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<Rol> obtenerRolesPorAdministrativoInstancia(
			Integer id_administrativo, Integer id_instancia) {
		Map<String,Object> params = new HashMap<>();
		params.put("id_administrativo", id_administrativo);
		params.put("id_instancia", id_instancia);
		return (List<Rol>)queryForList("Rol.obtenerRolesPorAdministrativoInstancia",params);
	}
	
	

}
