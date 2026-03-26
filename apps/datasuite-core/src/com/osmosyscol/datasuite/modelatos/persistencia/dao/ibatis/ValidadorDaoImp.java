package com.osmosyscol.datasuite.modelatos.persistencia.dao.ibatis;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.ValidadorDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class ValidadorDaoImp extends BaseSqlMapDao implements ValidadorDao {

	public ValidadorDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	public void validar_creadatos() {
		update("Validador.validar_creadatos", null);
	}

	public void validar_datasuite() {
		update("Validador.validar_datasuite", null);
	}

		
}
