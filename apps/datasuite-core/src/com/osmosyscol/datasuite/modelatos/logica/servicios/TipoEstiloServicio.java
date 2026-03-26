package com.osmosyscol.datasuite.modelatos.logica.servicios;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.MapCache;
import com.osmosyscol.datasuite.modelatos.logica.dto.TipoEstilo;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.TipoEstiloDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class TipoEstiloServicio {
	
	private static TipoEstiloServicio service;
	private static TipoEstiloDao dao;
	
	private MapCache<Integer, TipoEstilo> cacheTipoEstilos;
	
	private TipoEstiloServicio(){
		try {
			if (dao == null){				
				DaoManager daoManager = DaoConfig.getDaoManager();
				dao = (TipoEstiloDao) daoManager.getDao(TipoEstiloDao.class);
			}
		}catch (Exception e){
			SimpleLogger.setError("Error en constructor", e);
		}
		cacheTipoEstilos = new MapCache<Integer, TipoEstilo>(20);
	}
	
	public static TipoEstiloServicio getInstance(){
		if (service == null){
			service = new TipoEstiloServicio();
		}
		return service;
	}
	
	public List<TipoEstilo> obtenerTipoEstilos(){
		try {
			List<TipoEstilo> estilos = dao.obtenerTipoEstilos();
			if(CollectionUtils.isNotEmpty(estilos)){
				for(TipoEstilo estilo : estilos){
					cacheTipoEstilos.put(estilo.getId_tipo_estilo(), estilo);
				}
			}
			return estilos;
		}catch (Exception e){
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
	}
	
	public TipoEstilo obtenerTipoEstilo(Integer id_tipo_estilo){
		try {
			if (id_tipo_estilo == null){
				throw new IllegalArgumentException("El atributo id_tipo_estilo no puede ser nulo");
			}
			
			if (cacheTipoEstilos.contains(id_tipo_estilo)){
				return cacheTipoEstilos.get(id_tipo_estilo);
			}
			
			TipoEstilo estilo = dao.obtenerTipoEstilo(id_tipo_estilo);
			cacheTipoEstilos.put(estilo.getId_tipo_estilo(), estilo);
			return estilo;
		}catch (Exception e){
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
	}
	

}
