package com.osmosyscol.osmoautenticador.servicioweb.roles;

import java.util.List;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.osmoautenticador.dominio.RolUs;
import com.osmosyscol.osmoautenticador.servicio.AdministracionServicio;

public class WSRoles {

	public RolUs[] obtenerRolesUsuario(String loginUsuario) {
		try {
			
			List<RolUs> listaRoles = AdministracionServicio.getInstance().obtenerRolesUsuario(loginUsuario);
			RolUs[] roles = new RolUs[listaRoles.size()];
			
			return listaRoles.toArray(roles);
			
		} catch (Exception e) {
			SimpleLogger.setDebug("Error obteniendo los roles del usuario");
		}

		return null;
	}

}
