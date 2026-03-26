/**
 * 
 */
package com.osmosyscol.osmoautenticador.autenticador;

import java.util.HashMap;

import com.osmosyscol.osmoautenticador.constantes.ConstantesGenerales;
import com.osmosyscol.osmoautenticador.servicio.UsuarioServicio;

/**
 * @author ojcchar
 * 
 */
public class AutenticadorDB implements Autenticador {

	public RespuestaAutenticador autenticar(EntradaAutenticador solicitud) {

		HashMap<String, Object> datos = solicitud.getDatos();

		String login = (String) datos.get(ConstantesGenerales.LOGIN);
		String clave = (String) datos.get(ConstantesGenerales.CLAVE);
		String tipoClave = (String) datos.get(ConstantesGenerales.TIPO_CLAVE);
		String aplicacion = (String) datos.get(ConstantesGenerales.APLICACION);

		RespuestaAutenticador respuesta = new RespuestaAutenticador();
		respuesta.setAutenticado(UsuarioServicio.getInstance().autenticar(login, clave, tipoClave, aplicacion));
		return respuesta;
	}

	public RespuestaAutenticador autorizar(EntradaAutenticador solicitud) {
		HashMap<String, Object> datos = solicitud.getDatos();

		String login = (String) datos.get(ConstantesGenerales.LOGIN);
		String aplicacion = (String) datos.get(ConstantesGenerales.APLICACION);
		String url= (String) datos.get(ConstantesGenerales.URL);
		
		RespuestaAutenticador respuesta = new RespuestaAutenticador();
		respuesta.setAutenticado(UsuarioServicio.getInstance().autorizar(login, url,
				aplicacion));
		return respuesta;
	}

	public int cargarConfiguracion(String rutaConfiguracion) {
		return 0;
	}

	public Boolean cerrarSesion(EntradaAutenticador solicitud) {
		return false;
	}

	public Boolean perderSesion(EntradaAutenticador solicitud) {
		return false;
	}

}
