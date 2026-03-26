package com.osmosyscol.datasuite.modelatos.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.modelatos.logica.dto.TipoCampo;

public interface TipoCampoDao {

	public List<TipoCampo> obtenerTipoCampos();

	public TipoCampo obtenerTipoCampo(Integer id_tipocampo);
	
}
