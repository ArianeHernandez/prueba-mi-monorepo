// CVS $Id$

package com.osmosyscol.commons.servicio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.apache.cocoon.ProcessingException;
import org.apache.cocoon.acting.ServerPagesAction;
import org.apache.cocoon.components.language.markup.xsp.XSPUtil;
import org.apache.cocoon.environment.Cookie;
import org.apache.cocoon.environment.Redirector;
import org.apache.cocoon.environment.Request;
import org.apache.cocoon.environment.Response;
import org.apache.cocoon.environment.Session;
import org.apache.cocoon.environment.SourceResolver;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;

import co.htsoft.commons.rsa.SecureJsonUtils;

import com.google.gson.Gson;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.rpc.jsonrpc.InvocationCallback;
import com.osmosyscol.commons.rpc.jsonrpc.JSONCallBackAutorizacion;
import com.osmosyscol.commons.rpc.jsonrpc.JSONRPCBridge;
import com.osmosyscol.commons.servicio.json.Core_JsonServicio;
import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.commons.servlet.ContextInfo;
import com.osmosyscol.commons.utils.CallPage;
import com.osmosyscol.commons.utils.CocoonUtils;
import com.osmosyscol.commons.utils.GestorVentanilla;
import com.osmosyscol.commons.utils.Mensaje;
import com.osmosyscol.commons.utils.ServletUtils;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.commons.utils.ValidarTipoDato;
import com.osmosyscol.commons.utils.ldap.LDAPAuthenticator;
import com.osmosyscol.commons.utils.ldap.LDAPConnectionFactory;
import com.osmosyscol.commons.utils.ldap.LDAPLookupConfig;
import com.osmosyscol.commons.utils.ldap.LDAPUser;
import com.osmosyscol.commons.validacion.DefaultBaseAccion;
import com.osmosyscol.commons.validacion.ResultadoValidacion;
import com.osmosyscol.commons.validacion.ValidacionAccion;
import com.osmosyscol.datapi.logica.dto.RequiereClaveTX;
import com.osmosyscol.datapi.logica.dto.SSOAuth;
import com.osmosyscol.datapi.logica.dto.SessionData;
import com.osmosyscol.g3a.cliente.RespuestaAutorizarPTX;
import com.osmosyscol.g3a.cliente.entidades.SessionTicket;
import com.osmosyscol.g3a.servicios.AutenticacionServicioG3A;
import com.osmosyscol.osmoautenticador.servicioweb.UrlServicio.TipoElementoSalidaobtenerUrlServicios;
import com.osmosyscol.osmoautenticador.servicioweb.cliente.autenticar.RespuestaAutenticar;
import com.osmosyscol.osmoautenticador.servicioweb.cliente.autenticar.RespuestaAutorizar;
import com.osmosyscol.osmoautenticador.servicioweb.cliente.autenticar.SolicitudAutenticar;
import com.osmosyscol.osmoautenticador.servicioweb.cliente.autenticar.SolicitudAutorizar;
import com.osmosyscol.osmoautenticador.servicioweb.cliente.autenticar.WSAutenticadorSoapBindingStub;
import com.osmosyscol.osmoautenticador.servicioweb.cliente.cambiarclave.RespuestaCambiarClave;
import com.osmosyscol.osmoautenticador.servicioweb.cliente.cambiarclave.SolicitudCambiarClave;
import com.osmosyscol.osmoautenticador.servicioweb.cliente.cambiarclave.WSCambiarClaveSoapBindingStub;
import com.osmosyscol.osmoautenticador.servicioweb.informacionPersona.InformacionPersonaSOAPStub;
import com.osmosyscol.osmoautenticador.servicioweb.informacionPersona.TipoElementoEntradainformacionPersona;
import com.osmosyscol.osmoautenticador.servicioweb.informacionPersona.TipoElementoSalidainformacionPersona;
import com.osmosyscol.osmoautenticador.servicioweb.informacionPersona.TipoEntradainformacionPersona;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class AutenticacionServicio {

	public static final int USER_AGENT_SIZE = 400;
	public static final String LOGIN_ATRIBUTO = "login";
	public static final String HOST_ATRIBUTO = "_remote_host_ip_browser";
	public static final String ULTIMO_ACCESO = "_last_login";
	public static final String MAX_TIEMPO_INACTIVIDAD = "_session_maxtime";
	public static int valor_max_tiempo_inactividad = 1000;

	private static final String AUTORIZADO_ATRIBUTO = "autorizado";
	private static String endpoint;
	private static final String SERVICES_WS_AUTENTICADOR = "/services2/WSAutenticador";
	private static final String SERVICES_WS_CAMBIAR_CLAVE = "/services2/WSCambiarClave";
	private static final String SERVICES_WS_OBTENER_PERSONA = "/ws/informacionPersona";

	private static final String URLS_AUTORIZAR = "URLS_AUTORIZAR";
	public static final String MAPA_AUTORIZACION = "MAPA_AUTORIZACION";

	// -----------------------------------------------------------------

	public static String ID_APLICACION = null;
	public static String PATH_APLICACION = null;
	public static String PAGINA_INICIO = "/inicio/inicio.pub";
	public static String PAGINA_ACCESODENEGADO = "/inicio/inicio.pub";
	public static String PAGINA_ERRORINESPERADO = "/inicio/inicio.pub";
	public static String PAGINA_SALIDA = "/inicio/inicio.pub?logout";
	public static String NOMBRE_APLICACION = "default";
	public static String VERSION_APLICACION = "undefined";
	public static String PAQUETE_VALIDACION = "com.osmosyscol.validacion.accion";

	// -----------------------------------------------------------------

	public static String MODO_PROXY = "mod.proxy.enabled";

	// -----------------------------------------------------------------

	public List<Thread> THREADS = new ArrayList<Thread>();

	public static Boolean SHUTDOWN = false;

	// -----------------------------------------------------------------

	private static Set<Class<?>> jsonCallbacks = new HashSet<Class<?>>();

	// -----------------------------------------------------------------

	private boolean redirectAbsoluto;
	private boolean validarIp = true;

	// -----------------------------------------------------------------

	public static void setValorMaxTiempoInactividad(int tiempo) {
		valor_max_tiempo_inactividad = tiempo;
	}

	private static AutenticacionServicio autenticacionServicio = null;
	
	private static ConcurrentHashMap<String, Integer> mapIntentosFallidos = new ConcurrentHashMap<String, Integer>();

	private AutenticacionServicio() {
		SimpleLogger.setInfo(String.format("Cargando servicio de autenticacion ... "));

		// ----------------------------------

		XPathFactory factory = XPathFactory.newInstance();
		XPath xPath = factory.newXPath();
		File xmlDocument = null;
		String filename;

		filename = getDiskPathForResource("WEB-INF/actions.xml");

		PATH_APLICACION = getDiskPathBase(null);

		SimpleLogger.setInfo(String.format(" fijando parametros servicio autenticacion: [actions-file:%s,app-path:%s]", filename, PATH_APLICACION));

		try {
			xmlDocument = new File(filename);
			InputSource inputSource = new InputSource(new FileInputStream(xmlDocument));
			String pagina_inicio = xPath.evaluate("/actions/init/PAGINA_INICIO", inputSource);
			if (pagina_inicio != null && pagina_inicio.trim().length() > 0) {
				PAGINA_INICIO = pagina_inicio;
			}

			inputSource = new InputSource(new FileInputStream(xmlDocument));
			String pagina_accesodenegado = xPath.evaluate("/actions/init/PAGINA_ACCESODENEGADO", inputSource);
			if (pagina_accesodenegado != null && pagina_accesodenegado.trim().length() > 0) {
				PAGINA_ACCESODENEGADO = pagina_accesodenegado;
			}

			inputSource = new InputSource(new FileInputStream(xmlDocument));
			String pagina_errorinesperado = xPath.evaluate("/actions/init/PAGINA_ERRORINESPERADO", inputSource);
			if (pagina_errorinesperado != null && pagina_errorinesperado.trim().length() > 0) {
				PAGINA_ERRORINESPERADO = pagina_errorinesperado;
			} else {
				PAGINA_ERRORINESPERADO = PAGINA_INICIO;
			}

			inputSource = new InputSource(new FileInputStream(xmlDocument));
			String pagina_salida = xPath.evaluate("/actions/init/PAGINA_SALIDA", inputSource);
			if (pagina_salida != null && pagina_salida.trim().length() > 0) {
				PAGINA_SALIDA = pagina_salida;
			}

			inputSource = new InputSource(new FileInputStream(xmlDocument));
			String paquete_validacion = xPath.evaluate("/actions/init/PAQUETE_VALIDACION", inputSource);
			if (paquete_validacion != null && paquete_validacion.trim().length() > 0) {
				PAQUETE_VALIDACION = paquete_validacion;
			}

			inputSource = new InputSource(new FileInputStream(xmlDocument));
			String id_aplicacion = xPath.evaluate("/actions/@id_app", inputSource);
			if (id_aplicacion != null && id_aplicacion.trim().length() > 0) {
				ID_APLICACION = id_aplicacion;
			}

			inputSource = new InputSource(new FileInputStream(xmlDocument));
			String nombre_aplicacion = xPath.evaluate("/actions/@app", inputSource);
			if (nombre_aplicacion != null && nombre_aplicacion.trim().length() > 0) {
				NOMBRE_APLICACION = nombre_aplicacion;
				SimpleLogger.setAppname(NOMBRE_APLICACION.toUpperCase());
			}

			inputSource = new InputSource(new FileInputStream(xmlDocument));
			String version_aplicacion = xPath.evaluate("/actions/@version", inputSource);
			if (version_aplicacion != null && version_aplicacion.trim().length() > 0) {
				VERSION_APLICACION = version_aplicacion;
			}

		} catch (Exception e) {
			SimpleLogger.setError("error realizando la carga del servicio: AutenticacionServicio", e);
		}

		// ---------------------------------

	}

	private static String getDiskPathBase(Request object) {
		return ContextInfo.getInstance().getRealPath(null);
	}

	public static AutenticacionServicio getInstance() {
		if (autenticacionServicio == null) {
			synchronized (AutenticacionServicio.class) {
				if (null == autenticacionServicio) {
					autenticacionServicio = new AutenticacionServicio();
					autenticacionServicio.inicializarHilos();
				}
			}
		}
		return autenticacionServicio;
	}

	// -----------------------------------------------------------------

	public void autorizacionPage(Request request, Response response, SourceResolver resolver, ContentHandler contentHandler, Redirector actionRedirector, Map<Object, Object> objectModel) throws ProcessingException, IOException {

		// Si el header de sec-fetch-mode no es navigate entonces finaliza
		String sec_fetch_mode = StringUtils.trimToNull(request.getHeader("sec-fetch-mode"));
		
		if(sec_fetch_mode != null && !sec_fetch_mode.equalsIgnoreCase("navigate")){
			redireccionar(request, actionRedirector, "/");
			return;
		}
		
		Session session = request.getSession();

		long timeservice_osmcoresystem = System.currentTimeMillis();

		String destino = obtenerDestinoConParametros(PAGINA_ACCESODENEGADO, request);

		// -------------------------------
		// Determina si esta autenticado

		Boolean esAutorizado = AutenticacionServicio.getInstance().autenticacion(request, response, resolver, contentHandler);

		// -------------------------------
		// Si es null es que ocurrio un error en la validacion
		if (esAutorizado == null) {

			destino = obtenerDestinoConParametros(PAGINA_ERRORINESPERADO, request);

		} else {

			if (esAutorizado) {

				ResultadoValidacion rvalidacion = null;

				// *********************************************************************************

				String serverRequest = request.getRequestURI().substring(request.getContextPath().length());

				boolean osm_ejecutar_pagina = false; // Determina si se debe ejecutar la pagina
				boolean osm_cargar_pagina = false; // Determina si se debe carga de cache ( solo por ser transaccional )
				boolean osm_salir_pagina = false; // Determina si se salir de la pagina

				// -----------------
				// Clave transaccional (ctx)
				String usuario = (String) session.getAttribute("login");

				if (usuario != null) {
					RequiereClaveTX requiereCtx = ClaveTransaccionalServicio.getInstance().requiereClaveTransaccional(serverRequest, usuario);

					// Si requiere ctx
					if (requiereCtx.isRequerido()) {
						rvalidacion = ClaveTransaccionalServicio.getInstance().validarClaveTransaccional(requiereCtx.getId_servicio(), session, serverRequest, PAGINA_INICIO);

						if (rvalidacion != null && !rvalidacion.isPermitido()) {
							SimpleLogger.setInfo("Se requiere clave transaccional y no se tiene..");
							redireccionar(request, actionRedirector, rvalidacion.getSiguientePagina());
							return;
						}
					} else {
						ClaveTransaccionalServicio.getInstance().limpiarToken(session);
					}
				}

				// -----------------

				String osm_tmp_type = serverRequest.substring(serverRequest.lastIndexOf("."));

				if (osm_tmp_type.lastIndexOf(".do") >= 0) {
					osm_tmp_type = ".do";
				}
				String type_action = osm_tmp_type + "_action";

				String osm_url_page = "cocoon:" + serverRequest.substring(0, serverRequest.lastIndexOf(".")) + type_action;

				GestorVentanilla gestorventanilla = (GestorVentanilla) session.getAttribute("gestor_ventanilla");

				String osm_ticket = request.getParameter("osm_ticket");

				// ----------------------

				boolean esTransaccional = AutenticacionServicio.getInstance().esTransaccional(request, serverRequest);

				// ------------------------------------------------------------------
				// Si es transaccional determina la accion a realizar

				if (esTransaccional) {

					if (gestorventanilla.existeTiquete(osm_ticket)) {

						if (gestorventanilla.esValidoTiquete(osm_ticket)) {
							osm_ejecutar_pagina = true;
						} else {
							if (gestorventanilla.esRealizarCarga(osm_ticket, osm_url_page)) {
								osm_cargar_pagina = true;
							} else {
								osm_salir_pagina = true;
								SimpleLogger.setError("La pagina es transaccional y no tiene un tiquete valido. " + request.getRequestURI());
							}
						}
					} else {
						SimpleLogger.setError("La pagina es transaccional no tiene un tiquete. " + request.getRequestURI());
						osm_salir_pagina = true;
					}

				} else {

					if (StringUtils.isBlank(osm_ticket) || gestorventanilla.esValidoTiquete(osm_ticket) || CocoonUtils.getRemoteAddr(request).equals("127.0.0.1")) {
						osm_ejecutar_pagina = true;
					} else {
						osm_salir_pagina = true;
					}
				}

				// ***********************************************************************************
				// Acciones a realizar si se debe ejecutar

				if (osm_ejecutar_pagina) {

					// --------------------------------------------------------
					// Marca el ticket como utilizado

					gestorventanilla.usarTiquete(osm_ticket, osm_url_page);

					// ------------------------------------------------------------------
					// Determina si se guarda en cache

					String cacheId = AutenticacionServicio.getInstance().getCacheId(request, serverRequest);

					if (cacheId != null) {

						// --------------------------------------------------------------
						// Carga el resultado de lavalidacion si se tiene en cache

						if (session.getAttribute("VALIDACION." + cacheId) != null) {

							rvalidacion = (ResultadoValidacion) session.getAttribute("VALIDACION." + cacheId);

							request.setAttribute("FCORE.CACHESESION", cacheId);
						}

						// --------------------------------------------------------------
						// Procesa y guarda la validacion si no se tiene en cache

						else {

							try {

								rvalidacion = AutenticacionServicio.getInstance().validar(request);
								session.setAttribute("VALIDACION." + cacheId, rvalidacion);

								request.setAttribute("FCORE.PROCESAR", Boolean.TRUE);
								request.setAttribute("FCORE.GUARDARCACHESESION", cacheId);

							} catch (Exception e) {
								SimpleLogger.setError("Ha ocurrido un error al realizar la validacion de la pagina. " + osm_url_page);
								osm_salir_pagina = true;
							}
						}

					}

					// --------------------------------------------------------------
					// Si la pagina no tiene activa cache se procesa

					else {

						try {

							rvalidacion = AutenticacionServicio.getInstance().validar(request);
							request.setAttribute("FCORE.PROCESAR", Boolean.TRUE);

						} catch (Exception e) {
							SimpleLogger.setError("Ha ocurrido un error al realizar la validacion de la pagina. " + osm_url_page);
							osm_salir_pagina = true;
						}
					}

					// --------------------------------------------------------------
					// Si la pagina es transaccional guarda el contenido de la pagina en memoria

					if (esTransaccional && rvalidacion != null) {
						gestorventanilla.setRvalidacion(rvalidacion);
						gestorventanilla.setUrl_memoria(osm_url_page);
						request.setAttribute("FCORE.GUARDARCACHETRANSACCIONAL", Boolean.TRUE);
					}

				}

				// ***********************************************************************************
				// Acciones a realizar si se debe cargar de memoria

				if (osm_cargar_pagina) {

					String osm_urlmemoria = gestorventanilla.getUrl_memoria();

					if (osm_url_page.equals(osm_urlmemoria)) {
						rvalidacion = gestorventanilla.getRvalidacion();

						if (rvalidacion != null) {
							request.setAttribute("FCORE.CACHETRANSACCIONAL", Boolean.TRUE);
						} else {
							SimpleLogger.setError("Error al cargan contenido de cache. " + osm_url_page);
							osm_salir_pagina = true;
						}

					} else {
						SimpleLogger.setError("Error al cargan contenido de cache. (no es la ultima en memoria) " + osm_url_page);
						osm_salir_pagina = true;
					}
				}

				// -------------------------------------------

				destino = null; // si es permitido es null

				if (osm_salir_pagina || rvalidacion == null) {
					rvalidacion = new ResultadoValidacion();
					rvalidacion.setPermitido(false);
				}

				if (!rvalidacion.isPermitido()) {
					session.setAttribute("resultadovalidacion", rvalidacion);

					SimpleLogger.setInfo("La validacion ha denegado el acceso a la pagina.");
					destino = rvalidacion.getSiguientePagina();

					String urlrequest = request.getRequestURI().substring(request.getContextPath().length());

					if (urlrequest.equals(destino)) {
						destino = null;
					}
				}

				// ***************************************************************************************

			}
			// ----------------------------------------------------------------
			// Si no es autorizado determina la pagina a redireccionar

			else {

				String auth_username = request.getParameter("auth_username");

				aumentarIntentosFallidos(request);
				
				if (auth_username != null) {
					
					if (request.getParameter("osm_lastpage") != null && request.getParameter("osm_lastpage").trim().indexOf(".pub") >= 0) {
						destino = request.getParameter("osm_lastpage").trim() + "?invalido";
						SimpleLogger.setInfo("Invalido: regresando a pagina anterior: " + destino);
					} else {
						destino = AutenticacionServicio.PAGINA_INICIO + "?invalido";
						SimpleLogger.setInfo("Invalido: regresando a pagina inicio: " + destino);
					}

				} else {

					destino = obtenerDestinoConParametros(AutenticacionServicio.PAGINA_ACCESODENEGADO, request);

				}

			}

			// ---------------------------------------------------------------
		}

		AutenticacionServicio.addLogPage("System://AutenticacionServicio.autenticacion", System.currentTimeMillis() - timeservice_osmcoresystem);

		if (destino == null) {

			objectModel.put(ServerPagesAction.ACTION_SUCCESS_OBJECT, Boolean.TRUE);

		} else {
			redireccionar(request, actionRedirector, destino);
		}
	}

	private void redireccionar(Request request, Redirector actionRedirector, String destino) throws IOException, ProcessingException {
		if (destino.startsWith("http://") || destino.startsWith("https://")) {

			System.out.println("(AutenticacionServicio) Redireccionando a destino: " + destino);

			actionRedirector.redirect(false, destino);

		} else {

			if (redirectAbsoluto) {

				if (destino.charAt(0) != '/') {
					destino = "/" + destino;
				}

				destino = request.getContextPath() + destino;

			} else {

				if (destino.charAt(0) == '/') {
					destino = destino.substring(1);
				}

				String serverRequest = request.getRequestURI().substring(request.getContextPath().length());

				int r = (serverRequest.split("/").length - 2);

				for (int i = 0; i < r; i++) {
					destino = "../" + destino;
				}
			}

			System.out.println("(AutenticacionServicio) Redireccionando a destino: " + destino);

			actionRedirector.redirect(false, destino);
		}
	}

	// -----------------------------------------------------------------

	public static void registrarJsonCallback(Class<?> callback) {

		if (InvocationCallback.class.isAssignableFrom(callback)) {
			jsonCallbacks.add(callback);
		} else {
			throw new IllegalArgumentException(callback.getName() + " no implementa InvocationCallback");
		}

	}

	// -----------------------------------------------------------------

	public void inicializarServiciosJSONSession(Request request) {

		HttpSession session = CocoonUtils.getHttpSession(request);

		JSONRPCBridge json_bridge = null;
		json_bridge = (JSONRPCBridge) session.getAttribute("JSONRPCBridge");
		if (json_bridge == null) {
			json_bridge = new JSONRPCBridge();

			JSONCallBackAutorizacion callBack = new JSONCallBackAutorizacion();

			json_bridge.registerCallback(callBack, HttpServletRequest.class);

			if (jsonCallbacks != null && !jsonCallbacks.isEmpty()) {
				for (Class<?> callbackClass : jsonCallbacks) {
					try {

						InvocationCallback callBackInstance = (InvocationCallback) callbackClass.getConstructor().newInstance();

						json_bridge.registerCallback(callBackInstance, HttpServletRequest.class);

						// Las siguientes exceptions solo deberían lanzarse en tiempo de desarrollo
					} catch (IllegalArgumentException e) {
						SimpleLogger.setError("Error inicializando JsonCallback ", e);
					} catch (SecurityException e) {
						SimpleLogger.setError("Error inicializando JsonCallback ", e);
					} catch (InstantiationException e) {
						SimpleLogger.setError("Error inicializando JsonCallback ", e);
					} catch (IllegalAccessException e) {
						SimpleLogger.setError("Error inicializando JsonCallback ", e);
					} catch (InvocationTargetException e) {
						SimpleLogger.setError("Error inicializando JsonCallback ", e);
					} catch (NoSuchMethodException e) {
						SimpleLogger.setError("Error inicializando JsonCallback ", e);
					}
				}
			}

			session.setAttribute("JSONRPCBridge", json_bridge);
		}

		Core_JsonServicio core = new Core_JsonServicio();
		core.setSession(request.getSession());

		json_bridge.registerObject("core", core);

		// -----

		XPathFactory factory = XPathFactory.newInstance();
		XPath xPath = factory.newXPath();
		File xmlDocument = null;
		String filename;
		filename = getDiskPathForResource("WEB-INF/json-services.xml");

		try {
			xmlDocument = new File(filename);
			if (xmlDocument.exists()) {
				InputSource inputSource = new InputSource(new FileInputStream(xmlDocument));
				Integer cantidad = Integer.parseInt(xPath.evaluate("count(/json-services/session-service)", inputSource));

				for (int i = 1; i <= cantidad; i++) {

					try {

						String urn = xPath.evaluate("/json-services/session-service[" + i + "]/@urn", new InputSource(new FileInputStream(xmlDocument)));
						String className = xPath.evaluate("/json-services/session-service[" + i + "]/@class", new InputSource(new FileInputStream(xmlDocument)));

						Object obj = Class.forName(className).newInstance();

						if (obj instanceof JsonService) {
							((JsonService) obj).setSession((Session) session.getAttribute("cocoon-session"));
						} else {
							SimpleLogger.setWarn("El servicio Json de session no es JsonService");
						}

						json_bridge.registerObject(urn, obj);

					} catch (Throwable e) {
						SimpleLogger.setError("No se ha podido agregar el servicio json", e);
					}
				}

			}
		} catch (Exception e) {
			SimpleLogger.setError("Error al realizar inicializacion de servicios json", e);
		}
	}

	// -----------------------------------------------------------------
	// -----------------------------------------------------------------

	public static Boolean INICIALIZAR_HILOS = true;

	public void inicializarHilos() {

		if (INICIALIZAR_HILOS) {

			// ---------------------------------------------------------------------

			XPathFactory factory = XPathFactory.newInstance();
			XPath xPath = factory.newXPath();
			File xmlDocument = null;
			String filename;
			filename = getDiskPathForResource("WEB-INF/actions.xml");

			try {
				xmlDocument = new File(filename);
				if (xmlDocument.exists()) {
					InputSource inputSource = new InputSource(new FileInputStream(xmlDocument));
					Integer cantidad = Integer.parseInt(xPath.evaluate("count(/actions/thread)", inputSource));

					for (int i = 1; i <= cantidad; i++) {

						try {

							String classname = xPath.evaluate("/actions/thread[" + i + "]/@class", new InputSource(new FileInputStream(xmlDocument)));

							Runnable runnable = loadClass(classname);

							if (runnable != null) {
								Thread hilo = new Thread(runnable);
								hilo.setName(classname);
								hilo.setPriority(Thread.MIN_PRIORITY);
								hilo.start();

								THREADS.add(hilo);

								SimpleLogger.setInfo("hilo inicializado :" + runnable.getClass().getSimpleName());
							}
						} catch (Throwable e) {
							SimpleLogger.setError("No se ha podido inicializar el hilo", e);
						}
					}

				}
			} catch (Exception e) {
				SimpleLogger.setError("Error al realizar la inicializacion de hilos", e);
			}

			// ----------------------------------------------------------------------------
		}
	}

	public Runnable loadClass(String classname) {
		try {
			return (Runnable) Class.forName(classname).newInstance();

		} catch (Exception e) {
			SimpleLogger.setError("No es posible cargar el Hilo: " + classname);
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	public void finalizarHilos() {

		SHUTDOWN = true;

		int intentos = 0;

		boolean activos = true;

		while (activos) {

			activos = false;
			intentos++;

			for (Thread hilo : THREADS) {

				if (hilo.isAlive()) {
					activos = true;

					SimpleLogger.setInfo("(" + intentos + ") Deteniendo hilo " + hilo.getName());

					try {
						if (intentos < 20) {
							hilo.interrupt();
						} else {
							hilo.stop(); // Forzar la detencion del hilo..!!
							hilo.destroy();
						}
					} catch (Exception e) {
						SimpleLogger.setError("No se puede detener el Hilo " + hilo, e);
					}

				}
			}

			try {
				Thread.sleep(500);
			} catch (Exception e) {
			}
		}

		SimpleLogger.setInfo("Hilos core Finalizados");

	}

	public Boolean autenticar(String login, String clave, String tipoclave, String configuracion) {

		if (login != null)
			login = login.toLowerCase();

		XPathFactory factory = XPathFactory.newInstance();
		XPath xPath = factory.newXPath();
		File xmlDocument = null;
		String filename;

		filename = getDiskPathForResource("WEB-INF/users.xml");

		try {
			xmlDocument = new File(filename);
			if (xmlDocument.exists()) {
				InputSource inputSource = new InputSource(new FileInputStream(xmlDocument));
				String consulta = xPath.evaluate("/users/user[@login='" + login + "' and ( count(@pass)=0 or @pass='" + StringUtils.SHA512(clave) + "' or @pass='" + StringUtils.MD5(clave) + "' )]/@login", inputSource);
				return consulta != null && consulta.length() > 0;
			}
		} catch (Exception e) {
			SimpleLogger.setError("Error al realizar la autenticacion Interna");
		}

		// -------------------

		URL url;
		try {
			url = new URL(getEndpoint() + SERVICES_WS_AUTENTICADOR);

			WSAutenticadorSoapBindingStub stub = new WSAutenticadorSoapBindingStub(url, null);

			SolicitudAutenticar solicitudAutenticar = new SolicitudAutenticar();

			solicitudAutenticar.setLogin(login);
			solicitudAutenticar.setClave(clave);
			solicitudAutenticar.setTipoclave(tipoclave);
			solicitudAutenticar.setConfiguracion(configuracion);
			solicitudAutenticar.setAplicacion(NOMBRE_APLICACION);

			RespuestaAutenticar resp = stub.autenticar(solicitudAutenticar);
			return resp.getAutenticado();

		} catch (Exception e) {
			SimpleLogger.setError(">> Ha ocurrido al intentar autenticar. ", e);
		}

		return false;
	}

	// -----------------------------------------------------------------
	// -----------------------------------------------------------------

	public Boolean autenticar(String login, String clave, String configuracion) {
		return autenticar(login, clave, "1", configuracion);
	}

	// -----------------------------------------------------------------

	public Boolean autenticar2(String login, String clave, String configuracion) {
		return autenticar(login, clave, "2", configuracion);
	}

	// -----------------------------------------------------------------
	// -----------------------------------------------------------------

	public TipoElementoSalidainformacionPersona obtenerInformacionPersona(String identificacion) {

		URL url;
		try {
			url = new URL(getEndpoint() + SERVICES_WS_OBTENER_PERSONA);

			InformacionPersonaSOAPStub stub = new InformacionPersonaSOAPStub(url, null);

			TipoEntradainformacionPersona entrada = new TipoEntradainformacionPersona();
			TipoElementoEntradainformacionPersona elementoEntrada = new TipoElementoEntradainformacionPersona();

			elementoEntrada.setIdentificacion(identificacion);

			entrada.setElementoEntrada(elementoEntrada);
			TipoElementoSalidainformacionPersona[] resp = stub.informacionPersona(entrada);

			if (resp != null) {
				return resp[0];
			}

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error al autorizar.", e);
		}

		return null;

	}

	// -----------------------------------------------------------------
	// -----------------------------------------------------------------

	public Boolean autorizar(String login, String nurl) {

		if (login != null)
			login = login.toLowerCase();

		nurl = nurl.replace(".do.pdf", ".do").replace(".do_pdf", ".do").replace(".do.xls", ".do").replace(".do_xls", ".do").replace(".do_xml", ".do");

		// ---------------------

		XPathFactory factory = XPathFactory.newInstance();
		XPath xPath = factory.newXPath();
		File xmlDocument = null;
		String filename;

		filename = getDiskPathForResource("WEB-INF/users.xml");

		try {
			xmlDocument = new File(filename);
			if (xmlDocument.exists()) {
				InputSource inputSource = new InputSource(new FileInputStream(xmlDocument));
				String consulta = xPath.evaluate("count(//services/service[url='" + nurl + "' and @name = //user[@login='" + login + "']/service/@name])", inputSource);
				return new Integer(consulta) > 0;
			}
		} catch (Exception e) {
			SimpleLogger.setError("Error al realizar la autorizacion Interna");
		}

		// -------------------

		URL url;
		try {
			url = new URL(getEndpoint() + SERVICES_WS_AUTENTICADOR);

			WSAutenticadorSoapBindingStub stub = new WSAutenticadorSoapBindingStub(url, null);
			SolicitudAutorizar solicitudAutorizar = new SolicitudAutorizar();

			solicitudAutorizar.setLogin(login);
			solicitudAutorizar.setUrl(nurl);
			solicitudAutorizar.setAplicacion(NOMBRE_APLICACION);

			RespuestaAutorizar resp = stub.autorizar(solicitudAutorizar);

			SimpleLogger.setDebug("Autorizacion: [" + login + "," + nurl + "," + resp.getAutorizado() + "]");

			return resp.getAutorizado();

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error al autorizar.", e);
		}

		return false;
	}

	// -----------------------------------------------------------------

	/**
	 * Determina si el usuario tiene permitido el acceso a la url
	 * 
	 * @param session
	 *            - Sesion del usuario
	 * @param nurl
	 *            - url a comparar
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Boolean autorizarUrl(Session session, String nurl) {

		String login = (String) session.getAttribute("login");
		if (!StringUtils.esNoVacio(login, nurl)) {
			return false;
		}

		nurl = nurl.replace(".do.pdf", ".do").replace(".do_pdf", ".do").replace(".do.xls", ".do").replace(".do_xls", ".do").replace(".do_xml", ".do");

		// ---------------------

		XPathFactory factory = XPathFactory.newInstance();
		XPath xPath = factory.newXPath();
		File xmlDocument = null;

		String filename = getDiskPathForResource("WEB-INF/users.xml");

		try {
			xmlDocument = new File(filename);
			if (xmlDocument.exists()) {
				InputSource inputSource = new InputSource(new FileInputStream(xmlDocument));
				String consulta = xPath.evaluate("count(//services/service[url='" + nurl + "' and @name = //user[@login='" + login + "']/service/@name])", inputSource);
				return new Integer(consulta) > 0;
			}
		} catch (Exception e) {
			SimpleLogger.setError("Error al realizar la autorizacion Interna");
		}

		// -------------------

		try {
			List<String> list;
			if (session.getAttribute(URLS_AUTORIZAR) != null) {
				list = (List<String>) session.getAttribute(URLS_AUTORIZAR);
			} else {
				list = obtenerMapUrls(login);
				session.setAttribute(URLS_AUTORIZAR, list);
			}

			if (list != null) {
				return list.contains(nurl);
			}

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error al autorizar.", e);
		}

		return false;
	}

	private List<String> obtenerMapUrls(String login) {

		TipoElementoSalidaobtenerUrlServicios[] urlsValidas = UsuarioServicio.getInstance().obtenerUrlsServicio(login, NOMBRE_APLICACION);

		if (urlsValidas != null) {
			List<String> listUrls = new ArrayList<String>();
			for (TipoElementoSalidaobtenerUrlServicios elemento : urlsValidas) {
				listUrls.add(elemento.getUrl_servicio());
			}
			return listUrls;
		}
		return null;

	}

	// -----------------------------------------------------------------
	/**
	 * Retorna el resultado de validacion de una pagina.
	 * 
	 * @param request
	 * @return
	 */
	public ResultadoValidacion validar(Request request) {

		ResultadoValidacion resultadoValidacion = null;
		ValidacionAccion validacionAccion = null;

		String nameclass = null;
		try {
			nameclass = PAQUETE_VALIDACION + ".Validacion";
			validacionAccion = (ValidacionAccion) Class.forName(nameclass).newInstance();
			resultadoValidacion = validacionAccion.validar(request);

			SimpleLogger.setDebug("validacion: " + nameclass + " : " + resultadoValidacion);
		} catch (Exception e) {
			SimpleLogger.setInfo("No se puede realizar la validacion general: " + nameclass, e);
		}

		if (resultadoValidacion == null || resultadoValidacion.isPermitido()) {

			nameclass = PAQUETE_VALIDACION + ".Validacion" + request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]", "_").replaceAll("[.]", "_").replaceAll("[-]", "");

			String[] searchList = { "_xdo", "_xxdo", "_do_pdf" };
			String[] replacementList = { "_do", "_do", "_do" };

			org.apache.commons.lang3.StringUtils.replaceEachRepeatedly(nameclass, searchList, replacementList);

			nameclass = nameclass.replace("_xdo", "_do").replace("_xxdo", "_do").replace("_do_pdf", "_do");

			try {
				validacionAccion = (ValidacionAccion) Class.forName(nameclass).newInstance();
				ResultadoValidacion resultadoValidacionEspecifica = validacionAccion.validar(request);

				if (validacionAccion instanceof DefaultBaseAccion) {
					request.setAttribute("ACORE.BASECORE", "TRUE");
				}

				if (resultadoValidacion == null) {
					resultadoValidacion = resultadoValidacionEspecifica;
				} else {

					resultadoValidacion.setPermitido(resultadoValidacionEspecifica.isPermitido());
					resultadoValidacion.setSiguientePagina(resultadoValidacionEspecifica.getSiguientePagina());

					if (resultadoValidacionEspecifica.getMensaje() != null) {

						if (resultadoValidacion.getMensaje() != null) {
							resultadoValidacion.setMensaje(resultadoValidacion.getMensaje() + " - " + resultadoValidacionEspecifica.getMensaje());
						} else {
							resultadoValidacion.setMensaje(resultadoValidacionEspecifica.getMensaje());
						}
					}

					resultadoValidacion.getMensajeCampo().putAll(resultadoValidacionEspecifica.getMensajeCampo());
				}

				SimpleLogger.setDebug("validacion: " + nameclass + " : " + resultadoValidacion);
			} catch (ClassNotFoundException e) {
				SimpleLogger.setDebug("No se encuentra el archivo de validacion: " + nameclass);

			} catch (Exception e) {
				SimpleLogger.setError("No se puede realizar la validacion: " + nameclass, e);
			}

		}

		if (resultadoValidacion == null) {
			resultadoValidacion = new ResultadoValidacion();
		}

		return resultadoValidacion;
	};

	// -----------------------------------------------------------------

	static Map<String, Boolean> mapatransaccional = new HashMap<String, Boolean>();

	/**
	 * Determina si una url es una pagina transaccional
	 * 
	 * @param url
	 * @return
	 */
	public boolean esTransaccional(Request req, String url) {

		String validarTickets = ParametrosInicio.getProperty("validar.tickets");

		if ("false".equals(validarTickets)) {
			return false;
		}

		Boolean est = mapatransaccional.get(url);
		if (est != null) {
			return est.booleanValue();
		}

		XPathFactory factory = XPathFactory.newInstance();
		XPath xPath = factory.newXPath();
		File xmlDocument = null;
		String filename;
		// String filename = new
		// File(AutenticacionServicio.class.getResource("/").getPath()).getParentFile().getAbsolutePath()+
		// "/actions.xml";
		filename = getDiskPathForResource("WEB-INF/actions.xml");
		try {
			xmlDocument = new File(filename);
			InputSource inputSource = new InputSource(new FileInputStream(xmlDocument));
			String consulta = xPath.evaluate("/actions/action[@url='" + url + "' and @transactional='yes']/@url", inputSource);
			if (consulta != null && consulta.length() > 0) {
				SimpleLogger.setDebug("La url " + url + " es transaccional.");

				mapatransaccional.put(url, Boolean.valueOf(true));

				return true;
			}
		} catch (Exception e) {
			SimpleLogger.setError("no se puede saber si la url es transaccional. " + url);
		}

		SimpleLogger.setDebug("La url " + url + " No es transaccional.");

		mapatransaccional.put(url, Boolean.valueOf(false));

		return false;
	}

	// -----------------------------------------------------------------

	/**
	 * Determina si una url es una pagina se guarda en cache
	 * 
	 * @param url
	 * @return
	 */
	public String getCacheId(Request request, String url) {

		XPathFactory factory = XPathFactory.newInstance();
		XPath xPath = factory.newXPath();
		File xmlDocument = null;

		try {
			String filename = getDiskPathForResource("WEB-INF/actions.xml");
			filename = filename.replaceAll("%20", " ");

			xmlDocument = new File(filename);
			InputSource inputSource = new InputSource(new FileInputStream(xmlDocument));
			String consulta = xPath.evaluate("/actions/action[@url='" + url + "' and @cache='yes']/@url", inputSource);

			if (consulta != null && consulta.length() > 0) {

				StringBuffer parameters = new StringBuffer("");
				Enumeration<?> e = request.getParameterNames();

				while (e.hasMoreElements()) {
					String s_clave = e.nextElement().toString();
					String s_aux = request.getParameter(s_clave);

					if (s_clave == null) {
						s_clave = "";
					}
					s_aux = s_aux.trim();
					inputSource = new InputSource(new FileInputStream(xmlDocument));
					String cc = "/actions/action[@url='" + url + "' and @cache='yes']/parameter[@name='" + s_clave + "']/@name";
					String consultaparametro = xPath.evaluate(cc, inputSource);

					if (consultaparametro != null && consultaparametro.trim().length() > 0) {
						consultaparametro = consultaparametro.trim();

						if (s_clave.trim().equals(consultaparametro.trim())) {
							parameters.append("[" + s_clave + ":" + s_aux + "]");
						}
					}
				}

				return url + parameters.toString();
			}

		} catch (Exception e) {
			SimpleLogger.setError("Error al leer archivo actions.xml", e);
		}

		return null;
	}

	public static String getEndpoint() {

		if (AutenticacionServicio.endpoint == null) {

			AutenticacionServicio.endpoint = ParametrosInicio.getProperty("osmoautenticador.endpoint");

			if (AutenticacionServicio.endpoint == null) {
				try {
					XPathFactory factory = XPathFactory.newInstance();
					XPath xPath = factory.newXPath();
					File xmlDocument = null;
					String filename;
					filename = getDiskPathForResource("WEB-INF/actions.xml");
					xmlDocument = new File(filename);
					InputSource inputSource = new InputSource(new FileInputStream(xmlDocument));
					String autenticadorendpoint = xPath.evaluate("/actions/osmo-autenticador/@endpoint", inputSource);
					if (autenticadorendpoint != null && autenticadorendpoint.trim().length() > 0) {
						AutenticacionServicio.endpoint = autenticadorendpoint;
					}
				} catch (Exception e) {
				}
			}
		}

		if (AutenticacionServicio.endpoint == null) {
			SimpleLogger.setWarn("No se pudo obtener el endPoint del osmoautenticador. Autenticacion deshabilitada.");
		}

		return AutenticacionServicio.endpoint;
	}

	// static final String DISKLOCATION_MODE__RELATIVETOCLASSPATH =
	// "RELATIVETOCLASSPATH";
	// static final String DISKLOCATION_MODE__LOADEDBYSERVLET =
	// "LOADEDBYSERVLET";
	// static String DISKLOCATION_MODE = DISKLOCATION_MODE__RELATIVETOCLASSPATH;

	private static String getDiskPathForResource(String relativeToContextPath) {
		return ContextInfo.getInstance().getDiskPathForResource(relativeToContextPath);
	}

	public static void setEndpoint(String endpoint) {
		AutenticacionServicio.endpoint = endpoint;
	}

	// -------------------------------------------------

	@SuppressWarnings("rawtypes")
	public Mensaje cambiarClave(String login, String clave, String clave_nueva) {
		return cambiarClave(login, clave, "1", clave_nueva);
	}

	// -------------------------------------------------

	@SuppressWarnings("rawtypes")
	public Mensaje cambiarClave2(String login, String clave, String clave_nueva) {
		return cambiarClave(login, clave, "2", clave_nueva);
	}

	// -------------------------------------------------

	@SuppressWarnings("rawtypes")
	public Mensaje cambiarClave(String login, String clave, String tipoclave, String clave_nueva) {

		if (login != null)
			login = login.toLowerCase();

		Mensaje mensaje = new Mensaje();

		URL url;
		try {
			url = new URL(getEndpoint() + SERVICES_WS_CAMBIAR_CLAVE);

			WSCambiarClaveSoapBindingStub stub = new WSCambiarClaveSoapBindingStub(url, null);

			SolicitudCambiarClave solicitudCambiarClave = new SolicitudCambiarClave();
			solicitudCambiarClave.setLogin(login);
			solicitudCambiarClave.setAplicacion(NOMBRE_APLICACION);
			solicitudCambiarClave.setClave(clave);
			solicitudCambiarClave.setTipoclave(tipoclave);
			solicitudCambiarClave.setClave_nueva(clave_nueva);

			RespuestaCambiarClave rcc = stub.cambiarclave(solicitudCambiarClave);
			mensaje.setMensaje(rcc.getMensaje());
			mensaje.setExitoso(rcc.getExitoso());
			mensaje.setEstado("EJECUTADO");

		} catch (RemoteException e) {
			mensaje.setMensaje("Por favor, intente mas tarde.");
			mensaje.setEstado("REMOTE_ERROR");
			mensaje.setExitoso(false);
		}

		catch (Exception e) {
			mensaje.setMensaje("Por favor, consulte con el administrador.");
			mensaje.setEstado("REMOTE_ERROR");
			mensaje.setExitoso(false);
		}
		return mensaje;
	}

	// -------------------------------------------------
	// -------------------------------------------------

	public static class CaptchaResp {
		Boolean success;
	}

	Map<String, Session> sesionesUsuario = new HashMap<>();

	public Boolean autenticacion(Request request, Response response, SourceResolver resolver, ContentHandler contentHandler) {

		Boolean isAutorizado = false;

		try {
			Session session = request.getSession();

			// ------------- Autenticacion

			String auth_username = request.getParameter("auth_username");
			String auth_password = request.getParameter("auth_password");

			// si el valor es cifrado.. decifrarlo
			try {
				auth_password = SecureJsonUtils.ds(auth_password);
				auth_username = SecureJsonUtils.ds(auth_username);
			} catch (Exception e) {
			}

			// esto se utiliza para forzar a recargar los datos de sesion del usuario por peticion
			String autenticarNuevamente = request.getParameter("autenticarNuevamente");
			boolean forzarAutenticacion = false;
			if ("SI".equals(autenticarNuevamente)) {
				forzarAutenticacion = true;
			}

			boolean aprobado = false;

			String ldap_auth = request.getParameter("ldap_auth");
			
			// autenticacion por login
			if (auth_username != null && !StringUtils.esVerdad(ldap_auth)) {
				limpiarSesion(session);
				auth_username = auth_username.toLowerCase();

				aprobado = autenticar(auth_username, auth_password, request.getParameter("auth_conf"));
				SimpleLogger.setDebug("Autenticado: " + auth_username + "/" + aprobado);
			}

			// autenticacion por HTSAutenticacion
			{

				String auth_token = request.getParameter("auth_token");

				// validar el token ()
				if (auth_username == null && auth_token != null) {
					auth_username = obtenerLoginPorToken(auth_token);

					if (auth_username != null) {
						sesionesUsuario.remove(auth_username);
						aprobado = true;
					}
				}

			}
			
			// autenticacion por WebDataSSO
			{
				String sso_token = request.getParameter("sso_token");
				if (StringUtils.isNotBlank(sso_token) && auth_username == null ){
					SessionData sd = obtenerLoginSSO(sso_token);
					if (sd != null){						
						auth_username = sd.getUser().getAccountName();
					}
					if (auth_username != null) {
						sesionesUsuario.remove(auth_username);
						aprobado = true;
					}
				}
			}
			
			// autenticacion por LDAP
			{
				if (StringUtils.esVerdad(ldap_auth)){
					LDAPUser user = obtenerLoginLDAP(auth_username, auth_password);
					if (user != null){						
						auth_username = user.getName();
					}
					if (auth_username != null) {
						sesionesUsuario.remove(auth_username);
						aprobado = true;
					}
				}
			}

			// verifica si tiene recaptha

			String recaptcha_sid = StringUtils.trimToNull(ParametrosInicio.getProperty("recaptcha.secret"));

			String recaptcha_intentos = StringUtils.trimToNull(ParametrosInicio.getProperty("recaptcha.intentos"));
			
			if (aprobado && recaptcha_sid != null) {

				if (solicitarRecaptcha(request)){
					try {
						
						Map<String, List<Object>> params = new HashMap<>();
						
						CallPage.addParam(params, "secret", recaptcha_sid);
						CallPage.addParam(params, "response", request.getParameter("g-recaptcha-response"));
						
						Map<String, String> headers = new HashMap<>();
						CaptchaResp resp = new Gson().fromJson(new CallPage().callPost("https://www.google.com/recaptcha/api/siteverify", params, headers, false), CaptchaResp.class);
						
						aprobado = resp.success;
					} catch (Exception e) {
						SimpleLogger.setError("Errror en validacion g-recaptcha-response", e);
						aprobado = false;
					}
				}
			}

			String endPointG3A = ParametrosInicio.getProperty(AutenticacionServicioG3A.G3A_ENPOINT);

			boolean autenticacion = false;

			if (StringUtils.esNoVacio(endPointG3A) && session.getAttribute(LOGIN_ATRIBUTO) != null && !forzarAutenticacion) {
				AutenticacionServicioG3A.getInstance().validarSSID(request);
			}

			if ((StringUtils.esNoVacio(endPointG3A) && session.getAttribute(LOGIN_ATRIBUTO) == null) || forzarAutenticacion) {
				request.setAttribute("autenticarNuevamente", "NO");
				AutenticacionServicioG3A.getInstance().autenticacion(request);

				if (session.getAttribute(LOGIN_ATRIBUTO) != null) {
					autenticacion = true;
					try {
						session.setAttribute("OSM-INIT-SESSION", XSPUtil.getSourceContents("cocoon:/session/init.ser?auth_username=" + auth_username, null, resolver));
					} catch (Exception __e) {
						SimpleLogger.setError("No se puede incluir el servicio: 'cocoon:/session/init.ser?auth_username=" + auth_username + "'", __e);
					}
				}
			}

			if (!aprobado && session.getAttribute(LOGIN_ATRIBUTO) == null) {

				try {
					XSPUtil.getSourceContents("cocoon:/session/autenticacion.ser", null, resolver);
				} catch (Exception __e) {
					SimpleLogger.setError("No se puede incluir el servicio: 'cocoon:/session/autenticacion.ser", __e);
				}

				if (session.getAttribute(LOGIN_ATRIBUTO) != null) {
					String servicio = "cocoon:/session/init.ser?auth_username=" + auth_username;
					try {
						session.setAttribute("OSM-INIT-SESSION", XSPUtil.getSourceContents(servicio, null, resolver));
					} catch (Exception __e) {
						SimpleLogger.setError("No se puede incluir el servicio: '" + servicio + "'", __e);
					}
				}
			}

			if (aprobado) {
				
				mapIntentosFallidos.put(CocoonUtils.getRemoteAddr(request), 0);
				
				SimpleLogger.setDebug("Creando session: " + auth_username);
				session.setAttribute(LOGIN_ATRIBUTO, auth_username);
				session.setAttribute("gestor_ventanilla", new GestorVentanilla());

				try {
					Session ss = sesionesUsuario.get(auth_username);
					if (ss != null) {
						ss.removeAttribute(LOGIN_ATRIBUTO);
					}
				} catch (Exception e) {
				}

				sesionesUsuario.put(auth_username, session);

				String ip = CocoonUtils.getRemoteAddr(request);

				session.setAttribute("REQUEST.REMOTE.ADDR", ip);

				try {
					session.setAttribute("OSM-INIT-SESSION", XSPUtil.getSourceContents("cocoon:/session/init.ser?auth_username=" + auth_username, null, resolver));
				} catch (Exception __e) {
					SimpleLogger.setError("No se puede incluir el servicio: 'cocoon:/session/init.ser?auth_username=" + auth_username + "'", __e);
				}

			} else {
				
				if (auth_username != null) {

					try {
						XSPUtil.getSourceContents("cocoon:/session/fail.ser?auth_username=" + auth_username, null, resolver);
					} catch (Exception __e) {
						SimpleLogger.setError("No se puede incluir el servicio: 'cocoon:/session/fail.ser?auth_username=" + auth_username + "'", __e);
					}

					session.removeAttribute(LOGIN_ATRIBUTO);

					session.setAttribute("INVALIDATE_ACCESS", Boolean.TRUE);

				}
			}

			if (session.getAttribute(LOGIN_ATRIBUTO) != null && session.getAttribute("gestor_ventanilla") == null) {
				session.removeAttribute(LOGIN_ATRIBUTO);
				SimpleLogger.setWarn("Se ha cerrado la session por inconsistencias en Session." + aprobado);
			}

			// *********************************************************************************************
			// Autorizacion

			boolean cambioUbicacion = false;

			String urlrequest = request.getRequestURI().substring(request.getContextPath().length());

			String navegador_cliente = ValidarTipoDato.validateMaxSize(request.getHeader("user-agent"), USER_AGENT_SIZE);
			if (navegador_cliente == null) {
				navegador_cliente = ValidarTipoDato.validateMaxSize(request.getHeader("USER-AGENT"), USER_AGENT_SIZE);
			}

			if (session.getAttribute(LOGIN_ATRIBUTO) != null && !isAutorizado) {

				String host_attr = CocoonUtils.getRemoteAddr(request) + "-" + navegador_cliente;

				if (session.getAttribute(HOST_ATRIBUTO) == null) {
					session.setAttribute(HOST_ATRIBUTO, host_attr);
				} else {
					if (CocoonUtils.getRemoteAddr(request).equals("127.0.0.1")) {
						host_attr = StringUtils.trimToEmpty((String) session.getAttribute(HOST_ATRIBUTO));
					}
				}

				// TODO: SE COMENTA MIENTRAS TANTO
				if (true) {// if (!isValidarIp() || host_attr.equals((String) session.getAttribute(HOST_ATRIBUTO))) {
					isAutorizado = autorizarConCache(request.getSession(), urlrequest);
				} else {
					cambioUbicacion = true;
					SimpleLogger.setError("No se ha autorizado la Session, el usuario ha cambiado de ip. Actual: " + host_attr + ", Inicial: " + session.getAttribute(HOST_ATRIBUTO));
				}

			}

			if (!cambioUbicacion) {

				if (ParametrosInicio.ISDEBUG && (request.getRequestURI().indexOf(".xdo") > 0 || request.getRequestURI().indexOf(".xxdo") > 0)) {
					isAutorizado = true;
				}

				if (isAutorizado) {
					try {
						String osm_contentvalidacionpage = XSPUtil.getSourceContents("cocoon:/session/validacion.ser", resolver);
						session.setAttribute("OSM-VALIDACION", osm_contentvalidacionpage);

					} catch (Exception e) {
						SimpleLogger.setWarn("No se puede cargar el contenido de la validacion");
					}
				} else {

					try {
						XSPUtil.getSourceContents("cocoon:/session/autorizacion.ser", null, resolver);
					} catch (Exception __e) {
						SimpleLogger.setError("No se puede incluir el servicio: 'cocoon:/session/autorizacion.ser", __e);
					}

					isAutorizado = session.getAttribute(AUTORIZADO_ATRIBUTO) == null ? false : (Boolean) session.getAttribute(AUTORIZADO_ATRIBUTO);
				}

				if (StringUtils.esNoVacio(endPointG3A) && session.getAttribute(LOGIN_ATRIBUTO) != null && isAutorizado) {

					// Se debe evitar que se envien dos peticiones con el mismo token a G3A
					synchronized (session) {

						SessionTicket sessionTicket = AutenticacionServicioG3A.getInstance().obtenerTicket(request, autenticacion);
						RespuestaAutorizarPTX resp = AutenticacionServicioG3A.getInstance().autorizar(sessionTicket.getSsid(), sessionTicket.getValorTicket(), sessionTicket.getIdFilial(), sessionTicket.getIpUsuario(), navegador_cliente);

						if (resp == null) {
							session.removeAttribute(LOGIN_ATRIBUTO);
						} else {
							if (!resp.isAutorizado()) {
								session.removeAttribute(LOGIN_ATRIBUTO);
							} else {
								AutenticacionServicioG3A.getInstance().actualizarTicket(session, resp.getTicket());
							}
						}

					}
				}

			}

			SimpleLogger.setDebug("isAutorizado ws:" + isAutorizado);

			// -- Por login

			String l_login = (String) session.getAttribute(LOGIN_ATRIBUTO);

			if (l_login == null) {
				isAutorizado = false;
			}

			// --

			if (isAutorizado && endPointG3A != null) {
				isAutorizado = validarTiempoInactividad(session);
			}

			// --

			if (isAutorizado) {
				session.setAttribute(ULTIMO_ACCESO, System.currentTimeMillis());
			}

			// --

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un Error Inesperado (AutenticacionServicio.autenticacion)", e);

			return null;

		}

		if (isAutorizado && StringUtils.esVerdad(ParametrosInicio.getProperty("tokenid.activo"))) {

			if (!"127.0.0.1".equals(request.getRemoteAddr())) {

				Session session = request.getSession();
				String tokenid = (String) session.getAttribute("STOKENID");

				Cookie cookie = getCookie(request, "STOKENID");

				if (tokenid != null && (cookie == null || !cookie.getValue().equals(tokenid))) {
					SimpleLogger.setInfo("AUT: El STOKENID no coincide: " + tokenid + " != " + (cookie == null ? "" : cookie.getValue()));
					isAutorizado = false;
				}

				String code = StringUtils.randomString(32);

				session.setAttribute("STOKENID", code);

				Cookie ncookie = response.createCookie("STOKENID", code);
				ncookie.setPath(request.getContextPath() + "/");
				ncookie.setMaxAge(60 * 10);
				response.addCookie(ncookie);
			}

		}

		return isAutorizado;
	}

	public static class Auth {
		public String code, secretid;
	}

	private String obtenerLoginPorToken(String code) {
		try {
			Auth auth = new Auth();
			auth.secretid = ParametrosInicio.getProperty("htsauth.secretid");
			auth.code = code;

			String auth_endpoint = ParametrosInicio.getProperty("htsauth.endpoint");

			if (StringUtils.isBlank(auth_endpoint) || StringUtils.isBlank(auth.secretid)) {
				return null;
			}

			// ------
			CallPage cp = new CallPage();
			SessionData sd = cp.callJSON(auth_endpoint + "/rest/authentication/data", auth, SessionData.class);

			return sd.getUser().getEmail();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private SessionData obtenerLoginSSO(String sso_token) {
		String ssoEndpoint = ParametrosInicio.getProperty("webdatasso.endpoint");
		String seed = ParametrosInicio.getProperty("webdatasso.seed");
		Integer id_aplicacion = Integer.parseInt(ParametrosInicio.getProperty("webdatasso.id_aplicacion"));
		SSOAuth auth = new SSOAuth(id_aplicacion, seed, sso_token);
		
		CallPage cp = new CallPage();
		return cp.callJSON(ssoEndpoint + "/rest/auth/check_auth", auth, SessionData.class);
	}
	
	public static LDAPUser obtenerLoginLDAP(String userDN, String passwd){
		String host = ParametrosInicio.getProperty("ldap.host");
		Integer port = Integer.parseInt(ParametrosInicio.getProperty("ldap.port"), 10);
		Boolean sslRequired = StringUtils.esVerdad(ParametrosInicio.getProperty("ldap.ssl"));
		String userBaseDN = ParametrosInicio.getProperty("ldap.userbasedn");
		String roleBaseDN = ParametrosInicio.getProperty("ldap.rolebasedn");
		Boolean sAMAccount = StringUtils.esVerdad(ParametrosInicio.getProperty("ldap.sAMAccount"));
		
		LDAPConnectionFactory connectionFactory = new LDAPConnectionFactory(host, port, userDN, passwd);
		
		LDAPLookupConfig lookupConfig = new LDAPLookupConfig(userBaseDN, "uid", null, roleBaseDN);
		
		LDAPAuthenticator authenticator = new LDAPAuthenticator(connectionFactory, lookupConfig);
		
		return authenticator.authenticate(userDN, passwd, sslRequired, sAMAccount);
	}

	private Cookie getCookie(Request request, String string) {

		Cookie cookies[] = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(string)) {
				return cookie;
			}
		}

		return null;
	}

	public boolean validarTiempoInactividad(Session session) {

		Long lasttime = (Long) session.getAttribute(AutenticacionServicio.ULTIMO_ACCESO);

		if (lasttime == null) {
			lasttime = System.currentTimeMillis();
		}

		Long tiempoMaximoInactividad = 1000000L;

		Long maxtime = (Long) session.getAttribute(AutenticacionServicio.MAX_TIEMPO_INACTIVIDAD);

		if (maxtime == null) {

			tiempoMaximoInactividad = valor_max_tiempo_inactividad * 1000L;

			if (ParametrosInicio.getProperty(AutenticacionServicioG3A.G3A_ENPOINT) != null) {
				tiempoMaximoInactividad = AutenticacionServicioG3A.getInstance().obtenerTiempoMaximoSesion() * 1000L;
			}

			session.setAttribute(AutenticacionServicio.MAX_TIEMPO_INACTIVIDAD, tiempoMaximoInactividad);

		} else {
			tiempoMaximoInactividad = maxtime.longValue();
		}

		return System.currentTimeMillis() < lasttime + tiempoMaximoInactividad;
	}

	/**
	 * Realiza la autenticación de la url pasada y guarda los permisos en cache
	 * 
	 * @param session
	 *            sesion activa del usuario a validar
	 * @param urlrequest
	 *            url a validar
	 * @return true si el usuario tiene permisos para esa url, false de lo contrario
	 */
	@SuppressWarnings("unchecked")
	public Boolean autorizarConCache(Session session, String urlrequest) {
		Boolean isAutorizado;
		// --- Mapa de las autorizacion por url
		Map<String, Boolean> mapaAutorizacion = (Map<String, Boolean>) session.getAttribute(MAPA_AUTORIZACION);

		if (mapaAutorizacion == null) {
			mapaAutorizacion = new HashMap<String, Boolean>();
			session.setAttribute(MAPA_AUTORIZACION, mapaAutorizacion);
		}

		Boolean isAutorizadoMap = mapaAutorizacion.get(urlrequest);

		if (isAutorizadoMap != null) {
			isAutorizado = isAutorizadoMap;
		} else {
			isAutorizado = autorizar((String) session.getAttribute(LOGIN_ATRIBUTO), urlrequest);
			mapaAutorizacion.put(urlrequest, isAutorizado);
		}
		return isAutorizado;
	}

	private String obtenerDestinoConParametros(String paginaDestino, Request request) {

		if (paginaDestino == null || request == null) {
			return null;
		}

		Map<String, String> parametros = ServletUtils.getMapRequest(request);

		StringBuffer bufer = new StringBuffer(paginaDestino);

		if (paginaDestino.endsWith(".pub") || paginaDestino.endsWith(".do") || paginaDestino.endsWith(".xdo") || paginaDestino.endsWith(".xxdo")) {
			bufer.append("?");
		} else {
			bufer.append("&");
		}

		String urlRetorno = parametros.get("ret");

		if (StringUtils.esNoVacio(urlRetorno)) {
			bufer.append("ret=" + urlRetorno + "&");
		}

		String idFilial = parametros.get("FIL"); // idAplicacion

		if (StringUtils.esNoVacio(idFilial)) {
			bufer.append("FIL=" + idFilial + "&");
		}

		String naturaleza = parametros.get("naturaleza"); // naturaleza

		if (StringUtils.esNoVacio(naturaleza)) {
			bufer.append("naturaleza=" + naturaleza + "&");
		}

		return bufer.toString();
	}

	@SuppressWarnings("unchecked")
	public static void adicionarMensaje(Request request, String mensaje) {

		if (request != null) {

			Session ss = request.getSession(false);

			if (ss != null) {

				HashSet<String> mensajes = (HashSet<String>) ss.getAttribute("osm_mensaje");

				if (mensajes == null) {
					mensajes = new HashSet<String>();
				}

				mensajes.add(mensaje);

				ss.setAttribute("osm_mensaje", mensajes);
			}
		}
	}

	public static void borrarMensajes(Request request) {
		if (request != null) {
			Session ss = request.getSession(false);
			if (ss != null) {
				ss.removeAttribute("osm_mensaje");
			}
		}
	}

	// -------------------------------------------------------------------

	private static Map<String, PageLog> PAGE_STATUS = new HashMap<String, PageLog>();

	public static void addLogPage(String servletPage, long tiempo) {

		PageLog pageLog = PAGE_STATUS.get(servletPage);

		if (pageLog == null) {
			pageLog = new PageLog();
			PAGE_STATUS.put(servletPage, pageLog);
		}

		pageLog.adicionar(servletPage, tiempo);
	}

	public static List<PageLog> getPageLog() {

		List<PageLog> ret = new ArrayList<PageLog>();

		Set<String> keys = PAGE_STATUS.keySet();

		for (String key : keys) {
			ret.add(PAGE_STATUS.get(key));
		}

		return ret;
	}

	public static void clearLog() {
		PAGE_STATUS.clear();
	}

	@SuppressWarnings("unchecked")
	public static void limpiarSesion(Session session) {

		List<String> atributo = new ArrayList<String>();

		for (Object name : Collections.list(session.getAttributeNames())) {
			atributo.add((String) name);
		}

		for (String string : atributo) {
			session.removeAttribute(string);
		}
	}

	public void setRedirectAbsoluto(boolean redirectAbsoluto) {
		this.redirectAbsoluto = redirectAbsoluto;
	}

	public boolean isValidarIp() {
		return validarIp;
	}

	public void setValidarIp(boolean validarIp) {
		this.validarIp = validarIp;
	}
	
	public Integer getIntentosFallidos(Request request){
		return getIntentosFallidosHost(CocoonUtils.getRemoteAddr(request));
	}
	
	public Integer getIntentosFallidosHost(String host){
		Integer intentosFallidos = mapIntentosFallidos.get(host);
		if (intentosFallidos == null){
			intentosFallidos = 0;
			mapIntentosFallidos.put(host, intentosFallidos);
		}
		return intentosFallidos;		
	}
	
	public Boolean solicitarRecaptcha(Request request){
		return solicitarRecaptchaHost(CocoonUtils.getRemoteAddr(request));
	}
	
	public Boolean solicitarRecaptchaHost(String host){
		String recaptcha_sid = StringUtils.trimToNull(ParametrosInicio.getProperty("recaptcha.secret"));

		String recaptcha_intentos = StringUtils.trimToNull(ParametrosInicio.getProperty("recaptcha.intentos"));
		
		if (recaptcha_sid != null){
			if (recaptcha_intentos != null){
				try {					
					Integer maxIntentos = Integer.parseInt(recaptcha_intentos, 10);
					return (getIntentosFallidosHost(host) >= maxIntentos);
				}catch (Exception e){
					SimpleLogger.setError("Ha ocurrido un error", e);
					return true;
				}
			}
			return true;
		}
		return false;
	}
	
	
	public void aumentarIntentosFallidos(Request request){
		aumentarIntentosFallidos(CocoonUtils.getRemoteAddr(request));
		
	}
	public void aumentarIntentosFallidos(String host){
		Integer intentosFallidos = getIntentosFallidosHost(host);
		intentosFallidos++;
		mapIntentosFallidos.put(host, intentosFallidos);
		
	}

}
