package com.osmosyscol.osmoautenticador.persistencia.dao.ibatis;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.osmoautenticador.persistencia.dao.ServicioExDao;
import com.osmosyscol.osmoautenticador.persistencia.dto.ServicioExDto;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class ServicioExDaoImp extends BaseSqlMapDao implements ServicioExDao {

	public ServicioExDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	public List<ServicioExDto> obtenerServiciosEx(Integer idServicio, String naturaleza) {
		ServicioExDto servicio = new ServicioExDto();
		servicio.setId_servicio(idServicio);
		servicio.setNaturaleza(naturaleza);
		return queryForList("Servicio.obtenerServiciosEx", servicio);
	}

}
