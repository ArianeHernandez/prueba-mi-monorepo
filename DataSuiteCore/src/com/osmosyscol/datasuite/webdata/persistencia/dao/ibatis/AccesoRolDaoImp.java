package com.osmosyscol.datasuite.webdata.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.logica.dto.Rol;
import com.osmosyscol.datasuite.webdata.logica.dto.Servicio;
import com.osmosyscol.datasuite.webdata.persistencia.dao.AccesoRolDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class AccesoRolDaoImp extends BaseSqlMapDao implements AccesoRolDao {

	public AccesoRolDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	@SuppressWarnings("unchecked")
	public List<Servicio> obtenerServicios() {
		return queryForList("AccesoRol.obtenerServicios", null);
	}

	@SuppressWarnings("unchecked")
	public List<Rol> obtenerRolesPorServicio(Integer id_usuario, Integer id_servicio) {

		Map<String, Object> map = new HashMap<>();
		map.put("id_servicio", id_servicio);
		map.put("id_usuario", id_usuario);

		return queryForList("AccesoRol.obtenerRolesPorServicio", map);
	}

	public Boolean guardarAccesoRol(Integer id_servicio, Integer id_rol) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_servicio", id_servicio);
		map.put("id_rol", id_rol);

		insert("AccesoRol.guardarAccesoRol", map);
		return true;
	}

	public Boolean eliminarAccesoRol(Integer id_servicio, Integer id_rol) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_servicio", id_servicio);
		map.put("id_rol", id_rol);

		delete("AccesoRol.eliminarAccesoRol", map);
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<Servicio> obtenerServiciosAdministrativo(Integer id_administrativo) {
		return queryForList("AccesoRol.obtenerServiciosAdministrativo", id_administrativo);
	}

	public Integer obtenerIdServicioPorURL(String url) {
		return (Integer) queryForObject("AccesoRol.obtenerIdServicioPorURL", url);
	}

	public Boolean validarAccesoAdministrativo(Integer id_servicio, Integer id_administrativo) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_servicio", id_servicio);
		map.put("id_administrativo", id_administrativo);

		Integer resultado = (Integer) queryForObject("AccesoRol.validarAccesoAdministrativo", map);
		if (resultado > 0) {
			return true;
		}
		return false;
	}

	public Boolean validarAccesoAdministrativoPorUrl(Integer id_administrativo, String url) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("url", url);
		map.put("id_administrativo", id_administrativo);

		Integer resultado = (Integer) queryForObject("AccesoRol.validarAccesoAdministrativoPorUrl", map);
		if (resultado > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Servicio obtenerServicio(Integer id_servicio) {
		return (Servicio) queryForObject("AccesoRol.obtenerServicio", id_servicio);
	}

}
