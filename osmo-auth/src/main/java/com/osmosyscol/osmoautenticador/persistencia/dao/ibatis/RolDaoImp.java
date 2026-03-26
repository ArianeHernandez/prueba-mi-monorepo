package com.osmosyscol.osmoautenticador.persistencia.dao.ibatis;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.osmoautenticador.persistencia.dao.RolDao;
import com.osmosyscol.osmoautenticador.persistencia.dto.RolDto;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class RolDaoImp extends BaseSqlMapDao implements RolDao {

	private Integer idRol;
	public RolDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	public Boolean insertarRol(RolDto rol) {
		this.idRol = nextValue("oas_rol");
		rol.setIdRol(this.idRol);
		insert("Rol.insertarRol", rol);
		return true;
	}

	public Integer getIdRol() {
		return idRol;
	}

	public Boolean eliminarRol(Integer idRol) {
		insert("Rol.eliminarRol", idRol);
		return true;
	}

}
