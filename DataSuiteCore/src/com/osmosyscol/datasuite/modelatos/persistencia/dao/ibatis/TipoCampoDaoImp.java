package com.osmosyscol.datasuite.modelatos.persistencia.dao.ibatis;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.modelatos.logica.dto.TipoCampo;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.TipoCampoDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class TipoCampoDaoImp extends BaseSqlMapDao implements TipoCampoDao {

	public TipoCampoDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	// --------------------------------------------------------

	@SuppressWarnings("unchecked")
	public List<TipoCampo> obtenerTipoCampos() {
		return queryForList("TipoCampo.obtenerTipoCampos", null);
	}

	public TipoCampo obtenerTipoCampo(Integer id_tipocampo) {
		return (TipoCampo) queryForObject("TipoCampo.obtenerTipoCampoPorId", id_tipocampo);
	}

}
