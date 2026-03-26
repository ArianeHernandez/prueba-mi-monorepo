package com.osmosyscol.datasuite.reportedim.builder;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.FileUtils;
import com.osmosyscol.commons.utils.JavaToXML;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ReporteServicio;
import com.osmosyscol.datasuite.reportedim.dto.ServicioReporteDim;

public class ServicioReporteDimBuilder extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	private static final long serialVersionUID = -3543254758464836364L;

	private static List<String> globalpaths = null;

	private static Map<String, ServicioReporteDim> listadoServicios = new HashMap<String, ServicioReporteDim>();

	public static ServicioReporteDim getServicio(String ID) {
		return listadoServicios.get(ID);
	}
	
	public static ServicioReporteDim getCopiaServicio(String ID) {
		return new ServicioReporteDim(listadoServicios.get(ID));
	}

	public static Set<String> getListadoServicios() {
		return listadoServicios.keySet();
	}
	
	public static void loadServices(List<String> paths) {
		globalpaths = paths;
		
		SimpleLogger.setInfo("Cargado servicios reportedim de archivos en globalpaths [ServicioReporteDimBuilder]...");
		loadServices();
		
		SimpleLogger.setInfo("Cargado servicios reportedim de BD [ServicioReporteDimBuilder]...");
		cargaReportesBaseDatos();
	}

	public static void loadServices() {
		
		listadoServicios.clear();

		for (String path : globalpaths) {

			SimpleLogger.setInfo("ReporteDim --> Path: " + path);

			File file = new File(path);

			File[] archivos = file.listFiles();
			if (archivos != null) {
				for (int i = 0; i < archivos.length; i++) {
					File archivo = archivos[i];
					if (archivo.isFile() && archivo.getName().endsWith(".conf")) {

						SimpleLogger.setInfo(" ReporteDim --> File: " + archivo.getName());

						try {
							ServicioReporteDim servicioReporteDim = ServicioReporteDimBuilder.crearServicio(archivo);
							listadoServicios.put(servicioReporteDim.getId(), servicioReporteDim);
						} catch (Exception e) {
							SimpleLogger.setError("(" + ServicioReporteDimBuilder.class + "). Error al cargar el servicio (" + archivo.getName() + "). Excepcion=" + e.getMessage());
						}
					}
				}
			}
		}

	}

	/*
	 * Lee los reportes en Base de Datos y los agrega a la lista de servicios
	 */
	private static void cargaReportesBaseDatos() {
		
		// Obtener Reportes en Base
		List<ServicioReporteDim> serviciosEnBase = ReporteServicio.getInstance().obtenerReportes();
		
		// Guardar los reportes en el listado de servicios
		for(ServicioReporteDim srd : serviciosEnBase){
			if(!listadoServicios.containsKey(srd.getId())){
				listadoServicios.put(srd.getId(), srd);
			}else{
				SimpleLogger.setInfo("Ya existe un servicio llamado:\n" + srd.getId() + "\nen la lista de servicios|reportes");
			}
			
		}
		
	}
	
	/*
	 * Este metodo busca un elemento en la lista de servicios y lo quita...
	 */
	public static boolean quitarReporteBaseDatos(String id_reporte){
		if(listadoServicios.containsKey(id_reporte)){
			if(listadoServicios.remove(id_reporte) != null)
				return true;
			else
				return false;
		}else{
			SimpleLogger.setInfo("NO existe un servicio con id:\n" + id_reporte + "\nen la lista de servicios|reportes");
			return false;
		}
	}
	
	/*
	 * Este metodo agrega a la lista de Servicios un nuevo servicio reporte creado a ser utilizado
	 */
	public static boolean cargaReporteBaseDatos(ServicioReporteDim srd){
		if(srd!=null && srd.getId() != null){
			if(!listadoServicios.containsKey(srd.getId())){
				listadoServicios.put(srd.getId(), srd);
				return true;
			}else{
				SimpleLogger.setInfo("Ya existe un servicio llamado:\n" + srd.getId() + "\nen la lista de servicios|reportes");
				return false;
			}
		}else{
			SimpleLogger.setInfo("Problema con el agregando a lista en ServicioReporteDimBuilder.cargaReporteBaseDatos servicio llamado:\n" + srd.getId() + "\nen la lista de servicios|reportes");
			return false;
		}
	}

	public static ServicioReporteDim crearServicio(File archivo) throws Exception {

		String[] contenido = FileUtils.getContentFileToArray(archivo.getAbsolutePath());

		if (contenido == null || contenido.length == 0) {
			return null;
		}

		Map<String, String> mapRequest = new HashMap<String, String>();

		for (String linea : contenido) {

			linea = linea.replace('\t', ' ').trim();

			if (linea.length() > 0 && linea.charAt(0) != '#') {
				int a = linea.indexOf(" ");

				String param = "ServicioReporteDim." + linea.substring(0, a).trim();
				String valor = linea.substring(a).trim();

				mapRequest.put(param, valor);
			}
		}

		String nombreparametro = "ServicioReporteDim";
		String classname = "com.osmosyscol.datasuite.reportedim.dto.ServicioReporteDim";
		return (ServicioReporteDim) JavaToXML.createObject(classname, Class.forName(classname), nombreparametro, mapRequest, null, null);

	}

	// #########################################################

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

			SimpleLogger.setInfo("Recargando Servicios ReporteDim.");
			ServicioReporteDimBuilder.loadServices();

		} catch (Exception e) {
			SimpleLogger.setError("Fallo ela recarga de servicios ReporteDim de la aplicacion. ", e);
		}
	}

}
