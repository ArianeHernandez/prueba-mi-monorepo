package com.osmosyscol.datasuite.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.logica.dto.ListaDinamicaCampo;

public interface ListaDinamicaCampoDao {

	public List<ListaDinamicaCampo> obtenerListasPorFormato(Integer id_formato);

	public Boolean guardarListasDinamicaCampo(List<ListaDinamicaCampo> listaDC, Integer id_formato);

}
