package com.osmosyscol.datasuite.servlet;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.batik.ext.awt.image.SVGComposite.XorCompositeContext_INT_PACK;
import org.apache.cocoon.environment.Session;
import org.apache.commons.io.IOUtils;

import com.osmosyscol.commons.crypto.Crypto;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.AutenticacionServicio;
import com.osmosyscol.commons.utils.ServletUtils;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.dto.Aplicacion;
import com.osmosyscol.datasuite.modelatos.logica.servicios.AplicacionServicio;
import com.osmosyscol.datasuite.webdata.logica.servicios.AccesoRolServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class ProxyService extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	private static final String PAGINA_SIMPLE = "PAGINA_SIMPLE";

	static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		startUp(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		startUp(request, response);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		startUp(request, response);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		startUp(request, response);
	}

	private static final String STRING_CONTENT_DISPOSITION_HEADER = "Content-Disposition";

	private static Map<String, Map<String, String>> sessionMap = new HashMap<String, Map<String, String>>();

	// --------------------------------------------------------------------------
	// --------------------------------------------------------------------------

	public static String getSessionAplicacion(Session session, Aplicacion aplicacion) {

		String sessionId = "";
		Map<String, String> sesiones = new HashMap<String, String>();

		if (session != null) {
			sesiones = sessionMap.get(session.getId());

			if (sesiones == null) {
				sesiones = new HashMap<String, String>();
				sessionMap.put(session.getId(), sesiones);
			}

			sessionId = sesiones.get(aplicacion.getId_aplicacion());

			if (sessionId != null) {
				return sessionId;
			}

			// ----------------------------------------------------------------------

			String urlservicio = aplicacion.getUrl() + "/sys/login.pub";

			// ----------------------------------------------------------------------

			try {

				URL url = new URL(urlservicio);
				HttpURLConnection urlConn;
				urlConn = (HttpURLConnection) url.openConnection();

				// Open New URL connection channel.

				urlConn.setDoInput(true);
				urlConn.setDoOutput(true);
				urlConn.setUseCaches(false);

				// Send POST output.

				OutputStreamWriter wr = new OutputStreamWriter(urlConn.getOutputStream());

				String content = "u=" + URLEncoder.encode(Crypto.E(session.getAttribute("login")), wr.getEncoding());
				content += "&l=" + session.getAttribute("login");

				// Escribir..

				wr.write(content);
				wr.flush();
				wr.close();

				// Leer...

				InputStream input = urlConn.getInputStream();
				StringUtils.toString(input);
				input.close();

				String nCookie = urlConn.getHeaderField("Set-Cookie");
				sessionId = nCookie.split("[;]")[0].split("[=]")[1];

				sesiones.put(aplicacion.getId_aplicacion(), sessionId);

				return sessionId;

			} catch (Exception e) {
				SimpleLogger.setError("Error Fatal url: " + urlservicio, e);
			}
		}
		return null;
	}

	// --------------------------------------------------------------------------
	// --------------------------------------------------------------------------

	private static <T> boolean contiene(T[] lista, T elemento) {

		if (lista == null || elemento == null) {
			return false;
		}

		for (T t : lista) {
			if (t == elemento || elemento.equals(t)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Determina el acceso al recurso...
	 * 
	 * @param request
	 * @return
	 */
	private static boolean validarAcceso(HttpServletRequest request, HttpServletResponse response) {

		Boolean isAutorizado = false;

		String urlrequest = request.getRequestURI().substring(request.getContextPath().length());

		urlrequest = urlrequest.split("[;]")[0].split("[?]")[0];

		String ext = urlrequest.substring(urlrequest.lastIndexOf(".") + 1).toLowerCase();

		// --------------------------------------

		if (urlrequest.indexOf("images/") > 0) {

			String vext[] = new String[] { "gif", "png", "jpg" };

			if (contiene(vext, ext)) {
				return true;
			}

		}

		// --------------------------------------

		if (urlrequest.indexOf("styles/") > 0) {

			String vext[] = new String[] { "css" };

			if (contiene(vext, ext)) {
				return true;
			}

		}

		// --------------------------------------

		if (urlrequest.indexOf("scripts/") > 0) {

			String vext[] = new String[] { "js" };

			if (contiene(vext, ext)) {
				return true;
			}

		}

		// --------------------------------------

		if (urlrequest.indexOf("publicfiles") > 0) {

			// String vext[] = new String[] { "js", "css", "gif", "png", "jpg" ,
			// "ttf", "woff" };

			// if (contiene(vext, ext)) {
			return true;
			// }

		}

		// --------------------------------------

		if (urlrequest.indexOf("system") > 0) {

			String vext[] = new String[] { "info" };

			if (contiene(vext, ext)) {
				return true;
			}

		}

		// --------------------------------------

		if (urlrequest.indexOf("JSON-RPC") > 0) {
			return true;
		}

		// --------------------------------------

		if (urlrequest.indexOf("home") > 0 || urlrequest.indexOf("MisPesosJS") > 0) {
			return true;
		}

		// --------------------------------------

		if (urlrequest.indexOf("rest") > 0) {
			return true;
		}

		// --------------------------------------

		if (urlrequest.indexOf(".pub") > 0) {
			return true;
		}

		// --------------------------------------

		if (ParametrosInicio.ISDEBUG && (urlrequest.indexOf(".xpub") > 0 || urlrequest.indexOf(".xdo") > 0 || urlrequest.indexOf(".xxdo") > 0)) {
			return true;
		}

		// ---------------------------------------

		Session session = (Session) request.getSession().getAttribute("cocoon-session");

		if (session != null && session.getAttribute("login") != null) {
			isAutorizado = AutenticacionServicio.getInstance().autorizar((String) session.getAttribute("login"), urlrequest);
			if (isAutorizado) {
				// -----------------
				// Verifica si el administrativo tiene acceso a la url sólo si
				// la url hace
				// parte de un servicio
				Integer id_administrativo = (Integer) session.getAttribute("id_administrativo");
				if (id_administrativo != null && AccesoRolServicio.getInstance().obtenerIdServicioPorURL(urlrequest) != null) {
					isAutorizado = AccesoRolServicio.getInstance().validarAccesoAdministrativoPorUrl(id_administrativo, urlrequest);
					SimpleLogger.setDebug("ProxyService isAutorizado :  " + id_administrativo + " ' '" + urlrequest + "' | '" + isAutorizado + "'");
				}
			}
		}

		if (isAutorizado && StringUtils.esVerdad(ParametrosInicio.getProperty("tokenid.activo"))) {

			String tokenid = (String) session.getAttribute("STOKENID");

			Cookie cookie = getCookie(request, "STOKENID");

			if (tokenid != null && (cookie == null || !cookie.getValue().equals(tokenid))) {
				SimpleLogger.setInfo("PROX: El STOKENID no coincide: " + tokenid + " != " + (cookie == null ? "" : cookie.getValue()));
				isAutorizado = false;
			}

			String code = StringUtils.randomString(32);

			session.setAttribute("STOKENID", code);

			Cookie ncookie = new Cookie("STOKENID", code);
			ncookie.setPath(request.getContextPath() + "/");
			ncookie.setMaxAge(60 * 10);
			response.addCookie(ncookie);

		}

		return isAutorizado;
	}

	private static Cookie getCookie(HttpServletRequest request, String string) {

		Cookie cookies[] = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(string)) {
				return cookie;
			}
		}

		return null;
	}

	// --------------------------------------------------------------------------
	// --------------------------------------------------------------------------

	/**
	 * Determina la plantilla que debe tener.
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	private static String obtenerPlantilla(Aplicacion aplicacion, HttpServletRequest request) {

		try {

			URL url = null;

			String paginaSimple = request.getParameter(PAGINA_SIMPLE);
			String params = "";

			if (paginaSimple != null) {
				params = PAGINA_SIMPLE + "=1";
			}

			if (request.getRequestURI().endsWith(".pub")) {
				url = new URL(ParametrosInicio.getProperty("webdata.endpoint") + "/system/template.pub");
			}

			if (request.getRequestURI().endsWith(".do")) {
				url = new URL(ParametrosInicio.getProperty("webdata.endpoint") + "/system/template.do?" + params);
			}

			// ----------------------------------------

			if (url != null) {

				HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();

				return new String(getContenido(urlConn, request, request.getSession().getId(), true).getBytes());
			}

		} catch (Exception e) {
			SimpleLogger.setError("No se pudo obtener la plantilla", e);
		}

		return null;
	}

	// --------------------------------------------------------------------------
	// --------------------------------------------------------------------------

	private static InputStream aplicarPlantilla(byte[] bytes, String plantilla_html) {

		if (plantilla_html == null) {
			return new ByteArrayInputStream(bytes);
		} else {

			String contenido = new String(bytes);

			String body = "";

			try {
				body = contenido.substring(contenido.indexOf("<body"), contenido.indexOf("</body"));
				body = body.substring(body.indexOf(">") + 1);
			} catch (Exception e) {
			}

			String header = "";

			try {
				header = contenido.substring(contenido.indexOf("<head"), contenido.indexOf("</head"));
				header = header.substring(header.indexOf(">") + 1);
			} catch (Exception e) {
			}

			if (plantilla_html.indexOf("</head") > 0) {
				plantilla_html = plantilla_html.replace("</head>", header + "</head>");
			} else {
				plantilla_html = plantilla_html.replace("<html>", "<html> <head> " + header + "</head>");
			}
			plantilla_html = plantilla_html.replace("[APP_CONTENIDO]", body);

			return StringUtils.toInputStream(plantilla_html);
		}
	}

	// --------------------------------------------------------------------------
	// --------------------------------------------------------------------------

	private static void startUp(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String urlrequest = request.getRequestURI().substring(request.getContextPath().length());

		try {

			String strurl = urlrequest.substring(8);
			String id_aplicacion = strurl.substring(0, strurl.indexOf("/"));
			String servicio = strurl.substring(id_aplicacion.length());

			Aplicacion aplicacion = AplicacionServicio.getInstance().obtenerAplicacion(id_aplicacion);

			Session session = (Session) request.getSession().getAttribute("cocoon-session");

			String sessionId = getSessionAplicacion(session, aplicacion);

			// Verifica permisos de acceso a la pagina.

			if (validarAcceso(request, response)) {

				// ---------------------------------------------------------------------

				String urlservicio = aplicacion.getUrl() + servicio;

				// ----------------------------------------------------------------------

				URL url = new URL(urlservicio);
				HttpURLConnection urlConn;
				urlConn = (HttpURLConnection) url.openConnection();

				// ----------------------------------------------------------------------

				Pagina pagina = getContenido(urlConn, request, sessionId);

				if (pagina.getRedirect()) {
					String destino = pagina.getLocation();

					for (int i = 0; i < 4; i++) {
						destino = destino.substring(destino.indexOf("/") + 1);
					}

					destino = "CldXmn/" + aplicacion.getId_aplicacion() + "/" + destino;
					response.setStatus(302);
					ServletUtils.sendRedirect(request, response, destino);
					return;
				}

				byte[] contenido = pagina.getBytes();

				response.setContentType(pagina.getContentType());
				response.setStatus(pagina.getStatus());

				if (pagina.getContentDisposition() != null) {
					response.setHeader(STRING_CONTENT_DISPOSITION_HEADER, pagina.getContentDisposition());
				}

				String plantilla_html = obtenerPlantilla(aplicacion, request);

				// PrintWriter out = response.getWriter();
				InputStream in = aplicarPlantilla(contenido, plantilla_html);

				// ----------------------------------------------------------------------

				OutputStream out = response.getOutputStream();

				byte[] buf = new byte[1024];
				int count = 0;
				while ((count = in.read(buf)) >= 0) {
					out.write(buf, 0, count);
				}
				in.close();
				out.close();

			} else {
				response.sendRedirect(request.getContextPath() + "/inicio/0.pub?acceso_denegado&");
			}

		} catch (Exception e) {
			PrintWriter out = response.getWriter();
			out.print(e.getMessage());
			response.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
			SimpleLogger.setError("Error: " + urlrequest, e);
		}

	}

	// --------------------------------------------------------------------------
	// --------------------------------------------------------------------------

	public static String toString(InputStream in) throws IOException {
		StringBuffer out = new StringBuffer();
		byte[] b = new byte[4096];
		for (int n; (n = in.read(b)) != -1;) {
			out.append(new String(b, 0, n, java.nio.charset.Charset.defaultCharset()));
		}
		return out.toString();
	}

	public static Pagina getContenido(HttpURLConnection urlConn, HttpServletRequest request, String sessionId) {
		return getContenido(urlConn, request, sessionId, false);
	}

	@SuppressWarnings({ "unchecked", "static-access" })
	public static Pagina getContenido(HttpURLConnection urlConn, HttpServletRequest request, String sessionId, Boolean simple) {

		try {
			Boolean hasBody = false;
			// Open New URL connection channel.

			urlConn.setDoInput(true);
			urlConn.setDoOutput(true);
			urlConn.setUseCaches(false);
			urlConn.setInstanceFollowRedirects(false);
			if (!simple) {
				urlConn.setRequestMethod(request.getMethod());
			}
			urlConn.setRequestProperty("Accept-Encoding", "gzip");
			urlConn.setRequestProperty("REMOTE_HOST", request.getRemoteAddr());

			Enumeration<String> headers = request.getHeaderNames();

			while (headers.hasMoreElements()) {

				String headerName = headers.nextElement();

				if (headerName.equalsIgnoreCase("cookie")) {
					String cookies = request.getHeader("cookie");
					String[] cookiesList = cookies.split("[;]");
					String cookieProperty = "";
					if (cookiesList.length > 0) {
						for (String cookie : cookiesList) {
							if (StringUtils.equals("JSESSIONID", cookie.split("[=]")[0])) {
								cookieProperty = "JSESSIONID=" + sessionId + ";";
							} else {
								cookieProperty += cookie + ";";
							}
						}
						urlConn.setRequestProperty(headerName, cookieProperty);
					}
				} else {
					if (!simple || headerName.equalsIgnoreCase("User-Agent")) {
						urlConn.setRequestProperty(headerName, request.getHeader(headerName));
					}
				}

				if (headerName.equalsIgnoreCase("Content-Length")) {
					hasBody = true;
				}

			}

			// Send POST output.

			byte[] bytes = null;

			if (!simple) {

				InputStream is = new java.io.BufferedInputStream(request.getInputStream());

				bytes = IOUtils.toByteArray(is);

				if (bytes == null || bytes.length == 0) {

					String content = "";

					Map<String, String[]> param = request.getParameterMap();

					if (request != null) {
						for (String p : param.keySet()) {

							String vals[] = param.get(p);

							for (String pa : vals) {
								content += p + "=" + URLEncoder.encode(pa, "ISO-8859-1") + "&";
							}
						}
					}

					is = new ByteArrayInputStream(content.getBytes());

				} else {

					is = new ByteArrayInputStream(bytes);

				}

				byte[] b = new byte[4096];
				int length = 0;

				if (hasBody) {
					OutputStream op = urlConn.getOutputStream();
					while ((length = is.read(b)) != -1) {
						op.write(b, 0, length);
					}
					if (length >= 0) {
						op.flush();
						op.close();
					}
				}

			}

			// Leer...

			Pagina pagina = new Pagina();

			InputStream input = null;

			int responseCode = urlConn.getResponseCode();
			pagina.setStatus(responseCode);

			if (responseCode == urlConn.HTTP_MOVED_TEMP || responseCode == urlConn.HTTP_MOVED_PERM || responseCode == urlConn.HTTP_SEE_OTHER) {
				String location = urlConn.getHeaderField("Location");
				pagina.setRedirect(true);
				pagina.setLocation(location);
				pagina.setBytes(location.getBytes());
				return pagina;
			} else if (responseCode == urlConn.HTTP_OK) {

				pagina.setContentDisposition(urlConn.getHeaderField(STRING_CONTENT_DISPOSITION_HEADER));

				pagina.setContentType(urlConn.getContentType());

				input = urlConn.getInputStream();

				SimpleLogger.setDebug("OK urlConn -> " + urlConn);

			} else {

				DataInputStream dinput = new DataInputStream(urlConn.getErrorStream());
				BufferedReader reader = new BufferedReader(new InputStreamReader(dinput));

				String line;

				StringBuffer contenido = new StringBuffer();

				SimpleLogger.setError("Error urlConn -> " + urlConn);

				while ((line = reader.readLine()) != null) {
					contenido.append(line);
				}

				input = new ByteArrayInputStream(contenido.toString().getBytes());

			}

			bytes = IOUtils.toByteArray(input);

			input.close();

			pagina.setBytes(bytes);

			return pagina;

		} catch (Exception e) {
			Pagina pagina = new Pagina();
			pagina.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
			pagina.setBytes(e.getMessage().getBytes());
			SimpleLogger.setError("Error Fatal: ", e);
			return pagina;
		}

	}

	// -----------------------------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------------------

	public static void main(String[] args) {

	}

}

class Pagina {

	private byte[] bytes;
	private String contentType;
	private String contentDisposition;
	private Boolean redirect;
	private String location;
	private Integer status;

	public Pagina() {
		this.redirect = false;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] html) {
		this.bytes = html;
	}

	public String getContentType() {

		if (contentType == null) {
			return "text/html";
		}

		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getContentDisposition() {
		return contentDisposition;
	}

	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}

	public Boolean getRedirect() {
		return redirect;
	}

	public void setRedirect(Boolean redirect) {
		this.redirect = redirect;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
