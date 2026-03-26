// CVS $Id$

package com.osmosyscol.osmoautenticador.servicioweb.eliminarusuario;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.osmoautenticador.dominio.Mensaje;
import com.osmosyscol.osmoautenticador.servicio.UsuarioServicio;

public class WSEliminarUsuario {

	// ---------------------------------------------

	public RespuestaEliminarUsuario eliminarUsuario(String login) {

		RespuestaEliminarUsuario resEliminarUsuario = new RespuestaEliminarUsuario();

		try {

			Mensaje mensaje = UsuarioServicio.getInstance().eliminarUsuario(login);
			resEliminarUsuario.setExitoso(mensaje.getExitoso());
			resEliminarUsuario.setMensaje(mensaje.getMensaje());

		} catch (Exception e) {
			resEliminarUsuario.setExitoso(false);
			resEliminarUsuario.setMensaje("Ha ocurrido un error en el servidor. Por favor consulte al administrador.");
			SimpleLogger.setError("Error", e);
		}

		return resEliminarUsuario;
	}

	// ---------------------------------------------

}
