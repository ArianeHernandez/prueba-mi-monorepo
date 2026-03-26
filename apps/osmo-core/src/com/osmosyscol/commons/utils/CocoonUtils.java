package com.osmosyscol.commons.utils;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.cocoon.environment.Request;
import org.apache.cocoon.environment.Session;
import org.apache.cocoon.environment.http.HttpEnvironment;

import com.osmosyscol.commons.logging.dto.InfoTransaccion;
import com.osmosyscol.commons.servicio.InfoTransaccionServicio;

public class CocoonUtils {

	public static HttpServletRequest getHttpServletRequest(Request request) {
		try {
			return (HttpServletRequest) ((Map<?, ?>) request.getAttribute("OSM_OBJECTMODEL")).get(HttpEnvironment.HTTP_REQUEST_OBJECT);
		} catch (Exception e) {
			return null;
		}
	}

	public static HttpSession getHttpSession(Request request) {

		try {
			HttpServletRequest r = (HttpServletRequest) ((Map<?, ?>) request.getAttribute("OSM_OBJECTMODEL")).get(HttpEnvironment.HTTP_REQUEST_OBJECT);
			return r.getSession();
		} catch (Exception e) {
			return null;
		}
	}

	public static Session getCocoonSession(HttpServletRequest request) {

		return getCocoonSession(request.getSession(false));
	}

	public static Session getCocoonSession(HttpSession session) {

		if (session == null) {
			return null;
		}

		return (Session) session.getAttribute("cocoon-session");
	}

	public static void osmSessionRequest(Request request, String name) {
		osmSessionRequest(request, name, "");
	}

	public static void osmSessionRequest(Request request, String name, String _default) {
		Session session = request.getSession();

		if (request.getParameter(name) != null) {
			session.setAttribute("var." + name, request.getParameter(name));
		} else if (session.getAttribute("var." + name) == null) {

			if (!"".equals(_default)) {
				session.setAttribute("var." + name, _default);
			}
		}
	}

	public static String getRemoteAddr(Request request) {

		String ip = null;

		// ----

		if (request != null) {

			if (ip == null) {
				ip = request.getHeader("X-FORWARDED-FOR");
			}
			
			if (ip == null) {
				ip = request.getHeader("x-forwarded-for");
			}

			if (ip == null) {
				ip = request.getHeader("REMOTE_HOST");
			}
			
			if (ip == null) {
				ip = request.getHeader("remote_host");
			}
			
			if (ip == null) {
				ip = request.getRemoteAddr();
			}

		} else {
			InfoTransaccion infoTransaccion = InfoTransaccionServicio.getInstance().getInfoTransaccion();
			ip = infoTransaccion.getIp();
		}

		// ----
		
		ip = ip.split(",")[0];

		return ValidarTipoDato.validateIP(ip);
	}

	public static String getRemoteAddr(HttpServletRequest request) {

		String ip = null;

		// ----

		if (request != null) {
			
			if (ip == null) {
				ip = request.getHeader("X-FORWARDED-FOR");
			}
			
			if (ip == null) {
				ip = request.getHeader("x-forwarded-for");
			}

			if (ip == null) {
				ip = request.getHeader("REMOTE_HOST");
			}
			
			if (ip == null) {
				ip = request.getHeader("remote_host");
			}
			
			if (ip == null) {
				ip = request.getRemoteAddr();
			}


		} else {
			InfoTransaccion infoTransaccion = InfoTransaccionServicio.getInstance().getInfoTransaccion();
			ip = infoTransaccion.getIp();
		}

		// ----
		
		ip = ip.split(",")[0];

		return ValidarTipoDato.validateIP(ip);
	}
	
	public static String getRemoteAddr(ServletRequest request) {

		String ip = null;

		// ----

		if (request != null) {
			ip = request.getRemoteAddr();

		} else {
			InfoTransaccion infoTransaccion = InfoTransaccionServicio.getInstance().getInfoTransaccion();
			ip = infoTransaccion.getIp();
		}

		// ----
		
		ip = ip.split(",")[0];

		return ValidarTipoDato.validateIP(ip);
	}

}