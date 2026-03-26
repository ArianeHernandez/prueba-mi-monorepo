package com.osmosyscol.datasuite.webdata.persistencia.dao.ibatis;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.logica.dto.HistorialSobreflex;
import com.osmosyscol.datasuite.webdata.persistencia.dao.HistorialSobreflexDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class HistorialSobreflexDaoImp extends BaseSqlMapDao implements HistorialSobreflexDao {

	public HistorialSobreflexDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	public Integer obtenerSiguiente() {
		return (Integer) queryForObject("HistorialSobreflex.obtenerSiguiente");
	}

	public Boolean guardarHistorialSobreflex(HistorialSobreflex historialSobreflex) {
		insert("HistorialSobreflex.guardarHistorialSobreflex", historialSobreflex);
		return true;
	}

	public HistorialSobreflex obtenerHistorialSobreflex(String hash) {
		return (HistorialSobreflex) queryForObject("HistorialSobreflex.obtenerHistorialSobreflex", hash);
	}

	public Boolean desactivarHistorialSobreflex(String hash) {
		update("HistorialSobreflex.desactivarHistorialSobreflex", hash);
		return true;
	}

}
