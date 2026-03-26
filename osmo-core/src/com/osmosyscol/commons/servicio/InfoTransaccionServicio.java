package com.osmosyscol.commons.servicio;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.cocoon.environment.Request;

import com.osmosyscol.commons.logging.dto.InfoTransaccion;

public class InfoTransaccionServicio {

	private static InfoTransaccionServicio auditoriaServicio;

	private InfoTransaccionServicio() {

	}

	public static InfoTransaccionServicio getInstance() {
		if (auditoriaServicio == null) {
			auditoriaServicio = new InfoTransaccionServicio();
		}
		return auditoriaServicio;
	}

	private static Map<Long, InfoTransaccion> mapThreads = new HashMap<Long, InfoTransaccion>();

	public Boolean guardarInfoSession(Request request) {
		return guardarInfoSession(new CoreRequest(request));
	}

	public Boolean guardarInfoSession(HttpServletRequest request) {
		return guardarInfoSession(new CoreRequest(request));
	}

	public Boolean guardarInfoSession(CoreRequest request) {
		return guardarInfoSessionRequest(request);
	}

	public Boolean guardarInfoSession() {
		return guardarInfoSessionRequest(null);
	}

	private Boolean guardarInfoSessionRequest(CoreRequest request) {
		try {
			long id = Thread.currentThread().getId();

			synchronized (mapThreads) {
				mapThreads.put(id, InfoTransaccion.getInfoTransaccion(request));
			}
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public InfoTransaccion getInfoTransaccion() {

		InfoTransaccion infoTransaccion = null;
		synchronized (mapThreads) {
			infoTransaccion = mapThreads.get(Thread.currentThread().getId());
		}

		if (infoTransaccion == null) {
			infoTransaccion = InfoTransaccion.getInfoTransaccion(null);
		}

		return infoTransaccion;
	}

	public InfoTransaccion borrarInfoTransaccion() {
		synchronized (mapThreads) {
			return mapThreads.remove(Thread.currentThread().getId());
		}
	}

	public Boolean setInfoTransaccion(InfoTransaccion infoTransaccion) {
		try {
			long id = Thread.currentThread().getId();

			synchronized (mapThreads) {
				mapThreads.put(id, infoTransaccion);
			}
			return true;

		} catch (Exception e) {
			return false;
		}

	}

}
