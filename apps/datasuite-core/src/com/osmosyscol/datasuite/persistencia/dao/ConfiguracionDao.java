package com.osmosyscol.datasuite.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.logica.dto.RegistroConfiguracion;

public interface ConfiguracionDao {

	public RegistroConfiguracion obtenerRegistroConfiguracionByEtiqueta(String etiqueta);
	
	public List<RegistroConfiguracion> obtenerRegistrosConfiguracionByServicio(String servicio);
	
	public List<RegistroConfiguracion> obtenerTodosRegistrosConfiguracion();
	
	
}	
