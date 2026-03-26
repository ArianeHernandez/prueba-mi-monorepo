package com.osmosyscol.osmoautenticador.persistencia.dao;

import java.util.List;

import com.osmosyscol.osmoautenticador.persistencia.dto.ServicioDto;

public interface ServicioDao {
	public Boolean insertarServicioporRol(List<ServicioDto> servicio);
	public Boolean eliminarServicioporRol(Integer idRol);
	public ServicioDto obtenerServicio(Integer idServicio);
}
