package com.osmosyscol.datasuite.webdata.persistencia.dao.ibatis;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.webdata.logica.dto.TipoCarga;
import com.osmosyscol.datasuite.webdata.persistencia.dao.TipoCargaDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class TipoCargaDaoImp extends BaseSqlMapDao implements TipoCargaDao{

	public TipoCargaDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	public List<TipoCarga> obtenerTiposCarga() {
		
		return queryForList("TipoCarga.obtenerTiposCarga");
	}
	
	
}

