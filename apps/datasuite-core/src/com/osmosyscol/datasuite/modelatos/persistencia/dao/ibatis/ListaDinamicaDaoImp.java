package com.osmosyscol.datasuite.modelatos.persistencia.dao.ibatis;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.modelatos.logica.dto.ListaDinamica;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.ListaDinamicaDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class ListaDinamicaDaoImp extends BaseSqlMapDao implements ListaDinamicaDao {

	// --------------------------------------------------------

	public ListaDinamicaDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	// --------------------------------------------------------

	@SuppressWarnings("unchecked")
	public List<ListaDinamica> obtenerListasDinamicas() {
		return queryForList("ListaDinamica.obtenerListasDinamicas", null);
	}

	public ListaDinamica obtenerListaDinamica(Integer id_lista_dinamica) {
		return (ListaDinamica) queryForObject("ListaDinamica.obtenerListaDinamica", id_lista_dinamica);
	}

	// --------------------------------------------------------

	public void guardarListaDinamica(ListaDinamica listaDinamica) {

		if (listaDinamica.getId_lista_dinamica() == null) {
			listaDinamica.setId_lista_dinamica((Integer) queryForObject("ListaDinamica.obtenerSiguienteID", null));

			insert("ListaDinamica.creaListaDinamica", listaDinamica);
		} else {
			update("ListaDinamica.actualizarListaDinamica", listaDinamica);
		}
	}

	// --------------------------------------------------------
}
