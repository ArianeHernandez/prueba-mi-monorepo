package com.osmosyscol.datasuite.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.logica.dto.FormatoConfiguracion;

public interface FormatoConfiguracionDao {
	
	public List<FormatoConfiguracion> obtenerConfiguracionesFormato(Integer id_formato);
	
	public FormatoConfiguracion obtenerConfiguracionFormato(Integer id_formato, String tipo);

}
