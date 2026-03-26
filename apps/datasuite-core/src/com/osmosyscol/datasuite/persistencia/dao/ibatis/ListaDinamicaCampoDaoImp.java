package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.logica.dto.ListaDinamicaCampo;
import com.osmosyscol.datasuite.persistencia.dao.ListaDinamicaCampoDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class ListaDinamicaCampoDaoImp extends BaseSqlMapDao implements ListaDinamicaCampoDao {

	public ListaDinamicaCampoDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	@SuppressWarnings("unchecked")
	public List<ListaDinamicaCampo> obtenerListasPorFormato(Integer id_formato) {

		return queryForList("ListaDinamicaCampo.obtenerListasPorFormato", id_formato);

	}

	public Boolean guardarListasDinamicaCampo(List<ListaDinamicaCampo> listaDC, Integer id_formato) {

		eliminarListasDinamicaCampo(id_formato);

		if(listaDC != null){
			for (ListaDinamicaCampo listaDinamicaCampo : listaDC) {
				if (listaDinamicaCampo.getId_campo() != null && listaDinamicaCampo.getId_lista_dinamica() != null) {
					listaDinamicaCampo.setId_formato(id_formato);
					insert("ListaDinamicaCampo.guardarListasDinamicaCampo", listaDinamicaCampo);
				}
			}
		}
		

		return true;
	}

	public Boolean eliminarListasDinamicaCampo(Integer id_formato) {
		delete("ListaDinamicaCampo.eliminarListasDinamicaCampo", id_formato);
		return true;
	}
	// -------------------

}
