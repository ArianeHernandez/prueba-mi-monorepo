package com.osmosyscol.datasuite.webdata.persistencia.dao;

import com.osmosyscol.datasuite.logica.dto.HistorialSobreflex;

public interface HistorialSobreflexDao {

	public Integer obtenerSiguiente();
	
	public Boolean guardarHistorialSobreflex(HistorialSobreflex historialSobreflex);

	public HistorialSobreflex obtenerHistorialSobreflex(String hash);

	public Boolean desactivarHistorialSobreflex(String hash);
}
