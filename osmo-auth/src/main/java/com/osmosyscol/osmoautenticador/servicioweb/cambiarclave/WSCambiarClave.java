// CVS $Id$

package com.osmosyscol.osmoautenticador.servicioweb.cambiarclave;

import com.osmosyscol.osmoautenticador.dominio.Mensaje;
import com.osmosyscol.osmoautenticador.servicio.UsuarioServicio;

public class WSCambiarClave {

	// ---------------------------------------------
	// ---------------------------------------------

	public RespuestaCambiarClave cambiarclave(SolicitudCambiarClave solicitudCambiarClave) {

		RespuestaCambiarClave respuesta = new RespuestaCambiarClave();

		try {

			Mensaje mensaje = UsuarioServicio.getInstance().cambiarClave(solicitudCambiarClave.getLogin(), solicitudCambiarClave.getClave(), solicitudCambiarClave.getTipoclave(), solicitudCambiarClave.getClave_nueva(), solicitudCambiarClave.getAplicacion());
			respuesta.setExitoso(mensaje.getExitoso());
			respuesta.setMensaje(mensaje.getMensaje());

		} catch (Exception e) {
			
			e.printStackTrace();
			
			respuesta.setExitoso(false);
			respuesta.setMensaje("Ha ocurrido un error en el servidor. Por favor consulte al administrador.");
		}

		return respuesta;
	}

	// ---------------------------------------------

}
