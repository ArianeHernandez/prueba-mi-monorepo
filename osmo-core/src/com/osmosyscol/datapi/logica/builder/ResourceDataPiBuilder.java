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

import com.osmosyscol.datapi.constantes.VariablesAplicacion;
import com.osmosyscol.datapi.logica.dto.ServicioDataPi;

/**
 * Construccion de los servicios de dataPI usados para acceder recursos
 * @author jmgoyesc
 *
 */
public class ResourceDataPiBuilder extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	// #region 01 logger declaration ---------------------
	private static final Logger logger = LoggerFactory.getLogger(ResourceDataPiBuilder.class);
	// #endregion 01 -------------------------------------
	
	private static final long serialVersionUID = -1308771537641874364L;

	private static List<String> globalpaths = null;

	public static void loadServices(List<String> paths) {
		globalpaths = paths;
		loadServices();
	}

	public static void loadServices() {
		Map<String, ServicioDataPi> listadoServicios = new HashMap<String, ServicioDataPi>();

		logger.info("Cargado recursos datapi...");

		for (String path : globalpaths) {
			
			logger.info("Resource DataPI --> Path: " + path);
			
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
							logger.error("(" + ResourceDataPiBuilder.class + "). Error al cargar el servicio (" + archivo.getName() + "). Excepcion=" + e.getMessage());
						}
					}
				}
			}
		}

		VariablesAplicacion.getInstance().setListadoRecursosDataPI(listadoServicios);
	}

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
			ResourceDataPiBuilder.loadServices();

		} catch (Exception e) {
			logger.error("Fallo ela recarga de servicios Datapi de la aplicacion. ", e);
		}
	}

}
