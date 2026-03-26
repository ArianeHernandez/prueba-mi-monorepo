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
import com.osmosyscol.datasuite.modelatos.logica.servicios.ReporteServicio;
import com.osmosyscol.datasuite.reportedim.builder.ServicioReporteDimBuilder;
import com.osmosyscol.datasuite.reportedim.servicio.RDServicio;

public class InitReporteServices extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
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
			
			
			servicespath.add(ContextInfo.getInstance().getDiskPathForResource("WEB-INF/reportes"));
			servicespath.add(InitApp.class.getResource("/").getPath() + "com/osmosyscol/datasuite/reportesdinamicos");

			ServicioReporteDimBuilder.loadServices(servicespath);
			
			ReporteServicio.getInstance().crearServiciosDataPi();
			
			RDServicio.clear();
			
		} catch (Exception e) {
			SimpleLogger.setError("Fallo en la inicializacion de la aplicacion. ", e);
		}
	}

	// ----------------------------------------------------

}