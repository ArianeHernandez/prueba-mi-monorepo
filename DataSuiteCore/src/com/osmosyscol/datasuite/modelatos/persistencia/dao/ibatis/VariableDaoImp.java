package com.osmosyscol.datasuite.modelatos.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;





import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.config.Constantes;
import com.osmosyscol.datasuite.modelatos.logica.dto.Variable;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.VariableDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class VariableDaoImp extends BaseSqlMapDao implements VariableDao {

	public VariableDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	@SuppressWarnings("unchecked")
	public List<Variable> obtenerVariablesPorPersona(Integer id_persona, Integer id_modelo, Integer numero_pagina) {

		try {
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("id_persona", id_persona);
			map.put("id_modelo", id_modelo);

			return queryForListPag("Variable.obtenerVariablesPorPersona", map, numero_pagina, Constantes.PAGINACIONLISTADO);

		} catch (Exception e) {
			SimpleLogger.setError("Variable.obtenerVariablesPorPersona", e);
			return null;
		}
	}

	// ---------------------------------------

	public Integer totalVariablesPorPersona(Integer id_persona, Integer id_modelo) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_persona", id_persona);
		map.put("id_modelo", id_modelo);

		return (Integer) queryForObject("Variable.totalVariablesPorPersona", map);
	}

	// ---------------------------------------

	public Variable obtenerVariable(Integer id_variable) {
		Variable variable = new Variable();
		variable.setId_variable(id_variable);
		return (Variable) queryForObject("Variable.obtenerVariable", variable);
	}

	@SuppressWarnings("unchecked")
	public List<Variable> obtenerVariablesPorModelo(Integer id_modelo) {
		return queryForList("Variable.obtenerVariablesPorModelo", id_modelo);
	}

	public Boolean guardarVariable(Variable variable) {
		if (variable.getId_variable() == null) {
			insert("Variable.guardarVariable", variable);
		} else {
			update("Variable.actualizarVariable", variable);
		}

		return true;
	}

	@SuppressWarnings("unchecked")
	public List<Variable> obtenerVariablesPorModeloId(Integer id_modelo, Integer id_persona) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_modelo", id_modelo);
		map.put("id_persona", id_persona);

		return queryForList("Variable.obtenerVariablesPorModeloId", map);
	}

	public Boolean eliminarValoresVariables(Integer id_usuario) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_usuario", id_usuario);

		delete("Variable.eliminarValoresVariables", map);

		return true;
	}

	public Boolean eliminarVariableValor(Integer id_variable, Integer id_usuario) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_variable", id_variable);
		map.put("id_usuario", id_usuario);

		delete("Variable.eliminarVariableValor", map);

		return true;
	}

	public Boolean guardarVariableValor(Variable variable, Integer id_usuario) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (variable != null) {
			map.put("id_variable", variable.getId_variable());
			map.put("valor", variable.getValor_variable());

			map.put("id_usuario", id_usuario);

			delete("Variable.guardarVariableValor", map);

			return true;
		}
		return false;

	}

	public String obtenerValorVariable(Integer id_variable, Integer id_persona) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_variable", id_variable);
		map.put("id_persona", id_persona);

		Integer cantidad = (Integer) queryForObject("Variable.contarObtenerValorVariable", map);

		if (cantidad == 0) {
			return (String) queryForObject("Variable.obtenerValorVariableDefecto", map);
		} else {
			return (String) queryForObject("Variable.obtenerValorVariable", map);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Variable> obtenerVariablesPorPersona(Integer idPersona) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("id_persona", idPersona);
			map.put("id_modelo", 1);

			return queryForList("Variable.obtenerVariablesPorPersona", map);

		} catch (Exception e) {
			SimpleLogger.setError("Variable.obtenerVariablesPorPersona", e);
			return null;
		}
	}

	public Variable obtenerVariablePorNombre(String nombre_variable) {
		return (Variable) queryForObject("Variable.obtenerVariablePorNombre", nombre_variable);
	}

	public Variable obtenerVariableUsuarioPorIdUsuarioIdVariable(Integer id_usuario, Integer id_variable) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_usuario", id_usuario);
		map.put("id_variable", id_variable);
		return (Variable) queryForObject("Variable.obtenerVariableUsuarioPorIdUsuarioIdVariable", map);
	}

	public Boolean guardarVariableUsuario(Integer id_usuario, Integer id_variable) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_usuario", id_usuario);
		map.put("id_variable", id_variable);
		
		insert("Variable.guardarVariableUsuario", map);
		return true;
	}

	public Boolean eliminarVariableUsuario(Integer id_usuario, Integer id_variable) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_usuario", id_usuario);
		map.put("id_variable", id_variable);
		
		delete("Variable.eliminarVariableUsuario", map);
		return true;
	}

}
