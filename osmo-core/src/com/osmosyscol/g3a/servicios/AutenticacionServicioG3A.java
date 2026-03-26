// CVS $Id$

package com.osmosyscol.g3a.servicios;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.cocoon.environment.Request;
import org.apache.cocoon.environment.Session;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.AutenticacionServicio;
import com.osmosyscol.commons.utils.CocoonUtils;
import com.osmosyscol.commons.utils.GestorVentanilla;
import com.osmosyscol.commons.utils.JavaToXML;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.commons.utils.ValidarTipoDato;
import com.osmosyscol.g3a.cliente.EntradaAutenticarPTX;
import com.osmosyscol.g3a.cliente.EntradaAutorizarPTX;
import com.osmosyscol.g3a.cliente.RespuestaAutenticarPTX;
import com.osmosyscol.g3a.cliente.RespuestaAutorizarPTX;
import com.osmosyscol.g3a.cliente.WSAutenticarPTXSoapBindingStub;
import com.osmosyscol.g3a.cliente.entidades.IDUsuario;
import com.osmosyscol.g3a.cliente.entidades.Operacion;
import com.osmosyscol.g3a.cliente.entidades.RespuestaOperacion;
import com.osmosyscol.g3a.cliente.entidades.SessionTicket;
import com.osmosyscol.g3a.cliente.entidades.SolicitudActivacion;
import com.osmosyscol.g3a.cliente.entidades.SolicitudTransaccion;
import com.osmosyscol.g3a.cliente.entidades.Usuario;
import com.osmosyscol.g3a.serviciosweb.wsaplicaciones.Aplicacion;
import com.osmosyscol.g3a.serviciosweb.wsaplicaciones.WSAplicacionesSoapBindingStub;
import com.osmosyscol.g3a.serviciosweb.wsparametros.WSParametrosSoapBindingStub;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class AutenticacionServicioG3A {

	/**
	 * Logger declaration
	 */
	private static final Logger logger = LoggerFactory.getLogger(AutenticacionServicioG3A.class);

	private static final String SERVICIO_WEB_OSMOAUTENTICADOR = "/services2/WSAutenticarPTX";
	private static final String GESTOR_VENTANILLA = "gestor_ventanilla";
	public static final String LOGIN = "login";
	public static final String ID_CLIENTE = "g3a.id_cliente";
	public static final String G3A_TOKENVALUE = "g3a.tokenvalue";
	public static final String G3A_SESSIONID = "g3a.sessionid";
	public static final String G3A_USUARIO = "g3a.usuario";
	public static final String G3A_FILIAL = "g3a.filial";
	public static final String REQUEST_REMOTE_ADDR = "REQUEST.REMOTE.ADDR";
	public static final String REQUEST_REMOTE_USER_AGENT = "REQUEST.REMOTE.USER_AGENT";
	public static final String SSID_PARAMETRO = "SSID";
	public static final String TVALUE_PARAMETRO = "TVALUE";
	public static final String FIL_PARAMETRO = "FIL";
	public static final String FILIAL_DEFAULT = "5";
	public static final String ID_USUARIO = "g3a.id_usuario";
	public static final String ACEPTA_TERMINOS = "acepta_terminos";
	public static final String CAMBIAR_CLAVE = "cambiar_clave";
	public static final String G3A_ENPOINT = "loginsso.endpoint";
	public static final String G3A_FECHAACTUALIZACION = "fecha.actualizacion";
	public static final String MODO_PROXY = "mod.proxy.enabled";
	public static final String PERSONA_JURIDICA = "Juridica";
	public static final String P_JURIDICA = "J";
	public static final int USER_AGENT_SIZE = 400;

	// CONSTANTES DE LOS TIPOS DE LOG
	public static final String TIPO_LOG_G3A = "LOG_G3A";
	public static final String CANAL = "WB";
	public static final String NO_APLICA = "No Aplica";

	// NOMBRE DE LA APLICACIÓN
	public static final String APLICACION = "G3A";

	// desHABILITAR LA ENCRIPCION
	public static final String ZWT_SSO_BACKURL_ENCRYPTDISABLED = "zwt.sso.urlretorno.DisableEncrypt";

	/*
	 * Propiedades para endpoints de servicios. No se puede usar G3A_ENDPOINT porque en axis genera automaticamente el location del port y hay casos en que no corresponde especialmente cuando se usa un dominio y apache http server
	 */
	public static final String G3A_APLICACIONES_ENPOINT = "g3a.aplicaciones.servicio.endpoint";
	public static final String G3A_TIEMPOSESION_ENPOINT = "g3a.tiemposesion.servicio.endpoint";

	// -----------------------------------------------------------------

	private static AutenticacionServicioG3A autenticacionServicio;

	public static AutenticacionServicioG3A getInstance() {
		if (autenticacionServicio == null) {
			autenticacionServicio = new AutenticacionServicioG3A();
		}
		return autenticacionServicio;
	}

	// -----------------------------------------------------------------
	// -----------------------------------------------------------------

	@SuppressWarnings("rawtypes")
	public synchronized void validarSSID(Request request) {

		Session session = request.getSession();

		String req_ssid = request.getParameter(SSID_PARAMETRO);
		String ses_ssid = (String) session.getAttribute(G3A_SESSIONID);

		// Si el sid no corresponde con el actual se borran todos los parametros de la session
		if (req_ssid != null && !req_ssid.equals(ses_ssid)) {

			Enumeration e = session.getAttributeNames();
			while (e.hasMoreElements()) {
				String name = (String) e.nextElement();
				session.removeAttribute(name);
			}

		}

	}

	// -----------------------------------------------------------------
	// -----------------------------------------------------------------

	public synchronized void autenticacion(Request request) {

		String ssid = request.getParameter(SSID_PARAMETRO);
		String tValue = request.getParameter(TVALUE_PARAMETRO);
		String filial = request.getParameter(FIL_PARAMETRO);

		Session session = request.getSession();
		if (org.apache.commons.lang.StringUtils.isBlank(ssid) || org.apache.commons.lang.StringUtils.isBlank(tValue)) {

			SimpleLogger.setDebug("El session id o el ticket value estan vacios");

			session.removeAttribute(LOGIN);

			return;
		}

		// ---------------- para pruebas de concurrencia -------------------//
		String autenticar = ParametrosInicio.getProperty("g3a.autenticacion");

		String ip = CocoonUtils.getRemoteAddr(request);

		String navegador = ValidarTipoDato.validateMaxSize(request.getHeader("user-agent"), AutenticacionServicio.USER_AGENT_SIZE);
		if (navegador == null) {
			navegador = ValidarTipoDato.validateMaxSize(request.getHeader("USER-AGENT"), AutenticacionServicio.USER_AGENT_SIZE);
		}

		if ("false".equals(autenticar)) {
			fijarParametros(session, ip, navegador, tValue, ssid);
			return;
		}
		// -----------------------------------------------------------------------//

		if (filial == null) {
			filial = FILIAL_DEFAULT;
		}

		SimpleLogger.setDebug("Comenzado autenticacion");

		RespuestaAutenticarPTX respuesta = autenticarG3A(ssid, tValue, filial, ip, navegador);

		boolean autenticado = respuesta.isAutenticado();

		if (autenticado) {
			com.osmosyscol.commons.servicio.AutenticacionServicio.limpiarSesion(session);

			SimpleLogger.setDebug("Usuario autenticado con osmoautenticador");

			try {
				Usuario usuario = respuesta.getUsr();

				String idUsuario = usuario.getIdUsuario().getNumeroIdUsuario();
				String idCliente = usuario.getIdUsuario().getNumeroIdCliente();

				// Se quita el digito de verificacion
				String digitoVerificacion = com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio.getProperty("digito.verificacion");
				if (digitoVerificacion != null) {
					if (digitoVerificacion.equalsIgnoreCase("true")) {
						String naturaleza = usuario.getIdUsuario().getNaturaleza();
						if (P_JURIDICA.equalsIgnoreCase(naturaleza) || PERSONA_JURIDICA.equalsIgnoreCase(naturaleza)) {
							SimpleLogger.setDebug("Se elimina digito de verificación. idcliente: " + idCliente + " naturaleza: " + naturaleza);
							if (idCliente != null) {
								idCliente = idCliente.substring(0, idCliente.length() - 1);
							}
						}
					}
				}

				SimpleLogger.setDebug("Fijando valores en sesion");

				fijarParametros(session, ip, navegador, idUsuario, idCliente);

				session.setAttribute(G3A_SESSIONID, respuesta.getTicket().getSsid());
				session.setAttribute(G3A_TOKENVALUE, respuesta.getTicket().getValorTicket());
				session.setAttribute(G3A_FILIAL, respuesta.getTicket().getIdFilial());
				session.setAttribute(G3A_USUARIO, usuario);

				SimpleLogger.setDebug("Autenticacion finalizada exitosamente");

				return;
			} catch (Exception e) {

				SimpleLogger.setError("Ha ocurrido un error al obtener los datos del usuario en la autenticacion PTX", e);

			}
		} else {
			SimpleLogger.setError("El usuario NO ha sido autenticado.");
		}

		SimpleLogger.setDebug("Autenticacion finalizada");

		session.removeAttribute(LOGIN);

	}

	private void fijarParametros(Session sesion, String ip, String navegador, String idUsuario, String idCliente) {
		sesion.setAttribute(LOGIN, idCliente + "|" + idUsuario);
		sesion.setAttribute(ID_CLIENTE, idCliente);
		sesion.setAttribute(ID_USUARIO, idUsuario);
		sesion.setAttribute(GESTOR_VENTANILLA, new GestorVentanilla());
		sesion.setAttribute(REQUEST_REMOTE_ADDR, ip);
		sesion.setAttribute(REQUEST_REMOTE_USER_AGENT, navegador);
	}

	private RespuestaAutenticarPTX autenticarG3A(String sessionid, String tokenValue, String filial, String ip, String navegador) {

		RespuestaAutenticarPTX respuesta = new RespuestaAutenticarPTX();
		respuesta.setAutenticado(false);

		try {

			URL endpointURL = new URL(AutenticacionServicio.getEndpoint() + SERVICIO_WEB_OSMOAUTENTICADOR);
			WSAutenticarPTXSoapBindingStub stub = new WSAutenticarPTXSoapBindingStub(endpointURL, null);

			EntradaAutenticarPTX entrada = new EntradaAutenticarPTX();
			SessionTicket ticket = new SessionTicket();

			ticket.setIdFilial(filial);
			ticket.setIpUsuario(ip);
			ticket.setBrowserUsuario(navegador);
			ticket.setSsid(sessionid);
			ticket.setValorTicket(tokenValue);

			entrada.setTicket(ticket);

			SimpleLogger.setDebug("Realizando autenticación con osmoautenticador");

			respuesta = stub.autenticar(entrada);

		} catch (Exception e) {

			SimpleLogger.setError("Ha ocurrido un error al llamar al servicio de autenticacion", e);
		}

		return respuesta;
	}

	public RespuestaAutorizarPTX autorizar(String sessionid, String tokenValue, String filial, String ip, String navegador) {

		RespuestaAutorizarPTX respuesta = new RespuestaAutorizarPTX();
		respuesta.setAutorizado(false);

		SimpleLogger.setDebug("Comenzado autorizacion con osmoautenticador");

		try {
			URL endpointURL = new URL(AutenticacionServicio.getEndpoint() + SERVICIO_WEB_OSMOAUTENTICADOR);
			WSAutenticarPTXSoapBindingStub stub = new WSAutenticarPTXSoapBindingStub(endpointURL, null);

			EntradaAutorizarPTX entrada = new EntradaAutorizarPTX();
			SessionTicket ticket = new SessionTicket();
			ticket.setIdFilial(filial);
			ticket.setIpUsuario(ip);
			ticket.setBrowserUsuario(navegador);
			ticket.setSsid(sessionid);
			ticket.setValorTicket(tokenValue);

			entrada.setTicket(ticket);

			SimpleLogger.setDebug("Realizando autorizacion con osmoautenticador");

			SimpleLogger.setDebug(JavaToXML.exe("Entrada", entrada).toString());

			respuesta = stub.autorizar(entrada);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error al llamar al servicio de autorizacion", e);
			if (e instanceof InvocationTargetException) {
				InvocationTargetException ex = (InvocationTargetException) e;
				ex.getTargetException();
				SimpleLogger.setError("", ex);
			}
		}

		SimpleLogger.setDebug("Finalizando autorizacion con osmoautenticador");

		return respuesta;
	}

	public void cerrarSesion(Request request) {

		String cerrarSesion = ParametrosInicio.getProperty("g3a.cerrarsesion");

		if ("false".equals(cerrarSesion)) {
			return;
		}

		Session session = request.getSession();

		String sessionid = (String) session.getAttribute(G3A_SESSIONID);

		if (sessionid != null) {
			try {
				URL endpointURL = new URL(AutenticacionServicio.getEndpoint() + SERVICIO_WEB_OSMOAUTENTICADOR);
				WSAutenticarPTXSoapBindingStub stub = new WSAutenticarPTXSoapBindingStub(endpointURL, null);

				EntradaAutorizarPTX entrada = new EntradaAutorizarPTX();
				SessionTicket ticket = obtenerTicket(session);

				entrada.setTicket(ticket);

				stub.cerrarSesion(entrada);

			} catch (Exception e) {
				SimpleLogger.setError("Ha ocurrido un error al llamar al servicio de cerrar sesion", e);
			}
		} else {
			SimpleLogger.setInfo("El session ID es NULL. La sesion se ha cerrado");
		}

		SimpleLogger.setInfo("Cerrando sesion " + session.getAttribute(G3A_SESSIONID));

	}

	public boolean cerrarSesionFilial(HttpSession session) {

		String cerrarSesion = ParametrosInicio.getProperty("g3a.cerrarsesion.filial");

		if ("false".equals(cerrarSesion)) {
			return true;
		}

		if (session != null && session.getAttribute(G3A_SESSIONID) != null) {

			String sessionid = (String) session.getAttribute(G3A_SESSIONID);
			String tokenValue = (String) session.getAttribute(G3A_TOKENVALUE);
			String filial = (String) session.getAttribute(AutenticacionServicioG3A.G3A_FILIAL);
			if (filial == null) {
				filial = AutenticacionServicioG3A.FILIAL_DEFAULT;
			}
			String ip = (String) session.getAttribute(REQUEST_REMOTE_ADDR);

			try {
				URL endpointURL = new URL(AutenticacionServicio.getEndpoint() + SERVICIO_WEB_OSMOAUTENTICADOR);
				WSAutenticarPTXSoapBindingStub stub = new WSAutenticarPTXSoapBindingStub(endpointURL, null);

				EntradaAutorizarPTX entrada = new EntradaAutorizarPTX();
				SessionTicket ticket = new SessionTicket();
				ticket.setIdFilial(filial);
				ticket.setIpUsuario(ip);
				ticket.setSsid(sessionid);
				ticket.setValorTicket(tokenValue);

				entrada.setTicket(ticket);

				return stub.perderSesion(entrada);
			} catch (Exception e) {
				SimpleLogger.setError("Ha ocurrido un error al llamar al servicio de logoff filial", e);
			}
		}
		return false;
	}

	public RespuestaOperacion verificarPermisosTransaccion(Session session, Operacion operacion) {

		try {

			SessionTicket ticket = obtenerTicket(session);

			URL endpointURL = new URL(AutenticacionServicio.getEndpoint() + SERVICIO_WEB_OSMOAUTENTICADOR);
			WSAutenticarPTXSoapBindingStub stub = new WSAutenticarPTXSoapBindingStub(endpointURL, null);

			SolicitudTransaccion solicitud = new SolicitudTransaccion();
			solicitud.setOperacion(operacion);
			solicitud.setTicket(ticket);

			return stub.validarPermisosTransaccion(solicitud);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error haciendo la validacion de permisos de transaccion", e);
		}

		return null;
	}

	public RespuestaOperacion iniciarTransaccion(Session session, Operacion operacion) {

		try {
			URL endpointURL = new URL(AutenticacionServicio.getEndpoint() + SERVICIO_WEB_OSMOAUTENTICADOR);
			WSAutenticarPTXSoapBindingStub stub = new WSAutenticarPTXSoapBindingStub(endpointURL, null);

			SessionTicket ticket = obtenerTicket(session);

			SolicitudTransaccion solicitud = new SolicitudTransaccion();
			solicitud.setOperacion(operacion);
			solicitud.setTicket(ticket);

			return stub.iniciarTransaccion(solicitud);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error al iniciar la transaccion", e);
		}

		return null;
	}

	public RespuestaOperacion solicitarActivacion(Session session, IDUsuario idUsuario) {

		try {
			URL endpointURL = new URL(AutenticacionServicio.getEndpoint() + SERVICIO_WEB_OSMOAUTENTICADOR);
			WSAutenticarPTXSoapBindingStub stub = new WSAutenticarPTXSoapBindingStub(endpointURL, null);

			SessionTicket ticket = obtenerTicket(session);

			SolicitudActivacion solicitud = new SolicitudActivacion();
			solicitud.setIdUsuario(idUsuario);
			solicitud.setTicket(ticket);

			return stub.solicitarActivacion(solicitud);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error al solicitar la activacion del usuario", e);
		}

		return null;
	}

	public SessionTicket obtenerTicket(Session session) {

		String sessionid = (String) session.getAttribute(G3A_SESSIONID);
		String tokenValue = (String) session.getAttribute(G3A_TOKENVALUE);
		String filial = session.getAttribute(AutenticacionServicioG3A.G3A_FILIAL).toString();
		if (filial == null) {
			filial = AutenticacionServicioG3A.FILIAL_DEFAULT;
		}
		String ip = (String) session.getAttribute(REQUEST_REMOTE_ADDR);
		String navegador = (String) session.getAttribute(REQUEST_REMOTE_USER_AGENT);

		SessionTicket ticket = new SessionTicket();
		ticket.setIdFilial(filial);
		ticket.setIpUsuario(ip);
		ticket.setBrowserUsuario(navegador);
		ticket.setSsid(sessionid);
		ticket.setValorTicket(tokenValue);

		return ticket;
	}

	public void actualizarTicket(Session sesion, SessionTicket ticket) {
		sesion.setAttribute(G3A_SESSIONID, ticket.getSsid());
		sesion.setAttribute(G3A_TOKENVALUE, ticket.getValorTicket());
		sesion.setAttribute(G3A_FILIAL, ticket.getIdFilial());
	}

	public SessionTicket obtenerTicket(Request request, boolean autenticacion) {
		String ssid = request.getParameter(SSID_PARAMETRO);
		String tValue = null;
		if (!autenticacion) {
			tValue = request.getParameter(TVALUE_PARAMETRO);
		}
		String filial = request.getParameter(FIL_PARAMETRO);

		String ip = CocoonUtils.getRemoteAddr(request);

		String navegador = ValidarTipoDato.validateMaxSize(request.getHeader("user-agent"), AutenticacionServicio.USER_AGENT_SIZE);
		if (navegador == null) {
			navegador = ValidarTipoDato.validateMaxSize(request.getHeader("USER-AGENT"), AutenticacionServicio.USER_AGENT_SIZE);
		}

		Session session = request.getSession();
		SessionTicket ticket = new SessionTicket();

		if (org.apache.commons.lang.StringUtils.isBlank(ssid) || org.apache.commons.lang.StringUtils.isBlank(tValue)) {

			SimpleLogger.setDebug("El session id o el ticket value estan vacios");

			ticket = obtenerTicket(session);

			return ticket;
		}

		ticket.setIdFilial(filial);
		ticket.setIpUsuario(ip);
		ticket.setBrowserUsuario(navegador);
		ticket.setSsid(ssid);
		ticket.setValorTicket(tValue);

		return ticket;
	}

	public List<Aplicacion> obtenerAplicaciones(String login) {
		try {
			// SimpleLogger.setInfo("Obteniendo aplicaciones de G3A. Servicio con la nueva propiedad");
			String endPointG3A = ParametrosInicio.getProperty(G3A_APLICACIONES_ENPOINT);

			logger.trace(String.format("obtenerAplicaciones (begin) [endpoint-g3a=%s]", endPointG3A));

			if (StringUtils.esVacio(endPointG3A)) {
				return null;
			}

			URL url = new URL(endPointG3A);

			WSAplicacionesSoapBindingStub stub = new WSAplicacionesSoapBindingStub(url, null);

			Aplicacion[] aplicaciones = stub.obtenerAplicaciones(login);

			if (aplicaciones == null) {
				logger.info(String.format("obtenerAplicaciones (end) [num-apps=null]"));
				return null;
			}

			List<Aplicacion> list = new ArrayList<Aplicacion>();
			CollectionUtils.addAll(list, aplicaciones);

			logger.trace(String.format("obtenerAplicaciones (end) [num-apps=%d]", list.size()));
			return list;

		} catch (Throwable e) {
			logger.error(String.format("obtenerAplicaciones (error) [msg=%s]", e.getMessage()), e);
		}
		return null;
	}

	public Integer obtenerTiempoMaximoSesion() {
		Integer defaultTiempoMaximoSesion = 1000;
		try {

			SimpleLogger.setInfo("Obteniendo tiempo máximo de sesión de G3A. Servicio con la nueva propiedad");
			String endPointG3A = ParametrosInicio.getProperty(G3A_TIEMPOSESION_ENPOINT);

			if (StringUtils.esVacio(endPointG3A)) {
				return defaultTiempoMaximoSesion;
			}

			URL url = new URL(endPointG3A);

			WSParametrosSoapBindingStub stub = new WSParametrosSoapBindingStub(url, null);
			return stub.obtenerTiempoMaximoSesion();

		} catch (Throwable e) {
			SimpleLogger.setError("Ocurrio un problema al obtener el tiempo máximo de sesión de G3A. Error consultado parametro " + G3A_TIEMPOSESION_ENPOINT + ". Se utilizará el valor por defecto 1000", e);
			return defaultTiempoMaximoSesion;
		}
	}

}
