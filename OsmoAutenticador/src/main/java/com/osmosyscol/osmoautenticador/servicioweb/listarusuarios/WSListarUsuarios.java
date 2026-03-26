// CVS $Id$

package com.osmosyscol.osmoautenticador.servicioweb.listarusuarios;

import java.util.List;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.osmoautenticador.dominio.Usuario;
import com.osmosyscol.osmoautenticador.servicio.UsuarioServicio;

public class WSListarUsuarios {

	// ---------------------------------------------

	public Usuario[] listarUsuarios() {

		try {

			List<Usuario> listarUsuarios = UsuarioServicio.getInstance().listarUsuarios();
			Usuario []usuarios = new Usuario [listarUsuarios.size()];
			
			return listarUsuarios.toArray(usuarios);

		} catch (Exception e) {
			SimpleLogger.setDebug("Error listando usuarios");
		}

		return null;
	}

	// ---------------------------------------------

}
