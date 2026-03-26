package com.osmosyscol.datasuite.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.osmosyscol.commons.crypto.Crypto;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.AutenticacionServicio;
import com.osmosyscol.commons.utils.JavaToXML;
import com.osmosyscol.commons.utils.Navegador;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.config.Constantes;
import com.osmosyscol.datasuite.filtrosql.FiltroSqlDatasuite;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;
import com.osmosyscol.persistencia.dao.ibatis.core.InitDBConfig;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

import co.htsoft.htsauditoria.client.HTSAuditoriaServicio;

public class InitApp extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	static final long serialVersionUID = 1L;

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

	// --------------------------------------------------------------------------

	public static void startUp() {

		Locale.setDefault(new Locale("es", "CO"));
		System.setProperty("file.encoding", "ISO-8859-1");
		System.setProperty("javax.servlet.request.encoding", "ISO-8859-1"); 
		
		System.setProperty("java.awt.headless", "true");
		
		//Se activa log4j
		SimpleLogger.setLoggerEnabled(false);
		
		try {

			ParametrosInicio.loadProperties("DATASUITE");

			
			// configiracion de auditoria
			{
				HTSAuditoriaServicio.setApp(AutenticacionServicio.NOMBRE_APLICACION);
				HTSAuditoriaServicio.setUrl(ParametrosInicio.getProperty("auditoria.endpoint"));
			}
			
			
			String ct = ParametrosInicio.getProperty("crypto.type");

			if (ct != null) {
				Crypto.setTipoEncripcion(Integer.parseInt(ct));
			}

			// ---------------------

			DaoConfig.setResourceDao("com/osmosyscol/datasuite/persistencia/dao/ibatis/config/dao.xml");
			DaoConfig.setResourceSqlMapConfig("com/osmosyscol/datasuite/persistencia/dao/ibatis/config/sql-map-config.xml");

			InitDBConfig.cargarConfiguraciones();

			// ---------------------
			// Solo se cargan los archivos conf en modelatos

			if (com.osmosyscol.datasuite.logica.constantes.Constantes.NOMBRE_APLICACION_MODELATOS.equalsIgnoreCase(AutenticacionServicio.NOMBRE_APLICACION)) {
				String createconf = ParametrosInicio.getProperty("datasuite.createconf");
				if (StringUtils.esVerdad(createconf)) {
					crearConfDataPi();
				} else {
					SimpleLogger.setWarn("Los archivos .conf no han sido generados (datasuite.createconf).");
				}
			}

			FiltroSqlDatasuite.registrarFiltro();

			// Si es webdata se configura el formato de fecha
			if (com.osmosyscol.datasuite.logica.constantes.Constantes.NOMBRE_APLICACION_WEBDATA.equalsIgnoreCase(AutenticacionServicio.NOMBRE_APLICACION)) {
				JavaToXML.setDate_format(Constantes.FORMATO_FECHA);
			}

			String webdata_endpoint = ParametrosInicio.getProperty("webdata.endpoint");

			if (!StringUtils.esVacio(webdata_endpoint)) {
				Navegador.configure(webdata_endpoint);
			}

			// ---------------------
			// Se cargan los reportes

			if (com.osmosyscol.datasuite.logica.constantes.Constantes.NOMBRE_APLICACION_WEBDATA.equalsIgnoreCase(AutenticacionServicio.NOMBRE_APLICACION)) {
				InitReporteServices.startUp();
			}

			// ---------------------

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
	}

	// --------------------------------------------------------------------------

	public static void crearConfDataPi() {

		try {

			// ---------- Prepara la carpeta

			String DATASUITE = System.getenv(ParametrosInicio.SYSVAR);
			if (DATASUITE == null || DATASUITE.equals("")) {
				SimpleLogger.setError("Se debe configurar la Variable de entorno DATASUITE.");
				return;
			}

			String nombrecarpeta = DATASUITE + "/servicios";
			new File(nombrecarpeta).mkdirs();

			File carpeta = new File(nombrecarpeta);

			for (File archivo : carpeta.listFiles()) {
				if (archivo.getName().lastIndexOf(".conf") > 0) {
					archivo.delete();
				}
			}

			// ----------

			/*
			 * List<Formato> formatos = FormatoServicio.getInstance().obtenerFormatosPorTipo (Constantes.FORMATO_SALIDA);
			 * 
			 * for (Formato formato : formatos) { ConfServicio.getInstance().crearFormatoConf(formato); ConfServicio.getInstance().crearFormatoConf_Reg(formato); }
			 * 
			 * List<Estructura> estructuras = EstructuraServicio.getInstance().obtenerEstructurasPorEstado (Constantes.ESTRUCTURA_ESTADO_ACTIVA);
			 * 
			 * for (Estructura estructura : estructuras) { EstructuraServicio.getInstance().crearEstructuraConf(estructura); }
			 */

		} catch (Throwable me) {
		}

		InitWebServices.startUp();

	}

}