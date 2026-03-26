package com.osmosyscol.datasuite.mein.persistencia.dao.ibatis;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.mein.dtos.ProcesoPasante;
import com.osmosyscol.datasuite.mein.persistencia.dao.ProcesoPasanteDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class ProcesoPasanteDaoImp extends BaseSqlMapDao implements ProcesoPasanteDao {

	public ProcesoPasanteDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	@Override
	public ProcesoPasante obtenerProcesoPasante(Integer id_formato) {
		return (ProcesoPasante) queryForObject("ProcesoPasante.obtenerProcesoPasante", id_formato);
	}

}
