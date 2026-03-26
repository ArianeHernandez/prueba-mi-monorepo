package com.osmosyscol.datasuite.logica.servicios;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.cocoon.environment.Session;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.AutenticacionServicio;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.dto.Aplicacion;
import com.osmosyscol.datasuite.logica.dto.Plugin;
import com.osmosyscol.datasuite.persistencia.dao.PluginDao;
import com.osmosyscol.datasuite.servlet.ProxyService;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class PluginServicio {
	
	private static PluginServicio servicio = null;
	
	private PluginServicio(){
		
	}
	
	public static PluginServicio getInstance(){
		if (servicio == null){
			servicio = new PluginServicio();
		}
		return servicio;
	}
	
	public Plugin obtenerPlugin(String nombre) {
		try {
			
			DaoManager daoManager = DaoConfig.getDaoManager();
			PluginDao pluginDao = (PluginDao) daoManager.getDao(PluginDao.class);
			
			return pluginDao.obtenerPlugin(nombre) ;
			
		}catch (Exception e){
			SimpleLogger.setError("Error en PluginServicio.obtenerPlugin", e);
		}
		return null;
	}
	
	public List<Plugin> obtenerPlugins() {
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			PluginDao pluginDao = (PluginDao) daoManager.getDao(PluginDao.class);
			
			return pluginDao.obtenerPlugins();
			
		}catch (Exception e){
			SimpleLogger.setError("Error en PluginServicio.obtenerPlugins", e);
		}
		return null;
	}
	
	public List<Plugin> obtenerPluginsActivos() {
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			PluginDao pluginDao = (PluginDao) daoManager.getDao(PluginDao.class);
			
			return pluginDao.obtenerPluginsActivos();
			
		}catch (Exception e){
			SimpleLogger.setError("Error en PluginServicio.obtenerPluginsActivos", e);
		}
		return null;
	}

	public List<Plugin> obtenerPluginsPorAplicacion(String id_aplicacion) {
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			PluginDao pluginDao = (PluginDao) daoManager.getDao(PluginDao.class);
			
			return pluginDao.obtenerPluginsPorAplicacion(id_aplicacion);
			
		}catch (Exception e){
			SimpleLogger.setError("Error en PluginServicio.obtenerPluginsPorAplicacion", e);
		}
		return null;
	}
	
	public List<Plugin> obtenerPluginsAutorizados(Session session){
		try {

			List<Plugin> pluginsAuth = new ArrayList<Plugin>();
			
			List<Plugin> todosPlugin = obtenerPluginsActivos();
			
			Set<String> carpetasPlugins = new HashSet<String>();
			
			for (Plugin plugin : todosPlugin){
				String url = plugin.getAccion();
				Aplicacion aplicacion = plugin.getAplicacion();
				String sessionId = ProxyService.getSessionAplicacion(session, aplicacion);
				if(AutenticacionServicio.getInstance().autorizarUrl(session, url) && StringUtils.esNoVacio(sessionId)){
					pluginsAuth.add(plugin);
					carpetasPlugins.add(aplicacion.getCarpeta_plugins());
				}
			}
			
			session.setAttribute("carpetas_plugins", carpetasPlugins);
			
			return pluginsAuth;
			
		}catch (Exception e){
			SimpleLogger.setError("Error en PluginServicio.obtenerPluginsAutorizados", e);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Set<String> obtenerCarpetasPlugins(Session session){
		return (Set<String>) session.getAttribute("carpetas_plugins");
	}

}
