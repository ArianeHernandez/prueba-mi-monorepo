package com.osmosyscol.osmoautenticador.persistencia.dao;

import com.osmosyscol.osmoautenticador.persistencia.dto.RolDto;

public interface RolDao {
	public Boolean insertarRol(RolDto rol);
	public Boolean eliminarRol(Integer idRol);
	public Integer getIdRol();
}
