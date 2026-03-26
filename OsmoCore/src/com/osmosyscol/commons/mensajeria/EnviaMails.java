// CVS $Id$
package com.osmosyscol.commons.mensajeria;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.cocoon.environment.SourceResolver;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.mail.MailException;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.AutenticacionServicio;
import com.osmosyscol.commons.utils.JavaToXML;
import com.osmosyscol.commons.utils.Navegador;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class EnviaMails {

	// ------------------------------------------

	private static String SMTP = null;
	private static Integer SMTP_PORT = null;
	private static String USER = null;
	private static String PASS = null;
	private static Boolean SSL = null;
	private static Boolean STARTTLS = null;
	private static String FROM = null;
	private static String FROMNAME = null;
	private static String DEBUG = null;
	private static Boolean AUTHENTICATION = null;

	public static void configure(String smtp, String user, String pass, Boolean ssl, Boolean starttls, String from, String fromname, String maildebug) {
		SMTP = (String) splitSmtp(smtp, 0);
		SMTP_PORT = (Integer) splitSmtp(smtp, 1);
		USER = user;
		PASS = pass;
		SSL = ssl;
		FROM = from;
		FROMNAME = fromname;
		DEBUG = maildebug;
		STARTTLS = starttls;

		SimpleLogger.setInfo(">> Cargando Configuracion de Correo: \n smtp: " + SMTP + "\n user: " + USER + "\n pass: " + PASS + "\n ssl: " + SSL + "\n from: " + FROM + "\n fromname: " + FROMNAME + "\n debug: " + DEBUG);
	}
	
	public static void configure(String smtp, String user, String pass, Boolean ssl, Boolean starttls, String from, String fromname, String maildebug, Boolean authentication) {
		SMTP = (String) splitSmtp(smtp, 0);
		SMTP_PORT = (Integer) splitSmtp(smtp, 1);
		USER = user;
		PASS = pass;
		SSL = ssl;
		FROM = from;
		FROMNAME = fromname;
		DEBUG = maildebug;
		STARTTLS = starttls;
		AUTHENTICATION = authentication;
		SimpleLogger.setInfo(">> Cargando Configuracion de Correo: \n smtp: " + SMTP + "\n user: " + USER + "\n pass: " + PASS + "\n ssl: " + SSL + "\n from: " + FROM + "\n fromname: " + FROMNAME + "\n debug: " + DEBUG);
	}

	/**
	 * Desagrega el host del puerto en una cadena de tipo x.x.x.x:xx
	 * 
	 * @param smtp2
	 *            cadena a desagregar
	 * @param partId
	 *            parte a obtener (0: host, 1: puerto)
	 * @return el host como string y el puerto como entero
	 */
	public static Object splitSmtp(String smtp2, int partId) {

		if (StringUtils.isEmpty(smtp2)) {
			return null;
		}

		String[] tokens = smtp2.split(":");

		if (partId > tokens.length - 1) {
			return null;
		} else if (1 == partId) {
			try {
				return Integer.parseInt(tokens[partId]);
			} catch (Exception e) {
				SimpleLogger.setError("Valor de puerto inválido.", e);
				return null;
			}

		}

		return tokens[partId];

	}

	public static String toSring() {
		return "smtp: " + SMTP + "\n smtp-port: " + SMTP_PORT + "\n user: " + USER + "\n pass: " + PASS + "\n ssl: " + SSL + "\n from: " + FROM + "\n fromname: " + FROMNAME + "\n debug: " + DEBUG;
	}

	// ------------------------------------------

	public static void enviar(String to, String toName, String subject, String urlContenido, Map<String, String> archivos) throws EmailException, MalformedURLException {
		enviar(to, toName, subject, urlContenido, archivos, null, null);
	}

	public static void enviarAsync(String to, String toName, String subject, String urlContenido, Map<String, String> archivos) throws EmailException, MalformedURLException {
		enviarAsync(to, toName, subject, urlContenido, archivos, null, null);
	}

	public static void enviar(String to, String toName, String subject, String urlContenido, Map<String, String> archivos, Map<String, String> parametros) throws EmailException, MalformedURLException {
		enviar(to, toName, subject, urlContenido, archivos, parametros, null);
	}

	public static void enviarAsync(String to, String toName, String subject, String urlContenido, Map<String, String> archivos, Map<String, String> parametros) throws EmailException, MalformedURLException {
		enviarAsync(to, toName, subject, urlContenido, archivos, parametros, null);
	}

	public static void enviarAsync(String to, String toName, String subject, String urlContenido, Map<String, String> archivos, Map<String, String> parametros, SourceResolver resolver) {
		new EnviarAsync(to, toName, subject, urlContenido, archivos, parametros, resolver).start();
	}

	public static void enviar(String to, String toName, String subject, String urlContenido, Map<String, String> archivos, Map<String, String> parametros, SourceResolver resolver) throws EmailException, MalformedURLException {

		try {
			if (parametros != null && parametros.size() > 0) {
				StringBuffer adcionalUrl = new StringBuffer("?");

				Set<Entry<String, String>> conjunto = parametros.entrySet();

				for (Iterator<Entry<String, String>> iterator = conjunto.iterator(); iterator.hasNext();) {

					Entry<String, String> entry = iterator.next();

					try {
						String key = entry.getKey();
						String value = entry.getValue();
						value = StringEscapeUtils.unescapeXml(value);

						if (value != null) {
							value = URLEncoder.encode(value, "iso-8859-1");
						}

						adcionalUrl.append(key + "=" + value + "&");
					} catch (Exception e) {
						SimpleLogger.setError("", e);
					}

				}
				urlContenido += adcionalUrl.toString();
			}

			if (ParametrosInicio.ISDEBUG) {
				SimpleLogger.setInfo("UrlCorreo: " + urlContenido);
			}

			HtmlEmail correo = new HtmlEmail();
			correo.setSSLOnConnect(SSL);
			// Autentica al usuario en el SMTP
			if (AUTHENTICATION && org.apache.commons.lang.StringUtils.isNotBlank(USER) && org.apache.commons.lang.StringUtils.isNotBlank(PASS)) {
				DefaultAuthenticator da = new DefaultAuthenticator(USER, PASS);
				correo.setAuthenticator(da);
			}

			correo.setHostName(SMTP);
			if (null != SMTP_PORT) {
				if(SSL){
					correo.setSslSmtpPort(SMTP_PORT.toString());
				}else{
					correo.setSmtpPort(SMTP_PORT);
				}
			}
			
			if(STARTTLS != null){
				correo.setStartTLSEnabled(STARTTLS);
			}

			correo.setFrom(FROM, FROMNAME);

			if (ParametrosInicio.ISDEBUG) {
				correo.addTo(DEBUG, DEBUG);
			} else {
				correo.addTo(to, toName);
			}

			correo.setSubject(subject);

			// Trae el correo electrónico de la JSP respectiva
			String cuerpoMail = "";
			if (urlContenido != null && urlContenido.indexOf("cocoon:") >= 0) {
				cuerpoMail = Navegador.navegarCoocon(urlContenido, resolver);
			} else if (urlContenido != null && urlContenido.indexOf("plano:") >= 0) {
				cuerpoMail = urlContenido.substring(6);
			} else {
				cuerpoMail = Navegador.navegar(urlContenido);
			}

			SimpleLogger.setDebug("Enviando Mail [ to: " + toName + "<" + to + "> ] [ subject: " + subject + " ] [ file: " + archivos + " ]\n\r" + cuerpoMail);

			// Adjunta los archivos (ubicación fisica) y adiciona los CID:
			// respectivo
			cuerpoMail = adjuntarArchivos(correo, cuerpoMail, archivos);

			// Asignar el correo electrónico
			correo.setHtmlMsg(cuerpoMail);

			correo.send();

		} catch (Exception e) {
			SimpleLogger.setError("Error en EnviaMails.enviar:\n\r [ to: " + toName + "<" + to + "> ] [ subject: " + subject + " ] [ file: " + archivos + " ]\n\r[ urlContenido: " + urlContenido + " ]\n\r", e);
			if (e instanceof MailException) {
				throw (MailException) e;
			} else {
				throw new RuntimeException(e);
			}

		}
	}

	// -------------------------------

	private static class EnviarAsync extends Thread {

		private String to, toName, subject, urlContenido;
		private Map<String, String> archivos, parametros;
		private SourceResolver resolver;

		public EnviarAsync(String to, String toName, String subject, String urlContenido, Map<String, String> archivos, Map<String, String> parametros, SourceResolver resolver) {
			this.to = to;
			this.toName = toName;
			this.subject = subject;
			this.urlContenido = urlContenido;
			this.archivos = archivos;
			this.parametros = parametros;
			this.resolver = resolver;
		}

		@Override
		public void run() {
			try {
				enviar(to, toName, subject, urlContenido, archivos, parametros, resolver);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// -------------------------------

	private static String adjuntarArchivos(HtmlEmail correo, String cuerpoMail, Map<String, String> archivos) throws EmailException {
		Map<String, String> valoresCids = new HashMap<String, String>();

		if (archivos != null) {
			Set<Entry<String, String>> entrySetArchivos = archivos.entrySet();
			Iterator<Entry<String, String>> it = entrySetArchivos.iterator();
			
			int i = 0;
			while (it.hasNext()) {
				Map.Entry<String, String> entrada = (Map.Entry<String, String>) it.next();
				String clave = (String) entrada.getKey();

				String valor = null;
				String path = (String) entrada.getValue();

				if (path.startsWith("file://")) {
					valor = path.substring(7);
				} else {
					valor = AutenticacionServicio.PATH_APLICACION + "/resources/" + path;
				}

				File archivo = new File(valor);
				String cid = correo.embed(archivo, "Imagen" + i++);
				valoresCids.put(clave, "cid:" + cid);
			}
		}

		cuerpoMail = StringUtils.traducir(cuerpoMail, valoresCids);
		return cuerpoMail;
	}

	// ------------------------

	public static String replazarTextos(String html, Object... objetos) {

		html = StringUtils.trimToEmpty(html);

		for (Object objeto : objetos) {

			if (objeto != null) {
				Map<String, String> datos = JavaToXML.objectToParameters(objeto.getClass().getSimpleName(), objeto, true);

				for (String key : datos.keySet()) {

					String k = "{" + key.toUpperCase() + "}";
					System.out.println(k);

					html = html.replace(k, StringUtils.trimToEmpty(datos.get(key)));
				}
			}
		}

		return html;
	}

}
