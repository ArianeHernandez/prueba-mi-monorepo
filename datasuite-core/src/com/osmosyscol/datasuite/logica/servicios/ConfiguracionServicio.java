package com.osmosyscol.datasuite.logica.servicios;

import java.util.ArrayList;
import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.RegistroConfiguracion;
import com.osmosyscol.datasuite.persistencia.dao.ConfiguracionDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class ConfiguracionServicio {

	private static ConfiguracionServicio configuracionServicio;
	private List<RegistroConfiguracion> registrosConfiguracion;

	private ConfiguracionServicio() {

		// inicializamos el gestor de contenidos
		registrosConfiguracion = new ArrayList<RegistroConfiguracion>();

	}

	public static ConfiguracionServicio getInstance() {
		if (configuracionServicio == null) {
			configuracionServicio = new ConfiguracionServicio();
		}
		return configuracionServicio;
	}

	public String obtenerValorByEtiqueta(String etiqueta) {

		List<RegistroConfiguracion> todos = obtenerTodosRegistroConfiguracion();

		for (RegistroConfiguracion registroConfiguracion : todos) {
			
			if(registroConfiguracion.getEtiqueta().equalsIgnoreCase(etiqueta)){
				return registroConfiguracion.getValor();
			}
		}
		
		return null;
	}
	
	public String obtenerValorById(Integer id_configuracion) {

		List<RegistroConfiguracion> todos = obtenerTodosRegistroConfiguracion();

		for (RegistroConfiguracion registroConfiguracion : todos) {
			
			if(registroConfiguracion.getId_configuracion() == id_configuracion.intValue()){
				return registroConfiguracion.getValor();
			}
		}
		
		return null;
	}

	/*
	 * Consulta TODO los registros de configuracion
	 */

	private List<RegistroConfiguracion> obtenerTodosRegistroConfiguracionBD() {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			ConfiguracionDao configuracionDao = (ConfiguracionDao) daoManager.getDao(ConfiguracionDao.class);

			return configuracionDao.obtenerTodosRegistrosConfiguracion();
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	/**
	 * 
	 * @return lista de registros de configuracion
	 */
	public List<RegistroConfiguracion> obtenerTodosRegistroConfiguracion() {

		if ( registrosConfiguracion == null ||  registrosConfiguracion.size() == 0) {
			registrosConfiguracion = obtenerTodosRegistroConfiguracionBD();

		}

		return registrosConfiguracion;
	}

}
