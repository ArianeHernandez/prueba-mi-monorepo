package com.osmosyscol.datasuite.modelatos.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.config.Constantes;
import com.osmosyscol.datasuite.modelatos.logica.dto.Estructura;
import com.osmosyscol.datasuite.modelatos.logica.dto.Grupo;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.GrupoDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class GrupoDaoImp extends BaseSqlMapDao implements GrupoDao {

	public GrupoDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	// --------------------------------------------------------

	@SuppressWarnings("unchecked")
	public List<Grupo> obtenerGruposPorEstructura(Integer id_estructura) {
		Estructura estructura = new Estructura();
		estructura.setId_estructura(id_estructura);
		return queryForList("Grupo.obtenerGruposPorEstructura", estructura);
	}

	// --------------------------------------------------------

	@SuppressWarnings("unchecked")
	public List<Grupo> obtenerGruposPorPersona(Integer id_persona, Integer id_negocio, Integer numero_pagina) {
		
		try {
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("id_persona", id_persona);
			map.put("id_negocio", id_negocio);
			
			return queryForListPag("Grupo.obtenerGruposPorPersona", map, numero_pagina, Constantes.PAGINACIONLISTADO);
			
		} catch (Exception e) {
			SimpleLogger.setError("Grupo.obtenerGruposPorPersona", e);
			return null;
		}
		
	}

	public Grupo obtenerGrupo(Integer id_grupo) {
		Grupo grupo = new Grupo();
		grupo.setId_grupo(id_grupo);
		return (Grupo) queryForObject("Grupo.obtenerGrupo", grupo);
	}

	public Boolean guardarGrupo(Grupo grupo, Integer id_persona, Integer id_modelo) {

		if (grupo != null) {
			try {
				if (grupo.getId_grupo() == null) {
					grupo.setNombre(grupo.getNombre().toUpperCase());
					grupo.setId_modelo(id_modelo);
					
					insert("Grupo.insertarGrupo", grupo);
				} else {
					update("Grupo.actualizarGrupo", grupo);
				}
				
				return true;

			} catch (Exception e) {
				SimpleLogger.setError("Error al guardar Grupo. (GrupoDaoImp.guardarGrupo)", e);
			}
		}

		return false;
	}

	public Boolean eliminarGrupo(Integer id_grupo) {
		try {
			delete("Grupo.eliminarGrupoEstructura", id_grupo);			
			delete("Grupo.eliminarGrupo", id_grupo);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return false;
		}
		return true;	
	}

	public Integer totalGruposPorPersona(Integer id_persona, Integer id_negocio) {
		try {
			
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("id_persona", id_persona);
			map.put("id_negocio", id_negocio);
			
			return (Integer) queryForObject("Grupo.totalGruposPorPersona", map);

		} catch (Exception e) {
			SimpleLogger.setError("Grupo.totalGruposPorPersona", e);
			return null;
		}
	}

	public Boolean guardarGrupoEstructura(Integer id_grupo, Integer id_estructura, String accion) {
		
		if (id_grupo != null && id_estructura != null) {
			try {
				Map<String, Integer> parametros = new HashMap<String, Integer>();
				parametros.put("id_grupo", id_grupo);
				parametros.put("id_estructura", id_estructura);
				
				if(accion.equals("guardar")){
					
					insert("Grupo.guardarGrupoEstructura", parametros);
					
				}else if(accion.equals("eliminar")){
					
					delete("Grupo.eliminarGrupoPorEstructura", parametros);
				}

			} catch (Exception e) {
				SimpleLogger.setError("Error al guardar Grupo. (GrupoDaoImp.guardarGrupoEstructura)", e);
				return false;
			}
		}
		return true;
	}

	public Grupo obtenerGrupoPorNombre(String nombreGrupo, Integer id_modelo) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("nombreGrupo", nombreGrupo);
			map.put("id_modelo", id_modelo);
			
			return (Grupo) queryForObject("Grupo.obtenerGrupoPorNombre", map);

		} catch (Exception e) {
			SimpleLogger.setError("Grupo.obtenerGrupoPorNombre", e);
			return null;
		}
	}
	
	
}
