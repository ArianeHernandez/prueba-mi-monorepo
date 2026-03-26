package com.osmosyscol.commons.servicio.json;

import java.math.BigInteger;
import java.util.Date;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.AutenticacionServicio;
import com.osmosyscol.commons.servicio.VersionServicio;
import com.osmosyscol.commons.utils.StringUtils;

public class Core_JsonServicio implements JsonService {

	private Session session = null;

	public void setSession(Session session) {
		this.session = session;
	}

	// ----------------------

	public static String getAplicationName() {
		return "Aplicación: " + AutenticacionServicio.NOMBRE_APLICACION + "\nVersión: " + AutenticacionServicio.VERSION_APLICACION + "\nCore: " + VersionServicio.getVersion();
	}

	// ----------------------
	// ----------------------

	public static Boolean setErrorLog(String mensaje, String error) {
		SimpleLogger.setError("js: " + mensaje + " | " + error);
		return true;
	}

	public Boolean setSessionErrorLog(String mensaje, String error) {

		String usuario = (String) session.getAttribute("login");
		SimpleLogger.setError("js: " + usuario + " | " + mensaje + " | " + error);
		return true;
	}

	// ----------------------

	public static Boolean setInfoLog(String mensaje, String error) {
		SimpleLogger.setInfo("js: " + mensaje + " | " + error);
		return true;
	}

	public Boolean setSessionInfoLog(String mensaje, String error) {

		String usuario = (String) session.getAttribute("login");
		SimpleLogger.setError("js: " + usuario + " | " + mensaje + " | " + error);
		return true;
	}
	
	public void closeSesion() {

		session.removeAttribute("login");
		session.invalidate();
	}

	// ----------------------

	public static Date getCurrentDate() {
		return new Date();
	}

	public static Long getCurrentTime() {
		return System.currentTimeMillis();
	}

	public static Boolean validarCorreo(String correo) {
		return StringUtils.validaCorreoE(correo);
	}
	
	public static Integer calcularDigitoVerificacion(String nit) {
		return StringUtils.calcularDigitoVerificacion(nit);
	}
	
	public static Long modPow(Long n, Long exponent, Long m){
		return new BigInteger(n + "").modPow(new BigInteger(exponent + ""), new BigInteger(m + "")).longValue();
	}
	
	public static Integer[] getBytes(String txt){
		byte[] b = txt.getBytes();
		
		Integer[] res = new Integer[b.length];  
		
		for (int i=0; i<b.length; i++) {
			res[i] = (int) b[i];
		}
		
		return res;
	}
	

}
