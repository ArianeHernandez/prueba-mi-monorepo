package com.osmosyscol.osmoautenticador.persistencia.dao.ibatis;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.osmoautenticador.persistencia.dao.RolExDao;
import com.osmosyscol.osmoautenticador.persistencia.dto.RolExDto;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class RolExDaoImp extends BaseSqlMapDao implements RolExDao {

	public RolExDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	public List<RolExDto> obtenerRolesEx(Integer idRol) {
		RolExDto rol = new RolExDto();
		rol.setIdRol(idRol);
		return queryForList("Rol.obtenerRolesEx", rol);
	}

}
