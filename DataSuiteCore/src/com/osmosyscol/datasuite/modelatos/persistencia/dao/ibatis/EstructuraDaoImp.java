package com.osmosyscol.datasuite.modelatos.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.config.Constantes;
import com.osmosyscol.datasuite.modelatos.logica.dto.Estructura;
import com.osmosyscol.datasuite.modelatos.logica.dto.Grupo;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.EstructuraDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class EstructuraDaoImp extends BaseSqlMapDao implements EstructuraDao {

	public EstructuraDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	// --------------------------------------------------------
	@SuppressWarnings("unchecked")
	public List<Estructura> obtenerEstructuras() {
		return queryForList("Estructura.obtenerEstructuras", null);
	}

	// --------------------------------------------------------
	@SuppressWarnings("unchecked")
	public List<Estructura> obtenerEstructurasPorGrupo(Integer id_grupo) {
		Grupo grupo = new Grupo();
		grupo.setId_grupo(id_grupo);
		return queryForList("Estructura.obtenerEstructurasPorGrupo", grupo);
	}

	// --------------------------------------------------------

	@SuppressWarnings("unchecked")
	public List<Estructura> obtenerEstructurasPorPersona(Integer id_modelo, Integer numero_pagina) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_modelo", id_modelo);

		return queryForListPag("Estructura.obtenerEstructurasPorPersona", map, numero_pagina,
				Constantes.PAGINACIONLISTADO);
	}

	@SuppressWarnings("unchecked")
	public List<Estructura> obtenerEstructurasAccesiblesPorPersona(Integer id_modelo, String filtro,
			Integer numero_pagina) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_modelo", id_modelo);
		map.put("filtro", filtro);

		return queryForListPag("Estructura.obtenerEstructurasAccesiblesPorPersona", map, numero_pagina,
				Constantes.PAGINACIONLISTADO);
	}

	@SuppressWarnings("unchecked")
	public List<Estructura> obtenerEstructurasAccesiblesPorPersona(Integer id_modelo) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_modelo", id_modelo);

		return queryForList("Estructura.obtenerEstructurasAccesiblesPorPersona", map);
	}

	public Integer totalEstructurasPorPersona(Integer id_modelo) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_modelo", id_modelo);

		return (Integer) queryForObject("Estructura.totalEstructurasPorPersona", map);
	}

	public Integer totalEstructurasAccesiblesPorPersona(Integer id_modelo, String filtro) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_modelo", id_modelo);
		map.put("filtro", filtro);

		return (Integer) queryForObject("Estructura.totalEstructurasAccesiblesPorPersona", map);
	}

	// --------------------------------------------------------

	public Estructura obtenerEstructura(Integer id_estructura) {
		Estructura estructura = new Estructura();
		estructura.setId_estructura(id_estructura);
		return (Estructura) queryForObject("Estructura.obtenerEstructura", estructura);
	}

	public Estructura guardarEstructura(Estructura estructura, Integer id_persona, Integer id_modelo) {
		estructura.setNombre(estructura.getNombre().toUpperCase());
		if (estructura.getId_estructura() == null) {
			estructura.setId_modelo(id_modelo);

			Integer id_estructura = (Integer) queryForObject("Estructura.siguienteId", null);
			estructura.setId_estructura(id_estructura);

			insert("Estructura.guardarEstructura", estructura);
		} else {
			update("Estructura.actualizarEstructura", estructura);
		}
		return estructura;
	}

	public Integer contarCamposPorEstructura(Integer id_estructura) {
		return (Integer) queryForObject("Estructura.contarCamposPorEstructura", id_estructura);
	}

	// --------------------------------------------------------

	public Integer getSiguienteID_Objeto() {
		return (Integer) queryForObject("Estructura.siguienteID_Objeto", null);
	}

	@SuppressWarnings("unchecked")
	public List<Estructura> obtenerEstructurasPadre(Integer id_estructura) {
		return queryForList("Estructura.obtenerEstructurasPadre", id_estructura);
	}

	@SuppressWarnings("unchecked")
	public List<Estructura> obtenerEstructurasRelacionadas(Integer id_estructura) {
		return queryForList("Estructura.obtenerEstructurasRelacionadas", id_estructura);
	}

	public Boolean eliminarEstructura(Integer id_estructura) {
		update("Estructura.eliminarEstructura", id_estructura);
		return true;
	}

	public Estructura obtenerEstructuraPorNombre(String nombreEstructura, Integer id_modelo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nombreEstructura", nombreEstructura);
		map.put("id_modelo", id_modelo);

		return (Estructura) queryForObject("Estructura.obtenerEstructuraPorNombre", map);
	}

	@SuppressWarnings("unchecked")
	public List<Estructura> obtenerEstructurasPorEstado(String estado) {
		return queryForList("Estructura.obtenerEstructurasPorEstado", estado);
	}

	// --------------------------------------------------------

	@SuppressWarnings("unchecked")
	public List<Estructura> obtenerEstructurasPorFormatoDeSalida(Integer id_formato_salida) {
		return queryForList("Estructura.obtenerEstructurasPorFormatoDeSalida", id_formato_salida);
	}

	@Override
	public Boolean actualizarPosicion(Integer id_estructura, Integer xpos, Integer ypos) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_estructura", id_estructura);
		map.put("xpos", xpos);
		map.put("ypos", ypos);

		update("Estructura.actualizarPosicion", map);

		return true;
	}

	@Override
	public Boolean actualizarPosicionGrupo(Integer id_estructura, Integer xpos, Integer ypos, Integer id_grupo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_estructura", id_estructura);
		map.put("xpos", xpos);
		map.put("ypos", ypos);
		map.put("id_grupo", id_grupo);

		update("Estructura.actualizarPosicionGrupo", map);

		return true;
	}

	@Override
	public List<String> buscarNombresEstructurasAccesibles(String filtro) {
		return queryForList("Estructura.buscarNombresEstructurasAccesibles", filtro);
	}

}
