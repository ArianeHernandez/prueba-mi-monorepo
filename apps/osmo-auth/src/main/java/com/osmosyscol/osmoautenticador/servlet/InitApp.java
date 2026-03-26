package com.osmosyscol.osmoautenticador.servlet;

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
import com.osmosyscol.osmoautenticador.autenticador.InitAutenticadores;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;
import com.osmosyscol.persistencia.dao.ibatis.core.InitDBConfig;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class InitApp extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	static final long serialVersionUID = 1L;

	
	public void init() throws ServletException {
		startUp();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		startUp();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		startUp();
	}

	// -----------------------------------------------------------

	public static void startUp() {

		try {
			
			SimpleLogger.setAppname("OSMOAUTENTICADOR");
			SimpleLogger.setLoggerEnabled(false);

			ParametrosInicio.loadProperties("OSMOAUTENTICADOR_HOME");

			DaoConfig.setResourceDao("com/osmosyscol/osmoautenticador/persistencia/dao/ibatis/config/dao.xml");
			DaoConfig.setResourceSqlMapConfig("com/osmosyscol/osmoautenticador/persistencia/dao/ibatis/config/sql-map-config.xml");

			InitDBConfig.cargarConfiguraciones("OSMOAUTENTICADOR_HOME");

			InitAutenticadores.cargarAutenticadores("OSMOAUTENTICADOR_HOME");

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		try {

			// ----------------------- WebServices

			List<String> servicespath = new ArrayList<String>();
			servicespath.add(ContextInfo.getInstance().getDiskPathForResource("WEB-INF/servicios_datapi"));
			ServicioDataPiBuilder.loadServices(servicespath);

		} catch (Exception e) {
			SimpleLogger.setError("Fallo en la inicializacion de los webservices datapi. ", e);
		}
	}

}