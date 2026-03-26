// CVS $Id$

package com.osmosyscol.osmoautenticador.servicioweb.guardarusuario;

import com.osmosyscol.osmoautenticador.dominio.Mensaje;
import com.osmosyscol.osmoautenticador.servicio.UsuarioServicio;

public class WSGuardarUsuario {

	// ---------------------------------------------

	public RespuestaGuardarUsuario guardarUsuario(SolicitudGuardarUsuario solicitudGuardarUsuario) {

		RespuestaGuardarUsuario resGuardarUsuario = new RespuestaGuardarUsuario();

		try {

			Mensaje mensaje = UsuarioServicio.getInstance().guardarUsuario(solicitudGuardarUsuario.getLogin(), solicitudGuardarUsuario.getClave(), solicitudGuardarUsuario.getRoles());
			resGuardarUsuario.setExitoso(mensaje.getExitoso());
			resGuardarUsuario.setMensaje(mensaje.getMensaje());

		} catch (Exception e) {
			resGuardarUsuario.setExitoso(false);
			resGuardarUsuario.setMensaje("Ha ocurrido un error en el servidor. Por favor consulte al administrador.");
		}

		return resGuardarUsuario;
	}

	// ---------------------------------------------

}
