package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.logica.dto.Contenido;
import com.osmosyscol.datasuite.persistencia.dao.ContenidoDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class ContenidoDaoImp extends BaseSqlMapDao implements ContenidoDao {

	public ContenidoDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	public Boolean guardarContenido(Contenido contenido) {
		return null;
	}

	public Contenido obtenerContenido(String url, String etiqueta) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("url", url);
		map.put("etiqueta", etiqueta);
		return (Contenido)queryForObject("Contenido.obtenerContenido", map);
		
	}
	
	@SuppressWarnings("unchecked")
	public java.util.List<Contenido> obtenerContenidosByURL(String url) {
		return queryForList("Contenido.obtenerContenidosByURL", url);
		
	}

	@SuppressWarnings("unchecked")
	public List<Contenido> obtenerTodosLosContenidos(Integer numero_pagina, Integer registrosPorPagina) {
		
		return queryForListPag("Contenido.obtenerTodosLosContenidos", null, numero_pagina, registrosPorPagina);
	}

	public Boolean actualizarTextoContenido(Contenido contenido) {
		update("Contenido.actualizarTextoContenido", contenido);
		return true;
	}

	public Contenido obtenerContenidoPorID(Integer id_contenido) {
		
		return (Contenido)queryForObject("Contenido.obtenerContenidoPorID", id_contenido);
	}

	public Integer obtenerTotalTodosLosContenidos() {
		return (Integer)queryForObject("Contenido.obtenerTotalTodosLosContenidos", null);
	}

	@SuppressWarnings("unchecked")
	public List<String> obtenerTodosLosNombreServicio() {
		return queryForList("Contenido.obtenerTodosLosNombreServicio", null);
	}

	@SuppressWarnings("unchecked")
	public List<Contenido> obtenerContenidosPorServicio(String nombre_servicio) {
		
		return queryForList("Contenido.obtenerContenidosPorServicio", nombre_servicio);
	}

	
}
