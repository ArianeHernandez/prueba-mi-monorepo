package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.logica.dto.Rol;
import com.osmosyscol.datasuite.persistencia.dao.AccionResponsableDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class AccionResponsableDaoImp extends BaseSqlMapDao implements AccionResponsableDao {

	public AccionResponsableDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	@Override
	public List<Rol> obtenerRolesPorAccion(Integer id_accion) {
		return queryForList("AccionResponsable.obtenerRolesPorAccion", id_accion);
	}

	@Override
	public List<Rol> obtenerRolesActivosDisponiblesAccion(Integer id_accion) {
		return queryForList("AccionResponsable.obtenerRolesActivosDisponiblesAccion", id_accion);
	}

	@Override
	public Boolean insertarAccionRol(Integer id_rol, Integer id_accion) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_rol", id_rol);
		map.put("id_accion", id_accion);
		insert("AccionResponsable.insertarAccionRol", map);
		return true;
	}

	@Override
	public Boolean borrarAccionRol(Integer id_rol, Integer id_accion) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_rol", id_rol);
		map.put("id_accion", id_accion);
		insert("AccionResponsable.borrarAccionRol", map);
		return true;
	}

	@Override
	public List<Persona> obtenerListaResponsablesPorAccion(Integer id_accion) {
		return queryForList("AccionResponsable.obtenerListaResponsablesPorAccion", id_accion);
	}

	@Override
	public Boolean guardarAccionResponsable(Integer id_carga, Integer id_instancia, Integer id_persona) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_carga", id_carga);
		map.put("id_instancia", id_instancia);
		map.put("id_persona", id_persona);
		insert("AccionResponsable.guardarAccionResponsable", map);
		return true;
	}

	@Override
	public Integer obtenerResponsableAdmin(Integer id_carga, Integer id_instancia) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_carga", id_carga);
		map.put("id_instancia", id_instancia);
		return (Integer) queryForObject("AccionResponsable.obtenerResponsableAdmin", map);
	}

	@Override
	public boolean actualizarResponsable(Integer id_carga, Integer id_instancia, Integer id_persona) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_carga", id_carga);
		map.put("id_instancia", id_instancia);
		map.put("id_persona", id_persona);
		update("AccionResponsable.actualizarResponsable", map);
		return true;
	}
	
	public Integer obtenerResponsable(Integer id_carga, Integer id_accion) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_carga", id_carga);
		map.put("id_accion", id_accion);
		return (Integer) queryForObject("AccionResponsable.obtenerResponsable", map);
	}

	@SuppressWarnings("unchecked")
	public List<Integer> obtenerResponsablesPorCarga(Integer id_carga) {
		return queryForList("AccionResponsable.obtenerResponsablesPorCarga", id_carga);
	}
	
}
