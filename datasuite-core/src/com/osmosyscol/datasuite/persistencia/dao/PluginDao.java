package com.osmosyscol.datasuite.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.logica.dto.Plugin;

public interface PluginDao {
	
	public Plugin obtenerPlugin(String nombre);
	
	public List<Plugin> obtenerPlugins();
	
	public List<Plugin> obtenerPluginsActivos();
	
	public List<Plugin> obtenerPluginsPorAplicacion(String id_aplicacion);

}
