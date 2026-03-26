// CVS $Id$
package com.osmosyscol.commons.utils;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import com.osmosyscol.commons.validacion.ResultadoValidacion;

public class GestorVentanilla {

	private SecureRandom random = new SecureRandom();
	
	private String url_memoria = null;
	private String contenido_memoria = null;
	private ResultadoValidacion rvalidacion = null;
	
	private long numtiquete = 0;
	private Map<String, String> listado = new HashMap<String, String>();

	// -------------------------------------------------
	
	
	public synchronized String getTiquete() {
		numtiquete++;
		long currenttime = System.currentTimeMillis();
		StringBuffer ntiquete = new StringBuffer( Long.toString(numtiquete, 30) + Long.toString(currenttime, 30));
		while (ntiquete.length() < 32) {
			ntiquete.append( Long.toString(Math.round(random.nextDouble() * 30), 32));
		}
		listado.put(ntiquete.toString().toUpperCase(), "NEW");
		return ntiquete.toString().toUpperCase();
	}

	public synchronized boolean existeTiquete(String tiquete) {
		boolean ret = listado.get(tiquete) != null;
		return ret;
	}
	
	public synchronized boolean eliminarTiquete(String tiquete) {
		boolean ret = listado.remove(tiquete) != null;
		return ret;
	}

	public synchronized boolean esValidoTiquete(String tiquete) {
		String urltiquete = listado.get(tiquete);
		boolean ret = (urltiquete != null && urltiquete.equals("NEW"));
		return ret;
	}

	public synchronized boolean usarTiquete(String tiquete, String url) {
		String urltiquete = listado.get(tiquete);
		if (urltiquete != null && urltiquete.equals("NEW")) {
			listado.put(tiquete, url);
		}
		return false;
	}

	public synchronized boolean esRealizarCarga(String tiquete, String url) {
		String urltiquete = listado.get(tiquete);
		return (urltiquete != null && urltiquete.equals(url));
	}

	public String getUrl_memoria() {
		return url_memoria;
	}

	public void setUrl_memoria(String url_memoria) {
		this.url_memoria = url_memoria;
	}

	public String getContenido_memoria() {
		return contenido_memoria;
	}

	public void setContenido_memoria(String contenido_memoria) {
		this.contenido_memoria = contenido_memoria;
	}

	/**
	 * @return the rvalidacion
	 */
	public ResultadoValidacion getRvalidacion() {
		return rvalidacion;
	}

	/**
	 * @param rvalidacion the rvalidacion to set
	 */
	public void setRvalidacion(ResultadoValidacion rvalidacion) {
		this.rvalidacion = rvalidacion;
	}

}
