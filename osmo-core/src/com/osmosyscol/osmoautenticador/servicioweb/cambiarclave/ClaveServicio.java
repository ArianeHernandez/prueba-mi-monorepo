package com.osmosyscol.osmoautenticador.servicioweb.cambiarclave;

import java.net.URL;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.osmoautenticador.servicioweb.cliente.cambiarclave.RespuestaCambiarClave;
import com.osmosyscol.osmoautenticador.servicioweb.cliente.cambiarclave.SolicitudCambiarClave;
import com.osmosyscol.osmoautenticador.servicioweb.cliente.cambiarclave.WSCambiarClaveSoapBindingStub;

import co.htsoft.commons.rsa.SecureJsonUtils;

public class ClaveServicio {

	private static ClaveServicio claveServicio = new ClaveServicio();

	private ClaveServicio() {

	}

	public static ClaveServicio getInstance() {
		return claveServicio;
	}

	public RespuestaCambiarClave cambiarClave(String aplicacion, String clave, String nuevaClave, String login) {
		try {

			try {
				clave = SecureJsonUtils.ds(clave);
			} catch (Exception e) {
			}

			try {
				nuevaClave = SecureJsonUtils.ds(nuevaClave);
			} catch (Exception e) {
			}

			URL url = new URL(com.osmosyscol.commons.servicio.AutenticacionServicio.getEndpoint() + "/services2/WSCambiarClave");

			WSCambiarClaveSoapBindingStub a = new WSCambiarClaveSoapBindingStub(url, null);
			SolicitudCambiarClave solicitudCambiarClave = new SolicitudCambiarClave(aplicacion, clave, nuevaClave, login, "1");
			RespuestaCambiarClave r = a.cambiarclave(solicitudCambiarClave);

			return r;

		} catch (Throwable e) {

			SimpleLogger.setError("No se puede inicializar el servicio para listas de cautela.", e);
			return null;
		}

	}

}
