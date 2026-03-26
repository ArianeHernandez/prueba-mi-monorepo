/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.osmosyscol.datasuite.logica.servicios.json;

import java.util.Arrays;
import java.util.List;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.applet.autips.RSA;
import com.osmosyscol.datasuite.logica.servicios.IpServicio;

/**
 * 
 * @author ojcchar
 */
public class ValidarIPJsonServicio implements JsonService {

	private Session session = null;

	public void setSession(Session session) {
		this.session = session;

	}

	// ----------

	public Boolean validarIP(String ipsCifradas, String macsCifradas) {

		try {
			String ipsDescifradas = RSA.desencripta(ipsCifradas, RSA.clave);
			String macsDescifradas = RSA.desencripta(macsCifradas, RSA.clave);

			List<String> ip_privadaList = Arrays.asList(ipsDescifradas.split("\n"));
			List<String> macList = Arrays.asList(macsDescifradas.split("\n"));

			SimpleLogger.setDebug("IP_PRIVADAS: " + ip_privadaList);
			SimpleLogger.setDebug("MACS       : " + macList);

			Integer id_usuario = (Integer) session.getAttribute("id_usuario");
			String ip_cliente = (String) session.getAttribute("REQUEST.REMOTE.ADDR");

			Boolean permitido = IpServicio.getInstance().validarIP(id_usuario, ip_cliente, ip_privadaList);

			session.setAttribute("IP_VALIDA", permitido ? "S" : "N");

		} catch (Exception e) {
			SimpleLogger.setError("Error al validar ip. ", e);
			return false;
		}
		return true;

	}
}
