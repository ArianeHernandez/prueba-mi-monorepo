package com.osmosyscol.datasuite.webdata.persistencia.dao.ibatis;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.webdata.logica.dto.Estado;
import com.osmosyscol.datasuite.webdata.persistencia.dao.EstadoDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class EstadoDaoImp extends BaseSqlMapDao implements EstadoDao{
	
	public EstadoDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	@SuppressWarnings("unchecked")
	public List<Estado> obtenerEstados() {
		return queryForList("Estado.obtenerEstados");
	}
	
	public Estado obtenerEstado(String estado){
		return (Estado) queryForObject("Estado.obtenerEstado", estado);
	}

}
