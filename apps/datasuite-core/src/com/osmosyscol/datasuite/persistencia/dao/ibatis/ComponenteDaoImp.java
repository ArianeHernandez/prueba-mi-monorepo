package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.logica.dto.Componente;
import com.osmosyscol.datasuite.persistencia.dao.ComponenteDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class ComponenteDaoImp extends BaseSqlMapDao implements ComponenteDao {

	public ComponenteDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	// ---------------------

	@SuppressWarnings("unchecked")
	public List<Componente> obteneComponentes() {
		return queryForList("Componente.obtenerComponentes", null);
	}

	// ---------------------

}
