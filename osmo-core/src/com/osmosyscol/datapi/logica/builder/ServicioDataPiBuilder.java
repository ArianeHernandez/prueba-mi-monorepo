package com.osmosyscol.datapi.logica.builder;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.osmosyscol.commons.utils.FileUtils;
import com.osmosyscol.commons.utils.JavaToXML;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datapi.constantes.VariablesAplicacion;
import com.osmosyscol.datapi.logica.dto.ServicioDataPi;
import com.osmosyscol.datapi.logica.servicios.LogServicio;
import com.osmosyscol.datapi.persistencia.dao.ibatis.core.ManejadorConsultas;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class ServicioDataPiBuilder extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	// #region 01 logger declaration ---------------------
	private static final Logger logger = LoggerFactory.getLogger(LogServicio.class);
	// #endregion 01 -------------------------------------
	
	private static final long serialVersionUID = -1308771537641874364L;

	private static List<String> globalpaths = null;

	public static void loadServices(List<String> paths) {
		globalpaths = paths;
		loadServices();
	}

	public static void loadServices() {
		Map<String, ServicioDataPi> listadoServicios = new HashMap<String, ServicioDataPi>();

		logger.info("Cargado servicios datapi...");

		for (String path : globalpaths) {
			
			logger.info("Datapi --> Path: " + path);
			
			File file = new File(path);

			File[] archivos = file.listFiles();
			if (archivos != null) {
				for (int i = 0; i < archivos.length; i++) {
					File archivo = archivos[i];
					if (archivo.isFile() && archivo.getName().endsWith(".conf")) {

						logger.info(" Datapi --> File: " + archivo.getName());

						try {
							ServicioDataPi servicioDataPi = ServicioDataPiBuilder.crearServicio(archivo);
							listadoServicios.put(servicioDataPi.getNombre(), servicioDataPi);
						} catch (Exception e) {
							logger.error("(" + ServicioDataPiBuilder.class + "). error al cargar el servicio (" + archivo.getName() + "). Excepcion=" + e.getMessage());
						}
					}
				}
			}
		}

		VariablesAplicacion.getInstance().setListadoServicios(listadoServicios);

		String precarga = ParametrosInicio.getProperty("datapi.precarga");

		if (precarga == null || StringUtils.esVerdad(precarga)) {
			ManejadorConsultas.precargaMetadataProcedimientos();
		}
	}

	public static ServicioDataPi crearServicio(File archivo) throws Exception {

		String[] contenido = FileUtils.getContentFileToArray(archivo.getAbsolutePath());

		if (contenido == null || contenido.length == 0) {
			return null;
		}

		Map<String, String> mapRequest = new HashMap<String, String>();

		for (String linea : contenido) {

			linea = linea.replace('\t', ' ').trim();

			if (linea.length() > 0 && linea.charAt(0) != '#') {
				int a = linea.indexOf(" ");

				String param = "ServicioDataPi." + linea.substring(0, a).trim();
				String valor = linea.substring(a).trim();

				mapRequest.put(param, valor);
			}
		}

		String nombreparametro = "ServicioDataPi";
		String classname = "com.osmosyscol.datapi.logica.dto.ServicioDataPi";
		return (ServicioDataPi) JavaToXML.createObject(classname, Class.forName(classname), nombreparametro, mapRequest, null, null);

	}

	// #########################################################

	// ----------------------------------------------------

	
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

			logger.info("Recargando Servicios Datapi.");
			ServicioDataPiBuilder.loadServices();

		} catch (Exception e) {
			logger.error("Fallo ela recarga de servicios Datapi de la aplicacion. ", e);
		}
	}

}
