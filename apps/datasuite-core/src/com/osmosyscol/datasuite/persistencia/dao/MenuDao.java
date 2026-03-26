package com.osmosyscol.datasuite.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.logica.dto.Menu;
import com.osmosyscol.datasuite.logica.dto.SeccionMenu;


public interface MenuDao {
	
	public List<Menu> obtenerMenusPorAplicacion(String aplicacion);

	public List<SeccionMenu> obtenerSeccionesMenu();
	
}
