package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.logica.dto.Aplicacion;
import com.osmosyscol.datasuite.persistencia.dao.AplicacionDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class AplicacionDaoImp extends BaseSqlMapDao implements AplicacionDao {

	public AplicacionDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	public Aplicacion obtenerAplicacion(String id_aplicacion) {
		return (Aplicacion)queryForObject("Aplicacion.obtenerAplicacion", id_aplicacion);
	}

	
	
}
