package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.logica.dto.Menu;
import com.osmosyscol.datasuite.logica.dto.SeccionMenu;
import com.osmosyscol.datasuite.persistencia.dao.MenuDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class MenuDaoImp extends BaseSqlMapDao implements MenuDao {

	public MenuDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	@SuppressWarnings("unchecked")
	public List<Menu> obtenerMenusPorAplicacion(String aplicacion) {
		return queryForList("Menu.obtenerMenusPorAplicacion", aplicacion);
	}

	@SuppressWarnings("unchecked")
	public List<SeccionMenu> obtenerSeccionesMenu() {
		return queryForList("Menu.obtenerSeccionesMenu");
	}	
	
}
