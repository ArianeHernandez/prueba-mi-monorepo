package com.osmosyscol.datasuite.modelatos.persistencia.dao.ibatis;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.modelatos.logica.dto.TipoEstilo;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.TipoEstiloDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class TipoEstiloDaoImp extends BaseSqlMapDao implements TipoEstiloDao {

	public TipoEstiloDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	// --------------------------------------------------------

	@SuppressWarnings("unchecked")
	public List<TipoEstilo> obtenerTipoEstilos() {
		return queryForList("TipoEstilo.obtenerTipoEstilos", null);
	}

	public TipoEstilo obtenerTipoEstilo(Integer id_tipo_estilo) {
		return (TipoEstilo) queryForObject("TipoEstilo.obtenerTipoEstiloPorId", id_tipo_estilo);
	}

}
