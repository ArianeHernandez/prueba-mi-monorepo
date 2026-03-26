package com.osmosyscol.datasuite.logica.servicios;

import java.util.ArrayList;
import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.Contenido;
import com.osmosyscol.datasuite.logica.dto.ServicioContenido;
import com.osmosyscol.datasuite.persistencia.dao.ContenidoDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class ContenidoServicio {

	private static ContenidoServicio contenidoServicio;

	private ContenidoServicio() {
	}

	public static ContenidoServicio getInstance() {
		if (contenidoServicio == null) {
			contenidoServicio = new ContenidoServicio();
		}
		return contenidoServicio;
	}

	/*
	 * Consulta un contenido de una etiqueta y una url especifica.
	 */

	public Contenido obtenerContenido(String url, String etiqueta) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			ContenidoDao contenidoDao = (ContenidoDao) daoManager.getDao(ContenidoDao.class);
			return contenidoDao.obtenerContenido(url, etiqueta);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	/*
	 * Consulta todos los contenidos en la base de datos para una url
	 * especifica.
	 */

	public List<Contenido> obtenerContenidosByUrlBD(String url) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			ContenidoDao contenidoDao = (ContenidoDao) daoManager.getDao(ContenidoDao.class);
			return contenidoDao.obtenerContenidosByURL(url);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public List<Contenido> obtenerContenidosByURL(String url) {
		return obtenerContenidosByUrlBD(url);
	}
	
	/**
	 * Retorna el listado de contenidos paginado
	 * @param numero_pagina
	 * @param registrosPorPagina
	 * @return
	 */
	
	public List<ServicioContenido> obtenerAllServiciosContenido(){
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			ContenidoDao contenidoDao = (ContenidoDao) daoManager.getDao(ContenidoDao.class);
			
			
			List<ServicioContenido> servicioContenidos = new ArrayList<ServicioContenido>();
			
			List<String> servicios = contenidoDao.obtenerTodosLosNombreServicio();
			
			if(servicios!=null && servicios.size()>0){
				
				
				for (String servicio : servicios) {
					
					List<Contenido> contenidos = contenidoDao.obtenerContenidosPorServicio(servicio);
					
					ServicioContenido servicioContenido = new ServicioContenido();
					servicioContenido.setNombre_servicio(servicio);
					servicioContenido.setContenidos(contenidos);
					
					//se agrega a lista de servicios
					servicioContenidos.add(servicioContenido);
					
				}
				
			}
			
			return servicioContenidos;
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
		
	}

	public List<Contenido> obtenerTodosLosContenidos(Integer numero_pagina, Integer registrosPorPagina){
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			ContenidoDao contenidoDao = (ContenidoDao) daoManager.getDao(ContenidoDao.class);
			
			
			
			return contenidoDao.obtenerTodosLosContenidos(numero_pagina, registrosPorPagina);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
		
	}
	
	public Integer obtenerTotalTodosLosContenidos(){
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			ContenidoDao contenidoDao = (ContenidoDao) daoManager.getDao(ContenidoDao.class);
			return contenidoDao.obtenerTotalTodosLosContenidos();
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
		
	}
	
	public Boolean actualizarTextoContenido(Contenido contenido){
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			ContenidoDao contenidoDao = (ContenidoDao) daoManager.getDao(ContenidoDao.class);
			
			return contenidoDao.actualizarTextoContenido(contenido);
			
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	
	public Contenido obtenerContenidoPorID(Integer id_contenido){
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			ContenidoDao contenidoDao = (ContenidoDao) daoManager.getDao(ContenidoDao.class);
			return contenidoDao.obtenerContenidoPorID(id_contenido);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
		
	}
	

}
