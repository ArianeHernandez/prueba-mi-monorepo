package com.osmosyscol.commons.rpc.jsonrpc;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.apache.cocoon.environment.Session;
import org.xml.sax.InputSource;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.AutenticacionServicio;
import com.osmosyscol.commons.servlet.ContextInfo;

public class JSONCallBackAutorizacion implements InvocationCallback {

	public static boolean AUTORIZAR_JSON = false;

	private static List<String> clasesPublicas = null;

	private static void inicializarServiciosPublicos() {

		clasesPublicas = new ArrayList<String>();

		clasesPublicas.add("com.osmosyscol.commons.servicio.json.Core_JsonServicio");

		XPathFactory factory = XPathFactory.newInstance();
		XPath xPath = factory.newXPath();
		File xmlDocument = null;
		String filename = ContextInfo.getInstance().getDiskPathForResource("WEB-INF/json-services.xml");

		try {
			xmlDocument = new File(filename);
			if (xmlDocument.exists()) {

				InputSource inputSource = new InputSource(new FileInputStream(xmlDocument));
				Integer cantidad = Integer.parseInt(xPath.evaluate("count(/json-services/service)", inputSource));

				for (int i = 1; i <= cantidad; i++) {

					try {

						String className = xPath.evaluate("/json-services/service[" + i + "]/@class", new InputSource(new FileInputStream(xmlDocument)));

						clasesPublicas.add(className);

					} catch (Throwable e) {
						SimpleLogger.setError("Error evaluando xpath", e);
					}
				}

			}
		} catch (Exception e) {
			SimpleLogger.setError("Error identificando servicio json publicos", e);
		}

	}

	public void preInvoke(Object context, Object instance, Method m, Object[] arguments) throws Exception {

		if (AUTORIZAR_JSON) {

			if (clasesPublicas == null) {
				inicializarServiciosPublicos();
			}

			try {
				if (context instanceof HttpServletRequest) {

					String clase = m.getDeclaringClass().getCanonicalName();

					if (clasesPublicas.contains(clase)) {
						return;
					}

					// -------------

					HttpSession session = ((HttpServletRequest) context).getSession(false);

					if (session != null) {
						String rutaMetodo = clase + "." + m.getName();

						Session csession = (Session) session.getAttribute("cocoon-session");

						Boolean resultadoAutenticacion = false;

						if (AutenticacionServicio.getInstance().validarTiempoInactividad(csession)) {
							resultadoAutenticacion = AutenticacionServicio.getInstance().autorizarUrl(csession, rutaMetodo);
						}

						if (resultadoAutenticacion) {
							return;
						} else {
							throw new Exception("El usuario no tiene acceso al Json");
						}

					} else {
						throw new Exception("El usuario no tiene sesion, ni acceso al Json");
					}

				}
			} catch (Exception e) {
				SimpleLogger.setInfo("El usuario no tiene acceso al Json");
				throw e;
			}
		}

	}

	public void postInvoke(Object context, Object instance, Method m, Object result) {
	}

}
