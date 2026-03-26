package com.osmosyscol.datasuite.modelatos.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.modelatos.logica.dto.ListaDinamica;

public interface ListaDinamicaDao {

	public List<ListaDinamica> obtenerListasDinamicas();

	public ListaDinamica obtenerListaDinamica(Integer id_lista_dinamica);

	public void guardarListaDinamica(ListaDinamica listaDinamica);

}
