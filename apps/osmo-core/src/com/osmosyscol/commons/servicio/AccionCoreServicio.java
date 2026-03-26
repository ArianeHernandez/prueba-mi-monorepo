package com.osmosyscol.commons.servicio;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.avalon.framework.component.ComponentManager;
import org.apache.avalon.framework.parameters.Parameters;
import org.apache.cocoon.components.language.markup.xsp.XSPObjectHelper;
import org.apache.cocoon.components.language.markup.xsp.XSPUtil;
import org.apache.cocoon.environment.Request;
import org.apache.cocoon.environment.Response;
import org.apache.cocoon.environment.Session;
import org.apache.cocoon.environment.SourceResolver;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.CocoonUtils;
import com.osmosyscol.commons.utils.GestorVentanilla;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class AccionCoreServicio {

	// *******************************************************************************************************
	// *******************************************************************************************************

	public static void accionPage(Request request, Response response, Map<Object, Object> objectModel, Parameters parameters, ComponentManager manager, SourceResolver resolver, ContentHandler contentHandler) throws SAXException {
		
		AccionCoreServicio ac = new AccionCoreServicio(request, response, objectModel, parameters, manager, resolver, contentHandler);

		long timeservice_osmcoresystem = System.currentTimeMillis();

		// -----------------------------------------------------------------

		ac.accion();

		// ------------------------------------------------------------------
		// Adiciona el tiempo total de la peticion al log de tiempos

		timeservice_osmcoresystem = System.currentTimeMillis() - timeservice_osmcoresystem;

		AutenticacionServicio.addLogPage(ac.serverRequest, timeservice_osmcoresystem);
		
	}

	// *******************************************************************************************************
	// *******************************************************************************************************

	private SourceResolver resolver;
	private ContentHandler contentHandler;
	private ComponentManager manager;
	private Parameters parameters;
	private Request request;
	private Session session;
	private Response response;
	private Map<Object, Object> objectModel;

	private String path;
	private String ipHost;
	private GestorVentanilla gestorventanilla;
	private String serverRequest;

	// ---------------------------------------
	// ---------------------------------------

	public AccionCoreServicio(Request request, Response response, Map<Object, Object> objectModel, Parameters parameters, ComponentManager manager, SourceResolver resolver, ContentHandler contentHandler) {

		this.resolver = resolver;
		this.contentHandler = contentHandler;
		this.manager = manager;
		this.parameters = parameters;
		this.response = response;
		this.request = request;
		this.objectModel = objectModel;
		this.session = request.getSession();

		// --------------------------------------

		request.setAttribute("OSM_OBJECTMODEL", this.objectModel);
		session.setAttribute("last-resolver", this.resolver);

		// ---------------------------------------

		ipHost = CocoonUtils.getRemoteAddr(request);

		// ---------------------------------------

		gestorventanilla = (GestorVentanilla) session.getAttribute("gestor_ventanilla");

		// ---------------------------------------

		serverRequest = parameters.getParameter("page", null);

		path = request.getServletPath();

		// ---------------------------------------

	}

	// ---------------------------------------
	// ---------------------------------------

	public void accion() throws SAXException {

		// ------------------------------------
		// Deternima No Cache

		String noCache = parameters.getParameter("no-cache", null);

		if (noCache != null) {
			response.setHeader("Cache-Control", "no-store,must-revalidate,no-cache");
			response.setDateHeader("Expires", 0);
			response.setHeader("X-Content-Type-Options", "nosniff");
			
			String xFrame = StringUtils.trimToEmpty(ParametrosInicio.getProperty("header.xframe_frame_options"));
			
			if(xFrame.isEmpty()){
				xFrame = "SAMEORIGIN";
			}
			
			response.setHeader("X-Frame-Options", xFrame);
			
			String content_security_policy = StringUtils.trimToEmpty(ParametrosInicio.getProperty("header.content_security_policy"));
			
			if(xFrame.isEmpty()){
				content_security_policy = "default-src 'self' 'unsafe-inline' 'unsafe-eval' *.google.com *.googleapis.com *.gstatic.com";
			}
			
			response.setHeader("x-xss-protection", "1; mode=block");
			response.setHeader("Content-Security-Policy", content_security_policy);
		}

		// ------------------------------------
		// Para las paginas "do_xls" se determina el nombre del recurso a "archivo.xls"

		if (path.contains("do_xls")) {
			response.setHeader("Content-Disposition", "attachment; filename=archivo.xls");
		}

		// -------------------------------------
		// Registra mutuamente la sesion de java con la sesion de cocoon

		HttpServletRequest rr = com.osmosyscol.commons.utils.CocoonUtils.getHttpServletRequest(request);

		if (session.getAttribute("java-session") == null) {
			rr.getSession().setAttribute("cocoon-session", session);
			session.setAttribute("java-session", rr.getSession());

			AutenticacionServicio.getInstance().inicializarServiciosJSONSession(request);
		}

		// ------------------------------------------------------------------
		// Se presentan los valores de la peticion.

		add("VERSION_APLICACION", AutenticacionServicio.VERSION_APLICACION);
		add("SESSION_ID", request.getSession().getId());
		add("CONTEXT_PATH", request.getContextPath());
		add("SYSTEM_TIME", System.currentTimeMillis());
		add("LOGIN_USER", session.getAttribute("login"));
		add("REQUESTURI", request.getRequestURI().substring(request.getContextPath().length()));
		add("STRING_TIME", StringUtils.getStringTime());
		add("STRING_DATE_FORMATTED", StringUtils.getStringTime("dd/MM/yyyy"));
		add("STRING_TIME_FORMATTED", StringUtils.getStringTime("EEEE, dd/MM/yyyy - hh:mm a"));
		add("CLIENT_IP", ipHost);
		add("BASEPATH", com.osmosyscol.commons.servlet.ContextInfo.getInstance().getDiskPathForResource(""));
		add("SERVERPATH", (request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()));
		add("CHARACTERENCODING", request.getCharacterEncoding());
		add("SERVERREQUEST", serverRequest);

		// ------------------------------------------------------------------
		// Se presentan los headers de la peticion

		startAdd("HEADERS");

		Enumeration<?> a = request.getHeaderNames();

		while (a.hasMoreElements()) {
			String name = (String) a.nextElement();

			startAdd("HEADER");
			{
				add("name", name.toLowerCase());
				add("value", request.getHeader(name));
			}
			endAdd("HEADER");
		}

		endAdd("HEADERS");

		// ------------------------------------------------------------------
		// Se carga el xsp de informacion General ( se carga siempre )

		try {
			XSPUtil.includeSource("cocoon:/public/general.ser?page=" + serverRequest, null, this.resolver, this.contentHandler);
		} catch (Exception __e) {
		}

		// ------------------------------------------------------------------
		// Se carga el xml con los mensajes de velidacion existentes

		try {
			if (session.getAttribute("OSM-VALIDACION") != null) {
				XSPUtil.includeString(session.getAttribute("OSM-VALIDACION").toString(), this.manager, this.contentHandler);
			}
		} catch (Exception __e) {
			SimpleLogger.setError("No se pude incluir: 'OSM-VALIDACION'", __e);
		}

		// ------------------------------------------------------------------
		// Se carga el xml ejecutado al iniciar sesion

		try {
			if (session.getAttribute("OSM-INIT-SESSION") != null) {
				XSPUtil.includeString(session.getAttribute("OSM-INIT-SESSION").toString(), this.manager, this.contentHandler);
			}
		} catch (Exception __e) {
			SimpleLogger.setError("No se pude incluir: 'OSM-INIT-SESSION'", __e);
		}

		// ------------------------------------------------------------------
		// Se carga el xsp de las paginas de sesion

		try {
			XSPUtil.includeSource("cocoon:/session/session.ser", null, this.resolver, this.contentHandler);
		} catch (Exception __e) {
			SimpleLogger.setError("No se pude incluir: 'cocoon:/session/session.ser'", __e);
		}

		// ------------------------------------------------------------------

		boolean osm_procesar_pagina = getBooleanAttr(request,"FCORE.PROCESAR");

		String cacheId = (String) request.getAttribute("FCORE.CACHESESION");
		boolean osm_guardarCacheSesion = getBooleanAttr(request,"FCORE.GUARDARCACHESESION");

		boolean osm_cacheTransaccional = getBooleanAttr(request,"FCORE.CACHETRANSACCIONAL");
		boolean osm_guardarCcheTransaccional = getBooleanAttr(request,"FCORE.GUARDARCACHETRANSACCIONAL");

		// --

		String osm_contentpage = null; // Contenido de la Pagina

		Boolean esError = false;

		// ------------------------------------------------------------------

		String osm_tmp_type = serverRequest.substring(serverRequest.lastIndexOf("."));

		if (osm_tmp_type.lastIndexOf(".do") >= 0) {
			osm_tmp_type = ".do";
		}
		String type_action = osm_tmp_type + "_action";

		String osm_url_page = "cocoon:" + serverRequest.substring(0, serverRequest.lastIndexOf(".")) + type_action;

		// --

		if (osm_procesar_pagina) {

			try {

				if(request.getAttribute("ACORE.BASECORE")!=null){
					osm_contentpage = XSPUtil.getSourceContents("cocoon:/core/accionbase.ser_action", this.resolver);
				}else{
					osm_contentpage = XSPUtil.getSourceContents(osm_url_page, this.resolver);
				}

			} catch (Exception e) {
				SimpleLogger.setError("Ha ocurrido un error al cargar el contenido de la pagina. " + osm_url_page, e);
				esError = true;
			}

		}

		// --

		if (osm_cacheTransaccional) {

			osm_contentpage = gestorventanilla.getContenido_memoria();
		}

		// --

		if (cacheId != null) {

			osm_contentpage = session.getAttribute(cacheId).toString();

			if (osm_guardarCacheSesion) {
				session.setAttribute(cacheId, osm_contentpage);
			}

		}

		// --

		if (osm_guardarCcheTransaccional) {
			gestorventanilla.setContenido_memoria(osm_contentpage);
		}

		// --------------------------------------------------------
		// Incluye el contenido de la pagina

		XSPUtil.includeString(osm_contentpage, this.manager, this.contentHandler);

		// ***********************************************************************************
		// Carga el componente de mensajes

		try {
			XSPUtil.includeSource("cocoon:/core/mensajes.ser", null, this.resolver, this.contentHandler);
		} catch (Exception __e) {
			SimpleLogger.setError("No se pude incluir: 'cocoon:/core/mensajes.ser'", __e);
		}

		// ***********************************************************************************
		// Si se debe cerrar sesion se carga el componente de invalidar sesion

		if (session.getAttribute("no-session") != null) {

			try {
				XSPUtil.includeSource("cocoon:/session/invalidate.ser", null, this.resolver, this.contentHandler);
			} catch (Exception __e) {
				SimpleLogger.setError("No se pude incluir: 'cocoon:/session/invalidate.ser'", __e);
			}
			session.invalidate();
		}

		// ------------------------------------------------------------------
		// ------------------------------------------------------------------

		startAdd("OSM_TICKET");
		{
			add("OLD-TICKET", request.get("osm_ticket"));
			add("NEW-TICKET", gestorventanilla.getTiquete());

			startAdd("TYPE");
			{
				add("ERROR", esError);
				add("INCLUDE", osm_procesar_pagina);
				add("RELOAD", osm_cacheTransaccional);
			}
			endAdd("TYPE");
		}
		endAdd("OSM_TICKET");

		// ------------------------------------------------------------------

	}

	// ********************************************************************************************

	private boolean getBooleanAttr(Request request, String attribute) {

		Object v = request.getAttribute(attribute);

		if (v != null) {
			return StringUtils.esVerdad(StringUtils.toString(v)).booleanValue();
		}

		return false;

	}

	// ********************************************************************************************

	public void add(String label, Object valor) throws SAXException {

		startAdd(label);
		XSPObjectHelper.xspExpr(contentHandler, StringUtils.toString(valor));
		endAdd(label);

	}

	// ********************************************************************************************
	// ********************************************************************************************

	private final AttributesImpl _xspAttr = new AttributesImpl();

	private void startAdd(String name) throws SAXException {
		this.contentHandler.startElement("", name, name, _xspAttr);
		_xspAttr.clear();
	}

	private void endAdd(String name) throws SAXException {
		this.contentHandler.endElement("", name, name);
	}

	// ********************************************************************************************
	// ********************************************************************************************

}
