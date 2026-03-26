package com.osmosyscol.datasuite.modelatos.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.modelatos.logica.dto.TipoEstilo;

public interface TipoEstiloDao {

	public List<TipoEstilo> obtenerTipoEstilos();

	public TipoEstilo obtenerTipoEstilo(Integer id_tipo_estilo);
	
}
