package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.OperacionInterna;
import com.osmosyscol.datasuite.persistencia.dao.OperacionInternaDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class OperacionInternaDaoImp extends BaseSqlMapDao implements OperacionInternaDao {

	public OperacionInternaDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OperacionInterna> listarOperacionesInternas() {
		return queryForList("OperacionInterna.listarOperacionesInternas");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OperacionInterna> listarOperacionesInternasPorAccion(Integer id_accion) {
		return queryForList("OperacionInterna.listarOperacionesInternasPorAccion", id_accion);
	}

	@Override
	public Boolean eliminarOperacionInternaDeAccion(Integer id_accion, Integer id_operacion_interna) {
		Map<String, Integer> map = new HashMap<String, Integer>();

		map.put("id_accion", id_accion);
		map.put("id_operacion_interna", id_operacion_interna);

		try {
			insert("OperacionInterna.eliminarOperacionInternaDeAccion", map);
			return true;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en OperacionInternaDaoImp.eliminarOperacionInternaDeAccion", e);
			return false;
		}
	}

	@Override
	public Boolean eliminarTodasOperacionInternaDeAccion(Integer id_accion) {

		try {
			insert("OperacionInterna.eliminarTodasOperacionInternaDeAccion", id_accion);
			return true;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en OperacionInternaDaoImp.eliminarTodasOperacionInternaDeAccion", e);
			return false;
		}
	}

	@Override
	public Boolean insertarOperacionInternaDeAccion(Integer id_accion, Integer id_operacion_interna) {
		Map<String, Integer> map = new HashMap<String, Integer>();

		map.put("id_accion", id_accion);
		map.put("id_operacion_interna", id_operacion_interna);

		try {
			insert("OperacionInterna.insertarOperacionInternaDeAccion", map);
			return true;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en OperacionInternaDaoImp.insertarOperacionInternaDeAccion", e);
			return false;
		}
	}

	@Override
	public OperacionInterna obtenerOperacionInterna(Integer id_operacion_interna) {
		return (OperacionInterna) queryForObject("OperacionInterna.obtenerOperacionInterna", id_operacion_interna);
	}

	public List<OperacionInterna> obtenerOperacionesDisponiblesParaAsignar(Integer id_accion) {
		return queryForList("OperacionInterna.obtenerOperacionesDisponiblesParaAsignar", id_accion);
	}
	
}
