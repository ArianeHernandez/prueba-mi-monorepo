package com.osmosyscol.osmoautenticador.persistencia.dao;

import java.util.List;

import com.osmosyscol.osmoautenticador.persistencia.dto.ServicioExDto;

public interface ServicioExDao {
	public List<ServicioExDto> obtenerServiciosEx(Integer idServicio, String naturaleza);
}
