package com.osmosyscol.commons.servicio;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.cocoon.environment.Request;
import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.utils.CocoonUtils;

public class CoreRequest {

	private String remoteHost = "0.0.0.0";
	private String remoteAddr;

	private Map<String, Object> attributes = new HashMap<String, Object>();
	private Map<String, String[]> parameters = new HashMap<String, String[]>();
	private Map<String, String> headers = new HashMap<String, String>();
	private Session session;

	private String url;

	// ---------------

	public CoreRequest(HttpServletRequest request) {

		session = CocoonUtils.getCocoonSession(request);

		// ----------

		Enumeration<?> e = request.getAttributeNames();

		while (e.hasMoreElements()) {
			String name = (String) e.nextElement();
			attributes.put(name, request.getAttribute(name));
		}

		// ----------------

		Enumeration<?> ep = request.getParameterNames();

		while (ep.hasMoreElements()) {
			String name = (String) ep.nextElement();
			parameters.put(name, request.getParameterValues(name));
		}

		// ----------------

		Enumeration<?> a = request.getHeaderNames();

		while (a.hasMoreElements()) {
			String name = (String) a.nextElement();
			headers.put(name.toLowerCase(), request.getHeader(name));
		}

		remoteHost = request.getRemoteHost();
		remoteAddr =  CocoonUtils.getRemoteAddr(request);

		url = request.getRequestURI().substring(request.getContextPath().length());
	}

	public CoreRequest(Request request) {

		session = request.getSession(false);

		// ----------

		Enumeration<?> e = request.getAttributeNames();

		while (e.hasMoreElements()) {
			String name = (String) e.nextElement();
			attributes.put(name, request.getAttribute(name));
		}

		// ----------------

		Enumeration<?> ep = request.getParameterNames();

		while (ep.hasMoreElements()) {
			String name = (String) ep.nextElement();
			parameters.put(name, request.getParameterValues(name));
		}

		// ----------------

		Enumeration<?> a = request.getHeaderNames();

		while (a.hasMoreElements()) {
			String name = (String) a.nextElement();
			headers.put(name.toLowerCase(), request.getHeader(name));
		}

		remoteHost = request.getRemoteHost();
		remoteAddr = CocoonUtils.getRemoteAddr(request);

		url = request.getRequestURI().substring(request.getContextPath().length());
	}

	public Object getAttribute(String arg) {
		return attributes.get(arg);
	}

	public String getParameter(String arg) {

		String[] p = parameters.get(arg);

		if (p != null && p.length > 0) {
			return p[0];
		}
		return null;
	}

	public String[] getParameterValues(String arg) {
		return parameters.get(arg);
	}

	public Session getSession() {
		return session;
	}

	public String getHeader(String arg) {
		if(arg==null){
			return null;
		}
		return headers.get(arg.toLowerCase());
	}

	public String getRemoteHost() {
		return remoteHost;
	}

	public String getRemoteAddr() {
		return remoteAddr;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
