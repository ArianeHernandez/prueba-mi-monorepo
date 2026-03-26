package com.osmosyscol.datasuite.webdata.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.webdata.logica.dto.Estado;

public interface EstadoDao {

	List<Estado> obtenerEstados();
	
	Estado obtenerEstado(String estado);

}
