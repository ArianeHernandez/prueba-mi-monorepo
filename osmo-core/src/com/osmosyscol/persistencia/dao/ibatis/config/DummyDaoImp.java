package com.osmosyscol.persistencia.dao.ibatis.config;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class DummyDaoImp extends BaseSqlMapDao implements DummyDao {

	public DummyDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	// --------------------------------------------------------

	
}
