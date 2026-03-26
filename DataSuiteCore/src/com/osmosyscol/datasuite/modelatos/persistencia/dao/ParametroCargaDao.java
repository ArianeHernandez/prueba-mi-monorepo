package com.osmosyscol.datasuite.modelatos.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.modelatos.logica.dto.ParametroCarga;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValorParametroCarga;

public interface ParametroCargaDao {

	public List<ParametroCarga> obtenerParametrosCarga(Integer id_formato);

	public Boolean guardarValoresParametrosCarga(List<ValorParametroCarga> list);

	public Boolean guardarParametroCarga(ParametroCarga parametroCarga);

	public List<ParametroCarga> obtenerParametrosCargaPorFormatoSalida(Integer id_formato);

	public List<ValorParametroCarga> obtenerValoresParametrosCarga(Integer id_carga);

}
