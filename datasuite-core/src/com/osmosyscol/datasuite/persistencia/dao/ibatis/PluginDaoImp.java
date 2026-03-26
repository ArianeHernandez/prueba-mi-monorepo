package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.Plugin;
import com.osmosyscol.datasuite.persistencia.dao.PluginDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class PluginDaoImp extends BaseSqlMapDao implements PluginDao {

	public PluginDaoImp (DaoManager daoManager){
		super(daoManager);
	}
	
	public Plugin obtenerPlugin(String nombre) {
		try{
			return (Plugin) queryForObject("Plugin.obtenerPlugin", nombre);
		}catch (Exception e){
			SimpleLogger.setError("Error en PluginDaoImp.obtenerPlugin", e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Plugin> obtenerPlugins() {
		try{
			return (List<Plugin>) queryForList("Plugin.obtenerPlugins");
		}catch (Exception e){
			SimpleLogger.setError("Error en PluginDaoImp.obtenerPlugins", e);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Plugin> obtenerPluginsActivos() {
		try{
			return (List<Plugin>) queryForList("Plugin.obtenerPluginsActivos");
		}catch (Exception e){
			SimpleLogger.setError("Error en PluginDaoImp.obtenerPluginsActivos", e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Plugin> obtenerPluginsPorAplicacion(String id_aplicacion) {
		try{
			return (List<Plugin>) queryForList("Plugin.obtenerPluginsPorAplicacion", id_aplicacion);
		}catch (Exception e){
			SimpleLogger.setError("Error en PluginDaoImp.obtenerPluginsPorAplicacion", e);
		}
		return null;
	}

}
