/**
 * 
 */
package com.osmosyscol.osmoautenticador.autenticador;

import java.util.HashMap;

import com.osmosyscol.osmoautenticador.constantes.ConstantesGenerales;
import com.osmosyscol.osmoautenticador.servicio.AuthenticatorLDAP;

/**
 * @author ojcchar
 * 
 */
public class AutenticadorLDAP implements Autenticador {

	public RespuestaAutenticador autenticar(EntradaAutenticador solicitud) {
		HashMap<String, Object> datos = solicitud.getDatos();

		String config = (String) datos.get(ConstantesGenerales.CONF);
		String login = (String) datos.get(ConstantesGenerales.LOGIN);
		String clave = (String) datos.get(ConstantesGenerales.CLAVE);

		RespuestaAutenticador respuesta = new RespuestaAutenticador();
		respuesta.setAutenticado(AuthenticatorLDAP.getInstance().authenticate(login, clave, config));
		return respuesta;
	}

	public RespuestaAutenticador autorizar(EntradaAutenticador solicitud) {
		throw new UnsupportedOperationException("La autorizacion por LDAP no es soportada");
	}

	public int cargarConfiguracion(String rutaConfiguracion) {
		return 0;
	}

	public Boolean cerrarSesion(EntradaAutenticador solicitud) {
		return true;
	}

	public Boolean perderSesion(EntradaAutenticador solicitud) {
		return true;
	}

}
