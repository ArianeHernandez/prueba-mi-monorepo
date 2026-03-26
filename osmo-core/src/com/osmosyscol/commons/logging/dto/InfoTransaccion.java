package com.osmosyscol.commons.logging.dto;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.servicio.AutenticacionServicio;
import com.osmosyscol.commons.servicio.CoreRequest;
import com.osmosyscol.commons.utils.ValidarTipoDato;

public class InfoTransaccion {

	private static InfoTransaccionBuilder builder;

	private String login;
	private String nombre;
	private Date fecha;
	private String ip;
	private String navegador;
	private String url;

	private Map<String, Object> attributes = new HashMap<String, Object>();

	// --------------

	public static void setBuilder(InfoTransaccionBuilder builder) {
		InfoTransaccion.builder = builder;
	}

	public boolean setAttribute(String name, Object value) {

		try {
			attributes.put(name, value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Object getAttribute(String name) {
		return attributes.get(name);
	}

	public static InfoTransaccion getInfoTransaccion(CoreRequest request) {

		if (builder != null) {

			InfoTransaccion infoTransaccion = new InfoTransaccion(request);

			if (builder.crearInfoTransaccion(infoTransaccion, request)) {
				return infoTransaccion;
			}

			return null;
		}

		InfoTransaccion infoTransaccion = new InfoTransaccion(request);

		if (request != null) {
			Session session = request.getSession();

			if (session != null) {

				Enumeration<?> e = session.getAttributeNames();

				while (e.hasMoreElements()) {
					String key = e.nextElement().toString();
					infoTransaccion.attributes.put(key, session.getAttribute(key));
				}
			}
		}

		return infoTransaccion;
	}

	private InfoTransaccion(CoreRequest request) {

		fecha = new Date();

		if (request != null) {

			Session session = request.getSession();

			if (session == null) {
				login = "";
				nombre = "Visitante";
			} else {

				login = (String) session.getAttribute("login");
				nombre = (String) session.getAttribute("login");
			}

			ip = request.getHeader("remote_host");

			if (ip == null) {
				ip = request.getRemoteAddr();
			}

			navegador = ValidarTipoDato.validateMaxSize(request.getHeader("user-agent"), AutenticacionServicio.USER_AGENT_SIZE);
			url = request.getUrl();

		} else {
			login = "";
			nombre = "System";
			ip = "127.0.0.1";
			navegador = null;
			url = null;
		}
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getNavegador() {
		return navegador;
	}

	public void setNavegador(String navegador) {
		this.navegador = navegador;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
