package com.osmosyscol.datasuite.persistencia.dao;

import java.util.List;
import java.util.Map;

public interface DatosEstructuraDao {

	public List<Map<String, Object>> obtenerDatosEstructura(String sql);

	public void deleteSQL(String sql);

	public Integer obtenerSiguiente();

	public void insertSQL(String sql);

	public Integer updateSQL(String sql);
	
	
}

