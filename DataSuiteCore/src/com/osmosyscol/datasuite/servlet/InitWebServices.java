package com.osmosyscol.datasuite.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servlet.ContextInfo;
import com.osmosyscol.datapi.logica.builder.ServicioDataPiBuilder;

public class InitWebServices extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	static final long serialVersionUID = 1L;

	// ----------------------------------------------------

	@Override
	public void init() throws ServletException {
		startUp();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		startUp();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		startUp();
	}

	// ----------------------------------------------------

	public static void startUp() {

		try {

			// ----------------------- WebServices

			List<String> servicespath = new ArrayList<String>();
			servicespath.add(System.getenv("DATASUITE") + "/servicios");
			servicespath.add(System.getenv("DATASUITE") + "/servicios_generales");
			servicespath.add(ContextInfo.getInstance().getDiskPathForResource("WEB-INF/dpservicios"));
			servicespath.add(ContextInfo.getInstance().getDiskPathForResource("WEB-INF/dpservicios/actualizaestados"));
			servicespath.add(ContextInfo.getInstance().getDiskPathForResource("WEB-INF/dpservicios/bancos"));

			
			ServicioDataPiBuilder.loadServices(servicespath);

		} catch (Exception e) {
			SimpleLogger.setError("Fallo en la inicializacion de la aplicacion. ", e);
		}
	}

	// ----------------------------------------------------

}