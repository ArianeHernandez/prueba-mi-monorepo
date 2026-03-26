package com.osmosyscol.osmoautenticador.persistencia.dao;

import java.util.List;

import com.osmosyscol.osmoautenticador.persistencia.dto.RolExDto;

public interface RolExDao {
	
	public List<RolExDto> obtenerRolesEx(Integer idRol);

}
