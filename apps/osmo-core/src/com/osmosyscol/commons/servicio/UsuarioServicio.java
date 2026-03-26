// CVS $Id$

package com.osmosyscol.commons.servicio;

import java.net.URL;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.osmoautenticador.servicioweb.UrlServicio.TipoElementoEntradaobtenerUrlServicios;
import com.osmosyscol.osmoautenticador.servicioweb.UrlServicio.TipoElementoSalidaobtenerUrlServicios;
import com.osmosyscol.osmoautenticador.servicioweb.UrlServicio.TipoEntradaobtenerUrlServicios;
import com.osmosyscol.osmoautenticador.servicioweb.UrlServicio.UrlServicioSOAPStub;
import com.osmosyscol.osmoautenticador.servicioweb.cliente.guardarusuario.RespuestaGuardarUsuario;
import com.osmosyscol.osmoautenticador.servicioweb.cliente.guardarusuario.SolicitudGuardarUsuario;
import com.osmosyscol.osmoautenticador.servicioweb.cliente.guardarusuario.WSGuardarUsuarioSoapBindingStub;
import com.osmosyscol.osmoautenticador.servicioweb.eliminarusuario.RespuestaEliminarUsuario;
import com.osmosyscol.osmoautenticador.servicioweb.eliminarusuario.WSEliminarUsuarioSoapBindingStub;

public class UsuarioServicio {

	// -----------------------------------------------------------------

	private static final String SERVICES_WS_GUARDAR_USUARIO = "/services2/WSGuardarUsuario";
	private static final String SERVICES_WS_ELIMINAR_USUARIO = "/services2/WSEliminarUsuario";
	private static final String SERVICES_WS_URL_SERVICIO = "/ws2/UrlServicio";
	private static UsuarioServicio usuarioServicio;

	public static UsuarioServicio getInstance() {
		if (usuarioServicio == null) {
			usuarioServicio = new UsuarioServicio();
		}
		return usuarioServicio;
	}
	
	public TipoElementoSalidaobtenerUrlServicios[] obtenerUrlsServicio(String login, String aplicacion){
		
			try {
				URL url = new URL(AutenticacionServicio.getEndpoint() + SERVICES_WS_URL_SERVICIO);
				
				UrlServicioSOAPStub stub=new UrlServicioSOAPStub(url, null);
				
				TipoEntradaobtenerUrlServicios entrada=new TipoEntradaobtenerUrlServicios();
				TipoElementoEntradaobtenerUrlServicios elementoEntrada=new TipoElementoEntradaobtenerUrlServicios();
				
				elementoEntrada.setAplicacion(aplicacion);
				elementoEntrada.setLogin(login);
				entrada.setElementoEntrada(elementoEntrada);
				
				TipoElementoSalidaobtenerUrlServicios[] urls = stub.obtenerUrlServicios(entrada);
				
				return urls;
				
			} catch (Exception e) {
				SimpleLogger.setError("Url Servicio. ["+AutenticacionServicio.getEndpoint() + SERVICES_WS_URL_SERVICIO+"]. Ha ocurrido un error al obtener las urls del usuario ", e);
			}
			
			return null;
	}

	// -----------------------------------------------------------------
	// -----------------------------------------------------------------

	public Boolean guardarUsuario(String login, String clave, int[] roles) {

		URL url;
		try {
			url = new URL(AutenticacionServicio.getEndpoint() + SERVICES_WS_GUARDAR_USUARIO);

			WSGuardarUsuarioSoapBindingStub stub = new WSGuardarUsuarioSoapBindingStub(url, null);

			SolicitudGuardarUsuario solicitudGuardarUsuario = new SolicitudGuardarUsuario();

			solicitudGuardarUsuario.setLogin(login.trim().toLowerCase());
			solicitudGuardarUsuario.setClave(clave);
			solicitudGuardarUsuario.setRoles(roles);

			RespuestaGuardarUsuario resp = stub.guardarUsuario(solicitudGuardarUsuario);
			SimpleLogger.setInfo(resp.getMensaje());
			return resp.getExitoso();

		} catch (Exception e) {
			SimpleLogger.setError(">> Ha ocurrido al intentar guardar el usuario. ", e);
		}

		return false;
	}

	public Boolean eliminarUsuario(String login) {

		URL url;
		try {
			url = new URL(AutenticacionServicio.getEndpoint() + SERVICES_WS_ELIMINAR_USUARIO);

			WSEliminarUsuarioSoapBindingStub stub = new WSEliminarUsuarioSoapBindingStub(url, null);

			RespuestaEliminarUsuario resp = stub.eliminarUsuario(login);
			SimpleLogger.setInfo(resp.getMensaje());
			return resp.getExitoso();

		} catch (Exception e) {
			SimpleLogger.setError(">> Ha ocurrido al intentar guardar el usuario. ", e);
		}

		return false;
	}
	// -----------------------------------------------------------------
}
