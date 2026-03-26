package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.logica.dto.TipoDocumento;
import com.osmosyscol.datasuite.persistencia.dao.TipoDocumentoDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class TipoDocumentoDaoImp extends BaseSqlMapDao implements TipoDocumentoDao {

	public TipoDocumentoDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	public TipoDocumento buscarTipoDocumentoG3A(String tipoDocumentoG3A) {
		return (TipoDocumento) queryForObject("TipoDocumento.buscarTipoDocumentoG3A", tipoDocumentoG3A);
	}

	@Override
	public String getNombreTipoDocumento(Integer id) {
		// TODO Auto-generated method stub
		return (String) queryForObject("TipoDocumento.getNombreDocumento");
	}

	@SuppressWarnings("unchecked")
	public List<TipoDocumento> getListaTipoDocumento() {
		return (List<TipoDocumento>) queryForList("TipoDocumento.getListaTipoDocumento");
	}

	@Override
	public TipoDocumento getTipoDocumentoCodigo(String codigo) {
		return (TipoDocumento) queryForObject("TipoDocumento.buscarTipoDocumentoCodigo", codigo);
	}
}