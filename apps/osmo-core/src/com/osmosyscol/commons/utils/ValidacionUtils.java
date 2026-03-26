package com.osmosyscol.commons.utils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import org.apache.cocoon.environment.Request;
import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.log.SimpleLogger;

public class ValidacionUtils {

	public static String getSessionValue(Request request, String parametro) {
		return getSessionValue(request, parametro, null, String.class);
	}

	public static String getSessionValue(Session session, String parametro) {
		return getSessionValue(session, parametro, null, String.class);
	}

	public static String getSessionValue(Request request, String parametro, String valor_defecto) {
		return getSessionValue(request, parametro, valor_defecto, String.class);
	}

	public static String getSessionValue(Session session, String parametro, String valor_defecto) {
		return getSessionValue(session, parametro, valor_defecto, String.class);
	}

	public static <T> T getSessionValue(Request request, String parametro, Class<T> tipoRsp) {
		return getSessionValue(request, parametro, null, tipoRsp);
	}

	public static <T> T getSessionValue(Session session, String parametro, Class<T> tipoRsp) {
		return getSessionValue(session, parametro, null, tipoRsp);
	}

	public static <T> T getSessionValue(Request request, String parametro, T valor_defecto, Class<T> tipoRsp) {
		try {
			String valor = StringUtils.toString(valor_defecto);
			CocoonUtils.osmSessionRequest(request, parametro, valor);
			return getSessionValue(request.getSession(), parametro, valor_defecto, tipoRsp);
		} catch (Exception e) {
			SimpleLogger.setError("Error en ValidacionUtils.getSessionValue", e);
		} finally {
			request.setAttribute(parametro, request.getSession().getAttribute("var." + parametro));
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getSessionValue(Session session, String parametro, T valor_defecto, Class<T> tipoRsp) {

		try {

			Object obj = session.getAttribute("var." + parametro);

			if (obj == null) {
				setSessionValue(session, parametro, valor_defecto);
				obj = valor_defecto;
			}

			String res = StringUtils.trimToNull(StringUtils.toString(obj));

			if (res != null) {

				if (tipoRsp == null || String.class.equals(tipoRsp)) {
					return (T) res;
				}

				if (Integer.class.equals(tipoRsp)) {
					return (T) new Integer(res);
				}

				if (BigDecimal.class.equals(tipoRsp)) {
					return (T) new BigDecimal(res);
				}

				if (Boolean.class.equals(tipoRsp)) {
					return (T) StringUtils.esVerdad(res);
				}

				if (Date.class.equals(tipoRsp)) {
					return (T) StringUtils.toDate(res);
				}

			}

			// --------

		} catch (Exception e) {
			SimpleLogger.setError("Error en ValidacionUtils.getSessionValue", e);
		}

		return null;
	}

	public static void setSessionValue(Request request, String parametro, Object obj) {
		setSessionValue(request.getSession(), parametro, obj);
	}

	public static void setSessionValue(Session session, String parametro, Object obj) {
		session.setAttribute("var." + parametro, obj);
	}

	// ------------------------------

	public static void addParametersToRequest(Request request) {
		@SuppressWarnings("unchecked")
		Map<String, String> params = (Map<String, String>) request.getAttribute("ADD_PARAMETERS");
		if (params != null) {
			Iterator<Entry<String, String>> it = params.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, String> pairs = (Map.Entry<String, String>) it.next();
				request.setAttribute(pairs.getKey(), pairs.getValue());
			}
		}
	}

	public static Integer obtenerIdUsuario(HttpSession httpSession) {
		return obtenerIdUsuario((Session) httpSession.getAttribute("cocoon-session"));
	}

	public static Integer obtenerIdUsuario(Request request) {
		return obtenerIdUsuario(request.getSession());
	}

	public static Integer obtenerIdUsuario(Session session) {
		Object idUsuarioAtt = (session.getAttribute("var.id_usuario") != null) ? session.getAttribute("var.id_usuario") : session.getAttribute("id_usuario");
		if (idUsuarioAtt != null) {
			if (idUsuarioAtt instanceof String) {
				return Integer.parseInt(StringUtils.toString(idUsuarioAtt), 10);
			} else if (idUsuarioAtt instanceof Integer) {
				return (Integer) idUsuarioAtt;
			}
		}
		return null;
	}

	public static String createObjectRequest(String nombreParametro, Request request) {
		return (String) createObjectRequest(nombreParametro, String.class, request);
	}

	@SuppressWarnings("unchecked")
	public static <T> T createObjectRequest(String nombreParametro, Class<T> clase, Request request) {
		try {
			return (T) JavaToXML.createObjectRequest(nombreParametro, clase.getName(), request);
		} catch (Exception e) {
			SimpleLogger.setError("ValidacionUtils.createObjectRequest", e);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> createListObjectRequest(String nombreParametro, Class<T> clase, Request request) {
		try {
			return (List<T>) JavaToXML.createObjectRequest(nombreParametro, "java.util.List<" + clase.getName() + ">", request);
		} catch (Exception e) {
			SimpleLogger.setError("ValidacionUtils.createListObjectRequest", e);
		}

		return null;
	}

	public static String createObjectRequestAttributes(String nombreParametro, Request request) {
		return (String) createObjectRequestAttributes(nombreParametro, String.class, request);
	}

	@SuppressWarnings("unchecked")
	public static <T> T createObjectRequestAttributes(String nombreParametro, Class<T> clase, Request request) {
		try {
			return (T) JavaToXML.createObjectRequestAttributes(nombreParametro, clase.getName(), request);
		} catch (Exception e) {
			SimpleLogger.setError("ValidacionUtils.createObjectRequest", e);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> createListObjectRequestAttributes(String nombreParametro, Class<T> clase, Request request) {
		try {
			return (List<T>) JavaToXML.createObjectRequestAttributes(nombreParametro, "java.util.List<" + clase.getName() + ">", request);
		} catch (Exception e) {
			SimpleLogger.setError("ValidacionUtils.createListObjectRequest", e);
		}

		return null;
	}

	public static void requestParametersToAttributes(Request request) {
		Enumeration<?> a = request.getParameterNames();
		Map<String, String> requestMap = new HashMap<String, String>();
		while (a.hasMoreElements()) {
			String elem = (String) a.nextElement();
			requestMap.put(elem, request.getParameter(elem));
		}

		request.setAttribute("request", requestMap);
	}

}
