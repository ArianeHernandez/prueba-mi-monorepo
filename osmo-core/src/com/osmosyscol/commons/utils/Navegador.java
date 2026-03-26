package com.osmosyscol.commons.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.apache.cocoon.components.language.markup.xsp.XSPUtil;
import org.apache.cocoon.environment.SourceResolver;

import com.osmosyscol.commons.log.SimpleLogger;

public class Navegador {

	// ------------------------------------

	private static String URL_BASE = null;

	public static void configure(String urlbase) {

		if (org.apache.commons.lang.StringUtils.isBlank(urlbase)) {
			SimpleLogger.setError("No se puede configurar el Navegador Interno. La url no puede ser NULO");
		} else {
			URL_BASE = urlbase;
			SimpleLogger.setInfo("El Navegador Interno ha sido configurado. [" + URL_BASE + "]");
		}
	}

	// ------------------------------------

	private static String navegarInterno(String dirUrl) {
		StringBuffer cadSalida = new StringBuffer("");
		try {
			URL url = new URL(dirUrl);
			URLConnection connection = url.openConnection();
			connection.setDoInput(true);
			InputStream inStream = connection.getInputStream();
			BufferedReader input = new BufferedReader(new InputStreamReader(inStream));

			String line = "";
			while ((line = input.readLine()) != null) {
				cadSalida.append(line + "\n");
			}

			input.close();
		} catch (Exception e) {
			SimpleLogger.setError("No se puede Navegar Internamente", e);
		}
		return cadSalida.toString();
	}

	// ------------------------------------
	public static InputStream navegarRaw(String dirUrl) {
		InputStream inStream = null;
		try {
			URL url = new URL(dirUrl);
			URLConnection connection = url.openConnection();
			connection.setDoInput(true);
			inStream = connection.getInputStream();
		} catch (Exception e) {
			SimpleLogger.setError("No se puede Navegar Internamente", e);
		}
		return inStream;

	}

	// ------------------------------------

	public static String navegar(String urlContenido) {

		if (urlContenido == null || urlContenido.trim().length() == 0) {
			SimpleLogger.setWarn("WARN: El navegador interno no puede ir a una direccion NULA.");
			return "";
		}

		if (URL_BASE != null) {
			return navegarInterno(URL_BASE + "/" + urlContenido);
		} else {
			SimpleLogger.setWarn("WARN: El navegador interno no configurado.");
			return "";
		}
	}

	public static String navegarCoocon(String cpath, SourceResolver resolver) {

		if (cpath == null || cpath.trim().length() == 0) {
			SimpleLogger.setWarn("El navegador interno no puede ir a una direccion NULA.");
			return "";
		}

		if (resolver == null) {

			if (URL_BASE != null) {
				String url = URL_BASE + "/" + cpath.replace("cocoon:", "");
				System.out.println("URL NAVEGADOR COCOON: " + url);
				return navegarInterno(url);
			} else {
				SimpleLogger.setWarn("El navegador interno no puede navegar con Cocoon. El SourceResolver es NULO.");
				return "";
			}

		}

		try {
			return XSPUtil.getSourceContents(cpath, resolver);
		} catch (Exception e) {
			SimpleLogger.setError(">> No se puede obtener el contenido del servicio [" + cpath + "]. ", e);
			return "";
		}
	}

	// ------------------------------------
}