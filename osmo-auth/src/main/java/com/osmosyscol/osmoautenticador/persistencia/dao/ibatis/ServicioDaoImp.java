package com.osmosyscol.osmoautenticador.persistencia.dao.ibatis;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.osmoautenticador.persistencia.dao.ServicioDao;
import com.osmosyscol.osmoautenticador.persistencia.dto.ServicioDto;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class ServicioDaoImp extends BaseSqlMapDao implements ServicioDao {

	public ServicioDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	public Boolean eliminarServicioporRol(Integer idRol) {
		delete("Servicio.eliminarServicioporRol", idRol);
		return true;
	}

	public Boolean insertarServicioporRol(List<ServicioDto> servicio) {
		if (servicio != null) {
			for (ServicioDto servicioDto : servicio) {
				insert("Servicio.insertarServicioporRol", servicioDto);
			}
		}
		return true;
	}

	public ServicioDto obtenerServicio(Integer idServicio) {
		ServicioDto servicio = new ServicioDto();
		servicio.setIdServicio(idServicio);
		return (ServicioDto) queryForObject("Servicio.obtenerServicio", servicio);
	}
}
