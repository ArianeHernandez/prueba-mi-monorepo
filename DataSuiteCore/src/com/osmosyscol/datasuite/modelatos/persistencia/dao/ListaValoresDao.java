package com.osmosyscol.datasuite.modelatos.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.modelatos.logica.dto.ListaValores;
import com.osmosyscol.datasuite.modelatos.logica.dto.TipoCampo;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValorLista;

public interface ListaValoresDao {

	public List<ListaValores> obtenerListaValoresPorPersona(Integer id_modelo, Integer numeroPagina);

	public Integer totalListaValoresPorPersona(Integer id_modelo);

	public ListaValores obtenerListaValores(Integer id_listavalores);

	public ListaValores guardarListaValores(ListaValores listaValores, Integer id_persona, Integer id_modelo);

	public Boolean eliminarListaValores(Integer id_listavalores);

	public List<ValorLista> obtenerValoresLV(ListaValores listavalores);

	public Boolean actualizarValoresLV(ListaValores listaValores, TipoCampo tipoCampo, List<ValorLista> valoresLista);

	public ListaValores obtenerListaValoresPorNombre(String nombre);

	public List<ListaValores> obtenerListasDeValores(Integer idModelo);

	public List<ValorLista> obtenerValoresBD(String sql);

}
